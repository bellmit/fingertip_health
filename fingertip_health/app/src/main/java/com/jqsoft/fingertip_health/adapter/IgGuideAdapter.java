package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.AnswerListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.IgGuideBean;
import com.jqsoft.fingertip_health.di.ui.activity.IgGuideActivity;
import com.jqsoft.fingertip_health.util.Util;
import com.mixiaoxiao.smoothcompoundbutton.SmoothCheckBox;
import com.mixiaoxiao.smoothcompoundbutton.SmoothCompoundButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//智能引导适配器
public class IgGuideAdapter extends BaseQuickAdapterEx<IgGuideBean, BaseViewHolder> {
    public static final int TYPE_SINGLE_LINE=1;
    public static final int TYPE_MULTIPLE_LINE=2;
    private String SINGLECHOOSE="0";
    private String Multiselect="1";
    private Map<String, Boolean> checkmap = new HashMap<>();
    List<IgGuideBean> data;
    private List<Map<String, String>> mData;// 存储的EditText值
        public Map<String, String> editorValue = new HashMap<String, String>();//
    private int type=TYPE_MULTIPLE_LINE;
    private Context context;
    private List<AnswerListBean> answerList;
    private int mposition;
        int i=0;
    public IgGuideAdapter(List<IgGuideBean> data, int type,Context context) {
        super(R.layout.item_igguide_single_line, data);
        this.type = type;
        this.data = data;
        this.context=context;
    }

//    private void initMap() {
//        for (int i = 0; i < data.size(); i++) {
//            checkmap.put(i, true);
//        }
//    }


