package com.example.sunday;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private Handler sliderHandler = new Handler();
    ListView myListView;
    DatabaseOperations dob;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dob = new DatabaseOperations(ctx);

        //Saving to database
        dob.putInformation(dob,"1","0","0");

        final Button tutorialbtn= (Button) findViewById(R.id.tutorialbtn);
        tutorialbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), NavHomeActivity.class);
                startActivity(startIntent);
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadein);
                tutorialbtn.startAnimation(animation);
            }
        });

        final int c = 1;
        final Button startShoppingbtn = (Button) findViewById(R.id.startShoppingbtn);
        startShoppingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ScanProductsBarActivity.class);
                startActivity(intent);
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadein);
                startShoppingbtn.startAnimation(animation);
                Data data = new Data();
                data.Reset();
                boolean res = dob.updateUserInfo("1","0","0");


            }
        });


    }

}
