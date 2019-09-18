package com.example.myspannable;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.URLSpan;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edit1;
    TextView text1, text2, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.edit1);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);

        Spannable spannable = (Spannable)edit1.getText();
        spannable.setSpan(new ForegroundColorSpan(Color.RED), 6, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new RelativeSizeSpan(2.0f), 6, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        edit1.setText(spannable);

        SpannableString spannableString = new SpannableString(text2.getText());
        spannableString.setSpan(new URLSpan("http://sunrint.hs.kr"), 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text2.setText(spannableString);
        text2.setMovementMethod(new LinkMovementMethod());

        String html = "<br>Cliok <a href='https://sunrintl.hs.kr'>HERE</a> for more ...";
        text3.setText(Html.fromHtml(html));
        text3.setMovementMethod(new LinkMovementMethod());
    }
}
