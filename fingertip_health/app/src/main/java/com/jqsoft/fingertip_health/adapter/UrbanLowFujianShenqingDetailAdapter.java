package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianBean;
import com.jqsoft.fingertip_health.di.presenter.UrbanLowFamilyFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.fragment.UrbanFuJianFragment;
import com.jqsoft.fingertip_health.util.InputDialog;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.GlideUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.model.PictureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//政策新闻适配器
public class UrbanLowFujianShenqingDetailAdapter extends BaseQuickAdapterEx<UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;

    private int type=TYPE_MULTIPLE_LINE;
    private Context context;

    private UrbanLowFamilyFragmentPresenter mPresenter;
    private UrbanLowFujianBean.Jiuzhuxiang itemJiuzhuxiang;


    DeleteNewListener mDeleteNewListener;
    private String mybatchNo;
    private UrbanFuJianFragment urbanFuJianFragment;

    public interface DeleteNewListener {
        public void onDeleteNewClick(String s);
    }

    public void setOnDeleteNewClickListener (DeleteNewListener  DeleteNewListener) {
        this.mDeleteNewListener = DeleteNewListener;
    }

    public UrbanLowFujianShenqingDetailAdapter(List<UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang> data, int type,Context context,UrbanLowFamilyFragmentPresenter mPresenter,UrbanLowFujianBean.Jiuzhuxiang itemJiuzhuxiang,String mybatchNo,UrbanFuJianFragment urbanFuJianFragment) {
        super(R.layout.item_urbanlow_fujian_shenqingdetail, data);
        this.type = type;
        this.context=context;
        this.mPresenter=mPresenter;
        this.itemJiuzhuxiang =itemJiuzhuxiang;
     //   this.mybatchNo=mybatchNo;
      //  EventBus.getDefault().register(this);
        this.urbanFuJianFragment=urbanFuJianFragment;
    }

//    private int getLayoutId(){
//        int result = R.layout.item_policy_single_line;
//        if (type == TYPE_SINGLE_LINE){
//            result=R.layout.item_policy_single_line;
//        } else if (type == TYPE_MULTIPLE_LINE){
//            result=R.layout.item_policy_multiple_line;
//        }
//        return result;
//    }

    @Override
    protected void convert(final BaseViewHolder helper, final UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang item) {
        helper.setText(R.id.tv_name, Util.trimString(item.getFileName()));
        final ImageView iv_tupian = helper.getView(R.id.iv_tupian);

     //   String FIND_FILE_URL_BASE ="http://192.168.44.134:8080/sri";
        String FIND_FILE_URL_BASE = Version.FILE_URL_BASE;
        String imageUrl =FIND_FILE_URL_BASE+item.getFilePath();
        GlideUtils.loadImageNew(imageUrl, (ImageView) iv_tupian);

        final Button btn_del = helper.getView(R.id.btn_del);
        final Button btn_bianji = helper.getView(R.id.btn_bianji);
        final Button btn_yulan = helper.getView(R.id.btn_yulan);

        final String mybatchNoNew = urbanFuJianFragment.getbatchNo();

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 =item.getFileId();
               // Toast.makeText(context,s1+"啦",Toast.LENGTH_SHORT).show();
              //  mDeleteNewListener.onDeleteNewClick(Util.trimString(s1));
              /*  Map<String, String> map = getRequestMap();
                mPresenter.mainfujian(map);*/
                Map<String, String> map = ParametersFactory.getUrbanLowfujiandeleteMap(context,
                        s1,mybatchNoNew);
                mPresenter.mainfujiandelete(map);
            }
        });



        btn_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputDialog inputDialog = new InputDialog(context).builder().setTitle("编辑")
                        .setPositiveBtn("确定", new InputDialog.OnPositiveListener() {
                            @Override
                            public void onPositive(View view, String inputMsg) {
                                if(TextUtils.isEmpty(inputMsg)){
                                    Toast.makeText(context,"编辑内容不能为空!",Toast.LENGTH_SHORT).show();
                                }else {
                                    String fileId =item.getFileId();
                                    String fileName =inputMsg;
                                    String fileType =itemJiuzhuxiang.getFileCode();
                                //    String batchId ="20180131083618759";
                                    String batchId =mybatchNoNew;
                                    Map<String, String> map = ParametersFactory.getUrbanLowfujianbianjiMap(context,
                                            fileId,fileName,fileType,batchId);
                                    mPresenter.mainfujianbianji(map);
                                }
                            }
                        })
                        .setNegativeBtn("取消", null)
                        .setContentMsg("");
                inputDialog.getContentView().setHint("请输入编辑内容");
                inputDialog.getContentView().setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
                inputDialog.show();
            }
        });

        btn_yulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<LocalMedia> selectMedia = new ArrayList<>();
                selectMedia.clear();

                LocalMedia localMedia = new LocalMedia();
                localMedia.setUrl(Version.FILE_URL_BASE+item.getFilePath());
                localMedia.setPath("test");
                localMedia.setType(1);
                localMedia.setFileId(item.getFileId());
                selectMedia.add(localMedia);

                PictureConfig.getInstance().externalPicturePreview(urbanFuJianFragment.getActivity(), 0, selectMedia);
              //  PictureConfig.getInstance().externalPicturePreview(context, position, selectMedia);
            }
        });

       /* helper.setText(R.id.tv_name, Util.trimString(item.getName()));
        helper.setText(R.id.tv_idcard, Util.trimString(item.getCardNo()));

        final Button btn_bianji = helper.getView(R.id.btn_bianji);

        btn_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeleteListener.onDeleteClick(Util.trimString(item.getId()));

            }
        });*/
        /*String imageUrl = item.getMessageFirstImgSrc();
        ImageView imageView = helper.getView(R.id.iv_image);
        imageView.setTag(R.id.imageId, imageUrl);
        String url = (String) imageView.getTag(R.id.imageId);
        if (url!=null && url.equals(imageUrl)) {
            GlideUtils.loadImageWithPlaceholderAndError(imageView, imageUrl, R.mipmap.i_read_id_card, R.mipmap.i_read_id_card);
        }

        helper.setText(R.id.tv_content, Util.trimString(item.getTitle()));
        String createDate = Util.trimString(item.getReleaseTime());
        String processedCreateDate = createDate;
//        String processedCreateDate = Util.getYearMonthDayFromFullString(createDate);
//        if (type==TYPE_MULTIPLE_LINE){
            processedCreateDate= Constants.PUBLISH_TIME +processedCreateDate;
//        }
        helper.setText(R.id.tv_date,  processedCreateDate);*/

//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
//            }
//        });

    }



    //定义处理接收方法
  /*  @Subscribe
    public void onEventMainThread(UserEvent event) {
        mybatchNo =event.getUserName();
    }
*/



//    OnItemClickListener onItemClickListener;
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClickListener(String id, String imgUrl, View view);}

}
