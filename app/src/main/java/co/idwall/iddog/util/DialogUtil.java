package co.idwall.iddog.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import co.idwall.iddog.R;

public class DialogUtil {

    private Context context;

    public static void exibirMensagemErro(Context context, String mensagemErro) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(mensagemErro)
                .setPositiveButton(R.string.login_entendido, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
