package org.pt.pub.data.ws.events;

import org.pt.pub.data.sources.events.HistoricalEvents;
import org.pt.pub.data.sources.events.domain.HistoricalEvent;
import org.pt.pub.data.ws.domain.WebResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vitorfernandes on 11/7/15.
 */
@RestController
@RequestMapping("/ws/historical")
public class HistoricalDataController {
    private HistoricalEvents historicalEvents;

    public HistoricalDataController(){
        historicalEvents=new HistoricalEvents();
    }

    @RequestMapping("/today")
    public WebResult<List<HistoricalEvent>> getTodayEvents(){
        return WebResult.wrap(() -> { return historicalEvents.getTodayEvents();});
    }

}
