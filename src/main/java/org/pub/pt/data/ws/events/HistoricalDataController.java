package org.pub.pt.data.ws.events;

import org.pub.pt.data.sources.events.HistoricalEvents;
import org.pub.pt.data.sources.events.domain.HistoricalEvent;
import org.pub.pt.data.ws.domain.WebResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
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
        return WebResult.wrap(() -> {
            return historicalEvents.getTodayEvents();
        });
    }

    @RequestMapping("/event/{month}/{day}")
    @Cacheable(value = "cache",keyGenerator = "keyGenerator")
    public WebResult<List<HistoricalEvent>> getEventsByMonthDay(
        @PathVariable("month") int month,
        @PathVariable("day") int day){

        return WebResult.wrap(() -> {
            return historicalEvents.getEventsByDay(month,day);
        });
    }

    @RequestMapping("/births/{month}/{day}")
    @Cacheable(value = "cache",keyGenerator = "keyGenerator")
    public WebResult<List<HistoricalEvent>> getBirthsByMonthDay(
            @PathVariable("month") int month,
            @PathVariable("day") int day){

        return WebResult.wrap(() -> {
            return historicalEvents.getBirthdaysByDay(month,day);
        });
    }

    @RequestMapping("/deaths/{month}/{day}")
    @Cacheable(value = "cache",keyGenerator = "keyGenerator")
    public WebResult<List<HistoricalEvent>> getDeathsByMonthDay(
            @PathVariable("month") int month,
            @PathVariable("day") int day){

        return WebResult.wrap(() -> {
            return historicalEvents.getDeathsByDay(month,day);
        });
    }

}
