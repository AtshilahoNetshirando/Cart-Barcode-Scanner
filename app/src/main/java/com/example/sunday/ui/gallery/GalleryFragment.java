package com.example.sunday.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sunday.FileHelper;
import com.example.sunday.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private GalleryViewModel galleryViewModel;
    private EditText itemET;
    private Button btn;
    private ListView itemList;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        //
        itemET = root.findViewById(R.id.item_edit_text);
        btn = root.findViewById(R.id.add_btn);
        itemList = root.findViewById(R.id.item_list);

        items = FileHelper.readData(getContext());
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,items);
        itemList.setAdapter(adapter);
        btn.setOnClickListener(this);
        itemList.setOnItemClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_btn:
                String itemEntered = itemET.getText().toString();
                adapter.add(itemEntered);
                itemET.setText("");
                FileHelper.writeData(items,getContext());
                Toast.makeText(getContext(), "Item added", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();


    }
}
