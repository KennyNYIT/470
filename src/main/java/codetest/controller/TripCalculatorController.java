package codetest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import codetest.TripCalculator;
import codetest.PO.Result;
import codetest.exceptions.InvalidLocationException;

/**
 * Rest Controller for handling request
 */
@RestController
public class TripCalculatorController {

    TripCalculator tc;

    public TripCalculatorController() {
        super();
    }

    @Autowired
    public void setTc(TripCalculator tc) {
        this.tc = tc;
    }


    /**
     *  Calculate distance and cost of two given locations
     * @param start name of start location
     * @param end name of end location
     * @return  calculated distance and cost
     * @throws InvalidLocationException
     */
    @GetMapping("/location/{start}/{end}")
    @ResponseBody
    public Result calculate(@PathVariable String start, @PathVariable String end) throws InvalidLocationException {
        Result result;
        result = tc.tripCalculate(start, end);
        return result;

    }

    /**
     * Calculate distance and cost of two given locations
     * @param start ID of start location
     * @param end ID of end location
     * @return  calculated distance and cost
     * @throws InvalidLocationException
     */
    @GetMapping("/location/id/{start}/{end}")
    @ResponseBody
    public Result calculate(@PathVariable int start, @PathVariable int end) throws InvalidLocationException {
        Result result;
        result = tc.tripCalculate(start, end);
        return result;
    }

}
