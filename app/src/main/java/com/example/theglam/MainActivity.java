package com.example.theglam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    Button login;
    TextView signup;
    private FirebaseAuth auth;
    private FirebaseUser curUser;
    EditText email,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login =(Button)findViewById(R.id.login);
        signup =(TextView) findViewById(R.id.TsignUp);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String lemail=email.getText().toString();
              String  lpass=pass.getText().toString();
                if(lemail.isEmpty() || lpass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill the form", Toast.LENGTH_SHORT).show();
                    return;
                }


                    auth.signInWithEmailAndPassword(lemail,lpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                curUser=auth.getCurrentUser();
                                Toast.makeText(getApplicationContext(),"Login Success!",Toast.LENGTH_LONG).show();

                                Intent i = new Intent(getApplicationContext(),Home.class);
                                startActivity(i);

                            }else{
                                try{
                                    throw task.getException();
                                }catch (FirebaseAuthInvalidUserException e){
                                    Toast.makeText(getApplicationContext(),"Email not exist!",Toast.LENGTH_LONG).show();
                                    email.getText().clear();
                                    pass.getText().clear();
                                    email.setError("Email not exist!");
                                    email.requestFocus();
                                    return;
                                }catch (FirebaseAuthInvalidCredentialsException e){
                                    Toast.makeText(getApplicationContext(),"Wrong Password!",Toast.LENGTH_LONG).show();
                                    pass.getText().clear();
                                    pass.setError("Wrong Password!");
                                    pass.requestFocus();
                                    return;
                                }catch (Exception e ){
                                    Toast.makeText(getApplicationContext(),"Login Failed!",Toast.LENGTH_LONG).show();
                                }
                            }


                        }
                    });







            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Signup.class);
                startActivity(i);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        curUser=auth.getCurrentUser();
        if(curUser!=null){
            Intent i = new Intent(getApplicationContext(),Home.class);
            startActivity(i);

        }
    }
}