package restSumozPackage;

import org.codehaus.jackson.annotate.JsonProperty;

public class ForecastObj {
	private String DATE;
	private double TMax;
	private double TMin;
	
	public ForecastObj() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ForecastObj(String dATE, double tMax, double tMin) {
		super();
		DATE = dATE;
		TMax = tMax;
		TMin = tMin;
	}
	@JsonProperty("DATE")
	public String getDATE() {
		return DATE;
	}
	
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	@JsonProperty("TMAX")
	public double getTMax() {
		return TMax;
	}
	public void setTMax(double tMax) {
		TMax = tMax;
	}
	@JsonProperty("TMIN")
	public double getTMin() {
		return TMin;
	}
	public void setTMin(double tMin) {
		TMin = tMin;
	}
	
	
	
}
