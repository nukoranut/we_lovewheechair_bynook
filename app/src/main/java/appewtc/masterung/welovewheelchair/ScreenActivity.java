package appewtc.masterung.welovewheelchair;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ScreenActivity extends AppCompatActivity {

    private Handler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        myHandler  = new Handler();
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                Intent goMain = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(goMain);

            }
        }, 3000);

    }
}

