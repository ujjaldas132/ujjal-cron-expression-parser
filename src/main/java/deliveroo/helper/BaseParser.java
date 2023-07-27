package deliveroo.helper;

import java.util.StringJoiner;

 abstract class BaseParser {
    public int low;
    public int high;
    public String name;
    public final int nameColumnlength = 14;

    public BaseParser(int low, int high, String name) {
        this.low = low;
        this.high = high;
        this.name = name;
    }

    public String parse( String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        for(int i=name.length();i<=nameColumnlength;i++) {
            sb.append(" ");
        }
        sb.append(parseTimes(input));
        return sb.toString();
    }

    public String parseTimes(String input) {

        if(input.strip().contains(",")) {
            return commaParser(input);
        } else if(input.strip().contains("/")) {
            return stepParser(input);
        } else if(input.strip().contains("-")) {
            return rangeParser(input);
        } else if(input.strip().equals("*")) {
            return universeParser();
        }

        // validation
        validation(input);

        return input;
    }

     /**
      * Method to parse comma separted values.
      *
      * @param input
      * @return
      */
    public String commaParser(String input) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        String[] inputs = input.split(",");
        for(String inp : inputs) {
            stringJoiner.add(parseTimes(inp));
        }
        return stringJoiner.toString();
    }

     /**
      * Method to parse value with steps.
      *
      * @param input
      * @return
      */
    public String stepParser(String input) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        int index = input.indexOf("/");
        int start =0;
        if(input.charAt(index -1)=='*')
            start = this.low;
        else{
            // Validation
            validation(input.substring(0, index));
            low = Integer.valueOf(input.substring(0, index));
        }

        int step = Integer.valueOf(input.substring(index+1, input.length()));

        for(int i = start;i<=this.high;i=i+step) {
            stringJoiner.add(String.valueOf(i));
        }
        return stringJoiner.toString();
    }

     /**
      * Method to parse values for a range.
      *
      * @param input
      * @return
      */
    public String rangeParser(String input) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        int length = input.length();
        int index = input.indexOf("-");

        // value validation
        validation(input.substring(0, index));
        validation(input.substring( index+1, length));

        int start = Integer.valueOf(input.substring(0, index));
        int end = Integer.valueOf(input.substring( index+1, length));

        for(int i=start;i<=end;i++) {
            stringJoiner.add(String.valueOf(i));
        }
        return stringJoiner.toString();
    }

     /**
      * Method to parse all the values.
      *
      * @return
      */
    public String universeParser() {
        StringJoiner stringJoiner = new StringJoiner(" ");
        for(int i=this.low;i<=this.high;i++) {
            stringJoiner.add(String.valueOf(i));
        }
        return stringJoiner.toString();
    }

     /**
      * Method to validate where we enter correct value for names.
      *
      * @param value
      */
    private void validation(String value) {
        try {
            int integerValue = Integer.valueOf(value);
            if( integerValue >= low && integerValue <= high) {
                return;
            }
            throw new RuntimeException(value + " is not valid value for " + name);

        } catch (NumberFormatException e) {
            throw new NumberFormatException(value + " is not correct input for " + name);
        }
    }
}
