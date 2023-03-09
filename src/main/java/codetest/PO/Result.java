package codetest.PO;

public class Result {
    Double distance;
    Double cost;

    public Result() {
    }

    public Result(Double distance, Double cost) {
        this.distance = distance;
        this.cost = cost;
    }

    public Double getDistance() {
        return distance;
    }

    public Result setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public Double getCost() {
        return cost;
    }

    public Result setCost(Double cost) {
        this.cost = cost;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "distance=" + distance +
                ", cost=" + cost +
                '}';
    }
}
