package puzzleleaf.tistory.com.custompinktheme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class IconInfo extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_info);

        Button ico = (Button)findViewById(R.id.makeIcon);
        ico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IconData.saveValue(getApplicationContext());
                Toast.makeText(getApplicationContext(),"아이콘으로 이용이 가능합니다.",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

}
