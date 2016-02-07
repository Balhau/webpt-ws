package org.pt.pub.data.ws.vimeo;

import org.pt.pub.data.ws.domain.WebResult;
import org.pub.data.sources.vimeo.Vimeo;
import org.pub.data.sources.vimeo.domain.VimeoVideo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vitorfernandes on 2/7/16.
 */
@RestController
@RequestMapping("/ws/vimeo")
public class VimeoController{

    private Vimeo vs=new Vimeo();

    @RequestMapping("/getvideo/{idvideo}")
    public WebResult<VimeoVideo> getIndicatorData(@PathVariable("idvideo") int idvideo){
        return WebResult.wrap(() -> vs.getVideo(Vimeo.BASE_URL+idvideo));
    }
}
