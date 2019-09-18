package com.example.myspannable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    TextView spanText, htmlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        spanText = findViewById(R.id.text1);
        htmlText = findViewById(R.id.text2);

        // 제목 글자 크기, 글자색, 이미지 추가
        String spanString = getResources().getString(R.string.span_string);
        SpannableStringBuilder builder = new SpannableStringBuilder(spanString);
        int start = spanString.indexOf("소금사막");
        int end = start + "소금사막".length();
        builder.setSpan(new ForegroundColorSpan(0xFFFF0000), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new RelativeSizeSpan(2.0f), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main2Activity.this, "제목 클릭", Toast.LENGTH_SHORT).show();
            }
        };
        builder.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 이미지
        start = spanString.indexOf("img");
        end = start + "img".length();
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.img01, null);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        builder.setSpan(new ImageSpan(drawable), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanText.setText(builder);
        spanText.setMovementMethod(new LinkMovementMethod());

        // Html.fromHtml()
        String html = getResources().getString(R.string.html_string);
        htmlText.setText(Html.fromHtml(html, new MyImageGetter(), null));
        htmlText.setMovementMethod(new LinkMovementMethod());
    }

    private class MyImageGetter implements Html.ImageGetter {
        @Override
        public Drawable getDrawable(String src) {
            if(src.equals("img02")){
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.img02, null);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                return drawable;
            }
            return null;
        }
    }
}
