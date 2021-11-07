package com.example.sunday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BarcodeGeneratorActivity extends AppCompatActivity {

    ImageView imageView;
    TextView totalItems;
    TextView totalPrice;
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_generator);

        //Total Items
        data = new Data();
        totalItems = (TextView) findViewById(R.id.txtTotaltems);
        int ttitems = (data.getAllProducts()).size();
        totalItems.setText(ttitems+" items");
        imageView = findViewById(R.id.barcodeIMG);

        //Total Price
        totalPrice = (TextView) findViewById(R.id.txtTotalPriceB);
        float ttotal = data.getTotalAllPrice();
        totalPrice.setText("R"+ttotal);

        //Geneerating The Barcode
        List<String> allProducts = data.getAllProducts();
        List<String> allPrice = data.getAllPrices();
        String alltext = "";
        for (int i = 0;i<allProducts.size();i++){
            String pp = allProducts.get(i)+  "    "+allPrice.get(i)+"\n";
            alltext = alltext + pp;
        }
        //alltext = alltext +"\n"+"\n"+"R"+ttotal;
        String text = "Atshilaho na Tshifhiwa";
        if (!text.equals("")){
            new ImageDownloaderTask(imageView).execute("https://api.qrserver.com/v1/create-qr-code/?size=1000x1000&data=" + alltext);
        }
    }
}
