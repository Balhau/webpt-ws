package org.pub.pt.data.ws.rodonorte;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.pub.pt.data.sources.rodonorte.Rodonorte;
import org.pub.pt.data.sources.rodonorte.domain.Destination;
import org.pub.pt.data.sources.rodonorte.domain.Ride;
import org.pub.pt.data.ws.domain.WebResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vitorfernandes on 11/6/16.
 */
@RestController
@RequestMapping("/ws/rodonorte")
@Api(value = "Rodonorte bus system")
public class RodonorteController {
    private Rodonorte rodonorte;

    public RodonorteController(){
        this.rodonorte=new Rodonorte();
    }

    @RequestMapping(value = "/origins", method = RequestMethod.GET)
    @Cacheable(value = "cache", keyGenerator = "keyGenerator")
    @ApiOperation(
            value = "Get an origin",
            notes = "Get a list of string with possible origins",
            response = List.class
    )
    public WebResult<List<String>> getOrigins(){
        return WebResult.wrap(
                () -> rodonorte.getOriginList()
        );
    }

    @RequestMapping(value = "/destiny/{origin}", method = RequestMethod.GET)
    @Cacheable(value = "cache", keyGenerator = "keyGenerator")
    @ApiOperation(
            value="Get destiny",
            notes= "Get a list of possible destiny entries for a given origin",
            response = List.class
    )
    public WebResult<List<Destination>> getDestiny(@PathVariable String origin){
        return WebResult.wrap(
                () -> rodonorte.getDestinations(origin)
        );
    }

    @RequestMapping(value = "/ride/{origin}/{destiny}", method = RequestMethod.GET)
    @Cacheable(value = "cache", keyGenerator = "keyGenerator")
    @ApiOperation(
            value="Get rides",
            notes="Get a list of possible trips between a given origin and destiny",
            response = List.class
    )
    public WebResult<List<Ride>> getRides(@PathVariable String origin, @PathVariable String destiny){
        return WebResult.wrap(
                () -> rodonorte.getRides(origin,new Destination(0,destiny))
        );
    }
}
