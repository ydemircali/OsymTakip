package osymtakip.yakuprnk.com.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import osymtakip.yakuprnk.com.Helper.DBHelper;
import osymtakip.yakuprnk.com.adapters.FavoriAdapter;
import osymtakip.yakuprnk.com.models.FavoriteDetay;
import osymtakip.yakuprnk.com.R;

public class FavoriSinavlar extends Fragment {

    public FavoriSinavlar() {
    }

    DBHelper dbHelper;
    View view; FavoriAdapter adaptorumuz;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_favori_sinavlar, container, false);

        dbHelper = new DBHelper(this.getActivity().getApplicationContext());

        listviewdoldur();

        return view;
    }

    public void listviewdoldur() {

        // db den çekilip listview e  ekleniyor...
        final List<FavoriteDetay> favoriteDetayList=dbHelper.getAllFavoriler();
        final ListView listemiz = (ListView) view.findViewById(R.id.liste_favori);
        adaptorumuz=new FavoriAdapter(FavoriSinavlar.this.getActivity(), favoriteDetayList);
        listemiz.setAdapter(adaptorumuz);
        listemiz.setEmptyView(view.findViewById(R.id.emptyElement));


        // listview sınava tıklandığında yapılacak işlem...
        listemiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                diyalogfavorisil(favoriteDetayList.get(position).sinav_adi,favoriteDetayList.get(position).sinav_adi_detay,favoriteDetayList.get(position).sinav_tarihi,favoriteDetayList.get(position).basvuru_tarihi,favoriteDetayList.get(position).sonuc_tarihi);

            }
        });

    }

    private void diyalogfavorisil(final String sinav_adi, final String sinav_adi_detay, final String sinav_tarihi, final String basvuru_tarihi, final String sonuc_tarihi) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle(sinav_adi);
        alertDialog.setMessage(sinav_adi_detay+"\nFavorilerden Silinsin mi ?");
        alertDialog.setNegativeButton("Hayır", null);
        alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                favorisil(sinav_adi, sinav_adi_detay, sinav_tarihi, basvuru_tarihi, sonuc_tarihi);
                Toast.makeText(FavoriSinavlar.this.getActivity(), "Favori Sınav Silindi", Toast.LENGTH_SHORT).show();

            }
        });
        alertDialog.show();

    }

    private void favorisil(String sinav_adi, String sinav_adi_detay, String sinav_tarihi, String basvuru_tarihi, String sonuc_tarihi) {
        dbHelper.FavoriSil(sinav_adi,sinav_adi_detay,sinav_tarihi,basvuru_tarihi,sonuc_tarihi);
        listviewdoldur();
    }





}
