package com.jqsoft.fingertip_health.di.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
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
import com.jqsoft.fingertip_health.adapter.SelectChargeListActivityAdapter;
import com.jqsoft.fingertip_health.adapter.StockListAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.SelectOutPatientChargesBean;
import com.jqsoft.fingertip_health.bean.SelectOutPatientChargesBean;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.HighBloodListActivityContract;
import com.jqsoft.fingertip_health.di.contract.SeletChargeListActivityContract;
import com.jqsoft.fingertip_health.di.module.HighBloodListActivityModule;
import com.jqsoft.fingertip_health.di.module.SelectChargeListActivityModule;
import com.jqsoft.fingertip_health.di.presenter.HighBloodListActivityPresenter;
import com.jqsoft.fingertip_health.di.presenter.SelectChargeListActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.youtuIdentify.BitMapUtils;
import com.jqsoft.fingertip_health.di.youtuIdentify.IdentifyResult;
import com.jqsoft.fingertip_health.di.youtuIdentify.TecentHttpUtil;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.wangshijia.www.panellistviewlibrary.PanelListView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;


//?????????????????????
public class SelectOutChargeListActivity extends AbstractActivity implements SeletChargeListActivityContract.View {//, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener
    private int currentPage = Constants.HB_INITIAL_PAGE;
    private int pageSize = 20;
    private EditText et_search;
    private String tempString, cardNo, name = "";
    private SelectChargeListActivityAdapter mAdapter;
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
    @BindView(R.id.start_time)
    TextView start_time;
    @BindView(R.id.end_time)
    TextView end_time;
    @BindView(R.id.btn_start)
    ImageView btn_start;
    @BindView(R.id.btn_end)
    ImageView btn_end;
    @BindView(R.id.select_query_btn)
    ImageView select_query_btn;
    @BindView(R.id.btn_today)
    TextView btn_today;
    @BindView(R.id.btn_week)
    TextView btn_week;
    @BindView(R.id.btn_monoth)
    TextView btn_monoth;
    private TextView tvFailureView;
    private TextView tv_search_send;
    private LinearLayout ll_back;
    private Boolean isRefresh = false;
    private final static int REQUEST_IMAGE = 100;
    private String picture = null;
    private Bitmap bitmap = null;
    @Inject
    SelectChargeListActivityPresenter mPresenter;
    private StockListAdapter stockListAdapter;
    private PanelListView panelListView;
    private List<SelectOutPatientChargesBean> stockDataInfoList = new ArrayList<>();
    private int sortType = 2;
    private int currentTabIndex;
    private int ispull = 1;

