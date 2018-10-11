package co.idwall.iddog.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.filters.Suppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;

import co.idwall.iddog.R;
import co.idwall.iddog.api.Constants;
import co.idwall.iddog.mock.MockHelper;
import co.idwall.iddog.mock.MockConstantes;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest extends InstrumentationTestCase {

    private MockWebServer server;

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule =
            new ActivityTestRule<>(LoginActivity.class, false, true);

    @Before
    public void setUp() throws Exception {
        super.setUp();
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Constants.BASE_URL = server.url("/").toString();
    }

    @Test
    public void deve_ExibirViews_QuandoActivityInicia(){
        onView(withId(R.id.login_icone_dog)).check(matches(isDisplayed()));
        onView(withId(R.id.login_informativo)).check(matches(isDisplayed()));
        onView(withId(R.id.login_email)).check(matches(isDisplayed()));
        onView(withId(R.id.login_entrar)).check(matches(isDisplayed()));
    }

    @Test
    public void deve_ExibirProgressBar_QuandoBotaoEntrarEhClicado() {
        onView(withId(R.id.login_entrar)).perform(click());
        onView(withId(R.id.login_progresbar)).check(matches(isDisplayed()));
    }

    @Test
    public void deve_RetirarProgressBar_QuandoAhApiResponder() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(MockHelper.getStringFromFile(getInstrumentation().getContext(), MockConstantes.SIGNUP_ERRO)));

        onView(withId(R.id.login_entrar)).perform(click());
        onView(withText(R.string.login_entendido)).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.login_progresbar)).check(matches(not(isDisplayed())));
    }

    @Test
    public void deve_ExibirDialogoComMensagemDeEmailInvalido_QuandoEditTextEstaVazioEhBotaoEhClicado() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(MockHelper.getStringFromFile(getInstrumentation().getContext(), MockConstantes.SIGNUP_ERRO)));

        onView(withId(R.id.login_email)).perform(clearText());
        onView(withId(R.id.login_entrar)).perform(click());
        onView(withText(R.string.login_email_invalido)).inRoot(isDialog()).check(matches(isDisplayed()));
    }

    @Test
    public void deve_ExibirDialogoComMensagemDeEmailInvalido_QuandoEditTextEstaComEmailInvalidoEhBotaoEhClicado() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(MockHelper.getStringFromFile(getInstrumentation().getContext(), MockConstantes.SIGNUP_ERRO)));

        onView(withId(R.id.login_email)).perform(typeText("email invalido"));
        onView(withId(R.id.login_entrar)).perform(click());
        onView(withText(R.string.login_email_invalido)).inRoot(isDialog()).check(matches(isDisplayed()));
    }

    @Test
    public void deve_IrParaFeedActivity_QuandoBotaoEntrarEhClicadoComEmailValido() throws Exception {
        Intents.init();
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(MockHelper.getStringFromFile(getInstrumentation().getContext(), MockConstantes.SIGNUP_SUCESSO)));

        onView(withId(R.id.login_email)).perform(typeText("dogfun@gmail.com"),closeSoftKeyboard());
        Matcher<Intent> matcher = hasComponent(FeedActivity.class.getName());

        ActivityResult resultado = new ActivityResult(Activity.RESULT_OK,null);
        intending(matcher).respondWith(resultado);

        onView(withId(R.id.login_entrar)).perform(click());
        intended(matcher);
        Intents.release();
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

}