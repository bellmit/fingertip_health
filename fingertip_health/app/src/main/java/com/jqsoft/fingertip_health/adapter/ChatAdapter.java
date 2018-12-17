package com.jqsoft.fingertip_health.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.response.ChatBean;

import java.util.List;

/**
 * Created by Administrator on 2017-05-19.
 */

public class ChatAdapter extends BaseMultiItemQuickAdapter<ChatBean, BaseViewHolder> {
    public ChatAdapter(List data) {
        super( data);
        addItemType(ChatBean.OTHER_PERSON, R.layout.item_chat_other_person_say);
        addItemType(ChatBean.SELF, R.layout.item_chat_self_say);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatBean item) {
        switch (helper.getItemViewType()) {
            case ChatBean.OTHER_PERSON:
                helper.setText(R.id.tv_content, item.getContent());
                break;
            case ChatBean.SELF:
                helper.setText(R.id.tv_content, item.getContent());
                break;
        }

    }
}