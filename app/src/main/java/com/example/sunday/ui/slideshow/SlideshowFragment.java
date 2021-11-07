package com.example.sunday.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunday.Company;
import com.example.sunday.Product;
import com.example.sunday.ProductAdapter;
import com.example.sunday.R;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        //
        RecyclerView recyclerView = root.findViewById(R.id.recyclerViewPC);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Company> companies = new ArrayList<>();

        //
        ArrayList<Product> formsPayment = new ArrayList<>();
        formsPayment.add(new Product("Best Price accepts following payment methods"));
        formsPayment.add(new Product("1: You can pay with cash"));
        formsPayment.add(new Product("2: You can pay with credit card"));


        Company fPayment = new Company("When will i pay for the items i scanned?", formsPayment);
        companies.add(fPayment);

        //
        ArrayList<Product> googleProduct = new ArrayList<>();
        googleProduct.add(new Product("1: Go to Cart"));
        googleProduct.add(new Product("2: Long click the item you want delete"));
        googleProduct.add(new Product("3: On the pop-up window, Confirm to delete the item"));
        googleProduct.add(new Product("4: Tap Scan if you wish to scan more items"));

        Company google = new Company("How do I remove items from my cart?", googleProduct);
        companies.add(google);

        ArrayList<Product> microsoftProduct = new ArrayList<>();
        microsoftProduct.add(new Product("In some cases, The Best Price payment processor may " +
                "have trouble capturing your payment on its first attempt. The system will then re-try the" +
                "capture.This means that you may see a duplicate charge on your statement that is authorised but not captured"));


        Company microsoft = new Company("Why was I charged twice for may order?", microsoftProduct);
        companies.add(microsoft);



        ArrayList<Product> chargingOrder = new ArrayList<>();
        chargingOrder.add(new Product("You pay for the items scanned at the till"));

        Company charge = new Company("When will i pay for the items i scanned?", chargingOrder);
        companies.add(charge);

        //
        ArrayList<Product> paymentDiclined = new ArrayList<>();
        paymentDiclined.add(new Product("Your payement may be declined for one of the following reasons"));
        paymentDiclined.add(new Product ("1: Your card is expired"));
        paymentDiclined.add(new Product ("2: You have insufficient funds"));
        paymentDiclined.add(new Product ("3: Your card was reported stolen or frozen"));

        Company pD = new Company("Why is my payment being declined?", paymentDiclined);
        companies.add(pD);

        //
        ArrayList<Product> priceChanging = new ArrayList<>();
        priceChanging.add(new Product("The item was discounted for a limited time"));
        priceChanging.add(new Product("Best Price changed the price of the item."));

        Company pC = new Company("When will i pay for the items i scanned?", priceChanging);
        companies.add(pC);

        //
        ArrayList<Product> contactProblem = new ArrayList<>();
        contactProblem.add(new Product("You can contact customer support by selecting 'Customer' via " +
                "navigation panel"));

        Company cProblem = new  Company("Who can I contact if I have a problem?", contactProblem);
        companies.add(cProblem);

        //
        ArrayList<Product> inappropriateContent = new ArrayList<>();
        inappropriateContent.add(new Product("You should only report an item to Best Price if it violates one of their policies"));

        Company iContent = new  Company("How do i report inappropriate content?", inappropriateContent);
        companies.add(iContent);

        //
        ArrayList<Product> reportItem = new ArrayList<>();
        reportItem.add(new Product("If your are having issues with an item you scanned, Best Price " +
                "Customer Support team would be happy to help"));

        Company rItem = new  Company("How do i report a problem with an item that i picked up?", reportItem);
        companies.add(rItem);




        ProductAdapter adapter = new ProductAdapter(companies);
        recyclerView.setAdapter(adapter);

        /*final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}
