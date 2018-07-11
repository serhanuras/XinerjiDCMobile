package com.xinerji.xinerjidc.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.adapters.NetDateTimeJsonAdapter;
import com.xinerji.xinerjidc.fragment.HomeFragment;
import com.xinerji.xinerjidc.fragment.ProductsFragment;
import com.xinerji.xinerjidc.fragment.CompleteOrderFragment;
import com.xinerji.xinerjidc.fragment.UploadImageFragment;
import com.xinerji.xinerjidc.model.ChangeDeliverStatusRequest;
import com.xinerji.xinerjidc.model.ChangeDeliverStatusResponse;
import com.xinerji.xinerjidc.model.GetOrderDocumentListRequest;
import com.xinerji.xinerjidc.model.GetOrderDocumentListResponse;
import com.xinerji.xinerjidc.utilities.JsonServiceCallingUtil;
import com.xinerji.xinerjidc.utilities.PopupUtil;
import com.xinerji.xinerjidc.utilities.SessionUtil;
import com.xinerji.xinerjidc.utilities.XinerjiServiceUtil;

import org.json.JSONObject;

import java.util.Date;

import okhttp3.Request;
import okhttp3.Response;

public class FragmentActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {

    JsonServiceCallingUtil jsonServiceCallingUtil;
    final PopupUtil popupUtil = new PopupUtil();
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new NetDateTimeJsonAdapter()).create();

    private ImageView homeImageView;
    private ImageView prevImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.transaction_actionbar);
        View view =getSupportActionBar().getCustomView();

        Toolbar parent =(Toolbar) view.getParent();
        parent.setPadding(0,0,0,0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0,0);

        TextView transactionactionbar_label = (TextView) view.findViewById(R.id.transactionactionbar_label);
        homeImageView = (ImageView) view.findViewById(R.id.transactionactionbar_home);
        prevImageView = (ImageView) view.findViewById(R.id.transactionactionbar_prev);

        prevImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity.this.finish();
            }
        });

        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentActivity.this, LogonActivity.class);
                FragmentActivity.this.startActivity(intent);
            }
        });

        transactionactionbar_label.setText("Teslimat Detayı - " + SessionUtil.getSelectedTripOrder().getTitle());


        loadFragment(new HomeFragment());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_products:
                fragment = new ProductsFragment();
                break;

            case R.id.navigate_photo:
                fragment = new UploadImageFragment();

                break;

            case R.id.navigation_notifications:

                String location = SessionUtil.getSelectedTripOrder().getLocation();

                location = location.replace("(","");
                location = location.replace(")","");
                String[] cord = location.split(",");

                cord[0] = cord[0].trim();
                cord[1] = cord[1].trim();

                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("geo:" + cord[0] +"," + cord[1]);

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);

                //fragment = new MapFragment();
                break;

            case R.id.complete_order:
                fragment = new CompleteOrderFragment();
                break;
        }

        return loadFragment(fragment);
    }
}
