package iliada.dovizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.intrusoft.scatter.ChartData;
import com.intrusoft.scatter.PieChart;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Iliada on 29.11.2017.
 */

public class varliklarim_fragment_pie  extends Fragment {
    private static String TAG = "MainActivity";

    private float[] yData ;
    private String[] xData ;
    PieChart pieChart;
    TextView tv_piebilgilendirme,tv_pievarlikyok;
    RadioGroup radiogroup,radiogroupColor;
    RadioButton radio_yuzdelik,radio_miktar,radio_renkli,radio_tonlamali;
    Double toplam_varlik_miktari=0.0;

    public varliklarim_fragment_pie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_varliklarim_pie, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radiogroup=(RadioGroup)view.findViewById(R.id.radioGroup_pie);
        pieChart = (PieChart) view.findViewById(R.id.pie_chart);
        radio_yuzdelik = (RadioButton)view.findViewById(R.id.radio_yuzdelik);
        radio_miktar = (RadioButton)view.findViewById(R.id.radio_miktar);
        tv_piebilgilendirme=(TextView)view.findViewById(R.id.tv_piebilgilendirme);
        tv_pievarlikyok=(TextView)view.findViewById(R.id.tv_pievarlikyok);
        radiogroupColor=(RadioGroup)view.findViewById(R.id.radioGroup_piecolor);
        radio_renkli = (RadioButton)view.findViewById(R.id.radio_renkli);
        radio_tonlamali = (RadioButton)view.findViewById(R.id.radio_ton);





        yData=new float[20];
        xData=new String[20];
        final List<ChartData> data = new ArrayList<>();
        final List<ChartData> data1 = new ArrayList<>();

          int [] color = {Color.GREEN,Color.BLUE,Color.RED,Color.YELLOW,Color.CYAN,Color.MAGENTA,Color.GRAY};
//        data.add(new ChartData("First", 35));     //ARGS-> (display text, percentage)
//        data.add(new ChartData("Second", 25));
//        data.add(new ChartData("Third", 20));
//        data.add(new ChartData("Fourth", 10));
//        data.add(new ChartData("Five", 10));

//        data1.add(new ChartData("25%", 25, Color.WHITE, Color.parseColor("#33691E")));

        int index=0;
        int colorindex=0;
        for (int i= 0; i<Varliklarim_Fragment.varliklarim.size();i++){

            if(Varliklarim_Fragment.varliklarim.get(i).getDeger()>0) {
                if(colorindex >= 7) colorindex=0;
                DecimalFormat decimalFormat = new DecimalFormat("#");
                yData[index] = Float.parseFloat(decimalFormat.format(Varliklarim_Fragment.varliklarim.get(i).getMiktar()));
                xData[index] = Varliklarim_Fragment.varliklarim.get(i).getTur();

                //YÜZDELİK OLARAK GÖSTERMEK İCİN BURDA DATA.ADD YAPMADAN ÖNCE Ydata[İNDEX] degiskeninin varliklarim (Bütün ydata dizisi)
                //arasından % 'lik degerini bulmamız gerekiyor.
                data.add(new ChartData("%"+String.valueOf( yuzdehesapla(Varliklarim_Fragment.varliklarim.get(i).getMiktar()))+"\n"+xData[index],
                        yuzdehesapla(Varliklarim_Fragment.varliklarim.get(i).getMiktar())
                ));


//                data.add(new ChartData(String.valueOf(yData[index])+"\n"+xData[index],  yData[index]));
                data1.add(new ChartData(String.valueOf(yData[index])+"\n"+xData[index],  yData[index]));
                colorindex++;
                index++;
            }
        }
        if(index == 0){

            tv_pievarlikyok.setVisibility(View.VISIBLE);
            tv_piebilgilendirme.setVisibility(View.INVISIBLE);
            pieChart.setVisibility(View.GONE);
            radiogroup.setVisibility(View.INVISIBLE);
           radiogroupColor.setVisibility(View.GONE);

        }
        else {

//            radiogroupColor.setVisibility(View.VISIBLE);
            radiogroup.setVisibility(View.VISIBLE);
            tv_pievarlikyok.setVisibility(View.GONE);
            tv_piebilgilendirme.setVisibility(View.VISIBLE);
            pieChart.setVisibility(View.VISIBLE);
            pieChart.setChartData(data);

            pieChart.partitionWithPercent(true);
        }




        radiogroupColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(radio_renkli.isChecked())
                {
//                    pieChart.setChartData(data1);
                }
                else if(radio_tonlamali.isChecked())
                {

//                    pieChart.setChartData(data);

                }
            }
        });
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(radio_miktar.isChecked())
                {

                    pieChart.setChartData(data1);

                    pieChart.partitionWithPercent(false);

                }
                else if(radio_yuzdelik.isChecked())
                {
                    pieChart.animateChart();
                    pieChart.setChartData(data);
                    pieChart.partitionWithPercent(true);


                }
            }
        });


    }

    //HATALI...
    public float yuzdehesapla(Double miktar){

        float yuzdelik_degeri;
        for (int i= 0; i<Varliklarim_Fragment.varliklarim.size();i++) {

            if (Varliklarim_Fragment.varliklarim.get(i).getDeger() > 0) {
                toplam_varlik_miktari += Varliklarim_Fragment.varliklarim.get(i).getMiktar();

            }
        }
                System.out.println(""+toplam_varlik_miktari);
                Double varlik_miktari= miktar;
                      yuzdelik_degeri =  Float.parseFloat(String.valueOf (( 100 * varlik_miktari) / toplam_varlik_miktari ));
                      yuzdelik_degeri = (float) (Math.floor(yuzdelik_degeri * 100) / 100);

                      toplam_varlik_miktari=0.0;
                return Math.round(yuzdelik_degeri);




    }





}