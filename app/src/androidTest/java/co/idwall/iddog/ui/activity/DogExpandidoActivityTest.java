package co.idwall.iddog.ui.activity;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.idwall.iddog.Constantes;
import co.idwall.iddog.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class DogExpandidoActivityTest {

    @Rule
    public ActivityTestRule<DogExpandidoActivity> activityTestRule =
            new ActivityTestRule<DogExpandidoActivity>(DogExpandidoActivity.class, false, true) {
                @Override
                protected Intent getActivityIntent() {
                    Intent intent = new Intent();
                    intent.putExtra(Constantes.URL_DOG,"https://dog.ceo/api/img/husky/n02110185_10047.jpg");
                    return intent;
                }
            };


    @Before
    public void init(){
    }

    @Test
    public void deve_ExibirCardView_QuandoActivityEhIniciada(){
        onView(withId(R.id.dog_expandido_image)).check(matches(isDisplayed()));

    }
}