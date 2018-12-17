package com.jqsoft.fingertip_health.di.ui.activity.webview;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by YLL on 2018-7-5.
 */

public class ImageUtil {

    private static final String TAG ="ImageUtil";

    /**
     * go for Album.    相册
     */
    public static final Intent choosePicture() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        return Intent.createChooser(intent, null);
    }

    /**
     * go for camera.   相机
     */
    public static final Intent takeBigPicture(Context context) {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      //  intent.putExtra(MediaStore.EXTRA_OUTPUT, newPictureUri(context,getNewPhotoPath()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
            Uri uriForFile = FileProvider.getUriForFile(context, "com.jqsoft.fingertiphealthyquanmin.fileprovider", new File(getDirPath() + "/" + System.currentTimeMillis() + ".jpg"));
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
            intentFromCapture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intentFromCapture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, newPictureUri(context,getNewPhotoPath()));
        }



        return intentFromCapture;
    }

    public static final String getDirPath() {
        return Environment.getExternalStorageDirectory().getPath() + "/UploadImage";
    }

    private static final String getNewPhotoPath() {
        return getDirPath() + "/" + System.currentTimeMillis() + ".jpg";
    }

    public static final String retrievePath(Context context, Intent sourceIntent, Intent dataIntent) {
        String picPath = null;
        try {
            Uri uri;
            if (dataIntent != null) {
                uri = dataIntent.getData();
                if (uri != null) {
                    picPath = ContentUtil.getPath(context, uri);
                }
                if (isFileExists(picPath)) {
                    return picPath;
                }

                Log.w(TAG, String.format("retrievePath failed from dataIntent:%s, extras:%s", dataIntent, dataIntent.getExtras()));
            }

            if (sourceIntent != null) {
                uri = sourceIntent.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
               String s= uri.getAuthority();
                if (uri != null) {
                    String scheme = uri.getScheme();
                    if (scheme != null && scheme.startsWith("file")) {
                        picPath = uri.getPath();
                    }else {
                        if (scheme != null && scheme.startsWith("content")) {
                            //  picPath = uri.getPath();
//
//                       String s7= uri.getPath().substring( uri.getPath().indexOf("uploadImage")+1);
//                        picPath = "file:///storage/emulated/0"+s7;

//                        picPath=ContentUriUtil.getPath(context, uri);
//                        picPath=getAbsolutePath(context, uri);
                            picPath = getFromMediaUri(context, context.getContentResolver(), uri);

                            File file = new File(picPath);
                            int degree = FileUtils.readPictureDegree(picPath);
                            rotateImage(degree, file);
//                        Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
//                        if ( null != cursor ) {
//                            if ( cursor.moveToFirst() ) {
//                                int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
//                                if ( index > -1 ) {
//                                    picPath = cursor.getString( index );
//                                }
//                            }
//                            cursor.close();
//                        }
                        }
                    }
                }
                if (!TextUtils.isEmpty(picPath)) {
                    File file = new File(picPath);
                    if (!file.exists() || !file.isFile()) {
                        Log.w(TAG, String.format("retrievePath file not found from sourceIntent path:%s", picPath));
                    }
                }
            }
            return picPath;
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            Log.d(TAG, "retrievePath(" + sourceIntent + "," + dataIntent + ") ret: " + picPath);
        }
        return picPath;
    }


    /**
     * 判断拍照 图片是否旋转
     *
     * @param degree
     * @param file
     */
    private static  void  rotateImage(int degree, File file) {
        if (degree > 0) {
            // 针对相片有旋转问题的处理方式
            try {
                BitmapFactory.Options opts = new BitmapFactory.Options();//获取缩略图显示到屏幕上
                opts.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
                Bitmap bmp = FileUtils.rotaingImageView(degree, bitmap);
                FileUtils.saveBitmapFile(bmp, file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * 旋转图片
    * @param angle
    * @param bitmap
    * @return Bitmap
    */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }




    /**
     * 通过uri和selection来获取真实的图片路径,从相册获取图片时要用
     */
    private static String getImagePath(Context context, Uri uri, String selection) {
        String path = null;
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }



    private static final Uri newPictureUri(Context context,String path) {
        Uri imageUri=null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            String authority = getPackageName() + ".provider";
//            imageUri = FileProvider.getUriForFile(mContext, authority, new File(path));//通过FileProvider创建一个content类型的Uri
//        } else {
//            imageUri = Uri.fromFile(new File(path));
//        }

        imageUri = Uri.fromFile(new File(path));
        return imageUri;
    }

    private static final boolean isFileExists(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        File f = new File(path);
        if (!f.exists()) {
            return false;
        }
        return true;
    }

    /**
     * Gets the corresponding path to a file from the given content:// URI
     * @param selectedVideoUri The content:// URI to find the file path from
     * @param contentResolver The content resolver to use to perform the query.
     * @return the file path as a string
     */
    public static String getFilePathFromContentUri(Uri selectedVideoUri,
                                                   ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
//      也可用下面的方法拿到cursor
//      Cursor cursor = this.context.managedQuery(selectedVideoUri, filePathColumn, null, null, null);

        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    public static String getAbsolutePath(Context context, Uri uri) {
        if(Build.VERSION.SDK_INT >= 19){
            String lastPathSegment = uri.getLastPathSegment();
            String id = lastPathSegment.split(":")[1];
            final String[] imageColumns = {MediaStore.Images.Media.DATA };
            final String imageOrderBy = null;
            Uri tempUri = uri;
            Cursor imageCursor = context.getContentResolver().query(tempUri, imageColumns,
                    MediaStore.Images.Media._ID + "="+id, null, imageOrderBy);
            if (imageCursor.moveToFirst()) {
                return imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }else{
                return null;
            }
        }else{
            String[] projection = { MediaStore.MediaColumns.DATA };

            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            } else
                return null;
        }

    }


    private static String getFromMediaUri(Context context, ContentResolver resolver, Uri uri) {
        if (uri == null) return null;

        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            ParcelFileDescriptor pfd = resolver.openFileDescriptor(uri, "r");
            if (pfd == null) {
                return null;
            }
            FileDescriptor fd = pfd.getFileDescriptor();
            input = new FileInputStream(fd);

            String tempFilename = getTempFilename(context);
            output = new FileOutputStream(tempFilename);

            int read;
            byte[] bytes = new byte[4096];
            while ((read = input.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }

            return new File(tempFilename).getAbsolutePath();
        } catch (Exception ignored) {

            ignored.getStackTrace();
        } finally {
            closeSilently(input);
            closeSilently(output);
        }
        return null;
    }
    private static String getTempFilename(Context context) throws IOException {
        File outputDir = context.getCacheDir();
        File outputFile = File.createTempFile("image", "tmp", outputDir);
        return outputFile.getAbsolutePath();
    }
    public static void closeSilently(@Nullable Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (Throwable t) {
            // Do nothing
        }
    }
}
