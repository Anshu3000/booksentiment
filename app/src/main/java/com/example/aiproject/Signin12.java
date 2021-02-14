package com.example.aiproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Signin12 extends AppCompatActivity {

    private TextView entertextt1;
    private TextInputEditText email1,password1;
    private MaterialButton b1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin12);

        intit2();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create1();
            }
        });


        entertextt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                overridePendingTransition(R.anim.push_right_in,R.anim.push_left_in);
                startActivity(i);
                finish();
            }
        });

    }

    private void create1() {

        String emai= String.valueOf(email1.getText());
        String pass1= String.valueOf(password1.getText());


        if(emai.isEmpty()){
            email1.setError("PLease enter email");
            email1.requestFocus();
            return ;

        } else if(pass1.isEmpty()){
            password1.setError("please enter password");
            password1.requestFocus();
            return ;
        }
        email1.setError(null);
        password1.setError(null);


        if(!(android.util.Patterns.EMAIL_ADDRESS.matcher(emai).matches())){
            email1.setError("Please enter right email address ");
            return;
        }

        email1.setError(null);

        //user signin...

        mAuth.signInWithEmailAndPassword(emai, pass1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("succ1", "signInWithEmail:success");
                          //  FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Authentication succesfull..",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("error1", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            // ...
                        }

                        // ...
                    }
                });



    }


    private void intit2() {
        mAuth = FirebaseAuth.getInstance();
        entertextt1=findViewById(R.id.entertext12);
        email1=findViewById(R.id.enteremailaddr1);
        password1=findViewById(R.id.enterpassword1);
        b1=findViewById(R.id.signin1);
    }
}
