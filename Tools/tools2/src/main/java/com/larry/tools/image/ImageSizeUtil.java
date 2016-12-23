package com.larry.tools.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;


public class ImageSizeUtil {

    private static int width = 720;
    private static int height = 1280;


    /**
     * 根据图片需要显示的宽和高对图片进行压缩
     *
     * @param imagePath
     * @param viewWidth
     * @param viewHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromPath(String imagePath,
                                                     int viewWidth, int viewHeight) {
        Bitmap bitmap = null;
        try {
            // 获得图片的宽和高，并不把图片加载到内存中
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imagePath, options);
            options.inSampleSize = ImageSizeUtil.caculateInSampleSize(options,
                    viewWidth, viewHeight);
            // 使用获得到的InSampleSize再次解析图片
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(imagePath, options);
        } catch (OutOfMemoryError e) {
            // TODO: handle exception
        }
        return bitmap;
    }

    /**
     * 获取 bitmap
     *
     * @param is
     * @return
     */
    public static SoftReference<Bitmap> getSoftBitmap(InputStream is) {
        Bitmap bitMap = getBitMap(is);
        SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(bitMap);
        return softBitmap;
    }

    /**
     * 获取 bitmap
     *
     * @param context
     * @param source
     * @return
     */
    public static SoftReference<Bitmap> getSoftBitmap(Context context,
                                                      int source) {
        Bitmap bitMap = getBitMap(context, source);
        SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(bitMap);
        return softBitmap;
    }

    /**
     * 获取 bitmap
     *
     * @param path
     * @return
     */
    public static SoftReference<Bitmap> getSoftBitmap(String path) {
        Bitmap bitMap = getBitMap(path);
        SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(bitMap);
        return softBitmap;
    }

    public static float setDip(Context context, float pxValue) {
        float scaleHeight = (float) ImageSizeUtil.height / (float) 2208;
        float scaleWidth = (float) ImageSizeUtil.width / (float) 1242;
        float scaleMetrics = scaleHeight > scaleWidth ? scaleHeight
                : scaleWidth;
        return px2dip(context, pxValue * scaleMetrics);

    }

    /**
     * 创建bitmap
     *
     * @param context
     * @return
     */
    private static Bitmap getBitMap(Context context, int resId) {
        InputStream is = null;
        Bitmap newBitmap = null;
        Bitmap bm = null;
        try {
            Options opt = new Options();
            // opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
            opt.inPurgeable = true;
            // opt.inSampleSize = caculateInSampleSize();
            opt.inInputShareable = true;
            is = context.getResources().openRawResource(resId);
            bm = BitmapFactory.decodeStream(is, null, opt);

            Matrix matrix = new Matrix();
            // 计算缩放率
            float scaleHeight = (float) ImageSizeUtil.height / 2208;
            float scaleWidth = (float) ImageSizeUtil.width / 1242;
            float scaleMetrics = scaleHeight > scaleWidth ? scaleHeight
                    : scaleWidth;
            matrix.postScale(scaleMetrics, scaleMetrics);
            newBitmap = Bitmap.createBitmap(bm, 0, 0, opt.outWidth,
                    opt.outHeight, matrix, true);
        } catch (OutOfMemoryError e) {
            e.getStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.getStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                // bm.recycle();
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }

        return newBitmap;
    }

    /**
     * 创建bitmap
     *
     * @param context
     * @return
     */
    public static Bitmap getBitMap2Size(Context context, int resId) {
        InputStream is = null;
        Bitmap newBitmap = null;
        Bitmap bm = null;
        try {
            Options opt = new Options();
            // opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
            opt.inPurgeable = true;
            opt.inSampleSize = 2;
            opt.inInputShareable = true;
            is = context.getResources().openRawResource(resId);
            bm = BitmapFactory.decodeStream(is, null, opt);
        } catch (Exception e) {
            // TODO: handle exception
            e.getStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                // bm.recycle();
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }

        return bm;
    }

