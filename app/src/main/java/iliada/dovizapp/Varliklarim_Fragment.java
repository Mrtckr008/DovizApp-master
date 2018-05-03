package iliada.dovizapp;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Varliklarim_Fragment extends Fragment {

    // public static Varliklar[] varliklarim = new Varliklar[65];
    // public static int varliklarim_index=1;

    public static Varliklar TL =new Varliklar();
    public static ArrayList<Varliklar> varliklarim = new ArrayList<>();
    ScrollView sv;
    TableLayout tableLayout;
    TableRow row;
    ImageButton imagebutton_tur_karzarar;
    ImageView imageview_karzarar;
    TextView tv_varlik_yok,tv_tur,tv_miktar_txt,tv_miktar,tv_maliyet_txt,tv_maliyet,
            tv_satis_txt,tv_satis,tv_karzarar_txt,tv_karzarar,tv_toplam_bakiye,tv_toplam_bakiye_txt,tv_mevcut_tl_miktari;
    LinearLayout ll_tur,ll_miktar,ll_maliyet,
            ll_satis,ll_karzarar,ll_bilgiler;
    Double toplamdeger;
    Boolean zarar, varlikyok=true;


    public Varliklarim_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_varliklarim, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // sv=(ScrollView)view.findViewById(R.id.sc);
        tableLayout=(TableLayout) view.findViewById(R.id.tl);
        tv_mevcut_tl_miktari=(TextView)view.findViewById(R.id.tv_mevcut_tl_miktari);

            Guncel_Kurlar_Fragment.GUNCELLE=true;
            tv_mevcut_tl_miktari.setText(String.valueOf(TL.getDeger()+"0000000").substring(0, 7)+" ₺");
        varlik_guncelle();
        if(TL.getDeger()>0 )
        toplamdeger=TL.getDeger();
        else
            toplamdeger=0.0;
        for(int i=0;i<Varliklarim_Fragment.varliklarim.size();i++)
        {
            Log.d("Varliklarim Size:", " "+Varliklarim_Fragment.varliklarim.size());
            if((Varliklarim_Fragment.varliklarim.get(i).getMiktar()>0&&Varliklarim_Fragment.varliklarim.get(i).getTur().length() > 0 && Varliklarim_Fragment.varliklarim.get(i).getTur() != null)) {
                yeni_satir_olustur(varliklarim.get(i));
                toplamdeger = varliklarim.get(i).getDeger() + toplamdeger;
                varlikyok=false;
            }
        }
        if(varlikyok){
            TableRow varlik_yok_row =(TableRow)view.findViewById(R.id.varlik_yok_row);
            varlik_yok_row.setVisibility(View.VISIBLE);
        }
        else {
            TableRow varlik_yok_row =(TableRow)view.findViewById(R.id.varlik_yok_row);
            varlik_yok_row.setVisibility(View.GONE);
        }

        son_satir_olustur();
    }

    protected void yeni_satir_olustur(Varliklar varlik){

        yeni_satir_init();
        yeni_satir_set_txt(varlik);

        TableLayout.LayoutParams row_params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, 0);
        row.setBackgroundColor(Color.WHITE);
        row.setPadding(10,10,10,10);
        row_params.setMargins(0,5,0,0);
        row.setLayoutParams(row_params);


        TableRow.LayoutParams ll_tur_params = new TableRow.LayoutParams(0,TableRow.LayoutParams.MATCH_PARENT,0.8f);
        ll_tur.setWeightSum(2f);
        ll_tur.setOrientation(LinearLayout.VERTICAL);
        ll_tur.setLayoutParams(ll_tur_params);
        ll_tur.setGravity(Gravity.CENTER);
        //
        //     <LinearLayout 
        //  android:weightSum="2" 
        //    android:layout_weight="1" 
        //     android:orientation="vertical" 
        //   android:layout_width="0dp" 
        //    android:layout_height="match_parent" 
        // >
        LinearLayout.LayoutParams tv_tur_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1f);
        tv_tur.setTextSize(17);
        tv_tur.setGravity(Gravity.CENTER);
        tv_tur.setTypeface(Typeface.DEFAULT_BOLD);
        tv_tur.setTextColor(Color.BLACK);
        tv_tur.setLayoutParams(tv_tur_params);

        LinearLayout.LayoutParams imagebutton_tur_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,0,0.6f);
       // imagebutton_tur_karzarar.setBackgroundResource(R.drawable.ic_action_name); //DEGİSİCEK..
       //   imagebutton_tur_karzarar.setBackgroundColor(Color.TRANSPARENT);

        imagebutton_tur_karzarar.setLayoutParams(imagebutton_tur_params);




        TableRow.LayoutParams ll_bilgiler_params = new TableRow.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,2f);
        ll_bilgiler.setOrientation(LinearLayout.VERTICAL);
        ll_bilgiler.setLayoutParams(ll_bilgiler_params);

        LinearLayout.LayoutParams ll_bilgiler_txt_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1f);
        ll_satis.setOrientation(LinearLayout.HORIZONTAL);
        ll_maliyet.setOrientation(LinearLayout.HORIZONTAL);
        ll_miktar.setOrientation(LinearLayout.HORIZONTAL);
        ll_satis.setLayoutParams(ll_bilgiler_txt_params);
        ll_miktar.setLayoutParams(ll_bilgiler_txt_params);
        ll_maliyet.setLayoutParams(ll_bilgiler_txt_params);

        LinearLayout.LayoutParams tv_bilgiler_txt_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        tv_miktar_txt.setTextSize(17);
        tv_miktar_txt.setTypeface(Typeface.DEFAULT_BOLD);
        tv_miktar_txt.setTextColor(Color.BLACK);
        tv_miktar_txt.setLayoutParams(tv_bilgiler_txt_params);

        tv_satis_txt.setTextSize(17);
        tv_satis_txt.setTypeface(Typeface.DEFAULT_BOLD);
        tv_satis_txt.setTextColor(Color.BLACK);
        tv_satis_txt.setLayoutParams(tv_bilgiler_txt_params);

        tv_maliyet_txt.setTextSize(17);
        tv_maliyet_txt.setTypeface(Typeface.DEFAULT_BOLD);
        tv_maliyet_txt.setTextColor(Color.BLACK);
        tv_maliyet_txt.setLayoutParams(tv_bilgiler_txt_params);

        tv_karzarar_txt.setTextSize(17);
        tv_karzarar_txt.setTypeface(Typeface.DEFAULT_BOLD);
        tv_karzarar_txt.setTextColor(Color.BLACK);
        tv_karzarar_txt.setLayoutParams(tv_bilgiler_txt_params);

        LinearLayout.LayoutParams tv_bilgiler_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        tv_miktar.setTextSize(17);
        tv_miktar.setTypeface(Typeface.DEFAULT_BOLD);
        tv_miktar.setTextColor(Color.BLACK);
        tv_miktar.setLayoutParams(tv_bilgiler_params);

        tv_satis.setTextSize(17);
        tv_satis.setTypeface(Typeface.DEFAULT_BOLD);
        tv_satis.setTextColor(Color.BLACK);
        tv_satis.setLayoutParams(tv_bilgiler_params);


        tv_maliyet.setTextSize(17);
        tv_maliyet.setTypeface(Typeface.DEFAULT_BOLD);
        tv_maliyet.setTextColor(Color.BLACK);
        tv_maliyet.setLayoutParams(tv_bilgiler_params);


        LinearLayout.LayoutParams tv_bilgiler_karzarar_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1f);
        ll_karzarar.setOrientation(LinearLayout.HORIZONTAL);
        ll_karzarar.setLayoutParams(tv_bilgiler_karzarar_params);

        LinearLayout.LayoutParams tv_karzarar_params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,2f);
        tv_karzarar.setTextSize(17);
        tv_karzarar.setTypeface(Typeface.DEFAULT_BOLD);
        tv_karzarar.setTextColor(Color.BLACK);
        tv_karzarar.setLayoutParams(tv_karzarar_params);

        LinearLayout.LayoutParams imageview_karzarar_params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1f);
        if(zarar)
            imageview_karzarar.setBackgroundResource(R.drawable.icon_signal_down);
        else
            imageview_karzarar.setBackgroundResource(R.drawable.icon_signal_up);

        imageview_karzarar.setLayoutParams(imageview_karzarar_params);



        ll_satis.addView(tv_satis_txt);
        ll_satis.addView(tv_satis);

        ll_miktar.addView(tv_miktar_txt);
        ll_miktar.addView(tv_miktar);

        ll_maliyet.addView(tv_maliyet_txt,0);
        ll_maliyet.addView(tv_maliyet,1);

        ll_karzarar.addView(tv_karzarar_txt);
        ll_karzarar.addView(tv_karzarar);
       ll_karzarar.addView(imageview_karzarar);

        ll_bilgiler.addView(ll_miktar);
        ll_bilgiler.addView(ll_maliyet);
        ll_bilgiler.addView(ll_satis);
        ll_bilgiler.addView(ll_karzarar);

        ll_tur.addView(tv_tur);
        //ll_tur.addView(imagebutton_tur_karzarar);

        row.addView(ll_tur);
        row.addView(ll_bilgiler);
        tableLayout.addView(row);
