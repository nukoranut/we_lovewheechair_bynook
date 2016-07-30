package appewtc.masterung.welovewheelchair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ChooseSection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_section);
    }   // Main Method

    public void clickCategory1(View view) {
        myIntent("ภาคกลาง");
    }

    public void clickCategory2(View view) {
        myIntent("ภาคตะวันออก");
    }

    public void clickCategory3(View view) {
        myIntent("ภาคตะวันออกเฉียงเหนือ");
    }

    public void clickCategory4(View view) {
        myIntent("ภาคใต้");
    }

    public void clickCategory5(View view) {
        myIntent("ภาคเหนือ");
    }

    private void myIntent(String strCategory) {

        Intent intent = new Intent(ChooseSection.this, CategoryListView.class);
        intent.putExtra("Category", strCategory);
        startActivity(intent);

    }
    public void clickBackCatListView(View view) {
        finish();
    }
    public void clickBackhome(View view) {
    startActivity(new Intent(ChooseSection.this, MainActivity.class));
        finish();
    }
}   // Main Class
