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

        View itemView = convertView;
        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            itemView = inflater.inflate(R.layout.row_view, parent, false);
        }
//           get current image
        Image currentImage = images.get(position);

//            fill the view
        ImageView imageView = (ImageView) itemView.findViewById(R.id.row_icon);

        //Picasso.with(context).setDebugging(true);
        Picasso.with(context)
                .load(currentImage.getSvg().getPng_thumb())
                .resize(50,50)
                .centerCrop()
                .into(imageView);

        TextView nameText = (TextView) itemView.findViewById(R.id.row_label);
        nameText.setText(currentImage.getTitle());

        return itemView;
    }
}
