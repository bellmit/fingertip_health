package com.jqsoft.fingertip_health.di.ui.activity.fingertip;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.jqsoft.fingertip_health.R;

import com.jqsoft.fingertip_health.adapter.KucunfenxiListActivityAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.HighBloodListActivityBean;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;

import com.jqsoft.fingertip_health.bean.fingertip.KuncunListActivityBean;
import com.jqsoft.fingertip_health.di.contract.PersonJiandangListActivityContract;
import com.jqsoft.fingertip_health.di.module.PersonJiandangListActivityModule;
import com.jqsoft.fingertip_health.di.presenter.PersonJiandangListActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.youtuIdentify.BitMapUtils;
import com.jqsoft.fingertip_health.di.youtuIdentify.IdentifyResult;
import com.jqsoft.fingertip_health.di.youtuIdentify.TecentHttpUtil;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;


//??????fenxi?????????
public class KucunfenxiListActivity extends AbstractActivity implements PersonJiandangListActivityContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
    private EditText et_search;
    private String tempString, cardNo, name;
    private KucunfenxiListActivityAdapter mAdapter;
    RecyclerView recyclerView;
    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;
    @BindView(R.id.check_socaildetail)
    LinearLayout check_socaildetail;
    @BindView(R.id.btn_reset)
    LinearLayout btn_reset;
    @BindView(R.id.online_consultation_title)
    TextView online_consultation_title;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.lay_online_consultation_load_failure)
    View failureView;
    @BindView(R.id.query_btn)
    ImageView query_btn;
    @BindView(R.id.bt_username_clear)
    Button bt_username_clear;

    @BindView(R.id.tv_add)
    ImageView tv_add;

    private TextView tvFailureView;
    private TextView tv_search_send;
    private LinearLayout ll_back;
    private Boolean isRefresh = false;
    private final static int REQUEST_IMAGE = 100;
    private String picture = null;
    private Bitmap bitmap = null;
    @Inject
    PersonJiandangListActivityPresenter mPresenter;

    @Override
    protected void loadData() {
        onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_socialassistanceobject;
    }

    @Override
    protected void initData() {
        tv_add.setVisibility(View.GONE);
//        tv_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(KucunfenxiListActivity.this, PersonJiandangActivity.class);
//             //0?????????1?????????
//                intent.putExtra("flag","0");
//                intent.putExtra("id","");
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected void initView() {
        online_consultation_title.setText("????????????");
        et_search = (EditText) findViewById(R.id.et_search);
     //   tv_search_send = (TextView) findViewById(R.id.tv_search_send);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        recyclerView = (RecyclerView) srl.findViewById(R.id.recyclerview);
        tvFailureView = (TextView) failureView.findViewById(R.id.tv_load_failure_hint);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onRefresh();
            }
        });
        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        srl.setOnRefreshListener(this);


        final BaseQuickAdapter<KuncunListActivityBean, BaseViewHolder> mAdapter = new KucunfenxiListActivityAdapter(new ArrayList<KuncunListActivityBean>(), getApplicationContext());
        this.mAdapter = (KucunfenxiListActivityAdapter) mAdapter;
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setOnLoadMoreListener(this, recyclerView);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              //  Log.i(TAG, "???????????????????????????count??????????????????");

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
               // Log.i(TAG, "???????????????????????????");
            }

            @Override
            public void afterTextChanged(Editable s) {
               // Log.i(TAG, "????????????????????????");
                if(!TextUtils.isEmpty(s.toString())){
                    bt_username_clear.setVisibility(View.VISIBLE);
                }

            }
        });
        bt_username_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
            }
        });
        query_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempString = et_search.getText().toString();
//                if (tempString.length() < 18) {
//                    name = tempString;
//                } else {
//                    cardNo = tempString;
//                }
                if (!TextUtils.isEmpty(tempString)) {
                    onRefresh();
                } else {
                    Util.showToast(KucunfenxiListActivity.this, Constants.CHECK_DATA);
                }
            }
        });
        check_socaildetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempString = et_search.getText().toString();
                if (tempString.length() < 18) {
                    name = tempString;
                } else {
                    cardNo = tempString;
                }
                if (!TextUtils.isEmpty(tempString)) {
                    onRefresh();
                } else {
                    Util.showToast(KucunfenxiListActivity.this, Constants.CHECK_DATA);
                }
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
                onRefresh();

            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

//                KuncunListActivityBean pb = mAdapter.getItem(position);
//
//                Intent intent = new Intent(KucunfenxiListActivity.this, PersonJiandangActivity.class);
//                //0?????????1?????????
//                intent.putExtra("flag","1");
//                intent.putExtra("id",pb.getId());
//                startActivity(intent);
            }
        });
        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getApplicationContext(),"??????",Toast.LENGTH_SHORT).show();
                if (ActivityCompat.checkSelfPermission(KucunfenxiListActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // ?????????????????????????????????????????????
                    // ??????WRITE_EXTERNAL_STORAGE??????
                    ActivityCompat.requestPermissions(KucunfenxiListActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            100);
                } else {
                    selectImage();
                }
            }
        });

        //        List<HighBloodListActivityBean> list = getListFromResult(bean);
