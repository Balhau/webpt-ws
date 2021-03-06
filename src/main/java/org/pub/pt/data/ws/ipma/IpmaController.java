package org.pub.pt.data.ws.ipma;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.pub.pt.data.sources.ipma.Ipma;
import org.pub.pt.data.sources.ipma.domain.BeachEntry;
import org.pub.pt.data.sources.ipma.domain.GeoWeather;
import org.pub.pt.data.ws.domain.WebResult;
import org.pub.global.domain.TableData;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the IPMA data source
 * @author balhau
 *
 */
@RestController
@RequestMapping("/ws/ipma")
public class IpmaController {
	private Ipma ipma;
	
	public IpmaController(){
		this.ipma=new Ipma();
	}
	
	@RequestMapping("/forecast/{day}")
	public WebResult<List<GeoWeather<?>>> forecastDay(@PathVariable("day") int day){
		return WebResult.wrapWithException(()->{return ipma.getForecastDay(day);},
				new Exception("Invalid request. Maybe because day request is not available. "
				+ "Offset day should be between [0,2]"));
	}
	
	@RequestMapping("/forecast/beachlist")
	public WebResult<List<BeachEntry>> getBeachList(){
		return WebResult.wrap(()->ipma.getBeachEntries());
	}
	
	@RequestMapping("/forecast/beachinfo/{idbeach}")
	public WebResult<List<TableData>> getBeachInfo(
			@PathVariable("idbeach") int idbeach){
		return WebResult.wrap(()->ipma.getBeachInfo(idbeach));
	}
	
	@RequestMapping("/forecast/seismic/{fromdate}")
	public WebResult<List<TableData>> getSeismicInfo(
			@PathVariable("fromdate")
			@DateTimeFormat(iso=ISO.DATE)
			Date fromDate
	){
		return WebResult.wrap(()->{
			Calendar c=Calendar.getInstance();
			c.setTime(fromDate);
			return ipma.getSeismicActivity(fromDate);
		});
	}
	
}
