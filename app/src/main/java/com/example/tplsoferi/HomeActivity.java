package com.example.tplsoferi;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import com.example.tplsoferi.fragment.MapsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    String item;
    String[] linii= {"Linia 1", "Linia 2", "Linia 3", "Linia 4", "Linia 5",
            "Linia 6", "Linia 7", "Linia 8", "Linia 15", "Linia 17", "Linia 21",
            "Linia 22", "Linia 23", "Linia 26", "Linia 28", "Linia 29", "Linia 30"};
    AutoCompleteTextView autocompletetxt;
    ArrayAdapter<String> adapterItems;
    Button mStart;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MapsFragment()).commit();

        mStart = findViewById(R.id.start);
        autocompletetxt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this,R.layout.lista_linii,linii);
        autocompletetxt.setAdapter(adapterItems);
        autocompletetxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                mStart.setVisibility(View.VISIBLE);
            }
        });

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStart.setVisibility(View.GONE);
                String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

                FirebaseDatabase.getInstance().getReference("ID Soferi").child(userId).child("Linia").setValue(item);
            }
        });
    }
}
