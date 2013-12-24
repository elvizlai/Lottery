package com.sdrzlyz.lottery;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by lyz on 13-12-23.
 */
public final class Lottery {

    //private final int total=6;
    private String str;
    private int[] Redball;
    private int Blueball;




    //构造方法
    public Lottery(String input){
        str=input+DataToday();
        Redball=disRepeat(MD5split(MD5(str)));
    }

    public int getBlueball(){
        return Blueball;
    }

    public int[] getRedball(){
        return Redball;
    }

    //得到当天的日期
    private String DataToday(){
        Calendar time=Calendar.getInstance();
        Date date=time.getTime();
        SimpleDateFormat today=new SimpleDateFormat("yyyy-MM-dd");
        return today.format(date);
    }

    //MD5计算
    //返回str
    private String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //block用于存储MD5分割后的字段，前4组按5位分割，后2组按6位分割
    //分割后将16进制数字转换为10进制数组，存储于数组hexstr2int中
    //返回10进制数组hex2dec
    private int[] MD5split(String str){
        String[] block=new String[6];
        int[] hex2dec=new int[6];
        for (int i=0,j=0;i<6;i++){
            if (i<4){
                block[i]=str.substring(j,j+=5);
                hex2dec[i]=Integer.parseInt(block[i],16);
                Blueball+=hex2dec[i];
            }else{
                block[i]=str.substring(j,j+=6);
                hex2dec[i]=Integer.parseInt(block[i],16);
                Blueball+=hex2dec[i];
            }
        }
        Blueball=Blueball%16+1;
       for (int i=0;i<6;i++){
           hex2dec[i]=hex2dec[i]%33+1;
       }
        return hex2dec;
    }

    private int[] disRepeat(int[] num) {
        boolean flag = true;
        int[] res = new int[0];
        int lyz=1;
        while (flag) {
            res = Filter(num);
            if (res.length == 6) {
                flag = false;
            } else {
                int[] temp=MD5split(MD5(str+lyz));
                lyz++;
                for (int i = 0; i < res.length; i++) {
                    num[i] = res[i];
                }
                for (int i = res.length; i < num.length; i++) {
                    num[i] = temp[i];
                }
            }
        }
        Arrays.sort(res);
        return res;
    }

    private int[] Filter(int[] num){
        HashSet<Integer> hs=new HashSet<Integer>();
        for(int i: num){
            hs.add(i);
        }
        Object[] obj=hs.toArray();  //转换为OBJ数组
        int[] number=new int[obj.length];  //存放新的数据
        //复制数据
        for(int i=0;i<hs.size();i++){
            number[i]=(Integer)obj[i];
        }
        return number;
    }

}
