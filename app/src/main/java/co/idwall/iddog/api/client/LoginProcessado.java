package co.idwall.iddog.api.client;

public interface LoginProcessado {
    void sucesso(String token);
    void falha(String mensagemErro);
}
