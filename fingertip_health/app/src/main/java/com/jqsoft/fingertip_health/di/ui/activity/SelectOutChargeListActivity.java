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


//收费查询列表页
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
        online_consultation_title.setText("收费查询");
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
                //   Toast.makeText(getApplicationContext(),"服务时间",Toast.LENGTH_SHORT).show();
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
                //   Toast.makeText(getApplicationContext(),"服务时间",Toast.LENGTH_SHORT).show();
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
                //   Toast.makeText(getApplicationContext(),"服务时间",Toast.LENGTH_SHORT).show();
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
        et_search.setHint("请输入姓名或档案编号");
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Log.i(TAG, "输入文字中的状态，count是输入字符数");

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // Log.i(TAG, "输入文本之前的状态");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Log.i(TAG, "输入文字后的状态");
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
                //  Toast.makeText(getApplicationContext(),"拍照",Toast.LENGTH_SHORT).show();
                if (ActivityCompat.checkSelfPermission(SelectOutChargeListActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // 应用没有读取手机外部存储的权限
                    // 申请WRITE_EXTERNAL_STORAGE权限
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
                    Util.showToast(getApplicationContext(), "没有数据");
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
                Util.showToast(getApplicationContext(), "没有数据");
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
                .showCamera(true) // 是否显示相机. 默认为显示
//                .count(1) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                .single() // 单选模式
//                .multi() // 多选模式, 默认模式;
//                .origin(ArrayList<String>) // 默认已选择图片. 只有在选择模式为多选时有效
                .start(SelectOutChargeListActivity.this, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                Util.showGifProgressDialog(SelectOutChargeListActivity.this);
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理你自己的逻辑 ....
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
                                    // 识别成功
                                    Util.hideGifProgressDialog(SelectOutChargeListActivity.this);
                                    showDialogInfo(result);

                                } else {
                                    Util.hideGifProgressDialog(SelectOutChargeListActivity.this);
                                    Toast.makeText(SelectOutChargeListActivity.this, "识别失败，请拍照清楚后重新识别", Toast.LENGTH_SHORT).show();
                                /*switch (result.getErrorcode()){
                                    case -7001:
                                        Toast.makeText(MainActivity.this, "未检测到身份证，请对准边框(请避免拍摄时倾角和旋转角过大、摄像头)", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7002:
                                        Toast.makeText(MainActivity.this, "请使用第二代身份证件进行扫描", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7003:
                                        Toast.makeText(MainActivity.this, "不是身份证正面照片(请使用带证件照的一面进行扫描)", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7004:
                                        Toast.makeText(MainActivity.this, "不是身份证反面照片(请使用身份证反面进行扫描)", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7005:
                                        Toast.makeText(MainActivity.this, "确保扫描证件图像清晰", Toast.LENGTH_SHORT).show();
                                        break;
                                    case -7006:
                                        Toast.makeText(MainActivity.this, "请避开灯光直射在证件表面", Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        Toast.makeText(MainActivity.this, "识别失败，请稍后重试", Toast.LENGTH_SHORT).show();
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
     * 显示对话框
     *
     * @param result
     */
    private void showDialogInfo(final IdentifyResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("姓名：" + result.getName() + "\n");
        sb.append("性别：" + result.getSex() + "\t" + "民族：" + result.getNation() + "\n");
        sb.append("出生：" + result.getBirth() + "\n");
        sb.append("住址：" + result.getAddress() + "\n" + "\n");
        sb.append("公民身份号码：" + result.getId() + "\n");
        AlertDialog.Builder builder = new AlertDialog.Builder(SelectOutChargeListActivity.this);
        AlertDialog dialogInfo = builder.setTitle("识别成功")
                .setMessage(sb.toString())
                /*.setPositiveButton("复制号码", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("text", result.getId());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(SocialAssistanceObject.this, "身份证号已复制到粘贴板", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })*/
                .setNegativeButton("取消", null)
                .create();
        dialogInfo.show();

        et_search.setText(result.getId());

    }

    /**
     * 获取压缩后的图片
     *
     * @param srcPath
     * @return
     */
    private Bitmap getImage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

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
            if (options < 0) {
                image.compress(Bitmap.CompressFormat.JPEG, 10, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                break;
            } else {
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            }

            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
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
            //定义顶部栏
            panelListView.setHeaderListData(new String[]{"记账时间", "费用总额", "统筹补偿", "应收金额", "舍入金额", "费用状态", "疾病名称", "医疗证号", "开单科室", "开单医生"});
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
//            //点击列表item
//            panelListView.setOnItemClick((parent, view, position, id) -> Toast.makeText(SelectOutChargeListActivity.this, position + "", Toast.LENGTH_SHORT).show());
//            //点击头部按钮
//            panelListView.setOnHeaderClickedListener(string -> Toast.makeText(SelectOutChargeListActivity.this, string, Toast.LENGTH_SHORT).show());
//
//            //排序的头部按钮
//            panelListView.setOnHeaderSortClickListener((cIndex, imageView) -> {
//                sortType = changeShortType(cIndex, sortType);
//                currentTabIndex = cIndex;
//                setTitleSortImg(cIndex, imageView);
//                //执行排序操作
//            });

//            panelListView.setOnRefreshListener(() -> panelListView.postDelayed(() -> {
//                //执行你的网络请求
//                onLoadMoreRequested();
//                panelListView.setRefreshCompleted();
//            }, 1000));
//
//            panelListView.setOnLoadMoreListener(() -> panelListView.postDelayed(() -> {
//                //执行你的网络请求
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
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        sNowDate = formatter.format(curDate);

        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(new Date()); //设置时间为当前时间
//        ca.add(Calendar.YEAR, -1); //年份减1
        ca.add(Calendar.MONTH, -1);
        // ca.add(Calendar.DATE, -7); //年份减1
        Date lastMonth = ca.getTime(); //结果
        sNowDate1 = formatter.format(lastMonth);
//求前一月ca.add(Calendar.MONTH, -1)，
//前一天ca.add(Calendar.DATE, -1)
        start_time.setText(sNowDate1);
        end_time.setText(sNowDate);
    }

    public void setToWeekText() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        sNowDate = formatter.format(curDate);

        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(new Date()); //设置时间为当前时间
//        ca.add(Calendar.YEAR, -1); //年份减1
        //  ca.add(Calendar.MONTH, -1);
        ca.add(Calendar.DATE, -7); //年份减1
        Date lastMonth = ca.getTime(); //结果
        sNowDate1 = formatter.format(lastMonth);
//求前一月ca.add(Calendar.MONTH, -1)，
//前一天ca.add(Calendar.DATE, -1)
        start_time.setText(sNowDate1);
        end_time.setText(sNowDate);
    }
}
