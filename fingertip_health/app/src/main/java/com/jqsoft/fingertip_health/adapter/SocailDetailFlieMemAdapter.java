package com.jqsoft.fingertip_health.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.DetailUplodeFile;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.model.PictureConfig;

import java.util.ArrayList;
import java.util.List;

public class SocailDetailFlieMemAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<DetailUplodeFile> UploadFile;
    private List<LocalMedia> selectMediax = new ArrayList<>();

    public SocailDetailFlieMemAdapter(Context context, ArrayList<DetailUplodeFile> UploadFile) {
        super();
        this.context = context;
        this.UploadFile = UploadFile;

    }

    @Override
    public int getCount() {

        return UploadFile.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.contentview_flie, null);
            viewHolder = new ViewHolder();

            viewHolder.fliename = (TextView) convertView.findViewById(R.id.fliename);
            viewHolder.flietpye = (TextView) convertView.findViewById(R.id.flietpye);
            viewHolder.button = (Button) convertView.findViewById(R.id.button);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.fliename.setText(UploadFile.get(position).getFileName());
        viewHolder.flietpye.setText(UploadFile.get(position).getFileCode());
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(UploadFile.get(position).getFilePath())){
                    String ff = "http://60.170.111.156:8090/sri/JingQi_Sri_File/upload/sriattach/apply/2017/10/cb021cd1-1879-4528-9660-5396cbc155d8.jpg";
                    //selectMediax.add(UploadFile);
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setPath(Version.FILE_URL_BASE);
                    localMedia.setUrl(UploadFile.get(position).getFilePath());//
                    selectMediax.add(localMedia);
                    PictureConfig.getInstance().externalPicturePreview((Activity) context, position, selectMediax);
                }else{
                    Toast.makeText(context,"图片路径为空，无法预览",Toast.LENGTH_SHORT).show();
                }


            }
        });
        return convertView;
    }

    static class ViewHolder {
        public TextView fliename;
        public TextView flietpye;
        public Button button;

    }
}
