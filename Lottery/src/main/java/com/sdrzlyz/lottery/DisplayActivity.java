package com.sdrzlyz.lottery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by lyz on 13-12-20.
 */
public class DisplayActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayresult_displayactivity);

        Intent intent=getIntent();
        String stringfromedit=intent.getStringExtra(MainActivity.EXTRA_geteditText);


        TextView textView=new TextView(this);
        textView.setTextSize(40);
        textView.setText(stringfromedit);

        setContentView(textView);
    }
}
