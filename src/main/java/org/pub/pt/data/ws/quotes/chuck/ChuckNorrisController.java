package org.pub.pt.data.ws.quotes.chuck;

import org.pub.pt.data.sources.domain.Message;
import org.pub.pt.data.sources.quotes.chucknorris.ChuckNorris;
import org.pub.pt.data.ws.domain.WebResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vitorfernandes on 10/17/15.
 */
@RestController
@RequestMapping("/ws/quotes/chucknorris")
public class ChuckNorrisController {
    private ChuckNorris chuckNorris;

    public ChuckNorrisController(){
        this.chuckNorris=new ChuckNorris();
    }

    @RequestMapping("/fact/{page}")
    @Cacheable(value = "cache",keyGenerator = "keyGenerator")
    public WebResult<List<Message>> getChuckFacts(@PathVariable("page") Integer page){
        return WebResult.wrap(
                ()-> chuckNorris.getFacts(page)
        );
    }

    @RequestMapping("/fact/search/{keyword}/{page}")
    @Cacheable(value = "cache",keyGenerator = "keyGenerator")
    public WebResult<List<Message>> getChuckFactsByKeyword(
            @PathVariable("page") Integer page,
            @PathVariable("keyword") String patternSearch){
        return WebResult.wrap(
                ()-> chuckNorris.getFacts(patternSearch,page)
        );
    }
}
