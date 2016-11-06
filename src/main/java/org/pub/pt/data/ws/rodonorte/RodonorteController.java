package org.pub.pt.data.ws.rodonorte;

import org.pub.pt.data.sources.rodonorte.Rodonorte;
import org.pub.pt.data.sources.rodonorte.domain.Destination;
import org.pub.pt.data.sources.rodonorte.domain.Ride;
import org.pub.pt.data.ws.domain.WebResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vitorfernandes on 11/6/16.
 */
@RestController
@RequestMapping("/ws/rodonorte")
public class RodonorteController {
    private Rodonorte rodonorte;

    public RodonorteController(){
        this.rodonorte=new Rodonorte();
    }

    @RequestMapping(value = "/origins", method = RequestMethod.GET)
    @Cacheable(value = "cache", keyGenerator = "keyGenerator")
    public WebResult<List<String>> getOrigins(){
        return WebResult.wrap(
                () -> rodonorte.getOriginList()
        );
    }

    @RequestMapping(value = "/destiny/{origin}", method = RequestMethod.GET)
    @Cacheable(value = "cache", keyGenerator = "keyGenerator")
    public WebResult<List<Destination>> getDestiny(@PathVariable String origin){
        return WebResult.wrap(
                () -> rodonorte.getDestinations(origin)
        );
    }

    @RequestMapping(value = "/ride/{origin}/{destiny}", method = RequestMethod.GET)
    @Cacheable(value = "cache", keyGenerator = "keyGenerator")
    public WebResult<List<Ride>> getRides(@PathVariable String origin, @PathVariable String destiny){
        return WebResult.wrap(
                () -> rodonorte.getRides(origin,new Destination(0,destiny))
        );
    }
}
