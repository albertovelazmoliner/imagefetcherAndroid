package com.fieldaware.imagefetcher;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by alberto on 27/07/14.
 */
public class ImageListAdapter extends ArrayAdapter<Image> {

    private ArrayList<Image> images;
    private Context context;

    public ImageListAdapter(Context context, ArrayList<Image> images) {
        super(context, R.layout.row_view,images);
        this.images = images;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//            do we have a view
        View itemView = convertView;
        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            itemView = inflater.inflate(R.layout.row_view, parent, false);
        }
//           get current person
        Image currentImage = images.get(position);

//            fill the view
        ImageView imageView = (ImageView) itemView.findViewById(R.id.row_icon);
//            for development - shows the triangle - yellow=disk, green=memory

//            you need to include the Picasso Library - see tutorial
        //Picasso.with(context).setDebugging(true);
        Picasso.with(context)
                .load(currentImage.getImageUrl())
                .into(imageView);

        TextView nameText = (TextView) itemView.findViewById(R.id.row_label);
        nameText.setText(currentImage.getText());

        return itemView;
    }
}
