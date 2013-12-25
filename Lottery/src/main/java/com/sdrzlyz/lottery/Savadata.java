package com.sdrzlyz.lottery;

import android.net.NetworkInfo;
import android.os.Environment;
import junit.framework.Test;

import java.io.*;

/**
 * Created by lyz on 13-12-24.
 */
public class Savadata {
    private final String fileName = "LOTTERY.TXT";
    boolean mExternalStorageAvailable = false;
    boolean mExternalStorageWriteable = false;
    String Exstate = Environment.getExternalStorageState();


    public Savadata(String content) {
        //先执行ExAvailable()检查SD卡是否可以读写
        ExAvailable();
        //若可读写，则写入
        if (mExternalStorageAvailable && mExternalStorageWriteable) {
            try {
                saveToSDCard(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void ExAvailable() {

        if (Environment.MEDIA_MOUNTED.equals(Exstate)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(Exstate)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
    }


    private void saveToSDCard(String content) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        RandomAccessFile output = new RandomAccessFile(file, "rw");
        output.seek(file.length());
        output.write(content.getBytes());
        output.close();


    }

}
