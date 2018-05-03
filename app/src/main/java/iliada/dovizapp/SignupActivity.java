package iliada.dovizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword,inputbakiye;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    FirebaseDatabase db;
    Double TL_bakiye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        db=FirebaseDatabase.getInstance();

        auth = FirebaseAuth.getInstance();
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputbakiye = (EditText) findViewById(R.id.bakiye);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Email Adresinizi Giriniz!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Sifrenizi Giriniz!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Sifre En Az 6 Karakterden Oluşmalı!", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {

                    if(Integer.valueOf(inputbakiye.getText().toString()) > 0){
                        TL_bakiye = (double)Integer.valueOf(inputbakiye.getText().toString())+0.0;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Başlangıç Bakiyenizi Giriniz!", Toast.LENGTH_SHORT).show();
                        return;

                    }

                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Başlangıç Bakiyenizi Giriniz!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())

                                varlikyukle();

                                progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "İşlem Başarısız, Geçerli Bir Mail Adresi Giriniz!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
    public void varlikyukle() {


        Varliklarim_Fragment.TL.setDeger(TL_bakiye);
        DatabaseReference dbRefKeyliTL = db.getReference(auth.getCurrentUser().getUid() + "/TLDegeri" );
        dbRefKeyliTL.setValue(Varliklarim_Fragment.TL.getDeger());
        Toast.makeText(SignupActivity.this, "Kullanıcı Kaydı Başarılı, Hoşgeldiniz.. " , Toast.LENGTH_SHORT).show();

        startActivity(new Intent(SignupActivity.this, MainActivity.class));

        finish();





//        Varliklarim_Fragment.TL.setDeger(1000.0);
//        DatabaseReference dbRefKeyliTL = db.getReference(auth.getCurrentUser().getUid() + "/TLDegeri" );
//        dbRefKeyliTL.setValue(Varliklarim_Fragment.TL.getDeger());
    }




}