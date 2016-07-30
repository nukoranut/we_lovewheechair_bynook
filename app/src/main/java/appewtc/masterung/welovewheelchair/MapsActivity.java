package appewtc.masterung.welovewheelchair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static double centerLatADouble = 14.817763;
    private static double centerLngADouble = 101.668987;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }   // Main Method

    //Create Inner Class
    public class ConnectedJSON extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url("http://swiftcodingthai.com/nuk/php_get_shop.php").build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("2May", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                final String[] ShopNameStrings = new String[jsonArray.length()];
                final String[] AddressStrings = new String[jsonArray.length()];
                final String[] PhoneStrings = new String[jsonArray.length()];
                String[] IconStrings = new String[jsonArray.length()];
                String[] LatStrings = new String[jsonArray.length()];
                String[] LngStrings = new String[jsonArray.length()];
                final String[] CategoryStrings = new String[jsonArray.length()];
                final int[] iconInts = new int[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ShopNameStrings[i] = jsonObject.getString("ShopName");
                    AddressStrings[i] = jsonObject.getString("Address");
                    PhoneStrings[i] = jsonObject.getString("Phone");
                    IconStrings[i] = jsonObject.getString("Icon");
                    LatStrings[i] = jsonObject.getString("Lat");
                    LngStrings[i] = jsonObject.getString("Lng");
                    CategoryStrings[i] = jsonObject.getString("Category");

                    //Find Icon for Marker
                    iconInts[i] = findIconForMarker(IconStrings[i]);

                    //Create All Marker
                    double lat = Double.parseDouble(LatStrings[i]);
                    double lng = Double.parseDouble(LngStrings[i]);
                    LatLng latLng = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .icon(BitmapDescriptorFactory.fromResource(iconInts[i])));


                }   // for

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        String strIndex = marker.getId().toString().substring(1);
                        int intIndex = Integer.parseInt(strIndex);

                        Intent intent = new Intent(MapsActivity.this, DetailShop.class);

                        intent.putExtra("Name", ShopNameStrings[intIndex]);
                        intent.putExtra("Address", AddressStrings[intIndex]);
                        intent.putExtra("Phone", PhoneStrings[intIndex]);
                        intent.putExtra("Category", CategoryStrings[intIndex]);
                        intent.putExtra("Image", iconInts[intIndex]);

                        startActivity(intent);

                        return true;
                    }   // onMarkerClick
                });


            } catch (Exception e) {
                e.printStackTrace();
            }

        }   // onPost

    }   // ConnectedJSON Class

    private int findIconForMarker(String iconString) {

        int intIcon = R.drawable.first_hand;

        if (iconString.equals("มือหนึ่ง")) {
            intIcon = R.drawable.first_hand;
        } else {
            intIcon = R.drawable.second_hand;
        }

        return intIcon;
    }


    public void clickListShop(View view) {
        startActivity(new Intent(MapsActivity.this, ChooseSection.class));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Setup Center Thailand
        LatLng latLng = new LatLng(centerLatADouble, centerLngADouble);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 6));

        ConnectedJSON connectedJSON = new ConnectedJSON();
        connectedJSON.execute();

    }   // onMap

    public void clickBackCatListView(View view) {
        finish();
    }

}   // Main Class