    @Override
    protected void loadData() {
        onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.selectcharge_layout_new;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        online_consultation_title.setText("????????????");
        et_search = (EditText) findViewById(R.id.et_search);
        select_query_btn.setVisibility(View.VISIBLE);
        //   tv_search_send = (TextView) findViewById(R.id.tv_search_send);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        recyclerView = (RecyclerView) srl.findViewById(R.id.recyclerview);
        panelListView = (PanelListView) findViewById(R.id.hv_scrollview);
        tvFailureView = (TextView) failureView.findViewById(R.id.tv_load_failure_hint);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setDataText();
        btn_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_time.setText(sNowDate);
                end_time.setText(sNowDate);
                btn_today.setBackgroundResource(R.drawable.color_theme_background_corner_radius);
                btn_week.setBackgroundResource(R.drawable.select_btn_color_theme_border_4_radius_white_background);
                btn_monoth.setBackgroundResource(R.drawable.select_btn_color_theme_border_4_radius_white_background);

                btn_today.setTextColor(Color.WHITE);
                btn_week.setTextColor(android.graphics.Color.parseColor("#3cb371"));
                btn_monoth.setTextColor(android.graphics.Color.parseColor("#3cb371"));
            }
        });
        btn_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToWeekText();

                btn_week.setBackgroundResource(R.drawable.color_theme_background_corner_radius);
                btn_today.setBackgroundResource(R.drawable.select_btn_color_theme_border_4_radius_white_background);
                btn_monoth.setBackgroundResource(R.drawable.select_btn_color_theme_border_4_radius_white_background);

                btn_week.setTextColor(Color.WHITE);
                btn_today.setTextColor(android.graphics.Color.parseColor("#3cb371"));
                btn_monoth.setTextColor(android.graphics.Color.parseColor("#3cb371"));
            }
        });
        btn_monoth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataText();
                btn_monoth .setBackgroundResource(R.drawable.color_theme_background_corner_radius);
                btn_today.setBackgroundResource(R.drawable.select_btn_color_theme_border_4_radius_white_background);
                btn_week.setBackgroundResource(R.drawable.select_btn_color_theme_border_4_radius_white_background);

                btn_monoth.setTextColor(Color.WHITE);
                btn_today.setTextColor(android.graphics.Color.parseColor("#3cb371"));
                btn_week.setTextColor(android.graphics.Color.parseColor("#3cb371"));
            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(getApplicationContext(),"????????????",Toast.LENGTH_SHORT).show();
                //   et_execu_serverdate.setText("");
                String initial = getSignTimeString();
                Calendar c2 = Calendar.getInstance();
                Util.showDateNewDialogWithMaxDate(SelectOutChargeListActivity.this, initial, "a_party_fragment_sign_time", c2, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
                        start_time.setText(s);


                    }
                });
            }

        });
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(getApplicationContext(),"????????????",Toast.LENGTH_SHORT).show();
                //   et_execu_serverdate.setText("");
                String initial = getSignTimeString();
                Calendar c2 = Calendar.getInstance();
                Util.showDateNewDialogWithMaxDate(SelectOutChargeListActivity.this, initial, "a_party_fragment_sign_time", c2, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
                        start_time.setText(s);


                    }
                });
            }

        });
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(getApplicationContext(),"????????????",Toast.LENGTH_SHORT).show();
                //   et_execu_serverdate.setText("");
                Calendar c2 = Calendar.getInstance();
                String initial = getNextTimeString();
                Util.showDateNewDialogWithMaxDate(SelectOutChargeListActivity.this, initial, "a_party_fragment_sign_time", c2, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
                        end_time.setText(s);


                    }
                });
            }
        });
        select_query_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ispull = 2;
                onRefresh();
            }
        });
//        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                onRefresh();
//            }
//        });
//        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
//        srl.setOnRefreshListener(this);
//
//
//        final BaseQuickAdapter<SelectOutPatientChargesBean, BaseViewHolder> mAdapter = new SelectChargeListActivityAdapter(new ArrayList<SelectOutPatientChargesBean>(), getApplicationContext());
//        this.mAdapter = (SelectChargeListActivityAdapter) mAdapter;
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setOnLoadMoreListener(this, recyclerView);
//        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
//        recyclerView.setAdapter(mAdapter);
        et_search.setHint("??????????????????????????????");
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
                if (!TextUtils.isEmpty(s.toString())) {
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
                cardNo = tempString;
                if (!TextUtils.isEmpty(tempString)) {
                    //    onRefresh();
                } else {
                    Util.showToast(SelectOutChargeListActivity.this, Constants.CHECK_DATA);
                }
            }
        });

        check_socaildetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempString = et_search.getText().toString();
//                if (tempString.length() < 18) {
//                    name = tempString;
//                } else {
//                    cardNo = tempString;
//                }
                cardNo = tempString;
                if (!TextUtils.isEmpty(tempString)) {
                    //   onRefresh();
                } else {
                    Util.showToast(SelectOutChargeListActivity.this, Constants.CHECK_DATA);
                }
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
                //onRefresh();

            }
        });

