package org.pt.pub.data.ws.anacom;


import java.util.List;

import org.pt.pub.data.sources.anacom.Anacom;
import org.pt.pub.data.sources.anacom.domain.TarifaInternet;
import org.pt.pub.data.sources.anacom.domain.TarifaTelevisao;
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

    @RequestMapping("/tarifarios/internet")
    public WebResult<List<TarifaInternet>> getInternetTarifarios(){
        return WebResult.<List<TarifaInternet>>wrap(
                ()->anacom.getTarifariosInternet()
        );
    }

    @RequestMapping("/tarifarios/televisao")
    public WebResult<List<TarifaTelevisao>> getTelevisaoTarifarios(){
        return WebResult.<List<TarifaTelevisao>>wrap(
                () -> anacom.getTarifariosTelevisao()
        );
    }
}