//        List<HighBloodListActivityBean> list=new ArrayList<>();
//        list.add(new HighBloodListActivityBean("","","??????????????????????????????","","",""));
//
//
//
//        mAdapter.setNewData(list);
//
//        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(list));
//        mAdapter.loadMoreEnd(true);
    }

    public Map<String, String> getRequestMap() {
        String departmentCode ="600001";
        String drugId="";
        String stockId="";
        String avail="1";
        String expire="1";
        Map<String, String> map = ParametersFactory.getkuncunDataList(this, departmentCode, drugId, stockId,avail,expire, currentPage,pageSize,tempString );


        return map;
    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addKuncunfenxiListActivity(new PersonJiandangListActivityModule(this))
                .inject(this);

    }

    @Override
    public void onRefresh() {
        currentPage = Constants.HB_INITIAL_PAGE;
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        Map<String, String> map = getRequestMap();
        mPresenter.getSocialDatas(map, false);

    }

    @Override
    public void onLoadMoreRequested() {
        ++currentPage;
        Map<String, String> map = getRequestMap();
        mPresenter.getSocialDatas(map, true);
        LogUtil.i("OnlineConsultationActivity onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
    }

    @Override
    public void onLoadListSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        showNotificationSuccessOrFailureView(true, bean,false);
//        int listSize = getListSizeFromResult(bean);
//        List<HighBloodListActivityBean> list=new ArrayList<>();
//
//        mAdapter.setNewData(list);
//
//        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(list));
//
//        srl.setRefreshing(false);
//        setLoadMoreStatus(pageSize, listSize, true);
    }

    public void setLoadMoreStatus(int pageSize, int listSize, boolean isSuccessful) {
        if (isSuccessful) {
            if (listSize < pageSize) {
                mAdapter.setEnableLoadMore(false);
                mAdapter.loadMoreEnd(true);
            } else {
                mAdapter.setEnableLoadMore(true);
                mAdapter.loadMoreComplete();
            }
        } else {
            mAdapter.setEnableLoadMore(true);
            mAdapter.loadMoreFail();
        }
    }

    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        showNotificationSuccessOrFailureView(true, bean,true);
//        int listSize = getListSizeFromResult(bean);
//        List<HighBloodListActivityBean> list = getListFromResult(bean);
//        mAdapter.addData(list);
//
//        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
//        srl.setEnabled(true);
//        srl.setRefreshing(false);
//        setLoadMoreStatus(this.pageSize, listSize, true);
    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
        showRecyclerViewOrFailureView(false, true);
        if (isLoadMore) {
            if (currentPage > Constants.DEFAULT_INITIAL_PAGE) {
                --currentPage;
            }

        } else {

        }
        srl.setRefreshing(false);
        setLoadMoreStatus(0, 0, false);
