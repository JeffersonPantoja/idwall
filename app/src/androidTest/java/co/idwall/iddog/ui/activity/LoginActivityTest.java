package co.idwall.iddog.ui.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import co.idwall.iddog.R;
import okhttp3.mockwebserver.MockWebServer;

import static android.app.Instrumentation.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    private MockWebServer server;

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule =
            new ActivityTestRule<>(LoginActivity.class, false, true);

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    public void deve_ExibirViews_QuandoActivityInicia(){
        onView(withId(R.id.login_icone_dog)).check(matches(isDisplayed()));
        onView(withId(R.id.login_informativo)).check(matches(isDisplayed()));
        onView(withId(R.id.login_email)).check(matches(isDisplayed()));
        onView(withId(R.id.login_entrar)).check(matches(isDisplayed()));
    }

//    @Test
//    public void deve_ExibirDialogo_QuandoEditTextEhEstaVazioEBotaoEhClicado(){
//        onView(withId(R.id.login_email)).perform(clearText());
//        onView(withId(R.id.login_entrar)).perform(click());
//        onView(withText(R.string.login_email_invalido)).check(matches(isDisplayed()));
////        onView(withText(R.string.login_entendido)).perform(click());
////        onView(withText(R.string.login_entendido)).check(matches(not(isDisplayed())));
//    }

//    @Test
//    public void deve_ExibirProgresBar_QuandoBotaoEntrarEhClicado(){
//        onView(withId(R.id.login_entrar)).perform(click());
//        onView(withId(R.id.login_progresbar)).check(matches(isDisplayed()));
//    }

    @Test
    public void deve_IrParaFeedActivity_QuandoBotaoEntrarEhClicadoComEmailValido(){
        Intents.init();
        onView(withId(R.id.login_email)).perform(typeText("dogfun@idwall.co"),closeSoftKeyboard());
        Matcher<Intent> matcher = hasComponent(FeedActivity.class.getName());


        ActivityResult resultado = new ActivityResult(Activity.RESULT_OK,null);
        intending(matcher).respondWith(resultado);

        onView(withId(R.id.login_entrar)).perform(click());
        intended(matcher);
        Intents.release();
    }

}