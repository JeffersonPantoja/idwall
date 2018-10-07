package co.idwall.iddog.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FeedCategoria {

    @JsonProperty("category")
    private String categoria;

    @JsonProperty("list")
    private List<String> imagemDog;

    public String getCategoria() {
        return categoria;
    }

    public List<String> getImagemDog() {
        return imagemDog;
    }
}
