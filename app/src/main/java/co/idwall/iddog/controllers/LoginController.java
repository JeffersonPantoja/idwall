package co.idwall.iddog.controllers;

import java.util.Map;

import co.idwall.iddog.api.client.DogWebClient;
import co.idwall.iddog.api.client.LoginProcessado;

public class LoginController {


    public void loginEntrar(Map<String, String> email, LoginProcessado listener){
        DogWebClient client = new DogWebClient();
        client.loginEntrar(email,listener);
    }

}
