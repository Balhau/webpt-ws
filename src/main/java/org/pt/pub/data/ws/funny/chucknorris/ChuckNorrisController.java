package org.pt.pub.data.ws.funny.chucknorris;

import org.pt.pub.data.sources.accuweather.AccuWeather;
import org.pt.pub.data.sources.accuweather.domain.Weather;
import org.pt.pub.data.sources.accuweather.domain.WeatherLocationList;
import org.pt.pub.data.sources.amusing.chucknorris.ChuckNorris;
import org.pt.pub.data.ws.domain.WebResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;

/**
 * Created by vitorfernandes on 10/17/15.
 */
@RestController
@RequestMapping("/ws/funny/chucknorris")
public class ChuckNorrisController {
    private ChuckNorris chuckNorris;

    public ChuckNorrisController(){
        this.chuckNorris=new ChuckNorris();
    }

    @RequestMapping("/fact/{page}")
    public WebResult<List<String>> getChuckFacts(@PathVariable("page") Integer page){
        return WebResult.<List<String>>wrap(
                ()-> chuckNorris.getFacts(page)
        );
    }

    @RequestMapping("/fact/search/{keyword}/{page}")
    public WebResult<List<String>> getChuckFactsByKeyword(
            @PathVariable("page") Integer page,
            @PathVariable("keyword") String patternSearch){
        return WebResult.<List<String>>wrap(
                ()-> chuckNorris.getFacts(patternSearch,page)
        );
    }
}
