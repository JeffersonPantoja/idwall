package co.idwall.iddog.api.client;

import java.util.List;

public interface FeedProcessado {
    void sucesso(List<String> urlsDog);
    void falha(String mensagemErro);
}
