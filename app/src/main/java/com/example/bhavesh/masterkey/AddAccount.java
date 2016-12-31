package com.example.bhavesh.masterkey;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddAccount extends AppCompatActivity {

    EditText username,email,password,vendorname;
    Button save,logout;
    Account account;
    List<Account> accounts = new ArrayList<>();
    //User user;

    AlertDialog.Builder adb;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //defining a database reference
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        //getting the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, MainActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        username = (EditText)findViewById(R.id.editText8);
        email = (EditText)findViewById(R.id.editText9);
        password = (EditText)findViewById(R.id.editText10);
        vendorname = (EditText)findViewById(R.id.editText11);

        logout = (Button)findViewById(R.id.button7);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logging out the user
                firebaseAuth.signOut();
                //closing activity
                finish();
                //starting login activity
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        save = (Button)findViewById(R.id.button6);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                account.setUsername(username.getText().toString());
                account.setEmail(email.getText().toString());
                account.setPassword(password.getText().toString());
                account.setVendorname(vendorname.getText().toString());
                //user.setAccounts();*/

                saveAccount();

            }
        });
    }

    private void saveAccount(){



        String uname = username.getText().toString();
        String emailid = email.getText().toString();
        String pass = password.getText().toString();
        String vname = vendorname.getText().toString();

        if(TextUtils.isEmpty(uname)){
            Toast.makeText(this,"Please Enter UserName",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(vname)){
            Toast.makeText(this,"Please Enter website/vendor/app",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(emailid)){
            Toast.makeText(this,"Please Enter email Id",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please Enter Passward",Toast.LENGTH_LONG).show();
            return;
        }

        //adding account
        account = new Account(uname,pass,emailid,vname);

        //getting the current logged in user
        //FirebaseUser user = firebaseAuth.getCurrentUser();

        //saving data to firebase database
        /*
        * first we are creating a new child in firebase with the2
        * unique id of logged in user
        * and then for that user under the unique id we are saving data
        * for saving data we are using setvalue method this method takes a normal java object
        * */
        //databaseReference.child(user.getUid()).setValue(account);
        accounts.add(account);

        //displaying a success toast
        Toast.makeText(getApplicationContext(), "Account Saved...", Toast.LENGTH_LONG).show();

        adb = new AlertDialog.Builder(AddAccount.this);
        adb.setTitle("Confirmation");
        adb.setMessage("Do you want to add more...").setPositiveButton("Yes",new Yes()).setNegativeButton("No",new No());
        adb.show();
    }

    public class Yes implements android.content.DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
           // MainActivity.this.finish();

            dialog.cancel();

            username.setText("");
            email.setText("");
            password.setText("");
            vendorname.setText("");

            Toast.makeText(getApplicationContext(),"Enter more Details",Toast.LENGTH_SHORT).show();

            saveAccount();
        }
    }

    public class No implements android.content.DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).push().setValue(accounts);
            dialog.cancel();
            firebaseAuth.signOut();
            finish();
            //starting login activity
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

}
