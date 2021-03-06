package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
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
 * εΊειζ©
 * Created by Administrator on 2018-01-25.
 */

public class AreaSelectActivity extends AbstractActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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

    String[] areaLevelArray = new String[]{Constants.AREA_LEVEL_PROVINCE,
            Constants.AREA_LEVEL_CITY,
            Constants.AREA_LEVEL_COUNTY,
            Constants.AREA_LEVEL_STREET,
            Constants.AREA_LEVEL_VILLAGE,
            Constants.AREA_LEVEL_ZU};

    OptionsPickerView provincePicker, cityPicker, countyPicker, streetPicker, villagePicker;
    String provinceCode=Constants.EMPTY_STRING, cityCode=Constants.EMPTY_STRING,
            countyCode=Constants.EMPTY_STRING, streetCode=Constants.EMPTY_STRING,
            villageCode=Constants.EMPTY_STRING;

  @Override
    protected int getLayoutId() {
        return R.layout.activity_area_select_layout;
    }

    @Override
    protected void initData() {
        initAreaLevelSelectListener();

        provinceCode=getDeliveredStringByKey(Constants.SELECTED_PROVINCE_AREA_CODE_KEY);
        cityCode=getDeliveredStringByKey(Constants.SELECTED_CITY_AREA_CODE_KEY);
        countyCode=getDeliveredStringByKey(Constants.SELECTED_COUNTY_AREA_CODE_KEY);
        streetCode=getDeliveredStringByKey(Constants.SELECTED_STREET_AREA_CODE_KEY);
        villageCode=getDeliveredStringByKey(Constants.SELECTED_VILLAGE_AREA_CODE_KEY);

        initLoginAreaLevelAndCode();

        if (StringUtils.isBlank(provinceCode)){
            provinceCode=DataQueryUtil.getAreaCodeFromAreaLevel(Constants.AREA_LEVEL_PROVINCE);
        }

        if (StringUtils.isBlank(cityCode)){
            cityCode=DataQueryUtil.getAreaCodeFromAreaLevel(Constants.AREA_LEVEL_CITY);
        }

        if (StringUtils.isBlank(countyCode)){
            countyCode=DataQueryUtil.getAreaCodeFromAreaLevel(Constants.AREA_LEVEL_COUNTY);
        }

        if (StringUtils.isBlank(streetCode)){
            streetCode=DataQueryUtil.getAreaCodeFromAreaLevel(Constants.AREA_LEVEL_STREET);
        }

        if (StringUtils.isBlank(villageCode)){
            villageCode=DataQueryUtil.getAreaCodeFromAreaLevel(Constants.AREA_LEVEL_VILLAGE);
        }
    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);

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

    private void initLoginAreaLevelAndCode(){
        String loginAreaId = IdentityManager.getAreaId(this);
        String loginAreaLevel = getLoginAreaLevel();
        int index = getAreaLevelIndex(loginAreaLevel);
        try {
            while (index>-1){
                String currentLevel = areaLevelArray[index];
                SRCLoginAreaBean bean = DataQueryUtil.getSelectedAreaBeanFromSelectedAreaCodeAndAreaLevel(loginAreaId, currentLevel);
                setLevelCode(currentLevel, bean);
                loginAreaId=bean.getAreaPid();
                --index;
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
        String loginAreaId = IdentityManager.getAreaId(this);
        if (StringUtils.isBlank(loginAreaId)){
            return Constants.EMPTY_STRING;
        } else {
            String areaLevel = DataQueryUtil.getAreaLevelFromAreaCode(loginAreaId);
            return areaLevel;
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
                      List<SRCLoginAreaBean> provinceList = DataQueryUtil.getAreaListFromAreaLevelAndNullableAreaPid(Constants.AREA_LEVEL_PROVINCE, Constants.EMPTY_STRING);
                      initProvincePicker(tvProvince, provinceList, "η(η΄θΎεΈ)");
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
                      List<SRCLoginAreaBean> cityList = DataQueryUtil.getAreaListFromAreaLevelAndNonNullableAreaPid(Constants.AREA_LEVEL_CITY, provinceCode);
                      initCityPicker(tvCity, cityList, "εΈ");
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
                      List<SRCLoginAreaBean> countyList = DataQueryUtil.getAreaListFromAreaLevelAndNonNullableAreaPid(Constants.AREA_LEVEL_COUNTY, cityCode);
                      initCountyPicker(tvCounty, countyList, "εΏ(εΊ)");
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
                      List<SRCLoginAreaBean> streetList = DataQueryUtil.getAreaListFromAreaLevelAndNonNullableAreaPid(Constants.AREA_LEVEL_STREET, countyCode);
                      initStreetPicker(tvStreet, streetList, "θ‘ι(δΉ‘ι)");
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
                      List<SRCLoginAreaBean> villageList = DataQueryUtil.getAreaListFromAreaLevelAndNonNullableAreaPid(Constants.AREA_LEVEL_VILLAGE, streetCode);
                      initVillagePicker(tvVillage, villageList, "η€ΎεΊ(ζ)");
                      villagePicker.show();
                  }
              });
    }

    private void initProvincePicker(final TextView textView, final List<SRCLoginAreaBean> areaList, String title) {//ζ‘δ»Άιζ©ε¨εε§ε

        provincePicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (ListUtils.getSize(areaList)<=options1){
                    return;
                }
                //θΏεηεε«ζ―δΈδΈͺηΊ§ε«ηιδΈ­δ½η½?
                String tx = areaList.get(options1).getAreaName();
                textView.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                provinceCode=areaList.get(options1).getAreaCode();
                clearCitySelection();
            }
        })
                .setTitleText(title)
                .setContentTextSize(20)//θ?Ύη½?ζ»θ½?ζε­ε€§ε°
                .setDividerColor(Color.rgb(255, 177, 177))//θ?Ύη½?εε²ηΊΏηι’θ²
                .setSelectOptions(0, 1)//ι»θ?€ιδΈ­ι‘Ή
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //ζ―ε¦εͺζΎη€ΊδΈ­ι΄ιδΈ­ι‘Ήηlabelζε­οΌfalseεζ―ι‘Ήitemε¨ι¨ι½εΈ¦ζlabelγ
                .setLabels("", "", "")
                .build();
        provincePicker.setPicker(areaList);//δΈηΊ§ιζ©ε¨*/
        int selectedPos  = DataQueryUtil.getSelectedPositionFromSelectedAreaCodeAndBeanList(provinceCode, areaList);
        provincePicker.setSelectOptions(selectedPos);
    }
    private void initCityPicker(final TextView textView, final List<SRCLoginAreaBean> areaList, String title) {//ζ‘δ»Άιζ©ε¨εε§ε

        cityPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (ListUtils.getSize(areaList)<=options1){
                    return;
                }
                //θΏεηεε«ζ―δΈδΈͺηΊ§ε«ηιδΈ­δ½η½?
                String tx = areaList.get(options1).getAreaName();
                textView.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                cityCode=areaList.get(options1).getAreaCode();
                clearCountySelection();
            }
        })
                .setTitleText(title)
                .setContentTextSize(20)//θ?Ύη½?ζ»θ½?ζε­ε€§ε°
                .setDividerColor(Color.rgb(255, 177, 177))//θ?Ύη½?εε²ηΊΏηι’θ²
                .setSelectOptions(0, 1)//ι»θ?€ιδΈ­ι‘Ή
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //ζ―ε¦εͺζΎη€ΊδΈ­ι΄ιδΈ­ι‘Ήηlabelζε­οΌfalseεζ―ι‘Ήitemε¨ι¨ι½εΈ¦ζlabelγ
                .setLabels("", "", "")
                .build();
        cityPicker.setPicker(areaList);//δΈηΊ§ιζ©ε¨*/
        int selectedPos  = DataQueryUtil.getSelectedPositionFromSelectedAreaCodeAndBeanList(cityCode, areaList);
        cityPicker.setSelectOptions(selectedPos);
    }
    private void initCountyPicker(final TextView textView, final List<SRCLoginAreaBean> areaList, String title) {//ζ‘δ»Άιζ©ε¨εε§ε

        countyPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (ListUtils.getSize(areaList)<=options1){
                    return;
                }
                //θΏεηεε«ζ―δΈδΈͺηΊ§ε«ηιδΈ­δ½η½?
                String tx = areaList.get(options1).getAreaName();
                textView.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                countyCode=areaList.get(options1).getAreaCode();
                clearStreetSelection();
            }
        })
                .setTitleText(title)
                .setContentTextSize(20)//θ?Ύη½?ζ»θ½?ζε­ε€§ε°
                .setDividerColor(Color.rgb(255, 177, 177))//θ?Ύη½?εε²ηΊΏηι’θ²
                .setSelectOptions(0, 1)//ι»θ?€ιδΈ­ι‘Ή
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //ζ―ε¦εͺζΎη€ΊδΈ­ι΄ιδΈ­ι‘Ήηlabelζε­οΌfalseεζ―ι‘Ήitemε¨ι¨ι½εΈ¦ζlabelγ
                .setLabels("", "", "")
                .build();
        countyPicker.setPicker(areaList);//δΈηΊ§ιζ©ε¨*/
        int selectedPos  = DataQueryUtil.getSelectedPositionFromSelectedAreaCodeAndBeanList(countyCode, areaList);
        countyPicker.setSelectOptions(selectedPos);
    }
    private void initStreetPicker(final TextView textView, final List<SRCLoginAreaBean> areaList, String title) {//ζ‘δ»Άιζ©ε¨εε§ε

        streetPicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (ListUtils.getSize(areaList)<=options1){
                    return;
                }
                //θΏεηεε«ζ―δΈδΈͺηΊ§ε«ηιδΈ­δ½η½?
                String tx = areaList.get(options1).getAreaName();
                textView.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                streetCode=areaList.get(options1).getAreaCode();
                clearVillageSelection();
            }
        })
                .setTitleText(title)
                .setContentTextSize(20)//θ?Ύη½?ζ»θ½?ζε­ε€§ε°
                .setDividerColor(Color.rgb(255, 177, 177))//θ?Ύη½?εε²ηΊΏηι’θ²
                .setSelectOptions(0, 1)//ι»θ?€ιδΈ­ι‘Ή
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //ζ―ε¦εͺζΎη€ΊδΈ­ι΄ιδΈ­ι‘Ήηlabelζε­οΌfalseεζ―ι‘Ήitemε¨ι¨ι½εΈ¦ζlabelγ
                .setLabels("", "", "")
                .build();
        streetPicker.setPicker(areaList);//δΈηΊ§ιζ©ε¨*/
        int selectedPos  = DataQueryUtil.getSelectedPositionFromSelectedAreaCodeAndBeanList(streetCode, areaList);
        streetPicker.setSelectOptions(selectedPos);
    }
    private void initVillagePicker(final TextView textView, final List<SRCLoginAreaBean> areaList, String title) {//ζ‘δ»Άιζ©ε¨εε§ε

        villagePicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (ListUtils.getSize(areaList)<=options1){
                    return;
                }
                //θΏεηεε«ζ―δΈδΈͺηΊ§ε«ηιδΈ­δ½η½?
                String tx = areaList.get(options1).getAreaName();
                textView.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                villageCode=areaList.get(options1).getAreaCode();
            }
        })
                .setTitleText(title)
                .setContentTextSize(20)//θ?Ύη½?ζ»θ½?ζε­ε€§ε°
                .setDividerColor(Color.rgb(255, 177, 177))//θ?Ύη½?εε²ηΊΏηι’θ²
                .setSelectOptions(0, 1)//ι»θ?€ιδΈ­ι‘Ή
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //ζ―ε¦εͺζΎη€ΊδΈ­ι΄ιδΈ­ι‘Ήηlabelζε­οΌfalseεζ―ι‘Ήitemε¨ι¨ι½εΈ¦ζlabelγ
                .setLabels("", "", "")
                .build();
        villagePicker.setPicker(areaList);//δΈηΊ§ιζ©ε¨*/
        int selectedPos  = DataQueryUtil.getSelectedPositionFromSelectedAreaCodeAndBeanList(villageCode, areaList);
        villagePicker.setSelectOptions(selectedPos);
    }


    private void clearProvinceSelection(){
        tvProvince.setText("θ―·ιζ©η(η΄θΎεΈ)");
        provinceCode=Constants.EMPTY_STRING;
        clearCitySelection();
    }

    private void clearCitySelection(){
        tvCity.setText("θ―·ιζ©εΈ");
        cityCode=Constants.EMPTY_STRING;
        clearCountySelection();
    }

    private void clearCountySelection(){
        tvCounty.setText("θ―·ιζ©εΊ(εΏ)");
        countyCode=Constants.EMPTY_STRING;
        clearStreetSelection();
    }

    private void clearStreetSelection(){
        tvStreet.setText("θ―·ιζ©θ‘ι(δΉ‘ι)");
        streetCode=Constants.EMPTY_STRING;
        clearVillageSelection();
    }

    private void clearVillageSelection(){
        tvVillage.setText("θ―·ιζ©η€ΎεΊ(ζ)");
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Util.showToast(AreaSelectActivity.this, "ζ¨ζͺιζ©δ»»δ½εΊε");
                    }

                    @Override
                    public void onNext(String s) {
                        Intent intent = new Intent();
                        intent.putExtra(Constants.SELECTED_AREA_CODE_KEY, s);
                        intent.putExtra(Constants.SELECTED_PROVINCE_AREA_CODE_KEY, provinceCode);
                        intent.putExtra(Constants.SELECTED_CITY_AREA_CODE_KEY, cityCode);
                        intent.putExtra(Constants.SELECTED_COUNTY_AREA_CODE_KEY, countyCode);
                        intent.putExtra(Constants.SELECTED_STREET_AREA_CODE_KEY, streetCode);
                        intent.putExtra(Constants.SELECTED_VILLAGE_AREA_CODE_KEY, villageCode);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
