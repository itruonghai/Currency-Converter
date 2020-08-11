package com.example.currencyconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class chooseflag extends AppCompatActivity {
    CountryListViewAdapter CountryListViewAdapter1;
    ListView listViewCountry1;
    ArrayList<Integer> ListPositionClick = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_chooseflag);
        Intent intent = getIntent();

        ArrayList<Country> CountryList1 = intent.getParcelableArrayListExtra("countrylist1");
        ArrayList<Integer> HiddenPos2 = intent.getIntegerArrayListExtra("HiddenPos2");

        CountryListViewAdapter1 = new CountryListViewAdapter(CountryList1, HiddenPos2,0);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listViewCountry1 = findViewById(R.id.listviewcountry1);
        listViewCountry1.setAdapter(CountryListViewAdapter1);
        listViewCountry1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.findViewById(R.id.imageView_flag1).setVisibility(view.GONE);
                view.findViewById(R.id.CountryName).setVisibility(view.GONE);
                view.findViewById(R.id.Currency).setVisibility(view.GONE);
                ListPositionClick.add(position) ;
            }

        });


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putIntegerArrayListExtra("HiddenPosBack", ListPositionClick);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}