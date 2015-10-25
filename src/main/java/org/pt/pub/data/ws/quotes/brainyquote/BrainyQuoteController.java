package org.pt.pub.data.ws.quotes.brainyquote;

import com.sun.org.apache.xpath.internal.operations.Quo;
import org.pt.pub.data.sources.quotes.brainyquote.BrainyQuote;
import org.pt.pub.data.sources.quotes.brainyquote.domain.Quote;
import org.pt.pub.data.sources.quotes.brainyquote.domain.Topic;
import org.pt.pub.data.ws.domain.WebResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by vitorfernandes on 10/24/15.
 */
@RestController
@RequestMapping("/ws/quotes/brainyquote")
public class BrainyQuoteController {
    private BrainyQuote brainyQuote;

    public BrainyQuoteController(){
        this.brainyQuote=new BrainyQuote();
    }

    @RequestMapping("/topics")
    @Cacheable("cache")
    public WebResult<List<Topic>> getTopics(){
        return WebResult.wrap(
                () -> brainyQuote.getTopics()
        );
    }

    @RequestMapping("/topics/{topic}/{page}")
    @Cacheable("cache")
    public WebResult<List<Quote>> getQuotes(
            @PathVariable("topic") String topic,
            @PathVariable("page") int page
    ){
        return WebResult.wrap(
                () -> brainyQuote.getQuotes(topic,page)
        );
    }
}
