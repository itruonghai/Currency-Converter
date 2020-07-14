package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

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
    String onScreen = "" ;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CountryList = new ArrayList<>() ;
        CountryList.add(new Country("USA", "@drawable/usa1", "USA")) ;
        CountryList.add(new Country("Japan", "@drawable/japan", "Japanese Yen")) ;
        CountryList.add(new Country("Euro", "@drawable/euro", "Euro")) ;
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
                    img.setBackgroundResource(R.drawable.euro1);
                    text.setText(country.getCurrencyName());
                }
                else if (country.getCountryName().equals("Japan")) {
                    img.setBackgroundResource(R.drawable.euro1);
                    text.setText(country.getCurrencyName());
                }
                else if (country.getCountryName().equals("Euro") ){
                    img.setBackgroundResource(R.drawable.usa1);
                    text.setText(country.getCurrencyName());
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
            if (!Character.isDigit(c) || i == input.length()-1){
                int parsed = Integer.parseInt(parsedInteger);
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
    public void deleteClicked(View view){
        TextView textview = (TextView) findViewById(R.id.ScreenNumber) ;
        onScreen = "" ;
        textview.setText("0");
    }
    public void equalClicked(View view){

        TextView textview = (TextView) findViewById(R.id.vndcurrency) ;
        String answer = calculate(onScreen) ;
        if (answer == "BAD EXPRESSION")
            ((TextView) findViewById(R.id.ScreenNumber)).setText(answer) ;
        else {
            textview.setText(String.valueOf(answer));
            onScreen = "";
        }

    }

}