package com.example.currencyconverter;

import android.os.Parcel;
import android.os.Parcelable;

public class Country implements Parcelable{
    private String countryName;
    private double ratiorate ;
    // Image name (Without extension)
    private int flagName;
    private String CurrencyName;
    public Country(String countryName, int flagName, String CurrencyName, double ratiorate ){
        this.countryName = countryName ;
        this.flagName = flagName ;
        this.CurrencyName = CurrencyName ;
        this.ratiorate = ratiorate ;
    }
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
}
