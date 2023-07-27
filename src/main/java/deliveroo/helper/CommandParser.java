package deliveroo.helper;

public class CommandParser extends BaseParser{
    public CommandParser() {
        super(-1, -1, "command");
    }

    @Override
    public String parse(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        for(int i=name.length();i<=nameColumnlength;i++) {
            sb.append(" ");
        }
        sb.append(input);
        return sb.toString();
    }
}
