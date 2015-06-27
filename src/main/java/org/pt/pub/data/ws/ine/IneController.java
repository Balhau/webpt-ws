package org.pt.pub.data.ws.ine;

import java.util.Base64;

import org.pt.pub.data.sources.ine.INEDataSource;
import org.pt.pub.data.sources.ine.datatypes.INEResultData;
import org.pt.pub.data.sources.ine.datatypes.INEServices;
import org.pt.pub.data.ws.domain.WebResult;
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

	private INEDataSource ine;
	
	public IneController(){
		this.ine=new INEDataSource();
	}
	
	@RequestMapping("/services/{pagenumber}/{numperpage}")
	public WebResult<INEServices> getAvailableServices(
			@PathVariable("pagenumber") int pageNumber,
			@PathVariable("numperpage") int numperpage){
		return WebResult.<INEServices>wrap(()-> ine.getAvailableServices(pageNumber, numperpage));
	}
	
	@RequestMapping("/service/{b64url}")
	public WebResult<INEResultData> getServiceData(@PathVariable("b64url") String b64Url){
		return WebResult.<INEResultData>wrap(() -> {
			String urlData=new String(Base64.getDecoder().decode(b64Url.getBytes()));
			return ine.getDataFromService(urlData);
		});
	}
}
