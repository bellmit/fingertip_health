package com.jqsoft.fingertip_health.di.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.callback.AddressCallBackTwo;
import com.jqsoft.fingertip_health.di.ui.activity.ReceptionListActivity;
import com.jqsoft.fingertip_health.util.StringUtils;
import com.jqsoft.fingertip_health.util.Util;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class CityFragmentTwo implements AdapterView.OnItemClickListener{
    private AddressCallBackTwo callBack;
    private String code;
    private Context context;
    private AddressAdapter adapter;
    private ListView listview;
    private String provinceCode ;

    private   List<SRCLoginAreaBean>   allcityList ;
    List<SRCLoginAreaBean> cityList = new ArrayList<>();
    public CityFragmentTwo(Context context, AddressCallBackTwo callBack){
        this.context = context;
        this.callBack = callBack;
        initView();
    }
    public void setCode(String provinceCode){

//        List<gdws_sys_area>   list = DataSupport.where(" areaCode=? ", Identity.srcInfo.getAreaId() ).find(gdws_sys_area.class);
//        if (list.get(0).getAreaLevel().equals("area_2")){
//            allcityList = DataSupport.where(" areaLevel=? ","area_4" ).find(gdws_sys_area.class);
//
//        }else {
            allcityList = DataSupport.where(" areaLevel=? and state=?","area_4","0" ).order("areacode asc").find(SRCLoginAreaBean.class);
//        }

        if (!provinceCode.equals(this.provinceCode)){
            this.code = null;
        }
        if (StringUtils.isNoEmpty(code)){
            this.code = code;
        }
        this.provinceCode = provinceCode;

        for (int i=0;i<allcityList.size();i++){
            if (allcityList.get(i).getAreaPid().equals(provinceCode)){
                cityList.add(allcityList.get(i));
            }



        }
        adapter.notifyDataSetChanged();
    }
    public ListView getListview() {
        return listview;
    }

    public View initView() {
        listview = (ListView) LayoutInflater.from(context).inflate(R.layout.select_address_pop_listview,null);
        //cityList = AddressManager.newInstance().findProvinceByCode(provinceCode).getAllCities();
        adapter = new AddressAdapter(cityList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
        return listview;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        code = cityList.get(i).getAreaPid();
        if (callBack != null){

            callBack.selectCity(cityList.get(i));


            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.RECEPTION_ITEM_ACTIVITY_KEY,  cityList.get(i));

            Util.gotoActivityWithBundle(context, ReceptionListActivity.class, bundle);

           /* List<gdws_sys_area>   list = DataSupport.where(" areaCode=? ", Identity.srcInfo.getAreaId() ).find(gdws_sys_area.class);
            if (list.get(0).getAreaLevel().equals("area_2")){

                callBack.selectCity(cityList.get(i));


                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.RECEPTION_ITEM_ACTIVITY_KEY,  cityList.get(i));

                Util.gotoActivityWithBundle(context, ReceptionListActivity.class, bundle);
            }else {
               *//* callBack.selectCity(cityList.get(i));
                code = cityList.get(i).getAreaCode();*//*
            }*/
        }
       // adapter.notifyDataSetChanged();
    }

    class AddressAdapter extends BaseAdapter {

        private  List<SRCLoginAreaBean> list;

        public AddressAdapter( List<SRCLoginAreaBean> list){
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(context).inflate(R.layout.address_listiew_item_textview, null);
            TextView text = (TextView) view.findViewById(R.id.tvTextName);
            ImageView ivSelect = (ImageView) view.findViewById(R.id.ivSelect);
            text.setText(list.get(i).getAreaName());
            if (list.get(i).getAreaCode().equals(code)) {
                text.setTextColor(context.getResources().getColor(R.color.new_redb));
                ivSelect.setVisibility(View.VISIBLE);
            }
            return view;
        }
    }
}
