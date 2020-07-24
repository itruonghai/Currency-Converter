package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    ArrayList<Country> CountryList ;
    CountryListViewAdapter CountryListViewAdapter;
    ListView listViewCountry;
    ArrayList<Country> CountryList1 ;

    String onScreen = "" ;
    double currentratio = new Double(0.000043);
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CountryList = new ArrayList<>() ;
        CountryList1 = new ArrayList<>() ;
        CountryList.add(new Country("USA", "@drawable/usa1", "United States Dollar $ ",0.000043)) ;
        CountryList.add(new Country("Japan", "@drawable/japan", "Japanese Yen ¥",0.0046)) ;
        CountryList.add(new Country("Euro", "@drawable/euro1", "Euro €",0.000038)) ;
        CountryList.add(new Country("British", "@drawable/british", "Pound Sterling £",0.000034)) ;
        CountryList1.add(new Country("USA1", "@drawable/usa1", "United States Dollar $ ",0.000043)) ;
        CountryList1.add(new Country("Japan2", "@drawable/japan", "Japanese Yen ¥",0.0046)) ;
        CountryList1.add(new Country("Euro3", "@drawable/euro1", "Euro €",0.000038)) ;
        CountryList1.add(new Country("British4", "@drawable/british", "Pound Sterling £",0.000034)) ;
        CountryListViewAdapter = new CountryListViewAdapter(CountryList);
        listViewCountry = findViewById(R.id.listviewcountry);
        listViewCountry.setAdapter(CountryListViewAdapter);

        listViewCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) CountryListViewAdapter.getItem(position);

                ((TextView) findViewById(R.id.Name)).setText(country.getCountryName());
                TextView text = (TextView) findViewById(R.id.NameCurrency);
                ImageView img = (ImageView) findViewById(R.id.img) ;
                if (country.getCountryName().equals("USA") ) {
                    img.setBackgroundResource(R.drawable.usa1);
                    text.setText(country.getCurrencyName());
                    currentratio = country.getRatiorate() ;
                    changeCurrency();
                }
                else if (country.getCountryName().equals("Japan")) {
                    img.setBackgroundResource(R.drawable.japan);
                    text.setText(country.getCurrencyName());
                    currentratio = country.getRatiorate() ;
                    changeCurrency();

                }
                else if (country.getCountryName().equals("Euro") ){
                    img.setBackgroundResource(R.drawable.euro1);
                    text.setText(country.getCurrencyName());
                    currentratio = country.getRatiorate() ;
                    changeCurrency();

                }
                else if (country.getCountryName().equals("British") ) {
                    img.setBackgroundResource(R.drawable.british);
                    text.setText(country.getCurrencyName());
                    currentratio = country.getRatiorate();
                    changeCurrency();

                }


            }

        });




    }

    public String calculate(String input){
        String parsedInteger = "";
        String operator = "";
        int aggregate = 0;
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                parsedInteger += c;
            }
            int parsed = 0 ;
            if (!Character.isDigit(c) || i == input.length()-1){
                try {
                    parsed = Integer.parseInt(parsedInteger);
                }catch( NumberFormatException e){
                    return "BAD EXPRESSION";
                }
                if (operator == "") {
                    aggregate = parsed;
                }
                else {
                    if (operator.equals("+")) {
                        aggregate += parsed;
                    }else if (operator.equals("-")){
                        aggregate -= parsed;
                    }else if (operator.equals("x")){
                        aggregate *= parsed;
                    }
                    else if (operator.equals(":")) {
                        if (parsed == 0 )
                            return "BAD EXPRESSION" ;
                        aggregate /= parsed;
                    }
                }

                parsedInteger ="";
                operator = ""+c;
            }
        }
        return String.valueOf(aggregate);
    }
    public void numpadClicked(View view) {
        TextView textview = (TextView) findViewById(R.id.ScreenNumber) ;
        Button button = (Button) findViewById(view.getId());
        onScreen = onScreen + button.getText() ;
        textview.setText(onScreen);

    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this,chooseflag.class);
//        bundle.putSerializable("countrylist1", CountryList1);
//        intent.putExtra("hai dep trai vai loz", "Giang heo");
        intent.putExtra("countrylist1", CountryList1);
        startActivity(intent);
    }
    public void deleteClicked(View view){
        TextView textview = (TextView) findViewById(R.id.ScreenNumber) ;
        onScreen = "" ;
        textview.setText("0");
        ((TextView)findViewById(R.id.vndcurrency)).setText("0");
        ((TextView)findViewById(R.id.now)).setText("0");
        ((TextView) findViewById(R.id.ScreenNumber)).setTextColor(Color.BLACK) ;



    }
    public void equalClicked(View view){
        TextView textview = (TextView) findViewById(R.id.vndcurrency) ;
        TextView textviewtarget = (TextView) findViewById(R.id.now) ;
        String answer = calculate(onScreen) ;
        if (answer == "BAD EXPRESSION") {
            ((TextView) findViewById(R.id.ScreenNumber)).setText(answer);
            ((TextView) findViewById(R.id.ScreenNumber)).setTextColor(Color.RED) ;
            textview.setText("0") ;
            textviewtarget.setText("0" );
            onScreen= "";


        }
        else {
            textview.setText(answer);
            int res = Integer.valueOf(answer) ;
            double finalres = new Double(res * currentratio) ;
            TextView targetcurrency = (TextView) findViewById(R.id.now) ;
            targetcurrency.setText(String.valueOf(finalres)) ;
            ((TextView) findViewById(R.id.ScreenNumber)).setTextColor(Color.BLACK);
        }
    }
    public void changeCurrency(){
        String answer = calculate(onScreen) ;
        int res = Integer.valueOf(answer) ;
        double finalres = new Double(res * currentratio) ;
        TextView targetcurrency = (TextView) findViewById(R.id.now) ;
        targetcurrency.setText(String.valueOf(finalres)) ;
        ((TextView) findViewById(R.id.ScreenNumber)).setTextColor(Color.BLACK);
    }

}