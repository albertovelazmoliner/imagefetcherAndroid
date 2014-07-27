package com.fieldaware.imagefetcher;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ImagesFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ImagesFragment extends Fragment {

        private ArrayList<Image> images = new ArrayList<Image>();
        private View rootView;


        public ImagesFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            populateList();
            populateListView();



            return rootView;
        }

        public void populateList() {
            images.add(new Image("http://images.all-free-download.com/images/graphicmedium/cow_4_117427.jpg", "vaca1"));
            images.add(new Image("http://3.bp.blogspot.com/-GEJ-QdO_-dw/Tym6TDgjFlI/AAAAAAAAcxI/GJds8Q2tFV8/s320/dibujodevacasparaimprimir2.jpg", "vaca2"));
            images.add(new Image("http://images.all-free-download.com/images/graphicmedium/cow_4_117427.jpg", "vaca3"));
            images.add(new Image("http://images.all-free-download.com/images/graphicmedium/cow_4_117427.jpg", "vaca4"));
            images.add(new Image("http://images.all-free-download.com/images/graphicmedium/cow_4_117427.jpg", "vaca5"));
            images.add(new Image("http://images.all-free-download.com/images/graphicmedium/cow_4_117427.jpg", "vaca1"));
            images.add(new Image("http://3.bp.blogspot.com/-GEJ-QdO_-dw/Tym6TDgjFlI/AAAAAAAAcxI/GJds8Q2tFV8/s320/dibujodevacasparaimprimir2.jpg", "vaca2"));
            images.add(new Image("http://images.all-free-download.com/images/graphicmedium/cow_4_117427.jpg", "vaca3"));
            images.add(new Image("http://images.all-free-download.com/images/graphicmedium/cow_4_117427.jpg", "vaca4"));
            images.add(new Image("http://images.all-free-download.com/images/graphicmedium/cow_4_117427.jpg", "vaca5"));
            images.add(new Image("http://images.all-free-download.com/images/graphicmedium/cow_4_117427.jpg", "vaca1"));
            images.add(new Image("http://3.bp.blogspot.com/-GEJ-QdO_-dw/Tym6TDgjFlI/AAAAAAAAcxI/GJds8Q2tFV8/s320/dibujodevacasparaimprimir2.jpg", "vaca2"));
            images.add(new Image("http://images.all-free-download.com/images/graphicmedium/cow_4_117427.jpg", "vaca3"));
            images.add(new Image("http://images.all-free-download.com/images/graphicmedium/cow_4_117427.jpg", "vaca4"));
            images.add(new Image("http://images.all-free-download.com/images/graphicmedium/cow_4_117427.jpg", "vaca5"));
        }

        public void populateListView() {
            ArrayAdapter<Image> adapter = new ImageListAdapter(getActivity(), images);
            ListView list = (ListView)rootView.findViewById(R.id.imagesListView);
            list.setAdapter(adapter);
        }
    }
}
