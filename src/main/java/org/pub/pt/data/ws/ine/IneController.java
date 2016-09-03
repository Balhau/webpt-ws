package org.pub.pt.data.ws.ine;

import java.util.Base64;

import org.pub.pt.data.sources.ine.Ine;
import org.pub.pt.data.sources.ine.domain.INEResultData;
import org.pub.pt.data.sources.ine.domain.INEServices;
import org.pub.pt.data.ws.domain.WebResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the public data of INE (Instituto Nacional de Estatística)
 * @author balhau
 *
 */
@RestController
@RequestMapping("/ws/ine")
public class IneController {

	private Ine ine;
	
	public IneController(){
		this.ine=new Ine();
	}
	
	@RequestMapping("/services/{pagenumber}/{numperpage}")
	public WebResult<INEServices> getAvailableServices(
			@PathVariable("pagenumber") int pageNumber,
			@PathVariable("numperpage") int numperpage){
		return WebResult.wrap(()-> ine.getAvailableServices(pageNumber, numperpage));
	}
	
	@RequestMapping("/service/{b64url}")
	public WebResult<INEResultData> getServiceData(@PathVariable("b64url") String b64Url){
		return WebResult.<INEResultData>wrap(() -> {
			String urlData=new String(Base64.getDecoder().decode(b64Url.getBytes()));
			return ine.getDataFromService(urlData);
		});
	}
}
