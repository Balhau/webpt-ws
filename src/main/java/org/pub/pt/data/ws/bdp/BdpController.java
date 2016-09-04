package org.pub.pt.data.ws.bdp;

import java.util.Date;
import java.util.List;

import org.pub.pt.data.sources.bdp.BancoPortugal;
import org.pub.pt.data.ws.domain.WebResult;
import org.pub.global.domain.TableData;
import org.springframework.cache.annotation.Cacheable;
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
	@Cacheable
	public WebResult<TableData> getBdpCategories(){
		return WebResult.wrap(()-> bdp.getCategories());
	}
	
	@RequestMapping("/category/{categorie}")
	@Cacheable
	public WebResult<TableData> getBdpSeries(@PathVariable("categorie") String categorie){
		return WebResult.wrap(()-> bdp.getSeriesForCategorie(categorie));
	}
	
	@RequestMapping("/category/serie/{id}")
	@Cacheable
	public WebResult<List<TableData>> getBdpSerieData(@PathVariable("id") String id){
		return WebResult.wrap(()->bdp.getDataForSeries(StringUtils.asList(id), new Date()));
	}
}
