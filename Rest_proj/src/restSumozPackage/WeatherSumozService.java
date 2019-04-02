package restSumozPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/supersumoz")
public class WeatherSumozService {
	
	
	@GET
	@Path("/sumoz")
	public String helloWeatherFile() 
	{
			return "Hello Sumoz Weather app";
	}
	
	@GET
	@Path("/csvtojson")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ForecastObj> Convert_CSV_to_JSON()
	{
		URL filePathWeather = getClass().getResource("dailyweather.csv");
		
		
		 BufferedReader readWeather = null;
		  
		 List<ForecastObj> ListObjectForWeather = new ArrayList<ForecastObj>();
		 List<DateOfWeather> ListOfDateWeather = new ArrayList<DateOfWeather>();
		 
		  try {
		   
		   String line = "";
		   readWeather = new BufferedReader(new FileReader(new File(filePathWeather.getPath())));
		   readWeather.readLine();
		   
		   while((line = readWeather.readLine()) != null) {
			   
		    String[] fieldForWeather = line.split(",");
		    
		    if(fieldForWeather.length > 0) {
		    ForecastObj weatherObj = new ForecastObj();
		     weatherObj.setDATE(fieldForWeather[0]);
		     weatherObj.setTMax(Double.parseDouble(fieldForWeather[1]));
		     weatherObj.setTMin(Double.parseDouble(fieldForWeather[2]));
		     ListObjectForWeather.add(weatherObj);
		     
		     DateOfWeather dateobj = new DateOfWeather();
		    dateobj.setDate(fieldForWeather[0]);
		    ListOfDateWeather.add(dateobj);
		     
		    }
		   }
		   
	} catch(IOException e)
		  {
			System.out.println("Exception: "+e);
		  }
	finally {
		
	}
		  
		  return ListObjectForWeather;
}
/*************************************/
	@GET
	@Path("/historical")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DateOfWeather> getWeatherDates()
	{
		URL filePath = getClass().getResource("dailyweather.csv");
		
		
		 BufferedReader reader = null;
		 List<DateOfWeather> ListDateWeather = new ArrayList<DateOfWeather>();
		 
		  try {
		   
		   String line = "";
		   reader = new BufferedReader(new FileReader(new File(filePath.getPath())));
		   reader.readLine();
		   
		   while((line = reader.readLine()) != null) {
		    String[] column = line.split(",");
		    
		    if(column.length > 0) {
		    
		     
		    	DateOfWeather dateWeatherobj = new DateOfWeather();
		    	dateWeatherobj.setDate(column[0]);
		    	ListDateWeather.add(dateWeatherobj);
		     
		    }
		   }
		   
		   
	} catch(IOException e)
		  {
			System.out.println("Exception: "+e);
		  }
	finally {
		
	}
		  
		  return ListDateWeather;
	}
/**************************************************************************************/
	
	@GET
	@Path("/historical/{getDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public ForecastObj getparticularDate(@PathParam("getDate") String date)
	{
		List<ForecastObj> resultCsvJson=Convert_CSV_to_JSON();
		
		ForecastObj resultOfjson= new ForecastObj();
		for(ForecastObj i: resultCsvJson)	
		{
			if(i.getDATE().equals(date))
			{
				resultOfjson=i;
				break;
			}
			
		}
		if(resultOfjson.getDATE()==null)
		{
			throw new WebApplicationException(404);
		}
	
			return resultOfjson;
	}
	
/******************************************************************************************/
	@POST
	@Path("/historical")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addDate(ForecastObj inWeatherdate)
	{	
		ForecastObj inputWeatherobj = new ForecastObj();
		ForecastBeanObj weatherbeanobj = new ForecastBeanObj();
		System.out.println(inWeatherdate);
		String json = "";
		try {
			
			inputWeatherobj.setDATE((String)inWeatherdate.getDATE());
			inputWeatherobj.setTMax((double)((int)inWeatherdate.getTMax()));
			inputWeatherobj.setTMin((double)((int)inWeatherdate.getTMin()));
			json = new Gson().toJson(inputWeatherobj);
			
			weatherbeanobj.addWeatherDateInformation(inputWeatherobj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(201).entity(json).build();
		
		
	
	}
/******************************************************************************************/
	
	@GET
	@Path("/forecast/{getForecast}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ForecastObj> getForecast(@PathParam("getForecast") String date)
	{
		ForecastBeanObj weatherbeanobject = new ForecastBeanObj();
		List<ForecastObj> weatherforecastobj = weatherbeanobject.getWeatherInformation(date);
		
		return weatherforecastobj;
	}	
	
/****************************************************************************************/
	
	@DELETE
	@Path("/historical/{deletedate}")
	public void deleteInfo(@PathParam("deletedate") String date){
		ForecastBeanObj weatherbeanobj = new ForecastBeanObj();
		weatherbeanobj.deleteWeatherRecord(date);
	}
	
/*********************************************************************************************/

}
