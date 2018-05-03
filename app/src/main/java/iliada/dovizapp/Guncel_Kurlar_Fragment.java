package iliada.dovizapp;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import android.os.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Guncel_Kurlar_Fragment extends Fragment {
    public static boolean GUNCELLE;
    public static Para[] para = new Para[66];
    TextView tv_name, tv_purchase_price, tv_selling_price, tv_rate;
    TableLayout tableLayout;
    ScrollView scroll;
    RelativeLayout content;
    TableRow row;
    LinearLayout linearLayout;
    ImageView iv_rate;

    public Guncel_Kurlar_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_guncel__kurlar, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Güncel Kur Değerleri");
        content = (RelativeLayout) view.findViewById(R.id.fragment_guncel_kurlar_mainlayout);

        scroll = new ScrollView(getActivity());

        scroll.setBackgroundColor(Color.parseColor("#F5F5F5"));
        scroll.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT));
        GUNCELLE=true;
//
//        if (!isInternetAvailable()) {
//
//            show_dialoginternet().show();
//
//
//        }
        if(!isOnline()){
            show_dialoginternet().show();
        }

            GetData();
            varlik_guncelle();


        final Handler handler = new Handler();
        handler.postDelayed( new Runnable() {

            @Override
            public void run() {

                    if(Guncel_Kurlar_Fragment.this.isVisible()){
                    scroll.removeAllViews();
                    content.removeAllViews();
                    GetData();
                    scroll.addView(tableLayout);
                    content.addView(scroll);
                    System.out.println("this");


                }


                else if(GUNCELLE){
                    for (int i = 0; i < 20; i++)
                        guncelle(i);

                        System.out.println("else");
                        varlik_guncelle();


                    }
                    else
                        System.out.println("GUNCELLEME");


            }
        }, 25000 );


        scroll.addView(tableLayout);
        content.addView(scroll);
    }



    public void guncelle(final int i) {

        ApiService.Factory.getInstance().getdoviz().enqueue(new Callback<List<ExampleDoviz>>() {
            @Override
            public void onResponse(Call<List<ExampleDoviz>> call, Response<List<ExampleDoviz>> response) {

                List<ExampleDoviz> jsondoviz = response.body();


                Guncel_Kurlar_Fragment.para[i] = new Para();
//                Guncel_Kurlar_Fragment.para[i].setId(i);
                Guncel_Kurlar_Fragment.para[i].setTur(String.valueOf(jsondoviz.get(i).getFullName()));
                Guncel_Kurlar_Fragment.para[i].setGuncel_alim_kuru(Double.valueOf(String.valueOf(jsondoviz.get(i).getBuying() + "000000").substring(0, 6)));
                Guncel_Kurlar_Fragment.para[i].setGuncel_satim_kuru(Double.valueOf(String.valueOf(jsondoviz.get(i).getSelling() + "000000").substring(0, 6)));
                Guncel_Kurlar_Fragment.para[i].setGuncel_degisim_orani(Double.valueOf(String.valueOf(jsondoviz.get(i).getChangeRate() + "00000").substring(0, 5)));

            }

            @Override
            public void onFailure(Call<List<ExampleDoviz>> call, Throwable t) {

            }
        });


    }
    protected void initforloop() {

        //SET ROW
        row = new TableRow(getContext());
        row.setBackgroundColor(Color.parseColor("#FAFAFA"));
        row.setPadding(0, 5, 0, 5);
        row.setGravity(Gravity.CENTER);
        TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        row.setWeightSum(5f);
        tableRowParams.setMargins(0,3,0,3);
        row.setLayoutParams(tableRowParams);

        //İNİTİAL VIEWS
        tv_name = new TextView(getActivity());
        tv_purchase_price = new TextView(getActivity());
        tv_selling_price = new TextView(getActivity());
        tv_rate = new TextView(getActivity());
        linearLayout = new LinearLayout(getActivity());
        iv_rate = new ImageView(getActivity());

        //params for set-weight of TEXTVIEWS
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
        TableRow.LayoutParams paramsname = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 2f);
        tv_name.setLayoutParams(paramsname);
        tv_purchase_price.setLayoutParams(params);
        tv_selling_price.setLayoutParams(params);

        //TEXT SETTINGS (FONT-TEXTSIZE)
        tv_name.setTypeface(tv_name.getTypeface(), Typeface.BOLD);
        tv_purchase_price.setTypeface(tv_purchase_price.getTypeface(), Typeface.BOLD);
        tv_selling_price.setTypeface(tv_selling_price.getTypeface(), Typeface.BOLD);
        tv_rate.setTypeface(tv_rate.getTypeface(), Typeface.BOLD);
        tv_name.setTextSize(13f);
        tv_purchase_price.setTextSize(12f);
        tv_selling_price.setTextSize(12f);
        tv_rate.setTextSize(12f);
        tv_name.setTypeface(Typeface.DEFAULT_BOLD);
        tv_name.setTextColor(Color.BLACK);
        tv_purchase_price.setTypeface(Typeface.DEFAULT_BOLD);
        tv_purchase_price.setTextColor(Color.BLACK);
        tv_selling_price.setTypeface(Typeface.DEFAULT_BOLD);
        tv_selling_price.setTextColor(Color.BLACK);
        tv_rate.setTypeface(Typeface.DEFAULT_BOLD);
        tv_rate.setTextColor(Color.BLACK);




        //LINEARLAYOUT SETTINGS FOR RATE
        linearLayout.setWeightSum(1);
        TableRow.LayoutParams paramsrate = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.7f);
        TableRow.LayoutParams paramsimage = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.3f);
        iv_rate.setLayoutParams(paramsimage);
        iv_rate.setBackgroundResource(android.R.color.transparent);
        tv_rate.setLayoutParams(paramsrate);
        linearLayout.setLayoutParams(params);
        linearLayout.addView(tv_rate);
        linearLayout.addView(iv_rate);
    }

    protected void initrowforheader() {

        //INIT AND SETTING ROW
        row = new TableRow(getActivity());
        row.setBackgroundColor(Color.parseColor("#FAFAFA"));
        row.setPadding(0, 5, 0, 5);
        TableLayout.LayoutParams tableRowParams2 = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        row.setWeightSum(5f);
        tableRowParams2.setMargins(0, 5, 0, 0);
        row.setLayoutParams(tableRowParams2);

        //INIT TEXTVIEWS
        tv_name = new TextView(getActivity());
        tv_purchase_price = new TextView(getActivity());
        tv_selling_price = new TextView(getActivity());
        tv_rate = new TextView(getActivity());

        //SETTING TEXTS
        tv_name.setText("Para \nBirimi");
        tv_purchase_price.setText("Alış \nFiyatı");
        tv_selling_price.setText("Satış \nFiyatı");
        tv_rate.setText("Değişim \nOranı");
        tv_name.setTypeface(Typeface.DEFAULT_BOLD);
        tv_name.setTextColor(Color.BLACK);
        tv_purchase_price.setTypeface(Typeface.DEFAULT_BOLD);
        tv_purchase_price.setTextColor(Color.BLACK);
        tv_selling_price.setTypeface(Typeface.DEFAULT_BOLD);
        tv_selling_price.setTextColor(Color.BLACK);
        tv_rate.setTypeface(Typeface.DEFAULT_BOLD);
        tv_rate.setTextColor(Color.BLACK);

        //TEXTVIEW SETTINGS (FONT-TEXTSIZE)
        tv_name.setTypeface(tv_name.getTypeface(), Typeface.BOLD);
        tv_purchase_price.setTypeface(tv_purchase_price.getTypeface(), Typeface.BOLD);
        tv_selling_price.setTypeface(tv_selling_price.getTypeface(), Typeface.BOLD);
        tv_rate.setTypeface(tv_rate.getTypeface(), Typeface.BOLD);
        tv_name.setTextSize(16f);
        tv_purchase_price.setTextSize(16f);
        tv_selling_price.setTextSize(16f);
        tv_rate.setTextSize(16f);

        //params for set-weight of TEXTVIEWS
        TableRow.LayoutParams paramsHeader = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f);
        TableRow.LayoutParams paramsnameHeader = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 2f);
        tv_name.setLayoutParams(paramsnameHeader);
        tv_purchase_price.setLayoutParams(paramsHeader);
        tv_rate.setLayoutParams(paramsHeader);
        tv_selling_price.setLayoutParams(paramsHeader);

    }

    protected void GetData() {

        tableLayout = new TableLayout(getActivity());


        initrowforheader();

        row.addView(tv_name);
        row.addView(tv_purchase_price);
        row.addView(tv_selling_price);
        row.addView(tv_rate);

        tableLayout.addView(row);

        for (int i = 0; i < 20; i++) {

            initforloop();

            dovizgetir(i, tv_name, tv_purchase_price, tv_selling_price, tv_rate, iv_rate);
            row.addView(tv_name);
            row.addView(tv_purchase_price);
            row.addView(tv_selling_price);
            row.addView(linearLayout);



            tableLayout.addView(row);
        }


    }


    public void dovizgetir(final int i, final TextView tv_name, final TextView tv_selling_price, final TextView tv_purchase_price, final TextView tv_rate, final ImageView iv_rate) {

        ApiService.Factory.getInstance().getdoviz().enqueue(new Callback<List<ExampleDoviz>>() {
            @Override
            public void onResponse(Call<List<ExampleDoviz>> call, Response<List<ExampleDoviz>> response) {

                List<ExampleDoviz> jsondoviz = response.body();


                Guncel_Kurlar_Fragment.para[i] = new Para();
//                Guncel_Kurlar_Fragment.para[i].setId(i);
                Guncel_Kurlar_Fragment.para[i].setTur(String.valueOf(jsondoviz.get(i).getFullName()));
                Guncel_Kurlar_Fragment.para[i].setGuncel_alim_kuru(Double.valueOf(String.valueOf(jsondoviz.get(i).getBuying() + "000000").substring(0, 6)));
                Guncel_Kurlar_Fragment.para[i].setGuncel_satim_kuru(Double.valueOf(String.valueOf(jsondoviz.get(i).getSelling() + "000000").substring(0, 6)));
                Guncel_Kurlar_Fragment.para[i].setGuncel_degisim_orani(Double.valueOf(String.valueOf(jsondoviz.get(i).getChangeRate() + "00000").substring(0, 5)));


                tv_name.setText(String.valueOf(para[i].getTur()));
                tv_selling_price.setText(String.valueOf(para[i].getGuncel_alim_kuru()) + "₺ \n ");
                tv_purchase_price.setText(String.valueOf(para[i].getGuncel_satim_kuru()) + "₺ \n ");
                tv_rate.setText(String.valueOf(para[i].getGuncel_degisim_orani()) + "%");

                if (para[i].getGuncel_degisim_orani() < 0)
                    iv_rate.setImageResource(R.drawable.icon_value_down);

                else if (para[i].getGuncel_degisim_orani() > 0)
                    iv_rate.setImageResource(R.drawable.icon_value_up);

                else
                    iv_rate.setImageResource(R.drawable.icon_action_minus);


            }
            @Override
            public void onFailure(Call<List<ExampleDoviz>> call, Throwable t) {

            }
        });


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
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public Dialog show_dialoginternet(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("İnternet Bağlantınızı Kontrol Edin Ve Tekrar Deneyin !")
                .setPositiveButton("Tekrar Dene", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();
                    }

                });

        return builder.create();
    }
    }


