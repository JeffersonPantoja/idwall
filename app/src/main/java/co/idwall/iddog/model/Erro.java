package co.idwall.iddog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Erro {

    @JsonProperty("message")
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }
}
