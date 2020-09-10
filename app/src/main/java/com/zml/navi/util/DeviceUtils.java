package com.zml.navi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author : zmliang
 * @package: com.zml.navi.util
 * @date on: 2020/9/7 9:29
 * @company: qvbian
 **/
public class DeviceUtils {


    static String[] hexArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /***
     * 获取指定的字符串的MD5
     */
    public static String CalcMD5(String originString) {
        try {
            //创建具有MD5算法的信息摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
            byte[] bytes = md.digest(originString.getBytes());
            //将得到的字节数组变成字符串返回
            String s = byteArrayToHex(bytes);
            return s.toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 将字节数组转换成十六进制，并以字符串的形式返回
     * 128位是指二进制位。二进制太长，所以一般都改写成16进制，
     * 每一位16进制数可以代替4位二进制数，所以128位二进制数写成16进制就变成了128/4=32位。
     */
    private static String byteArrayToHex(byte[] b){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(byteToHex(b[i]));
        }
        return sb.toString();
    }
    /**
     * 将一个字节转换成十六进制，并以字符串的形式返回
     */
    public static String byteToHex(byte b) {
        int n = b;
        if (n < 0) {
            n = n + 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexArray[d1]+hexArray[d2];
    }

    public static String uniqueDeviceID(){
        String hardwareInfo = android.os.Build.BOARD + android.os.Build.BRAND + android.os.Build.DEVICE + android.os.Build.DISPLAY
                + android.os.Build.HOST + android.os.Build.ID + android.os.Build.MANUFACTURER + android.os.Build.MODEL
                + android.os.Build.PRODUCT + android.os.Build.TAGS + android.os.Build.TYPE +android.os. Build.USER;

        return CalcMD5(hardwareInfo);
    }

}