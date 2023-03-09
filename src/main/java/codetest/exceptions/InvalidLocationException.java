package codetest.exceptions;

public abstract class InvalidLocationException extends Exception{

    public InvalidLocationException(String errorMessage){
        super(errorMessage);
    }
}
