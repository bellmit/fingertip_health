package com.jqsoft.fingertip_health.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.fingertip.SignPeopleManagementItemBeanForFingertip;

import java.util.List;


//签约人群管理适配器
public class SignPeopleManagementAdapterForFingertip extends BaseQuickAdapterEx<SignPeopleManagementItemBeanForFingertip, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;

    private int type=TYPE_MULTIPLE_LINE;
    private Context context;

    public SignPeopleManagementAdapterForFingertip(Context context, List<SignPeopleManagementItemBeanForFingertip> data) {
        super(R.layout.item_sign_people_management_layout, data);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SignPeopleManagementItemBeanForFingertip item) {
////        String imageUrl = item.getMessageFirstImgSrc();
//        String pictureUrl = item.getPicture();
////        pictureUrl= Util.getUrlPathWithoutLastSlashCharacter(Version.HTTP_URL)+pictureUrl;
//        ImageView imageView = helper.getView(R.id.iv_image);
//        imageView.setTag(R.id.imageId, pictureUrl);
//        String url = (String) imageView.getTag(R.id.imageId);
//        if (url!=null && url.equals(pictureUrl)) {
//            GlideUtils.loadImageWithPlaceholderAndError(imageView, pictureUrl, R.mipmap.icon_wutu, R.mipmap.icon_wutu);
//        }

        helper.setText(R.id.tv_person_name, item.getName());
        String gender = item.getSexCode();

        helper.setText(R.id.tv_person_age, item.getAge()+"岁");
        helper.setText(R.id.tv_address, item.getAreaFulladdress());
        helper.setText(R.id.tv_date, item.getBirthday());
    }


}
