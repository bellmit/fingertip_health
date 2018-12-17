package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.jqsoft.fingertip_health.bean.GDWS_ICD;
import com.jqsoft.fingertip_health.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiseaseAdapter<T> extends BaseAdapter implements Filterable {
	// private List<String> mObjects;

	private List<GDWS_ICD> beanList;// 支持多音字,类似:{{z,c},{j},{z},{q,x}}的集合

	private final Object mLock = new Object();

	private int mResource;

	private int mFieldId = 0;

	private Context mContext;

	private ArrayList<GDWS_ICD> alllistdata;
	private ArrayFilter mFilter;

	private LayoutInflater mInflater;

	public static final int ALL = -1;// 全部
	private int maxMatch = 10;// 最多显示多少个可能选项

	// public SearchAdapter(Context context,int textViewResourceId,
	// List<People> objects,int maxMatch){
	// // TODO Auto-generated constructor stub
	// init(context,textViewResourceId,0,objects);
	// this.pinyinList = getHanziSpellList(objects);
	// this.maxMatch = maxMatch;
	// }

	public DiseaseAdapter(Context context, int textViewResourceId,
                          List<GDWS_ICD> objects, int maxMatch) {
		// TODO Auto-generated constructor stub
		init(context, textViewResourceId, 0, objects);
		this.beanList = objects;
		this.maxMatch = maxMatch;
	}

	private void init(Context context, int resource, int textViewResourceId,
			List<GDWS_ICD> beanList) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mResource = resource;
		mFieldId = textViewResourceId;
	}

	public int getCount() {
		return beanList == null ? 0 : beanList.size();
	}

	public Object getItem(int position) {
		return beanList.get(position);
	}

	public int getPosition(T item) {
		return beanList.indexOf(item);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		return createViewFromResource(position, convertView, parent, mResource);
	}

	private View createViewFromResource(int position, View convertView,
			ViewGroup parent, int resource) {
		View view;
		if (convertView == null) {
			view = mInflater.inflate(resource, parent, false);
		} else {
			view = convertView;
		}
		TextView tv_zdid = (TextView) view.findViewById(R.id.tv_zdid);
		TextView tv_zdname = (TextView) view.findViewById(R.id.tv_zdname);

		tv_zdid.setText(beanList.get(position).getCode());
		tv_zdname.setText(beanList.get(position).getName());
		return view;
	}

	public Filter getFilter() {
		if (mFilter == null) {
			mFilter = new ArrayFilter();
		}
		return mFilter;
	}

	private class ArrayFilter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence prefix) {
			System.out.println("performFiltering----->>>" + prefix);
			FilterResults results = new FilterResults();

			if (alllistdata == null) {
				synchronized (mLock) {
					alllistdata = new ArrayList<GDWS_ICD>(beanList);//
				}
			}

			if (prefix == null || prefix.length() == 0) {
				synchronized (mLock) {
					// ArrayList<T> list = new ArrayList<T>();//无
					ArrayList<GDWS_ICD> list = new ArrayList<GDWS_ICD>(
							alllistdata);// List<T>
					results.values = list;
					results.count = list.size();
				}
			} else {
				String prefixString = prefix.toString().toLowerCase();
				System.out.println(prefixString);
				final ArrayList<GDWS_ICD> hanzi = alllistdata;// 汉字String
				final int count = hanzi.size();

				final Set<GDWS_ICD> newValues = new HashSet<GDWS_ICD>(
						count);// 支持多音字,不重复

				for (int i = 0; i < count; i++) {
					final String value = hanzi.get(i).getName();// 汉字String
					//Toast.makeText(mContext,value,Toast.LENGTH_SHORT).show();
					final String pinyin = hanzi.get(i).getPinyin()
							.toLowerCase();
					System.out.println("+++++++++++++"+hanzi.get(i).getName());
					System.out.println("+++++++++++++"+hanzi.get(i).getPinyin()
							.toLowerCase());
					if (pinyin.contains(prefixString.toLowerCase())
							|| pinyin.indexOf(prefixString.toLowerCase()) != -1
							|| pinyin.equals(prefixString.toLowerCase())) {// 任意匹配
						newValues.add(hanzi.get(i));
					} else if (value.indexOf(prefixString) != -1) {
						newValues.add(hanzi.get(i));
					}
					if (maxMatch > 0) {// 有数量限制
						if (newValues.size() > maxMatch - 1) {// 不要太多
							break;
						}
					}
				}
				List<GDWS_ICD> list = Set2List(newValues);// 转成List
				results.values = list;
				results.count = list.size();
			}
			return results;
		}

		protected void publishResults(CharSequence constraint,
				FilterResults results) {

			beanList = (List<GDWS_ICD>) results.values;
			if (results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}
	}

	// List Set 相互转换
	public <T extends Object> Set<T> List2Set(List<T> tList) {
		Set<T> tSet = new HashSet<T>(tList);
		// TODO 具体实现看需求转换成不同的Set的子类。
		return tSet;
	}

	public <T extends Object> List<T> Set2List(Set<T> oSet) {
		List<T> tList = new ArrayList<T>(oSet);
		// TODO 需要在用到的时候另外写构造，根据需要生成List的对应子类。
		return tList;
	}

	// public List<People> getList(){
	// return mObjects;
	// }

	public void updateList(List<GDWS_ICD> list) {
		this.beanList = list;
	}

	public List<GDWS_ICD> getBeanList() {
		return beanList;

	}
}
