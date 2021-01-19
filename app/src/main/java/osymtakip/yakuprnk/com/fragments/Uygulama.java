package osymtakip.yakuprnk.com.fragments;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import osymtakip.yakuprnk.com.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Uygulama extends Fragment {


    public Uygulama() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_uygulama,container,false);



        return view;
    }

}
