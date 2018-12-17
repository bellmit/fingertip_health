package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.SettingServerAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.bean.OrganizationBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginSalvationBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SettingServerBean;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.PreferencesUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import butterknife.BindView;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;


//设置服务器
public class SettingServerActivity extends AbstractActivity implements SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.treatment_title)
    TextView treatment_title;
    public static final int TYPE_MULTIPLE_LINE=2;
    private SettingServerAdapter mAdapter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.im_add)
    ImageView im_add;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.btn_delete)
    RelativeLayout btn_delete;
    @BindView(R.id.btn_save)
    RelativeLayout btn_save;
    List<SettingServerBean> list;
    private static  String CHANGESERVER="1";
    private static  String ADDSERVER="2";
    List<SettingServerBean>  Serverlist;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_server;
    }

    @Override
    protected void initData() {

    }


    private void loaddata(){

        Serverlist = DataSupport.findAll(SettingServerBean.class);
        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());

        if (Serverlist.size()==0){

            //     SettingServerBean bean=new SettingServerBean("192.168.44.134:8080","安徽民政厅(默认)",name,"1") ;
            String baseHttpUrl = Util.getMetaDataFromManifest(this, "HTTP_ACCESS_URL");
            String sBaseHttpUrl= baseHttpUrl.substring(7,26);

            SettingServerBean bean=new SettingServerBean(sBaseHttpUrl,"默认服务器地址",name,"1") ;
//            SettingServerBean bean=new SettingServerBean(Version.FILE_URL_BASE,"安徽民政厅",name) ;
            if (bean.save()) {
                ((SettingServerAdapter) mAdapter).SettingChoose(sBaseHttpUrl);
            }else {
                Toast.makeText(this,"数据库 储存失败",Toast.LENGTH_LONG).show();
            }
        }
        for (int i=0;i<Serverlist.size();i++){
            if (Serverlist.get(i).getIsUse().equals("1")){
                ((SettingServerAdapter) mAdapter).SettingChoose( Serverlist.get(i).getIp());
            }
        }

        mAdapter.setNewData(Serverlist);
        recyclerView.setAdapter(mAdapter);
    }
    @Override
    protected void initView() {

        setToolBar(toolbar, Constants.EMPTY_STRING);
        treatment_title.setText("设置服务器");

        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        srl.setOnRefreshListener(this);


        final BaseQuickAdapter<SettingServerBean, BaseViewHolder> mAdapter = new SettingServerAdapter(new ArrayList<SettingServerBean>(), TYPE_MULTIPLE_LINE);
        this.mAdapter = (SettingServerAdapter) mAdapter;
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, recyclerView, true);
        loaddata();
        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);

                SettingServerBean pb = mAdapter.getItem(position);
                Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.SETTING_SERVER_POSTION_ACTIVITY_KEY,   position);
                bundle.putSerializable(Constants.SETTING_SERVER_ACTIVITY_KEY,   pb);
                bundle.putSerializable(Constants.SETTING_SERVER_ACTIVITY_STYE_KEY,CHANGESERVER);

                Util.gotoActivityWithBundle(SettingServerActivity.this, SettingServerChangeActivity.class, bundle);
            }
        });

        im_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.SETTING_SERVER_ACTIVITY_STYE_KEY,ADDSERVER);
                Util.gotoActivityWithBundle(SettingServerActivity.this, SettingServerChangeActivity.class, bundle);

            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> iplist1= ((SettingServerAdapter) mAdapter).getIplist();
                final SettingServerBean settingServerBean=  ((SettingServerAdapter) mAdapter).getSettingServerBean();
                List<String> iplist = new ArrayList(new TreeSet(iplist1));
                HashMap<String, Boolean> beans=  ((SettingServerAdapter) mAdapter).getstates();
                String ip=null;

                if (beans==null){
                    Toast.makeText(SettingServerActivity.this,"无数据",Toast.LENGTH_SHORT).show();

                }
                else {
                for (int i=0;i<iplist.size();i++){

                    if ( beans.get(iplist.get(i))){
                       ip=iplist.get(i);
//                       Toast.makeText(SettingServerActivity.this,"获得"+ip,Toast.LENGTH_SHORT).show();
                   }

                }
                }
                final String finalIp = ip;
                Util.showMaterialDialog(SettingServerActivity.this, Constants.HINT, "确定更改ip为"+ip+"(更改成功后，会跳到登录页面重新登录)", Constants.CANCEL, new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }, Constants.CONFIRM, new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        if (settingServerBean.save()){
                            for (int i=0;i<Serverlist.size();i++){
                                if (Serverlist.get(i).getIsUse().equals("1")){
                                    Serverlist.get(i).setIsUse("0");
                                    Serverlist.get(i).save();
                                }

                            }
                            for (int i=0;i<Serverlist.size();i++){
                                if (Serverlist.get(i).getIp().equals(finalIp)){
                                    Serverlist.get(i).setIsUse("1");
                                    Serverlist.get(i).save();
                                }

                            }

//                            String newGlobalUrl = "http://" + finalIp + "/sri/";
                            String newGlobalUrl = "http://" + finalIp + "/";
                            setRetrofitGlobalUrl(newGlobalUrl);
//                            setRetrofitGlobalUrl(finalIp);
                            Toast.makeText(SettingServerActivity.this,"服务器更改成功，请重新登录",Toast.LENGTH_SHORT).show();

                            DataSupport.deleteAll(SRCLoginDataDictionaryBean.class);
                            DataSupport.deleteAll(SRCLoginSalvationBean.class);
                            DataSupport.deleteAll(OrganizationBean.class);

                            if(Identity.srcInfo==null){

                            }else {
//                                String s1= Identity.srcInfo.getConfigvalue();
//                                String s2= Identity.srcInfo.getConfigvalue();
                            }



                            exit();

                        }else {
                            Toast.makeText(SettingServerActivity.this,"数据库存储失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                }, true);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> iplist1= ((SettingServerAdapter) mAdapter).getIplist();
                final SettingServerBean settingServerBean=  ((SettingServerAdapter) mAdapter).getSettingServerBean();
                List<String> iplist = new ArrayList(new TreeSet(iplist1));
                HashMap<String, Boolean> beans=  ((SettingServerAdapter) mAdapter).getstates();
                String ip=null;

                if (beans==null){
                    Toast.makeText(SettingServerActivity.this,"无数据",Toast.LENGTH_SHORT).show();

                }
                else {
                    for (int i=0;i<iplist.size();i++){

                        if ( beans.get(iplist.get(i))){
                            ip=iplist.get(i);
//                       Toast.makeText(SettingServerActivity.this,"获得"+ip,Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                final String finalIp = ip;
                String baseHttpUrl = Util.getMetaDataFromManifest(SettingServerActivity.this, "HTTP_ACCESS_URL");
                String sBaseHttpUrl= baseHttpUrl.substring(7,25);
                if (finalIp.equals(sBaseHttpUrl)){
                    Toast.makeText(SettingServerActivity.this,"不能删除默认服务器数据！",Toast.LENGTH_SHORT).show();

                }else {
                Util.showMaterialDialog(SettingServerActivity.this, Constants.HINT, "确定删除ip为"+ip+"的服务器数据", Constants.CANCEL, new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }, Constants.CONFIRM, new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                             DataSupport.deleteAll(SettingServerBean.class,"ip=?", finalIp);
                            Toast.makeText(SettingServerActivity.this,"数据删除成功！",Toast.LENGTH_SHORT).show();
                            onRefresh();

                    }
                }, true);

            }      }
        });

    }

    private void setRetrofitGlobalUrl(String url){
        RetrofitUrlManager.getInstance().setGlobalDomain(url);
        Util.setVariousUrlFromBaseUrl(this, url);
    }


    @Override
    protected void loadData() {

    }

    @Override
    protected void onResume() {

        Serverlist = DataSupport.findAll(SettingServerBean.class);
        mAdapter.setNewData(Serverlist);
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_my_info, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void exit() {
        PreferencesUtils.putString(this, Constants.REMEMBERED_USERNAME_KEY, Constants.EMPTY_STRING);
        PreferencesUtils.putBoolean(this, Constants.WHETHER_REMEMBER_PASSWORD_KEY, false);
        PreferencesUtils.putString(this, Constants.REMEMBERED_PASSWORD_KEY, Constants.EMPTY_STRING);


        RxBus.getDefault().post(Constants.EVENT_TYPE_FINISH_ACTIVITY, true);

        Intent intent = new Intent();
        intent.setClass(this,LoginActivityNew.class);
        startActivity(intent);


//        Intent intent = getPackageManager()
//                .getLaunchIntentForPackage(getApplication().getPackageName());
//        PendingIntent restartIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
//        AlarmManager mgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        mgr.set(AlarmManager.RTC, System.currentTimeMillis() , restartIntent); // 1秒钟后重启应用
//        System.exit(0);


     //   gotoActivity(this, LoginActivityNew.class);


//        RxBus.getDefault().post(Constants.EVENT_TYPE_FINISH_ACTIVITY, true);
//        finish();
//        System.exit(0);


      //  registerFinishActivityEvent();
     /*   finish();
        Util.gotoActivity(getApplicationContext(), LoginActivityNew.class);
        System.exit(0);*/

       /* Intent intent = new Intent(this, LoginActivityNew.class);

        ComponentName cn = intent.getComponent();
        Intent mainIntent = IntentCompat.makeRestartActivityTask(cn);//ComponentInfo{包名+类名}
        startActivity(mainIntent);*/
       /* ActivityManager manager = (ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);
        manager.restartPackage("com.jqsoft.fingertip_health");
        */


      /*  final Intent intent = new Intent();

        SettingServerActivity.this.stopService(intent);
        System.exit(0);

        ActivityManager am = (ActivityManager) SettingServerActivity.this.getSystemService(SettingServerActivity.this.ACTIVITY_SERVICE);
        am.restartPackage("com.jqsoft.fingertip_health");
*/
    }

    @Override
    public void onRefresh() {

        loaddata();
        mAdapter.notifyDataSetChanged();
        srl.setRefreshing(false);
    }
}
