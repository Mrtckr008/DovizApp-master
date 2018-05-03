package iliada.dovizapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth auth;
    public static String userid;
    public static FirebaseUser user;
    FirebaseDatabase db;
    Boolean ApphasRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        db=FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        auth = FirebaseAuth.getInstance();
        userid = user.getUid();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Uygulama back tusu ile arka plana atildiginda veritabanından cekme işlemi tekrar gerceklesir.
        // Bu yüzden veritabanı cekme isleminden sonra Varliklarim dizisini sıfırlamak gerekir..
        if(ApphasRun == null) ApphasRun=false;

        if(!ApphasRun) {
            Varliklarim_Fragment.varliklarim.clear();
            Satim_Islemi_Fragment.satim_islemi.clear();
            Satim_Islemi_Fragment.butun_islemler.clear();
            Alim_Islemi_Fragment.alim_islemi.clear();
            ApphasRun = true;
        }
        varliklari_al_db();
        islemleri_al_db();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        displaySelectedScreen(R.id.nav_guncel_kur_degerleri); //// GİRİŞ SAYFASI İÇİN
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Fragment fragment=new Guncel_Kurlar_Fragment();

        if(fragment instanceof Guncel_Kurlar_Fragment)
        {
            drawer.openDrawer(GravityCompat.START);
        }
        if(fragment instanceof Varliklarim_Fragment)
        {
            drawer.openDrawer(GravityCompat.START);
        }
        if(fragment instanceof Satim_Islemi_Fragment)
        {
            drawer.openDrawer(GravityCompat.START);
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);

            super.onBackPressed();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

//       getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int id) {
        Fragment fragment= null;

        if (id == R.id.nav_guncel_kur_degerleri) {
            fragment=new Guncel_Kurlar_Fragment();
        } else if (id == R.id.nav_varliklarim) {
//            fragment=new Varliklarim_Fragment();
            Intent varliklarimactivity =new Intent(this,Varliklarim_Activity.class);
            startActivity(varliklarimactivity);
            finish();

        } else if (id == R.id.nav_alimislemi) {
            fragment=new Alim_Islemi_Fragment();
        } else if (id == R.id.nav_satimislemi) {
            fragment=new Satim_Islemi_Fragment();
        }
        else if (id == R.id.nav_hesapozeti) {
            Intent hesapozeti =new Intent(this,Tab_Activity_HesapOzeti.class);
            startActivity(hesapozeti);
            finish();
        }
        else if (id == R.id.nav_dovizcevirici) {
            fragment= new Doviz_Cevirici_fragment();
        }
        else if (id == R.id.nav_profil) {
            fragment= new Profil_fragment();

        }

        else if (id == R.id.nav_kurgrafik) {
            fragment= new Kur_Grafik_fragment();

        }

        else if (id == R.id.nav_chat) {

            Intent chat =new Intent(this,Activity_chat.class);
            startActivity(chat);

        }
        else if (id == R.id.nav_cikis) {

            create_dialogforCikis().show();

        }
        else if (id == R.id.nav_uygulamahakkında) {

            fragment= new Uygulama_hakkinda_fragment();

        }
        else if(id == R.id.nav_hesapsil){
            create_dialogforHesapsil().show();
        }
        if(fragment != null){
            FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.content_main,fragment);
            fragmentTransaction.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);

        return true;
    }


    public Dialog create_dialogforCikis(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Uygulamadan Çıkmak Üzeresiniz! Çıkış Yapmak İstediğinize Emin Misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, "Oturum Kapatma İşlemi Başarılı!", Toast.LENGTH_LONG).show();
                        auth.signOut();
                        Varliklarim_Fragment.varliklarim.clear();
                        Satim_Islemi_Fragment.satim_islemi.clear();
                        Satim_Islemi_Fragment.butun_islemler.clear();
                        Alim_Islemi_Fragment.alim_islemi.clear();
                        Intent giris = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(giris);
                        finish();
                    }
                })
                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }
    public Dialog create_dialogforHesapsil(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Hesabınız Kalıcı Olarak Silinecektir.İşlemi Gerçekleştirmek İstediğinize Emin Misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (user != null) {
                            user.delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(MainActivity.this, "Hesabınız Kalıcı Olarak Silinmiştir. Yeni Bir Hesap Oluşturmak İçin İlgili Alanları Doldurunuz..", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(MainActivity.this, SignupActivity.class));
                                                finish();
                                            } else {
                                                Toast.makeText(MainActivity.this, "Hesap Kaldırma İşlemi Başarısız, Lütfen Daha Sonra Tekrar Deneyiniz..", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                        }
                    }
                })
                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }

    private void varliklari_al_db(){

        DatabaseReference oku =db.getReference(MainActivity.userid+"/Varliklar");
        oku.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> keys = dataSnapshot.getChildren();
                for(DataSnapshot key : keys){
                    Varliklar varlik = key.getValue(Varliklar.class);

                    Varliklarim_Fragment.varliklarim.add(varlik);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference okutl =db.getReference(MainActivity.userid+"/TLDegeri");
        okutl.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                try {

                    Double tldegerim = (Double) dataSnapshot.getValue();
                    Varliklarim_Fragment.TL.setDeger(tldegerim);
                    if(tldegerim == null){
                        Long tldegerim_int = (Long) dataSnapshot.getValue();
                        Double tldegerim2=tldegerim_int+0.0;
                        Varliklarim_Fragment.TL.setDeger(tldegerim2);
                    }

                }
                catch (Exception e){

                    Long tldegerim_int = (Long) dataSnapshot.getValue();
                    Double tldegerim=tldegerim_int+0.0;
                    Varliklarim_Fragment.TL.setDeger(tldegerim);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void islemleri_al_db() {

        DatabaseReference oku_butunislemler = db.getReference(MainActivity.userid + "/Butun Islemler");
        oku_butunislemler.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> keys = dataSnapshot.getChildren();
                for (DataSnapshot key : keys) {
                    Islemler islem = key.getValue(Islemler.class);

                    Satim_Islemi_Fragment.butun_islemler.add(islem);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        DatabaseReference oku_alimislemleri =db.getReference(MainActivity.userid+"/Alim Islemleri");
        oku_alimislemleri.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> keys = dataSnapshot.getChildren();
                for(DataSnapshot key : keys){
                    Islemler islem = key.getValue(Islemler.class);

                    Alim_Islemi_Fragment.alim_islemi.add(islem);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference oku_satimislemleri =db.getReference(MainActivity.userid+"/Satis Islemleri");
        oku_satimislemleri.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> keys = dataSnapshot.getChildren();
                for(DataSnapshot key : keys){
                    Islemler islem = key.getValue(Islemler.class);

                    Satim_Islemi_Fragment.satim_islemi.add(islem);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
