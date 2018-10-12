package co.idwall.iddog.api.client;

import java.util.List;

import co.idwall.iddog.model.FeedCategoria;

public interface FeedProcessado {
    void sucesso(FeedCategoria feed);
    void falha(String mensagemErro);
}
