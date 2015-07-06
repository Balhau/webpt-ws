package org.pt.pub.data.ws;

import java.util.List;
import java.util.Vector;

import org.pt.pub.data.ws.domain.WebResult;
import org.pt.pub.data.ws.service.ServiceDescription;
import org.pt.pub.data.ws.service.ServiceDescriptionDomain;
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
}
