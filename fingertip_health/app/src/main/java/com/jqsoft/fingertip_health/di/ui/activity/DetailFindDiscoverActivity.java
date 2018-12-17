package com.jqsoft.fingertip_health.di.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.DetailFindDiscoverAdapter;
import com.jqsoft.fingertip_health.adapter.GridImageNewAdapter;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.DetailFindBeans;
import com.jqsoft.fingertip_health.bean.DiscoverListBean;
import com.jqsoft.fingertip_health.bean.DiscoverManageBean;
import com.jqsoft.fingertip_health.bean.FileListBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.di.contract.AddFindContract;
import com.jqsoft.fingertip_health.di.module.AddFindModule;
import com.jqsoft.fingertip_health.di.presenter.DetailFindDiscoverPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyGridLayoutManager;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.PictureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailFindDiscoverActivity extends AbstractActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener,AddFindContract.View{

    @BindView(R.id.title_baseinfo)
    RelativeLayout title_baseinfo;

/*    @BindView(R.id.title_basectx)
    LinearLayout title_basectx;*/

    @BindView(R.id.title_manageinfo)
    RelativeLayout title_manageinfo;


    @BindView(R.id.tv_xiangzhen)
    TextView tv_xiangzhen;
    @BindView(R.id.tv_myphone)
    TextView tv_myphone;
    @BindView(R.id.tv_myadress)
    TextView tv_myadress;
    @BindView(R.id.tv_reason)
    TextView tv_reason;

   /* @BindView(R.id.tv_managepeople)
    TextView tv_managepeople;
    @BindView(R.id.tv_manage_mode)
    TextView tv_manage_mode;
    @BindView(R.id.tv_manage_remark)
    TextView tv_manage_remark;
    @BindView(R.id.tv_manage_time)
    TextView tv_manage_time;*/

    @BindView(R.id.sv_content)
    NestedScrollView sv_content;

    @BindView(R.id.lay_his)
    View content_his;

    @BindView(R.id.lay_his_base)
    View lay_his_base;

    @BindView(R.id.sociallist)
    ListView sociallist;


    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.srl)
    SwipeRefreshLayout srl;


    @BindView(R.id.btn_add_handle)
    RelativeLayout btn_add_handle;

    private int mFoldedViewMeasureHeight_his;


    private Boolean isAnimating = false;

    private float mDensity;

    private int mFoldedViewMeasureHeight_baseinfo;
    private int mFoldedViewMeasureHeight_manageinfo;
    private String batchNo,isMine;

    private int mFoldedViewMeasureHeight_New;

    private DetailFindDiscoverAdapter hisAdapter;

    private GridImageNewAdapter adapter;
    private List<LocalMedia> selectMedia = new ArrayList<>();

    private  DetailFindBeans detailFindBeans;

    @Inject
    DetailFindDiscoverPresenter mPresenter;

    @BindView(R.id.lay_inhospital_load_failure)
    View failureView;

    @BindView(R.id.lay_chuli_load_failure)
    View failureChuliView;


    TextView tvFailureView;

    public static final int REQUEST_A = 1;
    private String name;


    @BindView(R.id.ll_back)
    LinearLayout ll_back;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_find_discover;
    }

    @Override
    protected void initData() {
        btn_add_handle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("batchNo", detailFindBeans.getDiscover().getBatchNo());
                bundle.putString("discoverId", detailFindBeans.getDiscover().getId());
                bundle.putString("userName", detailFindBeans.getDiscover().getEditor());

                Intent i = new Intent(DetailFindDiscoverActivity.this, HandleFindActivity.class);
                i.putExtras(bundle);

                startActivityForResult(i, REQUEST_A);

             /* String test =  IDCard.IDCardValidate("320827197706014018");*/

            }
        });

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
       // onRefresh();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //先判断是哪个页面返回过来的
        switch (requestCode) {
            case REQUEST_A:

                //再判断返回过来的情况，是成功还是失败还是其它的什么……
                switch (resultCode) {
                    case HandleFindActivity.RESULT_SUCCESS:
                        //成功了
                        onRefresh();
                        break;
                    case HandleFindActivity.RESULT_FAILED:
                        //失败了
                        break;
                }
                break;

        }
    }


    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .detaildiscoverFind(new AddFindModule(this))
                .inject(this);
    }


    @Override
    public void onRefresh() {
        Map<String, String> map = ParametersFactory.getDetailFind(DetailFindDiscoverActivity.this,name,
                batchNo,isMine);
        mPresenter.maindetail(map);
    }

    @Override
    protected void initView() {
        title_baseinfo.setOnClickListener(DetailFindDiscoverActivity.this);
        title_manageinfo.setOnClickListener(DetailFindDiscoverActivity.this);
        mDensity = getResources().getDisplayMetrics().density;
        //获取布局的高度
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        lay_his_base.measure(w, h);


       /* int height2 = title_managectx.getMeasuredHeight();
        mFoldedViewMeasureHeight_manageinfo = (int) (height2 );*/
        content_his.measure(w, h);

        batchNo=getDeliveredStringByKey("batchNo");
        isMine=getDeliveredStringByKey("isMine");

         name= IdentityManager.getLoginSuccessUsername(getApplicationContext());


        onRefresh();

        final FullyGridLayoutManager manager = new FullyGridLayoutManager(DetailFindDiscoverActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageNewAdapter(DetailFindDiscoverActivity.this, onAddPicClickListener);
        adapter.setList(selectMedia);
        adapter.setSelectMax(1);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new GridImageNewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                switch (1) {
                    case FunctionConfig.TYPE_IMAGE:
                        // 预览图片 可长按保存 也可自定义保存路径
                        //PictureConfig.getInstance().externalPicturePreview(MainActivity.this, "/custom_file", position, selectMedia);
                        PictureConfig.getInstance().externalPicturePreview(DetailFindDiscoverActivity.this, position, selectMedia);
                        break;
                    case FunctionConfig.TYPE_VIDEO:
                        // 预览视频
                        if (selectMedia.size() > 0) {
                            PictureConfig.getInstance().externalPictureVideo(DetailFindDiscoverActivity.this, selectMedia.get(position).getPath());
                        }
                        break;
                }

            }
        });

        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        srl.setOnRefreshListener(this);



    }

    @Override
    protected void loadData() {

    }

    private int ceszhuan=0;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_baseinfo:
                if (isAnimating) return;
                //如果动画没在执行,走到这一步就将isAnimating制为true , 防止这次动画还没有执行完毕的
                //情况下,又要执行一次动画,当动画执行完毕后会将isAnimating制为false,这样下次动画又能执行
                isAnimating = true;
                if (lay_his_base.getVisibility() == View.GONE) {

                    //打开动画
                    animateOpen(lay_his_base, ceszhuan);
                } else {
                    int height1 = lay_his_base.getMeasuredHeight();
                    mFoldedViewMeasureHeight_baseinfo = (int) (height1 );
                    ceszhuan=mFoldedViewMeasureHeight_baseinfo;
                    //关闭动画
                    animateClose(lay_his_base, mFoldedViewMeasureHeight_baseinfo);
                }
                sv_content.postInvalidate();
                break;
            case R.id.title_manageinfo:
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

                break;

        }

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
                LogUtil.i("drop animator height:"+value);
            }
        });
        return animator;
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

    @Override
    public void onAddFindSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {

    }

    @Override
    public void onAddFindFailure(String message) {

    }

    @Override
    public void onDetailFindSuccess(HttpResultBaseBean<DetailFindBeans> bean) {

        if(bean!=null){
            srl.setRefreshing(false);
            Toast.makeText(getApplicationContext(),"加载成功", Toast.LENGTH_SHORT).show();
            detailFindBeans=null;
            detailFindBeans  = bean.getData();
            DiscoverListBean discoverListBean =detailFindBeans.getDiscover();



            List<DiscoverManageBean> discoverManageBeanList = new ArrayList<>();
            discoverManageBeanList.clear();
            discoverManageBeanList.addAll(detailFindBeans.getDiscoverLogList());


            List<FileListBean> fileList = new ArrayList<>();
            fileList.clear();
            fileList.addAll(detailFindBeans.getFileList());
            selectMedia.clear();
            for(int i=0;i<fileList.size();i++){
                LocalMedia localMedia = new LocalMedia();
                localMedia.setUrl(Version.FILE_URL_BASE+fileList.get(i).getFilePath());
                localMedia.setPath("test");

                localMedia.setType(1);
                localMedia.setFileId(fileList.get(i).getFileId());
                selectMedia.add(localMedia);
            }

            adapter.setList(selectMedia);
            adapter.notifyDataSetChanged();

            if(fileList.size()==0){
                failureView.setVisibility(View.VISIBLE);
            }else {
                failureView.setVisibility(View.GONE);
            }


           /* setListViewHeightBasedOnChildren(recyclerView, adapter);*/
            int height7 =getLastVisiblePosition();
            mFoldedViewMeasureHeight_New = (int) (height7 );


            tv_xiangzhen.setText(discoverListBean.getOfficeName()+discoverListBean.getCommunityName());
            tv_myphone.setText(Util.isMyEmpty(discoverListBean.getDiscoverPhone()));
            tv_myadress.setText(Util.isMyEmpty(discoverListBean.getFamilyAddress()));
            tv_reason.setText(Util.isMyEmpty(discoverListBean.getSriReason()));

          /*  tv_managepeople.setText(discoverManageBean.getEditor());

            String act =discoverManageBean.getAct();
            if(act.equals("2")){
                tv_manage_mode.setText("处理中");
            }else if(act.equals("3")){
                tv_manage_mode.setText("已处理");
            }

            tv_manage_remark.setText(discoverManageBean.getRemarks());
            tv_manage_time.setText("");*/

            hisAdapter = new DetailFindDiscoverAdapter(this, discoverManageBeanList);
            sociallist.setAdapter(hisAdapter);

            if(discoverManageBeanList.size()==0){
                failureChuliView.setVisibility(View.VISIBLE);
            }else {
                failureChuliView.setVisibility(View.GONE);
            }
//        hisAdapter.notifyDataSetChanged();
            setListViewHeightBasedOnChildren(sociallist, hisAdapter);
            int height6 = setListViewHeightBasedOnChildren(sociallist, hisAdapter);
            mFoldedViewMeasureHeight_his = (int) (height6 );

            sv_content.postInvalidate();
        }
    }

    @Override
    public void onDetailFindFailure(String message) {
        srl.setRefreshing(false);
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
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
        int ultimateHeight = totalHeight +div;
        //计算分割线高度
        params.height =  ultimateHeight;
        //给listview设置高度
        listView.setLayoutParams(params);
        return ultimateHeight;
    }

    public int getLastVisiblePosition() {
        RecyclerView.LayoutManager layoutManager =recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
            int lastItemPosition = linearManager.findLastVisibleItemPosition();
            return lastItemPosition;

        }

        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int lastItemPosition = gridLayoutManager.findLastVisibleItemPosition();
            return lastItemPosition;

        }

        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int first[] = new int[staggeredGridLayoutManager.getSpanCount()];
            staggeredGridLayoutManager.findLastVisibleItemPositions(first);

            ArrayList<Integer> list = new ArrayList<>(first.length);
            if (list == null || list.size() == 0) {
                return -1;
            }
            return list.get(list.size() - 1);
        }

        return -1;

    }

    /**
     * 删除图片回调接口
     */

    private GridImageNewAdapter.onAddPicClickListener onAddPicClickListener = new GridImageNewAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(int type, int position) {
            switch (type) {
                case 0:

                    break;
                case 1:
                    // 删除图片
                  /*  selectMedia.remove(position);
                    adapter.notifyItemRemoved(position);*/
                    break;
            }
        }
    };
}
