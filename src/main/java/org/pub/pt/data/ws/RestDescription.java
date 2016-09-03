package org.pub.pt.data.ws;

import java.util.List;

import org.pub.pt.data.ws.domain.WebResult;
import org.pub.pt.data.ws.service.ServiceDescription;
import org.pub.pt.data.ws.service.ServiceDescriptionDomain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestDescription {
	private ServiceDescription sd;
	public RestDescription(){
		sd=new ServiceDescription();
	}
	
	@RequestMapping("/")
	public WebResult<List<ServiceDescriptionDomain>> describeServices(){
		return WebResult.<List<ServiceDescriptionDomain>>wrap(
				()->{return sd.getControllers();}
		);
	}
	
	@RequestMapping("/info")
	public WebResult<ServiceInfo> getService(){
		return WebResult.<ServiceInfo>wrap(()->{
			return new ServiceInfo(
					"1.0 SNAPSHOT", 
					"Balhau", 
					"No fucking rights reserved", 
					"This is a webservice that abstracts a bunch of scrappers...");
		});
	}
}
