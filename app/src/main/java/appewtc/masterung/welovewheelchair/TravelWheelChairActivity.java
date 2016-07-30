package appewtc.masterung.welovewheelchair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class TravelWheelChairActivity extends AppCompatActivity {

    //Explicit
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_wheel_chair);

        listView = (ListView) findViewById(R.id.listView3);

        ConnectedTravel connectedTravel = new ConnectedTravel();
        connectedTravel.execute();

    }   // Main Method

    //Create Inner Class
    public class ConnectedTravel extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url("http://swiftcodingthai.com/nuk/php_get_travel.php").build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                return null;
            }
        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                JSONArray jsonArray = new JSONArray(s);

                final String[] namePlaceStrings = new String[jsonArray.length()];
                final String[] addressStrings = new String[jsonArray.length()];
                final String[] openCloseStrings = new String[jsonArray.length()];
                final String[] descriptionStrings = new String[jsonArray.length()];
                final String[] imagePlaceStrings = new String[jsonArray.length()];
                final String[] imageServiceStrings = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    namePlaceStrings[i] = jsonObject.getString("Nameplace");
                    addressStrings[i] = jsonObject.getString("Address");
                    openCloseStrings[i] = jsonObject.getString("OpenClose");
                    descriptionStrings[i] = jsonObject.getString("Discription");
                    imagePlaceStrings[i] = jsonObject.getString("ImagePlace");
                    imageServiceStrings[i] = jsonObject.getString("ImageService");

                }   // for

                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(TravelWheelChairActivity.this,
                        android.R.layout.simple_list_item_1, namePlaceStrings);
                listView.setAdapter(stringArrayAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent intent = new Intent(TravelWheelChairActivity.this, DetailTravel.class);

                        intent.putExtra("NamePlace", namePlaceStrings[i]);
                        intent.putExtra("Address", addressStrings[i]);
                        intent.putExtra("OpenClose", openCloseStrings[i]);
                        intent.putExtra("Description", descriptionStrings[i]);
                        intent.putExtra("ImagePlace", imagePlaceStrings[i]);
                        intent.putExtra("ImageService", imageServiceStrings[i]);

                        startActivity(intent);
                    }   // onItemClick
                });


            } catch (Exception e) {
                e.printStackTrace();
            }

        }   // onPost
        }

    public void clickBackTripListView(View view) {
        finish();

    }   // Connected Class
    public void clickBackCatListView(View view) {
        finish();
    }
    public void clickBackhome(View view) {
        startActivity(new Intent(TravelWheelChairActivity.this, MainActivity.class));
        finish();
    }

}   // Main Class
