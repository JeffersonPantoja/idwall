package co.idwall.iddog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario implements Serializable {

    @JsonProperty("_id")
    private String id;

    private String token;

    public String getToken() {
        return token;
    }
}
