package com.example.sunday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EditProductsActivity extends AppCompatActivity {
    String[] products;
    String[] prices;
    List<String> allproducts;
    List<String> allprices;
    DatabaseOperations dob;
    Context ctx = this;
    TextView quantityEdittxt;
    TextView priceEdittxt;
    int quantity;
    float sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_products);

        //Reading position from last activity
        Intent in = getIntent();
        int index = in.getIntExtra("ITEM_INDEX",-1);

        //Reading array of products and prices
        Resources res = getResources();
        products= res.getStringArray(R.array.products);
        prices = res.getStringArray(R.array.prices);

        //Instance of Data
        Data data =new  Data();
        allproducts = data.getAllProducts();
        allprices = data.getAllPrices();

        //Reading the product number from database
        dob = new DatabaseOperations(ctx);
        Cursor CR = dob.getInformation(dob);
        CR.moveToFirst();
        String p_number = CR.getString(1);
        String count = CR.getString(2);
        int product_num =Integer.parseInt(p_number);

        //Displaying Price and Quantiy, Add
        Button addEditbtn = (Button) findViewById(R.id.AddEdittxt);
        Button minusEditbtn = (Button) findViewById(R.id.minusEdittxt);
        quantityEdittxt = (TextView) findViewById(R.id.quantityEdittxt);
        priceEdittxt = (TextView) findViewById(R.id.priceEdittxt);
        priceEdittxt.setText(allprices.get(index));
        quantityEdittxt.setText("1");

        String s1 = allprices.get(index);
        String s2 = s1.substring(1);
        float ns = Float.parseFloat(s2);
        //int ns1 = (int) ns;
        float ns1 = ns;
        sum = ns1;
        quantity = 1;
        addEditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantity = quantity+ 1;

                String m = allprices.get(index);
                String m2 = m.substring(1);
                float num = Float.parseFloat(m2);
                //int n = (int) num;
                float n = num;
                sum = sum +  n;

                quantityEdittxt.setText(quantity+"");
                priceEdittxt.setText("R"+sum);

                Animation animation = AnimationUtils.loadAnimation(EditProductsActivity.this,R.anim.fadein);
                addEditbtn.startAnimation(animation);
            }
        });

        minusEditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (quantity > 1){
                    quantity = quantity -1;
                    String m = allprices.get(index);
                    String m2 = m.substring(1);
                    float num = Float.parseFloat(m2);
                    //int n = (int) num;
                    float n = num;
                    sum = sum - n;

                    quantityEdittxt.setText(quantity+"");
                    priceEdittxt.setText("R"+sum);

                }


                Animation animation = AnimationUtils.loadAnimation(EditProductsActivity.this,R.anim.fadein);
                minusEditbtn.startAnimation(animation);
            }
        });



        if (index > -1){
            int pic = getIgm(index);
            ImageView img = (ImageView) findViewById(R.id.imageView10);
            scaleImg(img, pic);
        }

        //Saving the updated price
        Button save_updated_pricebtn = (Button) findViewById(R.id.saveEditbtn);
        save_updated_pricebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.saveAllQuantity(index,quantity);
                data.saveUpdateAllPricesAdd(index,sum);
                data.saveUpdateAllPrices2();
                Intent startIntent = new Intent(getApplicationContext(),ProductsScannedActivity.class);
                startIntent.putExtra("UpdatedPrice",index);
                startActivity(startIntent);
                Animation animation = AnimationUtils.loadAnimation(EditProductsActivity.this,R.anim.fadein);
                save_updated_pricebtn.startAnimation(animation);
            }
        });

        Button cancelbtn = (Button) findViewById(R.id.cancelEditbtn);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),ProductsScannedActivity.class);
                startActivity(startIntent);
                Animation animation = AnimationUtils.loadAnimation(EditProductsActivity.this,R.anim.fadein);
                cancelbtn.startAnimation(animation);

            }
        });



    }

    private int getIgm(int index){

        Data data = new Data();
        List<String> products = data.getAllProducts();
        Resources res = getResources();
        String[] str_products= res.getStringArray(R.array.products);
        List<String> cheproducts = new ArrayList<>();
        cheproducts.add(products.get(index));

        for (int i = 0;i<str_products.length;i++){
            if (cheproducts.contains(str_products[i])){
                index = i;
                break;
            }
        }




        switch (index){
            case 0: return R.drawable.ssensodyne;
            case 1: return R.drawable.pplay;
            case 2: return R.drawable.coke;
            case 3: return R.drawable.clover;
            case 4: return R.drawable.dairymilk;
            case 5: return R.drawable.colgate;
            case 6: return R.drawable.airoma;
            case 7: return R.drawable.babysoft;
            case 8: return R.drawable.fullcream;
            case 9: return R.drawable.kellogsprotein;
            case 10: return R.drawable.kellogsprotein;
            case 11: return R.drawable.luckystar;
            case 12: return R.drawable.niveacare;
            case 13: return R.drawable.oreochoc;
            case 14: return R.drawable.roberstonspice;
            default:return -1;
        }
    }

    private void scaleImg(ImageView img, int pic){
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();



        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if (imgWidth > screenWidth){
            int ratio = Math.round((float)imgWidth / (float)screenWidth);
            options.inSampleSize = ratio;
        }
        options.inJustDecodeBounds = false;
        Bitmap scaledImg  = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);

        ;
    }
}
