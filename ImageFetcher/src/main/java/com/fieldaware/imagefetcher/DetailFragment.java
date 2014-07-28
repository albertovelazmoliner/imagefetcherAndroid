package com.fieldaware.imagefetcher;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by alberto on 28/07/14.
 */
public class DetailFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String urlPng = getArguments().getString("urlPng");
        //Log.d("FieldAware urlPng", urlPng);
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageViewFlower);
        Picasso.with(getActivity())
                .load(urlPng)
                .into(imageView);

        return rootView;
    }
}
