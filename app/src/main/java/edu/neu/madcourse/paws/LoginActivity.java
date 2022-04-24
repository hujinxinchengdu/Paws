package edu.neu.madcourse.paws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.neu.madcourse.paws.R;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LOG_IN_TAG";
    private String email;
    private String password;
    private FirebaseAuth myauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText email_input = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText password_input = (EditText) findViewById(R.id.editTextTextPassword);
        Button sign_up = (Button) findViewById(R.id.sign_up_button);
        myauth = FirebaseAuth.getInstance();
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = email_input.getText().toString();
                password = password_input.getText().toString();
                createAccount(email,password);
            }
        });

        Button sign_in = (Button) findViewById(R.id.login_button);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = email_input.getText().toString();
                password = password_input.getText().toString();
                signIn(email,password);
            }
        });
    }

    public void createAccount(String email,String password){
        myauth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.e(TAG,"create user success");
                            openSettingPage();
                        }else{
                            Log.e(TAG,task.getException().toString());
                            Toast.makeText(getApplicationContext(),"authentication failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signIn(String email, String password){
        myauth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.e(TAG,"sign in success");
                            openNavPage();
                        }else{
                            Log.e(TAG,task.getException().toString());
                            Toast.makeText(getApplicationContext(),"auth failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void openSettingPage(){
        Intent intent = new Intent(this,settingActivity.class);
        startActivity(intent);
    }

    public void openNavPage(){
        Intent intent = new Intent(this, NavActivity.class);
        startActivity(intent);
    }
}