package org.pub.pt.data.ws.yts;

import org.pub.data.sources.piratebay.domain.TorrentInfo;
import org.pub.data.sources.yts.YifyTorrents;
import org.pub.data.sources.yts.domain.TorrentLink;
import org.pub.data.sources.yts.domain.YifyTorrent;
import org.pub.pt.data.ws.domain.WebResult;
import org.pub.pt.data.ws.piratebay.domain.PirateBayRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Yify torrents controller service
 * Created by vitorfernandes on 9/4/16.
 */

@RestController
@RequestMapping("/ws/yts")
public class YifyTorrentsController {
    private YifyTorrents yts;

    public YifyTorrentsController(){
        this.yts=new YifyTorrents();
    }

    @RequestMapping(value = "/torrents/{page}", method = RequestMethod.GET)
    @Cacheable(value = "cache",keyGenerator = "keyGenerator")
    public WebResult<List<YifyTorrent>> getTorrentsPage(@PathVariable int page){
        return WebResult.wrap(
                () -> yts.getTorrentsFromPage(page)
        );
    }

    @RequestMapping(value = "/links/{page}",method = RequestMethod.GET)
    @Cacheable(value = "cache",keyGenerator = "keyGenerator")
    public WebResult<List<TorrentLink>> getLinksPage(@PathVariable int page){
        return WebResult.wrap(() -> yts.getTorrentURLs(page));
    }
}
