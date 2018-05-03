package iliada.dovizapp;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Doviz_Cevirici_fragment extends Fragment {

    Para cevrilen_para;
    EditText tv_miktarcevirici;
    Spinner spinner;
    Double miktar;
    RadioGroup radiogroup;
    RadioButton radio_alis,radio_satis;
    LinearLayout ll_genel,ll_satir,ll_bilgi;
    TextView tv_paraturu,tv_paradegeri;
    Boolean satis_degerine_gore_hesapla;
    public Doviz_Cevirici_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doviz__cevirici, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        String[] items = new String[20];
        getActivity().setTitle("Döviz Çevirici");
        for(int i=0; i< 20;i++){
            if((Guncel_Kurlar_Fragment.para[i].getTur().toString().length() > 0 && Guncel_Kurlar_Fragment.para[i].getTur().toString() != null))
                items[i]= Guncel_Kurlar_Fragment.para[i].getTur();

        }
//        items[20]="Türk Lirası";
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

                        cevrilen_para = Guncel_Kurlar_Fragment.para[i];
                        cevrilen_para.setGuncel_alim_kuru(Guncel_Kurlar_Fragment.para[i].getGuncel_alim_kuru());
                        cevrilen_para.setGuncel_satim_kuru(Guncel_Kurlar_Fragment.para[i].getGuncel_satim_kuru());
                        cevrilen_para.setTur(Guncel_Kurlar_Fragment.para[i].getTur());


                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }

        });


        tv_miktarcevirici.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }


            //miktar texti değiştiginde yapılacak işlemler..
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if((tv_miktarcevirici.getText().toString().length() > 0 && tv_miktarcevirici.getText().toString() != null))
                {
                    miktar = Double.valueOf(tv_miktarcevirici.getText().toString());
                    if(miktar !=null && satis_degerine_gore_hesapla!=null){
                        degerleri_olustur();
                    }
                    if(miktar ==null) Toast.makeText(getActivity(),"Lütfen Miktar Belirtiniz",Toast.LENGTH_LONG).show();
                    if(satis_degerine_gore_hesapla == null) Toast.makeText(getActivity(),"Alım / Satım Hesaplama Kurunu Belirtiniz..",Toast.LENGTH_LONG).show();

                }
                else;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });




        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(radio_alis.isChecked())
                {
                    satis_degerine_gore_hesapla=false;
                    if(miktar ==null) Toast.makeText(getActivity(),"Lütfen Miktar Belirtiniz",Toast.LENGTH_LONG).show();
                    else
                    degerleri_olustur();
                }
                else if(radio_satis.isChecked())
                {
                    satis_degerine_gore_hesapla=true;
                    if(miktar ==null) Toast.makeText(getActivity(),"Lütfen Miktar Belirtiniz",Toast.LENGTH_LONG).show();
                    else
                    degerleri_olustur();
                }
            }
        });


    }



    protected void init(View v){


        ll_genel= (LinearLayout)v.findViewById(R.id.ll_genelcevirici);
        radio_alis = (RadioButton)v.findViewById(R.id.radio_alis);
        radio_satis = (RadioButton)v.findViewById(R.id.radio_satis);
        tv_miktarcevirici=(EditText)v.findViewById(R.id.tv_miktarcevirici);
        radiogroup = (RadioGroup) v.findViewById(R.id.radioGroup1);
        spinner = (Spinner)v.findViewById(R.id.spinner_cevirici);
        cevrilen_para=new Para();

    }

    protected void yeni_satir_olustur(){

        ll_satir =new LinearLayout(getActivity());
        LinearLayout.LayoutParams paramssatir = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,0, 1f);
        ll_satir.setPadding(5,5,5,5);
        ll_satir.setLayoutParams(paramssatir);
    }

    protected void yeni_hucre_olustur(){

        ll_bilgi=new LinearLayout(getActivity());
        tv_paradegeri=new TextView(getActivity());
        tv_paraturu = new TextView(getActivity());
        LinearLayout.LayoutParams paramsbilgi = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
        paramsbilgi.setMargins(5,5,5,5);
        ll_bilgi.setOrientation(LinearLayout.VERTICAL);
        ll_bilgi.setGravity(Gravity.CENTER);
        ll_bilgi.setBackground(getResources().getDrawable(R.drawable.doviz_cevirici_borders)); //KAFANA GÖRE DEGİSTİREBİLİRSİN.
        ll_bilgi.setLayoutParams(paramsbilgi);
        ll_bilgi.setForegroundGravity(Gravity.CENTER);

    }

    protected void degerleri_olustur(){

        ll_genel.removeAllViews();

        int para_index=0;
     for(int i=0;i<10;i++){

         yeni_satir_olustur();

         for(int k=0;k<2;k++){

             yeni_hucre_olustur();

             tv_paraturu.setGravity(Gravity.CENTER);
             tv_paradegeri.setGravity(Gravity.CENTER);
             tv_paraturu.setText("Türü: "+ Guncel_Kurlar_Fragment.para[para_index].getTur());
             tv_paraturu.setTypeface(Typeface.DEFAULT_BOLD);
             tv_paraturu.setTextColor(Color.BLACK);
             //1 USD 0.84 EU için DOLAR KURU / EUR KURU
             Double parite;
             if(satis_degerine_gore_hesapla)
             parite= cevrilen_para.getGuncel_satim_kuru()/Guncel_Kurlar_Fragment.para[para_index].getGuncel_satim_kuru();
             else
                 parite= cevrilen_para.getGuncel_alim_kuru()/Guncel_Kurlar_Fragment.para[para_index].getGuncel_alim_kuru();

             tv_paradegeri.setText("Değeri: "+ String.valueOf( (parite * miktar) + "000000").substring(0, 6) ); //ALIM KURU RADIOYA GÖRE DEGİSMELİ!
             tv_paradegeri.setTypeface(Typeface.DEFAULT_BOLD);
             tv_paradegeri.setTextColor(Color.BLACK);

             ll_bilgi.addView(tv_paraturu);
             ll_bilgi.addView(tv_paradegeri);

             ll_satir.addView(ll_bilgi);
             para_index++;
         }
         ll_genel.addView(ll_satir);
     }











    }
}
