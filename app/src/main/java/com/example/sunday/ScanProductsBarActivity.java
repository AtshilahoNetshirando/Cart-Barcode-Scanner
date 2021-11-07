package com.example.sunday;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import android.os.Bundle;

import androidx.core.content.res.TypedArrayUtils;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

public class ScanProductsBarActivity extends AppCompatActivity {
    Button scan_btn;
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    DatabaseOperations dob;
    Context ctx = this;
    String[] prices;
    String[] products;
    int number;
    ImageView imgf;
    int quantity3;
    int sump;

    // Scanning Method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();


            }
            else{

                final Button scan22 = (Button) findViewById(R.id.confirm_scanbtn);
                scan22.setVisibility(View.VISIBLE);

                TextView dislpay = (TextView) findViewById(R.id.txtdisplanew);
                //dislpay.setText(result.getContents().toString());

                String product1 = result.getContents();
                String pp = product1.substring(0,4);

                Resources res = getResources();
                products = res.getStringArray(R.array.products);
                prices = res.getStringArray(R.array.prices);
                TextView price2 =(TextView) findViewById(R.id.txtTesting);

                number = 1;
                switch(pp){
                    case "9780":
                        number = 0;
                        break;
                    case "PLAY":
                        number = 1;
                        break;
                    case "Coca":
                        number = 2;
                        break;
                    case "Clov":
                        number = 3;
                        break;
                    case "Dair":
                        number = 4;
                        break;
                    case "Colg":
                        number = 5;
                        break;
                    case "Airo":
                        number = 6;
                        break;
                    case "Baby":
                        number = 7;
                        break;
                    case "Full":
                        number = 8;
                        break;
                    case "Corn":
                        number = 9;
                        break;
                    case "Kell":
                        number = 10;
                        break;
                    case "Luck":
                        number = 11;
                        break;
                    case "Nive":
                        number = 12;
                        break;
                    case "Oreo":
                        number = 13;
                        break;
                    case "Robe":
                        number= 14;
                        break;
                    default:
                }

                //Diplaying the price
                price2.setText(prices[number]);

                // Displaying the Image
                imgf.setVisibility(View.VISIBLE);
                if (number > -1){
                    int pic = getIgm(number);
                    scaleImg(imgf, pic);
                }

                Data data3 = new Data();

                // Saving Product Checker
                data3.save_condition_ProductChecker(number+"");



                final TextView txtProductCheck = (TextView) findViewById(R.id.txtPCheckerR);
                //txtProductCheck.setText(number+"");

                boolean cond_thur = data3.get_condition_ProductChecker();
                if (cond_thur == true){
                    //txtProductCheck.setText("Second");
                    final Button scan2 = (Button) findViewById(R.id.confirm_scanbtn);
                    scan2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Animation animation = AnimationUtils.loadAnimation(ScanProductsBarActivity.this,R.anim.fadein);
                            scan2.startAnimation(animation);
                            Toast.makeText(getBaseContext(), "Item already scanned!",Toast.LENGTH_LONG).show();
                        }
                    });


                }
                else{




                    //txtProductCheck.setText("First");
                    final Button scan2 = (Button) findViewById(R.id.confirm_scanbtn);
                    scan2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {



                            //Saving
                            //saving to all price
                            data3.saveTotalAllPrice(prices[number]);

                            //Saving to Product Checker
                            data3.saveProductChecker(number+"");

                            //Saving Saving Saving



                            dob = new DatabaseOperations(ctx);

                            Cursor CR = dob.getInformation(dob);
                            CR.moveToFirst();
                            String c = CR.getString(2);
                            int co = Integer.parseInt(c) +1;
                            String counter = String.valueOf(co);
                            String n= String.valueOf(number);
                            String new_nn = "8";
                            boolean up = dob.updateUserInfo("1",new_nn,counter);

                            Data data2 = new Data();

                            //save
                            int c_count = Integer.parseInt(c);
                            String m = prices[number];
                            String m2 = m.substring(1);
                            float numk = Float.parseFloat(m2);
                            data3.saveTotalUpdatedPrice(c_count,1);

                            boolean allCondition = data3.getAllConditon();
                            if (allCondition == true){
                                data3.saveAllProducts(products[number]);
                                data3.saveQuantityProducts(quantity3);
                                data3.saveAllPrices(prices[number]);

                            }


                            Animation animation = AnimationUtils.loadAnimation(ScanProductsBarActivity.this,R.anim.fadein);
                            scan2.startAnimation(animation);
                            Toast.makeText(getBaseContext(), "Item saved",Toast.LENGTH_LONG).show();
                            //Intent startIntent = new Intent(getApplicationContext(),ProductsScannedActivity.class);
                            //startActivity(startIntent);
                            final Button scan24 = (Button) findViewById(R.id.confirm_scanbtn);
                            scan24.setVisibility(View.GONE);

                        }
                    });


                }


            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_products_bar);

        //Product Checker
        Data data90 = new Data();

        Button btncart = (Button) findViewById(R.id.btncart);
        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(ScanProductsBarActivity.this,R.anim.fadein);
                btncart.startAnimation(animation);
                Intent startIntent = new Intent(getApplicationContext(),ProductsScannedActivity.class);
                startActivity(startIntent);

            }
        });


        //Hiding the image
        imgf = (ImageView) findViewById(R.id.imageViewDisable);
        imgf.setVisibility(View.GONE);


        scan_btn = (Button) findViewById(R.id.scan_btn);
        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(ScanProductsBarActivity.this,R.anim.fadein);
                scan_btn.startAnimation(animation);
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        //
        /*Slidr.attach(this);

        viewPager2 = findViewById(R.id.viewPagerImageSlider);

        List<SliderItem> sliderItems = new ArrayList<SliderItem>();
        ;

        sliderItems.add(new SliderItem(R.drawable.play2));
        sliderItems.add(new SliderItem(R.drawable.dairy2));
        sliderItems.add(new SliderItem(R.drawable.clover2));


        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f +  r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000); //slide duration 3 seconds
            }
        });*/



    }



    /*private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }*/

    //1
    public void scaleImg(ImageView img, int pic){
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


    }

    private int getIgm(int index) {

        switch (index) {
            case 0:
                return R.drawable.ssensodyne;
            case 1:
                return R.drawable.pplay;
            case 2:
                return R.drawable.coke;
            case 3:
                return R.drawable.clover;
            case 4:
                return R.drawable.dairymilk;
            case 5:
                return R.drawable.colgate;
            case 6:
                return R.drawable.airoma;
            case 7:
                return R.drawable.babysoft;
            case 8:
                return R.drawable.fullcream;
            case 9:
                return R.drawable.kellogsprotein;
            case 10:
                return R.drawable.kellogsprotein;
            case 11:
                return R.drawable.luckystar;
            case 12:
                return R.drawable.niveacare;
            case 13:
                return R.drawable.oreochoc;
            case 14:
                return R.drawable.roberstonspice;
            default:
                return -1;
        }
    }
}
