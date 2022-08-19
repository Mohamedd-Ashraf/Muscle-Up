package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityBmiHistoryBinding;
import com.example.myapplication.databinding.ActivityBmiPageBinding;

import java.util.Calendar;

import java.util.ArrayList;
import java.util.Date;

public class BMI_History extends AppCompatActivity {
    RecyclerView recyclerView;
    DBHelper myDB;
    ArrayList<String> height, weight, date, Bmi;
    @NonNull
    ActivityBmiHistoryBinding binding;

    BMI_AD bmi_ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBmiHistoryBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_bmi_history);
        recyclerView= findViewById(R.id.recylerview);
        myDB = new DBHelper(BMI_History.this);
        height = new ArrayList<>();
        weight = new ArrayList<>();
        date = new ArrayList<>();
        Bmi = new ArrayList<>();
        displayData();
        bmi_ad = new BMI_AD(BMI_History.this,height,weight,date,Bmi);
        recyclerView.setAdapter(bmi_ad);
        recyclerView.setLayoutManager(new LinearLayoutManager(BMI_History.this));
        binding.recylerview.setOnClickListener(view -> {

        });




    }

    void displayData() {
        Cursor cursor = myDB.readAllData(); //store there result from our readAllData in our cursor obj
        if (cursor.getCount() == 0) { //there is no data
            Toast.makeText(this, "No Data. ", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) { //read all data from our cursor
                Bmi.add(cursor.getString(5)); //0 means the 1st column
                height.add(cursor.getString(2)); //1 means the 2nd column
                weight.add(cursor.getString(3)); //2 means the 3rd column
                date.add(cursor.getString(4)); //3 means the 4th column
            }
        }
    }
}