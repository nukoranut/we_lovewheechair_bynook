package appewtc.masterung.welovewheelchair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailTravel extends AppCompatActivity {

    //Explicit
    private TextView nameTextView, addressTextView, openCloseTextView,
            descriptionTextView;
    private ImageView placeImageView, serviceImageView;
    private String nameString, addressString, openCloseString,
            descriptionString, placeString, serviceString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_travel);



        //Bind Widget
        nameTextView = (TextView) findViewById(R.id.textView17);
        addressTextView = (TextView) findViewById(R.id.textView41);
        openCloseTextView = (TextView) findViewById(R.id.textView42);
        descriptionTextView = (TextView) findViewById(R.id.textView43);
        placeImageView = (ImageView) findViewById(R.id.imageView31);
        serviceImageView = (ImageView) findViewById(R.id.imageView32);

        //Get Intent
        nameString = getIntent().getStringExtra("NamePlace");
        addressString = getIntent().getStringExtra("Address");
        openCloseString = getIntent().getStringExtra("OpenClose");
        descriptionString = getIntent().getStringExtra("Description");
        placeString = getIntent().getStringExtra("ImagePlace");
        serviceString = getIntent().getStringExtra("ImageService");


        //Show View
        nameTextView.setText(nameString);
        addressTextView.setText(addressString);
        openCloseTextView.setText(openCloseString);
        descriptionTextView.setText(descriptionString);
        Picasso.with(this).load(placeString).into(placeImageView);
        Picasso.with(this).load(serviceString).into(serviceImageView);


    }   // Main Method
    public void clickBackCatListView(View view) {
        finish();
    }
    public void clickBackhome(View view) {
        startActivity(new Intent(DetailTravel.this, MainActivity.class));
        finish();
    }

} // Main Class
