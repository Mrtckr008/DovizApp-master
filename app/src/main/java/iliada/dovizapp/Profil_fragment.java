package iliada.dovizapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profil_fragment extends Fragment {
    private Button btnChangeEmail, btnChangePassword, btnSendResetEmail,
            changeEmail, changePassword, sendEmail;
    private TextView tv_mail;
    private EditText oldEmail, newEmail, newPassword;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    public Profil_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profil Bilgilerim");


        init(view);


        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(getActivity(), LoginActivity.class));

//                    finish();
                }
            }
        };
        oldEmail.setVisibility(View.GONE);
        newEmail.setVisibility(View.GONE);
        newPassword.setVisibility(View.GONE);
        changeEmail.setVisibility(View.GONE);
        changePassword.setVisibility(View.GONE);
        sendEmail.setVisibility(View.GONE);
        tv_mail.setText(user.getEmail());
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
        btnChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldEmail.setVisibility(View.GONE);
                anim(newEmail);
                newEmail.setVisibility(View.VISIBLE);
                newPassword.setVisibility(View.GONE);
                anim(changeEmail);
                changeEmail.setVisibility(View.VISIBLE);
                changePassword.setVisibility(View.GONE);
                sendEmail.setVisibility(View.GONE);
            }
        });
        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (user != null && !newEmail.getText().toString().trim().equals("")) {
                    user.updateEmail(newEmail.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "E-mail Adresiniz Güncellendi, Lütfen Yeni Mailiniz İle Giriş Yapınız..", Toast.LENGTH_LONG).show();
                                        auth.signOut();
                                        Intent giris = new Intent(getActivity(), LoginActivity.class);
                                        startActivity(giris);
                                        getActivity().finish();
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(getActivity(), "Mail Güncelleme İşlemi Başarısız!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                } else if (newEmail.getText().toString().trim().equals("")) {
                    newEmail.setError("Mailinizi Giriniz");
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldEmail.setVisibility(View.GONE);
                newEmail.setVisibility(View.GONE);
                anim(newPassword);
                newPassword.setVisibility(View.VISIBLE);
                changeEmail.setVisibility(View.GONE);
                anim(changePassword);
                changePassword.setVisibility(View.VISIBLE);
                sendEmail.setVisibility(View.GONE);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (user != null && !newPassword.getText().toString().trim().equals("")) {
                    if (newPassword.getText().toString().trim().length() < 6) {
                        newPassword.setError("Password too short, enter minimum 6 characters");
                        progressBar.setVisibility(View.GONE);
                    } else {
                        user.updatePassword(newPassword.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getActivity(), "Sifreniz Güncellendi, Lütfen Yeni Sifreniz İle Giriş Yapınız..", Toast.LENGTH_LONG).show();
                                            auth.signOut();
                                            Intent giris = new Intent(getActivity(), LoginActivity.class);
                                            startActivity(giris);
                                            getActivity().finish();
                                            progressBar.setVisibility(View.GONE);
                                        } else {
                                            Toast.makeText(getActivity(), "Sifre Güncelleme İşlemi Başarısız!", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                    }
                } else if (newPassword.getText().toString().trim().equals("")) {
                    newPassword.setError("Sifrenizi Giriniz..");
                    progressBar.setVisibility(View.GONE);

                }
            }
        });

        btnSendResetEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               anim(oldEmail);
                oldEmail.setVisibility(View.VISIBLE);
                newEmail.setVisibility(View.GONE);
                newPassword.setVisibility(View.GONE);
                changeEmail.setVisibility(View.GONE);
                changePassword.setVisibility(View.GONE);
              anim(sendEmail);
                sendEmail.setVisibility(View.VISIBLE);
            }
        });

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                if (!oldEmail.getText().toString().trim().equals("")) {
                    auth.sendPasswordResetEmail(oldEmail.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        auth.sendPasswordResetEmail(oldEmail.getText().toString());
                                        Toast.makeText(getActivity(), "Sifre Sıfırlama Linki Gönderildi, Lütfen Mailinizi Kontrol Ediniz..", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(getActivity(), "Sifre Sıfırlama İşlemi Başarısız", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                } else {
                    oldEmail.setError("Enter email");
                    progressBar.setVisibility(View.GONE);
                }
            }
        });



    }

    //sign out method
    public void signOut() {
        auth.signOut();
    }


    protected void init(View v){
        btnChangeEmail = (Button) v.findViewById(R.id.change_email_button);
        btnChangePassword = (Button) v.findViewById(R.id.change_password_button);
        btnSendResetEmail = (Button) v.findViewById(R.id.sending_pass_reset_button);
        changeEmail = (Button) v.findViewById(R.id.changeEmail);
        changePassword = (Button) v.findViewById(R.id.changePass);
        sendEmail = (Button) v.findViewById(R.id.send);
        tv_mail=(TextView)v.findViewById(R.id.tv_mailim);
        oldEmail = (EditText) v.findViewById(R.id.old_email);
        newEmail = (EditText) v.findViewById(R.id.new_email);
        newPassword = (EditText) v.findViewById(R.id.newPassword);

        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);

    }
    protected void anim(View v){
        if(v instanceof EditText) {
            YoYo.with(Techniques.RollIn)
                    .duration(2000)
                    .playOn(v);
        }
        else {
            YoYo.with(Techniques.BounceIn)
                    .duration(2000)
                    .playOn(v);
        }

    }
}
