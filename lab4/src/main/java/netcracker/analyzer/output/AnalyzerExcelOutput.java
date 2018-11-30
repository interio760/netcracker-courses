package netcracker.analyzer.output;

import netcracker.analyzer.AnalyzerResult;
import netcracker.sorters.AbstractSorter;
import netcracker.sorters.HalfDivisionSort;
import netcracker.sorters.Sorter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyzerExcelOutput {

    public void writeToExcel(List<AnalyzerResult> list, File excelFile) throws IOException {
        if(list == null || excelFile == null || list.isEmpty()) throw new IllegalArgumentException();
        Map<String, Map<AbstractSorter, AnalyzerResult>> map = getOrderedMap(list);
        XSSFWorkbook workbook = new XSSFWorkbook();
        for(Map.Entry<String, Map<AbstractSorter, AnalyzerResult>> entry : map.entrySet()){
            XSSFSheet sheet = workbook.createSheet(entry.getKey());

            boolean flagNew = true;
            int rowIndex = 0;
            int sorterColumn = 0;
            for(Map.Entry<AbstractSorter, AnalyzerResult> sorterResultEntry : entry.getValue().entrySet()){

                if(flagNew){
                    sheet.createRow(0).createCell(0).setCellValue("Size");
                    for(Integer size : sorterResultEntry.getValue().getElapsedTime().keySet()){
                        XSSFRow row = sheet.createRow(++rowIndex);
                        row.createCell(0).setCellValue(size);
                    }
                    flagNew = false;
                }

                AbstractSorter sorter = sorterResultEntry.getKey();
                AnalyzerResult result = sorterResultEntry.getValue();
                sorterColumn = createSorterColumn(sheet, sorter);

                for(Map.Entry<Integer, Long> sizeResult : result.getElapsedTime().entrySet()){
                    XSSFRow row = sheet.getRow(findSizeIndex(sheet, sizeResult.getKey()));
                    row.createCell(sorterColumn).setCellValue(sizeResult.getValue());
                }
            }
            createLineChart(sheet, rowIndex + 1, sorterColumn + 1);
        }


        workbook.write(new FileOutputStream(excelFile));

    }

    private void createLineChart(XSSFSheet dataSheet, int rows, int columns){

        Drawing drawing = dataSheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 1, rows + 3, 8, 25);

        Chart chart = drawing.createChart(anchor);
        ChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.RIGHT);

        LineChartData data = chart.getChartDataFactory().createLineChartData();

        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(dataSheet, new CellRangeAddress(0, rows - 1, 0, 0));

        for(int i = 1; i < columns; i++){
            ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(dataSheet, new CellRangeAddress(0, rows - 1, i, i));
            LineChartSeries series1 = data.addSeries(xs, ys1);
            series1.setTitle(dataSheet.getRow(0).getCell(i).getStringCellValue());
        }

        chart.plot(data, bottomAxis, leftAxis);

        XSSFChart xssfChart = (XSSFChart) chart;
        CTPlotArea plotArea = xssfChart.getCTChart().getPlotArea();
        plotArea.getLineChartArray()[0].getSmooth();
        CTBoolean ctBool = CTBoolean.Factory.newInstance();
        ctBool.setVal(false);
        plotArea.getLineChartArray()[0].setSmooth(ctBool);
        for (CTLineSer ser : plotArea.getLineChartArray()[0].getSerArray()) {
            ser.setSmooth(ctBool);
        }

    }

    private int findSizeIndex(XSSFSheet sheet, int size){
        int rowIndex = 1;
        while(sheet.getRow(rowIndex) != null){
            XSSFRow row = sheet.getRow(rowIndex++);
            if(row.getCell(0).getRawValue().equals(size + ".0"))
                return --rowIndex;
        }
        throw new IllegalArgumentException();
    }

    private int createSorterColumn(XSSFSheet sheet, AbstractSorter sorter){
        XSSFRow row = sheet.getRow(0);
        if(row == null) row = sheet.createRow(0);
        int columnIndex = 1;
        while(row.getCell(columnIndex) != null) columnIndex++;
        XSSFCell cell = row.createCell(columnIndex);
        String sorterName;
        if(sorter.getClass() == HalfDivisionSort.class){
            HalfDivisionSort halfDivisionSorter = (HalfDivisionSort) sorter;
            sorterName = halfDivisionSorter.toString();
        }
        else sorterName = sorter.getClass().getAnnotation(Sorter.class).name();
        cell.setCellValue(sorterName);
        sheet.autoSizeColumn(columnIndex);
        return columnIndex;
    }

    private Map<String, Map<AbstractSorter, AnalyzerResult>> getOrderedMap(List<AnalyzerResult> list){
        // map (filler - sorter - size - result)
        Map<String, Map<AbstractSorter, AnalyzerResult>> map = new HashMap<>();
        for(AnalyzerResult result : list){
            if(map.containsKey(result.getFiller())){
                map.get(result.getFiller()).put(result.getSorter(), result);
            }
            else{
                Map<AbstractSorter, AnalyzerResult> entry = new HashMap<>();
                entry.put(result.getSorter(), result);
                map.put(result.getFiller(), entry);
            }
        }
        return map;
    }

}
