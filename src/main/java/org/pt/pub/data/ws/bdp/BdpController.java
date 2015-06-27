package org.pt.pub.data.ws.bdp;

import java.util.Date;
import java.util.List;

import org.pt.pub.data.sources.bdp.BancoPortugal;
import org.pt.pub.data.sources.bdp.domain.TableData;
import org.pt.pub.data.ws.domain.WebResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balhau.utils.StringUtils;

@RestController
@RequestMapping("/ws/bdp")
public class BdpController {
	
	private BancoPortugal bdp;
	
	public BdpController(){
		bdp=new BancoPortugal();
	}
	
	@RequestMapping("/categories")
	public WebResult<TableData> getBdpCategories(){
		return WebResult.<TableData>wrap(
				()-> bdp.getCategories()
		);
	}
	
	@RequestMapping("/category/{categorie}")
	public WebResult<TableData> getBdpSeries(@PathVariable("categorie") String categorie){
		return WebResult.<TableData>wrap(
				()-> bdp.getSeriesForCategorie(categorie)
		);
	}
	
	@RequestMapping("/category/serie/{id}")
	public WebResult<List<TableData>> getBdpSerieData(@PathVariable("id") String id){
		return WebResult.<List<TableData>>wrap(
				()->bdp.getDataForSeries(StringUtils.asList(id), new Date())
		);
	}
}
