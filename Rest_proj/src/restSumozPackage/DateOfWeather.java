package restSumozPackage;

import org.codehaus.jackson.annotate.JsonProperty;

public class DateOfWeather {
	private String Date;

	@JsonProperty("DATE")
	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

}
