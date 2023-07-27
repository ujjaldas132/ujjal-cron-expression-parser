package deliveroo;

import deliveroo.helper.*;

import java.util.StringJoiner;

public class Parser {
    private static final MinuteParser minuteParser = new MinuteParser();
    private static final HourParser hourParser = new HourParser();
    private static final DayParser dayParser = new DayParser();
    private static final MonthParser monthParser = new MonthParser();
    private static final WeekParser weekParser = new WeekParser();
    private static final CommandParser commandParser = new CommandParser();

    public static void main(String[] args) {
        int argsLength = args.length;
        if(argsLength ==0)
            throw new RuntimeException("Insufficient argument length.");
        Parser parser = new Parser();
        String output = parser.parser(args[0]);
        System.out.println(output);
    }

    public String parser(String input) {

        String[] params = input.split("\\s+");
        int paramsLength = params.length;
        if(paramsLength != 6) {
            throw new RuntimeException("Incorrect length of cron expression");
        }

        StringJoiner output  = new StringJoiner("\n");
        for(int i = 0;i<=5;i++) {
            switch (i) {
                case 0:
                    output.add(minuteParser.parse(params[i]));
                    break;
                case 1:
                    output.add(hourParser.parse(params[i]));
                    break;
                case 2:
                    output.add(dayParser.parse(params[i]));
                    break;
                case 3:
                    output.add(monthParser.parse(params[i]));
                    break;
                case 4:
                    output.add(weekParser.parse(params[i]));
                    break;
                default:
                    output.add(commandParser.parse(params[i]));
            }
        }
        return output.toString();
    }
}