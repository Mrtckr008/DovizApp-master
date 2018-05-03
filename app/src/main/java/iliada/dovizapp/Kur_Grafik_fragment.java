package iliada.dovizapp;


import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;




import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Kur_Grafik_fragment extends Fragment implements
        OnChartGestureListener,OnChartValueSelectedListener {
    private LineChart lineChart;

    Spinner spinner;
    RadioGroup radiogroup;
    RadioButton radio_gunluk,radio_haftalik,radio_aylik,radio_yillik;
    String paraturu;
    public Kur_Grafik_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kur_grafik, container, false);
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Kur Değişim Grafikleri");


        init(view);

        String[] items = new String[20];


        for(int i=0; i< 20;i++){
            if((Guncel_Kurlar_Fragment.para[i].getTur().toString().length() > 0 && Guncel_Kurlar_Fragment.para[i].getTur().toString() != null))
                items[i]= Guncel_Kurlar_Fragment.para[i].getTur();

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                Guncel_Kurlar_Fragment.GUNCELLE=false;

                for(int i=0; i< 20;i++){

                    //secilen degerin hangi para nesnesi olduğunu buluyoruz..
                    if(parentView.getItemAtPosition(position).toString() == Guncel_Kurlar_Fragment.para[i].getTur()){

                        //Spinnerdan secilen iteme göre yapılan işlemler..
                        //Guncel_Kurlar_Fragment.para[i].getTur() > dolar secilmisse amerikan doları yazisini ceker.
                        paraturu=Guncel_Kurlar_Fragment.para[i].getTur();

                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }

        });
        CizUSDdaily(paraturu,view);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override/*GUNLUKLER*/
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radio_gunluk.isChecked()&& Objects.equals(paraturu, "Amerikan Doları")) {
                    CizUSDdaily(paraturu,view);}
                else if (radio_haftalik.isChecked()&& Objects.equals(paraturu, "Euro")) {
                    CizEurodaily(paraturu,view);}
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "İngiliz Sterlini")){
                    CizSterlindaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "İsviçre Frangı")) {
                    CizIsvicredaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "Kanada Doları")) {
                    CizKanadadaily(paraturu,view);       }

                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "Rus Rublesi")) {
                    CizRusdaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "B.A.E. Dirhemi")) {
                    CizBAEdaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "Avustralya Doları")) {
                    CizAvustdaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "Danimarka Kronu")) {
                    CizDankdaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "İsveç Kronu")) {
                    CizIsvecdaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "Norveç Kronu")) {
                    CizNorvecdaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "100 Japon Yeni")) {
                    CizJapondaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "Kuveyt Dinarı")) {
                    CizKuveytdaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "Güney Afrika Randı")) {
                    CizGafrdaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "Bahreyn Dinarı")) {
                    CizBahrdaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "Libya Dinarı")) {
                    CizLibyadaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "S. Arabistan Riyali")) {
                    CizSuudidaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "Irak Dinarı")) {
                    CizIrakdaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "İsrail Şekeli")) {
                    CizIsraildaily(paraturu,view);       }
                else if(radio_gunluk.isChecked()&& Objects.equals(paraturu, "İran Riyali")) {
                    CizIrandaily(paraturu,view);       }






/*HAFTALIKLAR*/




                else if (radio_haftalik.isChecked()&& Objects.equals(paraturu, "Amerikan Doları")) {
                    CizDolarWeekly(paraturu,view);}
                else if (radio_haftalik.isChecked()&& Objects.equals(paraturu, "Euro")) {
                    CizEuroWeekly(paraturu,view);}
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "İngiliz Sterlini")){
                    CizSterlinWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "İsviçre Frangı")) {
                    CizIsvicreWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "Kanada Doları")) {
                    CizKanadaWeekly(paraturu,view);       }

                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "Rus Rublesi")) {
                    CizRusWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "B.A.E. Dirhemi")) {
                    CizBAEWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "Avustralya Doları")) {
                    CizAvDolarWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "Danimarka Kronu")) {
                    CizDankWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "İsveç Kronu")) {
                    CizIsvecWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "Norveç Kronu")) {
                    CizNorvecWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "100 Japon Yeni")) {
                    CizJaponWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "Kuveyt Dinarı")) {
                    CizKuveWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "Güney Afrika Randı")) {
                    CizGafrWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "Bahreyn Dinarı")) {
                    CizBahrWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "Libya Dinarı")) {
                    CizLibyaWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "S. Arabistan Riyali")) {
                    CizSuudiWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "Irak Dinarı")) {
                    CizIrakWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "İsrail Şekeli")) {
                    CizIsrailWeekly(paraturu,view);       }
                else if(radio_haftalik.isChecked()&& Objects.equals(paraturu, "İran Riyali")) {
                    CizIranWeekly(paraturu,view);       }







