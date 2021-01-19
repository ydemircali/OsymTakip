package osymtakip.yakuprnk.com.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.StringTokenizer;

import osymtakip.yakuprnk.com.Helper.DBHelper;
import osymtakip.yakuprnk.com.Helper.DateHelper;
import osymtakip.yakuprnk.com.R;
import osymtakip.yakuprnk.com.alertclass.SinavDetayAlert;
import osymtakip.yakuprnk.com.models.FavoriteDetay;
import osymtakip.yakuprnk.com.models.SinavlarItem;

/**
 * Created by Yakup on 05.04.2017.
 */

public class SinavAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<SinavlarItem> sinavList;
    public TextView sinav_adi,sinav_tarihi;
    public ImageView favori,info;
    public DateHelper dateHelper;
    public DBHelper dbHelper;
    ListView mlist;
    public SinavAdapter(Activity activity, List<SinavlarItem> sinavlar, ListView list) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        sinavList = sinavlar;
        mlist=list;
        dbHelper=new DBHelper(activity);

    }

    @Override
    public int getCount() {
        return sinavList.size();
    }

    @Override
    public Object getItem(int position) {
        return sinavList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    View view;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        dateHelper=new DateHelper();
        view = mInflater.inflate(R.layout.tumsinavlar, null);
        sinav_adi = (TextView) view.findViewById(R.id.sinav_adi);
        sinav_tarihi = (TextView) view.findViewById(R.id.sinav_tarihi);
        favori=(ImageView)view.findViewById(R.id.favori);

        SinavlarItem sinavlarItem=sinavList.get(position);

        sinav_adi.setText(sinavlarItem.getName());
        sinav_tarihi.setText(aybul(sinavlarItem.getSinavDate()).substring(0,aybul(sinavlarItem.getSinavDate()).length()-3));

        String sonuc = dbHelper.FavoriVarmi(sinavList.get(position).getName(),
                sinavList.get(position).getContent(),
                sinavList.get(position).getSinavDate(),
                sinavList.get(position).getBasvuruStartDate()+" "+sinavList.get(position).getBasvuruEndDate(),
                sinavList.get(position).getSonucDate());
        if (sonuc == "var") {
            favori.setImageResource(R.mipmap.favorite);
        }


        favori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sonuc=dbHelper.FavoriVarmi(sinavList.get(position).getName(),
                        sinavList.get(position).getContent(),
                        sinavList.get(position).getSinavDate(),
                        sinavList.get(position).getBasvuruStartDate()+" "+sinavList.get(position).getBasvuruEndDate(),
                        sinavList.get(position).getSonucDate());
                if(sonuc=="yok") {
                    dbHelper.insertFavoriler(new FavoriteDetay(sinavList.get(position).getName(),
                        sinavList.get(position).getContent(),
                        sinavList.get(position).getSinavDate(),
                        sinavList.get(position).getBasvuruStartDate()+" "+sinavList.get(position).getBasvuruEndDate(),
                        sinavList.get(position).getSonucDate()));
                    favori.setImageResource(R.mipmap.favorite);
                    notifyDataSetChanged();
                    Toast.makeText(view.getContext(),"Favorilere Eklendi",Toast.LENGTH_SHORT).show();
                }
                else {
                    dbHelper.FavoriSil(sinavList.get(position).getName(),
                            sinavList.get(position).getContent(),
                            sinavList.get(position).getSinavDate(),
                            sinavList.get(position).getBasvuruStartDate()+" "+sinavList.get(position).getBasvuruEndDate(),
                            sinavList.get(position).getSonucDate());

                    favori.setImageResource(R.mipmap.favorite_pasif);
                    notifyDataSetChanged();
                    Toast.makeText(view.getContext(),"Favorilerden Silindi", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SinavDetayAlert alert=new SinavDetayAlert();
                alert.showDialog(view.getContext(),sinavList.get(position).getName(),
                        sinavList.get(position).getContent(),sinavList.get(position).getLink(),dateHelper.date_to_date(sinavList.get(position).getSinavDate()),
                        dateHelper.date_to_just_date(sinavList.get(position).getBasvuruStartDate()),
                        dateHelper.date_to_just_date(sinavList.get(position).getBasvuruEndDate()),
                        dateHelper.date_to_date(sinavList.get(position).getSonucDate()));
            }
        });



        return view;
    }

    private String aybul(String sinav_tarihi) {

        String[] aylar={"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"};
        StringTokenizer sinavD = new StringTokenizer(sinav_tarihi, " ");
        String sdate=sinavD.nextToken(); String stime=sinavD.nextToken();

        StringTokenizer sinavDate = new StringTokenizer(sdate, "-");
        int yil=Integer.parseInt(sinavDate.nextToken()); int ay=Integer.parseInt(sinavDate.nextToken());int gun=Integer.parseInt(sinavDate.nextToken());
        return gun+" "+aylar[ay-1]+" "+yil+" "+stime;

    }

}
