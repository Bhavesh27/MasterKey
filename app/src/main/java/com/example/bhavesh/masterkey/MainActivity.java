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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText emailbox,passbox;
    Button signup,signin;

    ProgressDialog progressDialog;

    //User user;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //final List<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), welcome.class));
        }

        progressDialog = new ProgressDialog(this);

        emailbox = (EditText)findViewById(R.id.editText);
        passbox = (EditText)findViewById(R.id.editText2);

        signin = (Button)findViewById(R.id.button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                databaseReference.child("User").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                        for (DataSnapshot child : children) {

                            user = child.getValue(User.class);
                            //users.add(user);

                            if (user.getEmail().equals(emailbox.getText().toString()) && user.getPassword().equals(passbox.getText().toString())){
                                //login();
                            }
                        }
                    }*/

                    /*private void login() {

                        if (user.getEmail().equals(emailbox.getText().toString()) && user.getPassword().equals(passbox.getText().toString())){

                            Intent intent = new Intent(MainActivity.this,welcome.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("name",user.getName());
                            bundle.putString("email",user.getEmail());
                            bundle.putLong("mobileno",user.getMobileno());
                            bundle.putString("password",user.getPassword());
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }
                        else  {

                            Toast.makeText(MainActivity.this,"Invalid Creditials",Toast.LENGTH_LONG).show();
                            emailbox.setText("");
                            passbox.setText("");

                        }

                    }*/

                    /*@Override
                    public void onCancelled(DatabaseError databaseError) {

                        Log.w("OnCancellation", "loadPost:onCancelled", databaseError.toException());

                    }
                });*/

                userLogin();

            }
        });

        signup = (Button)findViewById(R.id.button2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        });

    }

    private void userLogin(){
        String email = emailbox.getText().toString().trim();
        String password  = passbox.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Logging In Please Wait...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), welcome.class));
                        }
                    }
                });

    }

}
