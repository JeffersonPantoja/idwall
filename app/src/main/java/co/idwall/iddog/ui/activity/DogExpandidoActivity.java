package co.idwall.iddog.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import co.idwall.iddog.R;

import static co.idwall.iddog.Constantes.URL_DOG;

public class DogExpandidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_expandido);


        Intent intent = getIntent();
        String urlDog = intent.getStringExtra(URL_DOG);


        ImageView avatarExpandido = findViewById(R.id.dog_expandido_image);
        Picasso.get().load(urlDog).into(avatarExpandido);
    }
}
