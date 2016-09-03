package org.pub.pt.data.ws.quotes.brainyquote;

import org.pub.pt.data.sources.domain.Message;
import org.pub.pt.data.sources.quotes.brainyquote.BrainyMessage;
import org.pub.pt.data.sources.quotes.brainyquote.domain.Topic;
import org.pub.pt.data.ws.domain.WebResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vitorfernandes on 10/24/15.
 */
@RestController
@RequestMapping("/ws/quotes/brainyquote")
public class BrainyQuoteController {
    private BrainyMessage brainyQuote;

    public BrainyQuoteController(){
        this.brainyQuote=new BrainyMessage();
    }

    @RequestMapping("/topics")
    @Cacheable("cache")
    public WebResult<List<Topic>> getTopics(){
        return WebResult.wrap(
                () -> brainyQuote.getTopics()
        );
    }

    @RequestMapping("/quote/{topic}/{page}")
    @Cacheable("cache")
    public WebResult<List<Message>> getQuotes(
            @PathVariable("topic") String topic,
            @PathVariable("page") int page
    ){
        return WebResult.wrap(
                () -> brainyQuote.getQuotes(topic,page)
        );
    }
}
