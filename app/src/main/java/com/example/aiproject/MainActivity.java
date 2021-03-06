package com.example.aiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private MaterialButton b1;
    private TextView   signintext1;
    private TextInputEditText email1,password1,repassword1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intial1();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create1();
            }
        });

        signintext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Signin12.class);
                overridePendingTransition(R.anim.push_right_in,R.anim.push_left_in);
                startActivity(i);
                finish();
            }
        });

    }



    private void create1() {
        String emai= String.valueOf(email1.getText());
        String pass1= String.valueOf(password1.getText());
        String pass2= String.valueOf(repassword1.getText());

        if(emai.isEmpty()){
            email1.setError("PLease enter email");
            email1.requestFocus();
            return ;

        } else if(pass1.isEmpty()){
            password1.setError("please enter password");
            password1.requestFocus();
            return ;
        }else if(pass2.isEmpty()){
            repassword1.setError("please retype a password");
            repassword1.requestFocus();
            return ;
        }
        email1.setError(null);
        password1.setError(null);
        repassword1.setError(null);

        if(!(android.util.Patterns.EMAIL_ADDRESS.matcher(emai).matches())){
            email1.setError("Please enter right email address ");
            email1.requestFocus();
            return;
        }else if(!pass1.equals(pass2)){
           Toast t1= Toast.makeText(this, "password and retype passord should be same..", Toast.LENGTH_SHORT);
            t1.setGravity(Gravity.CENTER, 0, 0);
            t1.show();
            repassword1.setError("password did not match ");
            repassword1.requestFocus();
            return;
        }

        repassword1.setError(null);
        email1.setError(null);


        //here we enter a data

        mAuth.createUserWithEmailAndPassword(emai, pass1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("succes", "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(getApplicationContext(), Signin12.class);
                            overridePendingTransition(R.anim.push_right_in,R.anim.push_left_in);
                            startActivity(i);
                            finish();
//

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("erro105", "createUserWithEmail:failure", task.getException());

                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });


    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //TODO create a user already register then what to do

        }
    }

    private void intial1() {
        mAuth=FirebaseAuth.getInstance();

        b1=findViewById(R.id.signup1);
        signintext1=findViewById(R.id.text12sig);
        email1=findViewById(R.id.emailaddr1);
        password1=findViewById(R.id.pass1);
        repassword1=findViewById(R.id.pass2);
    }
}
