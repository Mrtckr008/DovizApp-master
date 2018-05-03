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

import java.util.ArrayList;
import java.util.Date;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Alim_Islemi_Fragment extends Fragment {


    FirebaseDatabase db;
    public static ArrayList<Islemler> alim_islemi = new ArrayList<>();
    public static Para para_alinan =new Para();
    EditText tv_birimkur,tv_maliyet,tv_miktar;
    Spinner spinner;
    Button btn_al;
    Double maliyet=0.0;
    Double miktar=0.0;

    public Alim_Islemi_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alim__islemi, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Alış Ekle");

       init(view);

       String[] items = new String[20];


        // Spinneri Dolduruyorum..
        for(int i=0; i< 20;i++){
            if((Guncel_Kurlar_Fragment.para[i].getTur().toString().length() > 0 && Guncel_Kurlar_Fragment.para[i].getTur().toString() != null))
            items[i]= Guncel_Kurlar_Fragment.para[i].getTur();

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        //Spinnerdan secilen iteme göre yapılan işlemler..
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                    Guncel_Kurlar_Fragment.GUNCELLE=false;

                for(int i=0; i< 20;i++){

                    //secilen degerin hangi para nesnesi olduğunu buluyoruz..
                     if(parentView.getItemAtPosition(position).toString() == Guncel_Kurlar_Fragment.para[i].getTur()){

                         para_alinan =Guncel_Kurlar_Fragment.para[i];
                         para_alinan.setTur(parentView.getItemAtPosition(position).toString());
                         tv_birimkur.setText(String.valueOf(para_alinan.getGuncel_satim_kuru()+"000000").substring(0,6)+"₺");


                         if((tv_miktar.getText().toString().length() > 0 && tv_miktar.getText().toString() != null))
                         {
                              miktar = Double.valueOf(tv_miktar.getText().toString());
                             maliyet =miktar * para_alinan.getGuncel_satim_kuru();
                             tv_maliyet.setText(String.valueOf(maliyet+"000000").substring(0, 6)+"₺");
                         }
                     }

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

                if((tv_miktar.getText().toString().length() > 0 && tv_miktar.getText().toString() != null))
                {
                    miktar = Double.valueOf(tv_miktar.getText().toString());
                    maliyet =miktar * para_alinan.getGuncel_satim_kuru();
                    tv_maliyet.setText(String.valueOf(maliyet +"000000").substring(0, 6)+"₺");
                }
                else;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btn_al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(Varliklarim_Fragment.TL.getDeger() >= maliyet && tv_miktar.getText().toString() != null ){

                if(tv_miktar.getText().toString().equalsIgnoreCase("") ||
                        tv_miktar.getText().toString().equalsIgnoreCase("0")   ||
                        tv_miktar.getText().toString().equalsIgnoreCase("00")  ||
                        tv_miktar.getText().toString().equalsIgnoreCase("000") ||
                        tv_miktar.getText().toString().equalsIgnoreCase("0000"))
                {
                    Snackbar.make(getView(), "Geçerli Bir Miktar Giriniz.. ", 2000)
                            .setAction("Action", null).show();
                }
                else
                show_dialogalim().show();

            }
            else if(tv_miktar.getText().toString() == null)
                Snackbar.make(getView(), "Geçerli Bir Miktar Giriniz.. ", 2000)
                    .setAction("Action", null).show();
            else{
                Snackbar.make(getView(), "Yeterli Bakiyeniz Bulunmamaktadır. Lütfen Paranızı TL'ye çeviriniz.. ", 2000)
                        .setAction("Action", null).show();
            }


            }
        });
    }


    protected void satinal(){

        int varlik_index =yeni_varlik_mi_kontrol();

     if( varlik_index == -1){ //yeni varlık ise..

         Varliklar yeni_varlik=new Varliklar();

         yeni_varlik.setTur(para_alinan.getTur());
         yeni_varlik.setMiktar(miktar);
         yeni_varlik.setMaliyet(maliyet);

         yeni_varlik.setGuncel_alim_kuru(para_alinan.getGuncel_alim_kuru());

         yeni_varlik.setDeger(para_alinan.getGuncel_alim_kuru()*miktar);

         Varliklarim_Fragment.TL.setDeger(Varliklarim_Fragment.TL.getDeger() - maliyet);

         Varliklarim_Fragment.varliklarim.add(yeni_varlik);
     }

     else{ // yeni varlik degil ise..



         Double eski_maliyet= Varliklarim_Fragment.varliklarim.get(varlik_index).getMaliyet();
         Double eski_miktar= Varliklarim_Fragment.varliklarim.get(varlik_index).getMiktar();
         Varliklarim_Fragment.varliklarim.get(varlik_index).setDeger(para_alinan.getGuncel_alim_kuru()*(miktar+eski_miktar));
         Varliklarim_Fragment.varliklarim.get(varlik_index).setMaliyet(maliyet+eski_maliyet);
         Varliklarim_Fragment.varliklarim.get(varlik_index).setMiktar(miktar+eski_miktar);

         //YANLIS OLABİLİR..
     /*    Varliklarim_Fragment.varliklarim.get(varlik_index).setDeger(//VARLIKKLAR SAYFASINI ACTIGINDA DA DEGERİ TEKRAR HESAPLAMALII
                 Varliklarim_Fragment.varliklarim.get(varlik_index).getGuncel_alim_kuru()*miktar); */

         Varliklarim_Fragment.TL.setDeger(Varliklarim_Fragment.TL.getDeger() - maliyet);

     }

     alim_islemi_ekle();
    }



    protected  int yeni_varlik_mi_kontrol() {
        int i;
        for(i=0; i< Varliklarim_Fragment.varliklarim.size();i++){

            if((Varliklarim_Fragment.varliklarim.get(i).getTur().length() > 0 && Varliklarim_Fragment.varliklarim.get(i).getTur() != null))
            if(para_alinan.getTur().equalsIgnoreCase(Varliklarim_Fragment.varliklarim.get(i).getTur()))
                return i;

        }
        return -1;
    }



 //HESAP DÖKÜMÜ İÇİN.....
    protected void alim_islemi_ekle(){
        Islemler islem =new Islemler();

        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());

        islem.setpara_turu(para_alinan.getTur());
        islem.setMaliyet(maliyet);
        islem.setMiktar(miktar);
        islem.setIslem_turu("alis");
        islem.setIslem_tarihi(currentDateTimeString);
        islem.setTl_degeri(0.0);


        Satim_Islemi_Fragment.butun_islemler.add(islem);
        alim_islemi.add(islem);

        veritabani_hesap_islemleri(islem);


    }


    protected void init(View v){

        db=FirebaseDatabase.getInstance();

        spinner = (Spinner)v.findViewById(R.id.spinner1);
        tv_birimkur =(EditText) v.findViewById(R.id.tv_birimkur);
        tv_maliyet=(EditText)v.findViewById(R.id.tv_maliyet);
        tv_miktar=(EditText)v.findViewById(R.id.tv_miktar);
        btn_al = (Button) v.findViewById(R.id.btn_buy);
    }
    public Dialog show_dialogalim(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Miktar: "+miktar+"\n"+
                "Para Türü: " +para_alinan.getTur()+
                "\nAlım İşlemini Onaylıyor Musunuz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Snackbar.make(getView(), "Alım İşlemi Onaylandı. ", 2000)
                                .setAction("Action", null).show();
                        satinal();
                        veritabani_varlik_islemleri();
                    }

                })

                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

    protected void veritabani_varlik_islemleri(){




        for(int i=0;i<Varliklarim_Fragment.varliklarim.size();i++)
        {
            String key = Varliklarim_Fragment.varliklarim.get(i).getTur().replaceAll("[^A-Za-z0-9]", "");
            DatabaseReference dbRefKeyli = db.getReference(MainActivity.userid+"/Varliklar/"+key);
            dbRefKeyli.setValue(Varliklarim_Fragment.varliklarim.get(i));
        }
        DatabaseReference dbRefKeyliTL = db.getReference(MainActivity.userid + "/TLDegeri" );
        dbRefKeyliTL.setValue(Varliklarim_Fragment.TL.getDeger());



    }

    protected void veritabani_hesap_islemleri(Islemler islem){

        DatabaseReference dbRefkey = db.getReference(MainActivity.userid+"/Alim Islemleri");
        String key = dbRefkey.push().getKey();
        DatabaseReference dbRefKeyli = db.getReference(MainActivity.userid + "/Alim Islemleri/" + key);
        dbRefKeyli.setValue(islem);


        DatabaseReference dbRefkey2 = db.getReference(MainActivity.userid+"/Butun Islemler");
        String key2 = dbRefkey2.push().getKey();
        DatabaseReference dbRefKeyli2 = db.getReference(MainActivity.userid + "/Butun Islemler/" + key2);
        dbRefKeyli2.setValue(islem);

    }

}
