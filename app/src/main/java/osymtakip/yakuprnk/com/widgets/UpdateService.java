package osymtakip.yakuprnk.com.widgets;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import osymtakip.yakuprnk.com.Helper.DBHelper;
import osymtakip.yakuprnk.com.Helper.DateHelper;
import osymtakip.yakuprnk.com.Helper.SessionManager;
import osymtakip.yakuprnk.com.R;
import osymtakip.yakuprnk.com.models.FavoriteDetay;

/**
 * Created by yutku on 29/11/16.
 */

public class UpdateService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    DBHelper dbHelper;
    SessionManager sessionManager;
    DateHelper dateHelper;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // generates random number

        sessionManager=new SessionManager(this.getApplicationContext());
        dateHelper = new DateHelper();
        dbHelper=new DBHelper(this.getApplicationContext());
        final List<FavoriteDetay> favoriteDetayList=dbHelper.getAllFavoriler();


        RemoteViews view = new RemoteViews(getPackageName(), R.layout.updating_widget);
        if(favoriteDetayList.size()>0) {
            if(favoriteDetayList.size()==1)
            {
                sessionManager.setWidgetId(0);
            }
            view.setTextViewText(R.id.txt_sinav_adi, favoriteDetayList.get(sessionManager.getWidgetId()).getSinav_adi());
            view.setTextViewText(R.id.txt_kalangun, kalangunhesapla(favoriteDetayList.get(sessionManager.getWidgetId()).getSinav_tarihi()));
            view.setTextViewText(R.id.txt_basvuru, aybul2(dateHelper.date_to_just_date(favoriteDetayList.get(sessionManager.getWidgetId()).getBasvuru_tarihi().substring(0, 18))) +
                    "\n" + aybul2(dateHelper.date_to_just_date(favoriteDetayList.get(sessionManager.getWidgetId()).getBasvuru_tarihi().substring(19, favoriteDetayList.get(sessionManager.getWidgetId()).getBasvuru_tarihi().length()))));
            view.setTextViewText(R.id.txt_sinav_tarihi, aybul(dateHelper.date_to_exclude_sec(favoriteDetayList.get(sessionManager.getWidgetId()).getSinav_tarihi())));
            view.setTextViewText(R.id.txt_sonuc, aybul(dateHelper.date_to_exclude_sec(favoriteDetayList.get(sessionManager.getWidgetId()).getSonuc_tarihi())));

        }
        ComponentName theWidget = new ComponentName(this, UpdatingWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(theWidget, view);

        return super.onStartCommand(intent, flags, startId);
    }

    public String kalangunhesapla(String sinav_tarihi) {

        int sgun,say,ngun,nay,nyil;
        StringTokenizer sinavD = new StringTokenizer(sinav_tarihi, " ");
        String sdate=sinavD.nextToken(); String stime=sinavD.nextToken();

        StringTokenizer sinavDate = new StringTokenizer(sdate, "-");
        int yil=Integer.parseInt(sinavDate.nextToken()); say=Integer.parseInt(sinavDate.nextToken());sgun=Integer.parseInt(sinavDate.nextToken());

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = df.format(c.getTime());

        StringTokenizer nowD = new StringTokenizer(formattedDate, ".");
        ngun =Integer.parseInt(nowD.nextToken());nay = Integer.parseInt(nowD.nextToken()); nyil=Integer.parseInt(nowD.nextToken());

        int kgun=(((yil-nyil)*365)+(say-nay)*30)+(sgun-ngun);


        if(kgun<0) {
           return "0";
        }
        else
           return  ""+kgun;

    }

    private String aybul(String sinav_tarihi) {

        String[] aylar={"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"};
        StringTokenizer sinavD = new StringTokenizer(sinav_tarihi, " ");
        String sdate=sinavD.nextToken(); String stime=sinavD.nextToken();

        StringTokenizer sinavDate = new StringTokenizer(sdate, "-");
        int yil=Integer.parseInt(sinavDate.nextToken()); int ay=Integer.parseInt(sinavDate.nextToken());int gun=Integer.parseInt(sinavDate.nextToken());
        return gun+" "+aylar[ay-1]+" "+yil+"\n      "+stime;

    }
    private String aybul2(String sinav_tarihi) {

        String[] aylar={"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"};
        StringTokenizer sinavD = new StringTokenizer(sinav_tarihi, ".");
        String gun=sinavD.nextToken(); int ay=Integer.parseInt(sinavD.nextToken());String gerisi=sinavD.nextToken();
        return gun+" "+aylar[ay-1]+" "+gerisi;

    }
}
