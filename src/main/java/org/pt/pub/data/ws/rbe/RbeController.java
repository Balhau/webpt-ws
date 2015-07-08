package org.pt.pub.data.ws.rbe;

import java.util.List;

import org.pt.pub.data.sources.rbe.Rbe;
import org.pt.pub.data.sources.rbe.domain.RbeIndicator;
import org.pt.pub.data.sources.rbe.domain.RbeIndicatorData;
import org.pt.pub.data.ws.domain.WebResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ws/rbe")
public class RbeController {
	private Rbe rbe;
	
	public RbeController() {
		rbe=new Rbe();
	}
	
	@RequestMapping("/indicators")
	public WebResult<List<RbeIndicator>> getIndicators(){
		return WebResult.<List<RbeIndicator>>wrap(()->{
			return rbe.getIndicators();
		});
	}
	
	@RequestMapping("/indicator/{cat}/{serie}")
	public WebResult<RbeIndicatorData> getIndicatorData(
			@PathVariable("cat") int cat,
			@PathVariable("serie") int serie){
		return WebResult.<RbeIndicatorData>wrap(()->{
			return rbe.getIndicator(cat, serie);
		});
	}
	
}
