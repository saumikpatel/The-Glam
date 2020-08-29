package com.example.theglam;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.FormatException;
import android.os.Build;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.functions.FirebaseFunctions;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    //private static final String TAG = ;

    /**
     * signup button
     */
    Button signup;
    /**
     * editetxt
     */
    EditText fname, lname, email, password;

    /**
     * firebase auth variable
     */
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup=findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        fname=(EditText) findViewById(R.id.fname);
        lname=(EditText) findViewById(R.id.lname);
        email=(EditText) findViewById(R.id.semail);
        password=(EditText) findViewById(R.id.spassword);


        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        /**
         * fignup button onclick listener
         */
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userfname = fname.getText().toString();
                String userlanme = lname.getText().toString();
                String useremail = email.getText().toString();
                String userpassword = password.getText().toString();


                if(useremail.isEmpty() || userfname.isEmpty()|| userlanme.isEmpty()|| userpassword.isEmpty()){
                    Toast.makeText(Signup.this, "Please fill the form", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(userpassword.length()<6){
                    Toast.makeText(Signup.this, "Password should be 6 character long", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(!isEmailValid(useremail)){
                    Toast.makeText(Signup.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    return;

                }
                final Map<String,Object> usermap=new HashMap<>();
                usermap.put("Email",useremail);
                usermap.put("Fname",userfname);
                usermap.put("Lname",userlanme);


                /**
                 * authentiction using firebase
                 */
                mAuth.createUserWithEmailAndPassword(useremail, userpassword).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            db.collection("User").document(mAuth.getCurrentUser().getUid()).set(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext().getApplicationContext(),"Register Success!",Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(getApplicationContext(), Home.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            });
                        }



                        else {
                            Toast.makeText(Signup.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();

                        }
                    }
                });



            }
        });


    }

    /**
     * email validity function
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}