package codetest.exceptions;

import codetest.PO.Location;


public class UnexpectedErrorException extends InvalidLocationException {
    Location start;
    Location end;

    public UnexpectedErrorException(Location start, Location end) {
        super("unexpected error with locaction :" + start.getName()+" and "+end.getName());
        this.start = start;
        this.end = end;
    }

    public Location getStartLocation() {
        return start;
    }
    
    public Location getEndLocation() {
        return end;
    }
}
