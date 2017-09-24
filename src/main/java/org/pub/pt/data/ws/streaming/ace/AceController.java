package org.pub.pt.data.ws.streaming.ace;

import org.pub.data.sources.streaming.ace.AceStream;
import org.pub.data.sources.streaming.ace.domain.AceFeed;
import org.pub.data.sources.streaming.ace.domain.AceSportsFeed;
import org.pub.pt.data.ws.domain.WebResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This endpoint will expose the acestream feed service api
 */
@RestController
@RequestMapping("/ws/stream/ace")
public class AceController {
    private AceStream aceStream;

    public AceController(){
        this.aceStream=new AceStream();
    }

    @RequestMapping(value="/acestop",method = RequestMethod.GET)
    @Cacheable(value = "cache", keyGenerator = "keyGenerator")
    public WebResult<List<AceFeed>> getAceStopFeeds(){
        return WebResult.wrap(() -> aceStream.getAceStopFeeds());
    }

    @RequestMapping(value="/acesports",method = RequestMethod.GET)
    @Cacheable(value = "cache", keyGenerator = "keyGenerator")
    public WebResult<List<AceSportsFeed>> getAceSportsFeeds(){
        return WebResult.wrap(() -> aceStream.getAceSportsFeeds());
    }
}
