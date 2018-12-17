package com.jqsoft.fingertip_health.di.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.RegexUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.di.contract.RegisterContract;
import com.jqsoft.fingertip_health.di.module.RegisterModule;
import com.jqsoft.fingertip_health.di.presenter.RegisterPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils2.RegexUtil;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017-08-16.
 * 注册界面
 */

public class RegisterActivity extends AbstractActivity implements RegisterContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.iv_delete_photo)
    ImageView ivDeletePhoto;
    @BindView(R.id.acet_nickname)
    AppCompatEditText acetNickname;
    @BindView(R.id.bt_nickname_clear)
    Button btNicknameClear;
    @BindView(R.id.acet_id_card)
    AppCompatEditText acetIdCard;
    @BindView(R.id.bt_id_card_clear)
    Button btIdCardClear;
    @BindView(R.id.acet_phone)
    AppCompatEditText acetPhone;
    @BindView(R.id.bt_phone_clear)
    Button btPhoneClear;
    @BindView(R.id.acet_password)
    AppCompatEditText acetPassword;
    @BindView(R.id.bt_password_clear)
    Button btPasswordClear;
    @BindView(R.id.bt_password_eye)
    Button btPasswordEye;
    @BindView(R.id.bt_register)
    AppCompatButton btRegister;

    private String photoPath = null;
    private Bitmap photoBitmap = null;

    private final static int REQUEST_IMAGE = 200;

    @Inject
    RegisterPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);

        ivPhoto.setScaleType(ImageView.ScaleType.FIT_CENTER);
        RxView.clicks(ivPhoto)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object value) {
                        if (ActivityCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            // 应用没有读取手机外部存储的权限
                            // 申请WRITE_EXTERNAL_STORAGE权限
                            ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    100);
                        }else {
                            selectImage();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxView.clicks(ivDeletePhoto)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        ivPhoto.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        RegisterActivity.this.ivPhoto.setImageResource(R.mipmap.i_add);
                        RegisterActivity.this.photoPath=Constants.EMPTY_STRING;
                        RegisterActivity.this.photoBitmap=null;
                        ivDeletePhoto.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxTextView.textChanges(acetNickname)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence value) {
                        setNicknameClearButtonVisibility();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxView.clicks(btNicknameClear)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        acetNickname.setText(Constants.EMPTY_STRING);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxTextView.textChanges(acetIdCard)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence value) {
                        setIdCardClearButtonVisibility();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxView.clicks(btIdCardClear)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        acetIdCard.setText(Constants.EMPTY_STRING);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxTextView.textChanges(acetPhone)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence value) {
                        setPhoneClearButtonVisibility();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxView.clicks(btPhoneClear)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        acetPhone.setText(Constants.EMPTY_STRING);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxTextView.textChanges(acetPassword)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence value) {
                        setPasswordClearButtonVisibility();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        acetPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO){
                    onRegister();
                }
                return false;
            }
        });

        RxView.clicks(btPasswordClear)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        acetPassword.setText(Constants.EMPTY_STRING);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxView.clicks(btPasswordEye)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        onChangePasswordVisibility();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxView.clicks(btRegister)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        onRegister();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        setClearButtonVisibility();
    }

    private void onRegister() {
        if (!isInputValid()){
            return;
        }
        Map<String, String> map = ParametersFactory.getRegisterMapFromParameters(RegisterActivity.this, getEncodedHeadImageString(),
                getNickname(), getIdCard(), getPhone(), getPassword());
        RequestBody body = Util.getBodyFromMap(map);
        mPresenter.main(body);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addRegister(new RegisterModule(this))
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onChangePasswordVisibility() {
        int inputType = acetPassword.getInputType();
        if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            btPasswordEye.setBackgroundResource(R.mipmap.eye_open_blue);
            acetPassword.setInputType((InputType.TYPE_CLASS_TEXT));
        } else {
//            btPasswordEye.setBackgroundResource(R.mipmap.eye_close_purple);
            btPasswordEye.setBackgroundResource(R.mipmap.eye_open_gray);
            acetPassword.setInputType((InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
        }
        acetPassword.setSelection(acetPassword.getText().toString().length());
    }

    public void setClearButtonVisibility() {
        setNicknameClearButtonVisibility();
        setIdCardClearButtonVisibility();
        setPhoneClearButtonVisibility();
        setPasswordClearButtonVisibility();
    }

    private void setNicknameClearButtonVisibility(){
        String nickname = getNickname();
         if (nickname.length() > 0) {
            btNicknameClear.setVisibility(View.VISIBLE);
        } else {
            btNicknameClear.setVisibility(View.INVISIBLE);
        }

    }

    private void setIdCardClearButtonVisibility(){
        String idCard = getIdCard();
        if (StringUtils.isBlank(idCard)){
            btIdCardClear.setVisibility(View.INVISIBLE);
        } else {
            btIdCardClear.setVisibility(View.VISIBLE);
        }
    }

    private void setPhoneClearButtonVisibility(){
        String phone = getPhone();
        if (StringUtils.isBlank(phone)){
            btPhoneClear.setVisibility(View.INVISIBLE);
        } else {
            btPhoneClear.setVisibility(View.VISIBLE);
        }
    }

    private void setPasswordClearButtonVisibility(){
        String password = getPassword();
        if (StringUtils.isBlank(password)){
            btPasswordClear.setVisibility(View.INVISIBLE);
        } else {
            btPasswordClear.setVisibility(View.VISIBLE);
        }
    }


    private boolean isInputValid(){
        String nickname = getNickname();
        if (StringUtils.isBlank(nickname)){
            Util.showToast(this, getResources().getString(R.string.register_hint_nickname_empty));
            return false;
        } else if (StringUtils.length(nickname)<=0){
            Util.showToast(this, getResources().getString(R.string.register_hint_nickname_invalid));
            return false;
        }

        String idCard = getIdCard();
        if (StringUtils.isBlank(idCard)){
            Util.showToast(this, getResources().getString(R.string.register_hint_id_card_empty));
            return false;
        } else if (!RegexUtil.checkIdCard(idCard)) {
            Util.showToast(this, getResources().getString(R.string.register_hint_id_card_invalid));
            return false;
        }

        String phone = getPhone();
        if (StringUtils.isBlank(phone)){
            Util.showToast(this, getResources().getString(R.string.register_hint_phone_empty));
            return false;
        } else if (!RegexUtils.isMobileSimple(phone)){
            Util.showToast(this, getResources().getString(R.string.register_hint_phone_invalid));
            return false;
        }

        String password = getPassword();
        if (StringUtils.isBlank(password)){
            Util.showToast(this, getResources().getString(R.string.register_hint_password_empty));
            return false;
        } else if (StringUtils.length(password)<=0){
            Util.showToast(this, getResources().getString(R.string.register_hint_password_invalid));
            return false;
        }

        return true;
    }

    private String getEncodedHeadImageString(){
        if (photoBitmap==null){
            return Constants.EMPTY_STRING;
        } else {
            return bitmaptoString(photoBitmap);
        }
    }

    private String getNickname(){
        return Util.trimString(acetNickname.getText().toString());
    }

    private String getIdCard(){
        return Util.trimString(acetIdCard.getText().toString());
    }

    private String getPhone(){
        return Util.trimString(acetPhone.getText().toString());
    }

    private String getPassword(){
        return Util.trimString(acetPassword.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){

                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理你自己的逻辑 ....

                if(path != null && path.size() > 0){
                    photoPath = path.get(0);
                    photoBitmap = getImage(photoPath);
                    //   Util.hideGifProgressDialog(UpdatePeopleInfoActivity.this);
                    ivPhoto.setScaleType(ImageView.ScaleType.FIT_XY);
                    ivPhoto.setImageBitmap(photoBitmap);
                    ivDeletePhoto.setVisibility(View.VISIBLE);
//                    Util.hideGifProgressDialog(this);
                }
            }
        }

    }

    private void selectImage(){

        MultiImageSelector.create(this)
                .showCamera(true) // 是否显示相机. 默认为显示
//                .count(1) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                .single() // 单选模式
//                .multi() // 多选模式, 默认模式;
//                .origin(ArrayList<String>) // 默认已选择图片. 只有在选择模式为多选时有效
                .start(this, REQUEST_IMAGE);
//        Util.showGifProgressDialog(this);


    }

    /**
     * 获取压缩后的图片
     * @param srcPath
     * @return
     */
    private Bitmap getImage(String srcPath) {


        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }

    private Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;


        while (baos.toByteArray().length / 1024 > 100) {  // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            if(options<0){
                image.compress(Bitmap.CompressFormat.JPEG, 10, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                break;
            }else {
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            }

            options -= 10;// 每次都减少10
        }

       /* if(baos.toByteArray().length / 1024 > 100){
            image.compress(Bitmap.CompressFormat.JPEG, 10, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }else{
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }*/


        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    public String bitmaptoString(Bitmap bitmap) {

        // 将Bitmap转换成字符串



        ByteArrayOutputStream bStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream);

        byte[] bytes = bStream.toByteArray();

        String str = Base64.encodeToString(bytes, Base64.NO_WRAP);

        return str;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.clear();
//        getMenuInflater().inflate(R.menu.menu_register, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_take_photo:
//                Util.showToast(this, "拍照按钮被点击");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRegisterSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {
        Util.showToast(this, Constants.HINT_REGISTER_SUCCESS);
        finish();
    }

    @Override
    public void onRegisterFailure(String message) {
        Util.showToast(this, message);
//        Util.showToast(this, Constants.HINT_REGISTER_FAILURE);
    }
}
