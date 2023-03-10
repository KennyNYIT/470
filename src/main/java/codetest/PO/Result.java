package codetest.PO;

public class Result {
	String start;
	String end;
	Double distance;
	Double cost;

	public Result() {
	}

	public Result(String start, String end, Double distance, Double cost) {
		super();
		this.start = start;
		this.end = end;
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

	public String getStart() {
		return start;
	}

	public Result setStart(String start) {
		this.start = start;
		return this;
	}

	public String getEnd() {
		return end;
	}

	public Result setEnd(String end) {
		this.end = end;
		return this;
	}

	@Override
	public String toString() {
		return "Result [start=" + start + ", end=" + end + ", distance=" + distance + ", cost=" + cost + "]";
	}

}
