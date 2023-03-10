package codetest;

import codetest.PO.Result;
import codetest.dao.LocationManager;
import codetest.exceptions.InvalidLocationException;
import codetest.exceptions.InvalidLocationIdException;
import codetest.util.JSONLocationBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    /**
     * entry point of this program
     * @param args
     */
    public static void main(String[] args) {
        try {
            String json = new String(Files.readAllBytes(Path.of(ClassLoader.getSystemResource("interchanges.json").toURI())), StandardCharsets.UTF_8);

            TripCalculator tc = new TripCalculator();
            tc.setLocationDAO(new LocationManager().setLocationById(new JSONLocationBuilder(json).getLocationList()));
            tc.setTripCacheManager(new TripCacheManager());
            consoleControl(tc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * create user interactive console
     * @param tripCalculator
     */
    public static void consoleControl(TripCalculator tripCalculator) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Reading data using readLine

        String exitKeyword = "exit";
        boolean exit = false;
        while (!exit) {
            try {
            	System.out.println("===============================================================");
            	System.out.println("To Exit, enter \"exit\" anytime");
            	System.out.println("===============================================================");
                System.out.println("please input start location");
                String start = reader.readLine();

                if (exitKeyword.equals(start)) {
                    exit = true;
                    break;
                }
                System.out.println("please input end location");
                String end = reader.readLine();

                if (exitKeyword.equals(end)) {
                    exit = true;
                    break;
                }
                Result result = tripCalculator.tripCalculate(start, end);
                System.out.println("===============================================================");
                System.out.println("from: " + start +", to: "+ end);
                System.out.println("distance: " + String.format("%.3f", result.getDistance()));
                System.out.println("cost: " + String.format("%.3f", result.getCost()));
                System.out.println("===============================================================");

            } catch (InvalidLocationIdException e) {
                System.out.println(e.toString());
            } catch (InvalidLocationException e) {
                System.out.println(e.toString());
            } catch (Exception e) {

            }
        }
        System.out.println("System exit gracefully");
    }
}
