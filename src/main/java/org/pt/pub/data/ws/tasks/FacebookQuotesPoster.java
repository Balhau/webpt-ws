package org.pt.pub.data.ws.tasks;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pt.pub.data.sources.domain.Quote;
import org.pt.pub.data.sources.domain.QuoteService;
import org.pt.pub.data.sources.quotes.brainyquote.BrainyQuote;
import org.pt.pub.data.sources.quotes.chucknorris.ChuckNorris;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vitorfernandes on 10/17/15.
 */
@Component
public class FacebookQuotesPoster {

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
    private static QuoteService[] services=new QuoteService[]{
            new ChuckNorris(),new BrainyQuote()
    };

    @Scheduled(fixedRate = 3600000)
    private void postFacebookChuckFact() throws Exception{
        Facebook facebook= new FacebookFactory().getInstance();

        facebook.setOAuthAppId(applicationId,applicationSecret);
        facebook.setOAuthPermissions(applicationPermissions);
        facebook.setOAuthAccessToken(new AccessToken(accessToken,null));

        QuoteService service = Math.random() > 0.5 ? new ChuckNorris() : new BrainyQuote();
        Quote quote=service.getQuote();
        System.out.println(quote);

        facebook.postStatusMessage(quote.getQuote()+"\n\n"+quote.getAuthor());
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
