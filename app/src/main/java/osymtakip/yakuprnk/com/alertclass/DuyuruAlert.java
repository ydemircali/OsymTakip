package osymtakip.yakuprnk.com.alertclass;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import osymtakip.yakuprnk.com.R;

/**
 * Created by Yakup on 14.04.2017.
 */

public class DuyuruAlert {

    public void showDialog(Context activity, String duyuru,String link){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_duyuru);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView txtduyuru = (TextView) dialog.findViewById(R.id.txt_duyuru);
        txtduyuru.setText(duyuru);
        TextView txtlink = (TextView) dialog.findViewById(R.id.txt_duyuru_link);
        txtlink.setText(link);


        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
