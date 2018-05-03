package iliada.dovizapp;

/**
 * Created by ilyada on 18.11.2017.
 */

public class Islemler {


    String islem_turu;
    String para_turu;
    String islem_tarihi;
    Double miktar;
    Double maliyet; //ALIM İSLEMİ İCİN..
    Double tl_degeri; //SATIM İŞLEMİ İCİN..

    public String getIslem_turu() {
        return islem_turu;
    }

    public void setIslem_turu(String islem_turu) {
        this.islem_turu = islem_turu;
    }

    public String getpara_turu() {
        return para_turu;
    }

    public void setpara_turu(String para_turu) {
        this.para_turu = para_turu;
    }

    public String getIslem_tarihi() {
        return islem_tarihi;
    }

    public void setIslem_tarihi(String islem_tarihi) {
        this.islem_tarihi = islem_tarihi;
    }

    public Double getMiktar() {
        return miktar;
    }

    public void setMiktar(Double miktar) {
        this.miktar = miktar;
    }

    public Double getMaliyet() {
        return maliyet;
    }

    public void setMaliyet(Double maliyet) {
        this.maliyet = maliyet;
    }

    public Double getTl_degeri() {
        return tl_degeri;
    }

    public void setTl_degeri(Double tl_degeri) {
        this.tl_degeri = tl_degeri;
    }
}
