package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.ReceptionNewListAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NewsListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi.PersonLocationBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.ReceptionDetailBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.ReceptionListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.ReceptionDetailActivityContract;
import com.jqsoft.fingertip_health.di.module.ReceptionDetailActivityModule;
import com.jqsoft.fingertip_health.di.presenter.ReceptionDetailActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.activity.map_navi.BusRouteActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.NotInputDialog;
import com.jqsoft.fingertip_health.util.tts.TTSController;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.GlideUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.model.PictureConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jqsoft.fingertip_health.adapter.ReceptionNewListAdapter.TYPE_MULTIPLE_LINE;
import static com.jqsoft.fingertip_health.di.ui.activity.map_navi.MapServiceActivity.INFO_NULL_CANT_NAVIGATE;

/**
 *
 * 受理中心详情
 */

public class ReceptionDetailActivity extends AbstractActivity  implements    ReceptionDetailActivityContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
//    @BindView(R.id.tv_author)
//    TextView tvAuthor;
//    @BindView(R.id.tv_datetime)
//    TextView tvDatetime;

    @BindView(R.id.policy_title)
    TextView title;
    @BindView(R.id.im_banner)
    ImageView im_banner;
    @BindView(R.id.Collection)
    TextView tv_Collection;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_officetime)
    TextView tv_officetime;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.img_Collection)
    ImageView img_Collection;
    String CollectionId;
    @Inject
    ReceptionDetailActivityPresenter mPresenter;
    @BindView(R.id.ll_Collection)
    LinearLayout ll_Collection;
    private ReceptionListBean receptionListBean;
    private  String ReceptionId;
    JSONObject obj;
    String bodyJson;
    String name;
    private ReceptionNewListAdapter mAdapter;
    ReceptionDetailBean receptionDetailBean;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILED = 1;

    TTSController mTtsManager;
    AMapNavi mAMapNavi;

    private void initTtsController(){
        try {
            mTtsManager = TTSController.getInstance(getApplicationContext());
            mTtsManager.init();
        }catch (Exception e){

        }


    }

    private void initNaviListener(){
        mAMapNavi = AMapNavi.getInstance(getApplicationContext());
//        mAMapNavi.addAMapNaviListener(this);
        mAMapNavi.addAMapNaviListener(mTtsManager);
        mAMapNavi.setEmulatorNaviSpeed(60);

    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addReceptionDetailActivity(new ReceptionDetailActivityModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reception_detail_layout;
    }

    @Override
    protected void initData() {
        ReceptionId=(String) getDeliveredSerializableByKey(Constants.RELIEF_DETAIL_ACTIVITY_KEY);

//        receptionListBean=(ReceptionListBean)getDeliveredSerializableByKey(Constants.RELIEF_DETAIL_ACTIVITY_KEY);
    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);

        initTtsController();
        initNaviListener();


        name = IdentityManager.getLoginSuccessUsername(getApplicationContext());
        ButterKnife.bind(this);
        ll_Collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (img_Collection.isSelected()==false){
                    tv_Collection.setText("收藏");
                    img_Collection.setSelected(true);
                img_Collection.setImageDrawable(getResources().getDrawable(R.mipmap.star_full));
                    Collection();
                }
                else {

                    tv_Collection.setText("取消收藏");
                    removeCollection();
                    img_Collection.setSelected(false);
                    img_Collection.setImageDrawable(getResources().getDrawable(R.mipmap.star_empty));
                }
            }
        });

        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_phone.getText()==null){

                }else {
                    call(tv_phone.getText().toString());
                }
            }
        });
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));


        final BaseQuickAdapter<NewsListBean, BaseViewHolder> mAdapter = new ReceptionNewListAdapter(new ArrayList<NewsListBean>(), TYPE_MULTIPLE_LINE,this);
        this.mAdapter = (ReceptionNewListAdapter) mAdapter;
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
//               Util.showToast(PoliticsActivity.this, position+" is clicked");
                NewsListBean pb = mAdapter.getItem(position);
//
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.RECRPTION_NEWLIST_ACTIVITY_KEY,   pb);
//
                    Util.gotoActivityWithBundle(ReceptionDetailActivity.this, ReceptionDetailNewListActivity.class, bundle);
            }
        });



        tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String address=receptionDetailBean.getAddress();
                String Latitude=receptionDetailBean.getLatitude();//纬度
                String Longitude= receptionDetailBean.getLongitude();
                PersonLocationBean pb = new PersonLocationBean();
                pb.setAddress(address);
                pb.setLat(Latitude);
                pb.setLng(Longitude);

                startNavi(pb);
