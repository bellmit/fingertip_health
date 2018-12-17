package com.jqsoft.fingertip_health.content;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;

//累计评价星级
public class AccumulatedAssessStarLevelContent {

    private Context context;
    private View view;
    private String text;
    private float level;
    private int intLevel;
    public AccumulatedAssessStarLevelContent(Context context) {
        this.context=context;
    }

    public void initView(String text, float level){
        this.text=text;
        this.level=level;
        this.intLevel=(int)level;
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//        View   dialog = inflater.inflate(R.layout.layout_recyclerview_with_padding,(ViewGroup) getActivity().findViewById(R.id.root));
        View rootView = inflater.inflate(R.layout.layout_star_level, null);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView tvText = (TextView) rootView.findViewById(R.id.tv_text);
        TextView tvStarLevel = (TextView) rootView.findViewById(R.id.tv_star_level);
        String title = getTitle();
        String starText = getStarText();
        tvText.setText(title);
        tvStarLevel.setText(starText);

        view=rootView;
    }

    public String getTitle(){
        return text+Constants.COLON_STRING;
    }

    public String getStarText(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intLevel; ++i){
            sb.append(Constants.FILLED_STAR);
        }
        for (int i = 0; i < Constants.MAX_STAR_NUMBER-intLevel; ++i){
            sb.append(Constants.UNFILLED_STAR);
        }
        return sb.toString();
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
