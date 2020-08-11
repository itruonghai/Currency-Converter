package com.example.currencyconverter;

import android.os.Parcel;
import android.os.Parcelable;

public class Country implements Parcelable{
    private String countryName;
    private double ratiorate ;
    // Image name (Without extension)
    private int flagName;
    private String CurrencyName;
    private String unit;
    public Country(String countryName, int flagName, String CurrencyName, String unit ){
        this.countryName = countryName ;
        this.flagName = flagName ;
        this.CurrencyName = CurrencyName ;
        this.ratiorate = 0 ;
        this.unit = unit ;
    }
    public String getUnit(){return this.unit;}
    public String getCountryName(){
        return this.countryName ;
    }
    public double getRatiorate(){return this.ratiorate;}
    public int getFlagName() {
        return flagName;
    }
    public String getCurrencyName() {
        return CurrencyName;
    }
    public Country(Parcel in) {
        // put your data using = in.readString();
        this.countryName = in.readString();;
        this.flagName = in.readInt();;
        this.CurrencyName = in.readString();;
        this.ratiorate = in.readDouble();
        this.unit = in.readString() ;

    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(countryName);
        dest.writeInt(flagName);
        dest.writeString(CurrencyName);
        dest.writeDouble(ratiorate);
        dest.writeString(unit);
    }

    public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }

        @Override
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }
    };

    public void addratiorate(Double aDouble) {
        this.ratiorate = aDouble  ;
    }
}