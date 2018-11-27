package netcracker.analyzer.output;

import netcracker.analyzer.AnalyzerResult;
import netcracker.sorters.AbstractSorter;
import netcracker.sorters.HalfDivisionSort;
import netcracker.sorters.Sorter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
            for(Map.Entry<AbstractSorter, AnalyzerResult> sorterResultEntry : entry.getValue().entrySet()){

                if(flagNew){
                    int rowIndex = 0;
                    sheet.createRow(0).createCell(0).setCellValue("Size");
                    for(Integer size : sorterResultEntry.getValue().getElapsedTime().keySet()){
                        XSSFRow row = sheet.createRow(++rowIndex);
                        row.createCell(0).setCellValue(size);
                    }
                    flagNew = false;
                }

                AbstractSorter sorter = sorterResultEntry.getKey();
                AnalyzerResult result = sorterResultEntry.getValue();
                int sorterColumn = createSorterColumn(sheet, sorter);

                for(Map.Entry<Integer, Long> sizeResult : result.getElapsedTime().entrySet()){
                    XSSFRow row = sheet.getRow(findSizeIndex(sheet, sizeResult.getKey()));
                    row.createCell(sorterColumn).setCellValue(sizeResult.getValue());
                }

            }

        }
        workbook.write(new FileOutputStream(excelFile));

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
