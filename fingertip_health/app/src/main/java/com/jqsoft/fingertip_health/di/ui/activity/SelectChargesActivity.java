package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.DiseaseAdapter;
import com.jqsoft.fingertip_health.base.Constant;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.bean.GDWS_ICD;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/9/5.
 */

public class SelectChargesActivity extends AbstractActivity {
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.recyclerview)
    ListView recyclerview;
    @BindView(R.id.et_search)
    EditText et_search;
    private List<GDWS_ICD> datalist = new ArrayList<>();
    private List<GDWS_ICD>toList = new ArrayList<>();
    private String area_id = "";//5226
    private DiseaseAdapter<GDWS_ICD> adapter;
    private String search = "";
    private GDWS_ICD disInfo;
    @Override
    protected int getLayoutId() {
        return R.layout.selectcharge_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        area_id= IdentityManager.getCmsIcdAreaForFingertip(this);

        String dbPath = "/data/data/" + getPackageName() + "/databases/gdws.db";
//        boolean isFileExist = FileUtils.isFileExist(dbPath);
//        long fileSize = FileUtils.getFileSize(dbPath);
//        //List<GDWS_ICD> persons=DataSupport.findAll(GDWS_ICD.class);
        Util.showGifProgressDialog(SelectChargesActivity.this);
        new Thread(sendable).start();
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search = s.toString();
                adapter.getFilter().filter(search);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        recyclerview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                disInfo = adapter.getBeanList().get(position);

                if (disInfo != null) {
                    Intent intent = getIntent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("disInfo",  disInfo);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "请选择后确认",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void loadData() {


    }

    private Runnable sendable = new Runnable() {
        public void run() {
            try {
//                datalist = DataSupport.where("area_id = ?", area_id).find(GDWS_ICD.class);
//                datalist = DataSupport.where("area_id = ?", area_id).find(GDWS_ICD.class);
                Thread.sleep(1000);
                myhandler.sendEmptyMessage(Constant.MSG_LOAD_OVER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Handler myhandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.MSG_LOAD_OVER:
                    Util.hideGifProgressDialog(SelectChargesActivity.this);
                    datalist= DaggerApplication.getInstance().getDatalist();
                    adapter = new DiseaseAdapter<GDWS_ICD>(SelectChargesActivity.this,
                            R.layout.zhenduan_item, datalist, 20000);
                    //设置Adapter
                    recyclerview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
            }

        }
    };

    private void queryByRawString(){
        //                datalist = DataSupport.findAll(GDWS_ICD.class);
//                Cursor cursor = DataSupport.findBySQL("select * from gdws_icd where area_id = ?", area_id);
//                datalist.clear();
//                if (cursor != null && cursor.moveToFirst()) {
//                    do {
//                        String id = cursor.getString(cursor.getColumnIndex("id"));
//                        String code = cursor.getString(cursor.getColumnIndex("code"));
//                        String name = cursor.getString(cursor.getColumnIndex("name"));
//                        String area_id_string = cursor.getString(cursor.getColumnIndex("area_id"));
//                        String pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
//                        GDWS_ICD item = new GDWS_ICD();
//                        item.setId(id);
//                        item.setCode(code);
//                        item.setName(name);
//                        item.setArea_id(area_id_string);
//                        item.setPinyin(pinyin);
//                        datalist.add(item);
//                    } while (cursor.moveToNext());
//                }

    }
}
