package com.example.currencyconverter;

import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


class CountryListViewAdapter extends BaseAdapter {

    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩms
    final ArrayList<Country> CountryList;

    CountryListViewAdapter(ArrayList<Country> CountryList) {
        this.CountryList = CountryList;
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
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.list_item_layout, null);
        } else viewProduct = convertView;

        //Bind sữ liệu phần tử vào View
        Country Country = (Country) getItem(position);
        holder.tv1 = ((TextView) viewProduct.findViewById(R.id.CountryName));
        holder.tv2 = ((TextView) viewProduct.findViewById(R.id.Currency));
        holder.img = (ImageView) viewProduct.findViewById(R.id.imageView_flag1) ;
        holder.tv1.setText(Country.getCountryName());
        holder.tv2.setText(Country.getCurrencyName());
        if (Country.getCountryName()== "USA" )
            holder.img.setBackgroundResource(R.drawable.usa1);
        else if (Country.getCountryName()== "Japan" )
            holder.img.setBackgroundResource(R.drawable.japan);
        else if (Country.getCountryName()== "Euro" )
            holder.img.setBackgroundResource(R.drawable.euro1);
        else if (Country.getCountryName()== "British" )
            holder.img.setBackgroundResource(R.drawable.british);

//        holder.tv1.setVisibility(convertView.GONE);
//        holder.tv2.setVisibility(convertView.GONE);
//        holder.img.setVisibility(convertView.GONE);









        return viewProduct;
    }
}
