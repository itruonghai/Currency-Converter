package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class chooseflag extends AppCompatActivity {
    CountryListViewAdapter CountryListViewAdapter1;
    ListView listViewCountry1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseflag);
        Intent intent = getIntent();
        ArrayList<Country> CountryList1 = intent.getParcelableArrayListExtra("countrylist1");

//        String message = intent.getStringExtra("hai dep trai vai loz");

        // Capture the layout's TextView and set the string as its text
//        EditText textView = findViewById(R.id.testflag);
        CountryListViewAdapter1 = new CountryListViewAdapter(CountryList1);
        listViewCountry1 = findViewById(R.id.listviewcountry1);
        listViewCountry1.setAdapter(CountryListViewAdapter1);

    }
    public void BackButton(View view) {
        finish();
    }
}