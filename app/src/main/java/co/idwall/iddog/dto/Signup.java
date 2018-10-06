package co.idwall.iddog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.idwall.iddog.model.Usuario;


public class Signup {

    @JsonProperty("user")
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }
}
