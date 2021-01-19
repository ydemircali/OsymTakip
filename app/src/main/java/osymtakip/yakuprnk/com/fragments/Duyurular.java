package osymtakip.yakuprnk.com.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.List;

import osymtakip.yakuprnk.com.Helper.AppController;
import osymtakip.yakuprnk.com.Helper.SessionManager;
import osymtakip.yakuprnk.com.R;
import osymtakip.yakuprnk.com.adapters.DuyuruAdapter;
import osymtakip.yakuprnk.com.adapters.SinavAdapter;
import osymtakip.yakuprnk.com.models.DuyuruModel;
import osymtakip.yakuprnk.com.models.DuyurularItem;
import osymtakip.yakuprnk.com.models.SinavDetay;
import osymtakip.yakuprnk.com.models.SinavlarItem;

public class Duyurular extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    public Duyurular() {

    }
    SessionManager session;
    View view;
    DuyuruAdapter adaptorumuz;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_duyurular, container, false);

        session=new SessionManager(getContext());
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(this);


        if(session.getDuyurular()!=null) {
            Gson gson = new Gson();
            DuyuruModel gsonResponse = gson.fromJson(session.getDuyurular(), DuyuruModel.class);
            final List<DuyurularItem> duyurularItems = gsonResponse.getDuyurular();

            final ListView listemiz = (ListView) view.findViewById(R.id.liste_duyuru);
            adaptorumuz = new DuyuruAdapter(getActivity(), duyurularItems, listemiz);
            listemiz.setAdapter(adaptorumuz);
            listemiz.setEmptyView(view.findViewById(R.id.emptyDuyuru));
            adaptorumuz.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Sayfayı aşağı çekerek güncelleyebilirsiniz." , Toast.LENGTH_SHORT).show();
        }
        else
        {
            swipeRefreshLayout.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            swipeRefreshLayout.setRefreshing(true);
                                            get_duyurular();
                                        }
                                    }
            );

        }


        return view;
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

                        final ListView listemiz = (ListView) view.findViewById(R.id.liste_duyuru);
                        adaptorumuz = new DuyuruAdapter(getActivity(), duyurularItems, listemiz);
                        listemiz.setAdapter(adaptorumuz);
                        listemiz.setEmptyView(view.findViewById(R.id.emptyDuyuru));
                        adaptorumuz.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "Duyurular Güncellendi " , Toast.LENGTH_LONG).show();
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
                if(session.getDuyurular()==null) {
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
        get_duyurular();
    }
}
