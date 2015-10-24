package org.pt.pub.data.ws.tasks;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.Like;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pt.pub.data.sources.amusing.chucknorris.ChuckNorris;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vitorfernandes on 10/17/15.
 */
@Component
public class FacebookChuckPoster {

    private static String URL_TOKEN_UPDATER="https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&grant_type=fb_exchange_token&fb_exchange_token=%s";

    @Value("${accessToken}")
    private String accessToken;
    @Value("${applicationId}")
    private String applicationId;
    @Value("${applicationSecret}")
    private String applicationSecret;
    @Value("${applicationPermissions}")
    private String applicationPermissions;
    private Facebook facebook;

    //@Scheduled(fixedRate = 3600000)
    private void postFacebookChuckFact() throws Exception{
        Facebook facebook= new FacebookFactory().getInstance();

        facebook.setOAuthAppId(applicationId,applicationSecret);
        facebook.setOAuthPermissions(applicationPermissions);
        facebook.setOAuthAccessToken(new AccessToken(accessToken,null));
        ChuckNorris chuckNorris = new ChuckNorris();

        List<String> pageFacts=chuckNorris.getFacts((int)Math.floor(Math.random()*20));
        String chuckFact=pageFacts.get((int)Math.floor(Math.random()*pageFacts.size()));

        System.out.println(chuckFact);

        facebook.postStatusMessage(chuckFact);
        refreshTheAuthToken();
    }

    private void refreshTheAuthToken() throws Exception{
        Connection connection= Jsoup.connect(String.format(URL_TOKEN_UPDATER,
                applicationId,applicationSecret,accessToken
                ));
        Document doc=connection.get();
        String out=doc.text();
        accessToken=out.split("=")[1].split("&")[0];
    }
}