//                private TextView content；
//                String showText="我是多行文字，我末尾需要添加一张图片";
//注意此处showText后+ " "主要是为了占位
//                SpannableString ss = new SpannableString(receptionDetailBean.getAddress());
//                int len = ss.length();
//                //图片
//                Drawable d = ContextCompat.getDrawable(ReceptionDetailActivity.this, (R.mipmap.ic_weizhi));
//                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
//                //构建ImageSpan
//                ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
//                ss.setSpan(span, len - 1, len, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//                tv_address.setText(ss);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(Constants.RECRPTION_NEWLIST_ACTIVITY_KEY,   pb);
//                Util.gotoActivityWithBundle(ReceptionDetailActivity.this, ReceptionMapServiceActivity.class, bundle);

//                Intent intent = new Intent(this, RouteNaviActivity.class);
//                intent.putExtra(Constants.GPS_KEY, true);
//                intent.putExtra(Constants.START_KEY, new NaviLatLng(currentLatitude, currentLongitude));
//                double dstLat = Util.getDoubleValueFromString(pb.getLat());
//                double dstLon = Util.getDoubleValueFromString(pb.getLng());
//                intent.putExtra(Constants.END_KEY, new NaviLatLng(dstLat, dstLon));
//                startActivity(intent);



            }
        });


        im_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<LocalMedia> selectMedia = new ArrayList<>();
                selectMedia.clear();

                LocalMedia localMedia = new LocalMedia();
                localMedia.setUrl(Version.FILE_URL_BASE+receptionDetailBean.getFilePath());
                localMedia.setPath("test");
                localMedia.setType(1);
                localMedia.setFileId(receptionDetailBean.getFilePath());
                selectMedia.add(localMedia);

                PictureConfig.getInstance().externalPicturePreview(	ReceptionDetailActivity.this, 0, selectMedia);
            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();
        mTtsManager.stopSpeaking();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAMapNavi.stopNavi();
