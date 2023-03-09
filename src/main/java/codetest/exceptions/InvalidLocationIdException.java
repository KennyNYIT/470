package codetest.exceptions;

public class InvalidLocationIdException extends InvalidLocationException {
    int id;

    public InvalidLocationIdException(int id) {
        super("invalid ID:" + id);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
