package org.pt.pub.data.ws.base;

import java.util.List;

import org.pt.pub.data.sources.base.Base;
import org.pt.pub.data.sources.base.domain.BaseQueryResponse;
import org.pt.pub.data.ws.domain.WebResult;
import org.pt.pub.global.domain.TableData;
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
	public WebResult<BaseQueryResponse> getContracts(
			@PathVariable("start") int startOffset,
			@PathVariable("end") int endOffset
			){
		return WebResult.<BaseQueryResponse>wrap(()->{
			return base.getAllResults(startOffset, endOffset);
		});
	}
	
	@RequestMapping("/contract/{idContract}")
	public WebResult<List<TableData>> getIndicatorData(
			@PathVariable("idContract") int idContract){
		return WebResult.<List<TableData>>wrap(()->{
			return base.getEntryInformationByContractoId(idContract);
		});
	}
	
	@RequestMapping("/contract/adjudicante/{start}/{end}/{adjudicante}")
	public WebResult<BaseQueryResponse> getByAdjudicante(
			@PathVariable("start") int start,
			@PathVariable("end") int end,
			@PathVariable("adjudicante") String adjudicante){
			return WebResult.<BaseQueryResponse>wrap(()->{
				return base.getByAdjudicante(start, end, adjudicante);
			});
	}
	
	@RequestMapping("/contract/adjudicatario/{start}/{end}/{adjudicatario}")
	public WebResult<BaseQueryResponse> getByAdjudicatario(
			@PathVariable("start") int start,
			@PathVariable("end") int end,
			@PathVariable("adjudicatario") String adjudicatario
			){
		
		return WebResult.<BaseQueryResponse>wrap(()->{
			return base.getByAjudicatario(start, end, adjudicatario);
		});
	}
}
