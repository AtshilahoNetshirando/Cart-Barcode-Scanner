package com.example.sunday;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NavHomeActivity extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    DatabaseOperations dob;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dob = new DatabaseOperations(ctx);
        //Saving to database
        dob.putInformation(dob,"1","5","0");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ScanProductsBarActivity.class);
                startActivity(intent);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Data data = new Data();
                data.Reset();
                boolean res = dob.updateUserInfo("1","0","0");
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.blankFragment,R.id.blackFragment)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //
        /*recyclerView=findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        int arr[] = {R.drawable.kellogsprotein2,R.drawable.luckystar2,R.drawable.niveacare2,R.drawable.apampers,R.drawable.roberstonspice2,
        R.drawable.satiskingocean2,R.drawable.fullcream2,R.drawable.babysoft2,R.drawable.airoma,R.drawable.aallgoukd,R.drawable.aamasi,
        R.drawable.abiscuiys1,R.drawable.abuiscuts2,R.drawable.ablossom,R.drawable.acornflakes,R.drawable.adetol,R.drawable.adlight,
        R.drawable.adogsfood,R.drawable.afanta,R.drawable.ahugmug,R.drawable.ainkomasi,R.drawable.akchicken,R.drawable.aknomeat,R.drawable.akrush,
        R.drawable.aliqurf,R.drawable.alite,R.drawable.amilo,R.drawable.amuscle,R.drawable.anandos,R.drawable.aoros,R.drawable.aperiperi,
        R.drawable.asalad,R.drawable.asimba,R.drawable.asunlightclo,R.drawable.asunlightwa,R.drawable.atropicaa,R.drawable.aultramel,R.drawable.dairymilk};
        recyclerViewAdapter = new RecyclerViewAdapter(arr);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);*/



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
