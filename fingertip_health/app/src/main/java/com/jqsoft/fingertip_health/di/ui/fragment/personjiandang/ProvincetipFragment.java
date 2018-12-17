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

import org.litepal.crud.DataSupport;

import java.util.List;

public class ProvincetipFragment implements AdapterView.OnItemClickListener{
    private AddresstipCallBack callBack;
    private String code;
    private Context context;
    private AddressAdapter adapter;
    private ListView listview;
    private   List<gdws_sys_area>  Province ;
    private String setName;

    public ProvincetipFragment(Context context, AddresstipCallBack callBack){
        this.context = context;
        this.callBack = callBack;
        initView();
    }
    public void setCode(String code){
        this.code = code;
    }

    public ListView getListview() {
        return listview;
    }

    public View initView() {


//        List<gdws_sys_area>   list = DataSupport.where(" areaCode=? ",Identity.srcInfo.getAreaId() ).find(gdws_sys_area.class);
//        if (list.get(0).getAreaLevel().equals("area_2")){
//            Province =DataSupport.where(" areaPid=? ",Identity.srcInfo.getAreaId() ).find(gdws_sys_area.class);
//
//
//        }else {
            Province = DataSupport.where(" p_code=?","1" ).order("code asc").find(gdws_sys_area.class);


//        }





        listview = (ListView) LayoutInflater.from(context).inflate(R.layout.select_address_pop_listview,null);
        adapter = new AddressAdapter(Province);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);

        return listview;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


//        code = AddressManager.newInstance().getAllProvinces().get(i).getCode();
        if (callBack != null){
            callBack.selectProvince(Province.get(i));

            code=Province.get(i).getCode();
//            callBack.selectProvince(AddressManager.newInstance().getAllProvinces().get(i));
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
