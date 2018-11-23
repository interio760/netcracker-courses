package netcracker.analyzer;

import org.junit.Test;

public class AnalyzerTest {

    @Test(expected = IllegalArgumentException.class, timeout = 10000)
    public void analyzeDifficultyIllegalArgumentsTest_0(){
        new AnalyzerImpl().analyzeDifficulty();
    }

    @Test(expected = IllegalArgumentException.class, timeout = 10000)
    public void analyzeDifficultyIllegalArgumentsTest_1(){
        new AnalyzerImpl().analyzeDifficulty(null);
    }

}
