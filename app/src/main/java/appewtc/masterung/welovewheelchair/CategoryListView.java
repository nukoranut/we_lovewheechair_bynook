package appewtc.masterung.welovewheelchair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoryListView extends AppCompatActivity {

    //Explicit
    private TextView textView;
    private ListView listView;
    private String catString;
    private String urlJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list_view);

        //Bind Widget
        textView = (TextView) findViewById(R.id.textView36);
        listView = (ListView) findViewById(R.id.listView2);

        //Show View
        catString = getIntent().getStringExtra("Category");
        textView.setText(catString);

        //Choose URL JSON
        urlJSON = chooseURLjson(catString);
        Log.d("2MayV2", "URL ==> " + urlJSON);

        MyConnected myConnected = new MyConnected();
        myConnected.execute();

    }   // Main Method

    private int findIconForMarker(String iconString) {

        int intIcon = R.drawable.first_hand;

        if (iconString.equals("มือหนึ่ง")) {
            intIcon = R.drawable.first_hand;
        } else {
            intIcon = R.drawable.second_hand;
        }

        return intIcon;
    }

    //Create Inner Class
    public class MyConnected extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                return null;
            }
        }   //doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("2MayV2", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                int intCount = jsonArray.length();
                final String[] nameStrings = new String[intCount];
                final String[] addressStrings = new String[intCount];
                final String[] phoneStrings = new String[intCount];
                final String[] categoryStrings = new String[intCount];
                String[] iconStrings = new String[intCount];
                final int[] iconInts = new int[intCount];

                for (int i = 0; i < intCount; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    nameStrings[i] = jsonObject.getString("ShopName");
                    addressStrings[i] = jsonObject.getString("Address");
                    phoneStrings[i] = jsonObject.getString("Phone");
                    categoryStrings[i] = jsonObject.getString("Category");
                    iconStrings[i] = jsonObject.getString("Icon");
                    iconInts[i] = findIconForMarker(iconStrings[i]);

                }   // for

                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(CategoryListView.this,
                        android.R.layout.simple_list_item_1, nameStrings);
                listView.setAdapter(stringArrayAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int intIndex, long l) {

                        Intent intent = new Intent(CategoryListView.this, DetailShop.class);

                        intent.putExtra("Name", nameStrings[intIndex]);
                        intent.putExtra("Address", addressStrings[intIndex]);
                        intent.putExtra("Phone", phoneStrings[intIndex]);
                        intent.putExtra("Category", categoryStrings[intIndex]);
                        intent.putExtra("Image", iconInts[intIndex]);

                        startActivity(intent);


                    }   // onItemClick
                });



            } catch (Exception e) {
                e.printStackTrace();
            }


        }   // onPost

    }   // MyConnected Class

    private String chooseURLjson(String catString) {

        String[] jsonStrings = new String[5];
        jsonStrings[0] = "http://swiftcodingthai.com/nuk/php_get_cat1.php";
        jsonStrings[1] = "http://swiftcodingthai.com/nuk/php_get_cat2.php";
        jsonStrings[2] = "http://swiftcodingthai.com/nuk/php_get_cat3.php";
        jsonStrings[3] = "http://swiftcodingthai.com/nuk/php_get_cat4.php";
        jsonStrings[4] = "http://swiftcodingthai.com/nuk/php_get_cat5.php";

        String urlJSON = null;
        if (catString.equals("ภาคกลาง")) {
            urlJSON = jsonStrings[0];
        } else if (catString.equals("ภาคตะวันออก")) {
            urlJSON = jsonStrings[1];
        } else if (catString.equals("ภาคตะวันออกเฉียงเหนือ")) {
            urlJSON = jsonStrings[2];
        } else if (catString.equals("ภาคใต้")) {
            urlJSON = jsonStrings[3];
        } else {
            urlJSON = jsonStrings[4];
        }


        return urlJSON;
    }

    public void clickBackCatListView(View view) {
        finish();
    }
    public void clickBackhome(View view) {
        startActivity(new Intent(CategoryListView.this, MainActivity.class));
        finish();
    }

}   // Main Class
