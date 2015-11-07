package org.pt.pub.data.ws.tasks;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pt.pub.data.sources.domain.Message;
import org.pt.pub.data.sources.domain.MessageService;
import org.pt.pub.data.sources.quotes.brainyquote.BrainyMessage;
import org.pt.pub.data.sources.quotes.chucknorris.ChuckNorris;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * Created by vitorfernandes on 10/17/15.
 */
@Component
public class PosterService {

    private static String URL_TOKEN_UPDATER="https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&grant_type=fb_exchange_token&fb_exchange_token=%s";

    //Facebook auth
    @Value("${facebook.accessToken}")
    private String facebookAccessToken;
    @Value("${facebook.applicationId}")
    private String facebookApplicationId;
    @Value("${facebook.applicationSecret}")
    private String applicationSecret;
    @Value("${facebook.applicationPermissions}")
    private String applicationPermissions;
    private Facebook facebook;

    //Twitter auth
    @Value("${twitter.consumerKey}")
    private String consumerKey;
    @Value("${twitter.consumerSecret}")
    private String consumerSecret;
    @Value("${twitter.accessToken}")
    private String accessToken;
    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;

    //Twitter auth

    private static MessageService[] services=new MessageService[]{
            new ChuckNorris(),new BrainyMessage()
    };

    @Scheduled(fixedRate = 3600000)
    private void postFacebookChuckFact() throws Exception{
        Facebook facebook=buildFacebook();
        Twitter twitter=buildTwitter();

        MessageService service = Math.random() > 0.5 ? new ChuckNorris() : new BrainyMessage();
        Message message =service.getMessage();
        System.out.println(message);

        String update= message.getMessage()+"\n\n"+ message.getSource();

        facebook.postStatusMessage(update);
        twitter.updateStatus(update);

        refreshFacebookTheAuthToken();
    }

    private Twitter buildTwitter(){
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(consumerKey,consumerSecret);
        twitter.setOAuthAccessToken(new twitter4j.auth.AccessToken(accessToken,accessTokenSecret));
        return twitter;
    }

    private Facebook buildFacebook(){
        Facebook facebook=new FacebookFactory().getInstance();

        facebook.setOAuthAppId(facebookApplicationId,applicationSecret);
        facebook.setOAuthPermissions(applicationPermissions);
        facebook.setOAuthAccessToken(new AccessToken(facebookAccessToken,null));
        return facebook;
    }

    private void refreshFacebookTheAuthToken() throws Exception{
        Connection connection= Jsoup.connect(String.format(URL_TOKEN_UPDATER,
                facebookApplicationId,applicationSecret, facebookAccessToken
                ));
        Document doc=connection.get();
        String out=doc.text();
        facebookAccessToken =out.split("=")[1].split("&")[0];
    }
}
