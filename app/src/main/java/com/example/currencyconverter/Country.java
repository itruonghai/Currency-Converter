package com.example.currencyconverter;

public class Country {
    private String countryName;

    // Image name (Without extension)
    private String flagName;
    private String CurrencyName;
    public Country(String countryName, String flagName, String CurrencyName ){
        this.countryName = countryName ;
        this.flagName = flagName ;
        this.CurrencyName = CurrencyName ;
    }
    public String getCountryName(){
        return this.countryName ;
    }

    public String getFlagName() {
        return flagName;
    }
    public String getCurrencyName() {
        return CurrencyName;
    }

}