////        Util.showToast(this, message);
        Util.showToast(this, message);

    }

    @Override
    public void onLoadListChakanSuccess(HttpResultBaseBeanForFingertip<String> bean) {

    }

    @Override
    public void onLoadListChakanFailure(String message, boolean isLoadMore) {

    }

    public int getListSizeFromResult(List<KuncunListActivityBean> beanList) {
        if (beanList != null) {
            List<KuncunListActivityBean> list = beanList;
            if (list != null) {
                int size = ListUtils.getSize(list);
                return size;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public List<HighBloodListActivityBean> getListFromResult(HttpResultBaseBeanForFingertip<List<HighBloodListActivityBean>> beanList) {
        if (beanList != null) {
            List<HighBloodListActivityBean> list = beanList.getResult();
            return list;
        } else {
            return null;
        }
    }

    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty) {
        if (success) {
            if (isListEmpty) {
                srl.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
            } else {
                srl.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);
            }
        } else {
            srl.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());

        }
    }

    private String getListEmptyHint() {
        return getResources().getString(R.string.hint_no_consultation);
    }

    private String getFailureHint() {
        return getResources().getString(R.string.hint_load_failure);
    }

    /**
     * select picture
     */
    private void selectImage() {
        MultiImageSelector.create(KucunfenxiListActivity.this)
                .showCamera(true) // ??????????????????. ???????????????
//                .count(1) // ????????????????????????, ?????????9. ???????????????????????????????????????
                .single() // ????????????
//                .multi() // ????????????, ????????????;
//                .origin(ArrayList<String>) // ?????????????????????. ???????????????????????????????????????
                .start(KucunfenxiListActivity.this, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // ???????????????????????????
                Util.showGifProgressDialog(KucunfenxiListActivity.this);
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // ???????????????????????? ....
                if (path != null && path.size() > 0) {
                    picture = path.get(0);
//                    onSelected();
                    bitmap = getImage(picture);
                    //   imageView.setImageBitmap(bitmap);

                    TecentHttpUtil.uploadIdCard(BitMapUtils.bitmapToBase64(bitmap), "0", new TecentHttpUtil.SimpleCallBack() {
                        @Override
                        public void Succ(String res) {
                            IdentifyResult result = new Gson().fromJson(res, IdentifyResult.class);
                            if (result != null) {
                                if (result.getErrorcode() == 0) {
                                    // ????????????
                                    Util.hideGifProgressDialog(KucunfenxiListActivity.this);
                                    showDialogInfo(result);

                                } else {
                                    Util.hideGifProgressDialog(KucunfenxiListActivity.this);
                                    Toast.makeText(KucunfenxiListActivity.this, "?????????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                                /*switch (result.getErrorcode()){
                                    case -7001:
                                        Toast.makeText(MainActivity.this, "???????????????????????????????????????(??????????????????????????????????????????????????????)", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7002:
                                        Toast.makeText(MainActivity.this, "??????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7003:
                                        Toast.makeText(MainActivity.this, "???????????????????????????(??????????????????????????????????????????)", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7004:
                                        Toast.makeText(MainActivity.this, "???????????????????????????(????????????????????????????????????)", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7005:
                                        Toast.makeText(MainActivity.this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7006:
                                        Toast.makeText(MainActivity.this, "????????????????????????????????????", Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        Toast.makeText(MainActivity.this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
                                        break;
                                }*/
                                }
                            }
                        }

                        @Override
                        public void error() {

                        }
                    });


                }
            }
        }
    }

    /**
     * ???????????????
     *
     * @param result
     */
    private void showDialogInfo(final IdentifyResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("?????????" + result.getName() + "\n");
        sb.append("?????????" + result.getSex() + "\t" + "?????????" + result.getNation() + "\n");
        sb.append("?????????" + result.getBirth() + "\n");
        sb.append("?????????" + result.getAddress() + "\n" + "\n");
        sb.append("?????????????????????" + result.getId() + "\n");
        AlertDialog.Builder builder = new AlertDialog.Builder(KucunfenxiListActivity.this);
        AlertDialog dialogInfo = builder.setTitle("????????????")
                .setMessage(sb.toString())
                /*.setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("text", result.getId());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(SocialAssistanceObject.this, "?????????????????????????????????", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })*/
                .setNegativeButton("??????", null)
                .create();
        dialogInfo.show();

        et_search.setText(result.getId());

    }

    /**
     * ????????????????????????
     *
     * @param srcPath
     * @return
     */
    private Bitmap getImage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // ??????????????????????????????options.inJustDecodeBounds ??????true???
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// ????????????bm??????

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // ??????????????????????????????800*480??????????????????????????????????????????
        float hh = 800f;// ?????????????????????800f
        float ww = 480f;// ?????????????????????480f
        // ????????????????????????????????????????????????????????????????????????????????????????????????
        int be = 1;// be=1???????????????
        if (w > h && w > ww) {// ???????????????????????????????????????????????????
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// ???????????????????????????????????????????????????
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// ??????????????????
        // ??????????????????????????????????????????options.inJustDecodeBounds ??????false???
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);// ?????????????????????????????????????????????
    }

    private Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// ???????????????????????????100????????????????????????????????????????????????baos???
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  // ?????????????????????????????????????????????100kb,??????????????????
            baos.reset();// ??????baos?????????baos
            if (options < 0) {
                image.compress(Bitmap.CompressFormat.JPEG, 10, baos);// ????????????options%?????????????????????????????????baos???
                break;
            } else {
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);// ????????????options%?????????????????????????????????baos???
            }

            options -= 10;// ???????????????10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// ?????????????????????baos?????????ByteArrayInputStream???
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// ???ByteArrayInputStream??????????????????
        return bitmap;
    }


    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }


    private void showNotificationSuccessOrFailureView(boolean success, HttpResultBaseBeanForFingertip<String> bean,boolean isloadmore){
        if (success) {
            if (bean!=null){
                String resultString = bean.getResult();
                try {
                    List<KuncunListActivityBean> resultList = JSON.parseObject(resultString,
                            new TypeReference<List<KuncunListActivityBean>>(){});
                    if (!ListUtils.isEmpty(resultList)) {
                        if (isloadmore){
                            int listSize = getListSizeFromResult(resultList);
                            mAdapter.addData(resultList);
                            showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
                            srl.setEnabled(true);
                            srl.setRefreshing(false);
                            setLoadMoreStatus(this.pageSize, listSize, true);

                        }else {

                            int listSize = getListSizeFromResult(resultList);
                            mAdapter.setNewData(resultList);
                            showRecyclerViewOrFailureView(true, ListUtils.isEmpty(resultList));
                            srl.setRefreshing(false);
                            setLoadMoreStatus(pageSize, listSize, true);
                        }


                    } else {
                        showRecyclerViewOrFailureView(true,true);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showRecyclerViewOrFailureView(false,true);
                }
            } else {
                showRecyclerViewOrFailureView(true,true);
            }
        } else {
            showRecyclerViewOrFailureView(false,true);
        }
    }


}
