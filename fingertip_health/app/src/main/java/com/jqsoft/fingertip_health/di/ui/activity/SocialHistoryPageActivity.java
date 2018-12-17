package com.jqsoft.fingertip_health.di.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.HistorySocialPageAdapter;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.SocailHistoryDetailsBean;
import com.jqsoft.fingertip_health.bean.SocialHisDetail;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.HistoryDetailContract;
import com.jqsoft.fingertip_health.di.module.HistoryDetailModule;
import com.jqsoft.fingertip_health.di.presenter.HistoryDetailPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.view.IDCard;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

import static com.jqsoft.fingertip_health.R.id.title_his;

/**
 * Created by Administrator on 2018/1/11.
 */

public class SocialHistoryPageActivity extends AbstractActivity implements HistoryDetailContract.View, View.OnClickListener {
    @BindView(R.id.online_consultation_title)
    TextView online_consultation_title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.objectname)
    TextView objectname;
    @BindView(R.id.objectidcard)
    TextView objectidcard;
    @BindView(R.id.objectsex)
    ImageView objectsex;
    @BindView(R.id.iv_title)
    SimpleDraweeView draweeView;
    @BindView(R.id.sociallist)
    ListView sociallist;
    @BindView(R.id.basename)
    TextView basename;
    @BindView(R.id.basesex)
    TextView basesex;
    @BindView(R.id.basenative)
    TextView basenative;
    @BindView(R.id.basebirthday)
    TextView basebirthday;
    @BindView(R.id.baseidcard)
    TextView baseidcard;
    @BindView(R.id.title_baseinfo)
    RelativeLayout title_baseinfo;
    @BindView(R.id.title_his)
    RelativeLayout title_his;
    @BindView(R.id.title_basectx)
    LinearLayout title_basectx;
    @BindView(R.id.sv_content)
    ScrollView sv_content;
    @BindView(R.id.lay_his)
    View content_his;
    @BindView(R.id.minzu_re)
    RelativeLayout minzu_re;
    private Boolean isAnimating = false;
    private ArrayList<SocailHistoryDetailsBean> progressHisdata = new ArrayList<>();
    private ArrayList<SocialHisDetail> ReliefHisVoList = new ArrayList<>();
    private HistorySocialPageAdapter mAdapter;
    private int mFoldedViewMeasureHeight_baseinfo;
    private int mFoldedViewMeasureHeight_his;
    @Inject
    HistoryDetailPresenter mPresenter;
    private String cardNo;
    private float mDensity;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_socialhisdetail;
    }

    @Override
    protected void initData() {

    }

    public Map<String, String> getRequstMapHis() {
        Map<String, String> map = ParametersFactory.getHistoryDetail(this, cardNo);
        return map;
    }

    @Override
    protected void initView() {
        online_consultation_title.setText("救助历史详情");
        minzu_re.setVisibility(View.GONE);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title_baseinfo.setOnClickListener(SocialHistoryPageActivity.this);
        title_his.setOnClickListener(SocialHistoryPageActivity.this);
        mDensity = getResources().getDisplayMetrics().density;

        measureHeight();
    }
    private void measureHeight(){
        //获取布局的高度
//        int w = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,
//                View.MeasureSpec.AT_MOST);
//        int h = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,
//                View.MeasureSpec.AT_MOST);
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        title_basectx.measure(w, h);
        content_his.measure(w, h);

    }

    @Override
    protected void loadData() {
        Bundle bundle = getIntent().getExtras();
        cardNo = bundle.getString("cardNo");//读出数据
        objectidcard.setText(cardNo);
        //  progressHisdata = (ArrayList<SocailHistoryDetailsBean>) getIntent().getSerializableExtra("progressHisdata");



            if (cardNo.length()<17){

            }else {
                String sex = IDCard.getSex(Util.trimString(cardNo));
                if (sex.equals("男")) {
                    objectsex.setImageResource(R.mipmap.icon_sex_man);
                } else {
                    objectsex.setImageResource(R.mipmap.icon_sex_woman);
                }
                basebirthday.setText(IDCard.getbirthdayNew(Util.trimString(cardNo)));
            }



        Map<String, String> map = getRequstMapHis();
        mPresenter.gethisdetail(map, false);

    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addhisdetailPage(new HistoryDetailModule(this))
                .inject(this);
    }

    @Override
    public void onLoadHistoryDataSuccess(GCAHttpResultBaseBean<List<SocailHistoryDetailsBean>> bean) {
        if (bean.getData().size() > 0) {
            progressHisdata.addAll(bean.getData());

            objectname.setText(progressHisdata.get(0).getName());
            if ((progressHisdata.get(0).getCardNo().length()<17)){
                Toast.makeText(this,"获取身份证号码长度有误。",Toast.LENGTH_SHORT).show();
            }else {
            String sex = IDCard.getSex(Util.trimString(progressHisdata.get(0).getCardNo()));
            if (sex.equals("男")) {
                objectsex.setImageResource(R.mipmap.icon_sex_man);
            } else {
                objectsex.setImageResource(R.mipmap.icon_sex_woman);
            }
            }
            String imageUrl = Version.FIND_FILE_URL_BASE + progressHisdata.get(0).getFilePath();
            Uri uri = Uri.parse(imageUrl);
            draweeView.setImageURI(uri);
            objectidcard.setText(progressHisdata.get(0).getCardNo());
            basename.setText(progressHisdata.get(0).getName());
            baseidcard.setText(progressHisdata.get(0).getCardNo());
            basesex.setText(progressHisdata.get(0).getSex());
            final List<SRCLoginDataDictionaryBean> myregpro = DataSupport.where(" pcode=? and state=?", "nation","0").find(SRCLoginDataDictionaryBean.class);
            String nation = progressHisdata.get(0).getNation();
            if (!TextUtils.isEmpty(nation)) {

                for (int i = 0; i < myregpro.size(); i++) {
                    if (nation.equals(myregpro.get(i).getCode())) {
                        basenative.setText(myregpro.get(i).getName());
                    } else {
                        basenative.setText("无");
                    }

                }

            } else if (null==nation){
                basenative.setText("未知");
            }
            else  if (nation.equals("未知")) {
                basenative.setText("未知");

            }
            basebirthday.setText(IDCard.getbirthdayNew(Util.trimString(progressHisdata.get(0).getCardNo())));
            //  basebirthday.setText(progressHisdata.get(0).getBirthday());
            ReliefHisVoList.addAll(progressHisdata.get(0).getReliefHisVoList());
            mAdapter = new HistorySocialPageAdapter(this, ReliefHisVoList);
            sociallist.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            Util.showToast(getApplicationContext(), bean.getMsg());
        }
        int height1 = title_basectx.getMeasuredHeight();
        mFoldedViewMeasureHeight_baseinfo = (int) (height1);
        int height6 = setListViewHeightBasedOnChildren(sociallist, mAdapter);
        mFoldedViewMeasureHeight_his = (int) (height6);

        sv_content.postInvalidate();

    }
    private void animateClose(final View view, int calcHeight) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, calcHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                sv_content.postInvalidate();
                isAnimating = false;
            }
        });
        animator.start();
    }

    private void animateOpen(View view, int mFoldedViewMeasureHeight) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view, 0, mFoldedViewMeasureHeight);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
            }
        });
        animator.start();
    }
    private ValueAnimator createDropAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
