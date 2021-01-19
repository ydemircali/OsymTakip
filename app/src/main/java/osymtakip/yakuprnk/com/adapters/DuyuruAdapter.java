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

import java.util.List;
import java.util.StringTokenizer;

import osymtakip.yakuprnk.com.Helper.DateHelper;
import osymtakip.yakuprnk.com.R;
import osymtakip.yakuprnk.com.alertclass.DuyuruAlert;
import osymtakip.yakuprnk.com.models.DuyurularItem;

/**
 * Created by Yakup on 14.04.2017.
 */

public class DuyuruAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<DuyurularItem> duyuruModelList;
    public TextView duyuru,duyuru_tarih;
    public ImageView info;
    public DateHelper dateHelper;
    ListView mList;
    View view;
    public DuyuruAdapter(Activity activity, List<DuyurularItem> duyurular, ListView list) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        duyuruModelList = duyurular;
        mList=list;


    }

    @Override
    public int getCount() {
        return duyuruModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return duyuruModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        dateHelper=new DateHelper();
        view = mInflater.inflate(R.layout.duyurular, null);
        duyuru = (TextView) view.findViewById(R.id.duyuru);
        duyuru_tarih = (TextView) view.findViewById(R.id.duyuru_tarihi);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DuyuruAlert alert=new DuyuruAlert();
                alert.showDialog(view.getContext(),duyuruModelList.get(position).getContent(),
                        duyuruModelList.get(position).getLink());
            }
        });

        DuyurularItem duyurularItem=duyuruModelList.get(position);

        duyuru.setText(duyurularItem.getContent().substring(0,duyurularItem.getContent().length()-12));
        duyuru_tarih.setText(dateHelper.date_to_just_date(duyurularItem.getDuyuruDate()));



    return view;
    }


}
