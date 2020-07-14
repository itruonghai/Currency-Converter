package com.example.currencyconverter;

public class Country {
    private String countryName;
    private double ratiorate ;
    // Image name (Without extension)
    private String flagName;
    private String CurrencyName;
    public Country(String countryName, String flagName, String CurrencyName, double ratiorate ){
        this.countryName = countryName ;
        this.flagName = flagName ;
        this.CurrencyName = CurrencyName ;
        this.ratiorate = ratiorate ;
    }
    public String getCountryName(){
        return this.countryName ;
    }
    public double getRatiorate(){return this.ratiorate;}
    public String getFlagName() {
        return flagName;
    }
    public String getCurrencyName() {
        return CurrencyName;
    }

}
