package com.example.sunday.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunday.NavHomeActivity;
import com.example.sunday.R;
import com.example.sunday.RecyclerViewAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        recyclerView= root.findViewById(R.id.recyclerView);
        //layoutManager = new GridLayoutManager(this,2);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        int arr[] = {R.drawable.kellogsprotein2,R.drawable.luckystar2,R.drawable.niveacare2,R.drawable.apampers,R.drawable.roberstonspice2,
        R.drawable.satiskingocean2,R.drawable.fullcream2,R.drawable.babysoft2,R.drawable.airoma,R.drawable.aallgoukd,R.drawable.aamasi,
        R.drawable.abiscuiys1,R.drawable.abuiscuts2,R.drawable.ablossom,R.drawable.acornflakes,R.drawable.adetol,R.drawable.adlight,
        R.drawable.adogsfood,R.drawable.afanta,R.drawable.ahugmug,R.drawable.ainkomasi,R.drawable.akchicken,R.drawable.aknomeat,R.drawable.akrush,
        R.drawable.aliqurf,R.drawable.alite,R.drawable.amilo,R.drawable.amuscle,R.drawable.anandos,R.drawable.aoros,R.drawable.aperiperi,
        R.drawable.asalad,R.drawable.asimba,R.drawable.asunlightclo,R.drawable.asunlightwa,R.drawable.atropicaa,R.drawable.aultramel,R.drawable.dairymilk};
        recyclerViewAdapter = new RecyclerViewAdapter(arr);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);


        return root;
    }
}
