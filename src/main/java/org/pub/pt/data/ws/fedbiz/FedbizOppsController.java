package org.pub.pt.data.ws.fedbiz;

import org.pub.data.sources.fedbizopps.FedbizOpps;
import org.pub.data.sources.fedbizopps.domain.Opportunity;
import org.pub.global.base.ScraperPool;
import org.pub.pt.data.ws.domain.WebResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/ws/fedbiz")
public class FedbizOppsController {
    private FedbizOpps fedbizOpps;

    public FedbizOppsController() {
        fedbizOpps = new FedbizOpps(ScraperPool.getPool());
    }

    @RequestMapping(value = "/opps/{page}", method = RequestMethod.GET)
    @Cacheable(value = "cache", keyGenerator = "keyGenerator")
    public WebResult<List<Opportunity>> getOpportunitiesPage(@PathVariable Integer page) {
        return WebResult.wrap(() -> {
            return fedbizOpps.getOpportunitiesPage(page);
        });
    }

}
