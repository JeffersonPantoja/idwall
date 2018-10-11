package co.idwall.iddog.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static co.idwall.iddog.Constantes.JA_ABRIU_APP;
import static co.idwall.iddog.Constantes.PREFERENCIAS_DO_USUARIO;
import static co.idwall.iddog.Constantes.TOKEN;

public class PreferencesUtil {

    public static void salvaPreferenciaJaAbriu(Context context, boolean valor){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCIAS_DO_USUARIO, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(JA_ABRIU_APP, valor);
        editor.commit();
    }


    public static void deletaTokenIvalido(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCIAS_DO_USUARIO, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(TOKEN);
        editor.apply();
    }

    public static void salvaToken(Context context, String token) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCIAS_DO_USUARIO, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TOKEN, token);
        editor.commit();
    }
}
