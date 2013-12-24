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
        setContentView(R.layout.displayresult);

        Intent intent=getIntent();
        String getfromMain=intent.getStringExtra(MainActivity.EXTRA_geteditText);
        Lottery lottery=new Lottery(getfromMain);
        String Redball="";
        for (int i=0;i<6;i++){
            Redball+=lottery.getRedball()[i]+" ";
        }
        String Blueball=lottery.getBlueball()+"";

        TextView textView1= (TextView) findViewById(R.id.textView1);
        TextView textView2= (TextView) findViewById(R.id.textView2);

        textView1.setText(Redball);
        textView2.setText(Blueball);
        ///////////////////////////
        try {
            new Savadata().saveToSDCard("LOTTERY.TXT","19890106");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
