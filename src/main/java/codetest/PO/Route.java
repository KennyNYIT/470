package codetest.PO;

public class Route {

    private int toId;
    private double distance;

    private boolean enter = true;
    private boolean exit = true;

    private String startDate;


    public Route setDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Route setEnter(boolean enter) {
        this.enter = enter;
        return this;
    }

    public Route setExit(boolean exit) {
        this.exit = exit;
        return this;
    }

    public Route setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public Route setToId(int toId) {
        this.toId = toId;
        return this;
    }

    public double getDistance() {
        return distance;
    }


    public boolean isEnter() {
        return enter;
    }


    public boolean isExit() {
        return exit;
    }


    public String getStartDate() {
        return startDate;
    }


    public int getToId() {
        return toId;
    }


    @Override
    public String toString() {
        return "Route{" +
                "toId=" + toId +
                ", distance=" + distance +
                ", enter=" + enter +
                ", exit=" + exit +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
