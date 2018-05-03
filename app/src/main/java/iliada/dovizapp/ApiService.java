package iliada.dovizapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public interface ApiService {
    String Base_Url="http://www.doviz.com/";  //for emulator 10.0.2.2

    @GET("api/v1/currencies/all/latest")
    Call<List<ExampleDoviz>> getdoviz();
/*YILLIKLAR*/
    @GET("api/v1/currencies/USD/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyUsd();

    @GET("api/v1/currencies/EUR/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyEuro();

    @GET("api/v1/currencies/GBP/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlySterlin();

    @GET("api/v1/currencies/CHF/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyIsvFrangi();

    @GET("api/v1/currencies/CAD/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyKanDoları();

    @GET("api/v1/currencies/RUB/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyRusRub();

    @GET("api/v1/currencies/AED/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyBae();

    @GET("api/v1/currencies/AUD/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyAvsDoları();

    @GET("api/v1/currencies/DKK/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyDanKron();

    @GET("api/v1/currencies/SEK/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyIsvecKron();

    @GET("api/v1/currencies/NOK/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyNorvKron();

    @GET("api/v1/currencies/JPY/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyJapYeni();

    @GET("api/v1/currencies/KWD/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyKuvDinarı();

    @GET("api/v1/currencies/ZAR/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyGunAfrRan();

    @GET("api/v1/currencies/BHD/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyBahDinar();

    @GET("api/v1/currencies/LYD/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyLibDinar();

    @GET("api/v1/currencies/SAR/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlySuudiRiyal();

    @GET("api/v1/currencies/IQD/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyIrakDinar();

    @GET("api/v1/currencies/ILS/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyIsrailSek();

    @GET("api/v1/currencies/IRR/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyIranRiyal();

    @GET("api/v1/currencies/INR/archive?start=2017-01-01&end=2017-12-07")
    Call<List<ExampleGraph>> getgraphyearlyHintRupi();

    /*AYLIKLAR*/

    @GET("api/v1/currencies/USD/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyUsd();

    @GET("api/v1/currencies/EUR/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyEuro();

    @GET("api/v1/currencies/GBP/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlySter();

    @GET("api/v1/currencies/CHF/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyIsvicre();

    @GET("api/v1/currencies/CAD/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyKanada();

    @GET("api/v1/currencies/RUB/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyRus();

    @GET("api/v1/currencies/AED/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyBAE();

    @GET("api/v1/currencies/AUD/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyAvustralya();

    @GET("api/v1/currencies/DKK/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyDank();

    @GET("api/v1/currencies/SEK/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyIsvec();

    @GET("api/v1/currencies/NOK/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyNorv();

    @GET("api/v1/currencies/JPY/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyJap();

    @GET("api/v1/currencies/KWD/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyKuveyt();

    @GET("api/v1/currencies/ZAR/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyGAfri();

    @GET("api/v1/currencies/BHD/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyBahr();

    @GET("api/v1/currencies/LYD/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyLib();

    @GET("api/v1/currencies/SAR/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlySuudi();

    @GET("api/v1/currencies/IQD/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyIrak();

    @GET("api/v1/currencies/ILS/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyIsrail();

    @GET("api/v1/currencies/IRR/archive?start=2017-11-05&end=2017-12-05")
    Call<List<ExampleGraph>> getgraphmonthlyIran();


/*HAFTALIKLAR*/

    @GET("api/v1/currencies/USD/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgrapweeklyUsd();

    @GET("api/v1/currencies/EUR/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklylyEuro();

    @GET("api/v1/currencies/GBP/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklySter();

    @GET("api/v1/currencies/CHF/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyIsvicre();

    @GET("api/v1/currencies/CAD/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyKanada();

    @GET("api/v1/currencies/RUB/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyRus();

    @GET("api/v1/currencies/AED/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyBAE();

    @GET("api/v1/currencies/AUD/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyAvustralya();

    @GET("api/v1/currencies/DKK/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyDank();

    @GET("api/v1/currencies/SEK/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyIsvec();

    @GET("api/v1/currencies/NOK/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyNorv();

    @GET("api/v1/currencies/JPY/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyJap();

    @GET("api/v1/currencies/KWD/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyKuveyt();

    @GET("api/v1/currencies/ZAR/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyGAfri();

    @GET("api/v1/currencies/BHD/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyBahr();

    @GET("api/v1/currencies/LYD/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyLib();

    @GET("api/v1/currencies/SAR/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklySuudi();

    @GET("api/v1/currencies/IQD/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyIrak();

    @GET("api/v1/currencies/ILS/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyIsrail();

    @GET("api/v1/currencies/IRR/archive?start=2017-12-01&end=2017-12-08")
    Call<List<ExampleGraph>> getgraphweeklyIran();

    /*GUNLUKLER*/
    @GET("api/v1/currencies/USD/daily")
    Call<List<ExampleGraph>> getgrapdailyUsd();

    @GET("api/v1/currencies/EUR/daily")
    Call<List<ExampleGraph>> getgraphdailyEuro();

    @GET("api/v1/currencies/GBP/daily")
    Call<List<ExampleGraph>> getgraphdailySter();

    @GET("api/v1/currencies/CHF/daily")
    Call<List<ExampleGraph>> getgraphdailyIsvicre();

    @GET("api/v1/currencies/CAD/daily")
    Call<List<ExampleGraph>> getgraphdailyKanada();

    @GET("api/v1/currencies/RUB/daily")
    Call<List<ExampleGraph>> getgraphdailyRus();

    @GET("api/v1/currencies/AED/daily")
    Call<List<ExampleGraph>> getgraphdailyBAE();

    @GET("api/v1/currencies/AUD/daily")
    Call<List<ExampleGraph>> getgraphdailyAvustralya();

    @GET("api/v1/currencies/DKK/daily")
    Call<List<ExampleGraph>> getgraphdailyDank();

    @GET("api/v1/currencies/SEK/daily")
    Call<List<ExampleGraph>> getgraphdailyIsvec();

    @GET("api/v1/currencies/NOK/daily")
    Call<List<ExampleGraph>> getgraphdailyNorv();

    @GET("api/v1/currencies/JPY/daily")
    Call<List<ExampleGraph>> getgraphdailyJap();

    @GET("api/v1/currencies/KWD/daily")
    Call<List<ExampleGraph>> getgraphdailyKuveyt();

    @GET("api/v1/currencies/ZAR/daily")
    Call<List<ExampleGraph>> getgraphdailyGAfri();

    @GET("api/v1/currencies/BHD/daily")
    Call<List<ExampleGraph>> getgraphdailyBahr();

    @GET("api/v1/currencies/LYD/daily")
    Call<List<ExampleGraph>> getgraphdailyLib();

    @GET("api/v1/currencies/SAR/daily")
    Call<List<ExampleGraph>> getgraphdailySuudi();

    @GET("api/v1/currencies/IQD/daily")
    Call<List<ExampleGraph>> getgraphdailyIrak();

    @GET("api/v1/currencies/ILS/daily")
    Call<List<ExampleGraph>> getgraphdailyIsrail();

    @GET("api/v1/currencies/IRR/daily")
    Call<List<ExampleGraph>> getgraphdailyIran();















    @GET("api/v1/currencies/USD/archive?start=2017-11-19&end=2017-11-29")
    Call<List<ExampleGraph>> getgraphweeklyUsd();

    @GET("api/v1/currencies/USD/daily")
    Call<List<ExampleGraph>> getgraphdailyUsd();
//    @GET("api/v1/golds/all/latest")
  //  Call<List<ExampleAltin>> getaltin();



    class Factory{

        private static ApiService service;

        public static ApiService getInstance(){
            if (service==null){

                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Base_Url).build();

                service=retrofit.create(ApiService.class);
                return service;


            }else{
                return service;
            }


        }

    }



}