/*AYLIKLAR*/
                else if (radio_aylik.isChecked()&& Objects.equals(paraturu, "Amerikan Doları")) {
                    CizDolarMontly(paraturu,view);}
                else if (radio_aylik.isChecked() && Objects.equals(paraturu, "Euro")) {
                    CizEuroMontly(paraturu, view);
                }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "İngiliz Sterlini")){
                    CizSterlinMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "İsviçre Frangı")) {
                    CizIsvicreMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "Kanada Doları")) {
                    CizKanadaMontly(paraturu,view);       }

                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "Rus Rublesi")) {
                    CizRusMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "B.A.E. Dirhemi")) {
                    CizBAEMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "Avustralya Doları")) {
                    CizAvustrMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "Danimarka Kronu")) {
                    CizDankMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "İsveç Kronu")) {
                    CizIsvecMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "Norveç Kronu")) {
                    CizNorvecMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "100 Japon Yeni")) {
                    CizJaponMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "Kuveyt Dinarı")) {
                    CizKuveytMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "Güney Afrika Randı")) {
                    CizGafriMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "Bahreyn Dinarı")) {
                    CizBahrMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "Libya Dinarı")) {
                    CizLibyaMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "S. Arabistan Riyali")) {
                    CizSarabiMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "Irak Dinarı")) {
                    CizIrakMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "İsrail Şekeli")) {
                    CizIsrailMontly(paraturu,view);       }
                else if(radio_aylik.isChecked()&& Objects.equals(paraturu, "İran Riyali")) {
                    CizIranMontly(paraturu,view);       }


                /*YILLIKLAR*/
                else if (radio_yillik.isChecked() && Objects.equals(paraturu, "Amerikan Doları")) {
                    CizDolarYearly(paraturu, view);
                } else if (radio_yillik.isChecked() && Objects.equals(paraturu, "Euro")) {
                    CizEuroYearly(paraturu, view);
                }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "İngiliz Sterlini")){
                    CizSterlinYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "İsviçre Frangı")) {
                    CizIsvcreYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "Kanada Doları")) {
                    CizKanadaYearly(paraturu,view);       }

                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "Rus Rublesi")) {
                    CizRusYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "B.A.E. Dirhemi")) {
                    CizBAEYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "Avustralya Doları")) {
                    CizAvustYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "Danimarka Kronu")) {
                    CizDanimarkaYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "İsveç Kronu")) {
                    CizIsvecYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "Norveç Kronu")) {
                    CizNorvecYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "100 Japon Yeni")) {
                    CizJaponyaYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "Kuveyt Dinarı")) {
                    CizKuveytYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "Güney Afrika Randı")) {
                    CizGuneyAfrYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "Bahreyn Dinarı")) {
                    CizBahreynYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "Libya Dinarı")) {
                    CizLibyaYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "S. Arabistan Riyali")) {
                    CizSuudiYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "Irak Dinarı")) {
                    CizIrakYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "İsrail Şekeli")) {
                    CizIsrailYearly(paraturu,view);       }
                else if(radio_yillik.isChecked()&& Objects.equals(paraturu, "İran Riyali")) {
                    CizIranYearly(paraturu,view);       }


            }













        });


    }



    public void CizDolarYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyUsd().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);


                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);

                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);
                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast= Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Amerikan Doları'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);
                                                                  //  TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizEuroYearly(String USD, final View view){
        ApiService.Factory.getInstance().getgraphyearlyEuro().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);
                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast= Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Euro'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizSterlinYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlySterlin().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);
                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast= Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İngiliz Sterlini'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizIsvcreYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyIsvFrangi().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);
                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast= Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İsviçre Frangı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizKanadaYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyKanDoları().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast= Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Kanada Doları'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizRusYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyRusRub().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast= Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Rus Rublesi'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizBAEYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyBae().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast= Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"B.A.E Dirhemi'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }


    public void CizAvustYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyAvsDoları().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast= Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Avustralya Doları'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }


    public void CizDanimarkaYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyDanKron().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast= Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Danimarka Kronu'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizIsvecYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyIsvecKron().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast= Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İsveç Kronu'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizNorvecYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyNorvKron().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast= Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Norveç Kronu'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizJaponyaYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyJapYeni().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast= Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"100 Japon Yeni'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizKuveytYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyKuvDinarı().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Kuveyt Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }


    public void CizGuneyAfrYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyGunAfrRan().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Güney Afrika Randı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }



    public void CizBahreynYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyBahDinar().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Bahreyn Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizLibyaYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyLibDinar().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Libya Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizSuudiYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlySuudiRiyal().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"S. Arabistan Riyalı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }


    public void CizIrakYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyIrakDinar().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Irak Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizIsrailYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyIsrailSek().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İsrail Şekili'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizIranYearly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphyearlyIranRiyal().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(10);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İran Riyali'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    /*AYLIKLAR*/
    public void CizDolarMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyUsd().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Amerikan Doları'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizEuroMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyEuro().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Euro'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizSterlinMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlySter().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İngiliz Sterlini'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizIsvicreMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyIsvicre().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İsviçre Frangı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizKanadaMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyKanada().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Kanada Doları'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizRusMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyRus().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Rus Rublesi'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizBAEMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyBAE().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"B.A.E Dirhemi'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizAvustrMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyAvustralya().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Avustralya Doları'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizDankMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyDank().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Danimarka Kronu'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizIsvecMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyIsvec().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İsveç Kronu'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizNorvecMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyNorv().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Norveç Kronu'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizJaponMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyJap().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"100 Japon Yeni'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizKuveytMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyKuveyt().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Kuveyt Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizGafriMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyGAfri().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Güney Afrika Randı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizBahrMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyBahr().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Bahreyn Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizLibyaMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyLib().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Libya Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizSarabiMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlySuudi().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"S. Arabistan Riyali'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizIrakMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyIrak().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Irak Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizIsrailMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyIsrail().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İsrail Şekili'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizIranMontly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphmonthlyIran().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İran Riyali'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizDolarWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgrapweeklyUsd().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Amerikan Doları'nınn değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizEuroWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklylyEuro().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Euro'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizSterlinWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklySter().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İngiliz Sterlini'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }

    public void CizIsvicreWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyIsvicre().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İsviçre Frangı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizKanadaWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyKanada().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Kanada Doları'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizRusWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyRus().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Rus Rublesi'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizBAEWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyBAE().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"B.A.E Dirhemi'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizAvDolarWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyAvustralya().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Avustralya Doları'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizDankWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyDank().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Danimarka Kronu'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizIsvecWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyIsvec().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İsveç Kronu'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizNorvecWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyNorv().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Norveç Kronu'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizJaponWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyJap().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"100 Japon Yeni'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizKuveWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyKuveyt().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Kuveyt Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizGafrWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyGAfri().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Güney Afrika Randı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizBahrWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyBahr().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Bahreyn Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizLibyaWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyLib().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Libya Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizSuudiWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklySuudi().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"S. Arabistan Riyali'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizIrakWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyIrak().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"Irak Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizIsrailWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyIsrail().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İsrail Şekeli'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizIranWeekly(String USD, final View view){

        ApiService.Factory.getInstance().getgraphweeklyIran().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[1000];
                final String[] date1 = new String [1000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    String v1 = new SimpleDateFormat("dd.MM.yyyy").format(df);
                    date1[i]=v1;

                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y] +" tarihinde "+"İran Riyali'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizUSDdaily(String USD, final View view){

        ApiService.Factory.getInstance().getgrapdailyUsd ().enqueue(new Callback<List<ExampleGraph>>()
        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+ /*date2[(int)y]+*/" saatinde "+"Amerikan Doları'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizEurodaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyEuro().enqueue(new Callback<List<ExampleGraph>>()

        {   @Override
        public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
            LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
            final Float[] graph_value = new Float[4000];
            final String[] date1 = new String [4000];
            final String[] date2 = new String [4000];
            lineChart.setTouchEnabled(true);
            for (int i = 0; i < 50; i++) {
                lineChart.zoomOut();
            }
            lineChart.setPinchZoom(true);
            List<ExampleGraph> jsondoviz1 = response.body();
            for (int i = 0; i < jsondoviz1.size(); i++) {

                Float substr1 = jsondoviz1.get(i).getSelling();
                graph_value[i] = substr1;

                String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                Date df = new java.util.Date(dv);
                //   String v2 = new SimpleDateFormat("kk").format(df);
                String v1 = new SimpleDateFormat("kk:hh").format(df);
                //   String v3 = new SimpleDateFormat("mm").format(df);
                //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                date1[i]=String.valueOf(v1);
            }
            ArrayList<Entry> entries = new ArrayList<>();
            for (int h = 0; h < jsondoviz1.size(); h++) {
                Float deger213 = graph_value[h];
                entries.add(new Entry(deger213, h));

            }
            LineDataSet dataset = new LineDataSet(entries, "# of Calls");

            ArrayList<String> labels = new ArrayList<String>();
            for (int s = 0; s < jsondoviz1.size(); s++)
                labels.add(date1[s]);
            LineData data = new LineData(labels, dataset);
            dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            dataset.setDrawCubic(true);
            dataset.setDrawFilled(true);
            dataset.setCircleSize(2);
            dataset.setValueTextSize(0);
            dataset.setHighLightColor(Color.BLACK);

            dataset.setFillColor(Color.parseColor("#FF7F00"));
            dataset.setCircleColor(Color.parseColor("#cd661d"));
            dataset.setCircleColorHole(Color.parseColor("#cd661d"));
            lineChart.setData(data);
            lineChart.notifyDataSetChanged();
            lineChart.animateX(1000);
            //lineChart.animateY(1000);
            lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                          @Override
                                                          public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                              float y=e.getXIndex();
                                                              Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"Euro'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);
                                                              TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                                                              v.setTextColor(Color.BLACK);
                                                              toast.show();
                                                          }
                                                          @Override
                                                          public void onNothingSelected() {
                                                          }
                                                      }

            );
        }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizSterlindaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailySter().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"İngiliz Sterlini'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizIsvicredaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyIsvicre().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"İsviçre Frangı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizKanadadaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyKanada().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"Kanada Doları'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizRusdaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyRus().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());
                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"Rus Rublesi'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizBAEdaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyBAE().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"B.A.E Dirhemi'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizAvustdaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyAvustralya().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"Avustralya Doları'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizDankdaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyDank().enqueue(new Callback<List<ExampleGraph>>()

        {    @Override
        public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
            LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
            final Float[] graph_value = new Float[4000];
            final String[] date1 = new String [4000];
            final String[] date2 = new String [4000];
            lineChart.setTouchEnabled(true);
            for (int i = 0; i < 50; i++) {
                lineChart.zoomOut();
            }
            lineChart.setPinchZoom(true);
            List<ExampleGraph> jsondoviz1 = response.body();
            for (int i = 0; i < jsondoviz1.size(); i++) {

                Float substr1 = jsondoviz1.get(i).getSelling();
                graph_value[i] = substr1;

                String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                Date df = new java.util.Date(dv);
                //   String v2 = new SimpleDateFormat("kk").format(df);
                String v1 = new SimpleDateFormat("kk:hh").format(df);
                //   String v3 = new SimpleDateFormat("mm").format(df);
                //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                date1[i]=String.valueOf(v1);
            }
            ArrayList<Entry> entries = new ArrayList<>();
            for (int h = 0; h < jsondoviz1.size(); h++) {
                Float deger213 = graph_value[h];
                entries.add(new Entry(deger213, h));

            }
            LineDataSet dataset = new LineDataSet(entries, "# of Calls");

            ArrayList<String> labels = new ArrayList<String>();
            for (int s = 0; s < jsondoviz1.size(); s++)
                labels.add(date1[s]);
            LineData data = new LineData(labels, dataset);
            dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            dataset.setDrawCubic(true);
            dataset.setDrawFilled(true);
            dataset.setCircleSize(2);
            dataset.setValueTextSize(0);
            dataset.setHighLightColor(Color.BLACK);

            dataset.setFillColor(Color.parseColor("#FF7F00"));
            dataset.setCircleColor(Color.parseColor("#cd661d"));
            dataset.setCircleColorHole(Color.parseColor("#cd661d"));
            lineChart.setData(data);
            lineChart.notifyDataSetChanged();
            lineChart.animateX(1000);
            //lineChart.animateY(1000);
            lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                          @Override
                                                          public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                              float y=e.getXIndex();
                                                              Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"Danimarka Kronu'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);
                                                              TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                                                              v.setTextColor(Color.BLACK);
                                                              toast.show();
                                                          }
                                                          @Override
                                                          public void onNothingSelected() {
                                                          }
                                                      }

            );
        }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizIsvecdaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyIsvec().enqueue(new Callback<List<ExampleGraph>>()

        {    @Override
        public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
            LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
            final Float[] graph_value = new Float[4000];
            final String[] date1 = new String [4000];
            final String[] date2 = new String [4000];
            lineChart.setTouchEnabled(true);
            for (int i = 0; i < 50; i++) {
                lineChart.zoomOut();
            }
            lineChart.setPinchZoom(true);
            List<ExampleGraph> jsondoviz1 = response.body();
            for (int i = 0; i < jsondoviz1.size(); i++) {

                Float substr1 = jsondoviz1.get(i).getSelling();
                graph_value[i] = substr1;

                String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());
                long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                Date df = new java.util.Date(dv);
                //   String v2 = new SimpleDateFormat("kk").format(df);
                String v1 = new SimpleDateFormat("kk:hh").format(df);
                //   String v3 = new SimpleDateFormat("mm").format(df);
                //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                date1[i]=String.valueOf(v1);
            }
            ArrayList<Entry> entries = new ArrayList<>();
            for (int h = 0; h < jsondoviz1.size(); h++) {
                Float deger213 = graph_value[h];
                entries.add(new Entry(deger213, h));

            }
            LineDataSet dataset = new LineDataSet(entries, "# of Calls");

            ArrayList<String> labels = new ArrayList<String>();
            for (int s = 0; s < jsondoviz1.size(); s++)
                labels.add(date1[s]);
            LineData data = new LineData(labels, dataset);
            dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            dataset.setDrawCubic(true);
            dataset.setDrawFilled(true);
            dataset.setCircleSize(2);
            dataset.setValueTextSize(0);
            dataset.setHighLightColor(Color.BLACK);

            dataset.setFillColor(Color.parseColor("#FF7F00"));
            dataset.setCircleColor(Color.parseColor("#cd661d"));
            dataset.setCircleColorHole(Color.parseColor("#cd661d"));
            lineChart.setData(data);
            lineChart.notifyDataSetChanged();
            lineChart.animateX(1000);
            //lineChart.animateY(1000);
            lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                          @Override
                                                          public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                              float y=e.getXIndex();
                                                              Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"İsveç Kronu'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);
                                                              TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                                                              v.setTextColor(Color.BLACK);
                                                              toast.show();
                                                          }
                                                          @Override
                                                          public void onNothingSelected() {
                                                          }
                                                      }

            );
        }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizNorvecdaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyNorv().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());
                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"Norveç Kronu'nun değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizJapondaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyJap().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"100 Japon Yeni'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizKuveytdaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyKuveyt().enqueue(new Callback<List<ExampleGraph>>()

        {    @Override
        public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
            LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
            final Float[] graph_value = new Float[4000];
            final String[] date1 = new String [4000];
            final String[] date2 = new String [4000];
            lineChart.setTouchEnabled(true);
            for (int i = 0; i < 50; i++) {
                lineChart.zoomOut();
            }
            lineChart.setPinchZoom(true);
            List<ExampleGraph> jsondoviz1 = response.body();
            for (int i = 0; i < jsondoviz1.size(); i++) {

                Float substr1 = jsondoviz1.get(i).getSelling();
                graph_value[i] = substr1;

                String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                Date df = new java.util.Date(dv);
                //   String v2 = new SimpleDateFormat("kk").format(df);
                String v1 = new SimpleDateFormat("kk:hh").format(df);
                //   String v3 = new SimpleDateFormat("mm").format(df);
                //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                date1[i]=String.valueOf(v1);
            }
            ArrayList<Entry> entries = new ArrayList<>();
            for (int h = 0; h < jsondoviz1.size(); h++) {
                Float deger213 = graph_value[h];
                entries.add(new Entry(deger213, h));

            }
            LineDataSet dataset = new LineDataSet(entries, "# of Calls");

            ArrayList<String> labels = new ArrayList<String>();
            for (int s = 0; s < jsondoviz1.size(); s++)
                labels.add(date1[s]);
            LineData data = new LineData(labels, dataset);
            dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            dataset.setDrawCubic(true);
            dataset.setDrawFilled(true);
            dataset.setCircleSize(2);
            dataset.setValueTextSize(0);
            dataset.setHighLightColor(Color.BLACK);

            dataset.setFillColor(Color.parseColor("#FF7F00"));
            dataset.setCircleColor(Color.parseColor("#cd661d"));
            dataset.setCircleColorHole(Color.parseColor("#cd661d"));
            lineChart.setData(data);
            lineChart.notifyDataSetChanged();
            lineChart.animateX(1000);
            //lineChart.animateY(1000);
            lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                          @Override
                                                          public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                              float y=e.getXIndex();
                                                              Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"Kuveyt Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);
                                                              TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                                                              v.setTextColor(Color.BLACK);
                                                              toast.show();
                                                          }
                                                          @Override
                                                          public void onNothingSelected() {
                                                          }
                                                      }

            );
        }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizGafrdaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyGAfri().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"Güney Afrika Randı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizBahrdaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyBahr().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());
                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"Bahreyn Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizLibyadaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyLib().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"Libya Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizSuudidaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailySuudi().enqueue(new Callback<List<ExampleGraph>>()

        {    @Override
        public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
            LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
            final Float[] graph_value = new Float[4000];
            final String[] date1 = new String [4000];
            final String[] date2 = new String [4000];
            lineChart.setTouchEnabled(true);
            for (int i = 0; i < 50; i++) {
                lineChart.zoomOut();
            }
            lineChart.setPinchZoom(true);
            List<ExampleGraph> jsondoviz1 = response.body();
            for (int i = 0; i < jsondoviz1.size(); i++) {

                Float substr1 = jsondoviz1.get(i).getSelling();
                graph_value[i] = substr1;

                String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());
                long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                Date df = new java.util.Date(dv);
                //   String v2 = new SimpleDateFormat("kk").format(df);
                String v1 = new SimpleDateFormat("kk:hh").format(df);
                //   String v3 = new SimpleDateFormat("mm").format(df);
                //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                date1[i]=String.valueOf(v1);
            }
            ArrayList<Entry> entries = new ArrayList<>();
            for (int h = 0; h < jsondoviz1.size(); h++) {
                Float deger213 = graph_value[h];
                entries.add(new Entry(deger213, h));

            }
            LineDataSet dataset = new LineDataSet(entries, "# of Calls");

            ArrayList<String> labels = new ArrayList<String>();
            for (int s = 0; s < jsondoviz1.size(); s++)
                labels.add(date1[s]);
            LineData data = new LineData(labels, dataset);
            dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            dataset.setDrawCubic(true);
            dataset.setDrawFilled(true);
            dataset.setCircleSize(2);
            dataset.setValueTextSize(0);
            dataset.setHighLightColor(Color.BLACK);

            dataset.setFillColor(Color.parseColor("#FF7F00"));
            dataset.setCircleColor(Color.parseColor("#cd661d"));
            dataset.setCircleColorHole(Color.parseColor("#cd661d"));
            lineChart.setData(data);
            lineChart.notifyDataSetChanged();
            lineChart.animateX(1000);
            //lineChart.animateY(1000);
            lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                          @Override
                                                          public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                              float y=e.getXIndex();
                                                              Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"S. Arabistan Riyali'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);
                                                              TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                                                              v.setTextColor(Color.BLACK);
                                                              toast.show();
                                                          }
                                                          @Override
                                                          public void onNothingSelected() {
                                                          }
                                                      }

            );
        }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizIrakdaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyIrak().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"Irak Dinarı'nın değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizIsraildaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyIsrail().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());
                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"İsrail Şekeli'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }
    public void CizIrandaily(String USD, final View view){

        ApiService.Factory.getInstance().getgraphdailyIran().enqueue(new Callback<List<ExampleGraph>>()

        {
            @Override
            public void onResponse(Call<List<ExampleGraph>> call, Response<List<ExampleGraph>> response) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
                final Float[] graph_value = new Float[4000];
                final String[] date1 = new String [4000];
                final String[] date2 = new String [4000];
                lineChart.setTouchEnabled(true);
                for (int i = 0; i < 50; i++) {
                    lineChart.zoomOut();
                }
                lineChart.setPinchZoom(true);
                List<ExampleGraph> jsondoviz1 = response.body();
                for (int i = 0; i < jsondoviz1.size(); i++) {

                    Float substr1 = jsondoviz1.get(i).getSelling();
                    graph_value[i] = substr1;

                    String ackwardDate = String.valueOf(jsondoviz1.get(i).getUpdateDate());

                    long dv = Long.valueOf(ackwardDate)*1000;// its need to be in milisecond
                    Date df = new java.util.Date(dv);
                    //   String v2 = new SimpleDateFormat("kk").format(df);
                    String v1 = new SimpleDateFormat("kk:hh").format(df);
                    //   String v3 = new SimpleDateFormat("mm").format(df);
                    //        date1[i]=v1;
/*int v4= Integer.parseInt(v2)+3;
if(v4>24){
    v4=v4-24;
}*/
                    //  date1[i]= String.valueOf(v4);
//date2[i]=String.valueOf(v3);
                    date1[i]=String.valueOf(v1);
                }
                ArrayList<Entry> entries = new ArrayList<>();
                for (int h = 0; h < jsondoviz1.size(); h++) {
                    Float deger213 = graph_value[h];
                    entries.add(new Entry(deger213, h));

                }
                LineDataSet dataset = new LineDataSet(entries, "# of Calls");

                ArrayList<String> labels = new ArrayList<String>();
                for (int s = 0; s < jsondoviz1.size(); s++)
                    labels.add(date1[s]);
                LineData data = new LineData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setCircleSize(2);
                dataset.setValueTextSize(0);
                dataset.setHighLightColor(Color.BLACK);

                dataset.setFillColor(Color.parseColor("#FF7F00"));
                dataset.setCircleColor(Color.parseColor("#cd661d"));
                dataset.setCircleColorHole(Color.parseColor("#cd661d"));
                lineChart.setData(data);
                lineChart.notifyDataSetChanged();
                lineChart.animateX(1000);
                //lineChart.animateY(1000);
                lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){
                                                              @Override
                                                              public void onValueSelected(Entry e, int dataGetValue, Highlight h) {
                                                                  float y=e.getXIndex();
                                                                  Toast toast = Toast.makeText(getActivity(),  date1[(int) y]+" saatinde "+"İran Riyali'nin değeri: "+graph_value[(int) y]+"₺",Toast.LENGTH_LONG);

                                                                  toast.show();
                                                              }
                                                              @Override
                                                              public void onNothingSelected() {
                                                              }
                                                          }

                );
            }
            @Override
            public void onFailure(Call<List<ExampleGraph>> call, Throwable t) {
            }
        });
    }








    protected void init(View view){
        radiogroup=(RadioGroup)view.findViewById(R.id.radioGroup_kurgrafik);
        radio_gunluk = (RadioButton)view.findViewById(R.id.radio_gunlukgrafik);
        radio_haftalik = (RadioButton)view.findViewById(R.id.radio_haftalikgrafik);
        radio_aylik=(RadioButton)view.findViewById(R.id.radio_aylikgrafik);
        radio_yillik = (RadioButton) view.findViewById(R.id.radio_yillikgrafik);
        spinner = (Spinner)view.findViewById(R.id.spinner_cevirici);

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("Entry selected", me.toString());
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }
}