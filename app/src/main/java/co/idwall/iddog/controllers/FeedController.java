package co.idwall.iddog.controllers;

import co.idwall.iddog.api.client.DogWebClient;
import co.idwall.iddog.api.client.FeedProcessado;

public class FeedController {

    public void buscarFeed(String token, String categoria, FeedProcessado listner){
        DogWebClient client = new DogWebClient();
        client.buscarFeed(token,categoria,listner);
    }


}
