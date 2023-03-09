package codetest.exceptions;

import codetest.PO.Location;

public class InvalidExitLocationException extends InvalidLocationException {

    Location location;

    public InvalidExitLocationException(Location location) {
        super("location cannot exit:" + location);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
