package com.example.currencyconverter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class History extends AppCompatActivity{
    CountryListViewAdapter CountryListViewAdapter1;
    ListView listViewCountry2;
    ArrayList<Integer> ListPositionClick = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.historylayout);
        Intent intent = getIntent();
        String datetime = intent.getStringExtra("datetime") ;
        String onScreen = intent.getStringExtra("StringOnScreen") ;
        ArrayList<Country> CountryList1 = intent.getParcelableArrayListExtra("countrylisthistory");
        ArrayList<Integer> HiddenPos2 = intent.getIntegerArrayListExtra("HiddenPoshistory");

        CountryListViewAdapter1 = new CountryListViewAdapter(CountryList1, HiddenPos2,Integer.valueOf(onScreen));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listViewCountry2 = findViewById(R.id.listviewcountry2);
        listViewCountry2.setAdapter(CountryListViewAdapter1);
        ((TextView)findViewById(R.id.vndcurrency)).setText(onScreen);
        ((TextView)findViewById(R.id.datetimeid)).setText(datetime);




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