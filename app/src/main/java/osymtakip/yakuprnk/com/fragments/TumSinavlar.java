package osymtakip.yakuprnk.com.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import osymtakip.yakuprnk.com.Helper.AppController;
import osymtakip.yakuprnk.com.Helper.SessionManager;
import osymtakip.yakuprnk.com.R;
import osymtakip.yakuprnk.com.adapters.SinavAdapter;
import osymtakip.yakuprnk.com.models.SinavDetay;
import osymtakip.yakuprnk.com.models.SinavlarItem;

/**
 * A simple {@link Fragment} subclass.
 */

public class TumSinavlar extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    public TumSinavlar() {

    }
    SessionManager session;
    View view;SinavAdapter adaptorumuz;
    Button btn_enyakin,btn_tumu;
    public ProgressDialog loadingdialog;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tum_sinavlar, container, false);
        session=new SessionManager(getActivity());

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(this);

        if(session.getSinavlar()!=null) {
            Gson gson = new Gson();
            SinavDetay gsonResponse = gson.fromJson(session.getSinavlar(), SinavDetay.class);

            final List<SinavlarItem> sinavlarItemList = gsonResponse.getSinavlar();

            final ListView listemiz = (ListView) view.findViewById(R.id.listemiz);
            adaptorumuz = new SinavAdapter(getActivity(), sinavlarItemList, listemiz);
            listemiz.setAdapter(adaptorumuz);
            listemiz.setEmptyView(view.findViewById(R.id.emptySinavlar));
            adaptorumuz.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Sayfayı aşağı çekerek güncelleyebilirsiniz." , Toast.LENGTH_SHORT).show();
        }
        else {

            swipeRefreshLayout.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            swipeRefreshLayout.setRefreshing(true);
                                            get_sinavlar();
                                        }
                                    }
            );
        }


        return view;

    }


    public void get_sinavlar() {


        String tag_string_req = "req_sinavlar";

        String url = "http://www.refcam.com.tr/ydemircali/OsymTakip/get_sinavlar.php";

        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

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
                        SinavDetay gsonResponse = gson.fromJson(response, SinavDetay.class);
                        session.setSinavlar(response);
                        final List<SinavlarItem> sinavlarItemList = gsonResponse.getSinavlar();

                        final ListView listemiz = (ListView) view.findViewById(R.id.listemiz);
                        adaptorumuz = new SinavAdapter(getActivity(), sinavlarItemList, listemiz);
                        listemiz.setAdapter(adaptorumuz);
                        listemiz.setEmptyView(view.findViewById(R.id.emptySinavlar));


                        adaptorumuz.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "Takvim güncellendi." , Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    // JSON hatası
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Bağlantı hatası: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
                swipeRefreshLayout.setRefreshing(false);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if(session.getSinavlar()==null) {
                    Toast.makeText(getActivity(),
                            "İnternet bağlantınızı kontrol ediniz.", Toast.LENGTH_LONG).show();
                }
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        // request kuyruğuna request ekleme
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    @Override
    public void onRefresh() {
        get_sinavlar();
    }
}
