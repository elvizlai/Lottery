package com.sdrzlyz.lottery;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by lyz on 13-12-24.
 */
public class Savadata {






    public void saveToSDCard(String filename, String content) throws Exception
    {
        File file = new File(Environment.getExternalStorageDirectory(), filename);
        FileOutputStream outStream = new FileOutputStream(file);
        outStream.write(content.getBytes());
        outStream.close();
    }

}
