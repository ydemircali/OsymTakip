package osymtakip.yakuprnk.com.alertclass;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.StringTokenizer;

import osymtakip.yakuprnk.com.R;

/**
 * Created by Yakup on 12.04.2017.
 */
public class SinavDetayAlert {

    public void showDialog(Context activity, String sadi, String sdetay,String slink, String starihi, String sbasvuru,String sbasvuruend, String ssonuc){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView sinav_adi = (TextView) dialog.findViewById(R.id.sinav_adi);
        TextView sinav_detay = (TextView) dialog.findViewById(R.id.sinav_detay);
        TextView sinav_link = (TextView) dialog.findViewById(R.id.sinav_link);
        TextView sinav_tarihi = (TextView) dialog.findViewById(R.id.sinav_tarihi);
        TextView basvuru_tarihi = (TextView) dialog.findViewById(R.id.basvuru_tarihi);
        TextView sonuc_tarihi = (TextView) dialog.findViewById(R.id.sonuc_tarihi);

        sinav_adi.setText(sadi);
        sinav_detay.setText(sdetay);
        sinav_link.setText(slink);
        sinav_tarihi.setText(starihi);
        basvuru_tarihi.setText(sbasvuru+" - "+sbasvuruend);
        sonuc_tarihi.setText(ssonuc);


        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    private String aybul(String sinav_tarihi) {

        String[] aylar={"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"};
        StringTokenizer sinavD = new StringTokenizer(sinav_tarihi, ".");
        String gun=sinavD.nextToken(); int ay=Integer.parseInt(sinavD.nextToken());String gerisi=sinavD.nextToken();
        return gun+" "+aylar[ay-1]+" "+gerisi;

    }
}