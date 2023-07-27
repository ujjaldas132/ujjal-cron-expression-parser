package deliveroo;


import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class parserTest {

    @Test
    public void parserTest() {
        Parser parser = new Parser();
        String input = "*/15 0 1,15,25-30 * 1-5 /usr/bin/find";
        String output = parser.parser(input);
        String[] namesTimesPairs = output.split("\n");
        assertEquals(6, namesTimesPairs.length);
        String[] minutesDetals = namesTimesPairs[0].split("\\s+");
        assertEquals(5, minutesDetals.length);
        for(int i=0;i<4;i++) {
            assertEquals(String.valueOf(i*15),
                    minutesDetals[i+1]);
        }
    }

    @Test(expected = RuntimeException.class)
    public void parserFailedTest() {
        Parser parser = new Parser();
        String input = "*/15 0 1,15,25-30 * 1-5";
        parser.parser(input);
    }

    @Test(expected = NumberFormatException.class)
    public void parserFailedWrongFormattedTest() {
        Parser parser = new Parser();
        String input = "*/15 0 1,15,25-a * 1-5 aa";
        parser.parser(input);
    }

    @Test(expected = RuntimeException.class)
    public void parserFailedWrongRangeTest() {
        Parser parser = new Parser();
        String input = "*/15 0 40 * 1-5 aa";
        parser.parser(input);
    }
}
