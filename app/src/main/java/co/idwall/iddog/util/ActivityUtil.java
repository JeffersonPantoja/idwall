package co.idwall.iddog.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import co.idwall.iddog.R;
import co.idwall.iddog.ui.activity.LoginActivity;

import static co.idwall.iddog.ui.Constantes.TOKEN;

public class ActivityUtil {

    public static void negaAcesso(final Context context, String mensagemErro) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(mensagemErro)
                .setPositiveButton(R.string.login_entendido, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        PreferencesUtil.deletaTokenIvalido(context);
                        vaiParaOutraActivity(context, LoginActivity.class);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void vaiParaOutraActivity(Context context, Class clazz) {
        Intent outraActivity = new Intent(context, clazz);
        iniciaActivity(context, outraActivity);
    }

    public static void vaiParaOutraActivityComExtra(Context context, String extra, Class clazz) {
        Intent outraActivity = new Intent(context, clazz);
        outraActivity.putExtra(TOKEN, extra);
        iniciaActivity(context, outraActivity);
    }

    private static void iniciaActivity(Context context, Intent outraActivity) {
        context.startActivity(outraActivity);
        Activity activity = (Activity) context;
        activity.finish();
    }
}
