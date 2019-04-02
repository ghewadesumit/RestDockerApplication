package restSumozPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForecastBeanObj {
	
	public void addWeatherDateInformation(ForecastObj inobj) {
		PrintWriter outobj = null;
		try {
			java.net.URL url = getClass().getResource("dailyweather.csv");
			File file = new File(url.getPath());
			FileWriter writeobj = new FileWriter(file, true);
			outobj = new PrintWriter(writeobj);
			StringBuilder stringobj = new StringBuilder();
			stringobj.append(inobj.getDATE());
			stringobj.append(",");
			stringobj.append(inobj.getTMax());
			stringobj.append(",");
			stringobj.append(inobj.getTMin());
			outobj.println(stringobj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (outobj != null){
					outobj.flush();
					outobj.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/****************************************************************************************/
	public List<ForecastObj> getWeatherInformation(String date) {
		String strline = "";
		BufferedReader buffer_read = null;
		List<ForecastObj> listobj = new ArrayList<>();
		try {
			URL url = getClass().getResource("dailyweather.csv");
			File getfile = new File(url.getPath());
			buffer_read = new BufferedReader(new FileReader(getfile));
			buffer_read.readLine();
			while ((strline = buffer_read.readLine()) != null) {
				String[] fieldobj = strline.split(",");
				ForecastObj fore_obj = new ForecastObj();
				fore_obj.setDATE(fieldobj[0]);
				fore_obj.setTMax(Double.parseDouble(fieldobj[1]));
				fore_obj.setTMin(Double.parseDouble(fieldobj[2]));
				listobj.add(fore_obj);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffer_read != null)
					buffer_read.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<ForecastObj> resultobj = new ArrayList<>();
		int firstdate = Integer.parseInt(date);
		int count = 0;
		for (ForecastObj obj1 : listobj) {
			if (!obj1.getDATE().equals(date)) {
				count++;
			} else
				break;
		}
		for (int i = count; i < count + 7; i++) {
			if (i >= listobj.size()) {
				ForecastObj fore_obj2 = new ForecastObj();
				fore_obj2.setDATE((firstdate + i) + "");
				fore_obj2.setTMax(7 + i);
				fore_obj2.setTMin(i);
				resultobj.add(fore_obj2);
			} else {
				resultobj.add(listobj.get(i));
			}
		}
		return resultobj;
	}	
/*********************************************************************************/
	public void deleteWeatherRecord(String date) {
		URL url = getClass().getResource("dailyweather.csv");
		File filepath = new File(url.getPath());
	    try {
	    	List<String> output = Files.lines(filepath.toPath())
	    			.filter(line -> !line.split(",")[0].equals(date))
	    			.collect(Collectors.toList());
			Files.write(filepath.toPath(), output, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