//
//        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
//            @Override
//            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                super.onNoDoubleItemClick(adapter, view, position);
//
//                SelectOutPatientChargesBean pb = mAdapter.getItem(position);
//
//
//                Intent intent = new Intent(SelectOutChargeListActivity.this, HighBloodActivity.class);
//
//
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data", pb);
//
//                intent.putExtras(bundle);
//
//                startActivity(intent);
////                Util.gotoActivityWithBundle(HighBloodListActivity.this, HighBloodActivity.class, bundle);
//            }
//        });

        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getApplicationContext(),"??????",Toast.LENGTH_SHORT).show();
                if (ActivityCompat.checkSelfPermission(SelectOutChargeListActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // ?????????????????????????????????????????????
                    // ??????WRITE_EXTERNAL_STORAGE??????
                    ActivityCompat.requestPermissions(SelectOutChargeListActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            100);
                } else {
                    selectImage();
                }
            }
        });

    }

    public Map<String, String> getRequestMap() {
        String no = cardNo;
        String isCharged = "1";
        String visitTimeStart = start_time.getText().toString() + " 00:00:00";
        String visitTimeEnd = end_time.getText().toString() + " 23:59:59";
        Map<String, String> map = ParametersFactory.getSelectChargeDataList(this, no, isCharged, currentPage, pageSize, visitTimeStart, visitTimeEnd);

        return map;
    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addselectchargeActivity(new SelectChargeListActivityModule(this))
                .inject(this);
    }

    public void onRefresh() {
        currentPage = Constants.HB_INITIAL_PAGE;
//        isRefresh = true;
//        mAdapter.setEnableLoadMore(false);
        Map<String, String> map = getRequestMap();
        mPresenter.getlist(map, false);
    }


    public void onLoadMoreRequested() {
        ++currentPage;
        Map<String, String> map = getRequestMap();
        mPresenter.getlist(map, true);
        LogUtil.i("OnlineConsultationActivity onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
//        srl.setEnabled(false);
    }


    private void showNotificationSuccessOrFailureView(boolean success, HttpResultBaseBeanForFingertip<String> bean, boolean isloadmore) {
        if (success) {
            if (bean != null) {
                String resultString = bean.getResult();
                try {
                    List<SelectOutPatientChargesBean> resultList = JSON.parseObject(resultString,
                            new TypeReference<List<SelectOutPatientChargesBean>>() {
                            });
                    if (!ListUtils.isEmpty(resultList)) {
                        if (isloadmore) {
                            int listSize = getListSizeFromResult(resultList);
                            mAdapter.addData(resultList);
                            showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
                            srl.setEnabled(true);
                            srl.setRefreshing(false);
                            setLoadMoreStatus(this.pageSize, listSize, true);

                        } else {

                            int listSize = getListSizeFromResult(resultList);
                            mAdapter.setNewData(resultList);
                            showRecyclerViewOrFailureView(true, ListUtils.isEmpty(resultList));
                            srl.setRefreshing(false);
                            setLoadMoreStatus(pageSize, listSize, true);
                        }


                    } else {
                        showRecyclerViewOrFailureView(true, true);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showRecyclerViewOrFailureView(false, true);
                }
            } else {
                showRecyclerViewOrFailureView(true, true);
            }
        } else {
            showRecyclerViewOrFailureView(false, true);
        }
    }

    @Override
    public void onSeletChargeListSuccess(HttpResultBaseBeanForFingertip<String> bean) {

        // showNotificationSuccessOrFailureView(true, bean, false);
        if (ispull == 1) {
            showadpter(bean, false);
        } else {
            String resultString = bean.getResult();
            try {
                stockDataInfoList = JSON.parseObject(resultString,
                        new TypeReference<List<SelectOutPatientChargesBean>>() {
                        });
                if (stockDataInfoList.size() > 0) {
                    resultList.clear();
                    resultList.addAll(0, stockDataInfoList);
                    stockListAdapter.notifyDataSetChanged();
                } else {
                    Util.showToast(getApplicationContext(), "????????????");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
    public void onSeletChargeMoreListSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        //   showNotificationSuccessOrFailureView(true, bean, true);

        // showadpter(bean, true);
        String resultString = bean.getResult();
        try {
            stockDataInfoList = JSON.parseObject(resultString,
                    new TypeReference<List<SelectOutPatientChargesBean>>() {
                    });
            if (stockDataInfoList.size() > 0) {
                resultList.addAll(stockDataInfoList);
                stockListAdapter.notifyDataSetChanged();
            } else {
                Util.showToast(getApplicationContext(), "????????????");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSeletChargeListFailure(String message, boolean isLoadMore) {
        showRecyclerViewOrFailureView(false, true);
        if (isLoadMore) {
            if (currentPage > Constants.HB_INITIAL_PAGE) {
                --currentPage;
            }

        } else {

        }
        srl.setRefreshing(false);
        setLoadMoreStatus(0, 0, false);
////        Util.showToast(this, message);
        Util.showToast(this, message);

    }

    public int getListSizeFromResult(List<SelectOutPatientChargesBean> beanList) {
        if (beanList != null) {
            List<SelectOutPatientChargesBean> list = beanList;
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

    public List<SelectOutPatientChargesBean> getListFromResult(HttpResultBaseBeanForFingertip<List<SelectOutPatientChargesBean>> beanList) {
        if (beanList != null) {
            List<SelectOutPatientChargesBean> list = beanList.getResult();
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
        MultiImageSelector.create(SelectOutChargeListActivity.this)
                .showCamera(true) // ??????????????????. ???????????????
//                .count(1) // ????????????????????????, ?????????9. ???????????????????????????????????????
                .single() // ????????????
//                .multi() // ????????????, ????????????;
//                .origin(ArrayList<String>) // ?????????????????????. ???????????????????????????????????????
                .start(SelectOutChargeListActivity.this, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // ???????????????????????????
                Util.showGifProgressDialog(SelectOutChargeListActivity.this);
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
                                    Util.hideGifProgressDialog(SelectOutChargeListActivity.this);
                                    showDialogInfo(result);

                                } else {
                                    Util.hideGifProgressDialog(SelectOutChargeListActivity.this);
                                    Toast.makeText(SelectOutChargeListActivity.this, "?????????????????????????????????????????????", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(SelectOutChargeListActivity.this);
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

    List<SelectOutPatientChargesBean> resultList = new ArrayList<>();

    public void showadpter(HttpResultBaseBeanForFingertip<String> bean, boolean isloadmore) {
        if (bean != null) {
            String resultString = bean.getResult();
            try {
                resultList = JSON.parseObject(resultString,
                        new TypeReference<List<SelectOutPatientChargesBean>>() {
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isloadmore) {

            }
            panelListView.setNeedShortTitle();
            //???????????????
            panelListView.setHeaderListData(new String[]{"????????????", "????????????", "????????????", "????????????", "????????????", "????????????", "????????????", "????????????", "????????????", "????????????"});
            stockListAdapter = new StockListAdapter(this, resultList, R.layout.adpter_item_layout);
            panelListView.setAdapter(stockListAdapter);
//            stockListAdapter.notifyDataSetChanged();
            panelListView.setOnItemClick(new PanelListView.OnItemClickedListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(SelectOutChargeListActivity.this, position + "", Toast.LENGTH_SHORT).show();
                }
            });
            panelListView.setOnRefreshListener(new PanelListView.OnRefreshListener() {
                @Override
                public void onPullRefresh() {
                    ispull = 2;
                    onRefresh();
                    panelListView.setRefreshCompleted();
//                    panelListView.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    }, 1000);

                }
            });
            panelListView.setOnLoadMoreListener(new PanelListView.OnLoadMoreListener() {
                @Override
                public void onLoadingMore() {

//                    panelListView.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    }, 1000);
                    onLoadMoreRequested();
                    panelListView.onLoadingComplete();

                }
            });
//            //????????????item
//            panelListView.setOnItemClick((parent, view, position, id) -> Toast.makeText(SelectOutChargeListActivity.this, position + "", Toast.LENGTH_SHORT).show());
//            //??????????????????
//            panelListView.setOnHeaderClickedListener(string -> Toast.makeText(SelectOutChargeListActivity.this, string, Toast.LENGTH_SHORT).show());
//
//            //?????????????????????
//            panelListView.setOnHeaderSortClickListener((cIndex, imageView) -> {
//                sortType = changeShortType(cIndex, sortType);
//                currentTabIndex = cIndex;
//                setTitleSortImg(cIndex, imageView);
//                //??????????????????
//            });

//            panelListView.setOnRefreshListener(() -> panelListView.postDelayed(() -> {
//                //????????????????????????
//                onLoadMoreRequested();
//                panelListView.setRefreshCompleted();
//            }, 1000));
//
//            panelListView.setOnLoadMoreListener(() -> panelListView.postDelayed(() -> {
//                //????????????????????????
//                panelListView.onLoadingComplete();
//            }, 1000));
        }
    }

    private void setTitleSortImg(int cIndex, ImageView imageView) {
        imageView.setImageResource(getSortImg(sortType));
        if (sortType != 2) {
            panelListView.initTitleImageRight(cIndex, true);
        } else {
            panelListView.initTitleImageRight(cIndex, false);
        }
    }


    private int changeShortType(int cIndex, int sortType) {
        if (cIndex != currentTabIndex) {
            sortType = 2;
        }
        currentTabIndex = cIndex;
        if (sortType == 2) {
            return 1;
        } else if (sortType == 1) {
            return 0;
        } else {
            return 2;
        }
    }


    private int getSortImg(int sortType) {
        int riseImgId;
        if (sortType == 0) {
            riseImgId = R.mipmap.rise_img;
        } else if (sortType == 1) {
            riseImgId = R.mipmap.fall_img;
        } else {
            riseImgId = R.mipmap.stocksign;
        }

        return riseImgId;
    }

    private String getSignTimeString() {
        String s = Util.trimString(start_time.getText().toString());
        return s;
    }

    private String getNextTimeString() {
        String s = Util.trimString(end_time.getText().toString());
        return s;
    }

    String sNowDate, sNowDate1;

    public void setDataText() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//??????????????????
        sNowDate = formatter.format(curDate);

        Calendar ca = Calendar.getInstance();//????????????Calendar?????????
        ca.setTime(new Date()); //???????????????????????????
//        ca.add(Calendar.YEAR, -1); //?????????1
        ca.add(Calendar.MONTH, -1);
        // ca.add(Calendar.DATE, -7); //?????????1
        Date lastMonth = ca.getTime(); //??????
        sNowDate1 = formatter.format(lastMonth);
//????????????ca.add(Calendar.MONTH, -1)???
//?????????ca.add(Calendar.DATE, -1)
        start_time.setText(sNowDate1);
        end_time.setText(sNowDate);
    }

    public void setToWeekText() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//??????????????????
        sNowDate = formatter.format(curDate);

        Calendar ca = Calendar.getInstance();//????????????Calendar?????????
        ca.setTime(new Date()); //???????????????????????????
//        ca.add(Calendar.YEAR, -1); //?????????1
        //  ca.add(Calendar.MONTH, -1);
        ca.add(Calendar.DATE, -7); //?????????1
        Date lastMonth = ca.getTime(); //??????
        sNowDate1 = formatter.format(lastMonth);
//????????????ca.add(Calendar.MONTH, -1)???
//?????????ca.add(Calendar.DATE, -1)
        start_time.setText(sNowDate1);
        end_time.setText(sNowDate);
    }
}
