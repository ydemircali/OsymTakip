package osymtakip.yakuprnk.com.models;

import java.io.Serializable;

/**
 * Created by Yakup on 08.04.2017.
 */

public class FavoriteDetay implements Serializable {

    public int   id;
    public String sinav_adi;
    public String sinav_adi_detay;
    public String sinav_tarihi;
    public String basvuru_tarihi;
    public String sonuc_tarihi;



    public FavoriteDetay()
    {
        super();
    }

    public FavoriteDetay(String sinav_adi,String sinav_adi_detay, String sinav_tarihi, String basvuru_tarihi, String sonuc_tarihi) {
        super();
        this.sinav_adi = sinav_adi;
        this.sinav_adi_detay=sinav_adi_detay;
        this.sinav_tarihi = sinav_tarihi;
        this.basvuru_tarihi = basvuru_tarihi;
        this.sonuc_tarihi = sonuc_tarihi;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSinav_adi() {
        return sinav_adi;
    }
    public void setSinav_adi(String sinav_adi) {
        this.sinav_adi = sinav_adi;
    }

    public String getSinav_adi_detay() {return sinav_adi_detay;}
    public void setSinav_adi_detay(String sinav_adi_detay) {this.sinav_adi_detay = sinav_adi_detay;}

    public String getSinav_tarihi() {
        return sinav_tarihi;
    }

    public void setSinav_tarihi(String sinav_tarihi) {
        this.sinav_tarihi = sinav_tarihi;
    }

    public String getBasvuru_tarihi() {
        return basvuru_tarihi;
    }

    public void setBasvuru_tarihi(String basvuru_tarihi) {
        this.basvuru_tarihi = basvuru_tarihi;
    }

    public String getSonuc_tarihi() {
        return sonuc_tarihi;
    }

    public void setSonuc_tarihi(String sonuc_tarihi) {
        this.sonuc_tarihi = sonuc_tarihi;
    }


}
