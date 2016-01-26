package com.momoxiangbei.imageloader.ImageLoader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Created by Administrator on 2016/1/26.
 * 图片压缩
 */
public class ImageResizer {

    private static final String TAG = "ImageResizer";

    public ImageResizer(){}

    public static Bitmap decodeSimpleBitmapFromResource(Resources res, int resId, int reqWidth , int reqHeight){

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res,resId,options);
        options.inSampleSize =  calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res,resId,options);
    }


    public static Bitmap decodeSimpleBitmapFromFileDescripter(FileDescriptor fd, int resId, int reqWidth , int reqHeight){

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd, null, options);
        options.inSampleSize =  calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFileDescriptor(fd, null, options);
    }
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        int height = options.outHeight;
        int width = options.outWidth;
        int simpleSize = 1;

        int heightRetio = Math.round((float)height/(float)reqHeight);
        int widthRetio = Math.round((float)width/(float)reqWidth);
        simpleSize = heightRetio > widthRetio ? widthRetio : heightRetio;

        return simpleSize;
    }
}
