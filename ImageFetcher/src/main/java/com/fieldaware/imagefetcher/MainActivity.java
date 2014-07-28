package com.fieldaware.imagefetcher;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.fieldaware.imagefetcher.R.string.error_timeout;


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

        private static final String DEBUG_TAG = "Debugging FieldAware" ;
        private ArrayList<Image> images = new ArrayList<Image>();
        private View rootView;
        private ProgressDialog dialog;
        private ListView list;
        int start = 1;
        int pageIntent = 1;
        boolean loadingMore = false;
        View loadMoreView;
        ArrayAdapter<Image> adapter;

        public ImagesFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            list = (ListView)rootView.findViewById(R.id.imagesListView);
            loadMoreView = inflater.inflate(R.layout.loadmore, null, false);
            list.addFooterView(loadMoreView);
            list.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {

                }

                @Override
                public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    int lastInScreen = firstVisibleItem + visibleItemCount;
                    ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        if ((lastInScreen == totalItemCount) && !(loadingMore) && start < 11) {
                            Log.d(DEBUG_TAG, "We're at the end of the list");
                            new GoForImages().execute("http://openclipart.org/search/json/?query=flower&page=" + start + "&amount=100", null, "");
                            pageIntent = start;
                            start++;
                            loadingMore = true;
                        } else {
                            list.removeFooterView(loadMoreView);
                        }
                    } else {
                        Toast t = Toast.makeText(getActivity(), "Connection error.", Toast.LENGTH_SHORT);
                        t.show();
                    }
                }
            });

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    if(!dialog.isShowing()) {
                        Image clickedImaged = images.get(position);
//                get big image for selected image
                        String urlPng = clickedImaged.getSvg().getPng_full_lossy();
                        Log.d("FieldAware urlPng", urlPng);
//                create intent and add details to it to send to create fragment
                        Intent intent = new Intent(getActivity(),DetailFragmentActivity.class);
                        intent.putExtra("urlPng", urlPng);
                        startActivity(intent);
                    }

                }
            });

            return rootView;
        }

        public void populateListView() {
            adapter = new ImageListAdapter(getActivity(), images);
            list.setAdapter(adapter);
        }

        private class GoForImages extends AsyncTask<String, Void, String> {

            protected void onPreExecute() {
                dialog = ProgressDialog.show(getActivity(),"","Loading Data...");

            }

            @Override
            protected String doInBackground(String... urls) {

                try {
                    return downloadJSON(urls[0]);
                } catch (IOException e) {
                    return "Unable to retrieve JSON. URL may be invalid.";
                }
            }

            private String downloadJSON(String myurl) throws IOException {
                InputStream is = null;

                try {
                    URL url = new URL(myurl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(20000 /* milliseconds */);
                    conn.setConnectTimeout(25000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn.connect();
                    int response = conn.getResponseCode();

                    //Log.d(DEBUG_TAG, "The response is: " + response);
                    is = conn.getInputStream();

                    // Convert the InputStream into a string
                    String contentAsString = null;
                    if (is !=null){
                        contentAsString = convertStreamToString(is);
                    }


                    return contentAsString;

                    // Makes sure that the InputStream is closed after the app is
                    // finished using it.
                } finally {
                    if (is != null) {
                        is.close();
                    } else {
                        //Toast.makeText(getActivity(), error_timeout, Toast.LENGTH_SHORT).show();
                    }

                }
            }

            private String convertStreamToString(InputStream is) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();

                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return sb.toString();
            }

            // onPostExecute displays the results of the AsyncTask.
            protected void onPostExecute(String result) {
                if (result != null && result !="Unable to retrieve JSON. URL may be invalid.") {
                    Log.d("FieldAware result: ", result);
                    Gson gson = new Gson();
                    try {
                        PageDataModel payload = gson.fromJson(result,PageDataModel.class);

                        //populateList();
                        for (int i = 0; i < payload.getPayload().size();i++){
                            images.add(payload.getPayload().get(i));
                            //Log.d(DEBUG_TAG, "The response string is: " + payload.getPayload().get(i).getSvg().getPng_thumb());
                        }
                        if (start == 2) {
                            populateListView();
                            loadingMore=false;
                            dialog.dismiss();
                        } else {
                            loadingMore=false;
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    } catch (Exception e) {
                        start = pageIntent;
                        dialog.dismiss();
                        Toast t = Toast.makeText(getActivity(), "Something was wrong.", Toast.LENGTH_SHORT);
                        t.show();
                    }

                } else {
                    start = pageIntent;
                    loadingMore=false;
                    dialog.dismiss();
                    Toast t = Toast.makeText(getActivity(), "Something was wrong.", Toast.LENGTH_SHORT);
                    t.show();
                }

            }

        }
    }
}
