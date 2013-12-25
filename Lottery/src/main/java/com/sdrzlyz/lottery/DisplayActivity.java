package com.sdrzlyz.lottery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lyz on 13-12-20.
 */
public class DisplayActivity extends Activity {

    String getfromMain = null;
    Lottery lottery = null;
    String Redball = "";
    String Blueball = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayresult);

        Intent intent = getIntent();
        getfromMain = intent.getStringExtra(MainActivity.EXTRA_geteditText);
        lottery = new Lottery(getfromMain);

        for (int i = 0; i < 6; i++) {
            Redball += lottery.getRedball()[i] + " ";
        }
        Blueball = lottery.getBlueball() + "";

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);

        textView1.setText(Redball);
        textView2.setText(Blueball);
        ///////////////////////////

    }

    public void click2SaveData(View view) {
        new Savadata("INPUT:" + getfromMain + "\n" + "MD5:" + lottery.getMD5() + "\n" + "Redball:" + Redball + "\n" + "Blueball:" + Blueball + "\n" +"TIME:"+ lottery.getDate()+"\n"+"==================================="+"\n");
    }
}
