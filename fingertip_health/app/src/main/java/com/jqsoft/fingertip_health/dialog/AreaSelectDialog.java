package com.jqsoft.fingertip_health.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.dialog.base.BaseDialog;
import com.jqsoft.fingertip_health.util.DataQueryUtil;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 区划选择对话框
 * Created by Administrator on 2018-03-07.
 */

public class AreaSelectDialog extends BaseDialog {
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_county)
    TextView tvCounty;
    @BindView(R.id.tv_street)
    TextView tvStreet;
    @BindView(R.id.tv_village)
    TextView tvVillage;
    @BindView(R.id.bt_search)
    AppCompatButton btSearch;

    AreaSelectCallback callback;

    String[] codeArray;

    String[] areaLevelArray = new String[]{Constants.AREA_LEVEL_PROVINCE,
            Constants.AREA_LEVEL_CITY,
            Constants.AREA_LEVEL_COUNTY,
            Constants.AREA_LEVEL_STREET,
            Constants.AREA_LEVEL_VILLAGE};

    OptionsPickerView provincePicker, cityPicker, countyPicker, streetPicker, villagePicker;
    String provinceCode=Constants.EMPTY_STRING, cityCode=Constants.EMPTY_STRING,
            countyCode=Constants.EMPTY_STRING, streetCode=Constants.EMPTY_STRING,
            villageCode=Constants.EMPTY_STRING;

    public AreaSelectDialog(@NonNull Context context, String[] codeArray) {
        super(context, R.style.white_background_dialog, R.layout.dialog_area_select_layout);
        if (codeArray!=null && codeArray.length>=5){
            this.codeArray=codeArray;
        }
    }


    @Override
    public void initData() {
        initAreaLevelSelectListener();

        if (codeArray!=null) {
            provinceCode=codeArray[0];
            cityCode=codeArray[1];
            countyCode=codeArray[2];
            streetCode=codeArray[3];
            villageCode=codeArray[4];
        }

        initLoginAreaLevelAndCode();

//        if (StringUtils.isBlank(provinceCode)){
//            provinceCode= DataQueryUtil.getAreaCodeFromAreaLevel(Constants.AREA_LEVEL_PROVINCE);
//        }
//
//        if (StringUtils.isBlank(cityCode)){
//            cityCode=DataQueryUtil.getAreaCodeFromAreaLevel(Constants.AREA_LEVEL_CITY);
//        }
//
//        if (StringUtils.isBlank(countyCode)){
//            countyCode=DataQueryUtil.getAreaCodeFromAreaLevel(Constants.AREA_LEVEL_COUNTY);
//        }
//
//        if (StringUtils.isBlank(streetCode)){
//            streetCode=DataQueryUtil.getAreaCodeFromAreaLevel(Constants.AREA_LEVEL_STREET);
//        }
//
//        if (StringUtils.isBlank(villageCode)){
//            villageCode=DataQueryUtil.getAreaCodeFromAreaLevel(Constants.AREA_LEVEL_VILLAGE);
//        }

    }

    @Override
    public void initView() {
        String initialProvince = DataQueryUtil.getSelectedAreaNameFromSelectedAreaCodeAndAreaLevel(provinceCode, Constants.AREA_LEVEL_PROVINCE);
        tvProvince.setText(initialProvince);
        String initialCity = DataQueryUtil.getSelectedAreaNameFromSelectedAreaCodeAndAreaLevel(cityCode, Constants.AREA_LEVEL_CITY);
        tvCity.setText(initialCity);
        String initialCounty = DataQueryUtil.getSelectedAreaNameFromSelectedAreaCodeAndAreaLevel(countyCode, Constants.AREA_LEVEL_COUNTY);
        tvCounty.setText(initialCounty);
        String initialStreet = DataQueryUtil.getSelectedAreaNameFromSelectedAreaCodeAndAreaLevel(streetCode, Constants.AREA_LEVEL_STREET);
        tvStreet.setText(initialStreet);
        String initialVillage = DataQueryUtil.getSelectedAreaNameFromSelectedAreaCodeAndAreaLevel(villageCode, Constants.AREA_LEVEL_VILLAGE);
        tvVillage.setText(initialVillage);

        initSearchButtonListener();

    }

