package osymtakip.yakuprnk.com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import osymtakip.yakuprnk.com.Helper.AppController;
import osymtakip.yakuprnk.com.Helper.DBHelper;
import osymtakip.yakuprnk.com.Helper.DateHelper;
import osymtakip.yakuprnk.com.Helper.SessionManager;
import osymtakip.yakuprnk.com.R;
import osymtakip.yakuprnk.com.models.DuyuruModel;
import osymtakip.yakuprnk.com.models.DuyurularItem;
import osymtakip.yakuprnk.com.models.FavoriteDetay;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnaSayfa extends Fragment {


    public AnaSayfa() {
        // Required empty public constructor
    }

    View view;

    DBHelper dbHelper;
    DateHelper dateHelper;
    SessionManager session;
    private TextView sinav_adi,sinav_tarihi, basvuru_tarihi,sonuc_tarihi,kalangun,duyuru,duyuru_tarih;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_anasayfa,container,false);
        dbHelper=new DBHelper(getActivity());
        dateHelper=new DateHelper();
        session=new SessionManager(getActivity());

        /*MobileAds.initialize(getActivity(), "ca-app-pub-1553226835028802~6599079827");
        AdView  mAdView = (AdView)view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/

        sinav_adi=(TextView)view.findViewById(R.id.txt_sinav_adi);
        sinav_tarihi=(TextView)view.findViewById(R.id.txt_sinav_tarihi);
        basvuru_tarihi=(TextView)view.findViewById(R.id.txt_basvuru);
        sonuc_tarihi=(TextView)view.findViewById(R.id.txt_sonuc);
        kalangun=(TextView)view.findViewById(R.id.txt_kalangun);

        final List<FavoriteDetay> favoriteDetayList=dbHelper.getAllFavoriler();
        int i=favoriteDetayList.size();

        while(i>0) {
            i--;
            int kalan=kalangunhesapla(favoriteDetayList.get(i).getSinav_tarihi());
            if( kalan>0) {
                kalangun.setText(""+kalan);
                sinav_adi.setText(favoriteDetayList.get(i).getSinav_adi());
                sinav_tarihi.setText(aybul(dateHelper.date_to_exclude_sec(favoriteDetayList.get(i).getSinav_tarihi())));
                basvuru_tarihi.setText(aybul2(dateHelper.date_to_just_date(favoriteDetayList.get(i).getBasvuru_tarihi().substring(0, 18))) +
                        "\n" + aybul2(dateHelper.date_to_just_date(favoriteDetayList.get(i).getBasvuru_tarihi().substring(19, favoriteDetayList.get(0).basvuru_tarihi.length()))));
                sonuc_tarihi.setText(aybul(dateHelper.date_to_exclude_sec(favoriteDetayList.get(i).getSonuc_tarihi())));
            }
        }

        if(session.getDuyurular()!=null) {
            Gson gson = new Gson();
            DuyuruModel gsonResponse = gson.fromJson(session.getDuyurular(), DuyuruModel.class);
            final List<DuyurularItem> duyurularItems = gsonResponse.getDuyurular();
            duyuru = (TextView) view.findViewById(R.id.duyuru);
            duyuru_tarih = (TextView) view.findViewById(R.id.duyuru_tarihi);
            duyuru.setText(duyurularItems.get(0).getContent().substring(0,duyurularItems.get(0).getContent().length()-12));
            duyuru_tarih.setText(dateHelper.date_to_just_date(duyurularItems.get(0).getDuyuruDate()));
        }
        else
        {
            get_duyurular();
        }


        return view;
    }
    public int kalangunhesapla(String sinav_tarihi) {

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

        int kgun=(((yil-nyil)*365)+((say-nay)*30)+(sgun-ngun));

        if(kgun<0) {
            return 0;
        }
        else
            return kgun;

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

    public void get_duyurular() {


        String tag_string_req = "req_duyurular";

        String url="http://www.refcam.com.tr/ydemircali/OsymTakip/get_duyuru.php";

        StringRequest strReq = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jObj = new JSONObject(response);
                    boolean status = jObj.getBoolean("status");
                    if (!status) {
                        Toast.makeText(getActivity().getApplicationContext(),
                                jObj.getString("message"), Toast.LENGTH_LONG).show();

                    } else {


                        Gson gson = new Gson();
                        DuyuruModel gsonResponse = gson.fromJson(response, DuyuruModel.class);
                        session.setDuyurular(response);
                        final List<DuyurularItem> duyurularItems = gsonResponse.getDuyurular();
                        duyuru = (TextView) view.findViewById(R.id.duyuru);
                        duyuru_tarih = (TextView) view.findViewById(R.id.duyuru_tarihi);
                        duyuru.setText(duyurularItems.get(0).getContent().substring(0,duyurularItems.get(0).getContent().length()-12));
                        duyuru_tarih.setText(dateHelper.date_to_just_date(duyurularItems.get(0).getDuyuruDate()));


                    }

                } catch (JSONException e) {
                    // JSON hatası
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Bağlantı hatası: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if(session.getDuyurular()==null) {
                    Toast.makeText(getActivity(),
                            "İnternet bağlantınızı kontrol ediniz.", Toast.LENGTH_LONG).show();
                }

            }
        });

        // request kuyruğuna request ekleme
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


}
