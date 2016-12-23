package com.larry.tools.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitmapToBase64 {
    /**
     * bitmap转成base64
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = "";
        ByteArrayOutputStream bos = null;
        try {
            if (null != bitmap) {
                bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                bos.flush();
                bos.close();
                byte[] bitmapByte = bos.toByteArray();
                result = Base64.encodeToString(bitmapByte, Base64.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 　　* 将base64转换成bitmap图片
     * <p/>
     * 　　*
     * <p/>
     * 　　* @param string base64字符串
     * <p/>
     * 　　* @return bitmap
     */

    public Bitmap stringtoBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string, Base64.DEFAULT);

            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 将byteToBitmap转换成Bitmap
     * @param imageBase64
     * @return
     */
    public Bitmap byteToBitmap(String imageBase64) {
        byte[] base64Bytes = Base64.decode(imageBase64.getBytes(), Base64.DEFAULT);
        Bitmap b = BitmapFactory.decodeByteArray(base64Bytes, 0, base64Bytes.length);
        return b;
    }

    /**
     * 将InputStream转换成Bitmap
     */
    public Bitmap InputStream2Bitmap(InputStream is) {
        return BitmapFactory.decodeStream(is);
    }
}

