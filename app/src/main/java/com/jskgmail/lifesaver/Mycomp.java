package com.jskgmail.lifesaver;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.victor.loading.rotate.RotateLoading;

public class Mycomp extends AppCompatActivity {
private RotateLoading rotateLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomp);
        rotateLoading=findViewById(R.id.rotateloading1);
        rotateLoading.start();
        SharedPreferences prefs = getSharedPreferences("acckeys",MODE_PRIVATE);

        final TextView des=findViewById(R.id.des);
        final TextView pro=findViewById(R.id.pro);
        final TextView mob=findViewById(R.id.no);
        final TextView add=findViewById(R.id.add);
        final String uid= prefs.getString("uid", "");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("PROBLEMS");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    if(uid.equals( String.valueOf(dataSnapshot1.getKey())))

                    for (DataSnapshot dataSnapshot2:dataSnapshot1.getChildren())
                {



                    if (dataSnapshot2.getKey().equals("Prob")) {
                    pro.setText(""+dataSnapshot2.getValue());


                }
                    if (dataSnapshot2.getKey().equals("Description")) {
                        des.setText(""+dataSnapshot2.getValue());

                    }
                    if (dataSnapshot2.getKey().equals("Address")) {
                        add.setText(""+dataSnapshot2.getValue());
                    }
                    if (dataSnapshot2.getKey().equals("Mobile")) {
                        mob.setText(""+dataSnapshot2.getValue());
                    }

                }

                }

                rotateLoading.stop();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });











    }
}
