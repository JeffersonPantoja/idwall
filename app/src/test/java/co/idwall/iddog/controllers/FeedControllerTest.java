package co.idwall.iddog.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.idwall.iddog.api.client.DogWebClient;
import co.idwall.iddog.api.client.FeedProcessado;
import co.idwall.iddog.model.FeedCategoria;
import co.idwall.iddog.ui.fragment.FeedFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FeedControllerTest {


    @Mock
    private DogWebClient client;

    @Mock
    private FeedProcessado listener;


    @Test
    public void deve_DevolverUmaListaDeUrls_QuandoBuscaFeedNaApiDog() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                FeedProcessado argument = invocation.getArgument(2);
                argument.sucesso(new ArrayList<String>(Arrays.asList("url01", "url02")));
                return null;
            }
        }).when(client).buscarFeed(any(String.class), any(String.class), any(FeedProcessado.class));

        FeedController feedController = new FeedController();

        feedController.buscarFeed("teste", "teste" , listener);

        verify(client).buscarFeed(anyString(), anyString() , any(FeedProcessado.class));
        verify(listener).sucesso(new ArrayList<String>(Arrays.asList("url01", "url02")));
    }




}