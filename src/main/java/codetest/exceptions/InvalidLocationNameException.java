package codetest.exceptions;

public class InvalidLocationNameException extends InvalidLocationException {

    String name;

    public InvalidLocationNameException(String name) {
        super("invalid location name:" + name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
