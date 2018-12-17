package com.jqsoft.fingertip_health.view.area;

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
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.AddressListAdapter;

import com.jqsoft.fingertip_health.bean.fingertip.gdws_sys_area;
import com.jqsoft.fingertip_health.callback.AddresstipCallBack;
import com.jqsoft.fingertip_health.callback.TabOnClickListener;
import com.jqsoft.fingertip_health.di.ui.activity.fingertip.PersonJiandangActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.CitytipFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.DistricttipFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.DistricttipFragment4;
import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.DistricttipFragment5;
import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.DistricttipFragment6;
import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.ProvincetipFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.DensityUtils;
import com.jqsoft.fingertip_health.util.StringUtils;
import com.jqsoft.fingertip_health.view.PagerSlidingTabStrip;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class SelectAddresstipPop extends DialogFragment implements AddresstipCallBack {
    private PersonJiandangActivity.SelectAddresFinish mSelectAddresFinish;
    private Context context;
    private View view;
    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerTab;
    private FrameLayout popBg;

    private gdws_sys_area province;
    private gdws_sys_area city ;
    private gdws_sys_area district;
    private gdws_sys_area district4;
    private gdws_sys_area district5;
    private gdws_sys_area district6;
    private String defutText;
    private ProvincetipFragment mProvinceFragment;
    private CitytipFragment mCityFragment;
    private DistricttipFragment mDistrictFragment;
    private DistricttipFragment4 mDistrictFragment4;
    private DistricttipFragment5 mDistrictFragment5;
    private DistricttipFragment6 mDistrictFragment6;

    public void setSelectAddresFinish(PersonJiandangActivity.SelectAddresFinish mSelectAddresFinish) {
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

    public void setAddress(String pCode, String cCode, String aCode, String aCode4,String aCode5,String aCode6){
        if (StringUtils.isNoEmpty(pCode) && StringUtils.isNoEmpty(cCode) && StringUtils.isNoEmpty(aCode)&& StringUtils.isNoEmpty(aCode4)&& StringUtils.isNoEmpty(aCode5)&& StringUtils.isNoEmpty(aCode6)){
//                province =findCityByCode1(pCode);
//                city = findCityByCode2(cCode);
//                district = findCityByCode3(aCode);
//            district4 = findCityByCode4(aCode4);
//            district5 = findCityByCode5(aCode5);
//            district6 = findCityByCode6(aCode6);
//
            province =findCityByCode1(pCode);
            city = findCityByCode(cCode);
            district = findCityByCode(aCode);
            district4 = findCityByCode(aCode4);
            district5 = findCityByCode(aCode5);
            district6 = findCityByCode(aCode6);


//            mCityFragment.setCode(cCode);

        }
    }

    public gdws_sys_area findCityByCode(String code){
        List<gdws_sys_area>   allcityList = DataSupport.where(" Code=? ",code ).find(gdws_sys_area.class);

        return allcityList.get(0);
    }

    public gdws_sys_area findCityByCode1(String code){
        List<gdws_sys_area> provinces= DaggerApplication.getInstance().getAreaBeanList1();
        for(int i=0;i<provinces.size();i++){
            if(provinces.get(i).getCode().equals(code)){
                return provinces.get(i);
            }
        }
        return null;
    }

    public gdws_sys_area findCityByCode2(String code){
        List<gdws_sys_area> provinces= DaggerApplication.getInstance().getAreaBeanList2();
        for(int i=0;i<provinces.size();i++){
            if(provinces.get(i).getCode().equals(code)){
                return provinces.get(i);
            }
        }
        return null;
    }

    public gdws_sys_area findCityByCode3(String code){
        List<gdws_sys_area> provinces= DaggerApplication.getInstance().getAreaBeanList3();
        for(int i=0;i<provinces.size();i++){
            if(provinces.get(i).getCode().equals(code)){
                return provinces.get(i);
            }
        }
        return null;
    }

    public gdws_sys_area findCityByCode4(String code){
        List<gdws_sys_area> provinces= DaggerApplication.getInstance().getAreaBeanList4();
        for(int i=0;i<provinces.size();i++){
            if(provinces.get(i).getCode().equals(code)){
                return provinces.get(i);
            }
        }
        return null;
    }

    public gdws_sys_area findCityByCode5(String code){
        List<gdws_sys_area> provinces= DaggerApplication.getInstance().getAreaBeanList5();
        for(int i=0;i<provinces.size();i++){
            if(provinces.get(i).getCode().equals(code)){
                return provinces.get(i);
            }
        }
        return null;
    }

    public gdws_sys_area findCityByCode6(String code){
        List<gdws_sys_area> provinces= DaggerApplication.getInstance().getAreaBeanList6();
        for(int i=0;i<provinces.size();i++){
            if(provinces.get(i).getCode().equals(code)){
                return provinces.get(i);
            }
        }
        return null;
    }

    private void initView() {

        view = LayoutInflater.from(context).inflate(R.layout.select_address_pop_layout,null);
        ImageView ivClose = (ImageView) view.findViewById(R.id.ivClose);
        TextView tv_queding = (TextView) view.findViewById(R.id.tv_queding);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        pagerTab = (PagerSlidingTabStrip) view.findViewById(R.id.pagerTab);
        popBg = (FrameLayout)view.findViewById(R.id.popBg);
        defutText = "请选择";
        pagerTab.setTextSize(DensityUtils.sp2px(context, 14));
        pagerTab.setSelectedColor(getResources().getColor(R.color.new_redb));
        pagerTab.setTextColor(getResources().getColor(R.color.regis_account_exist));

        List<View> lis = new ArrayList<View>();
        mProvinceFragment = new ProvincetipFragment(context,this);
        mCityFragment = new CitytipFragment(context,this);
        mDistrictFragment = new DistricttipFragment(context,this);
        mDistrictFragment4 = new DistricttipFragment4(context,this);
        mDistrictFragment5 = new DistricttipFragment5(context,this);
        mDistrictFragment6 = new DistricttipFragment6(context,this);
        lis.add(mProvinceFragment.getListview());
        lis.add(mCityFragment.getListview());
        lis.add(mDistrictFragment.getListview());
        lis.add(mDistrictFragment4.getListview());
        lis.add(mDistrictFragment5.getListview());
        lis.add(mDistrictFragment6.getListview());
        viewPager.setAdapter(new AddressListAdapter(lis));

        String[] addres = null;
        if (province != null && city != null && district != null && district4 != null
                && district5 != null){
            addres = new String[]{province.getName(),city.getName(),district.getName(),district4.getName(),district5.getName(),district6.getName()};
            mProvinceFragment.setCode(province.getCode());
//            mProvinceFragment.setName(province.getAreaName());
            mCityFragment.setCode(city.getCode());
            mDistrictFragment.setCode(district.getCode());
            mDistrictFragment4.setCode(district4.getCode());
            mDistrictFragment5.setCode(district5.getCode());
            mDistrictFragment6.setCode(district6.getCode());
            viewPager.setCurrentItem(5);
            pagerTab.setTabsText(addres);
            pagerTab.setCurrentPosition(5);
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
                        if (district6 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),district5.getName(),district6.getName()};
                        } else if (district5 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),district5.getName(),defutText};
                        }
                        else if (district4 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),defutText};
                        }else if(district != null) {
                            addres = new String[]{province.getName(), city.getName(), district.getName(),defutText};
                        }else if(city != null) {
                            addres = new String[]{province.getName(), city.getName(), defutText};
                        }else{
                            addres = new String[]{province.getName(), defutText};
                        }
                        break;
                    case 1:
                        if (district6 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),district5.getName(),district6.getName()};
                        } else if (district5 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),district5.getName(),defutText};
                        }
                        else if (district4 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),defutText};
                        }else if(district != null) {
                            addres = new String[]{province.getName(), city.getName(), district.getName(),defutText};
                        }else if(city != null) {
                            addres = new String[]{province.getName(), city.getName(), defutText};
                        }else{
                            addres = new String[]{province.getName(), defutText};
                        }
                        break;
                    case 2:
                        if (district6 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),district5.getName(),district6.getName()};
                        } else if (district5 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),district5.getName(),defutText};
                        }
                        else if (district4 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),defutText};
                        }else if(district != null) {
                            addres = new String[]{province.getName(), city.getName(), district.getName(),defutText};
                        }else if(city != null) {
                            addres = new String[]{province.getName(), city.getName(), defutText};
                        }else{
                            addres = new String[]{province.getName(), defutText};
                        }
                        break;
                    case 3:
                        if (district6 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),district5.getName(),district6.getName()};
                        } else if (district5 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),district5.getName(),defutText};
                        }
                        else if (district4 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),defutText};
                        }else if(district != null) {
                            addres = new String[]{province.getName(), city.getName(), district.getName(),defutText};
                        }else if(city != null) {
                            addres = new String[]{province.getName(), city.getName(), defutText};
                        }else{
                            addres = new String[]{province.getName(), defutText};
                        }
                        break;
                    case 4:
                        if (district6 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),district5.getName(),district6.getName()};
                        } else if (district5 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),district5.getName(),defutText};
                        }
                        else if (district4 != null) {
                            addres = new String[]{province.getName(), city.getName(),district.getName(),district4.getName(),defutText};
                        }else if(district != null) {
                            addres = new String[]{province.getName(), city.getName(), district.getName(),defutText};
                        }else if(city != null) {
                            addres = new String[]{province.getName(), city.getName(), defutText};
                        }else{
                            addres = new String[]{province.getName(), defutText};
                        }
                        break;
                    case 5:
                        addres = new String[]{province.getName(),city.getName(),district.getName(),district4.getName(),district5.getName(),district6.getName()};
                        break;
                }
                pagerTab.setTabsText(addres);
                pagerTab.setCurrentPosition(position);
            }
        });


        tv_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(province==null){
                    String myaddress="";
                    mSelectAddresFinish.finish("","","","","","","");
                }else if(city==null){
                    String myaddress=province.getName()+""+""+""+""+"";
                    mSelectAddresFinish.finish(province.getCode(),"","","","","",myaddress);
                }else if(district==null){
                    String myaddress=province.getName()+city.getName()+""+""+""+"";
                    mSelectAddresFinish.finish(province.getCode(),city.getCode(),"","","","",myaddress);
                }else if(district4==null){
                    String myaddress=province.getName()+city.getName()+district.getName()+""+""+"";
                    mSelectAddresFinish.finish(province.getCode(),city.getCode(),district.getCode(),"","","",myaddress);
                }else if(district5==null){
                    String myaddress=province.getName()+city.getName()+district.getName()+district4.getName()+""+"";
                    mSelectAddresFinish.finish(province.getCode(),city.getCode(),district.getCode(),district4.getCode(),"","",myaddress);
                }else if(district6==null){
                    String myaddress=province.getName()+city.getName()+district.getName()+district4.getName()+district5.getName()+"";
                    mSelectAddresFinish.finish(province.getCode(),city.getCode(),district.getCode(),district4.getCode(),district5.getCode(),"",myaddress);
                }else {
                    String myaddress=province.getName()+city.getName()+district.getName()+district4.getName()+district5.getName()+district6.getName();
                    mSelectAddresFinish.finish(province.getCode(),city.getCode(),district.getCode(),district4.getCode(),district5.getCode(),district6.getCode(),myaddress);
                }


                dismiss();
            }
        });

    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //KeyBoardUtils.closeKeybord((Activity) context);
        super.show(manager, tag);
    }

    @Override
    public void selectProvince(gdws_sys_area province) {
        String[] addres = new String[]{province.getName(),defutText};
        pagerTab.setTabsText(addres);
        pagerTab.setCurrentPosition(1);
        viewPager.setCurrentItem(1);
        if(province != this.province){
            city = null;
            district = null;
        }
        this.province = province;
        mCityFragment.setCode(province.getCode());
    }

    @Override
    public void selectCity(gdws_sys_area city) {
        String[] addres = new String[]{province.getName(),city.getName(),defutText};
        pagerTab.setTabsText(addres);
        pagerTab.setCurrentPosition(2);
        viewPager.setCurrentItem(2);
        if(city != this.city){
            district = null;
        }
        this.city = city;
        mDistrictFragment.setCode(city.getCode());
    }

    @Override
    public void selectDistrict(gdws_sys_area district) {
        String[] addres = new String[]{province.getName(),city.getName(),district.getName(),defutText};
        pagerTab.setTabsText(addres);
        pagerTab.setCurrentPosition(3);
        viewPager.setCurrentItem(3);
        if(district != this.district){

            district4 = null;
        }
        this.district = district;
        mDistrictFragment4.setCode(district.getCode());
    }

    @Override
    public void selectDistrict5(gdws_sys_area district5) {
        String[] addres = new String[]{province.getName(),city.getName(),district.getName(),district4.getName(),district5.getName(),defutText};
        pagerTab.setTabsText(addres);
        pagerTab.setCurrentPosition(5);
        viewPager.setCurrentItem(5);
        if(district5 != this.district5){
            district6 = null;
        }
        this.district5 = district5;
        mDistrictFragment6.setCode(district5.getCode());
    }


    @Override
    public void selectDistrict4(gdws_sys_area district4) {
        String[] addres = new String[]{province.getName(),city.getName(),district.getName(),district4.getName(),defutText};
        pagerTab.setTabsText(addres);
        pagerTab.setCurrentPosition(4);
        viewPager.setCurrentItem(4);
        if(district4 != this.district4){
            district5 = null;
        }
        this.district4 = district4;
        mDistrictFragment5.setCode(district4.getCode());
    }

    @Override
    public void selectDistrict6(gdws_sys_area district6) {


        String[] addres = new String[]{province.getName(),city.getName(),district.getName(),district4.getName(),district5.getName(),district6.getName()};
        String myaddress=province.getName()+city.getName()+district.getName()+district4.getName()+district5.getName()+district6.getName();
        pagerTab.setTabsText(addres);
        this.district6 = district6;
        mSelectAddresFinish.finish(province.getCode(),city.getCode(),district.getCode(),district4.getCode(),district5.getCode(),district6.getCode(),myaddress);
        dismiss();
    }

}
