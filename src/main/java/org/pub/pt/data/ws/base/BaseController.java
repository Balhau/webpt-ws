package org.pub.pt.data.ws.base;

import java.util.List;

import org.pub.pt.data.sources.base.Base;
import org.pub.pt.data.sources.base.domain.BaseQueryResponse;
import org.pub.pt.data.ws.domain.WebResult;
import org.pub.global.domain.TableData;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ws/base")
public class BaseController {
	private Base base;
	
	public BaseController() {
		base=new Base();
	}
	
	@RequestMapping("/contracts/{start}/{end}")
	@Cacheable(value = "cache",keyGenerator = "keyGenerator")
	public WebResult<BaseQueryResponse> getContracts(
			@PathVariable("start") int startOffset,
			@PathVariable("end") int endOffset
			){
		return WebResult.wrap(()->base.getAllResults(startOffset, endOffset));
	}
	
	@RequestMapping("/contract/{idContract}")
	@Cacheable(value = "cache",keyGenerator = "keyGenerator")
	public WebResult<List<TableData>> getIndicatorData(
			@PathVariable("idContract") int idContract){
		return WebResult.wrap(()-> base.getEntryInformationByContractoId(idContract));
	}
	
	@RequestMapping("/contract/adjudicante/{start}/{end}/{adjudicante}")
	@Cacheable(value = "cache",keyGenerator = "keyGenerator")
	public WebResult<BaseQueryResponse> getByAdjudicante(
			@PathVariable("start") int start,
			@PathVariable("end") int end,
			@PathVariable("adjudicante") String adjudicante){
			return WebResult.wrap(()-> base.getByAdjudicante(start, end, adjudicante));
	}
	
	@RequestMapping("/contract/adjudicatario/{start}/{end}/{adjudicatario}")
	@Cacheable(value = "cache",keyGenerator = "keyGenerator")
	public WebResult<BaseQueryResponse> getByAdjudicatario(
			@PathVariable("start") int start,
			@PathVariable("end") int end,
			@PathVariable("adjudicatario") String adjudicatario
			){
		
		return WebResult.wrap(()-> base.getByAjudicatario(start, end, adjudicatario));
	}
}
