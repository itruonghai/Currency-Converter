package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    ArrayList<Country> CountryList;
    ArrayList<Country> BaseCountryList;
    CountryListViewAdapter CountryListViewAdapter;
    ListView listViewCountry;
    ArrayList<Country> CountryList1;
    ArrayList<Integer> HiddenPos1 = new ArrayList<Integer>();
    ArrayList<Integer> HiddenPos2 = new ArrayList<Integer>();
    String URL_CountryRatioRate = "http://data.fixer.io/api/latest?access_key=4792f3c0ef59051bd9c8a00f8998f891&symbols=GBP,JPY,USD,VND,RUB,AUD,CNY,CAD"   ;
    HashMap<String, Double> getFromJson = new HashMap<>();

    private static final String TAG = "MyActivity";
    int res = 0 ;
    String onScreen = "";
    private static final int REQUEST_CODE_EXAMPLE = 0x9345;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JsonDownload myAsyncTask = new JsonDownload();
        if (myAsyncTask!= null && myAsyncTask.getStatus() == JsonDownload.Status.RUNNING) {
            myAsyncTask.cancel(true);
        }
        myAsyncTask.execute();

        if (savedInstanceState != null) {
            this.CountryList = (ArrayList<Country>)savedInstanceState.getSerializable("countryList");
            this.CountryList1 = (ArrayList<Country>)savedInstanceState.getSerializable("countryList1");
            this.HiddenPos1 = (ArrayList<Integer>)savedInstanceState.getSerializable("hiddenpos1");
            this.HiddenPos2 = (ArrayList<Integer>)savedInstanceState.getSerializable("hiddenpos2");
            this.res = savedInstanceState.getInt("Result") ;
            this.onScreen = savedInstanceState.getString("OnScreen") ;
            String answer = calculate(onScreen);
            ((TextView)findViewById(R.id.ScreenNumber)).setText(onScreen);
            ((TextView)findViewById(R.id.vndcurrency)).setText(answer);
//            Log.d("Size of COuntryList", "Size: "+ String.valueOf(HiddenPos1.size()));
        } else {

            BaseCountryList = new ArrayList<>();
            CountryList = new ArrayList<>();
            CountryList1 = new ArrayList<>();
            BaseCountryList.add(new Country("USA", R.drawable.usa1, "United States Dollar $ ", "USD"));
            BaseCountryList.add(new Country("Japan", R.drawable.japan, "Japanese Yen ¥", "JPY"));
            BaseCountryList.add(new Country("Euro", R.drawable.euro1, "Euro €", "EUR"));
            BaseCountryList.add(new Country("British", R.drawable.british, "Pound Sterling £",  "GBP"));
            BaseCountryList.add(new Country("Russian", R.drawable.russian, "Russia Ruble ₽",  "RUB"));
            BaseCountryList.add(new Country("Australia", R.drawable.austrailia, "Australia Dollar $", "AUD"));
            BaseCountryList.add(new Country("China", R.drawable.china, "China Yuan Renminbi ¥", "CNY"));
            BaseCountryList.add(new Country("Canada", R.drawable.canada, "Canada Dollar $", "CAD"));
            CountryList = BaseCountryList;
            CountryList1 = BaseCountryList;
            for (int i = 4; i < 8; ++i)
                HiddenPos1.add(i);
            for (int i = 0; i < 4; ++i)
                HiddenPos2.add(i);
        }
        CountryListViewAdapter = new CountryListViewAdapter(CountryList, HiddenPos1,0);
        CountryListViewAdapter.notifyDataSetChanged();
        listViewCountry = findViewById(R.id.listviewcountry);
        listViewCountry.setAdapter(CountryListViewAdapter);
        CountryListViewAdapter.InputResult(res);
        CountryListViewAdapter.notifyDataSetChanged();
        //Intent intent = new Intent(this,chooseflag.class);
        listViewCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Debug2:", "Hide " + String.valueOf(position));
                view.findViewById(R.id.imageView_flag1).setVisibility(view.GONE);
                view.findViewById(R.id.Currency).setVisibility(view.GONE);
                view.findViewById(R.id.CountryName).setVisibility(view.GONE);
                view.findViewById(R.id.NameCurrency).setVisibility(view.GONE);
//                view.setVisibility(view.GONE);
                if (!HiddenPos1.contains(position))
                    HiddenPos1.add(position);
                HiddenPos2.remove(HiddenPos2.indexOf(position));
            }
        });


    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.actionbar, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // action with ID action_refresh was selected
