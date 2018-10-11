package co.idwall.iddog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.idwall.iddog.model.Erro;

public class ErroResponse {

    @JsonProperty("error")
    private Erro erro;

    public Erro getErro() {
        return erro;
    }
}
