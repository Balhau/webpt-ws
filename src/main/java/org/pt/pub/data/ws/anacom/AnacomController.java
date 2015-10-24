package org.pt.pub.data.ws.anacom;


import java.util.List;

import org.pt.pub.data.sources.anacom.Anacom;
import org.pt.pub.data.sources.anacom.domain.Tarifa;
import org.pt.pub.data.ws.domain.WebResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/anacom")
public class AnacomController {
    private Anacom anacom;

    public AnacomController(){
        this.anacom=new Anacom();
    }

    @RequestMapping("/tarifarios")
    public WebResult<List<Tarifa>> getTarifarios(){
        return WebResult.<List<Tarifa>>wrap(
                ()->anacom.getTarifarios()
        );
    }
}
