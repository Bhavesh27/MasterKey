package com.example.bhavesh.masterkey;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.attr.name;

public class welcome extends AppCompatActivity {

    TextView welcomeMsg;
    EditText firstname,lastname,mobile;
    Button addAccount;

    //ProgressDialog progressDialog;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //defining a database reference
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //getting current user
        //FirebaseUser user = firebaseAuth.getCurrentUser();
       /* if(user.getUid().contains("firstname")){
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(getApplicationContext(), AddAccount.class));
        }*/

        //getting the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        firstname = (EditText)findViewById(R.id.editText3);
        lastname = (EditText)findViewById(R.id.editText5);
        mobile = (EditText)findViewById(R.id.editText12);

       // progressDialog = new ProgressDialog(this);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        /*if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, MainActivity.class));
        }*/


        welcomeMsg = (TextView)findViewById(R.id.textView);

        addAccount = (Button)findViewById(R.id.button4);
        //logout = (Button)findViewById(R.id.button5);

        /*Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");*/

        //welcomeMsg.setText("");

        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent1 = new Intent(welcome.this,AddAccount.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("name",name);
                intent1.putExtras(bundle);
                startActivity(intent1);*/

                saveUserInformation();

            }
        });

        /*logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logging out the user
                firebaseAuth.signOut();
                //closing activity
                finish();
                //starting login activity
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });*/
    }

    private void saveUserInformation() {
        //Getting values from database
        String fname = firstname.getText().toString().trim();
        String lname = lastname.getText().toString().trim();
        Long mobileno = Long.parseLong(mobile.getText().toString().trim());

        if(TextUtils.isEmpty(fname)){
            Toast.makeText(this,"Please Enter First Name",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(lname)){
            Toast.makeText(this,"Please Enter Last Name",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(mobile.getText().toString().trim())){
            Toast.makeText(getApplicationContext(),"Please Enter Mobile No",Toast.LENGTH_LONG).show();
            return;
        }

        //creating a userinformation object
        User userInformation = new User(fname,lname,mobileno);

        try {

            //getting the current logged in user
            FirebaseUser user = firebaseAuth.getCurrentUser();

            //saving data to firebase database
        /*
        * first we are creating a new child in firebase with the
        * unique id of logged in user
        * and then for that user under the unique id we are saving data
        * for saving data we are using setvalue method this method takes a normal java object
        * */
            databaseReference.child(user.getUid()).setValue(userInformation);

            //displaying a success toast
            Toast.makeText(getApplicationContext(), "Details Saved...", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(welcome.this, AddAccount.class);
            startActivity(intent);
        }
        catch(NullPointerException n){

        }
    }
}
