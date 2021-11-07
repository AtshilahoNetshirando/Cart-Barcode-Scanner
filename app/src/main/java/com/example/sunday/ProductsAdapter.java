package com.example.sunday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ProductsAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    //String[] products;
    //String[] prices;
    List<String> products;
    List<String> prices;
    List<Integer> quantity;

    public ProductsAdapter(Context c, /*String[] p*/List<String> p,/*String[] p2*/List<String> p2, List<Integer> p3){
        products = p;
        prices = p2;
        quantity = p3;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        //return products.length;
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        //return products[position];
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.my_list_view, null);
        TextView txtProductName = (TextView) v.findViewById(R.id.txtProductName);
        TextView txtPrice= (TextView) v.findViewById(R.id.txtPrice);
        TextView txtQuantity = (TextView) v.findViewById(R.id.txtQuantityd);
        //String product = products[position];
        //String price = prices[position];
        String product = products.get(position);
        String price = prices.get(position);
        int quantity1 = quantity.get(position);

        txtProductName.setText(product);
        txtPrice.setText(price);
        txtQuantity.setText(quantity1+"");
        return v;
    }
}
