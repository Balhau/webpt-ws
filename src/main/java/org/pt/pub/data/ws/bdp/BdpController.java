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
		TableData tbd;
		try{
			tbd=bdp.getCategories();
		} catch(Exception ex){
			return WebResult.<TableData>fail(ex.getMessage());
		}
		return WebResult.<TableData>ok(tbd);
	}
	
	@RequestMapping("/category/{categorie}")
	public WebResult<TableData> getBdpSeries(@PathVariable("categorie") String categorie){
		TableData tbd;
		try{
			return WebResult.<TableData>ok(bdp.getSeriesForCategorie(categorie));
		}catch(Exception ex){
			return WebResult.<TableData>fail(ex.getMessage());
		}
	}
	
	@RequestMapping("/category/serie/{id}")
	public WebResult<List<TableData>> getBdpSerieData(@PathVariable("id") String id){
		TableData tbd;
		try{
			return WebResult.<List<TableData>>ok(bdp.getDataForSeries(StringUtils.asList(id), new Date()));
		}catch (Exception ex){
		  return WebResult.<List<TableData>>fail("MESSAGE: "+id);
		}
	}
}
