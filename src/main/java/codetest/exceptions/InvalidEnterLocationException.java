package codetest.exceptions;

import codetest.PO.Location;

public class InvalidEnterLocationException extends InvalidLocationException {
    Location location;

    public InvalidEnterLocationException(Location location) {
        super("location cannot enter:" + location);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
