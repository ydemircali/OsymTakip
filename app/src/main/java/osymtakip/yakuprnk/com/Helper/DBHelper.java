package osymtakip.yakuprnk.com.Helper;

/**
 * Created by Yakup on 07.04.2017.
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import osymtakip.yakuprnk.com.models.FavoriteDetay;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME   = "sinavDB";

    private static final String TABLE_FAVORITE = "favorites";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlfavori = "CREATE TABLE " + TABLE_FAVORITE + "(id INTEGER PRIMARY KEY,sinav_adi TEXT,sinav_adi_detay TEXT,sinav_tarihi DATETIME,basvuru_tarihi TEXT,sonuc_tarihi DATETIME" + ")";
        Log.d("DBHelper", "SQL : " + sqlfavori);
        db.execSQL(sqlfavori);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);
        onCreate(db);
    }
    public void insertFavoriler(FavoriteDetay favoriteDetay) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("sinav_adi", favoriteDetay.getSinav_adi());
        values.put("sinav_adi_detay", favoriteDetay.getSinav_adi_detay());
        values.put("sinav_tarihi", favoriteDetay.getSinav_tarihi());
        values.put("basvuru_tarihi",favoriteDetay.getBasvuru_tarihi());
        values.put("sonuc_tarihi",favoriteDetay.getSonuc_tarihi());

        db.insert(TABLE_FAVORITE, null, values);
        db.close();
    }
    public int i=0;

    public List<FavoriteDetay> getAllFavoriler(){
        List<FavoriteDetay> favoriteDetayList = new ArrayList<FavoriteDetay>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from "+TABLE_FAVORITE+"  order by sinav_tarihi asc",null);
        while (cursor.moveToNext()) {
            FavoriteDetay favoriteDetay = new FavoriteDetay();
            favoriteDetay.setId(cursor.getInt(0));
            favoriteDetay.setSinav_adi(cursor.getString(1));
            favoriteDetay.setSinav_adi_detay(cursor.getString(2));
            favoriteDetay.setSinav_tarihi(cursor.getString(3));
            favoriteDetay.setBasvuru_tarihi(cursor.getString(4));
            favoriteDetay.setSonuc_tarihi(cursor.getString(5));
            favoriteDetayList.add(favoriteDetay);

        }

        return favoriteDetayList;
    }

    public void FavoriSil(String sinav_adi,String sinav_adi_detay, String sinav_tarihi, String basvuru_tarihi, String sonuc_tarihi)
    { //id si belli olan row u silmek için

        SQLiteDatabase db = this.getWritableDatabase();
        String [] whereArg={sinav_adi,sinav_adi_detay,sinav_tarihi,basvuru_tarihi,sonuc_tarihi};
        String kosul="sinav_adi==? and sinav_adi_detay==? and sinav_tarihi==? and basvuru_tarihi==? and sonuc_tarihi==?";
        db.delete(TABLE_FAVORITE, kosul, whereArg);
        db.close();
    }

    public String FavoriVarmi(String sinav_adi,String sinav_adi_detay, String sinav_tarihi, String basvuru_tarihi, String sonuc_tarihi)
    {
        String sonuc="yok";
        SQLiteDatabase db = this.getWritableDatabase();

        String [] kolonlar={"sinav_adi","sinav_adi_detay", "sinav_tarihi","basvuru_tarihi","sonuc_tarihi"};
        String [] whereArg={sinav_adi,sinav_adi_detay,sinav_tarihi,basvuru_tarihi,sonuc_tarihi};
        String kosul="sinav_adi==? and sinav_adi_detay==? and sinav_tarihi==? and basvuru_tarihi==? and sonuc_tarihi==?";
        Cursor cursor = db.query(TABLE_FAVORITE, kolonlar,kosul , whereArg, null, null, null);
        if(cursor.getCount()>0)
        {
            sonuc="var";
        }


        return sonuc;
    }


}
