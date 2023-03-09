package codetest.PO;

import java.util.Arrays;

public class Location {

    private int id;

    private String name;
    private double lat;
    private double lng;
    private Route[] routes;

    private String devcomment;

    private boolean exit =true;

    private boolean enter = true;

    public Location setId(int id) {
        this.id = id;
        return this;
    }
    public Location setDevcomment(String devcomment) {
        this.devcomment = devcomment;
        return this;
    }

    public Location setName(String name) {
        this.name = name;
        return this;
    }

    public Location setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public Location setLng(double lng) {
        this.lng = lng;
        return this;
    }
    public Location setRoutes(Route[] routes) {
        this.routes = routes;
        return this;
    }

    public Location setExit(boolean exit) {
        this.exit = exit;
        return this;
    }

    public Location setEnter(boolean enter) {
        this.enter = enter;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getDevcomment() {
        return devcomment;
    }



    public String getName() {
        return name;
    }



    public double getLat() {
        return lat;
    }



    public double getLng() {
        return lng;
    }



    public Route[] getRoutes() {
        return routes;
    }

    public boolean isExit() {
        return exit;
    }


    public boolean isEnter() {
        return enter;
    }


    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", routes=" + Arrays.toString(routes) +
                ", devcomment='" + devcomment + '\'' +
                ", exit=" + exit +
                ", enter=" + enter +
                '}';
    }
}
