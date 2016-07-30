package appewtc.masterung.welovewheelchair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class trip_bts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_bts);
    }
    public void clickBackCatListView(View view) {
        finish();
    }

    public void clickBackhome(View view) {
        startActivity(new Intent(trip_bts.this, MainActivity.class));
        finish();

    }
}
