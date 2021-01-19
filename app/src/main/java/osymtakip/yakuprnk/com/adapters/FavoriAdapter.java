package osymtakip.yakuprnk.com.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import osymtakip.yakuprnk.com.Helper.DBHelper;
import osymtakip.yakuprnk.com.Helper.DateHelper;
import osymtakip.yakuprnk.com.R;
import osymtakip.yakuprnk.com.alertclass.SinavDetayAlert;
import osymtakip.yakuprnk.com.models.FavoriteDetay;

/**
 * Created by Yakup on 09.04.2017.
 */

public class FavoriAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    DBHelper dbHelper;
    DateHelper dateHelper;
    private List<FavoriteDetay> favoriteDetayList;
    private TextView sinav_adi,sinav_tarihi, basvuru_tarihi,sonuc_tarihi,kalangun;
    ImageView info,favorisinav;
    ListView mlist; View view;

    public FavoriAdapter(Activity activity, List<FavoriteDetay> favoriteDetays) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        dbHelper=new DBHelper(activity.getApplication().getApplicationContext());
        dateHelper=new DateHelper();
        favoriteDetayList = favoriteDetays;
    }

    @Override
    public int getCount() {
        return favoriteDetayList.size();
    }

    @Override
    public Object getItem(int position) {
        return favoriteDetayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        view = mInflater.inflate(R.layout.favori_sinavlar, null);
        sinav_adi=(TextView)view.findViewById(R.id.txt_sinav_adi);
        sinav_tarihi=(TextView)view.findViewById(R.id.txt_sinav_tarihi);
        basvuru_tarihi=(TextView)view.findViewById(R.id.txt_basvuru);
        sonuc_tarihi=(TextView)view.findViewById(R.id.txt_sonuc);
        kalangun=(TextView)view.findViewById(R.id.txt_kalangun);
        info=(ImageView)view.findViewById(R.id.info);


        final FavoriteDetay favoriteDetay=favoriteDetayList.get(position);
        sinav_adi.setText(favoriteDetay.sinav_adi);
        sinav_tarihi.setText(aybul(dateHelper.date_to_exclude_sec(favoriteDetay.getSinav_tarihi())));
        basvuru_tarihi.setText(aybul2(dateHelper.date_to_just_date(favoriteDetay.getBasvuru_tarihi().substring(0,18)))+
                "\n"+aybul2(dateHelper.date_to_just_date(favoriteDetay.getBasvuru_tarihi().substring(19,favoriteDetay.getBasvuru_tarihi().length()))));
        sonuc_tarihi.setText(aybul(dateHelper.date_to_exclude_sec(favoriteDetay.getSonuc_tarihi())));
        kalangunhesapla(favoriteDetay.getSinav_tarihi());

        return view;
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



    public void kalangunhesapla(String sinav_tarihi) {

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
            kalangun.setTextSize(75);
            kalangun.setText("Bitti");
        }
        else
            kalangun.setText(""+kgun);



    }
}
