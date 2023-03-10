package codetest.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Advice for handling all InvalidLocationException
 */
@ControllerAdvice
public class InvalidLocationExceptionHandler {

    /**
     * handle all InvalidLocationException, return error message to user
     * @param e Exception
     * @return error message
     */
    @ExceptionHandler(value = InvalidLocationException.class)
    @ResponseBody
    public String exceptionHandling(InvalidLocationException e) {
        System.out.println(e);
		return e.getMessage();
    }

}
