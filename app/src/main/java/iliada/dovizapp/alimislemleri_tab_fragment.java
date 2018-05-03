package iliada.dovizapp;

/**
 * Created by ilyada on 19.11.2017.
 */


import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class alimislemleri_tab_fragment extends Fragment {


    LinearLayout linearlayout_genel,linearlayout_islem,linearLayout_bilgi1,linearLayout_bilgi2,linearLayout_bilgi3,linearLayout_bilgi4,linearLayout_bilgi5;
    TextView tv_bilgi_islemturu_txt, tv_bilgi_paraturu_txt, tv_bilgi_miktar_txt, tv_bilgi_maliyet_getiri_txt, tv_bilgi_tarih_txt;
    TextView tv_bilgi_islemturu, tv_bilgi_paraturu, tv_bilgi_miktar, tv_bilgi_maliyet_getiri, tv_bilgi_tarih;
    Boolean islem_yok=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_alimislemleri_hesap_ozeti, container, false);
        return rootView;
    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearlayout_genel = (LinearLayout) view.findViewById(R.id.ll_genel);

            for(int i = 0; i< Alim_Islemi_Fragment.alim_islemi.size(); i++){

                if(Alim_Islemi_Fragment.alim_islemi.get(i).getMiktar()>0 && Alim_Islemi_Fragment.alim_islemi.get(i).getMiktar() !=null) {
                    yeni_islem_olustur(i);
                    islem_yok=false;
                }
            }
        if(islem_yok){
            TextView tv_islemyok=(TextView)view.findViewById(R.id.txt_islemyok);
            tv_islemyok.setVisibility(View.VISIBLE);

        }


    }

    protected void yeni_islem_olustur(int i){
        yeni_islem_init();

        LinearLayout.LayoutParams layoutParams_islem=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1);
        linearlayout_islem.setOrientation(LinearLayout.VERTICAL);
        layoutParams_islem.setMargins(0,0,0,5);
        linearlayout_islem.setBackground(getResources().getDrawable(R.drawable.border)); //İŞLEM TÜRÜNE GÖRE DEĞİŞMELİ !!!!
        linearlayout_islem.setLayoutParams(layoutParams_islem);

        LinearLayout.LayoutParams layoutParams_bilgi=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams_bilgi.setMargins(0,0,0,5);
        linearLayout_bilgi1.setLayoutParams(layoutParams_bilgi);
        linearLayout_bilgi2.setLayoutParams(layoutParams_bilgi);
        linearLayout_bilgi3.setLayoutParams(layoutParams_bilgi);
        linearLayout_bilgi4.setLayoutParams(layoutParams_bilgi);
        linearLayout_bilgi5.setLayoutParams(layoutParams_bilgi);


        SpannableString content2 = new SpannableString("İşlem Türü: ");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        tv_bilgi_islemturu_txt.setText(content2);

        if(Alim_Islemi_Fragment.alim_islemi.get(i).getIslem_turu().equalsIgnoreCase("alis"))
        {
            SpannableString content = new SpannableString("Alım İşlemi");
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            tv_bilgi_islemturu.setText(content);
            tv_bilgi_maliyet_getiri_txt.setText("Maliyet: ");
            tv_bilgi_maliyet_getiri.setText(String.valueOf(Alim_Islemi_Fragment.alim_islemi.get(i).getMaliyet()));
        }
        else {
            SpannableString content = new SpannableString("Satım İşlemi");
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            tv_bilgi_islemturu.setText(content);
            tv_bilgi_maliyet_getiri_txt.setText("TL Değeri: ");
            tv_bilgi_maliyet_getiri.setText(String.valueOf(Alim_Islemi_Fragment.alim_islemi.get(i).getTl_degeri()));
        }

        tv_bilgi_paraturu_txt.setText("Para Türü: ");
        tv_bilgi_paraturu.setText(Alim_Islemi_Fragment.alim_islemi.get(i).getpara_turu());


        tv_bilgi_miktar_txt.setText("Miktarı: ");
        tv_bilgi_miktar.setText(String.valueOf(Alim_Islemi_Fragment.alim_islemi.get(i).getMiktar()));



        tv_bilgi_tarih_txt.setText("İşlem Tarihi: ");
        tv_bilgi_tarih.setText(Alim_Islemi_Fragment.alim_islemi.get(i).getIslem_tarihi());

        tv_bilgi_tarih_txt.setTextSize(15f);
        tv_bilgi_tarih.setTextSize(15f);
        tv_bilgi_miktar_txt.setTextSize(15f);
        tv_bilgi_miktar.setTextSize(15f);
        tv_bilgi_paraturu_txt.setTextSize(15f);
        tv_bilgi_paraturu.setTextSize(15f);
        tv_bilgi_maliyet_getiri_txt.setTextSize(15f);
        tv_bilgi_maliyet_getiri.setTextSize(15f);
        tv_bilgi_islemturu.setTextSize(20f);
        tv_bilgi_islemturu_txt.setTextSize(20f);

        tv_bilgi_tarih_txt.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bilgi_tarih_txt.setTextColor(Color.BLACK);
        tv_bilgi_miktar_txt.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bilgi_miktar_txt.setTextColor(Color.BLACK);
        tv_bilgi_paraturu_txt.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bilgi_paraturu_txt.setTextColor(Color.BLACK);
        tv_bilgi_maliyet_getiri_txt.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bilgi_maliyet_getiri_txt.setTextColor(Color.BLACK);
        tv_bilgi_islemturu_txt.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bilgi_islemturu_txt.setTextColor(Color.BLACK);

        tv_bilgi_tarih.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bilgi_tarih.setTextColor(Color.BLACK);
        tv_bilgi_miktar.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bilgi_miktar.setTextColor(Color.BLACK);
        tv_bilgi_paraturu.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bilgi_paraturu.setTextColor(Color.BLACK);
        tv_bilgi_maliyet_getiri.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bilgi_maliyet_getiri.setTextColor(Color.BLACK);
        tv_bilgi_islemturu.setTypeface(Typeface.DEFAULT_BOLD);
        tv_bilgi_islemturu.setTextColor(Color.BLACK);

        linearLayout_bilgi1.addView(tv_bilgi_islemturu_txt);
        linearLayout_bilgi1.addView(tv_bilgi_islemturu);

        linearLayout_bilgi2.addView(tv_bilgi_paraturu_txt);
        linearLayout_bilgi2.addView(tv_bilgi_paraturu);

        linearLayout_bilgi3.addView(tv_bilgi_miktar_txt);
        linearLayout_bilgi3.addView(tv_bilgi_miktar);

        linearLayout_bilgi4.addView(tv_bilgi_maliyet_getiri_txt);
        linearLayout_bilgi4.addView(tv_bilgi_maliyet_getiri);

        linearLayout_bilgi5.addView(tv_bilgi_tarih_txt);
        linearLayout_bilgi5.addView(tv_bilgi_tarih);

        linearlayout_islem.addView(linearLayout_bilgi1);
        linearlayout_islem.addView(linearLayout_bilgi2);
        linearlayout_islem.addView(linearLayout_bilgi3);
        linearlayout_islem.addView(linearLayout_bilgi4);
        linearlayout_islem.addView(linearLayout_bilgi5);

        linearlayout_genel.addView(linearlayout_islem);



    }




    protected void yeni_islem_init(){

        tv_bilgi_islemturu_txt=new TextView(getActivity());
        tv_bilgi_paraturu_txt=new TextView(getActivity());
        tv_bilgi_miktar_txt=new TextView(getActivity());
        tv_bilgi_maliyet_getiri_txt=new TextView(getActivity());
        tv_bilgi_tarih_txt=new TextView(getActivity());
        tv_bilgi_islemturu=new TextView(getActivity());
        tv_bilgi_paraturu=new TextView(getActivity());
        tv_bilgi_miktar=new TextView(getActivity());
        tv_bilgi_maliyet_getiri=new TextView(getActivity());
        tv_bilgi_tarih=new TextView(getActivity());


        linearlayout_islem=new LinearLayout(getActivity());
        linearLayout_bilgi1=new LinearLayout(getActivity());
        linearLayout_bilgi2=new LinearLayout(getActivity());
        linearLayout_bilgi3=new LinearLayout(getActivity());
        linearLayout_bilgi4=new LinearLayout(getActivity());
        linearLayout_bilgi5=new LinearLayout(getActivity());




    }

}



