package co.idwall.iddog.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;

import co.idwall.iddog.api.client.DogWebClient;
import co.idwall.iddog.api.client.FeedProcessado;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
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
        }).when(client).buscaFeed(any(String.class), any(String.class), any(FeedProcessado.class));

        FeedController feedController = new FeedController();

        feedController.buscaFeed("teste", "teste" , listener);

        verify(client).buscaFeed(anyString(), anyString() , any(FeedProcessado.class));
        verify(listener).sucesso(new ArrayList<String>(Arrays.asList("url01", "url02")));
    }




}