    public void restdata(){
        checkmap.clear();
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    public void checkmorepush(final SmoothCheckBox CheckBoxs , final String QusId, final String ansId){

        CheckBoxs.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCompoundButton smoothCompoundButton, boolean b) {
                if (b) {
                    ((IgGuideActivity) context).saveMUData(QusId, ansId);
                    checkmap.put(ansId, b);


                }else if (!b){
                    ((IgGuideActivity)context).removeMuData(QusId,ansId);
                    checkmap.put(ansId, b);
                }
            }


        });

        if (checkmap.get(ansId) == null) {
            checkmap.put(ansId, false);
        }
        CheckBoxs.setChecked(checkmap.get(ansId));
    }
    public void checkSingpush(final SmoothRadioButton  choose, final String QusId, final String data){

        choose.setOnCheckedChangeListener(new SmoothRadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCompoundButton smoothCompoundButton, boolean b) {
                if (b){
                    ((IgGuideActivity)context).saveData(QusId,data);
                    checkmap.put(data, b);
                }
                else if (!b){
                    ((IgGuideActivity)context).removeSingleChooseData(QusId,data);
                    checkmap.put(data, b);
                }
            }


        });
        //
        if (checkmap.get(data) == null) {
            checkmap.put(data, false);
        }
        choose.setChecked(checkmap.get(data));

    }





    @Override
    protected void convert(final BaseViewHolder helper, final IgGuideBean item) {


        final SmoothCheckBox choose= helper.getView(R.id.choose);
        final SmoothCheckBox choose1= helper.getView(R.id.choose1);
        final SmoothCheckBox choose2= helper.getView(R.id.choose2);
        final SmoothCheckBox choose3= helper.getView(R.id.choose3);
        final SmoothCheckBox choose4= helper.getView(R.id.choose4);
        final SmoothCheckBox choose5= helper.getView(R.id.choose5);
        final SmoothRadioButton muchoose= helper.getView(R.id.muchoose);
        final SmoothRadioButton muchoose1= helper.getView(R.id.muchoose1);
        final SmoothRadioButton muchoose2= helper.getView(R.id.muchoose2);
        final SmoothRadioButton muchoose3= helper.getView(R.id.muchoose3);
        final SmoothRadioButton muchoose4= helper.getView(R.id.muchoose4);



        final SmoothRadioButton[]  radioButtonArray = new SmoothRadioButton[]{muchoose, muchoose1, muchoose2, muchoose3, muchoose4};
        for (int i = 0; i < radioButtonArray.length; ++i) {
            final SmoothRadioButton radioButton = radioButtonArray[i];
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < radioButtonArray.length; ++j) {
                        SmoothRadioButton button = radioButtonArray[j];
                        if (button != radioButton) {
                            button.setChecked(false);
                        }
                    }
                    radioButton.setChecked(true);

                }
            });}




        answerList=item.getAnswerList();
        if (helper.getPosition()<2){
            helper.setVisible(R.id.star,true);
        }else {

            helper.setVisible(R.id.star,false);
        }




        helper.setText(R.id.tv_content, Util.trimString("Q"+(helper.getPosition()+1)+": "+item.getQuestion()));
        if (item.getAnswerType()!=null){
        if (item.getAnswerType().equals(Multiselect)){
            helper.setVisible(R.id.muchoose,false);
            helper.setVisible(R.id.muchoose1,false);
            helper.setVisible(R.id.muchoose2,false);
            helper.setVisible(R.id.muchoose3,false);
            helper.setVisible(R.id.muchoose4,false);
        switch (answerList.size()) {
            case 0:
                helper.setVisible(R.id.muchoose,false);
                helper.setVisible(R.id.muchoose1,false);
                helper.setVisible(R.id.muchoose2,false);
                helper.setVisible(R.id.muchoose3,false);
                helper.setVisible(R.id.muchoose4,false);
                helper.setVisible(R.id.choose,false);
                helper.setVisible(R.id.choose1,false);
                helper.setVisible(R.id.choose2,false);
                helper.setVisible(R.id.choose3,false);
                helper.setVisible(R.id.choose4,false);
                helper.setVisible(R.id.choose5,false);
                break;
            case 2:

                helper.setVisible(R.id.choose, true);
                helper.setVisible(R.id.choose1, true);
                helper.setText(R.id.choose, Util.trimString(answerList.get(0).getAnswer()));
                helper.setText(R.id.choose1, Util.trimString(answerList.get(1).getAnswer()));

                checkmorepush(choose,item.getQuestionId(),answerList.get(0).getAnswerId());
                checkmorepush(choose1,item.getQuestionId(),answerList.get(1).getAnswerId());

//                checkmorepush(checkboxs,item.getQuestionId(),item.getQuestionId()+"_"+answerList.get(1).getAnswerId());
                    break;
            case 3:

                helper.setVisible(R.id.choose, true);
                helper.setVisible(R.id.choose1, true);
                helper.setVisible(R.id.choose2, true);
                helper.setText(R.id.choose, Util.trimString(answerList.get(0).getAnswer()));
                helper.setText(R.id.choose1, Util.trimString(answerList.get(1).getAnswer()));
                helper.setText(R.id.choose2, Util.trimString(answerList.get(2).getAnswer()));
                checkmorepush(choose,item.getQuestionId(),answerList.get(0).getAnswerId());
                checkmorepush(choose1,item.getQuestionId(),answerList.get(1).getAnswerId());
                checkmorepush(choose2,item.getQuestionId(),answerList.get(2).getAnswerId());

                break;
            case 4:

                helper.setVisible(R.id.choose, true);
                helper.setVisible(R.id.choose1, true);
                helper.setVisible(R.id.choose2, true);
                helper.setVisible(R.id.choose3, true);
                helper.setText(R.id.choose, Util.trimString(answerList.get(0).getAnswer()));
                helper.setText(R.id.choose1, Util.trimString(answerList.get(1).getAnswer()));
                helper.setText(R.id.choose2, Util.trimString(answerList.get(2).getAnswer()));
                helper.setText(R.id.choose3, Util.trimString(answerList.get(3).getAnswer()));
                checkmorepush(choose,item.getQuestionId(),answerList.get(0).getAnswerId());
                checkmorepush(choose1,item.getQuestionId(),answerList.get(1).getAnswerId());
                checkmorepush(choose2,item.getQuestionId(),answerList.get(2).getAnswerId());
                checkmorepush(choose3,item.getQuestionId(),answerList.get(3).getAnswerId());
                break;
            case 5:

                helper.setVisible(R.id.choose, true);
                helper.setVisible(R.id.choose1, true);
                helper.setVisible(R.id.choose2, true);
                helper.setVisible(R.id.choose3, true);
                helper.setVisible(R.id.choose4, true);
                helper.setText(R.id.choose, Util.trimString(answerList.get(0).getAnswer()));
                helper.setText(R.id.choose1, Util.trimString(answerList.get(1).getAnswer()));
                helper.setText(R.id.choose2, Util.trimString(answerList.get(2).getAnswer()));
                helper.setText(R.id.choose3, Util.trimString(answerList.get(3).getAnswer()));
                helper.setText(R.id.choose4, Util.trimString(answerList.get(4).getAnswer()));
                checkmorepush(choose,item.getQuestionId(),answerList.get(0).getAnswerId());
                checkmorepush(choose1,item.getQuestionId(),answerList.get(1).getAnswerId());
                checkmorepush(choose2,item.getQuestionId(),answerList.get(2).getAnswerId());
                checkmorepush(choose3,item.getQuestionId(),answerList.get(3).getAnswerId());
                checkmorepush(choose4,item.getQuestionId(),answerList.get(4).getAnswerId());
                break;
            case 6:
                helper.setVisible(R.id.choose, true);
                helper.setVisible(R.id.choose1, true);
                helper.setVisible(R.id.choose2, true);
                helper.setVisible(R.id.choose3, true);
                helper.setVisible(R.id.choose4, true);
                helper.setVisible(R.id.choose5, true);
                helper.setText(R.id.choose, Util.trimString(answerList.get(0).getAnswer()));
                helper.setText(R.id.choose1, Util.trimString(answerList.get(1).getAnswer()));
                helper.setText(R.id.choose2, Util.trimString(answerList.get(2).getAnswer()));
                helper.setText(R.id.choose3, Util.trimString(answerList.get(3).getAnswer()));
                helper.setText(R.id.choose4, Util.trimString(answerList.get(4).getAnswer()));
                helper.setText(R.id.choose5, Util.trimString(answerList.get(5).getAnswer()));
                checkmorepush(choose,item.getQuestionId(),answerList.get(0).getAnswerId());
                checkmorepush(choose1,item.getQuestionId(),answerList.get(1).getAnswerId());
                checkmorepush(choose2,item.getQuestionId(),answerList.get(2).getAnswerId());
                checkmorepush(choose3,item.getQuestionId(),answerList.get(3).getAnswerId());
                checkmorepush(choose4,item.getQuestionId(),answerList.get(4).getAnswerId());
                checkmorepush(choose5,item.getQuestionId(),answerList.get(5).getAnswerId());
                break;



            default:
                break;}



    }else
        if (item.getAnswerType().equals(SINGLECHOOSE))
    {
            helper.setVisible(R.id.choose,false);
            helper.setVisible(R.id.choose1,false);
            helper.setVisible(R.id.choose2,false);
            helper.setVisible(R.id.choose3,false);
            helper.setVisible(R.id.choose4,false);
            helper.setVisible(R.id.choose5,false);


                switch (answerList.size()){

                    case 0:
                        helper.setVisible(R.id.muchoose,false);
                        helper.setVisible(R.id.muchoose1,false);
                        helper.setVisible(R.id.muchoose2,false);
                        helper.setVisible(R.id.muchoose3,false);
                        helper.setVisible(R.id.muchoose4,false);
                        helper.setVisible(R.id.choose,false);
                        helper.setVisible(R.id.choose1,false);
                        helper.setVisible(R.id.choose2,false);
                        helper.setVisible(R.id.choose3,false);
                        helper.setVisible(R.id.choose4,false);
                        helper.setVisible(R.id.choose5,false);
                        break;
                case 2:

                    helper.setVisible(R.id.muchoose4,false);
                    helper.setVisible(R.id.muchoose3,false);
                    helper.setVisible(R.id.muchoose2,false);
                    helper.setVisible(R.id.muchoose,true);
                    helper.setVisible(R.id.muchoose1,true);
                    helper.setText(R.id.muchoose, Util.trimString(answerList.get(0).getAnswer()));
                    helper.setText(R.id.muchoose1, Util.trimString(answerList.get(1).getAnswer()));


                    checkSingpush(muchoose,item.getQuestionId(),answerList.get(0).getAnswerId());
                    checkSingpush(muchoose1,item.getQuestionId(),answerList.get(1).getAnswerId());


            break;
            case 3:



                helper.setVisible(R.id.muchoose4,false);
                helper.setVisible(R.id.muchoose3,false);
                    helper.setVisible(R.id.muchoose,true);
                    helper.setVisible(R.id.muchoose1,true);
                    helper.setVisible(R.id.muchoose2,true);
                    helper.setText(R.id.muchoose, Util.trimString(answerList.get(0).getAnswer()));
                    helper.setText(R.id.muchoose1, Util.trimString(answerList.get(1).getAnswer()));
                    helper.setText(R.id.muchoose2, Util.trimString(answerList.get(2).getAnswer()));

                    checkSingpush(muchoose,item.getQuestionId(),answerList.get(0).getAnswerId());
                    checkSingpush(muchoose1,item.getQuestionId(),answerList.get(1).getAnswerId());
                    checkSingpush(muchoose2,item.getQuestionId(),answerList.get(2).getAnswerId());

                break;
            case 4:
                helper.setVisible(R.id.muchoose4,false);
                    helper.setVisible(R.id.muchoose,true);
                    helper.setVisible(R.id.muchoose1,true);
                    helper.setVisible(R.id.muchoose2,true);
                    helper.setVisible(R.id.muchoose3,true);
                    helper.setText(R.id.muchoose, Util.trimString(answerList.get(0).getAnswer()));
                    helper.setText(R.id.muchoose1, Util.trimString(answerList.get(1).getAnswer()));
                    helper.setText(R.id.muchoose2, Util.trimString(answerList.get(2).getAnswer()));
                    helper.setText(R.id.muchoose3, Util.trimString(answerList.get(3).getAnswer()));


                checkSingpush(muchoose,item.getQuestionId(),answerList.get(0).getAnswerId());
                checkSingpush(muchoose1,item.getQuestionId(),answerList.get(1).getAnswerId());
                checkSingpush(muchoose2,item.getQuestionId(),answerList.get(2).getAnswerId());
                checkSingpush(muchoose3,item.getQuestionId(),answerList.get(3).getAnswerId());

                break;
            case 5:

                    helper.setVisible(R.id.muchoose,true);
                    helper.setVisible(R.id.muchoose1,true);
                    helper.setVisible(R.id.muchoose2,true);
                    helper.setVisible(R.id.muchoose3,true);
                    helper.setVisible(R.id.muchoose4,true);
                    helper.setText(R.id.muchoose, Util.trimString(answerList.get(0).getAnswer()));
                    helper.setText(R.id.muchoose1, Util.trimString(answerList.get(1).getAnswer()));
                    helper.setText(R.id.muchoose2, Util.trimString(answerList.get(2).getAnswer()));
                    helper.setText(R.id.muchoose3, Util.trimString(answerList.get(3).getAnswer()));
                    helper.setText(R.id.muchoose4, Util.trimString(answerList.get(4).getAnswer()));

                checkSingpush(muchoose,item.getQuestionId(),answerList.get(0).getAnswerId());
                checkSingpush(muchoose1,item.getQuestionId(),answerList.get(1).getAnswerId());
                checkSingpush(muchoose2,item.getQuestionId(),answerList.get(2).getAnswerId());
                checkSingpush(muchoose3,item.getQuestionId(),answerList.get(3).getAnswerId());
                checkSingpush(muchoose4,item.getQuestionId(),answerList.get(4).getAnswerId());

                break;

            default:
                break;
        } }

        }else {
            //没有anstype
            helper.setVisible(R.id.muchoose,false);
            helper.setVisible(R.id.muchoose1,false);
            helper.setVisible(R.id.muchoose2,false);
            helper.setVisible(R.id.muchoose3,false);
            helper.setVisible(R.id.muchoose4,false);
            helper.setVisible(R.id.choose,false);
            helper.setVisible(R.id.choose1,false);
            helper.setVisible(R.id.choose2,false);
            helper.setVisible(R.id.choose3,false);
            helper.setVisible(R.id.choose4,false);
            helper.setVisible(R.id.choose5,false);

        }
        }


    }