//    private void initLoginAreaLevelAndCode(){
//        String loginAreaId = IdentityManager.getAreaId(context);
//        String loginAreaLevel = IdentityManager.getAreaLevel(context);
////        String loginAreaLevel = getLoginAreaLevel();
//        int index = getAreaLevelIndex(loginAreaLevel);
//        try {
//            while (index>-1){
//                String currentLevel = areaLevelArray[index];
//                gdws_sys_area bean = DataQueryUtil.getSelectedAreaBeanFromSelectedAreaCodeAndAreaLevel(loginAreaId, currentLevel);
//                setLevelCode(currentLevel, bean);
//                loginAreaId=bean.getAreaPid();
//                --index;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void initLoginAreaLevelAndCode(){
        String loginAreaId = IdentityManager.getAreaId(context);
        String loginAreaLevel = IdentityManager.getAreaLevel(context);
//        String loginAreaLevel = getLoginAreaLevel();
        List<SRCLoginAreaBean> list = DaggerApplication.getInstance().getAreaBeanList();
        int areaBeanIndex = 0;
        int index = getAreaLevelIndex(loginAreaLevel);
        try {
            while (index>-1){
                String currentLevel = areaLevelArray[index];
                int i = list.size()-1-areaBeanIndex;
                SRCLoginAreaBean bean =list.get(i) ;
//                gdws_sys_area bean = DataQueryUtil.getSelectedAreaBeanFromSelectedAreaCodeAndAreaLevel(loginAreaId, currentLevel);
                setLevelCode(currentLevel, bean);
                loginAreaId=bean.getAreaPid();
                --index;
                ++areaBeanIndex;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLevelCode(String areaLevel, SRCLoginAreaBean bean){
        if (StringUtils.isBlank(areaLevel) || bean==null){

        } else {
            String areaCode = bean.getAreaCode();
            if (Constants.AREA_LEVEL_PROVINCE.equals(areaLevel)){
                if (StringUtils.isBlank(provinceCode)) {
                    provinceCode=areaCode;
                }
                tvProvince.setEnabled(false);
            } else if (Constants.AREA_LEVEL_CITY.equals(areaLevel)){
                if (StringUtils.isBlank(cityCode)) {
                    cityCode=areaCode;
                }
                tvCity.setEnabled(false);
            } else if (Constants.AREA_LEVEL_COUNTY.equals(areaLevel)){
                if (StringUtils.isBlank(countyCode)) {
                    countyCode=areaCode;
                }
                tvCounty.setEnabled(false);
            } else if (Constants.AREA_LEVEL_STREET.equals(areaLevel)){
                if (StringUtils.isBlank(streetCode)) {
                    streetCode=areaCode;
                }
                tvStreet.setEnabled(false);
            } else if (Constants.AREA_LEVEL_VILLAGE.equals(areaLevel)){
                if (StringUtils.isBlank(villageCode)) {
                    villageCode=areaCode;
                }
                tvVillage.setEnabled(false);
            }
        }
    }

    private int getAreaLevelIndex(String areaLevel){
        areaLevel=Util.trimString(areaLevel);
        int index = -1;
        for (int i = 0; i < areaLevelArray.length; ++i){
            String level = areaLevelArray[i];
            if (areaLevel.equals(level)){
                index=i;
                break;
            }
        }
        return index;
    }

    private String getLoginAreaLevel(){
        String loginAreaId = IdentityManager.getAreaId(context);
        if (StringUtils.isBlank(loginAreaId)){
            return Constants.EMPTY_STRING;
        } else {
            String areaLevel = DataQueryUtil.getAreaLevelFromAreaCode(loginAreaId);
            return areaLevel;
        }
    }

    private void showDialog(){
        show();
    }

    private void hideDialog(){
        hide();
    }

    private void dismissDialog(){
        dismiss();
    }

    private void prependAllAreaBean(List<SRCLoginAreaBean> list, String currentLevel){
        if (list!=null && list.size() > 0){
            String currentAreaCode = list.get(0).getAreaCode();
            SRCLoginAreaBean all = new SRCLoginAreaBean();
            SRCLoginAreaBean current = DataQueryUtil.getSelectedAreaBeanFromSelectedAreaCodeAndAreaLevel(currentAreaCode, currentLevel);
            all.setAreaCode(Constants.AREA_CODE_ALL);
            all.setAreaName("全部");
            all.setAreaLevel(currentLevel);
            all.setAreaPid(current.getAreaPid());
            list.add(0, all);
        }
    }
    private void initAreaLevelSelectListener(){
        RxView.clicks(tvProvince)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        hideDialog();

                        List<SRCLoginAreaBean> provinceList = DataQueryUtil.getAreaListFromAreaLevelAndNullableAreaPid(Constants.AREA_LEVEL_PROVINCE, Constants.EMPTY_STRING);
                        prependAllAreaBean(provinceList, Constants.AREA_LEVEL_PROVINCE);
                        initProvincePicker(tvProvince, provinceList, "省(直辖市)");
                        provincePicker.show();
                    }
                });

        RxView.clicks(tvCity)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        hideDialog();

                        List<SRCLoginAreaBean> cityList = DataQueryUtil.getAreaListFromAreaLevelAndNonNullableAreaPid(Constants.AREA_LEVEL_CITY, provinceCode);
                        prependAllAreaBean(cityList, Constants.AREA_LEVEL_CITY);
                        initCityPicker(tvCity, cityList, "市");
                        cityPicker.show();
                    }
                });

        RxView.clicks(tvCounty)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        hideDialog();

                        List<SRCLoginAreaBean> countyList = DataQueryUtil.getAreaListFromAreaLevelAndNonNullableAreaPid(Constants.AREA_LEVEL_COUNTY, cityCode);
                        prependAllAreaBean(countyList, Constants.AREA_LEVEL_COUNTY);
                        initCountyPicker(tvCounty, countyList, "县(区)");
                        countyPicker.show();
                    }
                });

        RxView.clicks(tvStreet)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        hideDialog();

                        List<SRCLoginAreaBean> streetList = DataQueryUtil.getAreaListFromAreaLevelAndNonNullableAreaPid(Constants.AREA_LEVEL_STREET, countyCode);
                        prependAllAreaBean(streetList, Constants.AREA_LEVEL_STREET);
                        initStreetPicker(tvStreet, streetList, "街道(乡镇)");
                        streetPicker.show();
                    }
                });
        RxView.clicks(tvVillage)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        hideDialog();

                        List<SRCLoginAreaBean> villageList = DataQueryUtil.getAreaListFromAreaLevelAndNonNullableAreaPid(Constants.AREA_LEVEL_VILLAGE, streetCode);
                        prependAllAreaBean(villageList, Constants.AREA_LEVEL_VILLAGE);
                        initVillagePicker(tvVillage, villageList, "社区(村)");
                        villagePicker.show();
                    }
                });
    }

    private void initProvincePicker(final TextView textView, final List<SRCLoginAreaBean> areaList, String title) {//条件选择器初始化

        provincePicker = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (ListUtils.getSize(areaList)<=options1){
                    return;
                }
                //返回的分别是三个级别的选中位置
                String tx = areaList.get(options1).getAreaName();
                textView.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                provinceCode=areaList.get(options1).getAreaCode();
                clearCitySelection();
            }
        })
                .setTitleText(title)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .build();
        provincePicker.setPicker(areaList);//一级选择器*/
        int selectedPos  = DataQueryUtil.getSelectedPositionFromSelectedAreaCodeAndBeanList(provinceCode, areaList);
        provincePicker.setSelectOptions(selectedPos);

        provincePicker.setOnDismissListener(new com.bigkoo.pickerview.listener.OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                showDialog();
            }
        });

    }
    private void initCityPicker(final TextView textView, final List<SRCLoginAreaBean> areaList, String title) {//条件选择器初始化

        cityPicker = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (ListUtils.getSize(areaList)<=options1){
                    return;
                }
                //返回的分别是三个级别的选中位置
                String tx = areaList.get(options1).getAreaName();
                textView.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                cityCode=areaList.get(options1).getAreaCode();
                clearCountySelection();
            }
        })
                .setTitleText(title)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .build();
        cityPicker.setPicker(areaList);//一级选择器*/
        int selectedPos  = DataQueryUtil.getSelectedPositionFromSelectedAreaCodeAndBeanList(cityCode, areaList);
        cityPicker.setSelectOptions(selectedPos);

        cityPicker.setOnDismissListener(new com.bigkoo.pickerview.listener.OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                showDialog();
            }
        });

    }
    private void initCountyPicker(final TextView textView, final List<SRCLoginAreaBean> areaList, String title) {//条件选择器初始化

        countyPicker = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (ListUtils.getSize(areaList)<=options1){
                    return;
                }
                //返回的分别是三个级别的选中位置
                String tx = areaList.get(options1).getAreaName();
                textView.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                countyCode=areaList.get(options1).getAreaCode();
                clearStreetSelection();
            }
        })
                .setTitleText(title)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .build();
        countyPicker.setPicker(areaList);//一级选择器*/
        int selectedPos  = DataQueryUtil.getSelectedPositionFromSelectedAreaCodeAndBeanList(countyCode, areaList);
        countyPicker.setSelectOptions(selectedPos);

        countyPicker.setOnDismissListener(new com.bigkoo.pickerview.listener.OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                showDialog();
            }
        });

    }
    private void initStreetPicker(final TextView textView, final List<SRCLoginAreaBean> areaList, String title) {//条件选择器初始化

        streetPicker = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (ListUtils.getSize(areaList)<=options1){
                    return;
                }
                //返回的分别是三个级别的选中位置
                String tx = areaList.get(options1).getAreaName();
                textView.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                streetCode=areaList.get(options1).getAreaCode();
                clearVillageSelection();
            }
        })
                .setTitleText(title)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .build();
        streetPicker.setPicker(areaList);//一级选择器*/
        int selectedPos  = DataQueryUtil.getSelectedPositionFromSelectedAreaCodeAndBeanList(streetCode, areaList);
        streetPicker.setSelectOptions(selectedPos);

        streetPicker.setOnDismissListener(new com.bigkoo.pickerview.listener.OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                showDialog();
            }
        });

    }
    private void initVillagePicker(final TextView textView, final List<SRCLoginAreaBean> areaList, String title) {//条件选择器初始化

        villagePicker = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (ListUtils.getSize(areaList)<=options1){
                    return;
                }
                //返回的分别是三个级别的选中位置
                String tx = areaList.get(options1).getAreaName();
                textView.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                villageCode=areaList.get(options1).getAreaCode();
            }
        })
                .setTitleText(title)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .build();
        villagePicker.setPicker(areaList);//一级选择器*/
        int selectedPos  = DataQueryUtil.getSelectedPositionFromSelectedAreaCodeAndBeanList(villageCode, areaList);
        villagePicker.setSelectOptions(selectedPos);

        villagePicker.setOnDismissListener(new com.bigkoo.pickerview.listener.OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                showDialog();
            }
        });
    }


    private void clearProvinceSelection(){
        tvProvince.setText("请选择省(直辖市)");
        provinceCode=Constants.EMPTY_STRING;
        clearCitySelection();
    }

    private void clearCitySelection(){
        tvCity.setText("请选择市");
        cityCode=Constants.EMPTY_STRING;
        clearCountySelection();
    }

    private void clearCountySelection(){
        tvCounty.setText("请选择区(县)");
        countyCode=Constants.EMPTY_STRING;
        clearStreetSelection();
    }

    private void clearStreetSelection(){
        tvStreet.setText("请选择街道(乡镇)");
        streetCode=Constants.EMPTY_STRING;
        clearVillageSelection();
    }

    private void clearVillageSelection(){
        tvVillage.setText("请选择社区(村)");
        villageCode=Constants.EMPTY_STRING;
    }

    private void initSearchButtonListener(){
        RxView.clicks(btSearch)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        getSelectedResult();
                    }
                });
    }

    private void getSelectedResult(){
        Observable.just(villageCode, streetCode, countyCode, cityCode, provinceCode)
                .first(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !StringUtils.isBlank(s);
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        String result = s;
                        if (Constants.AREA_CODE_ALL.equals(villageCode)){
                            result = streetCode;
                        } else if (Constants.AREA_CODE_ALL.equals(streetCode)){
                            result = countyCode;
                        } else if (Constants.AREA_CODE_ALL.equals(countyCode)){
                            result=cityCode;
                        } else if (Constants.AREA_CODE_ALL.equals(cityCode)){
                            result=provinceCode;
                        }
                        return result;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Util.showToast(context, "您未选择任何区划");
                    }

                    @Override
                    public void onNext(String s) {
                        dismissDialog();
                        if (callback!=null){
                            String[] array = new String[]{provinceCode, cityCode, countyCode, streetCode, villageCode};
                            callback.areaDidSelect(s, array);
                        }
//                        Intent intent = new Intent();
//                        intent.putExtra(Constants.SELECTED_AREA_CODE_KEY, s);
//                        intent.putExtra(Constants.SELECTED_PROVINCE_AREA_CODE_KEY, provinceCode);
//                        intent.putExtra(Constants.SELECTED_CITY_AREA_CODE_KEY, cityCode);
//                        intent.putExtra(Constants.SELECTED_COUNTY_AREA_CODE_KEY, countyCode);
//                        intent.putExtra(Constants.SELECTED_STREET_AREA_CODE_KEY, streetCode);
//                        intent.putExtra(Constants.SELECTED_VILLAGE_AREA_CODE_KEY, villageCode);
//                        setResult(RESULT_OK, intent);
//                        finish();
                    }
                });
    }

    public void setCallback(AreaSelectCallback callback) {
        this.callback = callback;
    }

    public interface AreaSelectCallback {
        void areaDidSelect(String selectedAreaCode, String[] codeArray);
    }
}
