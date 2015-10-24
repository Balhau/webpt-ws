package org.pt.pub.data.ws.anacom;


import java.util.List;

import org.pt.pub.data.sources.anacom.Anacom;
import org.pt.pub.data.sources.anacom.domain.TarifaFixo;
import org.pt.pub.data.sources.anacom.domain.TarifaInternet;
import org.pt.pub.data.sources.anacom.domain.TarifaMovel;
import org.pt.pub.data.sources.anacom.domain.TarifaTelevisao;
import org.pt.pub.data.ws.domain.WebResult;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable("cache")
    public WebResult<List<TarifaInternet>> getInternetTarifarios(){
        return WebResult.<List<TarifaInternet>>wrap(
                ()->anacom.getTarifariosInternet()
        );
    }

    @RequestMapping("/tarifarios/televisao")
    @Cacheable("cache")
    public WebResult<List<TarifaTelevisao>> getTelevisaoTarifarios(){
        return WebResult.<List<TarifaTelevisao>>wrap(
                () -> anacom.getTarifariosTelevisao()
        );
    }

    @RequestMapping("/tarifarios/movel")
    @Cacheable("cache")
    public WebResult<List<TarifaMovel>> getMovelTarifarios(){
        return WebResult.<List<TarifaMovel>>wrap(
                ()->anacom.getTarifariosMovel()
        );
    }

    @RequestMapping("/tarifarios/fixo")
    @Cacheable("cache")
    public WebResult<List<TarifaFixo>> getFixoTarifarios(){
        return WebResult.<List<TarifaFixo>>wrap(
                () -> anacom.getTarifariosFixo()
        );
    }
}