//        animator.setDuration(1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
                LogUtil.i("drop animator height:" + value);
            }
        });
        return animator;
    }
    public static int setListViewHeightBasedOnChildren(ListView listView, Adapter adapter) {
        //获得adapter
        // SocailDetailHisAdapter adapter = (SocailDetailHisAdapter) listView.getAdapter();
        adapter = listView.getAdapter();
        if (adapter == null) {
            return 0;
        }

        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//            listItem.measure(0, 0);
            //计算总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int div = (listView.getDividerHeight() * (adapter.getCount() - 1));
        int ultimateHeight = totalHeight + div;
        //计算分割线高度
        params.height = ultimateHeight;
        //给listview设置高度
        listView.setLayoutParams(params);
        return ultimateHeight;
    }

    @Override
    public void onLoadHisdataFailure(String message, boolean isLoadMore) {
        Util.showToast(getApplicationContext(), message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_baseinfo:
                if (mFoldedViewMeasureHeight_baseinfo > 0) {

                    if (isAnimating) return;
                    //如果动画没在执行,走到这一步就将isAnimating制为true , 防止这次动画还没有执行完毕的
                    //情况下,又要执行一次动画,当动画执行完毕后会将isAnimating制为false,这样下次动画又能执行
                    isAnimating = true;
                    if (title_basectx.getVisibility() == View.GONE) {

                        //打开动画
                        animateOpen(title_basectx, mFoldedViewMeasureHeight_baseinfo);
                    } else {
                        //关闭动画
                        animateClose(title_basectx, mFoldedViewMeasureHeight_baseinfo);
                    }
                    sv_content.postInvalidate();

                } else {
                    Util.showToast(getApplicationContext(), "没有基本信息");
                }
                break;
            case R.id.title_his:
                if (mFoldedViewMeasureHeight_his > 0) {
                    if (isAnimating) return;
                    //如果动画没在执行,走到这一步就将isAnimating制为true , 防止这次动画还没有执行完毕的
                    //情况下,又要执行一次动画,当动画执行完毕后会将isAnimating制为false,这样下次动画又能执行
                    isAnimating = true;
                    if (content_his.getVisibility() == View.GONE) {
                        //打开动画
                        animateOpen(content_his, mFoldedViewMeasureHeight_his);
                    } else {
                        //关闭动画
                        animateClose(content_his, mFoldedViewMeasureHeight_his);

                    }

                    sv_content.postInvalidate();
                } else {
                    Util.showToast(getApplicationContext(), "没有救助历史信息");
                }
                break;
        }
    }
}
