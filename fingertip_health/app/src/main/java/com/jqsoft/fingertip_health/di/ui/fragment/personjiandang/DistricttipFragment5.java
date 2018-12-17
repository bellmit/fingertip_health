package com.jqsoft.fingertip_health.di.ui.fragment.personjiandang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.fingertip.gdws_sys_area;
import com.jqsoft.fingertip_health.callback.AddresstipCallBack;
import com.jqsoft.fingertip_health.util.StringUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class DistricttipFragment5 implements AdapterView.OnItemClickListener{
    private AddresstipCallBack callBack;
    private String code ;
    private Context context;
    private AddressAdapter adapter;
    private  ListView listview;
    private String provinceCode ;
    private String cityCode;
    private List<gdws_sys_area> districtsList = new ArrayList<gdws_sys_area>();
    private List<gdws_sys_area> alldistrictsList = DataSupport.where("area_level=? ","5" ).order("code asc").find(gdws_sys_area.class);


    public DistricttipFragment5(Context context, AddresstipCallBack callBack){
        this.context = context;
        this.callBack = callBack;
        initView();
    }
    public void setCode(String cityCode){
//        if ( !cityCode.equals(this.cityCode)){
//            this.code = null;
//        }
        if (StringUtils.isNoEmpty(code)){
            this.code = code;
        }
        this.cityCode = cityCode;
//        this.provinceCode = provinceCode;
//        districtsList.clear();
//        districtsList.addAll(AddressManager.newInstance().findProvinceByCode(provinceCode)
//                .findCityByCode(cityCode).getAllDistricts());
        districtsList.clear();
        for (int i=0;i<alldistrictsList.size();i++){
            if(alldistrictsList.get(i).getP_code().equals(cityCode)){
                districtsList.add(alldistrictsList.get(i));


            }
        }


        adapter.notifyDataSetChanged();
    }

    public ListView getListview() {
        return listview;
    }

    public View initView() {
        listview = (ListView) LayoutInflater.from(context).inflate(R.layout.select_address_pop_listview,null);
        //districtsList = AddressManager.newInstance().findProvinceByCode(provinceCode).findCityByCode(cityCode).getAllDistricts();
        adapter = new AddressAdapter(districtsList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
        return listview;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        code = districtsList.get(i).getCode();
        if (callBack != null){
            callBack.selectDistrict5(districtsList.get(i));


//            Bundle bundle = new Bundle();
//            bundle.putSerializable(Constants.RECEPTION_ITEM_ACTIVITY_KEY,  districtsList.get(i));
//
//            Util.gotoActivityWithBundle(context, ReceptionListActivity.class, bundle);

        }
        adapter.notifyDataSetChanged();
    }

    class AddressAdapter extends BaseAdapter {

        private List<gdws_sys_area> list;

        public AddressAdapter(List<gdws_sys_area> list){
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
            text.setText(list.get(i).getName());
            if (list.get(i).getCode().equals(code)) {
                text.setTextColor(context.getResources().getColor(R.color.new_redb));
                ivSelect.setVisibility(View.VISIBLE);
            }
            return view;
        }
    }
}
