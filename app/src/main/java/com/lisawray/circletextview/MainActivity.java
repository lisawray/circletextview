package com.lisawray.circletextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircleTextView textView = (CircleTextView) findViewById(R.id.circle_text_view);
        textView.setTextSize(R.dimen.text_size);

        SpannableStringBuilder builder = new SpannableStringBuilder(getResources().getString(R.string.hello_world));
        textView.setText(builder);
    }
}
