package com.example.sunday;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class ProductsScannedActivity extends AppCompatActivity {
    ListView myListView;
    String[] products;
    String[] prices;
    DatabaseOperations dob;
    Context ctx = this;
    boolean cond_up;
    TextView txtDprice;
    float sum2;
    Runnable run;

    ProductsAdapter productsAdapter;
    List<String> arr1;
    List<String> arr2;
    List<Integer> arr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_scanned);


        Data data =new  Data();

        //Reading array of products and prices
        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        products= res.getStringArray(R.array.products);
        prices = res.getStringArray(R.array.prices);



        //Read from data database
        dob = new DatabaseOperations(ctx);
        Cursor CR = dob.getInformation(dob);
        CR.moveToFirst();
        String p_number = CR.getString(1);
        String count = CR.getString(2);
        int product_num =Integer.parseInt(p_number);
        int count_2 = Integer.parseInt(count);

        //arrays
        //arr1 = new ArrayList<>();
        //arr2 = new ArrayList<>();

        //Read from

        int[] tnumbers = data.getTotalUpdatedPrice();
        float sum = 0;

        //read from a
        List<String> str_products = data.getProductChecker();
        boolean allCondition = data.getAllConditon();
        if (allCondition == false){
            for(int i = 0;i<count_2;i++){


                int n_a_1 = Integer.parseInt(str_products.get(i)+"");
                int nn_count1 = tnumbers[i];
                //arr1.add(products[n_a_1]);
                //new_prices[i] = prices[numr];
                String m = prices[n_a_1];
                String m2 = m.substring(1);
                float num = Float.parseFloat(m2);

                //
                num = num * nn_count1;
                //arr2.add("R"+num);
                sum = sum + num;
                //All
                data.saveAllProducts(products[n_a_1]);
                data.saveAllPrices(prices[n_a_1]);
                data.saveAllCondition();
            }
        }


        //Reseting The Checker data
        data.reset_condition_ProductChecker();



        //Total Price and Displaying
        sum2 = data.getTotalAllPrice();
        txtDprice = (TextView) findViewById(R.id.txtDisplayPrice2);
        txtDprice.setText("R"+sum2);





        //Saving to Remove Instance
        arr1 = data.getAllProducts();
        arr2 = data.getAllPrices();
        arr3 = data.getAllQuantity();

        productsAdapter = new ProductsAdapter(this,arr1,arr2,arr3);
        myListView.setAdapter(productsAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cond_up = true;
                Animation animation = AnimationUtils.loadAnimation(ProductsScannedActivity.this,R.anim.fadein);
                myListView.startAnimation(animation);
                Intent showDetailActivity = new Intent(getApplicationContext(), EditProductsActivity.class);
                showDetailActivity.putExtra("ITEM_INDEX",position);
                startActivity(showDetailActivity);

            }
        });


        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            // setting onItemLongClickListener and passing the position to the function
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {
                removeItemFromList(position);

                //data.removeAllElement(position);


                return true;
            }
        });






        Slidr.attach(this);

        //Scan Again
        final Button scanAgainbtn = (Button) findViewById(R.id.btnbackScan);
        scanAgainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getApplicationContext(),ScanProductsBarActivity.class);
                startActivity(intent);
                Animation animation = AnimationUtils.loadAnimation(ProductsScannedActivity.this,R.anim.fadein);
                scanAgainbtn.startAnimation(animation);
            }
        });

        //Checkout
        final Button checkoutbtn = (Button) findViewById(R.id.checkOutbtn);
        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert = new AlertDialog.Builder(
                        ProductsScannedActivity.this);

                alert.setTitle("Generate Barcode");
                alert.setMessage("Confirm?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TOD O Auto-generated method stub

                        Intent intent = new Intent(getApplicationContext(),BarcodeGeneratorActivity.class);
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });

                alert.show();

                //
                Animation animation = AnimationUtils.loadAnimation(ProductsScannedActivity.this,R.anim.fadein);
                checkoutbtn.startAnimation(animation);
            }
        });





    }

    // method to remove list item
    protected void removeItemFromList(int position) {
        final int deletePosition = position;

        AlertDialog.Builder alert = new AlertDialog.Builder(
                ProductsScannedActivity.this);

        alert.setTitle("Delete");
        alert.setMessage("Do you want delete this item?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TOD O Auto-generated method stub

                Data data = new Data();
                data.removeProductChecker(position);

                //Total Price and Displaying
                float sumrr = data.getTotalAllPrice();
                txtDprice = (TextView) findViewById(R.id.txtDisplayPrice2);
                txtDprice.setText("R"+sumrr);
                // main code on after clicking yes
                arr1.remove(deletePosition);
                arr2.remove(deletePosition);
                productsAdapter.notifyDataSetChanged();
                productsAdapter.notifyDataSetInvalidated();

            }
        });
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });

        alert.show();

    }


}
