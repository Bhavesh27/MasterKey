package com.example.bhavesh.masterkey;

import android.app.ListActivity;
import android.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Records extends ListActivity {

    FirebaseAuth firebaseAuth;
    List<Account> accounts = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    /*List<String> email = new ArrayList<>();
    List<String> uname = new ArrayList<>();
    List<String> vname = new ArrayList<>();
    List<String> pass = new ArrayList<>();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_records);

        //Firebase.setAndroidContext(this);

       firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        //Creating firebase object
        //Firebase ref = new Firebase(config.DB_URL + user.getUid());
        databaseReference.child(user.getUid()).addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                Iterable<com.google.firebase.database.DataSnapshot> children = dataSnapshot.getChildren();

                for (com.google.firebase.database.DataSnapshot ds:children) {
                    Account account = ds.getValue(Account.class);
                    accounts.add(account);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*for (Account acc : accounts) {

            email.add(acc.getEmail());
            vname.add(acc.getVendorname());
            uname.add(acc.getUsername());
            pass.add(acc.getPassword());

        }*/


        ArrayAdapter<Account> lists = new ArrayAdapter<>(Records.this,android.R.layout.simple_list_item_1,accounts);
        setListAdapter(lists);

    }



}
