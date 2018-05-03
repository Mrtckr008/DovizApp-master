package iliada.dovizapp;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Satim_Islemi_Fragment extends Fragment {

    public static ArrayList<Islemler> butun_islemler = new ArrayList<>();
    public static ArrayList<Islemler> satim_islemi = new ArrayList<>();
    public static Varliklar para_satilan =new Varliklar();
    EditText tv_birimkur,tv_gelir,tv_miktar;
    Spinner spinner;
    Button btn_sat;
    Double tlkazanci=0.0;
    Double miktar=0.0;
    ArrayAdapter<String> adapter;

    FirebaseDatabase db;

    public Satim_Islemi_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_satim__islemi, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Satış Ekle");
        init(view);



        // Spinneri Dolduruyorum..

        int itemindex=0;

        //count bul
        int count=0;
        for(int i=0;i<Varliklarim_Fragment.varliklarim.size();i++) {
            if (Varliklarim_Fragment.varliklarim.get(i).getTur().length() > 0 && Varliklarim_Fragment.varliklarim.get(i).getTur() != null) {
                if (Varliklarim_Fragment.varliklarim.get(i).getMiktar() != 0.0) {
                    count++;
                }

            }
        }
        String[] items = new String[count];

        for (int i = 0; i <Varliklarim_Fragment.varliklarim.size() ; i++) {

            if (Varliklarim_Fragment.varliklarim.get(i).getTur().length() > 0 && Varliklarim_Fragment.varliklarim.get(i).getTur() != null) {
                if (Varliklarim_Fragment.varliklarim.get(i).getMiktar() != 0.0) {
                    items[itemindex] = Varliklarim_Fragment.varliklarim.get(i).getTur();
                    itemindex++;
                } else{

                   continue;
                }

            }
        }



//            if ((  Varliklarim_Fragment.varliklarim.get(i).getMiktar()>0.0 && Varliklarim_Fragment.varliklarim.get(i).getTur().toString().length() > 0 && Varliklarim_Fragment.varliklarim.get(i).getTur().toString() != null))
//                items[i] = Varliklarim_Fragment.varliklarim.get(i).getTur();
//            Log.i("items size:"," "+items.length);
//            Log.i("miktar:"," "+Varliklarim_Fragment.varliklarim.get(i).getMiktar());


        if (Varliklarim_Fragment.varliklarim.size() > 0 ) {
            adapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
            spinner.setAdapter(adapter);

        } else {
            Snackbar.make(getView(), "Satacak Varlığınız Bulunmamaktadır, Satış Yapmak İçin Döviz Alımı Yapınız... ", 2500)
                    .setAction("Action", null).show();
        }


        //Spinnerdan secilen iteme göre yapılan işlemler..
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                Guncel_Kurlar_Fragment.GUNCELLE=false;

                for (int i = 0; i < Varliklarim_Fragment.varliklarim.size(); i++) {

                    if(!parentView.getItemAtPosition(position).toString().isEmpty()){
                        //secilen degerin hangi para nesnesi olduğunu buluyoruz..
                        if (parentView.getItemAtPosition(position).toString() == Varliklarim_Fragment.varliklarim.get(i).getTur()) {

                            para_satilan = Varliklarim_Fragment.varliklarim.get(i);
                            para_satilan.setTur(parentView.getItemAtPosition(position).toString());
                            tv_birimkur.setText(String.valueOf(para_satilan.getGuncel_alim_kuru() + "000000").substring(0, 6) + "₺");


                            if ((tv_miktar.getText().toString().length() > 0 && tv_miktar.getText().toString() != null)) {
                                miktar = Double.valueOf(tv_miktar.getText().toString());
                                tlkazanci = miktar * para_satilan.getGuncel_alim_kuru();
                                tv_gelir.setText(String.valueOf(tlkazanci + "000000").substring(0, 6) + "₺");
                            }
                        }

                    }
                    else
                        Snackbar.make(getView(), "Satacak Varlığınız Bulunmamaktadır, Satış Yapmak İçin Döviz Alımı Yapınız... ", 2500)
                                .setAction("Action", null).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }

        });

        tv_miktar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }


            //miktar texti değiştiginde yapılacak işlemler..
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if ((tv_miktar.getText().toString().length() > 0 && tv_miktar.getText().toString() != null)) {
                    miktar = Double.valueOf(tv_miktar.getText().toString());
                    tlkazanci = miktar*para_satilan.getGuncel_alim_kuru();
                    tv_gelir.setText(String.valueOf(tlkazanci+"000000").substring(0, 6) + "₺");
                } else ;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btn_sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if(  para_satilan.getMiktar() >= miktar )

                     show_dialogsatim().show();




                 else
                     Snackbar.make(getView(),"Varlıklarınızda "+para_satilan.getMiktar()+" "+para_satilan.getTur()+" Bulunmaktadır. Satış Miktarını Uygun Giriniz..", 2500)
                             .setAction("Action", null).show();
            }
        });


    }

    protected void sat(){

        int varlik_index =indexbul();
        if(para_satilan.getMiktar() == miktar){

            //VARLIGIN HEPSİ SATILIYORSA..

            double tl =  Varliklarim_Fragment.TL.getDeger()+tlkazanci;
            Varliklarim_Fragment.TL.setDeger(tl);
            Varliklarim_Fragment.varliklarim.remove(varlik_index);
//              Varliklarim_Fragment.varliklarim.get(varlik_index).setMiktar(0.0);
//              Varliklarim_Fragment.varliklarim.get(varlik_index).setDeger(0.0);
//              Varliklarim_Fragment.varliklarim.get(varlik_index).setMaliyet(0.0);
//            Varliklarim_Fragment.varliklarim.get(varlik_index).setGuncel_alim_kuru(0.0);
//            Varliklarim_Fragment.varliklarim.get(varlik_index).setGuncel_satim_kuru(0.0);
//            Varliklarim_Fragment.varliklarim.get(varlik_index).setGuncel_degisim_orani(0.0);
            // VARLIGII ARRAYLİSTTEN SİLEMEDİM...


        }
        else{

            //VARLIGIN BİR KISMI SATILIYORSA..

            Double eski_maliyet= Varliklarim_Fragment.varliklarim.get(varlik_index).getMaliyet();
            Double eski_miktar= Varliklarim_Fragment.varliklarim.get(varlik_index).getMiktar();

            Varliklarim_Fragment.varliklarim.get(varlik_index).setMiktar(eski_miktar-miktar);
            Varliklarim_Fragment.varliklarim.get(varlik_index).setMaliyet(eski_maliyet-tlkazanci);
            Varliklarim_Fragment.varliklarim.get(varlik_index).setDeger(
                    Varliklarim_Fragment.varliklarim.get(varlik_index).getMiktar()*Varliklarim_Fragment.varliklarim.get(varlik_index).getGuncel_alim_kuru()
            );
            double tl =  Varliklarim_Fragment.TL.getDeger()+tlkazanci;

            Varliklarim_Fragment.TL.setDeger(tl);

        }
        satim_islemi_ekle();

    }
    protected int indexbul() {


        for (int i = 0; i < Varliklarim_Fragment.varliklarim.size(); i++) {
            if((Varliklarim_Fragment.varliklarim.get(i).getTur().length() > 0 && Varliklarim_Fragment.varliklarim.get(i).getTur() != null))
                if(Varliklarim_Fragment.varliklarim.get(i).getTur().equalsIgnoreCase(para_satilan.getTur()))
                    return i;
        }
        return -1;
    }


    protected void init(View v){

        db=FirebaseDatabase.getInstance();
        spinner = (Spinner)v.findViewById(R.id.spinner1);
        tv_birimkur =(EditText) v.findViewById(R.id.tv_birimkur);
        tv_gelir=(EditText)v.findViewById(R.id.tv_gelir);
        tv_miktar=(EditText)v.findViewById(R.id.tv_miktar);
        btn_sat = (Button) v.findViewById(R.id.btn_sell);
    }



    //HESAP DÖKÜMÜ İÇİN.....
    protected void satim_islemi_ekle(){
        Islemler islem =new Islemler();

        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());

        islem.setpara_turu(para_satilan.getTur());
        islem.setMaliyet(0.0);
        islem.setMiktar(miktar);
        islem.setIslem_turu("satis");
        islem.setIslem_tarihi(currentDateTimeString);
        islem.setTl_degeri(tlkazanci);

        butun_islemler.add(islem);
        satim_islemi.add(islem);
        veritabani_hesap_islemleri(islem);

    }


    protected void veritabani_varlik_islemleri(){

        DatabaseReference dbRef = db.getReference(MainActivity.userid+"/Varliklar");


     //Varlıkları kaydet&güncelle
        for(int i=0;i<Varliklarim_Fragment.varliklarim.size();i++) {
            if (Varliklarim_Fragment.varliklarim.get(i).getMiktar() > 0) {
                String key = Varliklarim_Fragment.varliklarim.get(i).getTur().replaceAll("[^A-Za-z0-9]", "");
                DatabaseReference dbRefKeyli = db.getReference(MainActivity.userid + "/Varliklar/" + key);
                dbRefKeyli.setValue(Varliklarim_Fragment.varliklarim.get(i));

            }
            else {
                String key = Varliklarim_Fragment.varliklarim.get(i).getTur().replaceAll("[^A-Za-z0-9]", "");
                DatabaseReference dbRefKeyli = db.getReference(MainActivity.userid + "/Varliklar/" + key);
                dbRefKeyli.removeValue();
            }
        }
        //TL miktarını kaydet&güncelle
        DatabaseReference dbRefKeyliTL = db.getReference(MainActivity.userid + "/TLDegeri" );
        dbRefKeyliTL.setValue(Varliklarim_Fragment.TL.getDeger());

    }



    protected void veritabani_hesap_islemleri(Islemler islem){

        //Hesap gecmisi icin islemleri kaydet&güncelle
        DatabaseReference dbRefkey = db.getReference(MainActivity.userid+"/Satis Islemleri");
            String key =  dbRefkey.push().getKey();
            DatabaseReference dbRefKeyli = db.getReference(MainActivity.userid + "/Satis Islemleri/" + key);
            dbRefKeyli.setValue(islem);

        DatabaseReference dbRefkey2 = db.getReference(MainActivity.userid+"/Butun Islemler");
            String key2 = dbRefkey2.push().getKey();
            DatabaseReference dbRefKeyli2 = db.getReference(MainActivity.userid + "/Butun Islemler/" + key2);
            dbRefKeyli2.setValue(islem);

    }


    public Dialog show_dialogsatim(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Miktar: "+miktar+"\n"+
                "Para Türü: " +para_satilan.getTur()+
                "\nSatış İşlemini Onaylıyor Musunuz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Snackbar.make(getView(), "Satış İşlemi Onaylandı. ", 2000)
                                .setAction("Action", null).show();
                        sat();
                        veritabani_varlik_islemleri();
                    }

                })

                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }










}