    /**
     * 创建bitmap
     *
     * @param path
     * @return
     */
    private static Bitmap getBitMap(String path) {
        InputStream is = null;
        Bitmap newBitmap = null;
        Bitmap bm = null;
        try {
            is = new FileInputStream(path);
            Options opts = new Options();
            // opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
            opts.inPurgeable = true;
            // opts.inSampleSize = caculateInSampleSize();
            opts.inInputShareable = true;
            bm = BitmapFactory.decodeStream(is, null, opts);
            Matrix matrix = new Matrix();
            // 计算缩放率
            float scaleHeight = (float) ImageSizeUtil.height / (float) 2208;
            float scaleWidth = (float) ImageSizeUtil.width / (float) 1242;
            float scaleMetrics = scaleHeight > scaleWidth ? scaleHeight
                    : scaleWidth;
            matrix.postScale(scaleMetrics, scaleMetrics);
            newBitmap = Bitmap.createBitmap(bm, 0, 0, opts.outWidth,
                    opts.outHeight, matrix, true);

        } catch (OutOfMemoryError e) {
            e.getStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                // bm.recycle();
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        return newBitmap;
    }

    /**
     * 创建bitmap
     *
     * @param is
     * @return
     */
    private static Bitmap getBitMap(InputStream is) {
        Bitmap newBitmap = null;
        Bitmap bm = null;
        try {
            Options opt = new Options();
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            // opts.inSampleSize = caculateInSampleSize();
            bm = BitmapFactory.decodeStream(is, null, opt);
            Matrix matrix = new Matrix();
            // 计算缩放率
            float scaleHeight = (float) ImageSizeUtil.height / (float) 2208;
            float scaleWidth = (float) ImageSizeUtil.width / (float) 1242;
            float scaleMetrics = scaleHeight > scaleWidth ? scaleHeight
                    : scaleWidth;
            matrix.postScale(scaleMetrics, scaleMetrics);
            newBitmap = Bitmap.createBitmap(bm, 0, 0, opt.outWidth,
                    opt.outHeight, matrix, true);

        } catch (OutOfMemoryError e) {
            e.getStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // bm.recycle();

        }
        return newBitmap;
    }

    /**
     * 获取样板等比例压缩后的宽高
     */
    public static Options getImageOption(Context context,
                                         int cource) {
        Options opt = new Options();
        opt.inJustDecodeBounds = true;
        // opt.inSampleSize = caculateInSampleSize();
        BitmapFactory.decodeResource(context.getResources(), cource, opt);

        // 计算缩放率
        float scaleHeight = (float) ImageSizeUtil.height / (float) 2208;
        float scaleWidth = (float) ImageSizeUtil.width / (float) 1242;

        float scaleMetrics = scaleHeight > scaleWidth ? scaleHeight
                : scaleWidth;

        opt.outHeight = (int) (Math.floor(opt.outHeight * scaleMetrics) + 1);
        opt.outWidth = (int) (Math.floor(opt.outWidth * scaleMetrics) + 1);
        return opt;
    }

    /**
     * 根据手机像素取得 inSampleSize
     *
     * @return
     */
    public static float caculateInSampleSize() {
        // 效果图的宽和高
        int height = 2208;
        int width = 1242;

        float inSampleSize = 1;

        float heightRatio = (float) ImageSizeUtil.height / (float) height;
        float widthRatio = (float) ImageSizeUtil.width / (float) width;
        inSampleSize = heightRatio > widthRatio ? heightRatio : widthRatio;
        // inSampleSize = Math.max(widthRatio, heightRatio);
        return inSampleSize;
    }

    /**
     * 根据控件的宽和高以及图片实际的宽和高计算SampleSize
     *
     * @param options   图片的宽高
     * @param reqWidth  控件的宽
     * @param reqHeight 控件的高
     * @return
     */
    public static int caculateInSampleSize(Options options, int reqWidth,
                                           int reqHeight) {
        // 图片的宽和高
        int width = options.outWidth;
        int height = options.outHeight;

        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight) {
            int widthRadio = Math.round(width * 1.0f / reqWidth);
            int heightRadio = Math.round(height * 1.0f / reqHeight);
            inSampleSize = Math.max(widthRadio, heightRadio);
        }

        return inSampleSize;
    }

    /**
     * 根据ImageView获适当压缩的宽和高
     *
     * @param imageView
     * @return
     */
    public static ImageSize getImageViewSize(ImageView imageView) {

        ImageSize imageSize = new ImageSize();
        DisplayMetrics displayMetrics = imageView.getContext().getResources()
                .getDisplayMetrics();

        LayoutParams lp = imageView.getLayoutParams();

        int width = imageView.getWidth();// 获取imageview的实际宽度
        if (width <= 0) {
            width = lp.width;// 获取imageview在layout中声明的宽度
        }
        if (width <= 0) {
            // width = imageView.getMaxWidth();// 检查最大值
            width = getImageViewFieldValue(imageView, "mMaxWidth");
        }
        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }

        int height = imageView.getHeight();// 获取imageview的实际高度
        if (height <= 0) {
            height = lp.height;// 获取imageview在layout中声明的宽度
        }
        if (height <= 0) {
            height = getImageViewFieldValue(imageView, "mMaxHeight");// 检查最大值
        }
        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }
        imageSize.width = width;
        imageSize.height = height;

        return imageSize;
    }

    public static class ImageSize {
        public int width;
        public int height;
    }

    /**
     * 通过反射获取imageview的某个属性值 为了兼容低版本
     *
     * @param object
     * @param fieldName
     * @return
     */
    private static int getImageViewFieldValue(Object object, String fieldName) {
        int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = field.getInt(object);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                value = fieldValue;
            }
        } catch (Exception e) {
        }
        return value;

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        int sizeInt = (int) (dpValue * scale);
        sizeInt += 1;
        return sizeInt;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        int sizeInt = (int) (pxValue / scale * caculateInSampleSize());
        sizeInt += 1;
        return sizeInt;
    }
}
