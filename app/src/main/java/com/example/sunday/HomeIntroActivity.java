package com.example.sunday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class HomeIntroActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //
        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String FirstTime = preferences.getString("FirstTimeInstall", "");

        if(FirstTime.equals("Yes")){
            //If application was opened for the first time
            Intent homeintent = new Intent(HomeIntroActivity.this,NavHomeActivity.class);
            startActivity(homeintent);

        }
        else{
            //Else...
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();
        }


        //
        addFragment(new Step.Builder().setTitle("Welcome")
                .setContent("Welcome to Best Price")
                .setBackgroundColor(Color.parseColor("#F4511E")) // int background color
                .setDrawable(R.drawable.carticon) // int top drawable
                .setSummary("Ecommerce App")
                .build());
        addFragment(new Step.Builder().setTitle("Start shopping")
                .setContent("Press the cart button")
                .setBackgroundColor(Color.parseColor("#1E88E5")) // int background color
                .setDrawable(R.drawable.start_shop) // int top drawable
                .setSummary("Press the cart circle button to start shopping")
                .build());

        addFragment(new Step.Builder().setTitle("Start scanning")
                .setContent("Scan products")
                .setBackgroundColor(Color.parseColor("#FFB300")) // int background color
                .setDrawable(R.drawable.scan) // int top drawable
                .setSummary("Press the scan button to scan products bar codes")
                .build());

        addFragment(new Step.Builder().setTitle("Save product")
                .setContent("Saving scanned product")
                .setBackgroundColor(Color.parseColor("#FFB300")) // int background color
                .setDrawable(R.drawable.confirm_scan) // int top drawable
                .setSummary("Press the confirm button to save the scanned product")
                .build());

        addFragment(new Step.Builder().setTitle("Cart")
                .setContent("Go to cart")
                .setBackgroundColor(Color.parseColor("#03DAC5")) // int background color
                .setDrawable(R.drawable.cart) // int top drawable
                .setSummary("Press the cart button to go cart page")
                .build());


    }

    @Override
    public void finishTutorial() {
        // Your implementation
        Intent startIntent = new Intent(getApplicationContext(), NavHomeActivity.class);
        startActivity(startIntent);
    }

    @Override
    public void currentFragmentPosition(int position) {

    }
}
