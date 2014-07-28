package com.fieldaware.imagefetcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by alberto on 28/07/14.
 */
public class DetailFragmentActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

//get the selected items values out of intent sent by main activity on click event
        Intent intent = getIntent();
        String urlPng = intent.getStringExtra("urlPng");

//create intent to send construction arguments for fragment
        DetailFragment myFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("urlPng", urlPng);
        myFragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.container2, myFragment).commit();

    }
}