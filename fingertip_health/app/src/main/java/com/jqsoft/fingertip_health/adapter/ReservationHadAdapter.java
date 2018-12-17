//package com.jqsoft.grassroots_civil_administration_platform.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
//import com.jqsoft.fingertip_health.bean.ReservationBeanList;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils2.AppUtils;
//import com.jqsoft.fingertip_health.utils3.util.StringUtils;
//import com.mixiaoxiao.smoothcompoundbutton.SmoothCompoundButton;
//import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioButton;
//import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioGroup;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ReservationHadAdapter extends BaseAdapter implements  SmoothRadioButton.OnCheckedChangeListener,SmoothRadioGroup.OnCheckedChangeListener{
//
//	private Context context;
//	private ViewHolder viewHolder = null;
//	private LayoutInflater inflater;
//	private List<ReservationBeanList> data;
//	private PeopleBaseInfoBean mpeopleBasebean;
//	String address_shangmen="",address_cunsi="",address_xiangz="",address_other="";
//	public ReservationHadAdapter(Context context, List<ReservationBeanList> data ) {
//		super();
//		this.context = context;
//		this.data = data;
//
//	}
//
//	@Override
//	public int getCount() {
//
//		return data.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		return position;
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return position;
//	}
//
//	@Override
//	public View getView(final int position, View convertView, ViewGroup parent) {
//
//		if (convertView == null) {
//			inflater = LayoutInflater.from(parent.getContext());
//			convertView = inflater.inflate(R.layout.item_reservation_had, null);
//			viewHolder = new ViewHolder();
//			viewHolder.tv_applyname = (TextView) convertView.findViewById(R.id.tv_applyname);
//
//
//			viewHolder.iv_applysex = (ImageView) convertView.findViewById(R.id.iv_applysex);
//			viewHolder.tv_applyage = (TextView) convertView.findViewById(R.id.tv_applyage);
//			viewHolder.tv_applycard = (TextView) convertView.findViewById(R.id.tv_applycard);
//			viewHolder.tv_filename = (TextView) convertView.findViewById(R.id.tv_filename);
//			viewHolder.tv_applyadrress = (TextView) convertView.findViewById(R.id.tv_applyadrress);
//			viewHolder.tv_applypackname = (TextView) convertView.findViewById(R.id.tv_applypackname);
//			viewHolder.tv_applyservername = (TextView) convertView.findViewById(R.id.tv_applyservername);
//			viewHolder.tv_applydate = (TextView) convertView.findViewById(R.id.tv_applydate);
//
//			viewHolder.iv_tang = (ImageView) convertView.findViewById(R.id.iv_tang);
//			viewHolder.iv_gao = (ImageView) convertView.findViewById(R.id.iv_gao);
//			viewHolder.iv_lao = (ImageView) convertView.findViewById(R.id.iv_lao);
//			viewHolder.iv_jing = (ImageView) convertView.findViewById(R.id.iv_jing);
//			viewHolder.iv_mian = (ImageView) convertView.findViewById(R.id.iv_mian);
//			viewHolder.iv_pin = (ImageView) convertView.findViewById(R.id.iv_pin);
//			viewHolder.iv_tong = (ImageView) convertView.findViewById(R.id.iv_tong);
//			viewHolder.iv_tuo = (ImageView) convertView.findViewById(R.id.iv_tuo);
//			viewHolder.iv_yun = (ImageView) convertView.findViewById(R.id.iv_yun);
//			viewHolder.iv_applyphone = (ImageView) convertView.findViewById(R.id.iv_applyphone);
//
//			viewHolder.rg_adress1 = (SmoothRadioGroup) convertView.findViewById(R.id.rg_adress1);
//			viewHolder.rg_adress2 = (SmoothRadioGroup) convertView.findViewById(R.id.rg_adress2);
//			viewHolder.rb_adress1 = (SmoothRadioButton) convertView.findViewById(R.id.rb_adress1);
//			viewHolder.rb_adress2 = (SmoothRadioButton) convertView.findViewById(R.id.rb_adress2);
//			viewHolder.rb_adress3 = (SmoothRadioButton) convertView.findViewById(R.id.rb_adress3);
//			viewHolder.rb_adress4 = (SmoothRadioButton) convertView.findViewById(R.id.rb_adress4);
//			viewHolder.et_adress_other = (EditText) convertView.findViewById(R.id.et_execu_addressother);
//
//			convertView.setTag(viewHolder);
//		} else {
//			viewHolder = (ViewHolder) convertView.getTag();
//		}
//
//		viewHolder.tv_applyname.setText(data.get(position).getUserName());
//		String sSex =data.get(position).getSexCode();
//		if(sSex.equals("1")){
//			viewHolder.iv_applysex.setImageResource(R.mipmap.i_male);
//
//		}else{
//			viewHolder.iv_applysex.setImageResource(R.mipmap.i_female);
//		}
//		viewHolder.tv_applyage.setText(data.get(position).getAge()+"岁");
//		viewHolder.tv_applycard.setText(data.get(position).getCardNo());
//		viewHolder.tv_filename.setText(data.get(position).getNo());
//		viewHolder.tv_applyadrress.setText(data.get(position).getAddress());
//		viewHolder.tv_applypackname.setText(data.get(position).getPakageName());
//		viewHolder.tv_applyservername.setText(data.get(position).getServerContent());
//		viewHolder.tv_applydate.setText(data.get(position).getReservationTime());
//
//
//		String spersonMold =data.get(position).getPersonMold();
//
//		ArrayList<String> spersonMoldList = new ArrayList<>();
//
//		spersonMoldList.clear();
//		if(spersonMold.substring(0,1).equals("1")){
//			spersonMoldList.add("高");
//		}
//		if(spersonMold.substring(1,2).equals("1")){
//			spersonMoldList.add("糖");
//		}
//		if(spersonMold.substring(2,3).equals("1")){
//			spersonMoldList.add("精");
//		}
//		if(spersonMold.substring(3,4).equals("1")){
//			spersonMoldList.add("老");
//		}
//		if(spersonMold.substring(4,5).equals("1")){
//			spersonMoldList.add("孕");
//		}
//		if(spersonMold.substring(5,6).equals("1")){
//			spersonMoldList.add("童");
//		}
//		if(spersonMold.substring(6,7).equals("1")){
//			spersonMoldList.add("贫");
//		}
//		if(spersonMold.substring(6,7).equals("2")){
//			spersonMoldList.add("脱");
//		}
//		if(spersonMold.substring(6,7).equals("3")){
//			spersonMoldList.add("免");
//		}
//
//		String s="";
//		for(int i=0;i<spersonMoldList.size();i++){
//			s=s+spersonMoldList.get(i);
//		}
//		if(s.indexOf("糖")!=-1){
//			viewHolder.iv_tang.setImageResource(R.mipmap.ic_tang);
//			viewHolder.iv_tang.setVisibility(View.VISIBLE);
//		}
//
//		if(s.indexOf("高")!=-1) {
//			viewHolder.iv_gao.setImageResource(R.mipmap.ic_gao);
//			viewHolder.iv_gao.setVisibility(View.VISIBLE);
//		}
//		if(s.indexOf("老")!=-1) {
//			viewHolder.iv_lao.setImageResource(R.mipmap.ic_lao);
//			viewHolder.iv_lao.setVisibility(View.VISIBLE);
//		}
//		if(s.indexOf("精")!=-1) {
//			viewHolder.iv_jing.setImageResource(R.mipmap.ic_jing);
//			viewHolder.iv_jing.setVisibility(View.VISIBLE);
//		}
//		if(s.indexOf("免")!=-1) {
//			viewHolder.iv_mian.setImageResource(R.mipmap.ic_mian);
//			viewHolder.iv_mian.setVisibility(View.VISIBLE);
//		}
//		if(s.indexOf("贫")!=-1) {
//			viewHolder.iv_pin.setImageResource(R.mipmap.ic_pin);
//			viewHolder.iv_pin.setVisibility(View.VISIBLE);
//		}
//		if(s.indexOf("童")!=-1) {
//			viewHolder.iv_tong.setImageResource(R.mipmap.ic_tong);
//			viewHolder.iv_tong.setVisibility(View.VISIBLE);
//		}
//		if(s.indexOf("脱")!=-1) {
//			viewHolder.iv_tuo.setImageResource(R.mipmap.ic_tuo);
//			viewHolder.iv_tuo.setVisibility(View.VISIBLE);
//		}
//		if(s.indexOf("孕")!=-1) {
//			viewHolder.iv_yun.setImageResource(R.mipmap.ic_yun);
//			viewHolder.iv_yun.setVisibility(View.VISIBLE);
//		}
//
//		viewHolder.iv_applyphone.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if (!StringUtils.isBlank(data.get(position).getPhone())){
//					AppUtils.actionDial(context,
//							data.get(position).getPhone());
//				} else {
//					Util.showToast(context,
//							Constants.HINT_PHONE_NUMBER_EMPTY);
//				}
//				// Toast.makeText(getActivity(),mpeopleBasebean.getPhone(),Toast.LENGTH_SHORT).show();
//			}
//		});
//
//		String sHadCheck = data.get(position).getReservationServrtPlace();
//		if(sHadCheck.equals("1")){
//			viewHolder.rb_adress1.setChecked(true);
//			viewHolder.et_adress_other.setVisibility(View.GONE);
//
//		}else if(sHadCheck.equals("2")){
//			viewHolder.rb_adress2.setChecked(true);
//			viewHolder.et_adress_other.setVisibility(View.GONE);
//
//		}else if(sHadCheck.equals("3")){
//			viewHolder.rb_adress3.setChecked(true);
//			viewHolder.et_adress_other.setVisibility(View.GONE);
//
//		}else if(sHadCheck.equals("4")){
//			viewHolder.rb_adress4.setChecked(true);
//			viewHolder.et_adress_other.setVisibility(View.VISIBLE);
//			viewHolder.et_adress_other.setText(data.get(position).getReservationServrtPlaceOther());
//		}
//
//
//		viewHolder.rb_adress1.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				viewHolder.rg_adress2.clearCheck();
//				viewHolder.et_adress_other.setVisibility(View.GONE);
//			}
//		});
//
//		viewHolder.rb_adress2.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				viewHolder.rg_adress2.clearCheck();
//				viewHolder.et_adress_other.setVisibility(View.GONE);
//			}
//		});
//
//		viewHolder.rb_adress3.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				viewHolder.rg_adress2.clearCheck();
//				viewHolder.et_adress_other.setVisibility(View.GONE);
//			}
//		});
//
//		viewHolder.rb_adress4.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				viewHolder.rg_adress1.clearCheck();
//				viewHolder.et_adress_other.setVisibility(View.VISIBLE);
//
//			}
//		});
//
//
//
//
//		return convertView;
//	}
//
//	@Override
//	public void onCheckedChanged(SmoothCompoundButton smoothCompoundButton, boolean b) {
//		/*switch (smoothCompoundButton.getId()) {
//			case R.id.rb_adress1:
//				viewHolder.rg_adress2.clearCheck();
//				address_shangmen="1";
//				address_cunsi="";
//				address_xiangz="";
//				address_other="";
//				//     Toast.makeText(getApplicationContext(),"上门:"+ address_shangmen, Toast.LENGTH_SHORT).show();
//				break;
//			case R.id.rb_adress2:
//				viewHolder.rg_adress2.clearCheck();
//
//				address_shangmen="";
//				address_cunsi="2";
//				address_xiangz="";
//				address_other="";
//				//    Toast.makeText(getApplicationContext(), "村室"+address_cunsi, Toast.LENGTH_SHORT).show();
//				break;
//			case R.id.rb_adress3:
//				viewHolder.rg_adress2.clearCheck();
//				address_shangmen="";
//				address_cunsi="";
//				address_xiangz="3";
//				address_other="";
//				//      Toast.makeText(getApplicationContext(), "乡镇"+address_xiangz, Toast.LENGTH_SHORT).show();
//				break;
//			case R.id.rb_adress4:
//				viewHolder.rg_adress1.clearCheck();
//
//				address_shangmen="";
//				address_cunsi="";
//				address_xiangz="";
//				address_other="4";
//				//      Toast.makeText(getApplicationContext(), "其他"+address_other, Toast.LENGTH_SHORT).show();
//				break;
//
//			default:
//				break;
//		}*/
//	}
//
//	@Override
//	public void onCheckedChanged(SmoothRadioGroup smoothRadioGroup, int i) {
//		/*switch (i) {
//			case R.id.rb_adress1:
//				address_shangmen="1";
//				address_cunsi="";
//				address_xiangz="";
//				address_other="";
//				//   Toast.makeText(getApplicationContext(),"上门:"+ address_shangmen, Toast.LENGTH_SHORT).show();
//				break;
//			case R.id.rb_adress2:
//				address_shangmen="";
//				address_cunsi="2";
//				address_xiangz="";
//				address_other="";
//				//    Toast.makeText(getApplicationContext(), "村室"+address_cunsi, Toast.LENGTH_SHORT).show();
//				break;
//			case R.id.rb_adress3:
//
//				address_shangmen="";
//				address_cunsi="";
//				address_xiangz="3";
//				address_other="";
//				//   Toast.makeText(getApplicationContext(), "乡镇"+address_xiangz, Toast.LENGTH_SHORT).show();
//				break;
//			case R.id.rb_adress4:
//				address_shangmen="";
//				address_cunsi="";
//				address_xiangz="";
//				address_other="4";
//				//    Toast.makeText(getApplicationContext(), "其他"+address_other, Toast.LENGTH_SHORT).show();
//				break;
//			default:
//				break;
//		}*/
//	}
//
//	static class ViewHolder {
//		public TextView tv_applyname;
//		public ImageView iv_applysex;
//		public TextView tv_applyage;
//		public TextView tv_applycard;
//		public TextView tv_filename;
//		public TextView tv_applyadrress;
//		public TextView tv_applypackname;
//		public TextView tv_applyservername;
//		public TextView tv_applydate;
//		public ImageView iv_tang;
//		public ImageView iv_gao;
//		public ImageView iv_lao;
//		public ImageView iv_jing;
//		public ImageView iv_mian;
//		public ImageView iv_pin;
//		public ImageView iv_tong;
//		public ImageView iv_tuo;
//		public ImageView iv_yun;
//		public ImageView iv_applyphone;
//
//		public SmoothRadioGroup rg_adress1;
//		public SmoothRadioGroup rg_adress2;
//		public SmoothRadioButton rb_adress1;
//		public SmoothRadioButton rb_adress2;
//		public SmoothRadioButton rb_adress3;
//		public SmoothRadioButton rb_adress4;
//		public EditText et_adress_other;
//
//
//	}
//}
