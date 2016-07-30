package appewtc.masterung.welovewheelchair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailShop extends AppCompatActivity {

    //Explicit
    private TextView nameTextView, addressTextView, phoneTextView,
            categoryTextView;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shop);

        //Bind Widget
        nameTextView = (TextView) findViewById(R.id.textView34);
        addressTextView = (TextView) findViewById(R.id.textView35);
        phoneTextView = (TextView) findViewById(R.id.textView32);
        categoryTextView = (TextView) findViewById(R.id.textView33);
        imageView = (ImageView) findViewById(R.id.imageView28);

        //Show View
        String strName = getIntent().getStringExtra("Name");
        nameTextView.setText(strName);

        String strAddress = getIntent().getStringExtra("Address");
        addressTextView.setText(strAddress);

        String strPhone = getIntent().getStringExtra("Phone");
        phoneTextView.setText(getResources().getString(R.string.textView32) + " = " + strPhone);

        String strCategory = getIntent().getStringExtra("Category");
        categoryTextView.setText(strCategory);

        int intImage = getIntent().getIntExtra("Image", R.drawable.first_hand);
        imageView.setImageResource(intImage);

    }   // Main Method

    public void clickBackCatListView(View view) {
        finish();
    }

    public void clickBackhome(View view) {
        startActivity(new Intent(DetailShop.this, MainActivity.class));
        finish();
    }
}   // Main Class
