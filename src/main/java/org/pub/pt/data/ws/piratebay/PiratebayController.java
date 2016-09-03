package org.pub.pt.data.ws.piratebay;

import org.pub.pt.data.ws.domain.WebResult;
import org.pub.pt.data.ws.piratebay.domain.PirateBayRequest;
import org.pub.data.sources.piratebay.PirateBay;
import org.pub.data.sources.piratebay.domain.TorrentInfo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vitorfernandes on 9/3/16.
 */
@RestController
@RequestMapping("/ws/piratebay")
public class PiratebayController {

    private PirateBay pirateBay;

    public PiratebayController(){
        this.pirateBay=new PirateBay();
    }

    @RequestMapping(value = "/torrents", method = RequestMethod.PUT, consumes = "application/json")
    @Cacheable("cache")
    public WebResult<List<TorrentInfo>> searchTorrents(@RequestBody PirateBayRequest query){
        return WebResult.wrap(
                () -> pirateBay.searchTorrents(query.getQuery(),query.getPage(),query.getOrder())
        );
    }
}
