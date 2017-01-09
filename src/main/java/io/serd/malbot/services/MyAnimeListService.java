package io.serd.malbot.services;

import io.serd.malbot.BuildVars;
import io.serd.malbot.domain.AnimeSeries;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.telegram.telegrambots.logging.BotLogger;

/**
 *
 * @author Atte Lassila
 */
public class MyAnimeListService {
    
    private static final String LOG_TAG = "MAL_SERVICE";

    public MyAnimeListService() {
    }

    public static AnimeSeries queryAnime(String query) {
        query = query.replace(' ', '+');
        Client client = ClientBuilder.newClient(clientConfig());
        WebTarget target = client.target("https://myanimelist.net/api/anime/search.xml?q=" + query);
        String response = target.request(MediaType.APPLICATION_XML)
                .get()
                .readEntity(String.class);

        return unmarshal(response);
    }

    private static ClientConfig clientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(BuildVars.malUser, BuildVars.malPass);
        clientConfig.register(feature);
        clientConfig.register(JacksonFeature.class);
        return clientConfig;
    }

    private static AnimeSeries unmarshal(String xml) {
        try {
            JAXBContext jc = JAXBContext.newInstance(AnimeSeries.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            AnimeSeries series = (AnimeSeries) unmarshaller.unmarshal(new StringReader(xml));
            
            return series;
        } catch (JAXBException ex) {
            BotLogger.error(LOG_TAG, ex);
        }

        return null;
    }
}
