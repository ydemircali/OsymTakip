package osymtakip.yakuprnk.com.fragments;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import osymtakip.yakuprnk.com.Helper.DBHelper;
import osymtakip.yakuprnk.com.Helper.DateHelper;
import osymtakip.yakuprnk.com.Helper.SessionManager;
import osymtakip.yakuprnk.com.R;
import osymtakip.yakuprnk.com.models.FavoriteDetay;
import osymtakip.yakuprnk.com.widgets.UpdateService;
import osymtakip.yakuprnk.com.widgets.UpdatingWidget;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ayarlar extends Fragment {


    public Ayarlar() {
        // Required empty public constructor
    }

    View view;
    DBHelper dbHelper;
    DateHelper dateHelper;
    SessionManager sessionManager;
    TextView favorite_text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_ayarlar, container, false);

        dbHelper = new DBHelper(this.getActivity().getApplicationContext());
        dateHelper=new DateHelper();
        sessionManager=new SessionManager(this.getActivity().getApplicationContext());
        final List<FavoriteDetay> favoriteDetayList=dbHelper.getAllFavoriler();

        ArrayList<Favorites> favorites = new ArrayList<>();

        if(favoriteDetayList.size()>0) {

            if(favoriteDetayList.size()==1)
            {
                sessionManager.setWidgetId(0);
            }
            for (int i = 0; i < favoriteDetayList.size(); i++) {
                favorites.add(new Favorites(favoriteDetayList.get(i).getSinav_adi() + " - " +
                        aybul(dateHelper.date_to_exclude_sec(favoriteDetayList.get(i).getSinav_tarihi())),
                        favoriteDetayList.get(i).getId()));
            }

            Spinner widget_spinner = (Spinner) view.findViewById(R.id.widget_spinner);

            ArrayAdapter<Favorites> adapter =
                    new ArrayAdapter<Favorites>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item, favorites);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

            widget_spinner.setAdapter(adapter);

            widget_spinner.setSelection(sessionManager.getWidgetId());

            favorite_text=(TextView) view.findViewById(R.id.favorite_text);

            favorite_text.setText(favoriteDetayList.get(sessionManager.getWidgetId()).getSinav_adi()+ " - " +
                    aybul(dateHelper.date_to_exclude_sec(favoriteDetayList.get(sessionManager.getWidgetId()).getSinav_tarihi())));


            widget_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                    sessionManager.setWidgetId(position);
                    favorite_text.setText(favoriteDetayList.get(sessionManager.getWidgetId()).getSinav_adi()+ " - " +
                            aybul(dateHelper.date_to_exclude_sec(favoriteDetayList.get(sessionManager.getWidgetId()).getSinav_tarihi())));

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {

                }

            });
        }


        return view;
    }

    private class Favorites {
        private String favorite_name;
        private int favorite_id;

        public Favorites() {
        }

        public Favorites(String favorite_name, int favorite_id) {
            this.favorite_name = favorite_name;
            this.favorite_id = favorite_id;
        }

        public String getfavorite_name() {
            return favorite_name;
        }

        public void setfavorite_name(String favorite_name) {
            this.favorite_name = favorite_name;
        }

        public int getfavorite_id() {
            return favorite_id;
        }

        public void setfavorite_id(int favorite_id) {
            this.favorite_id = favorite_id;
        }

        /**
         * Pay attention here, you have to override the toString method as the
         * ArrayAdapter will reads the toString of the given object for the name
         *
         * @return favorite_name
         */
        @Override
        public String toString() {
            return favorite_name;
        }
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
