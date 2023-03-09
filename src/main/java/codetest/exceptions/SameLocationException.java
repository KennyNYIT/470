package codetest.exceptions;

import codetest.PO.Location;

public class SameLocationException extends InvalidLocationException {
    Location location;

    public SameLocationException(Location location) {
        super("begin and end cannot be the same:" + location.getName());
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
