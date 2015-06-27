package org.pt.pub.data.ws.bdp;

import org.pt.pub.data.sources.bdp.BancoPortugal;
import org.pt.pub.data.sources.bdp.domain.TableData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/bdp")
public class BdpController {
	
	@RequestMapping("/categories")
	public TableData getBdpCategories(){
		TableData tbd;
		try{
			BancoPortugal bdp=new BancoPortugal();
			tbd=bdp.getCategories();
		} catch(Exception ex){return new TableData();}
		return tbd;
	}
}
