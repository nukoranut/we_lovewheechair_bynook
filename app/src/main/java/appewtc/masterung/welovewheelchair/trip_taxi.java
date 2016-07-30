package appewtc.masterung.welovewheelchair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class trip_taxi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_taxi);
    }
    public void clickBackCatListView(View view) {
        finish();
    }
    public void clickBackhome(View view) {
        startActivity(new Intent(trip_taxi.this, MainActivity.class));
        finish();
    }
}
