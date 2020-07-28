package com.example.currencyconverter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


class CountryListViewAdapter extends BaseAdapter {

    ArrayList<Country> CountryList;
    ArrayList<Integer> HiddenPos;
    int resultinVND ;
    CountryListViewAdapter(ArrayList<Country> CountryList, ArrayList<Integer> HiddenPos, int resultinVND) {
        this.CountryList = CountryList;
        this.HiddenPos = HiddenPos;
        this.resultinVND = resultinVND ;
    }

    public void InputResult(int result) {
        this.resultinVND = result ;
    }

    public int getResultinVND() {
        return resultinVND;
    }

    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return CountryList.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return CountryList.get(position);
    }
    public Double getRatio(int position){
        return CountryList.get(position).getRatiorate();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class Holder{
        TextView tv1,tv2 ;
        ImageView img ;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới


        Holder holder = new Holder();
        View viewProduct;
        viewProduct = View.inflate(parent.getContext(), R.layout.list_item_layout, null);
//        viewProduct = convertView;

        //Bind sữ liệu phần tử vào View
        Country Country = (Country) getItem(position);
        holder.tv1 = ((TextView) viewProduct.findViewById(R.id.CountryName));
        holder.tv2 = ((TextView) viewProduct.findViewById(R.id.Currency));
        holder.img = (ImageView) viewProduct.findViewById(R.id.imageView_flag1) ;
        if (HiddenPos.contains(position)){
            holder.img.setVisibility(viewProduct.GONE);
            holder.tv1.setVisibility(viewProduct.GONE);
            holder.tv2.setVisibility(viewProduct.GONE);
        }
        holder.tv1.setText(Country.getCountryName());

        if (resultinVND == 0) {
            holder.tv2.setText(Country.getCurrencyName());
            Log.d("SHow VND", "VND:" + String.valueOf(resultinVND));
        }
        else
            holder.tv2.setText(String.valueOf(resultinVND * getRatio(position)));
        holder.img.setBackgroundResource(Country.getFlagName());
        viewProduct.setTag(holder);


        return viewProduct;
    }
}
