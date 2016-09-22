package org.pub.pt.data.ws.resultdb;

import org.pub.data.sources.sports.football.resultdb.ResultDb;
import org.pub.data.sources.sports.football.resultdb.domain.League;
import org.pub.data.sources.sports.football.resultdb.domain.LeagueInfo;
import org.pub.pt.data.ws.domain.WebResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vitorfernandes on 9/22/16.
 */
@RestController
@RequestMapping("/ws/resultdb")
public class ResultDbController {

    private ResultDb resultDb;

    public ResultDbController(){
        resultDb = new ResultDb();
    }

    @RequestMapping(value = "/leagues", method = RequestMethod.GET)
    @Cacheable(value = "cache", keyGenerator = "keyGenerator")
    public WebResult<List<League>> getAllLeagues(){
        return WebResult.wrap(()-> resultDb.getAllLeagues());
    }

    @RequestMapping(value = "/leaguesInfo", method = RequestMethod.GET)
    @Cacheable(value = "cache", keyGenerator = "keyGenerator")
    public WebResult<List<LeagueInfo>> getAllLeaguesInfo(){
        return WebResult.wrap(()-> resultDb.getAllLeaguesInfo());
    }

    @RequestMapping(value="/league",method = RequestMethod.POST, consumes = "application/json")
    @Cacheable(value = "cache", keyGenerator = "keyGenerator")
    public WebResult<League> getLeagueFromUrl(@RequestBody LeagueInfo leagueInfo){
        return WebResult.wrap(() -> resultDb.getLeagueFromLeagueInfo(leagueInfo));
    }


}
