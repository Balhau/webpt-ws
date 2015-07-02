package org.pt.pub.data.ws.ipma;

import java.util.List;

import org.pt.pub.data.sources.ipma.Ipma;
import org.pt.pub.data.sources.ipma.domain.GeoWeather;
import org.pt.pub.data.ws.domain.WebResult;
import org.pt.pub.global.domain.TableData;
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
	
	@RequestMapping("/forecast/zeroday")
	public WebResult<List<GeoWeather<?>>> forecastZeroDay(){
		return WebResult.<List<GeoWeather<?>>>wrap(()->{
			return ipma.getForecastDayZero();
		});
	}
}