//        sv.addView(tableLayout);

    }

    protected void yeni_satir_init(){

        row=new TableRow(getActivity());
        tv_tur=new TextView(getActivity());
        tv_miktar_txt=new TextView(getActivity());
        tv_miktar=new TextView(getActivity());
        tv_maliyet_txt=new TextView(getActivity());
        tv_maliyet=new TextView(getActivity());
        tv_satis_txt=new TextView(getActivity());
        tv_satis=new TextView(getActivity());
        tv_karzarar_txt=new TextView(getActivity());
        tv_karzarar=new TextView(getActivity());
        ll_bilgiler=new LinearLayout(getActivity());
        ll_tur=new LinearLayout(getActivity());
        ll_karzarar=new LinearLayout(getActivity());
        ll_maliyet=new LinearLayout(getActivity());
        ll_miktar=new LinearLayout(getActivity());
        ll_satis=new LinearLayout(getActivity());
        imagebutton_tur_karzarar=new ImageButton(getActivity());
        imageview_karzarar=new ImageView(getActivity());

    }
    protected void yeni_satir_set_txt(Varliklar varlik){
        tv_tur.setText(varlik.getTur());
        tv_miktar.setText(String.valueOf(varlik.getMiktar())+" Adet");
        tv_maliyet.setText(String.valueOf(varlik.getMaliyet()+"000000").substring(0,6)+" ₺");

      //  tv_karzarar.setText(String .valueOf((varlik.getMiktar()*varlik.getGuncel_alim_kuru())-varlik.getMaliyet())+" ₺");
        tv_karzarar.setText(String.valueOf(varlik.getDeger()-varlik.getMaliyet()+"00000000").substring(0, 8)+"₺");
        tv_satis.setText(String.valueOf(varlik.getDeger()+"00000000").substring(0, 8)+" ₺"); // BUNA Bİ BAK..

        if((varlik.getDeger()-varlik.getMaliyet())<0 ) zarar=true;
        else zarar=false;

        tv_miktar_txt.setText("Miktar: ");
        tv_maliyet_txt.setText("Maliyet: ");
        tv_satis_txt.setText("TL Değeri: ");
        tv_karzarar_txt.setText("Kar & Zarar Durumu: ");

    }


    protected void son_satir_olustur(){
        row=new TableRow(getActivity());
        tv_toplam_bakiye=new TextView(getActivity());
        tv_toplam_bakiye_txt=new TextView(getActivity());

        TableLayout.LayoutParams row_params_bakiye = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,0);
        row.setBackgroundColor(Color.WHITE);
        row.setPadding(10,10,10,10);
        row_params_bakiye.setMargins(0,5,0,0);
        row.setLayoutParams(row_params_bakiye);

        TableRow.LayoutParams toplam_bakiye_tv_params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

        tv_toplam_bakiye_txt.setTextSize(18);
        tv_toplam_bakiye_txt.setTypeface(Typeface.DEFAULT_BOLD);
        tv_toplam_bakiye_txt.setTextColor(Color.BLACK);
        tv_toplam_bakiye_txt.setLayoutParams(toplam_bakiye_tv_params);
        tv_toplam_bakiye_txt.setText(" Varlıkların Toplam TL Degeri: ");

        TableRow.LayoutParams toplam_bakiye_params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);

        tv_toplam_bakiye.setTextSize(18);
        tv_toplam_bakiye.setTypeface(Typeface.DEFAULT_BOLD);
        tv_toplam_bakiye.setTextColor(Color.BLACK);
        tv_toplam_bakiye.setLayoutParams(toplam_bakiye_params);
        tv_toplam_bakiye.setText(String.valueOf(toplamdeger+ "0000000").substring(0, 7)+"₺");

        row.addView(tv_toplam_bakiye_txt);
        row.addView(tv_toplam_bakiye);
        tableLayout.addView(row);
    }




    public static void varlik_guncelle() {


        for (int i = 0; i < Varliklarim_Fragment.varliklarim.size(); i++) {

            if ((Varliklarim_Fragment.varliklarim.get(i).getMiktar() > 0 && Varliklarim_Fragment.varliklarim.get(i).getTur().length() > 0 && Varliklarim_Fragment.varliklarim.get(i).getTur() != null)) {

                for (int k = 0; k < 20; k++) {

                    if (Guncel_Kurlar_Fragment.para[k].getTur().equalsIgnoreCase(Varliklarim_Fragment.varliklarim.get(i).getTur())) {

                        Varliklarim_Fragment.varliklarim.get(i).setGuncel_alim_kuru(Guncel_Kurlar_Fragment.para[k].getGuncel_alim_kuru());
                        Varliklarim_Fragment.varliklarim.get(i).setGuncel_satim_kuru(Guncel_Kurlar_Fragment.para[k].getGuncel_satim_kuru());
                        Varliklarim_Fragment.varliklarim.get(i).setDeger(Varliklarim_Fragment.varliklarim.get(i).getMiktar() * Varliklarim_Fragment.varliklarim.get(i).getGuncel_alim_kuru());
                        Varliklarim_Fragment.varliklarim.get(i).setGuncel_degisim_orani(Guncel_Kurlar_Fragment.para[k].getGuncel_degisim_orani());
                        System.out.println(""+Varliklarim_Fragment.varliklarim.get(i).getTur());
                    }

                }
                System.out.println("varlik güncelle");


            }
        }
    }



}
