package co.idwall.iddog.ui.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import co.idwall.iddog.Constantes;
import co.idwall.iddog.R;
import co.idwall.iddog.api.Constants;
import co.idwall.iddog.mock.MockConstantes;
import co.idwall.iddog.mock.MockHelper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class FeedActivityTest extends InstrumentationTestCase {

    private MockWebServer server;

    @Rule
    public ActivityTestRule<FeedActivity> activityTestRule =
            new ActivityTestRule<>(FeedActivity.class, false, true);

    @Before
    public void init(){
        activityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Constants.BASE_URL = server.url("/").toString();
    }

    @Test
    public void deve_ExibirQuatroTabs_QuandoActivityEhIniciada(){
        onView(withText("HUSKY")).check(matches(isDisplayed()));
        onView(withText("HOUND")).check(matches(isDisplayed()));
        onView(withText("PUG")).check(matches(isDisplayed()));
        onView(withText("LABRADOR")).check(matches(isDisplayed()));
    }

    @Test
    public void deve_ExibirUmImageView_QuandoBuscarFeedNaApi() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(MockHelper.getStringFromFile(getInstrumentation().getContext(), MockConstantes.FEED_SUCESSO_UM_ITEM)));

        activityTestRule.launchActivity(new Intent());
        onView(withId(R.id.item_card)).check(matches(isDisplayed()));
    }

    @Test
    public void deve_irParaDogExpandidoActivity_QuandoImageViewEhClicado() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(MockHelper.getStringFromFile(getInstrumentation().getContext(), MockConstantes.FEED_SUCESSO)));

        activityTestRule.launchActivity(new Intent());
        Intents.init();
        Matcher<Intent> matcher = allOf(
                hasComponent(DogExpandidoActivity.class.getName()),
                hasExtraWithKey(Constantes.TOKEN)
        );

        Instrumentation.ActivityResult
                result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);

        intending(matcher).respondWith(result);

        ViewInteraction viewInteraction = onView(allOf(
                withId(R.id.feed_lista),
                isDescendantOfA(withTagValue(is((Object) "https://dog.ceo/api/img/husky/n02110185_10047.jpg"))))
        );
        //.perform(actionOnItemAtPosition(0, click()));

        onView(allOf()withId(R.id.feed_lista)).perform(actionOnItemAtPosition(3, click()));

        intended(matcher);
        Intents.release();
    }

    @Test
    public void deve_ExibirErroDeAutorizacao_QuandoBuscarFeedNaApi() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(401)
                .setBody(MockHelper.getStringFromFile(getInstrumentation().getContext(), MockConstantes.FEED_ERRO)));

        activityTestRule.launchActivity(new Intent());
        onView(withText("Unauthorized")).inRoot(isDialog()).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }
}