//            case R.id.refresh:
//                Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT)
//                        .show();
//                break;
//            // action with ID action_settings was selected
//            case R.id.history:
//                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
//                        .show();
//                break;
//            default:
//                break;
//        }
//
//        return true;
//    }

    private class JsonDownload extends AsyncTask<Void, Void, HashMap<String, Double>> {
        @Override
        protected void onPreExecute()
        {
            getFromJson.clear() ;
        }

        @Override
        protected HashMap<String, Double> doInBackground(Void... voids) {
            String inline = "" ;
            URL url = null;
            try {
                url = new URL(URL_CountryRatioRate);
                HttpURLConnection conn = null;
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                Scanner sc = null;
                sc = new Scanner(url.openStream());
                while (sc.hasNext())
                    inline += sc.nextLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
//            Log.d("TestJson: ", "MyJson: " + inline);
            int index_of_rate = inline.lastIndexOf("{") ;
            String newString = inline.substring(index_of_rate + 1, inline.length() - 2 );
            String[] CurrencyCountry = newString.split(",") ;
//            Log.d("TestCurrency", CurrencyCountry[0]) ;
            for (String s : CurrencyCountry){
                int index_of_double = s.lastIndexOf(':') +1 ;
                Double d = Double.valueOf(s.substring(index_of_double, s.length())) ;
                String currency = s.substring(1, index_of_double - 2)  ;
                getFromJson.put(currency, d) ;
            }
            return getFromJson;
        }
        @Override
        protected void onPostExecute(HashMap<String, Double> stringDoubleHashMap) {
            super.onPostExecute(stringDoubleHashMap);
//            for (String i : stringDoubleHashMap.keySet())
//                Log.d("CheckJsonResult ", "Key: " + i + " Values " + String.valueOf(stringDoubleHashMap.get(i))) ;
            Double ratioVietnam = stringDoubleHashMap.get("VND") ;
            stringDoubleHashMap.put("EUR", 1.0) ;
            for (String i : stringDoubleHashMap.keySet())
                stringDoubleHashMap.replace(i, stringDoubleHashMap.get(i) / ratioVietnam) ;

            for (Country country: CountryList){
                if (stringDoubleHashMap.containsKey(country.getUnit()))
                    country.addratiorate(stringDoubleHashMap.get(country.getUnit()));
            }


        }
    }

    public String calculate(String input) {
        String parsedInteger = "";
        String operator = "";
        int aggregate = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                parsedInteger += c;
            }
            int parsed = 0;
            if (!Character.isDigit(c) || i == input.length() - 1) {
                try {
                    parsed = Integer.parseInt(parsedInteger);
                } catch (NumberFormatException e) {
                    return "BAD EXPRESSION";
                }
                if (operator == "") {
                    aggregate = parsed;
                } else {
                    if (operator.equals("+")) {
                        aggregate += parsed;
                    } else if (operator.equals("-")) {
                        aggregate -= parsed;
                    } else if (operator.equals("x")) {
                        aggregate *= parsed;
                    } else if (operator.equals(":")) {
                        if (parsed == 0)
                            return "BAD EXPRESSION";
                        aggregate /= parsed;
                    }
                }

                parsedInteger = "";
                operator = "" + c;
            }
        }
        return String.valueOf(aggregate);
    }

    public void numpadClicked(View view) {
        TextView textview = (TextView) findViewById(R.id.ScreenNumber);
        Button button = (Button) findViewById(view.getId());
        onScreen = onScreen + button.getText();
        textview.setText(onScreen);

    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, chooseflag.class);
//        bundle.putSerializable("countrylist1", CountryList1);
//        intent.putExtra("hai dep trai vai loz", "Giang heo");
        intent.putExtra("countrylist1", CountryList1);
        intent.putIntegerArrayListExtra("HiddenPos2", HiddenPos2);
        startActivityForResult(intent, REQUEST_CODE_EXAMPLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EXAMPLE) {

            if (resultCode == Activity.RESULT_OK) {
                ArrayList<Integer> pos = data.getIntegerArrayListExtra("HiddenPosBack");
                for (int position : pos) {
                    if (!HiddenPos2.contains(position))
                        HiddenPos2.add(position);
                    HiddenPos1.remove(HiddenPos1.indexOf(position));
                }

            }
        }
        CountryListViewAdapter.notifyDataSetChanged();

    }

    public void deleteClicked(View view) {
        TextView textview = (TextView) findViewById(R.id.ScreenNumber);
        onScreen = "";
        textview.setText("0");
        ((TextView) findViewById(R.id.vndcurrency)).setText("0");
        ((TextView) findViewById(R.id.ScreenNumber)).setTextColor(Color.BLACK);
    }

    public void equalClicked(View view) {
        TextView textview = (TextView) findViewById(R.id.vndcurrency);
        String answer = calculate(onScreen);
        if (answer == "BAD EXPRESSION") {
            ((TextView) findViewById(R.id.ScreenNumber)).setText(answer);
            ((TextView) findViewById(R.id.ScreenNumber)).setTextColor(Color.RED);
            textview.setText("0");
            onScreen = "";
        } else {
            textview.setText(answer);
            res = Integer.valueOf(answer);
            ((TextView) findViewById(R.id.ScreenNumber)).setTextColor(Color.BLACK);
            CountryListViewAdapter.InputResult(res);
            CountryListViewAdapter.notifyDataSetChanged();
        }
    }
    @Override
    protected  void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("countryList", this.CountryList);
        outState.putSerializable("countryList1", this.CountryList1);
        outState.putSerializable("hiddenpos1", this.HiddenPos1);
        outState.putSerializable("hiddenpos2", this.HiddenPos2);
        outState.putInt("Result", this.res);
        outState.putString("OnScreen", this.onScreen) ;

    }
//    ArrayList<Integer> HiddenPos1 = new ArrayList<Integer>();
//    ArrayList<Integer> HiddenPos2 = new ArrayList<Integer>();
}


