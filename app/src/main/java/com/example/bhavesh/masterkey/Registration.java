package com.example.bhavesh.masterkey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    EditText passwordBox,cpassword,emailBox;
    Button createAcc;

    ProgressDialog progressDialog;

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //name = (EditText)findViewById(R.id.editText3);
        emailBox = (EditText)findViewById(R.id.editText4);
        //mobileno = (EditText)findViewById(R.id.editText5);
        passwordBox = (EditText)findViewById(R.id.editText6);
        cpassword = (EditText)findViewById(R.id.editText7);


        progressDialog = new ProgressDialog(this);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if getCurrentUser does not returns null
        /*if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), welcome.class));
        }*/

        createAcc = (Button)findViewById(R.id.button3);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* user = new User();
                user.setName(name.getText().toString());
                user.setEmail(email.getText().toString());
                if(password.getText().toString().equals(cpassword.getText().toString())) {
                    user.setPassword(password.getText().toString());
                }
                else {
                    Toast.makeText(Registration.this,"Password and confirm Password do not match",Toast.LENGTH_LONG).show();
                    password.setText("");
                    cpassword.setText("");
                }
                user.setMobileno(Long.parseLong(mobileno.getText().toString()));
                try {
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference();
                    databaseReference.child("User").push().setValue(user);

                    Intent intent = new Intent(Registration.this,MainActivity.class);
                    startActivity(intent);

                }
                catch(Exception exception)
                {
                    Log.d("TAG","FireBase",exception);
                }*/

                registerUser();
            }
        });

    }

    private void registerUser(){

        //getting email and password from edit texts
        String email = emailBox.getText().toString().trim();
        String password  = passwordBox.getText().toString().trim();
        String cpass  = cpassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(cpass)){
            Toast.makeText(this,"Please Enter Confirm Password",Toast.LENGTH_LONG).show();
            return;
        }

        if(password.equals(cpass)) {

            //if the email and password are not empty
            //displaying a progress dialog

            progressDialog.setMessage("Registering User Please Wait...");
            progressDialog.show();

            //creating a new user
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if (task.isSuccessful()) {
                                finish();
                                startActivity(new Intent(Registration.this, welcome.class));
                            } else {
                                //display some message here
                                Toast.makeText(Registration.this, "Registration Error", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
        }
        else{
            Toast.makeText(getApplicationContext(),"Password and Confirm Password Do Not Match",Toast.LENGTH_LONG).show();
        }
    }

}
