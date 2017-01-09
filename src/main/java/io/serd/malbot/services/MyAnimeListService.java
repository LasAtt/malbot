package io.serd.malbot.services;

import io.serd.malbot.BuildVars;
import io.serd.malbot.domain.Anime;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 *
 * @author Atte Lassila
 */
public class MyAnimeListService {

    public MyAnimeListService() {
    }

    public static Anime queryAnime(String query) {
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(BuildVars.malUser, BuildVars.malPass);
        Client client = ClientBuilder.newClient();
        client.register(feature);
        WebTarget target = client.target("https://myanimelist.net/api/anime/search.xml?q=" + query);
        Response response = target.request(MediaType.APPLICATION_XML).get();
        
        return null;
    }
}
