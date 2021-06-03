package ru.maximoff.detect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.net.URLDecoder;

public class MainActivity extends Activity {
	private final String testUrl = "https://ds-blobs-2.cdn.devapps.ru/21994255/%F4%E0%E9%EB+%F1+%E8%EC%E5%ED%E5%EC+%EA%E8%F0%E8%EB%EB%E8%F6%E5%E9.txt?s=004383d9d9429d1c60b8975a0000000045b7f3afb59d2c90f07c9e35cc413865";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		final TextView tv = findViewById(R.id.mainTextView1);
		final EditText et = findViewById(R.id.mainEditText1);
		et.setText(testUrl);
		final Button bt = findViewById(R.id.mainButton1);
		bt.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View p1) {
					String str = et.getText().toString();
					if (str.length() > 0) {
						try {
							String encoding = DetectEncoding.detectNameEncoding(str);
							tv.setText(encoding + ": " + URLDecoder.decode(str, encoding));
						} catch (Exception e) {
							tv.setText("Error: " + e.getMessage());
						}
					}
				}
			});
    }
}
