package com.jqsoft.fingertip_health.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.AddressListAdapter;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.callback.AddressCallBack;
import com.jqsoft.fingertip_health.callback.TabOnClickListener;
import com.jqsoft.fingertip_health.di.ui.activity.ReceptionActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.DistrictFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.GuideProvinceFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.IgGuideCityFragment;
import com.jqsoft.fingertip_health.util.DensityUtils;
import com.jqsoft.fingertip_health.util.StringUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class IgGuideSelectAddressPop extends DialogFragment implements AddressCallBack {
    private ReceptionActivity.SelectAddresFinish mSelectAddresFinish;
    private Context context;
    private View view;
    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerTab;
    private FrameLayout popBg;

    private SRCLoginAreaBean province;
    private SRCLoginAreaBean city ;
    private SRCLoginAreaBean district;
    private String defutText;
    private GuideProvinceFragment mProvinceFragment;
    private IgGuideCityFragment mCityFragment;
    private DistrictFragment mDistrictFragment;

    public void setSelectAddresFinish(ReceptionActivity.SelectAddresFinish mSelectAddresFinish) {
        this.mSelectAddresFinish = mSelectAddresFinish;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
         view = inflater.inflate(R.layout.select_address_pop_layout, container, false);

        initView();
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        context = getActivity();
        initView();
        Dialog dialog = new Dialog(context, R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        return dialog;
    }

    public void setAddress(String pCode, String cCode, String aCode){
        if (StringUtils.isNoEmpty(pCode) && StringUtils.isNoEmpty(cCode) && StringUtils.isNoEmpty(aCode)){
                province =findCityByCode(cCode);
                city = findCityByCode(cCode);
                district = findCityByCode(aCode);
        }
    }

    public SRCLoginAreaBean findCityByCode(String code){
        List<SRCLoginAreaBean>   allcityList = DataSupport.where(" areaCode=? and state=?",code ,"0").find(SRCLoginAreaBean.class);

        return allcityList.get(0);
    }

    private void initView() {

//        view = LayoutInflater.from(context).inflate(R.layout.select_address_pop_layout,null);
        ImageView ivClose = (ImageView) view.findViewById(R.id.ivClose);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        pagerTab = (PagerSlidingTabStrip) view.findViewById(R.id.pagerTab);
        popBg = (FrameLayout)view.findViewById(R.id.popBg);
        defutText = "请选择";
        pagerTab.setTextSize(DensityUtils.sp2px(context, 14));
        pagerTab.setSelectedColor(getResources().getColor(R.color.new_redb));
        pagerTab.setTextColor(getResources().getColor(R.color.regis_account_exist));

        List<View> lis = new ArrayList<View>();
        mProvinceFragment = new GuideProvinceFragment(context,this);
        mCityFragment = new IgGuideCityFragment(context,this);
        mDistrictFragment = new DistrictFragment(context,this);
        lis.add(mProvinceFragment.getListview());
        lis.add(mCityFragment.getListview());
        lis.add(mDistrictFragment.getListview());
        viewPager.setAdapter(new AddressListAdapter(lis));

        String[] addres = null;
        if (province != null && city != null && district != null){
            addres = new String[]{province.getAreaName(),city.getAreaName(),district.getAreaName()};
            mProvinceFragment.setCode(province.getAreaCode());
//            mProvinceFragment.setName(province.getAreaName());
            mCityFragment.setCode(city.getAreaCode());
            mDistrictFragment.setCode(district.getAreaCode());
            viewPager.setCurrentItem(2);
            pagerTab.setTabsText(addres);
            pagerTab.setCurrentPosition(2);
        }else{
            addres = new String[]{defutText};
            viewPager.setCurrentItem(0);
            pagerTab.setTabsText(addres);
            pagerTab.setCurrentPosition(0);
        }
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        popBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        pagerTab.setTabOnClickListener(new TabOnClickListener() {
            @Override
            public void onClick(View tab, int position) {
                if (defutText.equals(pagerTab.getTabs()[position])){
                    return;
                }
                viewPager.setCurrentItem(position);
                String[] addres = null;
                switch (position){
                    case 0:
                        if (district != null) {
                            addres = new String[]{province.getAreaName(), city.getAreaName(),district.getAreaName()};
                        }else if(city != null) {
                            addres = new String[]{province.getAreaName(), city.getAreaName(), defutText};
                        }else{
                            addres = new String[]{province.getAreaName(), defutText};
                        }
                        break;
                    case 1:
                        if (district != null) {
                            addres = new String[]{province.getAreaName(), city.getAreaName(),district.getAreaName()};
                        }else {
                            addres = new String[]{province.getAreaName(), city.getAreaName(), defutText};
                        }
                        //mDistrictFragment.resetIndex();
                        break;
                    case 2:
                        addres = new String[]{province.getAreaName(),city.getAreaName(),district.getAreaName()};
                        break;
                }
                pagerTab.setTabsText(addres);
                pagerTab.setCurrentPosition(position);
            }
        });

    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //KeyBoardUtils.closeKeybord((Activity) context);
        super.show(manager, tag);
    }

    @Override
    public void selectProvince(SRCLoginAreaBean province) {
        String[] addres = new String[]{province.getAreaName(),defutText};
        pagerTab.setTabsText(addres);
        pagerTab.setCurrentPosition(1);
        viewPager.setCurrentItem(1);
        if(province != this.province){
            city = null;
            district = null;
        }
        this.province = province;
        mCityFragment.setCode(province.getAreaCode());
    }

    @Override
    public void selectCity(SRCLoginAreaBean city) {
        String[] addres = new String[]{province.getAreaName(),city.getAreaName()};
        pagerTab.setTabsText(addres);
//        pagerTab.setCurrentPosition(2);
//        viewPager.setCurrentItem(2);
//        if(city != this.city){
//            district = null;
//        }
//        this.city = city;
//        mDistrictFragment.setCode(city.getAreaCode());
    }

    @Override
    public void selectDistrict(SRCLoginAreaBean district) {
        String[] addres = new String[]{province.getAreaName(),city.getAreaName(),district.getAreaName()};
        pagerTab.setTabsText(addres);
        this.district = district;
        mSelectAddresFinish.finish(province.getAreaCode(),city.getAreaCode(),district.getAreaCode());
//        dismiss();
    }


}
