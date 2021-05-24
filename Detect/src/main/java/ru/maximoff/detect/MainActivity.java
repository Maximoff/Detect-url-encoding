package ru.maximoff.detect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		final TextView tv = findViewById(R.id.mainTextView1);
		final EditText et = findViewById(R.id.mainEditText1);
		final Button bt = findViewById(R.id.mainButton1);
		bt.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View p1) {
					String str = et.getText().toString();
					if (str.length() > 0) {
						try {
							tv.setText(DetectEncoding.detectNameEncoding(str));
						} catch (Exception e) {
							tv.setText("Error: " + e.getMessage());
						}
					}
				}
			});
    }
}