//		mAMapNavi.destroy();
        mTtsManager.destroy();
        mAMapNavi.removeAMapNaviListener(mTtsManager);

    }

    public void startNavi(final PersonLocationBean plb) {
        if (plb == null) {
            Util.showToast(this, INFO_NULL_CANT_NAVIGATE);
            return;
        }


//        if (!Util.isLongitudeValid(String.valueOf(currentLongitude))) {
//            Util.showToast(this, "当前经度不合法");
//            return;
//        } else if (!Util.isLatitudeValid(String.valueOf(currentLatitude))) {
//            Util.showToast(this, "当前纬度不合法");
//            return;
//        }

        showNavigateMethod(plb);

    }

    public void showNavigateMethod(final PersonLocationBean dstBean){
        if (dstBean==null){
            Util.showToast(this, "导航目标点为空");
            return;
        }
        String[] typeArray = new String[]{"驾车", "步行", "骑行", "公交"};
        List<String> typeList = Arrays.asList(typeArray);
//        Util.showSingleChoiceStringListMaterialDialog(this, "请选择导航类型", Constants.EMPTY_STRING, typeList,
        Util.showSingleChoiceStringListMaterialDialogWithOkButton(this, "请选择导航类型", null, typeList,
                        0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        dialog.dismiss();
                        if (3 == which){
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.DST_LATITIDE_KEY, dstBean.getLat());
                            bundle.putString(Constants.DST_LONGITUDE_KEY, dstBean.getLng());
                            Util.gotoActivityWithBundle(ReceptionDetailActivity.this, BusRouteActivity.class, bundle);
                        } else {
                            AmapNaviType naviType = AmapNaviType.DRIVER;
                            if (0 == which){
                                naviType=AmapNaviType.DRIVER;
                            } else if (1 == which){
                                naviType=AmapNaviType.WALK;
                            } else if (2 == which){
                                naviType=AmapNaviType.RIDE;
                            }
//                            LatLng myLocationLatLng = new LatLng(currentLatitude, currentLongitude);
                            LatLng dstLatLng = getLatLngFromPersonLocationBean(dstBean);
//                            Poi myLocationPoi = new Poi("我的位置", myLocationLatLng, "");
                            Poi dstPoi = new Poi(dstBean.getAddress(), dstLatLng, "");
                            AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), new AmapNaviParams(null, null, dstPoi, naviType), new INaviInfoCallback() {
                                @Override
                                public void onInitNaviFailure() {

                                }

                                @Override
                                public void onGetNavigationText(String s) {
                                    mTtsManager.startSpeaking(s);
                                }

                                @Override
                                public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

                                }

                                @Override
                                public void onArriveDestination(boolean b) {

                                }

                                @Override
                                public void onStartNavi(int i) {

                                }

                                @Override
                                public void onCalculateRouteSuccess(int[] ints) {

                                }

                                @Override
                                public void onCalculateRouteFailure(int i) {

                                }

                                @Override
                                public void onStopSpeaking() {
                                    mTtsManager.stopSpeaking();
                                }
                            });

                        }

                        return false;
                    }
                });
    }

    @NonNull
    private LatLng getLatLngFromPersonLocationBean(PersonLocationBean plb) {
        String latString = plb.getLat();
        String lonString = plb.getLng();
        double latDouble = Util.getDoubleValueFromString(latString);
        double lonDouble = Util.getDoubleValueFromString(lonString);
        return new LatLng(latDouble, lonDouble);
    }



    @Override
    protected void loadData() {
        //判断是否从 我的收藏 进入  1 是 2 不是

        if (("1").equals(Util.getFromCollection())){
            Map<String, String> map = ParametersFactory.getGCAReceptionDetailMapforconcetion(this,name,
                    "collectionData.queryMyCollection",ReceptionId);
            mPresenter.Collectionmain(map);

        }else {

            Map<String, String> map = ParametersFactory.getGCAReceptionDetailMap(this,name,
                    "receptionData.queryReceptionDetail",ReceptionId);
            mPresenter.main(map, false);
        }
//        Map<String, String> map = ParametersFactory.getGCAReceptionDetailMap(this,"admin",
//                "receptionData.queryReceptionDetail",receptionListBean.getReceptionId());

    }
    private  void  removeCollection(){



            Map<String, String> map = ParametersFactory.getGCAReliefremovecollectionMap(this,
                    CollectionId,"collectionData.removeCollection");
            mPresenter.removeCollection(map);



    }
    private  void  Collection(){

        if ("1".equals(Util.getFromCollection())){
            Map<String, String> map = ParametersFactory.getGCAReceptionDetailCollectionMap(this,name,
                    "receptionData.collectReception", ReceptionId );
            mPresenter.Collection(map);
        }else {
            obj.toString();
            Map<String, String> map = ParametersFactory.getGCAReceptionDetailCollectionMap(this,name,
                    "receptionData.collectReception", obj.toString() );
            mPresenter.Collection(map);
        }


    }


    @Override
    public void onLoadListSuccessfromCollection(GCAHttpResultBaseBean<List<ReceptionDetailBean>> bean) {
        if (bean!=null){

            receptionDetailBean= bean.getData().get(0);
            CollectionId= receptionDetailBean.getCollectionId();
            if ("1".equals(Util.getFromCollection())){
                img_Collection.setSelected(true);
                tv_Collection.setText("取消收藏");
                img_Collection.setImageDrawable(getResources().getDrawable(R.mipmap.star_full));

            }else {
                img_Collection.setSelected(false);
                tv_Collection.setText("收藏");
                img_Collection.setImageDrawable(getResources().getDrawable(R.mipmap.star_empty));

            }

//            tv_newlist.setText(receptionDetailBean.getNewsList().get(0).getTitle());
            title.setText(receptionDetailBean.getName());
            tv_officetime.setText(receptionDetailBean.getOfficeHours());
//            tv_address.setText(receptionDetailBean.getAddress());
            tvTitle.setText(receptionDetailBean.getName());
            tv_phone.setText(receptionDetailBean.getTelephone());
//            tvAuthor.setText(receptionDetailBean.getEditor());
            deleteCharString(receptionDetailBean.getEditDate(),'T');
//            tvDatetime.setText(deleteCharString(receptionDetailBean.getEditDate(),'T'));

            String   filePath = receptionDetailBean.getFilePath();
            String imageUrl = Version.FIND_FILE_URL_BASE+filePath;
            GlideUtils.loadImageNew(imageUrl, im_banner);

            mAdapter.setNewData(receptionDetailBean.getNewsList());

            SpannableString ss = new SpannableString(receptionDetailBean.getAddress()+" ");
            int len = ss.length();
            //图片
            Drawable d = ContextCompat.getDrawable(ReceptionDetailActivity.this, (R.mipmap.ic_weizhi));
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            //构建ImageSpan
            ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
            ss.setSpan(span, len - 1, len, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            tv_address.setText(ss);

            Util.addDividerToRecyclerView(this, recyclerView, true);

        }
    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<ReceptionDetailBean> bean) {
        if (bean!=null){

           receptionDetailBean= bean.getData();
            CollectionId= receptionDetailBean.getCollectionId();
            if (CollectionId==null){

                tv_Collection.setText("收藏");
                img_Collection.setSelected(false);
                img_Collection.setImageDrawable(getResources().getDrawable(R.mipmap.star_empty));
            }else {

                tv_Collection.setText("取消收藏");
                img_Collection.setSelected(true);
                img_Collection.setImageDrawable(getResources().getDrawable(R.mipmap.star_full));

            }
                 obj = new JSONObject();

            try {
                obj.put("recepId", receptionDetailBean.getCollectionUrl().getRecepId());
                obj.put("collectionType",receptionDetailBean.getCollectionUrl().getCollectionType());
                obj.put("name", receptionDetailBean.getCollectionUrl().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
//            tv_newlist.setText(receptionDetailBean.getNewsList().get(0).getTitle());
            title.setText(receptionDetailBean.getName());
            tv_officetime.setText(receptionDetailBean.getOfficeHours());
//            tv_address.setText(receptionDetailBean.getAddress());
            tvTitle.setText(receptionDetailBean.getName());
            tv_phone.setText(receptionDetailBean.getTelephone());
//            tvAuthor.setText(receptionDetailBean.getEditor());
            deleteCharString(receptionDetailBean.getEditDate(),'T');
//            tvDatetime.setText(deleteCharString(receptionDetailBean.getEditDate(),'T'));

             String   filePath = receptionDetailBean.getFilePath();
            String imageUrl = Version.FIND_FILE_URL_BASE+filePath;
            GlideUtils.loadImageNew(imageUrl, im_banner);

            mAdapter.setNewData(receptionDetailBean.getNewsList());

            SpannableString ss = new SpannableString(receptionDetailBean.getAddress()+" ");
            int len = ss.length();
            //图片
            Drawable d = ContextCompat.getDrawable(ReceptionDetailActivity.this, (R.mipmap.ic_weizhi));
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            //构建ImageSpan
            ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
            ss.setSpan(span, len - 1, len, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            tv_address.setText(ss);

            Util.addDividerToRecyclerView(this, recyclerView, true);

        }
    }

    @Override
    public void onCollectionSuccess(GCAHttpResultBaseBean<ReceptionDetailBean> bean) {
        ReceptionDetailBean bean1=    bean.getData();
        CollectionId= bean1.getCollectioId();
        tv_Collection.setText("取消收藏");
        Toast.makeText(this,"收藏成功",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onremoveCollectionSuccess(GCAHttpResultBaseBean<ReceptionDetailBean> bean) {
        Toast.makeText(this,"取消收藏成功",Toast.LENGTH_SHORT).show();
        tv_Collection.setText("收藏");
        setResult(RESULT_SUCCESS);
    }

    @Override
    public void onremoveCollectionFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoadListFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

        NotInputDialog inputDialog = new NotInputDialog(this).builder().setTitle("提示"+"\n\n"+"该项目尚未完善！" )
                .setCancelable(false)
                .setPositiveBtn("确定", new NotInputDialog.OnPositiveListener() {
                    @Override
                    public void onPositive(View view, String inputMsg) {
                        finish();
                    }
                })
                .setCanceledOnTouchOutside(false);


        inputDialog.show();

    }

    @Override
    public void onCollectionFailure(String message) {

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public String deleteCharString(String sourceString, char chElemData) {
        String deleteString = "";
        for (int i = 0; i < sourceString.length(); i++) {
            if (sourceString.charAt(i) != chElemData) {
                deleteString += sourceString.charAt(i);
            }else {

                deleteString +=" ";
            }
        }
        return deleteString;
    }


    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(RESULT_FAILED);

            finish();
        }
        return super.onKeyDown(keyCode, event);

    }


}
