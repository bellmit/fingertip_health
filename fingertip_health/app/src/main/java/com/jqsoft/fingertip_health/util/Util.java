package com.jqsoft.fingertip_health.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.afollestad.materialdialogs.folderselector.FileChooserDialog;
import com.afollestad.materialdialogs.folderselector.FolderChooserDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;
import com.alibaba.fastjson.JSON;
import com.amap.api.maps.AMap;
import com.amap.api.maps.Projection;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.SDCardUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.AreaAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.AreaBean;
import com.jqsoft.fingertip_health.bean.HospitalTypeBean;
import com.jqsoft.fingertip_health.bean.IconTextBackgroundColorBean;
import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.InstitutionCharacterNameValueBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.MedicalAssistantMoneyConstitutionBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValueBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValuePercentBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SubsistenceApprovePovertyReasonNaturalBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.TempDisasterAssistancePercentageBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultHuifuBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.MonthTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.QuarterLogicBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.QuarterTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.YearTextBean;
import com.jqsoft.fingertip_health.configuration.ExecutionProjectsType;
import com.jqsoft.fingertip_health.di.ui.activity.ExecutionProjectsActivity;
import com.jqsoft.fingertip_health.di.ui.activity.IntroductionActivity;
import com.jqsoft.fingertip_health.di.ui.activity.LoginActivityNew;
import com.jqsoft.fingertip_health.di.ui.activity.WorkbenchActivity;
import com.jqsoft.fingertip_health.di.ui.activity.base.VideoDisplayActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.personjiandang.IStringRepresentationAndValue;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCARetrofit;
import com.jqsoft.fingertip_health.helper.chart.DoubleValueFormatter;
import com.jqsoft.fingertip_health.helper.chart.IntValueFormatter;
import com.jqsoft.fingertip_health.listener.HospitalTypeSelectListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils.ToastUtil;
import com.jqsoft.fingertip_health.utils2.AppUtils;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.PreferencesUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.jqsoft.fingertip_health.view.HospitalTypePopupWindow;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.xutils.common.util.MD5;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.jqsoft.fingertip_health.base.Constants.MAX_STAR_NUMBER;

//import cn.jpush.android.api.JPushInterface;

//import com.jqsoft.fingertip_health.di.ui.activity.ChatActivity;

//import com.amap.api.maps.AMapUtils;
//import com.amap.api.maps.model.LatLng;
//import cn.jpush.android.api.JPushInterface;
//import cn.jpush.im.android.api.JMessageClient;

//import com.amap.api.maps.AMap;
//import com.amap.api.maps.AMapUtils;
//import com.amap.api.maps.Projection;
//import com.amap.api.maps.model.LatLng;
//import com.amap.api.maps.model.Marker;


/**
 * Created by Administrator on 2017/5/13.
 */

public class Util {
    public static boolean isOnlyPointNumber(String number) {
        Pattern pattern = Pattern.compile("^\\d+\\.?\\d{0,2}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
    public static DecimalFormat nonscientificNumberFormater = new DecimalFormat(Constants.NONSCIENTIFIC_NUMBER_FORMATTER_STRING);

    public static DecimalFormat COMMA_SEPARATED_INT_FORMATTER = new DecimalFormat(",###");
//    public static DecimalFormat COMMA_SEPARATED_DOUBLE_FORMATTER = new DecimalFormat(",###.00");

    public static int JPushNotificationId = 0;

    static String reception;
    static String areaCode;
    static String fromCollection;

    public static String getFromCollection() {
        return fromCollection;
    }

    public static void setFromCollection(String fromCollection) {
        Util.fromCollection = fromCollection;
    }

    public static String getAreaCode() {
        return areaCode;
    }

    public static void setAreaCode(String areaCode) {
        Util.areaCode = areaCode;
    }

    public static MaterialDialog getProgressDialog() {
        return progressDialog;
    }

    public static void setProgressDialog(MaterialDialog progressDialog) {
        Util.progressDialog = progressDialog;
    }

    public static String getReception() {
        return reception;
    }

    public static void setReception(String reception) {
        Util.reception = reception;
    }

    public static String choicArea(Context context) {
//        String roleId = Identity.srcInfo.getRoleId();
        String roleId = IdentityManager.getRoleId(context);
        if (roleId.contains("Leader")) {

            return "area_1";
        } else if (roleId.contains("SupportUnit")) {
            return "area_2";
        } else if (roleId.contains("TownApply")) {
            return "area_3";
        } else if (roleId.contains("StreetTownAuth")) {
            return "area_3";
        } else if (roleId.contains("CommunityApply")) {
            return "area_3";
        } else {
            return "";
        }


    }

    public static String isMyEmpty(String s){
        if(s==null || s.equals("null") || TextUtils.isEmpty(s)){
            return "无";
        }else {
            return s;
        }
    }

    public static String isMyEmpty1(String s){
        if(s==null || s.equals("null") || TextUtils.isEmpty(s)){
            return "";
        }else {
            return s;
        }
    }


    public static void hideGifProgressDialog(Context context) {
        LoadingUtil.getInstance(context).hide();
    }

    public static void showGifProgressDialog(Context context) {
        try {
            LoadingUtil.getInstance(context).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showGifProgressDialog(Context context, DialogInterface.OnDismissListener dismissListener) {
        try {
            LoadingUtil loadingUtil = LoadingUtil.getInstance(context);
            if (dismissListener != null) {
                loadingUtil.setOnDismissListener(dismissListener);
            }
            loadingUtil.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideLoadingDialog() {
        hideProgressDialog();
    }

    public static void showLoadingDialog(Context context) {
        Util.showProgressDialog(context, "", Constants.HINT_IN_LOADING);

    }

    public static MaterialDialog progressDialog;

    public static void hideProgressDialog() {
        progressDialog.dismiss();
    }

    public static void showProgressDialog(Context context, String title, String msg) {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(msg)
                .progress(true, 0)
                .show();
    }

    public static void showSelectHospitalTypePopupWindow(Context context, View anchorView, List<HospitalTypeBean> list, HospitalTypeSelectListener listener) {
        HospitalTypePopupWindow popupWindow = new HospitalTypePopupWindow((Activity) context,
                ViewGroup.LayoutParams.MATCH_PARENT, Constants.POPUP_WINDOW_HEIGHT,
                anchorView, list, listener);
        popupWindow.show();

    }

    //Icon+Text选择对话框
    public static void showIconTextListMaterialDialog(Context context, List<IconTextBackgroundColorBean> list, int titleResId,
                                                      MaterialSimpleListAdapter.Callback callback) {
        MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(callback);
        if (list == null) {
            list = new ArrayList<>();
        }
        for (int i = 0; i < list.size(); ++i) {
            IconTextBackgroundColorBean item = list.get(i);
            MaterialSimpleListItem listItem = new MaterialSimpleListItem.Builder(context)
                    .content(item.getText())
                    .icon(item.getIconResourceId())
                    .backgroundColor(item.getBackgroundColor())
                    .build();
            adapter.add(listItem);

        }

        new MaterialDialog.Builder(context)
                .title(titleResId)
                .negativeText(Constants.CANCEL)
                .autoDismiss(true)
                .adapter(adapter, null)
                .show();
    }

    //文件夹选择对话框
    public static <ActivityType extends AppCompatActivity & FolderChooserDialog.FolderCallback> void showFolderSelectMaterialDialog(ActivityType context, String initialPath, String tag) {
        initialPath = trimString(initialPath);
        boolean hasSdCard = SDCardUtils.isSDCardEnable();
        if (StringUtils.isBlank(initialPath)) {
            if (hasSdCard) {
                String sdcardPath = SDCardUtils.getSDCardPath();
                initialPath = sdcardPath;

            } else {
                initialPath = Constants.ROOT_PATH;
            }
        }
        String tagUuid = tag;
        // Pass AppCompatActivity which implements FolderCallback
        new FolderChooserDialog.Builder(context)
                .chooseButton(R.string.folder_select_button_title)  // changes label of the choose button
                .initialPath(initialPath)  // changes initial path, defaults to external storage directory
                .tag(tagUuid)
                .goUpLabel(Constants.UP_LEVEL_NAME) // custom go up label, default label is "..."
                .allowNewFolder(true, R.string.new_folder_action_name)
                .show();
    }

    //文件选择对话框
    public static <ActivityType extends AppCompatActivity & FileChooserDialog.FileCallback> void showFileSelectMaterialDialog(ActivityType context, String initialPath, int fileType, String tag) {
        initialPath = trimString(initialPath);
        boolean hasSdCard = SDCardUtils.isSDCardEnable();
        if (StringUtils.isBlank(initialPath)) {
            if (hasSdCard) {
                String sdcardPath = SDCardUtils.getSDCardPath();
                initialPath = sdcardPath;

            } else {
                initialPath = Constants.ROOT_PATH;
            }
        }
        String mime = getMimeTypeStringFromType(fileType);
        String tagUuid = tag;
        // Pass AppCompatActivity which implements FileCallback
        new FileChooserDialog.Builder(context)
                .initialPath(initialPath)  // changes initial path, defaults to external storage directory
                .mimeType(mime) // Optional MIME type filter
//                .extensionsFilter(".png", ".jpg") // Optional extension filter, will override mimeType()
                .tag(tagUuid)
                .goUpLabel(Constants.UP_LEVEL_NAME) // custom go up label, default label is "..."
                .show();
    }

    //颜色选择对话框
    public static <ActivityType extends AppCompatActivity & ColorChooserDialog.ColorCallback> void showColorSelectMaterialDialog(ActivityType context, int presetFirstLevelTitleId, int presetSecondLevelTitleSubId) {
        new ColorChooserDialog.Builder(context, presetFirstLevelTitleId)
                .titleSub(presetSecondLevelTitleSubId)  // title of dialog when viewing shades of a color
                .accentMode(false)  // when true, will display accent palette instead of primary palette
                .doneButton(R.string.confirm)  // changes label of the done button
                .cancelButton(R.string.cancel)  // changes label of the cancel button
                .backButton(R.string.back)  // changes label of the back button
                .customButton(R.string.color_custom)
                .presetsButton(R.string.color_preset)
                .preselect(Color.RED)  // 开始的时候的默认颜色
                .dynamicButtonColor(true)  // defaults to true, false will disable changing action buttons' color to currently selected color
                .show();
    }

    //Indeterminate 水平进度对话框
    public static MaterialDialog showIndeterminateHorizontalMaterialDialog(Context context, String title, String msg) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .content(msg)
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .show();
    }

    //Indeterminate 圆形进度对话框
    public static MaterialDialog showIndeterminateCircularMaterialDialog(Context context, String title, String msg) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .content(msg)
                .progress(true, 0)
                .show();
    }

    //含有RecyclerView(GridLayoutManager)的对话框
    public static MaterialDialog showGridRecyclerViewMaterialDialog(Context context, String title, RecyclerView.Adapter adapter) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .negativeText(Constants.CANCEL)
                // second parameter is an optional layout manager. Must be a LinearLayoutManager or GridLayoutManager.
                .adapter(adapter, new GridLayoutManager(context, Constants.GRID_LAYOUT_DEFAULT_COL_NUMBER))
                .autoDismiss(true)
                .show();


    }

    //含有RecyclerView(LinearLayoutManager)的对话框
    public static MaterialDialog showLinearRecyclerViewMaterialDialog(Context context, String title, RecyclerView.Adapter adapter) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .negativeText(Constants.CANCEL)
                // second parameter is an optional layout manager. Must be a LinearLayoutManager or GridLayoutManager.
                .adapter(adapter, new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
                .autoDismiss(true)
                .show();


    }

    //带有一个输入框的对话框
    public static void showEditTextMaterialDialog(Context context, String title, String msg, String hint, String prefillString, boolean allowEmpty, int minLength, int maxLength, int type, MaterialDialog.InputCallback callback) {
        int flag = getFlagFromEditInputType(type);
        new MaterialDialog.Builder(context)
                .title(title)
//                .iconRes(R.drawable.ic_logo)
                .content(msg)
//                                .widgetColor(Color.BLUE)//输入框光标的颜色
                .inputRange(minLength, maxLength)
                .inputType(flag)//可以输入的类型
                //前2个一个是hint一个是预输入的文字
                .input(hint, prefillString, allowEmpty, callback)

//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        if (dialog.getInputEditText().length() <=10) {
//
//                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
//                        }else {
//                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
//                        }
//                    }
//                })
                .show();
    }

    //自定义对话框
    public static MaterialDialog showCustomViewMaterialDialog(Context context, String title, String msg, int layoutId,
                                                              boolean isWrapInScrollView, MaterialDialog.SingleButtonCallback callback) {
        return new MaterialDialog.Builder(context)
                .title(title)
//                .content(msg)
                .customView(layoutId, isWrapInScrollView)
                .negativeText(Constants.CANCEL)
                .positiveText(Constants.CONFIRM)
                .onPositive(callback)
                .show();
    }

    //自定义对话框
    public static MaterialDialog showCustomViewMaterialDialogWithButtonText(Context context, String title, String msg, int layoutId,
                                                                            String negativeText, String positiveText,
                                                                            boolean isWrapInScrollView, MaterialDialog.SingleButtonCallback callback) {
        return new MaterialDialog.Builder(context)
                .title(title)
//                .content(msg)
                .customView(layoutId, isWrapInScrollView)
                .negativeText(negativeText)
                .positiveText(positiveText)
                .onPositive(callback)
                .show();
    }

    //多项选择列表对话框，每行一个字符串
    public static void showMultipleChoiceStringListMaterialDialog(final Context context, String title, String msg,
                                                                  List<String> list, int[] defaultSelectedIndex,
                                                                  MaterialDialog.ListCallbackMultiChoice callback) {
        ColorStateList csl = getColorStateList();
        Integer[] selectedIndexArray = getIntegerArrayFromIntArray(defaultSelectedIndex);
        new MaterialDialog.Builder(context)
                .title(title)
//                .iconRes(R.drawable.ic_logo)
                .content(msg)
                .items(list)
                .choiceWidgetColor(csl)
                .negativeText(Constants.CANCEL)
                .positiveText(Constants.CONFIRM)
                //多选框添加
                .itemsCallbackMultiChoice(selectedIndexArray, callback)
                //点击确定后获取选中的下标数组
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        dialog.dismiss();
//                        Integer[] array = dialog.getSelectedIndices();
//                        List<Integer> selectedList = getIntegerListFromIntegerArray(array);
//                        String s = join(array, '-');
//                        ToastUtil.show(context, "您选择了"+s);
//                    }
//                })
                .show();
    }

    //单项选择列表对话框，每行一个字符串
    public static void showSingleChoiceStringListMaterialDialog(Context context, String title, String msg,
                                                                List<String> list, int defaultSelectedIndex,
                                                                MaterialDialog.ListCallbackSingleChoice callback) {
        ColorStateList csl = getColorStateList();
        new MaterialDialog.Builder(context)
                .title(title)
                .content(msg)
                .items(list)
                .choiceWidgetColor(csl)
                .autoDismiss(true)
                .negativeText(Constants.CANCEL)
                .itemsCallbackSingleChoice(defaultSelectedIndex, callback)
                .show();
    }

    public static void showSingleChoiceStringListMaterialDialogWithOkButton(Context context, String title, String msg,
                                                                            List<String> list, int defaultSelectedIndex,
                                                                            MaterialDialog.ListCallbackSingleChoice callback) {
        ColorStateList csl = getColorStateList();
        new MaterialDialog.Builder(context)
                .title(title)
                .content(msg)
                .items(list)
                .choiceWidgetColor(csl)
                .autoDismiss(false)
                .positiveText(Constants.CONFIRM)
                .negativeText(Constants.CANCEL)
//                .onPositive(callback)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .itemsCallbackSingleChoice(defaultSelectedIndex, callback)
                .show();
    }

    public static ColorStateList getColorStateList() {
        int[] colors = new int[]{Color.YELLOW, Color.YELLOW, Color.RED, Color.YELLOW, Color.RED, Color.RED};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_window_focused};
        states[5] = new int[]{};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    //列表对话框，每行一个字符串
    public static void showStringListMaterialDialog(Context context, String title, String msg, List<String> list,
                                                    MaterialDialog.ListCallback callback) {
        new MaterialDialog.Builder(context)
                .title(title)
//                .iconRes(R.drawable.ic_logo)
                .content(msg)
                .items(list)
                //.listSelector(R.color.green)//列表的背景颜色
                .autoDismiss(true)//自动消失
                .negativeText(Constants.CANCEL)
                .itemsCallback(callback)
                .show();
    }

    //区域选择对话框
    public static void showAreaSelectMaterialDialog(Context context, List<AreaBean> areaList) {
        /*
  这里使用了 android.support.v7.app.AlertDialog.Builder
  可以直接在头部写 import android.support.v7.app.AlertDialog
  那么下面就可以写成 AlertDialog.Builder
  */
        AlertDialog areaDialog = null;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Constants.HINT_SELECT_AREA);
//        builder.setMessage("这是 android.support.v7.app.AlertDialog 中的样式");
        builder.setNegativeButton(Constants.CANCEL, null);
        builder.setPositiveButton(Constants.CONFIRM, null);

        LayoutInflater inflater = LayoutInflater.from(context);
        View contentView = inflater.inflate(R.layout.layout_recyclerview_with_padding_without_srl, null);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerview);
//        List<AreaBean> areaList = SimulateData.getSimulatedAreaList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, Constants.AREA_SELECTION_VIEW_COL_NUMBER);
        AreaAdapter adapter = new AreaAdapter(areaList);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        builder.setView(contentView);

        areaDialog = builder.create();
        final AlertDialog finalAreaDialog = areaDialog;
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AreaBean item = (AreaBean) adapter.getItem(position);
//                LogUtil.i("has selected item name:" + item.getName());
                RxBus.getDefault().post(Constants.EVENT_TYPE_DID_SELECT_AREA, item);
                finalAreaDialog.dismiss();
            }
        });

        areaDialog.show();
    }


    /**
     * MaterialDialog   普通对话框
     *
     * @param context               设备上下文
     * @param title                 标题
     * @param content               内容
     * @param negativeText          取消按钮的文字
     * @param negtiveClickListener  取消按钮的点击方法
     * @param positiveText          确定按钮的文字
     * @param positiveClickListener 确定按钮的点击方法
     * @param cancelable            点击对话框以外是否可以使对话框消失
     */
    public static void showMaterialDialog(Context context, String title, String content,
                                          String negativeText, MaterialDialog.SingleButtonCallback negtiveClickListener,
                                          String positiveText, MaterialDialog.SingleButtonCallback positiveClickListener,
                                          boolean cancelable) {
        MaterialDialog.Builder dialog = new MaterialDialog.Builder(context);
        dialog.backgroundColorRes(R.color.white);
        if (title != null) {
            dialog.title(title);
//            dialog.title(title).titleColorRes(R.color.material_dialog_title_color);
        }
        if (content != null) {
            dialog.content(content);
//            dialog.content(content).contentColorRes(R.color.material_dialog_content_color);
        }
        if (negativeText != null) {
            dialog.negativeText(negativeText);
//            dialog.negativeText(negativeText)
//                    .negativeColorRes(R.color.material_dialog_cancel_color);
        }
        if (negtiveClickListener != null) {
            dialog.onNegative(negtiveClickListener);
        } else {
            dialog.onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.cancel();
                }
            });
        }
        if (positiveText != null) {
            dialog.positiveText(positiveText)
                    .positiveColorRes(R.color.colorPrimary);
        }
        if (positiveClickListener != null) {
            dialog.onPositive(positiveClickListener);
        } else {
            dialog.onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                }
            });
        }
        dialog.cancelable(cancelable)
                .show();
    }

    // 带有确定与取消按钮的提示对话框
    public static void showAlert(Context context, String title, String msg) {
        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(msg)
                .positiveText(R.string.confirm)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).build();
        dialog.show();

    }

    // 去掉字符串首尾的空白符
    public static String trimString(String s) {
        if (s == null) {
            return Constants.EMPTY_STRING;
        } else {
            return s.trim();
        }
    }

    public static String checkString(String s) {
        if (s == null) {
            return Constants.EMPTY_STRING;
        } else {
            return s;
        }
    }

    public static String testString(String s) {
        String data = "";
        if (s == null || s == "") {
            data = "无";
        }else{
            data=s;
        }
        return data;
    }

    //获取urlencode后的字符串
    public static String encodeString(String s) {
        String checkedString = checkString(s);
        String encodedString = checkedString;
        try {
            encodedString = URLEncoder.encode(checkedString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            encodedString = "";
        }
        return encodedString;
    }

    public static List<Integer> getIntegerListFromIntegerArray(Integer[] array) {
        if (array == null || array.length == 0) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(array);
        }
    }

    public static String join(Integer[] array, char separator) {
        if (array == null || array.length == 0) {
            return Constants.EMPTY_STRING;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(String.valueOf(array[i]));
                sb.append(separator);
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
    }

    public static String join(String[] array, char separator) {
        return join(array, String.valueOf(separator));
    }

    public static String join(String[] array, String separator) {
        if (array == null || array.length == 0) {
            return Constants.EMPTY_STRING;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(String.valueOf(array[i]));
                sb.append(separator);
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
    }

    public static String join(List<String> list, String separator) {
        if (list == null || list.size() == 0) {
            return Constants.EMPTY_STRING;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); ++i) {
                sb.append(String.valueOf(list.get(i)));
                sb.append(separator);
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
    }

    public static Integer[] getIntegerArrayFromIntArray(int[] array) {
        if (array == null || array.length == 0) {
            return new Integer[0];
        } else {
            Integer[] result = new Integer[array.length];
            for (int i = 0; i < array.length; ++i) {
                result[i] = array[i];
            }
            return result;
        }
    }

    public static String[] getStringArrayFromStringList(List<String> list) {
        if (ListUtils.isEmpty(list)) {
            return new String[0];
        } else {
            String[] result = new String[list.size()];
            for (int i = 0; i < list.size(); ++i) {
                result[i] = list.get(i);
            }
            return result;
        }
    }

    public static List<String> getStringListFromStringArray(String[] array) {
        List<String> result = new ArrayList<>();
        if (isStringArrayEmpty(array)) {
            return result;
        } else {
            for (int i = 0; i < array.length; ++i) {
                result.add(array[i]);
            }
            return result;
        }
    }

    public static String getStringFromStringArray(String[] array) {
        if (array == null || array.length == 0) {
            return Constants.EMPTY_STRING;
        } else {
            return Arrays.toString(array);
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < array.length; ++i){
//                sb.append(array[i]);
//                sb.append(",");
//            }
//            return sb.toString();
        }
    }

    public static boolean isStringArrayEmpty(String[] array) {
        if (array == null || array.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isArrayEmpty(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int getFlagFromEditInputType(int type) {
        int result = InputType.TYPE_CLASS_TEXT;
        switch (type) {
            case Constants.EDIT_INPUT_TYPE_TEXT:
                result = InputType.TYPE_CLASS_TEXT;
                break;
            case Constants.EDIT_INPUT_TYPE_DATE_TIME:
                result = InputType.TYPE_CLASS_DATETIME;
                break;
            case Constants.EDIT_INPUT_TYPE_DATE:
                result = InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE;
                break;
            case Constants.EDIT_INPUT_TYPE_TIME:
                result = InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_TIME;
                break;
            case Constants.EDIT_INPUT_TYPE_POSITIVE_INTEGER:
                result = InputType.TYPE_CLASS_NUMBER;
                break;
            case Constants.EDIT_INPUT_TYPE_INTEGER:
                result = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED;
                break;
            case Constants.EDIT_INPUT_TYPE_POSITIVE_DECIMAL:
                result = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
                break;
            case Constants.EDIT_INPUT_TYPE_DECIMAL:
                result = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL;
                break;
            case Constants.EDIT_INPUT_TYPE_NUMBER_PASSWORD:
                result = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD;
                break;
            case Constants.EDIT_INPUT_TYPE_PHONE:
                result = InputType.TYPE_CLASS_PHONE;
                break;
            default:
                result = InputType.TYPE_CLASS_TEXT;
                break;

        }
        return result;
    }

    public static String getMimeTypeStringFromType(int type) {
        String mime = Constants.MIME_ALL;
        switch (type) {
            case Constants.FILE_TYPE_IMAGE:
                mime = Constants.MIME_IMAGE;
                break;
            case Constants.FILE_TYPE_VIDEO:
                mime = Constants.MIME_VIDEO;
                break;
            default:
                mime = Constants.MIME_ALL;
                break;
        }
        return mime;
    }

    public static String getUuid() {
        String uuid = UUID.randomUUID().toString();
        uuid = trimString(uuid);
        return uuid;
    }

    public static String getCurrentYearMonthString() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        String s = getCanonicalYearMonthString(year, month);
        return s;
    }

    public static String getCurrentYearMonthDayString() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        String s = getCanonicalYearMonthDayString(year, month, day);
        return s;
    }

    public static String getHourMinSecString() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR);
        int min = now.get(Calendar.MINUTE);
        int sec = now.get(Calendar.SECOND);
        String s = getCanonicalHourMinuteSecondString(hour, min, sec);
        return s;
//        return hour + ":" + min + ":" + sec;
    }


    public static String getCurrentYearString() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        return String.valueOf(year);
    }

    public static int getCurrentYearInt() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        return year;
    }

    public static int getCurrentYearMonth() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;


        // System.out.println(year + " 年 " + month + " 月");
        return month;
    }

    public static int getCurrentQuarterInt() {
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.YEAR);
        int quarter = 0;
        if (month >= 0 && month < 3) {
            quarter = 0;
        } else if (month >= 3 && month < 6) {
            quarter = 1;
        } else if (month >= 6 && month < 9) {
            quarter = 2;
        } else if (month >= 9 && month < 12) {
            quarter = 3;
        }
        return quarter;
    }

    public static int getStartMonthIntFromQuarter(int quarter) {
        int result = 0;
        if (quarter == 0) {
            result = 0;
        } else if (quarter == 1) {
            result = 3;
        } else if (quarter == 2) {
            result = 6;
        } else if (quarter == 3) {
            result = 9;
        } else {
            throw new IllegalArgumentException("quarter index is invalid");
        }
        return result;
    }

    public static int getEndMonthIntFromQuarter(int quarter) {
        int result = 0;
        if (quarter == 0) {
            result = 2;
        } else if (quarter == 1) {
            result = 5;
        } else if (quarter == 2) {
            result = 8;
        } else if (quarter == 3) {
            result = 11;
        } else {
            throw new IllegalArgumentException("quarter index is invalid");
        }
        return result;
    }

    public static void showDateSelectDialog(Context context, String initialString, String tag, DatePickerDialog.OnDateSetListener callback) {
        Calendar now = Calendar.getInstance();
        int[] ymdArray = getYearMonthDayFromCanonicalString(initialString);
        int year = now.get(Calendar.YEAR);
//        int month = now.get(Calendar.MONTH) - 1;
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        if (ymdArray != null && ymdArray.length == 3) {
            year = ymdArray[0];
            month = ymdArray[1] - 1;
            day = ymdArray[2];
        }
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                callback,
                year,
                month,
                day
        );
        dpd.dismissOnPause(false);
        dpd.showYearPickerFirst(false);
        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
        dpd.setAccentColor(context.getResources().getColor(R.color.colorTheme));
//        dpd.setAccentColor(Color.parseColor("#9C27B0"));
        dpd.setTitle(context.getResources().getString(R.string.please_select_date));
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.WEEK_OF_MONTH, -1);
        Calendar date3 = Calendar.getInstance();
        date3.add(Calendar.WEEK_OF_MONTH, 1);
        Calendar[] days = {date1, date2, date3};
        dpd.setHighlightedDays(days);
        dpd.show(((Activity) context).getFragmentManager(), tag);
//        dpd.show(getChildFragmentManager(), "Datepickerdialog1");
        Calendar calendar = Calendar.getInstance();
        dpd.setMinDate(calendar);
    }

    public static void showDateSelectMinDate(Context context, String initialString, String tag, DatePickerDialog.OnDateSetListener callback) {
        Calendar now = Calendar.getInstance();
        int[] ymdArray = getYearMonthDayFromCanonicalString(initialString);
        int year = now.get(Calendar.YEAR);
//        int month = now.get(Calendar.MONTH) - 1;
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        if (ymdArray != null && ymdArray.length == 3) {
            year = ymdArray[0];
            month = ymdArray[1] - 1;
            day = ymdArray[2];
        }
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                callback,
                year,
                month,
                day
        );
        dpd.dismissOnPause(false);
        dpd.showYearPickerFirst(false);
        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
        dpd.setAccentColor(context.getResources().getColor(R.color.colorTheme));
//        dpd.setAccentColor(Color.parseColor("#9C27B0"));
        dpd.setTitle(context.getResources().getString(R.string.please_select_date));
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.WEEK_OF_MONTH, -1);
        Calendar date3 = Calendar.getInstance();
        date3.add(Calendar.WEEK_OF_MONTH, 1);
        Calendar[] days = {date1, date2, date3};
        dpd.setHighlightedDays(days);
        dpd.show(((Activity) context).getFragmentManager(), tag);
//        dpd.show(getChildFragmentManager(), "Datepickerdialog1");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String sNowDate = formatter.format(curDate);
        int[] ymdArray1 = getYearMonthDayFromCanonicalString(sNowDate);
        calendar.set(ymdArray1[0], ymdArray1[1] - 1, ymdArray1[2]);
        dpd.setMinDate(calendar);

    }


    public static void showTimeSelectDialog(Context context, String initialString, String tag, TimePickerDialog.OnTimeSetListener callback) {
        Calendar calendar = Calendar.getInstance();
        int[] hmArray = getHourMinuteFromCanonicalString(initialString);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        if (hmArray != null && hmArray.length == 2) {
            hour = hmArray[0];
            minute = hmArray[1];
        }
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                callback,
                hour,
                minute,
                false
        );
        tpd.dismissOnPause(false);
        tpd.setAccentColor(context.getResources().getColor(R.color.colorTheme));
        tpd.setTitle(context.getResources().getString(R.string.please_select_time));
        tpd.show(((Activity) context).getFragmentManager(), tag);
    }

//        public static void showDateSelectDialog(Context context, String initialString, String tag, DatePickerDialog.OnDateSetListener callback) {
//        Calendar now = Calendar.getInstance();
//        int[] ymdArray = getYearMonthDayFromCanonicalString(initialString);
//        int year = now.get(Calendar.YEAR);
//        int month = now.get(Calendar.MONTH) - 1;
//        int day = now.get(Calendar.DAY_OF_MONTH);
//        if (ymdArray != null && ymdArray.length == 3) {
//            year = ymdArray[0];
//            month = ymdArray[1] - 1;
//            day = ymdArray[2];
//        }
//        DatePickerDialog dpd = DatePickerDialog.newInstance(
//                callback,
//                year,
//                month,
//                day
//        );
//        dpd.dismissOnPause(false);
////        dpd.showYearPickerFirst(true);
////        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
//        dpd.setAccentColor(context.getResources().getColor(R.color.colorTheme));
////        dpd.setAccentColor(Color.parseColor("#9C27B0"));
////        dpd.setTitle(context.getResources().getString(R.string.please_select_date));
////        Calendar date1 = Calendar.getInstance();
////        Calendar date2 = Calendar.getInstance();
////        date2.add(Calendar.WEEK_OF_MONTH, -1);
////        Calendar date3 = Calendar.getInstance();
////        date3.add(Calendar.WEEK_OF_MONTH, 1);
////        Calendar[] days = {date1, date2, date3};
////        dpd.setHighlightedDays(days);
//        dpd.show(((Activity) context).getFragmentManager(), tag);
////        dpd.show(getChildFragmentManager(), "Datepickerdialog1");
//    }
//
//    public static void showTimeSelectDialog(Context context, String initialString, String tag, TimePickerDialog.OnTimeSetListener callback){
//        Calendar calendar = Calendar.getInstance();
//        int[] hmArray = getHourMinuteFromCanonicalString(initialString);
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//        if (hmArray!=null&&hmArray.length==2){
//            hour=hmArray[0];
//            minute=hmArray[1];
//        }
//        TimePickerDialog tpd = TimePickerDialog.newInstance(
//                callback,
//                hour,
//                minute,
//                false
//        );
//        tpd.dismissOnPause(false);
//        tpd.setAccentColor(context.getResources().getColor(R.color.colorTheme));
//        tpd.setTitle(context.getResources().getString(R.string.please_select_time));
//        tpd.show(((Activity)context).getFragmentManager(), tag);
//    }


//    public static void showDateRangeSelectDialog(Context context, String beginString, String endString, String tag, com.borax12.materialdaterangepicker.date.DatePickerDialog.OnDateSetListener callback) {
//        Calendar now = Calendar.getInstance();
//        int[] ymdArrayBegin = getYearMonthDayFromCanonicalString(beginString);
//        int yearBegin = now.get(Calendar.YEAR);
//        int monthBegin = now.get(Calendar.MONTH) - 1;
//        int dayBegin = now.get(Calendar.DAY_OF_MONTH);
//        if (ymdArrayBegin != null && ymdArrayBegin.length == 3) {
//            yearBegin = ymdArrayBegin[0];
//            monthBegin = ymdArrayBegin[1] - 1;
//            dayBegin = ymdArrayBegin[2];
//        }
//
//        int[] ymdArrayEnd = getYearMonthDayFromCanonicalString(endString);
//        int yearEnd = now.get(Calendar.YEAR);
//        int monthEnd = now.get(Calendar.MONTH)-1;
//        int dayEnd = now.get(Calendar.DAY_OF_MONTH);
//        if (ymdArrayEnd!=null && ymdArrayEnd.length == 3){
//            yearEnd=ymdArrayEnd[0];
//            monthEnd = ymdArrayEnd[1]-1;
//            dayEnd = ymdArrayEnd[2];
//        }
//        com.borax12.materialdaterangepicker.date.DatePickerDialog dpd = com.borax12.materialdaterangepicker.date.DatePickerDialog.newInstance(
//                callback,
//                yearBegin,
//                monthBegin,
//                dayBegin,
//                yearEnd,
//                monthEnd,
//                dayEnd
//        );
//        dpd.dismissOnPause(false);
////        dpd.showYearPickerFirst(true);
////        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
//        dpd.setAccentColor(context.getResources().getColor(R.color.colorTheme));
////        dpd.setAccentColor(Color.parseColor("#9C27B0"));
//        dpd.setStartTitle(context.getResources().getString(R.string.please_select_start_date));
//        dpd.setEndTitle(context.getResources().getString(R.string.please_select_end_date));
////        Calendar date1 = Calendar.getInstance();
////        Calendar date2 = Calendar.getInstance();
////        date2.add(Calendar.WEEK_OF_MONTH, -1);
////        Calendar date3 = Calendar.getInstance();
////        date3.add(Calendar.WEEK_OF_MONTH, 1);
////        Calendar[] days = {date1, date2, date3};
////        dpd.setHighlightedDays(days);
//        dpd.show(((Activity) context).getFragmentManager(), tag);
////        dpd.show(getChildFragmentManager(), "Datepickerdialog1");
//    }
//    public static void showTimeRangeSelectDialog(Context context, String beginString, String endString, String tag, com.borax12.materialdaterangepicker.time.TimePickerDialog.OnTimeSetListener callback) {
//        Calendar now = Calendar.getInstance();
//        int[] hmArrayBegin = getHourMinuteFromCanonicalString(beginString);
//        int hourBegin = now.get(Calendar.HOUR_OF_DAY);
//        int minuteBegin = now.get(Calendar.MINUTE) ;
//        if (hmArrayBegin != null && hmArrayBegin.length == 2) {
//            hourBegin = hmArrayBegin[0];
//            minuteBegin = hmArrayBegin[1];
//        }
//
//        int[] hmArrayEnd = getHourMinuteFromCanonicalString(endString);
//        int hourEnd = now.get(Calendar.HOUR_OF_DAY);
//        int minuteEnd = now.get(Calendar.MINUTE);
//        if (hmArrayEnd!=null && hmArrayEnd.length == 2){
//            hourEnd=hmArrayEnd[0];
//            minuteEnd = hmArrayEnd[1];
//        }
//        com.borax12.materialdaterangepicker.time.TimePickerDialog tpd = com.borax12.materialdaterangepicker.time.TimePickerDialog.newInstance(
//                callback,
//                hourBegin,
//                minuteBegin,
//                true,
//                hourEnd,
//                minuteEnd
//        );
//        tpd.dismissOnPause(false);
////        dpd.showYearPickerFirst(true);
////        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
//        tpd.setAccentColor(context.getResources().getColor(R.color.colorTheme));
////        dpd.setAccentColor(Color.parseColor("#9C27B0"));
//        String startIndicator = context.getResources().getString(R.string.please_select_start_time);
//        String endIndicator = context.getResources().getString(R.string.please_select_end_time);
//        tpd.setTabIndicators(startIndicator, endIndicator);
//        tpd.setTitle(Constants.EMPTY_STRING);
////        Calendar date1 = Calendar.getInstance();
////        Calendar date2 = Calendar.getInstance();
////        date2.add(Calendar.WEEK_OF_MONTH, -1);
////        Calendar date3 = Calendar.getInstance();
////        date3.add(Calendar.WEEK_OF_MONTH, 1);
////        Calendar[] days = {date1, date2, date3};
////        dpd.setHighlightedDays(days);
//        tpd.show(((Activity) context).getFragmentManager(), tag);
////        dpd.show(getChildFragmentManager(), "Datepickerdialog1");
//    }

    public static String getCommaSeparatedForcedIntString(String s){
        String intString = getForcedIntStringFromDoubleString(s);
        String str = getCommaSeparatedIntString(intString);
        return str;
    }

    public static String getCommaSeparatedIntString(String s){
        int i = getIntFromString(s);
        String result = COMMA_SEPARATED_INT_FORMATTER.format(i);
        return result;
    }

    public static String getForcedIntStringFromDoubleString(String s) {
        double f = getDoubleValueFromString(s);
        int i = (int) f;
        String result = String.valueOf(i);
        return result;
    }

    public static String getForcedIntStringFromDouble(Double f) {
        if (f == null) {
            return Constants.EMPTY_STRING;
        }
        String s = String.valueOf(f);
        return getForcedIntStringFromDoubleString(s);
    }

    public static String getIntStringFromString(String s) {
        int i = getIntFromString(s);
        String result = String.valueOf(i);
        return result;
    }

    public static int getIntFromString(String s) {
        s = Util.trimString(s);
        int result = 0;
        try {
            result = Integer.parseInt(s, 10);
        } catch (NumberFormatException exception) {
            result = 0;
        }
        return result;
    }

    public static float getFloatFromString(String s) {
        s = Util.trimString(s);
        float result = 0;
        try {
            result = Float.parseFloat(s);
        } catch (NumberFormatException exception) {
            result = 0;
        }
        return result;
    }

    public static float getFloatFromPercentString(String s) {
        s = trimString(s);
        s = s.replaceAll(Constants.PERCENT_STRING, Constants.EMPTY_STRING);
        s = s.replaceAll(Constants.COMMA_STRING, Constants.EMPTY_STRING);
        float result = getFloatFromString(s);
        return result;
    }

    public static double getDoubleValueFromString(String s) {
        s = Util.trimString(s);
        double result = 0;
        try {
            result = Double.parseDouble(s);
        } catch (NumberFormatException exception) {
            result = 0.00;
        }
        return result;
    }

    public static double getDoubleFromString(String s) {
        s = Util.trimString(s);
        double result = 0;
        try {
            result = Double.parseDouble(Util.getStringdouble(s));
        } catch (NumberFormatException exception) {
            result = 0.00;
        }
        return result;
    }

    public static String getStringdouble(String s) {
        String result = "0.00";
        DecimalFormat df = new DecimalFormat("######0.00");
        try {
            result = df.format(Double.parseDouble(s));
        } catch (NumberFormatException exception) {
            result = "0.00";
        }
        return result;
    }

    public static String getCanonicalYearMonthString(int year, int month) {
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append(Constants.HYPHEN_STRING);
        if (month < 10) {
            sb.append(Constants.ZERO_STRING);
        }
        sb.append(month);
        return sb.toString();
    }

    public static String getCanonicalYearMonthDayString(int year, int month, int day) {
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append(Constants.HYPHEN_STRING);
        if (month < 10) {
            sb.append(Constants.ZERO_STRING);
        }
        sb.append(month);
        sb.append(Constants.HYPHEN_STRING);
        if (day < 10) {
            sb.append(Constants.ZERO_STRING);
        }
        sb.append(day);
        return sb.toString();
    }

    public static int[] getYearMonthDayFromCanonicalString(String s) {
        s = Util.trimString(s);
        String[] sArray = s.split(Constants.HYPHEN_STRING, 3);
        if (sArray != null && sArray.length == 3) {
            int year = getIntFromString(sArray[0]);
            int month = getIntFromString(sArray[1]);
            int day = getIntFromString(sArray[2]);
            return new int[]{year, month, day};
        } else {
            return null;
        }
    }

    public static int[] getYearMonthFromCanonicalString(String s) {
        s = Util.trimString(s);
        String[] sArray = s.split(Constants.HYPHEN_STRING, 2);
        if (sArray != null && sArray.length == 2) {
            int year = getIntFromString(sArray[0]);
            int month = getIntFromString(sArray[1]);
            return new int[]{year, month};
        } else {
            return null;
        }
    }

    public static String getCanonicalHourMinuteString(int hour, int minute) {
        StringBuilder sb = new StringBuilder();
        if (hour < 10) {
            sb.append(Constants.ZERO_STRING);
        }
        sb.append(hour);
        sb.append(Constants.COLON_STRING);
        if (minute < 10) {
            sb.append(Constants.ZERO_STRING);
        }
        sb.append(minute);
        return sb.toString();
    }

    public static String getCanonicalHourMinuteSecondString(int hour, int minute, int second) {
        StringBuilder sb = new StringBuilder();
        if (hour < 10) {
            sb.append(Constants.ZERO_STRING);
        }
        sb.append(hour);
        sb.append(Constants.COLON_STRING);
        if (minute < 10) {
            sb.append(Constants.ZERO_STRING);
        }
        sb.append(minute);
        sb.append(Constants.COLON_STRING);
        if (second < 10) {
            sb.append(Constants.ZERO_STRING);
        }
        sb.append(second);
        return sb.toString();
    }


    public static String getCanonicalHourString(String hour) {
        hour = trimString(hour);
        int i = getIntFromString(hour);
        String result = getCanonicalHourStringFromInteger(i);
        return result;
    }

    public static String getCanonicalMinuteString(String minute) {
        minute = trimString(minute);
        int i = getIntFromString(minute);
        String result = getCanonicalMinuteStringFromInteger(i);
        return result;
    }

    public static int[] getHourMinuteFromCanonicalString(String s) {
        s = Util.trimString(s);
        String[] sArray = s.split(Constants.COLON_STRING, 2);
        if (sArray != null && sArray.length == 2) {
            int hour = getIntFromString(sArray[0]);
            int minute = getIntFromString(sArray[1]);
            return new int[]{hour, minute};
        } else {
            return null;
        }
    }

    public static String getCanonicalStringFromHourMinute(int hour, int minute) {
        String hourString = getCanonicalHourStringFromInteger(hour);
        String minuteString = getCanonicalMinuteStringFromInteger(minute);
        String result = hourString + Constants.COLON_STRING + minuteString;
        return result;
    }

    public static int[] getHourMinuteIntArrayFromString(String s) {
        String[] stringArray = getCanonicalHourMinuteStringArray(s);
        int[] result = new int[2];
        result[0] = getIntFromString(stringArray[0]);
        result[1] = getIntFromString(stringArray[1]);
        return result;
    }

    /**
     * 从格式为##:##的时间获取合法字符串
     *
     * @param s
     * @return
     */
    public static String[] getCanonicalHourMinuteStringArray(String s) {
        s = trimString(s);
        String[] array = s.split(Constants.COLON_STRING, 2);
        String[] result = new String[2];
        if (array != null) {
            int length = array.length;
            if (length == 0) {
                result[0] = Constants.DOUBLE_ZERO_STRING;
                result[1] = Constants.DOUBLE_ZERO_STRING;
            } else if (length == 1) {
                result[0] = getCanonicalHourString(array[0]);
                result[1] = Constants.DOUBLE_ZERO_STRING;
            } else {
                result[0] = getCanonicalHourString(array[0]);
                result[1] = getCanonicalMinuteString(array[1]);
            }
        } else {
            result[0] = Constants.DOUBLE_ZERO_STRING;
            result[1] = Constants.DOUBLE_ZERO_STRING;
        }
        return result;
    }


    /**
     * 根据属性名获取属性值
     */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
//            LogUtil.i(e.getMessage());
            return null;
        }
    }

    /**
     * 获取属性名数组
     */
    public static String[] getFiledNameArray(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
//        Field[] fields=o.getClass().getFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
//            LogUtil.i(String.valueOf(fields[i].getType()));
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    public static void showToast(Context context, String msg) {
        ToastUtil.show(context, msg);
    }

    public static void addDividerToRecyclerView(Context context, RecyclerView rv, boolean isVertical) {
        int orientation = isVertical ? DividerItemDecoration.VERTICAL : DividerItemDecoration.HORIZONTAL;
        rv.addItemDecoration(new DividerItemDecoration(context, orientation));
    }

    public static String getRatingStringFromInt(int rating) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rating; ++i) {
            sb.append("☆");
        }
        return sb.toString();
    }

    public static void makeApplicationShowInLockedMode(Activity activity, boolean show) {
        if (activity != null) {
            int flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
            if (show) {
                activity.getWindow().addFlags(flags);
            } else {
                activity.getWindow().clearFlags(flags);
            }
        }
    }


    public static boolean isLongitudeValid(String longitude) {
        float longitudeFloat = getFloatFromString(longitude);
        if (longitudeFloat < Constants.MIN_LONGITUDE || longitudeFloat > Constants.MAX_LONGITUDE) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isLatitudeValid(String latitude) {
        float latitudeFloat = getFloatFromString(latitude);
        if (latitudeFloat < Constants.MIN_LATITUDE || latitudeFloat > Constants.MAX_LATITUDE) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * marker点击时跳动一下
     */
    public static void jumpPoint(final Marker marker, AMap aMap, final LatLng latLng, final Runnable runnable) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = aMap.getProjection();
        Point startPoint = proj.toScreenLocation(latLng);
        startPoint.offset(0, -100);
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = Constants.MAP_MARKER_JUMP_DURATION;

        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * latLng.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * latLng.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    handler.postDelayed(this, 10);
                } else {
                    marker.showInfoWindow();
                    if (runnable!=null){
                        runnable.run();
                    }
                }
            }
        });
    }
//
//    //获取两坐标点之间的距离(单位为米)
//    public static float getDistanceBetweenTwoCoordinates(float firstLongitude, float firstLatitude,
//                                                         float secondLongitude, float secondLatitude) {
//        LatLng firstLatLng = new LatLng(firstLatitude, firstLongitude);
//        LatLng secondLatLng = new LatLng(secondLatitude, secondLongitude);
//        float result = AMapUtils.calculateLineDistance(firstLatLng, secondLatLng);
//        return result;
//    }

    public static String getDistanceStringFromFloat(float distance) {
        String distanceString = "";
        if (distance < 1000) {
            distanceString = (int) distance + Constants.METER;
        } else {
            int value = (int) (distance / 1000);
            distanceString = value + Constants.KILOMETER;
        }
        return distanceString;
    }

    public static String getDecimalDistanceStringFromFloat(float distance) {
        String distanceString = "";
        if (distance < 1000) {
            distanceString = (int) distance + Constants.METER;
        } else {
            float value = (distance / 1000);
            String valueString = String.format(Locale.CHINA, "%.1f", value);
            distanceString = valueString + Constants.KILOMETER;
        }
        return distanceString;
    }

//    public static String getDistanceStringBetweenTwoCoordinates(float firstLongitude, float firstLatitude,
//                                                                float secondLongitude, float secondLatitude) {
//        float floatDistance = getDistanceBetweenTwoCoordinates(firstLongitude, firstLatitude, secondLongitude, secondLatitude);
//        String s = getDistanceStringFromFloat(floatDistance);
//        return s;
//    }
//
//    public static String getDecimalDistanceStringBetweenTwoCoordinates(float firstLongitude, float firstLatitude,
//                                                                       float secondLongitude, float secondLatitude) {
//        float floatDistance = getDistanceBetweenTwoCoordinates(firstLongitude, firstLatitude, secondLongitude, secondLatitude);
//        String s = getDecimalDistanceStringFromFloat(floatDistance);
//        return s;
//    }

    public static int getGenderImageResource(int gender) {
        int result = R.mipmap.male_blue;
        if (gender == Constants.GENDER_MALE) {
            result = R.mipmap.male_blue;
        } else if (gender == Constants.GENDER_FEMALE) {
            result = R.mipmap.female_blue;
        }
        return result;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 当前应用程序是否在后台运行
     *
     * @param context
     * @return
     */
    public static boolean isCurrentAppBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    LogUtil.i("后台运行:" + appProcess.processName);
                    return true;
                } else {
                    LogUtil.i("前台运行或没有运行:" + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isAppVisibleToUser(Context context) {
        String packageName = context.getPackageName();
        String topActivityClassName = getTopActivityName(context);
        LogUtil.i("top activity name:" + topActivityClassName);
        String topPackageName = getTopPackageName(context);
        LogUtil.i("top package name:" + topPackageName);

        if (packageName != null && topActivityClassName != null && topActivityClassName.startsWith(packageName)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAppRunning(Context context) {
        String currentAppPackageName = context.getPackageName();
        ActivityManager activityManager =
                (ActivityManager) (context.getSystemService(android.content.Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(Constants.RUNNING_TASK_SIZE);
        if (runningTaskInfos != null) {
            for (int i = 0; i < runningTaskInfos.size(); ++i) {
                ComponentName f = runningTaskInfos.get(i).topActivity;
                String packageName = f.getPackageName();
                if (currentAppPackageName.equalsIgnoreCase(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

//    public static boolean isCurrentChatActivity(Context context) {
//        String activityName = getTopActivityName(context);
////        if (!TextUtils.isEmpty(activityName) && Constants.CHAT_ACTIVITY_NAME.equalsIgnoreCase(activityName)) {
//        if (!TextUtils.isEmpty(activityName) && Constants.MESSAGE_LIST_ACTIVITY_NAME.equalsIgnoreCase(activityName)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public static String getCurrentApplicationTopActivityName(Context context) {
        if (context != null) {
            return getTopActivityNameByPackageName(context, context.getPackageName());
        } else {
            return Constants.EMPTY_STRING;
        }
    }

    public static String getTopActivityNameByPackageName(Context context, String packageName) {
        if (StringUtils.isBlank(packageName)) {
            return Constants.EMPTY_STRING;
        } else {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(Integer.MAX_VALUE);
            String result = Constants.EMPTY_STRING;
            for (int i = 0; i < taskList.size(); ++i) {
                ActivityManager.RunningTaskInfo rti = taskList.get(i);
                String tempPackageName = rti.topActivity.getPackageName();
                LogUtil.i("index:" + i + " packageName:" + tempPackageName);
                if (packageName.equals(tempPackageName)) {
                    result = rti.topActivity.getClassName();
                    break;
                }
            }
            return result;
        }
    }

    public static String getTopPackageName(Context context) {
        String topPackageClassName = null;
        ActivityManager activityManager =
                (ActivityManager) (context.getSystemService(android.content.Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
        if (runningTaskInfos != null) {
            ComponentName f = runningTaskInfos.get(0).topActivity;
            topPackageClassName = f.getPackageName();
        }
        return topPackageClassName;
    }

    public static String getTopActivityName(Context context) {
        String topActivityClassName = null;
        ActivityManager activityManager =
                (ActivityManager) (context.getSystemService(android.content.Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
        if (runningTaskInfos != null) {
            ComponentName f = runningTaskInfos.get(0).topActivity;
            topActivityClassName = f.getClassName();
        }
        return topActivityClassName;
    }

    public static String getLauncherActivityNameByPackageName(Context context, String packageName) {
        String className = Constants.EMPTY_STRING;
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);//android.intent.action.MAIN
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);//android.intent.category.LAUNCHER
        resolveIntent.setPackage(packageName);
        List<ResolveInfo> resolveinfoList = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            className = resolveinfo.activityInfo.name;
        }
        LogUtil.i("launcher activity class name:" + className);
        return className;
    }

    public static void bringApplicationToForegroundAndVisibleToUser(Context context) {
        if (context == null) {
            return;
        }
        bringApplicationByPackageNameToForegroundAndVisibleToUser(context, context.getPackageName());
    }

    public static void bringApplicationByPackageNameToForegroundAndVisibleToUser(Context context, String packageName) {
        if (context == null || StringUtils.isBlank(packageName)) {
            return;
        }
        String startupActivityClassName = Constants.EMPTY_STRING;
        startupActivityClassName = getLauncherActivityNameByPackageName(context, packageName);
        Class clazz = null;
        try {
            clazz = Class.forName(startupActivityClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            clazz = null;
        }
        if (clazz == null) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(context, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(intent);
    }

    public static void bringApplicationToFront(Context context) {
        String topActivityName = Util.getCurrentApplicationTopActivityName(context);
        Class clazz = null;
        try {
            clazz = Class.forName(topActivityName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            clazz = null;
        }
        if (clazz != null) {
            LogUtil.i("top activity class is not null:" + clazz.getName());
            Identity.shouldReadIdCard = false;
            Intent intent = new Intent(context, clazz);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        } else {
            LogUtil.i("top activity class is null");
        }

    }

    public static void bringApplicationToForeground(Context context) {
        Class clazz = null;
        if (LoginActivityNew.HAS_LOGGED_IN) {
            LogUtil.i("LoginActivityNew.HAS_LOGGED_IN:" + LoginActivityNew.HAS_LOGGED_IN + " ,WorkbenchActivity.class");
            clazz = WorkbenchActivity.class;
        } else {
            LogUtil.i("LoginActivityNew.HAS_LOGGED_IN:" + LoginActivityNew.HAS_LOGGED_IN + " ,LoginActivityNew.class");
            clazz = LoginActivityNew.class;
        }
        Intent intent = new Intent(context, clazz);
//        if (LoginActivityNew.HAS_LOGGED_IN){
//            intent.putExtra(Constants.WORKBENCH_ON_NEW_INTENT_INITIAL_INDEX_KEY, Constants.WORKBENCH_ONLINE_CONSULTATION);
//        }
        context.startActivity(intent);
    }

//    public static void bringApplicationToForegroundFromChatMessageNotificationClick(Context context, String fromId,
//                                                                                    String toId, String content) {
//        Class clazz = null;
//        if (LoginActivityNew.HAS_LOGGED_IN) {
//            LogUtil.i("LoginActivityNew.HAS_LOGGED_IN:" + LoginActivityNew.HAS_LOGGED_IN + " ,WorkbenchActivity.class");
//            clazz = WorkbenchActivity.class;
//            Intent workbenchIntent = new Intent(context, clazz);
//            workbenchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            workbenchIntent.putExtra(Constants.WORKBENCH_ON_NEW_INTENT_INITIAL_INDEX_KEY, Constants.WORKBENCH_ONLINE_CONSULTATION);
//            Intent chatIntent = new Intent(context, ChatActivity.class);
////            chatIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            chatIntent.putExtra(Constants.MESSAGE_FROM_USER_ID_KEY, fromId);
//            chatIntent.putExtra(Constants.MESSAGE_TO_USER_ID_KEY, toId);
//            chatIntent.putExtra(Constants.MESSAGE_CONTENT_KEY, content);
//
//            Intent[] intentArray = new Intent[]{workbenchIntent, chatIntent};
//            context.startActivities(intentArray);
//
//        } else {
//            LogUtil.i("LoginActivityNew.HAS_LOGGED_IN:" + LoginActivityNew.HAS_LOGGED_IN + " ,LoginActivityNew.class");
//            clazz = LoginActivityNew.class;
//            Intent loginIntent = new Intent(context, clazz);
//            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(loginIntent);
//        }
//    }

//    public static void bringApplicationToForegroundFromChatMessageNotificationClick2(Context context, String title,
//                                                                                     String targetId, String appkey) {
//        Class clazz = null;
//        if (LoginActivityNew.HAS_LOGGED_IN) {
//            LogUtil.i("LoginActivityNew.HAS_LOGGED_IN:" + LoginActivityNew.HAS_LOGGED_IN + " ,WorkbenchActivity.class");
//            clazz = WorkbenchActivity.class;
//            Intent workbenchIntent = new Intent(context, clazz);
//            workbenchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            workbenchIntent.putExtra(Constants.WORKBENCH_ON_NEW_INTENT_INITIAL_INDEX_KEY, Constants.WORKBENCH_ONLINE_CONSULTATION);
//            Intent chatIntent = new Intent(context, MessageListActivity.class);
////            chatIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            chatIntent.putExtra(Constants.CONV_TITLE, title);
//            chatIntent.putExtra(Constants.TARGET_ID, targetId);
//            chatIntent.putExtra(Constants.TARGET_APP_KEY, appkey);
//
//            Intent[] intentArray = new Intent[]{workbenchIntent, chatIntent};
//            context.startActivities(intentArray);
//
//        } else {
//            LogUtil.i("LoginActivityNew.HAS_LOGGED_IN:" + LoginActivityNew.HAS_LOGGED_IN + " ,LoginActivityNew.class");
//            clazz = LoginActivityNew.class;
//            Intent loginIntent = new Intent(context, clazz);
//            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(loginIntent);
//        }
//    }

    public static void runApplicationFromScratch(Context context, String packageName) {
        PackageInfo pi;
        try {
            pi = context.getPackageManager().getPackageInfo(packageName, 0);
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.setPackage(pi.packageName);
            PackageManager pManager = context.getPackageManager();
            List apps = pManager.queryIntentActivities(
                    resolveIntent, 0);

            ResolveInfo ri = (ResolveInfo) apps.iterator().next();
            if (ri != null) {
                packageName = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                ComponentName cn = new ComponentName(packageName, className);
                intent.setComponent(cn);
                context.startActivity(intent);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void showNotificationAndClickHandlerIsResumeApplication(Context context, String title, String msg) {
        NotificationManager notificationManager = (
                NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);
        CharSequence contentTitle = title; // 通知栏标题
        CharSequence contentText = msg; // 通知栏内容
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(context, IntroductionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        Context mContext = context.getApplicationContext();
        PendingIntent contextIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
        Bitmap largeBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.r_app_icon);
        Notification.Builder myBuilder = new Notification.Builder(context);
        myBuilder.setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSubText("")
                .setTicker(Constants.HINT_YOU_RECEIVE_NEW_MESSAGE)
                //设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示
                .setSmallIcon(R.mipmap.r_app_icon)
                .setLargeIcon(largeBitmap)
                //设置默认声音和震动
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setAutoCancel(true)//点击后取消
                .setWhen(System.currentTimeMillis())//设置通知时间
                .setPriority(Notification.PRIORITY_HIGH)//高优先级
                //                .setVisibility(Notification.VISIBILITY_PUBLIC)
                //android5.0加入了一种新的模式Notification的显示等级，共有三种：
                //VISIBILITY_PUBLIC  只有在没有锁屏时会显示通知
                //VISIBILITY_PRIVATE 任何情况都会显示通知
                //VISIBILITY_SECRET  在安全锁和没有锁屏的情况下显示通知
                .setContentIntent(contextIntent);  //3.关联PendingIntent
        Notification myNotification = myBuilder.build();
        //4.通过通知管理器来发起通知，ID区分通知
        notificationManager.notify(Constants.NOTIFICATION_ID, myNotification);
    }

    /**
     * 获取应用程序名称
     */
    public static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void showNotification(Context context, String title, String msg, String pushNotificationIntent, String userUuid) {
        boolean whetherReceiveNotification = Util.getWhetherEnablePushFlag(context);
        if (whetherReceiveNotification) {
            ++JPushNotificationId;
            // 创建一个NotificationManager的引用
            NotificationManager notificationManager = (
                    NotificationManager) context.getSystemService(
                    Context.NOTIFICATION_SERVICE);

//        // 定义Notification的各种属性
//        Notification notification = new Notification(
//                R.mipmap.icon,Constants.APP_NAME,
//                System.currentTimeMillis());
//        // 将此通知放到通知栏的"Ongoing"即"正在运行"组中
//        notification.flags |= Notification.FLAG_ONGOING_EVENT;
//        // 点击后自动清除Notification
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
//        notification.flags |= Notification.FLAG_SHOW_LIGHTS;
//        notification.defaults = Notification.DEFAULT_LIGHTS;
//        notification.ledARGB = Color.BLUE;
//        notification.ledOnMS = 5000;

//        Intent notificationIntent = new Intent(context, clazz);
////        notificationIntent.setAction(Intent.ACTION_MAIN);
////        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        PendingIntent contentIntent = PendingIntent.getActivity(
//                context, 0, notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
//        notification.setLatestEventInfo(
//                context, contentTitle, contentText, contentIntent);
//        // 把Notification传递给NotificationManager
//        notificationManager.notify(0, notification);

            if(title==null || TextUtils.isEmpty(title)){
                title=getAppName(context);
            }

            // 设置通知的事件消息
            CharSequence contentTitle = title; // 通知栏标题
            CharSequence contentText = msg; // 通知栏内容

            Class clazz = null;
            if (LoginActivityNew.HAS_LOGGED_IN) {
                LogUtil.i("LoginActivityNew.HAS_LOGGED_IN:" + LoginActivityNew.HAS_LOGGED_IN + " ,WorkbenchActivity.class");
                clazz = WorkbenchActivity.class;
            } else {
                LogUtil.i("LoginActivityNew.HAS_LOGGED_IN:" + LoginActivityNew.HAS_LOGGED_IN + " ,LoginActivityNew.class");
                clazz = LoginActivityNew.class;
            }
            Intent notificationIntent = new Intent(context, clazz);
//            if (LoginActivityNew.HAS_LOGGED_IN) {
            notificationIntent.putExtra(Constants.WORKBENCH_ON_NEW_INTENT_INITIAL_INDEX_KEY, Constants.WORKBENCH_INDEX);
            notificationIntent.putExtra(Constants.WORKBENCH_PUSH_NOTIFICATION_EXIST_KEY, true);
            notificationIntent.putExtra(Constants.WORKBENCH_PUSH_NOTIFICATION_INTENT_KEY, pushNotificationIntent);
            notificationIntent.putExtra(Constants.WORKBENCH_PUSH_NOTIFICATION_USER_UUID_KEY, userUuid);
//            }
//        notificationIntent.setAction(Intent.ACTION_MAIN);
//        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            PendingIntent pi = PendingIntent.getActivity(
                    context,
                    JPushNotificationId,
                    notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );

            //2.通过Notification.Builder来创建通知
            Bitmap largeBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.r_app_icon_new);
            Notification.Builder myBuilder = new Notification.Builder(context);
            myBuilder.setContentTitle(contentTitle)
                    .setContentText(contentText)
                    .setSubText("")
                    .setTicker(Constants.HINT_YOU_RECEIVE_NEW_MESSAGE)
                    //设置状态栏中的小图片，尺寸一般建议在24×24，这个图片同样也是在下拉状态栏中所显示
                    .setSmallIcon(R.mipmap.r_app_icon_new)
                    .setLargeIcon(largeBitmap)
                    //设置默认声音和震动
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                    .setAutoCancel(true)//点击后取消
                    .setWhen(System.currentTimeMillis())//设置通知时间
                    .setPriority(Notification.PRIORITY_HIGH)//高优先级
                    //                .setVisibility(Notification.VISIBILITY_PUBLIC)
                    //android5.0加入了一种新的模式Notification的显示等级，共有三种：
                    //VISIBILITY_PUBLIC  只有在没有锁屏时会显示通知
                    //VISIBILITY_PRIVATE 任何情况都会显示通知
                    //VISIBILITY_SECRET  在安全锁和没有锁屏的情况下显示通知
                    .setContentIntent(pi);  //3.关联PendingIntent
            Notification myNotification = myBuilder.build();
            //4.通过通知管理器来发起通知，ID区分通知
            notificationManager.notify(JPushNotificationId, myNotification);
        } else {
        }
    }

    public static int getWorkbenchIndexFromIntent(Intent intent) {
        if (intent != null) {
            int result = intent.getIntExtra(Constants.WORKBENCH_ON_NEW_INTENT_INITIAL_INDEX_KEY, Constants.WORKBENCH_INDEX);
            return result;
        } else {
            return Constants.WORKBENCH_INDEX;
        }
    }

    public static boolean getWorkbenchPushNotificationExistFromIntent(Intent intent) {
        if (intent != null) {
            boolean result = intent.getBooleanExtra(Constants.WORKBENCH_PUSH_NOTIFICATION_EXIST_KEY, false);
            return result;
        } else {
            return false;
        }
    }

    public static String getWorkbenchPushNotificationIntentFromIntent(Intent intent) {
        if (intent != null) {
            String result = intent.getStringExtra(Constants.WORKBENCH_PUSH_NOTIFICATION_INTENT_KEY);
            result = trimString(result);
            return result;
        } else {
            return Constants.EMPTY_STRING;
        }
    }

    public static String getWorkbenchPushNotificationUserUuidFromIntent(Intent intent) {
        if (intent != null) {
            String result = intent.getStringExtra(Constants.WORKBENCH_PUSH_NOTIFICATION_USER_UUID_KEY);
            result = trimString(result);
            return result;
        } else {
            return Constants.EMPTY_STRING;
        }
    }


    public static String getUrlEncodedString(String s) {
        s = trimString(s);
        String encodedString = Constants.EMPTY_STRING;
        try {
            encodedString = URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            encodedString = Constants.EMPTY_STRING;
        }
        return encodedString;
    }

    public static boolean getAliasSetSuccess(Context context) {
        boolean b = PreferencesUtils.getBoolean(context, Constants.SET_ALIAS_SUCCESS_KEY, false);
        LogUtil.i("is alias set success:" + b);
        return b;
    }

    public static void setAliasStatus(Context context, boolean isSuccess) {
        PreferencesUtils.putBoolean(context, Constants.SET_ALIAS_SUCCESS_KEY, isSuccess);
    }

    public static void exitApplication() {
        RxBus.getDefault().post(Constants.EVENT_TYPE_FINISH_ACTIVITY, true);

//        ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.LOGIN_ACTIVITY_NAME);
//       gotoActivity(com.blankj.utilcode.utils.Utils.getContext(), LoginActivityNew.class);

//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);
    }

//    public static void logoutJMessageAndExitApplication() {
//        logoutJMessage();
//        exitApplication();
//    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void setDialogWidthAndHeight(Dialog dialog, int width, int height) {
        try {
            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);

//        lp.x = 100; // 新位置X坐标
//        lp.y = 100; // 新位置Y坐标
            lp.width = width; // 宽度
            lp.height = height; // 高度
            lp.alpha = 0.7f; // 透明度

            dialogWindow.setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void saveWhetherEnablePushFlag(Context context, boolean whetherEnablePush) {
        PreferencesUtils.putBoolean(context, Constants.WHETHER_ENABLE_PUSH_KEY, whetherEnablePush);
    }

    public static boolean getWhetherEnablePushFlag(Context context) {
        boolean b = PreferencesUtils.getBoolean(context, Constants.WHETHER_ENABLE_PUSH_KEY, true);
        LogUtil.i("whether enable push:" + b);
        return b;
    }

    public static boolean isNetworkConnected(Context context) {
        boolean bConnected = NetworkUtils.isConnected();
        if (!bConnected) {
            showToast(context, Constants.HINT_NETWORK_DISCONNECTED);
        }
        return bConnected;
    }

    public static String getCanonicalTwoDigitsStringFromInteger(int i) {
        String result = Constants.EMPTY_STRING;
        if (i < 0 || i >= 100) {
            result = Constants.DOUBLE_ZERO_STRING;
        } else {
            if (i < 10) {
                result = Constants.ZERO_STRING + i;
            } else {
                result = String.valueOf(i);
            }

        }
        LogUtil.i("getCanonicalTwoDigitsStringFromInteger:" + i + "/" + result);
        return result;
    }

    public static int getValidHourIntFromString(String s) {
        s = trimString(s);
        int i = getIntFromString(s);
        if (i < 0 || i >= 24) {
            return 0;
        } else {
            return i;
        }
    }

    public static int getValidMinuteIntFromString(String s) {
        s = trimString(s);
        int i = getIntFromString(s);
        if (i < 0 || i >= 60) {
            return 0;
        } else {
            return i;
        }
    }

    public static String getCanonicalHourStringFromInteger(int i) {
        String result = Constants.EMPTY_STRING;
        if (i < 0 || i >= 24) {
            result = Constants.DOUBLE_ZERO_STRING;
        } else {
            if (i < 10) {
                result = Constants.ZERO_STRING + i;
            } else {
                result = String.valueOf(i);
            }

        }
        LogUtil.i("getCanonicalHourStringFromInteger:" + i + "/" + result);
        return result;
    }

    public static String getCanonicalMinuteStringFromInteger(int i) {
        String result = Constants.EMPTY_STRING;
        if (i < 0 || i >= 60) {
            result = Constants.DOUBLE_ZERO_STRING;
        } else {
            if (i < 10) {
                result = Constants.ZERO_STRING + i;
            } else {
                result = String.valueOf(i);
            }

        }
        LogUtil.i("getCanonicalMinuteStringFromInteger:" + i + "/" + result);
        return result;
    }

    public static Set<Integer> getAllowedPushWeekdays() {
        Set<Integer> days = new HashSet<>();
        for (int i = 0; i < 7; ++i) {
            days.add(i);
        }
        return days;
    }

    public static String getAllowedPushStartTime(Context context) {
        String result = PreferencesUtils.getString(context, Constants.ALLOWED_PUSH_START_TIME_KEY,
                Constants.ALLOWED_PUSH_START_TIME_DEFAULT_VALUE);
        LogUtil.i("allowed push start time:" + result);
        return result;
    }

    public static String getAllowedPushEndTime(Context context) {
        String result = PreferencesUtils.getString(context, Constants.ALLOWED_PUSH_END_TIME_KEY,
                Constants.ALLOWED_PUSH_END_TIME_DEFAULT_VALUE);
        LogUtil.i("allowed push end time:" + result);
        return result;
    }

    public static String getPushSilenceStartTime(Context context) {
        String original = PreferencesUtils.getString(context, Constants.PUSH_SILENCE_START_TIME_KEY,
                Constants.PUSH_SILENCE_START_TIME_DEFAULT_KEY);
        LogUtil.i("push silence start time from preference:" + original);
        return original;
    }

    public static String getPushSilenceEndTime(Context context) {
        String original = PreferencesUtils.getString(context, Constants.PUSH_SILENCE_END_TIME_KEY,
                Constants.PUSH_SILENCE_END_TIME_DEFAULT_KEY);
        LogUtil.i("push silence end time from preference:" + original);
        return original;
    }

    public static String[] getCanonicalPushSilenceStartTimeStringArray(Context context) {
        String original = getPushSilenceStartTime(context);
        String[] originalArray = getCanonicalHourMinuteStringArray(original);
        return originalArray;
    }

    public static String[] getCanonicalPushSilenceEndTimeStringArray(Context context) {
        String original = getPushSilenceEndTime(context);
        String[] originalArray = getCanonicalHourMinuteStringArray(original);
        return originalArray;
    }

    public static String getCanonicalPushSilenceStartTimeString(Context context) {
        String[] array = getCanonicalPushSilenceStartTimeStringArray(context);
        if (array != null && array.length >= 2) {
            return array[0] + Constants.COLON_STRING + array[1];

        } else {
            return Constants.DOUBLE_ZERO_STRING + Constants.COLON_STRING + Constants.DOUBLE_ZERO_STRING;
        }
    }

    public static String getCanonicalPushSilenceEndTimeString(Context context) {
        String[] array = getCanonicalPushSilenceEndTimeStringArray(context);
        if (array != null && array.length >= 2) {
            return array[0] + Constants.COLON_STRING + array[1];

        } else {
            return Constants.DOUBLE_ZERO_STRING + Constants.COLON_STRING + Constants.DOUBLE_ZERO_STRING;
        }

    }

    public static String getCanonicalAllowedPushStartTime(Context context) {
        String s = getAllowedPushStartTime(context);
        String result = getCanonicalAllowedPushTimeHour(s);
        return result;
    }

    public static String getCanonicalAllowedPushEndTime(Context context) {
        String s = getAllowedPushEndTime(context);
        String result = getCanonicalAllowedPushTimeHour(s);
        return result;
    }


    public static String getCanonicalAllowedPushTimeHour(String newValue) {
        newValue = trimString(newValue);
        int i = getIntFromString(newValue);
        String result = getCanonicalHourStringFromInteger(i);
        return result;
    }

    public static void saveAllowedPushStartTime(Context context, String newValue) {
        String result = getCanonicalAllowedPushTimeHour(newValue);
        LogUtil.i("saveAllowedPushStartTime:from/to:" + newValue + "/" + result);
        PreferencesUtils.putString(context, Constants.ALLOWED_PUSH_START_TIME_KEY, result);
    }

    public static void saveAllowedPushEndTime(Context context, String newValue) {
        String result = getCanonicalAllowedPushTimeHour(newValue);
        LogUtil.i("saveAllowedPushEndTime:from/to:" + newValue + "/" + result);
        PreferencesUtils.putString(context, Constants.ALLOWED_PUSH_END_TIME_KEY, result);
    }

    public static void savePushSilenceStartTime(Context context, String newValue) {
        String[] array = getCanonicalHourMinuteStringArray(newValue);
        LogUtil.i("savePushSilenceStartTime:hour/minute:" + array[0] + "/" + array[1]);
        String combined = Constants.DOUBLE_ZERO_STRING + Constants.COLON_STRING + Constants.DOUBLE_ZERO_STRING;
        if (array != null && array.length == 2) {
            combined = array[0] + Constants.COLON_STRING + array[1];
        }
        PreferencesUtils.putString(context, Constants.PUSH_SILENCE_START_TIME_KEY, combined);
    }

    public static void savePushSilenceEndTime(Context context, String newValue) {
        String[] array = getCanonicalHourMinuteStringArray(newValue);
        LogUtil.i("savePushSilenceEndTime:hour/minute:" + array[0] + "/" + array[1]);
        String combined = Constants.DOUBLE_ZERO_STRING + Constants.COLON_STRING + Constants.DOUBLE_ZERO_STRING;
        if (array != null && array.length == 2) {
            combined = array[0] + Constants.COLON_STRING + array[1];
        }
        PreferencesUtils.putString(context, Constants.PUSH_SILENCE_END_TIME_KEY, combined);
    }

//    public static void setAllowedPushTime(Context context, String startTime, String endTime) {
//        Set<Integer> days = getAllowedPushWeekdays();
//        String canonicalStartTime = getCanonicalAllowedPushTimeHour(startTime);
//        String canonicalEndTime = getCanonicalAllowedPushTimeHour(endTime);
//        int canonicalStartTimeInt = getIntFromString(canonicalStartTime);
//        int canonicalEndTimeInt = getIntFromString(canonicalEndTime);
//        LogUtil.i("will send to allow-push-time server,start/end:" + canonicalStartTimeInt + "/" + canonicalEndTimeInt);
//        JPushInterface.setPushTime(context.getApplicationContext(), days, canonicalStartTimeInt, canonicalEndTimeInt);
//        saveAllowedPushStartTime(context, startTime);
//        saveAllowedPushEndTime(context, endTime);
//    }
//
//    public static void setPushSilenceTime(Context context, String startHourString, String startMinuteString,
//                                          String endHourString, String endMinuteString) {
//        String startHour = getCanonicalHourString(startHourString);
//        String startMinute = getCanonicalMinuteString(startMinuteString);
//        String endHour = getCanonicalHourString(endHourString);
//        String endMinute = getCanonicalMinuteString(endMinuteString);
//        int startHourInt = getIntFromString(startHour);
//        int startMinuteInt = getIntFromString(startMinute);
//        int endHourInt = getIntFromString(endHour);
//        int endMinuteInt = getIntFromString(endMinute);
//        LogUtil.i("will send to push silence server:" + startHour + Constants.COLON_STRING + startMinute +
//                " " + endHour + Constants.COLON_STRING + endMinute);
//        JPushInterface.setSilenceTime(context.getApplicationContext(), startHourInt, startMinuteInt,
//                endHourInt, endMinuteInt);
//        savePushSilenceStartTime(context, startHour + Constants.COLON_STRING + startMinute);
//        savePushSilenceEndTime(context, endHour + Constants.COLON_STRING + endMinute);
//    }


    //gca begin
    public static <T> boolean isGCAResponseSuccessful(GCAHttpResultBaseBean<T> bean) {
        if (bean == null) {
            return false;
        } else {
            String code = bean.getResult();
            if (Constants.HTTP_RESPONSE_SUCCESS_CODE.equals(code) ||
                    Constants.HTTP_RESPONSE_SUCCESS_CODE_2.equals(code)) {
                return true;
            } else {
                return false;
            }
        }
    }

    //gca begin
    public static <T> boolean isGCAResponseNewSuccessful(GCAHttpResultHuifuBaseBean<T> bean) {
        if (bean == null) {
            return false;
        } else {
            String code = bean.getResult();
            if (Constants.HTTP_RESPONSE_SUCCESS_CODE.equals(code) ||
                    Constants.HTTP_RESPONSE_SUCCESS_CODE_2.equals(code)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static <T> boolean isGCAResponseEmpty(GCAHttpResultBaseBean<T> bean) {
        if (bean == null) {
            return false;
        } else {
            String code = bean.getResult();
            if (Constants.HTTP_RESPONSE_EMPTY_CODE.equals(code) ||
                    Constants.HTTP_RESPONSE_EMPTY_CODE_2.equals(code)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static <T> boolean isGCAResponseTokenTimeout(GCAHttpResultBaseBean<T> bean) {
        if (bean == null) {
            return false;
        } else {
            String code = bean.getResult();
            if (Constants.HTTP_RESPONSE_TOKEN_TIMEOUT_CODE.equals(code)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static <T> boolean isGCAResponseNewTokenTimeout(GCAHttpResultHuifuBaseBean<T> bean) {
        if (bean == null) {
            return false;
        } else {
            String code = bean.getResult();
            if (Constants.HTTP_RESPONSE_TOKEN_TIMEOUT_CODE.equals(code)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static <T> String getGCAMessageFromHttpResponse(GCAHttpResultBaseBean<T> bean) {
        if (bean == null) {
            return Constants.EMPTY_STRING;
        } else {
            String msg = bean.getMsg();
            msg = trimString(msg);
            return msg;
        }
    }

    public static <T> String getGCAMessageFromHttpNewResponse(GCAHttpResultHuifuBaseBean<T> bean) {
        if (bean == null) {
            return Constants.EMPTY_STRING;
        } else {
            String msg = bean.getMsg();
            msg = trimString(msg);
            return msg;
        }
    }

    public static <T> int getGCACodeFromHttpResponse(GCAHttpResultBaseBean<T> bean) {
        if (bean == null) {
            return Util.getIntFromString(Constants.HTTP_RESPONSE_DEFAULT_CODE);
        } else {
            String code = bean.getResult();
            int result = getIntFromString(code);
            return result;
        }
    }

    public static <T> T getGCADataFromHttpResponse(GCAHttpResultBaseBean<T> bean) {
        if (bean == null) {
            return null;
        } else {
            T data = bean.getData();
            return data;
        }
    }


    //gca end

    public static <T> boolean isResponseSuccessful(HttpResultBaseBean<T> bean) {
        if (bean == null) {
            return false;
        } else {
            String code = bean.getSuccess();
            if (Constants.HTTP_RESPONSE_SUCCESS_CODE.equals(code)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static <T> boolean isResponseTokenTimeout(HttpResultBaseBean<T> bean) {
        if (bean == null) {
            return false;
        } else {
            String code = bean.getSuccess();
            if (Constants.HTTP_RESPONSE_TOKEN_TIMEOUT_CODE.equals(code)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static <T> String getMessageFromHttpResponse(HttpResultBaseBean<T> bean) {
        if (bean == null) {
            return Constants.EMPTY_STRING;
        } else {
            String msg = bean.getMessage();
            msg = trimString(msg);
            return msg;
        }
    }
    public static <T> boolean isResponseSuccessfulForFingertip(HttpResultBaseBeanForFingertip<T> bean) {
        if (bean == null) {
            return false;
        } else {
            String code = bean.getFlag();
            if (Constants.HTTP_RESPONSE_SUCCESS_CODE_FOR_FINGERTIP.equals(code)) {
                return true;
            } else {
                return false;
            }
        }
    }


    public static <T> boolean isResponseSuccessfulForFingertip1(HttpResultBaseBeanForFingertip<T> bean) {
        if (bean == null) {
            return false;
        } else {
            String code = bean.getFlag();
            if (("1").equals(code)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static <T> boolean isResponseTokenTimeoutForFingertip(HttpResultBaseBeanForFingertip<T> bean) {
        if (bean == null) {
            return false;
        } else {
            String code = bean.getFlag();
            if (Constants.HTTP_RESPONSE_TIMEOUT_CODE_FOR_FINGERTIP.equals(code)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static <T> String getMessageFromHttpResponseForFingertip(HttpResultBaseBeanForFingertip<T> bean) {
        if (bean == null) {
            return Constants.EMPTY_STRING;
        } else {
            String msg = bean.getErrorMsg();
            msg = trimString(msg);
            return msg;
        }
    }

    public static <T> int getCodeFromHttpResponse(HttpResultBaseBean<T> bean) {
        if (bean == null) {
            return Util.getIntFromString(Constants.HTTP_RESPONSE_DEFAULT_CODE);
        } else {
            String code = bean.getSuccess();
            int result = getIntFromString(code);
            return result;
        }
    }
    public static Boolean isNumeric(String str) {
        if (!TextUtils.isEmpty(str)){
            Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
            Matcher isNum = pattern.matcher(str);
            if (!isNum.matches()) {
                return false;
            }
        }else {
            return false;
        }

        return true;
    }

    public static <T> T getDataFromHttpResponse(HttpResultBaseBean<T> bean) {
        if (bean == null) {
            return null;
        } else {
            T data = bean.getData();
            return data;
        }
    }

//    public static void jpushRequestPermission(Context context) {
//        JPushInterface.requestPermission(context);
//
//    }

//    public static void logoutJMessage() {
//        JMessageClient.logout();
//    }

    public static String getUserId() {
        String uid = Identity.uid;
        uid = trimString(uid);
        return uid;
    }

    public static String getUsername() {
        String username = Identity.username;
        username = trimString(username);
        return username;
    }

    public static String getRealname() {
        String realname = Identity.realname;
        realname = trimString(realname);
        return realname;
    }

    public static String getRole() {
        String role = Identity.role;
        role = trimString(role);
        return role;
    }

    public static void gotoLoginActivity(Context context, boolean shouldShowBackButton, boolean shouldFinishWhenLoginSuccess) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.LOGIN_ACTIVITY_SHOULD_SHOW_BACK_BUTTON_KEY, shouldShowBackButton);
        bundle.putBoolean(Constants.LOGIN_ACTIVITY_SHOULD_FINISH_WHEN_LOGIN_SUCCESS_KEY, shouldFinishWhenLoginSuccess);
//        ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.LOGIN_ACTIVITY_NAME, bundle);
        gotoActivityWithBundle(context, LoginActivityNew.class, bundle);
    }

    public static void gotoActivity(Context context, Class targetActivityClass) {
        gotoActivityWithBundle(context, targetActivityClass, null);
    }

    public static void gotoActivityWithBundle(Context context, Class targetActivityClass, Bundle bundle) {
        if (context == null || targetActivityClass == null) {
            return;
        } else {
            try {
                Intent intent = new Intent(context, targetActivityClass);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                context.startActivity(intent);
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    activity.overridePendingTransition(R.anim.right_in, R.anim.right_out);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void gotoVideoDisplayActivityWithUrl(Context context, String url){
        url=trimString(url);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.VIDEO_URL_KEY, url);
        gotoActivityWithBundle(context, VideoDisplayActivity.class, bundle);
    }

    public static boolean getShouldShowBackButton(Intent intent) {
        if (intent == null) {
            return Constants.LOGIN_ACTIVITY_SHOULD_SHOW_BACK_BUTTON;
        } else {
            boolean result = intent.getBooleanExtra(Constants.LOGIN_ACTIVITY_SHOULD_SHOW_BACK_BUTTON_KEY, Constants.LOGIN_ACTIVITY_SHOULD_SHOW_BACK_BUTTON);
            return result;
        }
    }

    public static boolean getShouldFinishWhenLoginSuccess(Intent intent) {
        if (intent == null) {
            return Constants.LOGIN_ACTIVITY_SHOULD_FINISH_WHEN_LOGIN_SUCCESS;
        } else {
            boolean result = intent.getBooleanExtra(Constants.LOGIN_ACTIVITY_SHOULD_FINISH_WHEN_LOGIN_SUCCESS_KEY, Constants.LOGIN_ACTIVITY_SHOULD_FINISH_WHEN_LOGIN_SUCCESS);
            return result;
        }
    }

    public static <T> List<T> getProcessedStatisticsChartTitleList(List<T> list){
        List<T> result = getCopyList(list);
        reverseList(result);
        return result;
    }

    public static <T> List<T> getCopyList(List<T> list){
        List<T> result = new ArrayList<>();
        if (!ListUtils.isEmpty(list)){
            result.addAll(list);
            return result;
        } else {
            return result;
        }
    }

    public static <T> void processStatisticsChartList(List<T> list){
        reverseList(list);
    }

    public static <T> void reverseList(List<T> list) {
        if (!ListUtils.isEmpty(list)) {
            Collections.reverse(list);
        }

    }

    public static String formatMillisecondTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
//        if(day > 0) {
//            sb.append(day+"天");
//        }
        if (hour > 0) {
            sb.append(hour);
        }
        sb.append(Constants.COLON_STRING);
        if (minute > 0) {
            sb.append(minute);
        }
//        if(second > 0) {
//            sb.append(second+"秒");
//        }
//        if(milliSecond > 0) {
//            sb.append(milliSecond+"毫秒");
//        }
        return sb.toString();
    }

//    public static String getJMessagePicturePath() {
//        String result = getJMessagePicturePath(Identity.getCurrentAppKey());
//        return result;
//    }

    public static String getJMessagePicturePath(String appKey) {
        return Version.JMESSAGE_PICTURE_FOLDER_PATH + appKey + "/";
    }

//    public static String getJMessageVoicePath() {
//        String result = getJMessageVoicePath(Identity.getCurrentAppKey());
//        return result;
//    }

    public static String getJMessageVoicePath(String appKey) {
        return Version.JMESSAGE_VOICE_FOLDER_PATH + appKey + "/";
    }

//    public static String getJMessageFilePath() {
//        String result = getJMessageFilePath(Identity.getCurrentAppKey());
//        return result;
//    }

    public static String getJMessageFilePath(String appKey) {
        return Version.JMESSAGE_FILE_FOLDER_PATH + appKey + "/";
    }

//    public static String getJMessageDisplayTime(Context context, long nowDate) {
//        TimeFormat timeFormat = new TimeFormat(context, nowDate);
//        String result = timeFormat.getDetailTime();
//        return result;
//    }

    public static String getWelcomeString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.HINT_WELCOME);
        sb.append(Identity.realname);
        sb.append(Constants.EXCLAMATION);
        return sb.toString();
    }

    public static void gotoExecutionProjectsActivity(Context context, ExecutionProjectsType.ExecutionProjectsTypeEnum type,
                                                     String cardNo) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.EXECUTION_PROJECTS_TYPE_KEY, type.getValue());
        bundle.putString(Constants.CARD_NO_KEY, cardNo);
        gotoActivityWithBundle(context, ExecutionProjectsActivity.class, bundle);
    }

    public static String getExecutionProjectsActivityTitle(ExecutionProjectsType.ExecutionProjectsTypeEnum en) {
        String result = Constants.TITLE_ACTIVITY_EXECUTION_PROJECTS_LATEST_7_DAYS;
        if (en == ExecutionProjectsType.ExecutionProjectsTypeEnum.Latest7Days) {
            result = Constants.TITLE_ACTIVITY_EXECUTION_PROJECTS_LATEST_7_DAYS;
        } else if (en == ExecutionProjectsType.ExecutionProjectsTypeEnum.Timeout) {
            result = Constants.TITLE_ACTIVITY_EXECUTION_PROJECTS_TIMEOUT;
        }
        return result;
    }

    public static String getExecutionProjectsActivityTitle(int type) {
        String result = Constants.TITLE_ACTIVITY_EXECUTION_PROJECTS_LATEST_7_DAYS;
        if (type == ExecutionProjectsType.ExecutionProjectsTypeEnum.Latest7Days.getValue()) {
            result = Constants.TITLE_ACTIVITY_EXECUTION_PROJECTS_LATEST_7_DAYS;
        } else if (type == ExecutionProjectsType.ExecutionProjectsTypeEnum.Timeout.getValue()) {
            result = Constants.TITLE_ACTIVITY_EXECUTION_PROJECTS_TIMEOUT;
        }
        return result;

    }

    public static String getExecutionProjectsActivityHintTitle(Context context, int type) {
        int resId = R.string.hint_list_empty_please_reload;
        if (type == ExecutionProjectsType.ExecutionProjectsTypeEnum.Latest7Days.getValue()) {
            resId = R.string.hint_no_latest_7_days_need_execution_projects_please_click_to_reload;
        } else if (type == ExecutionProjectsType.ExecutionProjectsTypeEnum.Timeout.getValue()) {
            resId = R.string.hint_no_timeout_not_execution_projects_please_click_to_reload;
        }
        String result = context.getResources().getString(resId);
        return result;

    }

    public static String getBase64String(String s) {
        if (StringUtils.isBlank(s)) {
            return Constants.EMPTY_STRING;
        } else {
            return Base64.encode(s);
        }
    }

    public static String getDecodedBase64String(String s) {
        if (StringUtils.isBlank(s)) {
            return Constants.EMPTY_STRING;
        } else {
            return Base64.decode(s);
        }
    }

    public static Map<String, String> getMapForHttpRequestFromMap(Map<String, String> map) {
        Map<String, String> ultimateMap = new HashMap<>();
        String jsonString = Constants.EMPTY_STRING;
        if (map != null) {
            jsonString = JSON.toJSONString(map);
        }
        ultimateMap.put(Constants.REQUEST_KEY, jsonString);
        return ultimateMap;
    }

    public static String getMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    public static List<MultipartBody.Part> getMultipartBodyList(String name, List<String> filePathList){
        List<MultipartBody.Part> result = new ArrayList<>();
        if (ListUtils.isEmpty(filePathList)){
            return result;
        } else {
            for (int i = 0; i < filePathList.size(); ++i){
                String filePath = filePathList.get(i);
//                String fileName = FileUtils.getFileName(filePath);
                File file = new File(filePath);

                RequestBody fileRequestBody = RequestBody.create(MediaType.parse(getMimeType(file.getPath())), file);

                MultipartBody.Part part = MultipartBody.Part.createFormData(name, file.getName(), fileRequestBody);

                result.add(part);

            }
            return result;
        }

    }

    public static MultipartBody.Part getPartFromFilePath(String filePath, String name){
        File file = new File(filePath);

        RequestBody fileRequestBody = RequestBody.create(MediaType.parse(getMimeType(file.getPath())), file);

        MultipartBody.Part part = MultipartBody.Part.createFormData(name, file.getName(), fileRequestBody);

        return part;
    }

    public static RequestBody getRequestBodyFromText (String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body ;
    }

    public static RequestBody getRequestBodyFromImage(File file){
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        return fileBody;
    }

    public static RequestBody getRequestBodyFromVideo(File file){
        String mime=getMimeType(file.getPath());
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        RequestBody fileBody = RequestBody.create(MediaType.parse("video/*"), file);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);

//        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("uploadVideo", file.getName(), RequestBody.create(null, file))
//                .build();

        return requestBody;
    }


    public static RequestBody getBodyFromMap(Map map) {
        if (map == null) {
            return null;
        } else {
            String bodyJson = JSON.toJSONString(map);
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json"), bodyJson);
            return body;
        }
    }

    public static RequestBody getBodyFromMap1(Map map) {
        if (map == null) {
            return null;
        } else {
            String bodyJson = JSON.toJSONString(map);
            /*JSONObject json=new JSONObject(map);
         //   JSONObject jsonMap = JSONObject.fromObject(map);
            String bodyJson = json.toString();*/
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json"), bodyJson);
            return body;
        }
    }


    public static View getImageViewOrRelativeLayoutFromView(View lit) {
        if (lit == null) {
            return null;
        } else {
            View rlImage = lit.findViewById(R.id.rl_image);
            View ivImage = lit.findViewById(R.id.iv_image);
            if (rlImage instanceof RelativeLayout) {
                return rlImage;
            } else if (ivImage instanceof ImageView) {
                return ivImage;
            } else {
                return null;
            }
        }
    }

    public static RelativeLayout getRelativeLayoutFromView(View lit) {
        if (lit == null) {
            return null;
        } else {
            RelativeLayout relativeLayout = (RelativeLayout) lit.findViewById(R.id.rl_image);
            return relativeLayout;
        }
    }

    public static ImageView getImageViewFromView(View lit) {
        if (lit == null) {
            return null;
        } else {
            ImageView imageView = (ImageView) lit.findViewById(R.id.iv_image);
            return imageView;
        }
    }

    public static TextView getTextViewFromView(View lit) {
        if (lit == null) {
            return null;
        } else {
            TextView textView = (TextView) lit.findViewById(R.id.tv_text);
            return textView;
        }
    }

    public static TextView getNumberTextViewFromView(View lit) {
        if (lit == null) {
            return null;
        } else {
            TextView textView = (TextView) lit.findViewById(R.id.tv_number);
            return textView;
        }
    }

    public static void setImageTextForViewHorizontal(View view, int imageId, String text) {
        if (view == null) {
            return;
        } else {
            ImageView iv = getImageViewFromView(view);
            if (iv != null) {
                iv.setImageResource(imageId);
            }

            TextView tvText = getTextViewFromView(view);
            if (tvText != null) {
                tvText.setText(text);
            }

        }
    }

    public static void setImageTextNumberForViewHorizontal(View view, int imageId, String text, String number) {
        if (view == null) {
            return;
        } else {
            ImageView iv = getImageViewFromView(view);
            if (iv != null) {
                iv.setImageResource(imageId);
            }

            TextView tvText = getTextViewFromView(view);
            if (tvText != null) {
                tvText.setText(text);
            }

            TextView tvNumber = getNumberTextViewFromView(view);
            if (tvNumber != null) {
                tvNumber.setText(number);
            }
        }
    }

    public static void setImageTextNumberForViewVertical(View view, int imageId, String text, String number) {
        if (view == null) {
            return;
        } else {
            View iv = getImageViewOrRelativeLayoutFromView(view);
            if (iv != null) {
                iv.setBackgroundResource(imageId);
            }

            TextView tvText = getTextViewFromView(view);
            if (tvText != null) {
                tvText.setText(text);
            }

            TextView tvNumber = getNumberTextViewFromView(view);
            if (tvNumber != null) {
                int n = getIntFromString(number);
//                n=1000;
                n = getDisplayNumber(n);
                String ultimateNumber = String.valueOf(n);
                tvNumber.setText(ultimateNumber);
                if (n <= 0) {
                    tvNumber.setVisibility(View.GONE);
                } else {
                    tvNumber.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public static int getDisplayNumber(int number) {
        if (number <= 0) {
            return 0;
        } else if (number > Constants.MAX_NUMBER) {
            return Constants.MAX_NUMBER;
        } else {
            return number;
        }
    }

    public static int getDisplayNumber(String numberString) {
        int number = getIntFromString(numberString);
        if (number <= 0) {
            return 0;
        } else if (number > Constants.MAX_NUMBER) {
            return Constants.MAX_NUMBER;
        } else {
            return number;
        }
    }

    public static String getDisplayNumberString(String numberString) {
        int result = getDisplayNumber(numberString);
        String stringResult = String.valueOf(result);
        return stringResult;
    }

    public static int getValidStarNumber(int n) {
        if (n <= 0) {
            return 0;
        } else if (n > Constants.MAX_STAR_NUMBER) {
            return Constants.MAX_STAR_NUMBER;
        } else {
            return n;
        }
    }

    public static String getPercentageStringFromFloat(float f) {
        float r = f * 100;
        String result = String.format(Locale.CHINA, "%.2f%%", r);
        return result;
    }

    public static String getPercentageStringFromString(String s) {
        s = trimString(s);
        float f = getFloatFromString(s);
//        float r = f * 100;
        float r = f;
        String result = String.format(Locale.CHINA, "%.2f%%", r);
        return result;
    }

    /**
     * 字段访问函数须使用get/set开头,即使字段名称以is开头,访问函数也应以get/set开头
     * (如字段名称为isHypertensionState,访问函数应为
     * public String getIsHypertensionState() {
     * return isHypertensionState;
     * }
     * <p>
     * public void setIsHypertensionState(String isHypertensionState) {
     * this.isHypertensionState = isHypertensionState;
     * }
     * ),否则将解析错误.
     *
     * @param o
     */
    public static void decodeBase64Bean(Object o) {
        BeanBase64Decoder.decodeBase64Bean(o);
    }

//    public static void decodeBase64BeanForFingertip(Object o, Class targetClass) {
//        BeanBase64Decoder.decodeBase64BeanForFingertip(o, targetClass);
//    }

    public static String getResourceString(Context context, int resourceId) {
        String result = context.getResources().getString(resourceId);
        return result;
    }

    public static int getBackgroundResourceIdFromPackageLevel(String level) {
        int result = R.drawable.package_level_base_background;
        level = trimString(level);
        if (Constants.PACKAGE_LEVEL_BASE.equals(level)) {
            result = R.drawable.package_level_base_background;
        } else if (Constants.PACKAGE_LEVEL_JUNIOR.equals(level)) {
            result = R.drawable.package_level_junior_background;
        } else if (Constants.PACKAGE_LEVEL_INTERMEDIATE.equals(level)) {
            result = R.drawable.package_level_intermediate_background;
        } else if (Constants.PACKAGE_LEVEL_ADVANCED.equals(level)) {
            result = R.drawable.package_level_advanced_background;
        }
        return result;
    }

    public static String getPackageLevelStringFromPackageLevel(String level) {
        String result = Constants.EMPTY_STRING;
        level = trimString(level);
        if (Constants.PACKAGE_LEVEL_BASE.equals(level)) {
            result = Constants.PACKAGE_LEVEL_BASE_REPRESENTATION_SIMPLIFY;
        } else if (Constants.PACKAGE_LEVEL_JUNIOR.equals(level)) {
            result = Constants.PACKAGE_LEVEL_JUNIOR_REPRESENTATION_SIMPLIFY;
        } else if (Constants.PACKAGE_LEVEL_INTERMEDIATE.equals(level)) {
            result = Constants.PACKAGE_LEVEL_INTERMEDIATE_REPRESENTATION_SIMPLIFY;
        } else if (Constants.PACKAGE_LEVEL_ADVANCED.equals(level)) {
            result = Constants.PACKAGE_LEVEL_ADVANCED_REPRESENTATION_SIMPLIFY;
        }
        return result;
    }

    public static String getStarLevelStringFromLevel(String level) {
        int levelInt = getIntFromString(level);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < levelInt; ++i) {
            sb.append(Constants.FILLED_STAR);
        }
        for (int i = 0; i < MAX_STAR_NUMBER - levelInt; ++i) {
            sb.append(Constants.UNFILLED_STAR);
        }
        return sb.toString();

    }

    public static float getProgressFromTwoNumber(float hadExecCount, float shouldExecCount) {
        float progress = 0;
        try {
            progress = hadExecCount / shouldExecCount;
        } catch (Exception e) {
            e.printStackTrace();
            progress = 0;
        }
        return progress;
    }

    public static float getProgressFromTwoString(String hadExecCount, String shouldExecCount) {
        float had = getFloatFromString(hadExecCount);
        float should = getFloatFromString(shouldExecCount);
        float progress = getProgressFromTwoNumber(had, should);
        return progress;
    }

    public static String getFileUrlBasePath() {
        String userId = Identity.getUserId();
        String result = Version.FILE_URL_BASE + userId;
        return result;
    }

    public static void setDialogWidthHeight(Activity activity, Dialog dialog, float widthPercent, float heightPercent) {
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * heightPercent); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * widthPercent); // 宽度设置为屏幕的0.65
        dialogWindow.setAttributes(p);

    }

    public static void setDialogWidthHeight(Dialog dialog, int width, int height) {
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (height); // 高度设置为屏幕的0.6
        p.width = (int) (width); // 宽度设置为屏幕的0.65
        dialogWindow.setAttributes(p);

    }

    public static List<SignSeverPakesBeanList> getDeepCopySignSeverPakesBeanList(List<SignSeverPakesBeanList> originalList) {
        if (ListUtils.isEmpty(originalList)) {
            return new ArrayList<>();
        } else {
            List<SignSeverPakesBeanList> result = null;
            try {
                result = new ArrayList<>();
                for (int i = 0; i < originalList.size(); ++i) {
                    SignSeverPakesBeanList bean = originalList.get(i);
                    SignSeverPakesBeanList newBean = (SignSeverPakesBeanList) bean.clone();
                    result.add(newBean);
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                result = new ArrayList<>();
            }
            return result;
        }
    }

    public static String getYearMonthDayFromFullString(String s) {
        s = trimString(s);
        int index = s.indexOf(Constants.SPACE_STRING);
        if (index != -1) {
            return s.substring(0, index);
        } else {
            return s;
        }
    }

    public static String getYearMonthDayHourMinuteSecondFromFullString(String s) {
        s = trimString(s);
        int index = s.indexOf(Constants.PERIOD_STRING);
        if (index != -1) {
            return s.substring(0, index);
        } else {
            return s;
        }
    }

    public static void dial(Context context, String phoneNumber) {
        phoneNumber = Util.trimString(phoneNumber);
        if (!StringUtils.isBlank(phoneNumber)) {
            AppUtils.actionDial(context, phoneNumber);
        } else {
            Util.showToast(context, Constants.HINT_PHONE_NUMBER_EMPTY);
        }

    }

    public static String getUnderlinedString(String s) {
        s = trimString(s);
        String result = "<u>" + s + "</u>";
        return result;
    }

    public static String getBlueUnderlinedString(String s) {
        s = trimString(s);
        String result = "<font color='blue'><u>" + s + "</u></font>";
        return result;
    }

    public static boolean endsWith(String src, String pattern) {
        pattern = trimString(pattern);
        src = trimString(src);
        if (StringUtils.isBlank(src)) {
            return false;
        } else {
            return src.endsWith(pattern);
        }
    }

    public static String getSpecificRatioHint(String src) {
        src = trimString(src);
        boolean b = endsWith(src, Constants.HINT_SIGN);
        if (b) {
            return src + Constants.RATIO;
        } else {
            return src + Constants.HINT_SIGN + Constants.RATIO;
        }
    }

    public static String getDBnamePath(Context context) {
        String DB_NAME;
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            DB_NAME = "/sdcard/yishu/usingservice.db";
        } else {
            DB_NAME = context.getFilesDir().getPath() + "/yishu/usingservice.db";
        }

        return DB_NAME;
    }

    public static String getRealBuglyAppId(String appId) {
        if (!StringUtils.isBlank(appId)) {
            return appId.substring(Constants.BUGLY_APP_ID_REAL_INDEX);
        } else {
            return Constants.EMPTY_STRING;
        }
    }


    public static String getMetaDataFromManifest(Context context, String key) {
        String result = Constants.EMPTY_STRING;
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            result = appInfo.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            result = Constants.EMPTY_STRING;
        }
        return result;
    }

    public static void setVariousUrlFromBaseUrl(Context context, String baseHttpUrl){
        baseHttpUrl=trimString(baseHttpUrl);
        //        GCARetrofit.BASE_URL =  Util.getMetaDataFromManifest(this, "HTTP_ACCESS_URL");
        GCARetrofit.BASE_URL = baseHttpUrl;
        GCARetrofit.BASE_URL_NEW = baseHttpUrl;
        Version.HTTP_URL = baseHttpUrl;
        String uploadServiceUrlSuffix = Util.getMetaDataFromManifest(context, "HTTP_UPLOAD_VIDEO_URL");
        Version.HTTP_UPLOAD_VIDEO_URL = Version.HTTP_URL+uploadServiceUrlSuffix;
//        Version.HTTP_UPLOAD_VIDEO_URL = uploadServiceUrlSuffix;
        Version.HTTP_UPLOAD_VIDEO_URL_HOST_PREFIX = getVideoUrlPrefix(Version.HTTP_UPLOAD_VIDEO_URL);
        Version.FIND_FILE_URL_BASE = "";
        Version.FILE_URL_BASE = "";
//        Version.FILE_URL_BASE = Version.HTTP_URL.substring(0, Version.HTTP_URL.length() - 1);
        Version.LOGIN_APP_NAME = Util.getMetaDataFromManifest(context, "LOGIN_APP_NAME");
        String buglyId = Util.getMetaDataFromManifest(context, "BUGLY_APP_ID");
        buglyId = Util.getRealBuglyAppId(buglyId);
        Version.BUGLY_APP_ID = buglyId;

    }

    public static String getVideoUrlPrefix(String baseUrl){
        String result = baseUrl;
        int lastIndexOfColon = result.lastIndexOf(Constants.COLON_STRING);
        if (lastIndexOfColon!=-1){
            int firstIndexOfSlash = result.indexOf(Constants.SLASH_STRING, lastIndexOfColon);
            if (firstIndexOfSlash!=-1){
                result=result.substring(0, firstIndexOfSlash);
            }
        }
        return result;
    }


    public static String getReplacedXmlTagString(String s) {
        s = trimString(s);
        String SPACE_PATTERN = " ";
        String PARAGRAPH_PATTERN = "(?<!\\r\\n|\\n)</p>(?!\\r\\n|\\n)";
        String BR_PATTERN = "(?<!\\r\\n|\\n)(?:<br/?>)(?!\\r\\n|\\n)";
        String REGEX_PATTERN = "<.*?>|</.*?>|&nbsp;|\\t";
        String result = s.replaceAll(SPACE_PATTERN, Constants.EMPTY_STRING);
        result = result.replaceAll(PARAGRAPH_PATTERN, "\n");
        result = result.replaceAll(BR_PATTERN, "\n");
        result = result.replaceAll(REGEX_PATTERN, Constants.EMPTY_STRING);
        result = result.replaceAll("&lt;", Constants.LT_STRING);
        result = result.replaceAll("&gt;", Constants.GT_STRING);
        return result;
    }

    public static boolean isServiceWorked(Context context, String serviceName) {
        ActivityManager myManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager.getRunningServices(Integer.MAX_VALUE);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    public static String getTimestampLeading10CharactersString() {
        long timestamp = System.currentTimeMillis();
        String s = String.valueOf(timestamp);
        if (s.length() > 10) {
            s = s.substring(0, 10);
        }
        return s;
    }

    public static String getEncryptedDigest(String timestamp) {
        return getEncryptedDigest(timestamp, Constants.API_KEY_VALUE, Constants.PRIVATE_KEY_VALUE);
    }

    //加密摘要,MD5(时间戳 + 身份标识 + 私钥)
    public static String getEncryptedDigest(String timestamp, String id, String privateKey) {
        String s = timestamp + id + privateKey;
        String md5 = MD5.md5(s);
        return md5;
    }

    public static String getCurrentTime() {
        long time = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳

        String str = String.valueOf(time);
        return str;
    }


    public static String getmd5(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 获取某月最大的天数
     *
     * @param year
     * @param month 从0开始
     * @return
     */
    public static int getMonthMaxDateFromYearAndMonth(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month);//注意,Calendar对象默认一月为0
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        return day;
    }

    public static List<MonthTextBean> getLatestNumberMonthTextBeanList(int number) {
        List<MonthTextBean> list = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat(MonthTextBean.FORMAT_STRING);
        for (int i = 0; i < number; ++i) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -i-1 );
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int endDateInt = getMonthMaxDateFromYearAndMonth(year, month);
            Date date = cal.getTime();
            String s = df.format(date);
            MonthTextBean mtb = new MonthTextBean(s, year, month, 1, endDateInt);
            list.add(mtb);
        }
        return list;
    }

    public static List<QuarterTextBean> getLatestNumberQuarterTextBeanList(int number) {
        List<QuarterTextBean> list = new ArrayList<>();
        int year = getCurrentYearInt();
        int quarter = getCurrentQuarterInt();
//        int previousYear = year;
//        int previousQuarter = quarter-1;
//        if (quarter==0){
//            --previousYear;
//            previousQuarter=3;
//        } else {
//            previousQuarter=quarter-1;
//        }
        QuarterLogicBean qlb = new QuarterLogicBean(year, quarter);
        for (int i = 0; i < number; ++i) {
            QuarterLogicBean nqlb = qlb.getPreviousNumberBean(i);
            QuarterTextBean qtb = nqlb.toQuarterTextBean();
            list.add(qtb);
        }
        return list;
    }

    public static List<YearTextBean> getLatestNumberYearTextBeanList(int number) {
        List<YearTextBean> list = new ArrayList<>();

        int sCurrentYearMonth = getCurrentYearMonth();
        if (sCurrentYearMonth == 1) {
            int year = getCurrentYearInt() - 1;
            for (int i = 0; i < number; ++i) {
                String presentation = String.format(Locale.CHINA, "%d年", (year - i));
                YearTextBean ytb = new YearTextBean(presentation, year - i);
                list.add(ytb);
            }
        } else {

            int year = getCurrentYearInt();
            for (int i = 0; i < number; ++i) {
                String presentation = String.format(Locale.CHINA, "%d年", (year - i));
                YearTextBean ytb = new YearTextBean(presentation, year - i);
                list.add(ytb);
            }
        }


        return list;
    }

    public static String formatDate(String x) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        String sDate = null;
        try {
            Date date = sdf1.parse(x);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sDate = sdf.format(date);
            System.out.println(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sDate;
    }

    public static Date stringToDate(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            // Fri Feb 24 00:00:00 CST 2012
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 2012-02-24
        date = java.sql.Date.valueOf(str);

        return date;
    }

    public static String getProcessedDateTimeString(String s) {
        s = trimString(s);
        try {
//            s=s.replaceAll(Constants.T_SEPARATOR, Constants.SPACE_STRING);
            int separatorIndex = s.indexOf(Constants.T_SEPARATOR);
            if (separatorIndex != -1) {
                s = s.substring(0, separatorIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void replaceIdWithFragment(FragmentActivity activity, int id, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public static void placeDialogAtBottom(Dialog dialog) {
        if (dialog != null) {
            Window window = dialog.getWindow();
//            WindowManager.LayoutParams lp = window.getAttributes();
            if (window != null) {
                window.setGravity(Gravity.BOTTOM);
            }
        }
    }

    public static void setDialogFillWidth(Activity activity, Dialog dialog) {
        if (activity != null && dialog != null) {
            Window window = dialog.getWindow();
            WindowManager manager = activity.getWindowManager();
            Display d = manager.getDefaultDisplay(); // 获取屏幕宽、高用
            if (window != null) {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = d.getWidth();
                window.setAttributes(lp);
            }
        }
    }

    public static void setDialogLeftRightMarginZero(Dialog dialog) {
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.horizontalMargin = 0f;
                window.setAttributes(lp);
            }
        }
    }

    public static int getScreenWidth(Activity activity) {
        if (activity != null) {
            WindowManager wm = activity.getWindowManager();
            Display display = wm.getDefaultDisplay();
            return display.getWidth();
        } else {
            return 0;
        }
    }

    public static int getScreenHeight(Activity activity) {
        if (activity != null) {
            WindowManager wm = activity.getWindowManager();
            Display display = wm.getDefaultDisplay();
            return display.getHeight();
        } else {
            return 0;
        }
    }

    public static void setViewWidth(View view, int width) {
        if (view == null || width < 0) {
            return;
        } else {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            if (lp == null) {
                lp = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            lp.width = width;
            view.setLayoutParams(lp);

        }
    }

//    public static void setTextViewMaxWidth(TextView view, int maxWidth){
//        if (view==null || maxWidth<0){
//            return;
//        } else {
//            view.setMaxWidth(maxWidth);
//        }
//    }
//
//    public static void setTextViewMaxWidthFill(TextView view){
////        int fillWidth = getScreenWidth((Activity) view.getContext());
//        int fillWidthPixels = com.blankj.utilcode.utils.ScreenUtils.getScreenWidth();
//        setTextViewMaxWidth(view, fillWidthPixels);
//    }

    public static String enCodeVideo(String path) {
        String strAttachmentCoded;
        File file = new File(path);
        FileInputStream objFileIS = null;
        try {
            objFileIS = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ByteArrayOutputStream objByteArrayOS = new ByteArrayOutputStream();
        byte[] byteBufferString = new byte[1024];
        try {
            for (int readNum; (readNum = objFileIS.read(byteBufferString)) != -1; ) {
                objByteArrayOS.write(byteBufferString, 0, readNum);
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        byte[] byteBinaryData = android.util.Base64.encode((objByteArrayOS.toByteArray()), android.util.Base64.DEFAULT);
        byte[] byteBinaryData = android.util.Base64.encode((objByteArrayOS.toByteArray()), android.util.Base64.NO_WRAP);
        strAttachmentCoded = new String(byteBinaryData);

//        strAttachmentCoded = Base64Util.imageToBase64()
        return strAttachmentCoded;
    }

    public static String getNumberWithSpecificFractionDigits(String numberString, int minFractionDigitsNumber, int maxFractionDigitsNumber) {
        double d = getDoubleValueFromString(numberString);
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(maxFractionDigitsNumber);
        nf.setMinimumFractionDigits(minFractionDigitsNumber);
        String s = nf.format(d);
        return s;
    }

    public static String getNumberWithSpecificFractionDigits(String numberString, int fractionDigitsNumber) {
        double d = getDoubleValueFromString(numberString);
        String s = String.format("%." + fractionDigitsNumber + "f", d);
        return s;
    }

    public static String getCanonicalLongitudeOrLatitude(String s) {
        String result = getNumberWithSpecificFractionDigits(s, Constants.LONGITUDE_LATITUDE_FRACTION_DIGITS_NUMBER, Constants.LONGITUDE_LATITUDE_FRACTION_DIGITS_NUMBER);
//        String result = getNumberWithSpecificFractionDigits(s, Constants.LONGITUDE_LATITUDE_FRACTION_DIGITS_NUMBER);
        return result;
    }

    public static String getWidthLessMultipleLineStringFromString(String s) {
        s = trimString(s);
        List<String> partList = new ArrayList<>();
        int MAX_NUMBER = Constants.MAX_CHARACTER_NUMBER_PER_LINE;
        if (!StringUtils.isBlank(s)) {
            int maxLine = (s.length() + (MAX_NUMBER - 1)) / MAX_NUMBER;
            for (int i = 0; i < maxLine; ++i) {
                int start = i * MAX_NUMBER;
                int end = Math.min((i + 1) * MAX_NUMBER, s.length());
                String temp = s.substring(start, end);
                partList.add(temp);
            }
            String[] stringArray = partList.toArray(new String[0]);
            String result = join(stringArray, Constants.NEW_LINE_SEPARATOR);
            return result;
        } else {
            return s;
        }
    }

    public static String getUrlPathWithoutLastSlashCharacter(String url) {
        url = trimString(url);
        if (url.endsWith(Constants.SLASH_STRING)) {
            return url.substring(0, url.length() - 1);
        }
        return url;
    }

    public static void resetHorizontalBarChartState(HorizontalBarChart hbcChart){
        if (hbcChart!=null) {
            hbcChart.clear();
            hbcChart.getAxisLeft().resetAxisMaximum();
            hbcChart.getAxisRight().resetAxisMaximum();
        }

    }

    public static void setHorizontalBarChartInversionPositionAxisMinimum(HorizontalBarChart hbcChart){
        if (hbcChart!=null) {
            setHorizontalBarChartInversionPositionSpecificAxisMinimum(hbcChart.getAxisLeft());
            setHorizontalBarChartInversionPositionSpecificAxisMinimum(hbcChart.getAxisRight());
        }
    }

    public static void setHorizontalBarChartInversionPositionSpecificAxisMinimum(YAxis yAxis) {
        if (yAxis!=null) {
            float axisMax = yAxis.getAxisMaximum();
            float ultimateMax = Math.max(axisMax, Constants.HORIZONTAL_BAR_CHART_MINIMUM_Y_AXIS_VALUE);
            LogUtil.i("axisMax:"+axisMax+" ultimateMax:"+ultimateMax);
            yAxis.setAxisMaximum(ultimateMax);
        }
    }

    public static void setHorizontalBarChartInversionPosition(HorizontalBarChart hbcChart){
        if (hbcChart!=null) {
            hbcChart.moveViewTo(Constants.HORIZONTAL_BAR_CHART_START_POINT_X,
                    Constants.HORIZONTAL_BAR_CHART_START_POINT_Y, YAxis.AxisDependency.LEFT);
            hbcChart.moveViewTo(Constants.HORIZONTAL_BAR_CHART_START_POINT_X,
                    Constants.HORIZONTAL_BAR_CHART_START_POINT_Y, YAxis.AxisDependency.RIGHT);
        }
    }

    public static void setLineChartAxisMinimumMaximum(LineChart lineChart, List<Float> floatList) {
        if (lineChart != null && !ListUtils.isEmpty(floatList)) {
            float min = Collections.min(floatList);
            float max = Collections.max(floatList);
            float extraSpace = (max - min) * Constants.CHART_RANGE_EXTRA_SPACE_PERCENT;
            if (isNearZero(extraSpace)) {
                extraSpace = Constants.CHART_RANGE_DEVIATION_VALUE;
            }
            float minReal = min - extraSpace;
            lineChart.getAxisLeft().setAxisMinimum(minReal);
            lineChart.getAxisRight().setAxisMinimum(minReal);
            float maxReal = max + extraSpace;
            lineChart.getAxisLeft().setAxisMaximum(maxReal);
            lineChart.getAxisRight().setAxisMaximum(maxReal);

        }
    }

    public static void setChartSpecificAxisMinimumMaximum(YAxis yAxis, List<Float> floatList, float extraSpacePercent) {
        if (yAxis != null && !ListUtils.isEmpty(floatList)) {
            float min = Collections.min(floatList);
            float max = Collections.max(floatList);
            float extraSpace = (max - min) * extraSpacePercent;
            if (isNearZero(extraSpace)) {
                extraSpace = Constants.CHART_RANGE_DEVIATION_VALUE;
            }
            float minReal = min - extraSpace;
            yAxis.setAxisMinimum(minReal);
            float maxReal = max + extraSpace;
            yAxis.setAxisMaximum(maxReal);
        }
    }

//    public static void setLineChartAxisMaximum(LineChart lineChart, List<Float> floatList){
//        if (lineChart!=null && !ListUtils.isEmpty(floatList)) {
//            float max = Collections.max(floatList);
//            float maxReal = max+Constants.CHART_RANGE_DEVIATION_VALUE;
//            lineChart.getAxisLeft().setAxisMaximum(maxReal);
//            lineChart.getAxisRight().setAxisMaximum(maxReal);
//        }
//    }

    public static void setHorizontalBarChartAxisMinimum(HorizontalBarChart chart, List<Float> floatList) {
        if (chart != null && !ListUtils.isEmpty(floatList)) {
            float min = Collections.min(floatList);
            float minReal = min - Constants.CHART_RANGE_DEVIATION_VALUE;
            chart.getAxisLeft().setAxisMinimum(minReal);
            chart.getAxisRight().setAxisMinimum(minReal);
        }
    }

    public static void setHorizontalBarChartAxisMaximum(HorizontalBarChart chart, List<Float> floatList) {
        if (chart != null && !ListUtils.isEmpty(floatList)) {
            float max = Collections.max(floatList);
            float maxReal = max + Constants.CHART_RANGE_DEVIATION_VALUE;
            chart.getAxisLeft().setAxisMaximum(maxReal);
            chart.getAxisRight().setAxisMaximum(maxReal);
        }
    }

    public static void setYAxisDefaultLabelCount(YAxis yAxis) {
        if (yAxis != null) {
            yAxis.setLabelCount(Constants.CHART_Y_LABEL_COUNT, false);
        }
    }

    public static void setYAxisDefaultLabelCount(YAxis yAxis, YAxis yAxis2) {
        setYAxisDefaultLabelCount(yAxis);
        setYAxisDefaultLabelCount(yAxis2);
    }

    public static void setYAxisLargeValueFormat(YAxis yAxis) {
        if (yAxis != null) {
            yAxis.setLabelCount(Constants.CHART_Y_LABEL_COUNT, false);
            yAxis.setValueFormatter(new LargeValueFormatter());
        }
    }

    public static void setYAxisLargeValueFormat(YAxis yAxis, YAxis yAxis2) {
        setYAxisLargeValueFormat(yAxis);
        setYAxisLargeValueFormat(yAxis2);
    }

    public static void setYAxisIntValueFormat(YAxis yAxis) {
        if (yAxis != null) {
            yAxis.setLabelCount(Constants.CHART_Y_LABEL_COUNT, false);
            yAxis.setValueFormatter(new IntValueFormatter());
        }
    }

    public static void setYAxisIntValueFormat(YAxis yAxis, YAxis yAxis2) {
        setYAxisIntValueFormat(yAxis);
        setYAxisIntValueFormat(yAxis2);
    }

    public static void setYAxisDoubleValueFormat(YAxis yAxis) {
        if (yAxis != null) {
            yAxis.setLabelCount(Constants.CHART_Y_LABEL_COUNT, false);
            yAxis.setValueFormatter(new DoubleValueFormatter());
        }
    }

    public static void setYAxisDoubleValueFormat(YAxis yAxis, YAxis yAxis2) {
        setYAxisDoubleValueFormat(yAxis);
        setYAxisDoubleValueFormat(yAxis2);
    }


    public static void setYAxisSpaceTop(YAxis yAxis) {
        if (yAxis != null) {
            yAxis.setSpaceTop(Constants.CHART_Y_SPACE_TOP_PERCENT);
        }
    }

    public static void setYAxisSpaceTop(YAxis yAxis, YAxis yAxis2) {
        setYAxisSpaceTop(yAxis);
        setYAxisSpaceTop(yAxis2);
    }

    public static void setChartDataLargeValueFormatter(ChartData chartData) {
        if (chartData != null) {
            chartData.setValueFormatter(new LargeValueFormatter());
        }
    }

    public static void setChartDataDoubleValueFormatter(ChartData chartData) {
        if (chartData != null) {
            chartData.setValueFormatter(new DoubleValueFormatter());
        }
    }

    public static void setChartDataIntValueFormatter(ChartData chartData) {
        if (chartData != null) {
            chartData.setValueFormatter(new IntValueFormatter());
        }
    }

    public static String getChartLabel(String s) {
        s = trimString(s);
        if (s.length() <= Constants.CHART_LABEL_STRING_MAX_COUNT) {
            return s;
        } else {
            return s.substring(0, 5) + Constants.ELLIPSIS_STRING;
        }
    }

    public static String getMultipleLineChartLabel(String s){
        s = trimString(s);
        List<String> partList = new ArrayList<>();
        int MAX_NUMBER = Constants.CHART_LABEL_STRING_MAX_COUNT;
        if (!StringUtils.isBlank(s)) {
            int maxLine = (s.length() + (MAX_NUMBER - 1)) / MAX_NUMBER;
            for (int i = 0; i < maxLine; ++i) {
                int start = i * MAX_NUMBER;
                int end = Math.min((i + 1) * MAX_NUMBER, s.length());
                String temp = s.substring(start, end);
                partList.add(temp);
            }
            String[] stringArray = partList.toArray(new String[0]);
            String result = join(stringArray, Constants.NEW_LINE_SEPARATOR);
            return result;
        } else {
            return s;
        }

    }

    public static boolean isStringMultipleLine(String s){
        s=trimString(s);
        if (s.contains(Constants.NEW_LINE_SEPARATOR)){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNearZero(float f) {
        return Math.abs(f) < Constants.TINY_NUMBER;
    }

    public static float getPercent(float item, float sum) {
        if (isNearZero(sum)) {
            return 0f;
        }
        float percent = item / sum;
        return percent;
    }

    public static boolean isItemPercentVerySmallCountLessThanFixedNumberFromNameValueBeanList(List<NameValueBean> list) {
        if (ListUtils.isEmpty(list)) {
            return true;
        } else {
            List<Float> fList = new ArrayList<>();
            for (int i = 0; i < list.size(); ++i) {
                NameValueBean bean = list.get(i);
                float f = getFloatFromPercentString(bean.getValue());
                fList.add(f);
            }
            return isItemPercentVerySmallCountLessThanFixedNumber(fList);
        }
    }

    public static boolean isItemPercentVerySmallCountLessThanFixedNumberFromInstitutionCharacterNameValueBeanList(List<InstitutionCharacterNameValueBean> list) {
        if (ListUtils.isEmpty(list)) {
            return true;
        } else {
            List<Float> fList = getFloatListFromInstitutionCharacterNameValueBeanList(list);
            return isItemPercentVerySmallCountLessThanFixedNumber(fList);
        }
    }

    public static boolean isItemPercentVerySmallCountLessThanFixedNumberFromNameValuePercentBeanList(List<NameValuePercentBean> list) {
        if (ListUtils.isEmpty(list)) {
            return true;
        } else {
            List<Float> fList = getFloatListFromNameValuePercentBeanList(list);
            return isItemPercentVerySmallCountLessThanFixedNumber(fList);
        }
    }

    public static boolean isItemPercentVerySmallCountLessThanFixedNumberFromMedicalAssistantMoneyConstitutionBeanList(List<MedicalAssistantMoneyConstitutionBean> list) {
        if (ListUtils.isEmpty(list)) {
            return true;
        } else {
            List<Float> fList = getFloatListFromMedicalAssistantMoneyConstitutionBeanList(list);
            return isItemPercentVerySmallCountLessThanFixedNumber(fList);
        }
    }

    public static boolean isItemPercentVerySmallCountLessThanFixedNumberFromSubsistenceApprovePovertyReasonNaturalBeanList(List<SubsistenceApprovePovertyReasonNaturalBean> list) {
        if (ListUtils.isEmpty(list)) {
            return true;
        } else {
            List<Float> fList = getFloatListFromSubsistenceApprovePovertyReasonNaturalBeanList(list);
            return isItemPercentVerySmallCountLessThanFixedNumber(fList);
        }
    }

    public static boolean isItemPercentVerySmallCountLessThanFixedNumberFromTempDisasterAssistancePercentageBeanList(List<TempDisasterAssistancePercentageBean> list) {
        if (ListUtils.isEmpty(list)) {
            return true;
        } else {
            List<Float> fList = getFloatListFromTempDisasterAssistancePercentageBeanList(list);
            return isItemPercentVerySmallCountLessThanFixedNumber(fList);
        }
    }

    public static boolean isItemPercentVerySmallCountLessThanFixedNumber(List<Float> list) {
        return isItemPercentVerySmallCountLessThan(list, Constants.CHART_PERCENT_SHOW_THRESHOLD);
    }

    public static boolean isItemPercentVerySmallCountLessThan(List<Float> list, int number) {
        if (ListUtils.isEmpty(list)) {
            return true;
        }
        int smallCount = 0;
        for (int i = 0; i < list.size(); ++i) {
            float f = list.get(i);
            if (isItemPercentVerySmall(f, list)) {
                smallCount += 1;
            }
        }
        if (smallCount < number) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isItemPercentVerySmall(float itemValue, List<Float> list) {
        if (!ListUtils.isEmpty(list)) {
            float sum = 0f;
            for (int i = 0; i < list.size(); ++i) {
                sum += list.get(i);
            }
            float percent = getPercent(itemValue, sum);
            if (percent < Constants.CHART_PERCENT_VERY_SMALL) {
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    public static boolean isItemPercentVerySmallInNameValueBeanList(float itemValue, List<NameValueBean> list) {
        if (ListUtils.isEmpty(list)) {
            return true;
        } else {
            List<Float> fList = new ArrayList<>();
            for (int i = 0; i < list.size(); ++i) {
                NameValueBean bean = list.get(i);
                float f = getFloatFromPercentString(bean.getValue());
                fList.add(f);
            }
            return isItemPercentVerySmall(itemValue, fList);
        }
    }

    public static boolean isItemPercentVerySmallInInstitutionCharacterNameValueBeanList(float itemValue, List<InstitutionCharacterNameValueBean> list) {
        if (ListUtils.isEmpty(list)) {
            return true;
        } else {
            List<Float> fList = getFloatListFromInstitutionCharacterNameValueBeanList(list);
            return isItemPercentVerySmall(itemValue, fList);
        }
    }

    public static boolean isItemPercentVerySmallInNameValuePercentBeanList(float itemValue, List<NameValuePercentBean> list) {
        if (ListUtils.isEmpty(list)) {
            return true;
        } else {
            List<Float> fList = getFloatListFromNameValuePercentBeanList(list);
            return isItemPercentVerySmall(itemValue, fList);
        }
    }

    public static boolean isItemPercentVerySmallInMedicalAssistantMoneyConstitutionBeanList(float itemValue, List<MedicalAssistantMoneyConstitutionBean> list) {
        if (ListUtils.isEmpty(list)) {
            return true;
        } else {
            List<Float> fList = getFloatListFromMedicalAssistantMoneyConstitutionBeanList(list);
            return isItemPercentVerySmall(itemValue, fList);
        }
    }

    public static boolean isItemPercentVerySmallInSubsistenceApprovePovertyReasonNaturalBeanList(float itemValue, List<SubsistenceApprovePovertyReasonNaturalBean> list) {
        if (ListUtils.isEmpty(list)) {
            return true;
        } else {
            List<Float> fList = getFloatListFromSubsistenceApprovePovertyReasonNaturalBeanList(list);
            return isItemPercentVerySmall(itemValue, fList);
        }
    }

    public static boolean isItemPercentVerySmallInTempDisasterAssistancePercentageBeanList(float itemValue, List<TempDisasterAssistancePercentageBean> list) {
        if (ListUtils.isEmpty(list)) {
            return true;
        } else {
            List<Float> fList = getFloatListFromTempDisasterAssistancePercentageBeanList(list);
            return isItemPercentVerySmall(itemValue, fList);
        }
    }

    public static List<Float> getFloatListFromNameValueBeanList(List<NameValueBean> list) {
        if (ListUtils.isEmpty(list)) {
            return new ArrayList<>();
        } else {
            List<Float> fList = new ArrayList<>();
            for (int i = 0; i < list.size(); ++i) {
                NameValueBean bean = list.get(i);
                float f = getFloatFromPercentString(bean.getValue());
                fList.add(f);
            }
            return fList;
        }
    }

    public static List<Float> getFloatListFromInstitutionCharacterNameValueBeanList(List<InstitutionCharacterNameValueBean> list) {
        if (ListUtils.isEmpty(list)) {
            return new ArrayList<>();
        } else {
            List<Float> fList = new ArrayList<>();
            for (int i = 0; i < list.size(); ++i) {
                InstitutionCharacterNameValueBean bean = list.get(i);
                float f = getFloatFromPercentString(bean.getValue());
                fList.add(f);
            }
            return fList;
        }
    }

    public static List<Float> getFloatListFromNameValuePercentBeanList(List<NameValuePercentBean> list) {
        if (ListUtils.isEmpty(list)) {
            return new ArrayList<>();
        } else {
            List<Float> fList = new ArrayList<>();
            for (int i = 0; i < list.size(); ++i) {
                NameValuePercentBean bean = list.get(i);
                float f = getFloatFromPercentString(bean.getValue());
                fList.add(f);
            }
            return fList;
        }
    }

    public static List<Float> getFloatListFromMedicalAssistantMoneyConstitutionBeanList(List<MedicalAssistantMoneyConstitutionBean> list) {
        if (ListUtils.isEmpty(list)) {
            return new ArrayList<>();
        } else {
            List<Float> fList = new ArrayList<>();
            for (int i = 0; i < list.size(); ++i) {
                MedicalAssistantMoneyConstitutionBean bean = list.get(i);
                float f = getFloatFromPercentString(bean.getValue());
                fList.add(f);
            }
            return fList;
        }
    }

    public static List<Float> getFloatListFromSubsistenceApprovePovertyReasonNaturalBeanList(List<SubsistenceApprovePovertyReasonNaturalBean> list) {
        if (ListUtils.isEmpty(list)) {
            return new ArrayList<>();
        } else {
            List<Float> fList = new ArrayList<>();
            for (int i = 0; i < list.size(); ++i) {
                SubsistenceApprovePovertyReasonNaturalBean bean = list.get(i);
                float f = bean.getPercent();
//                float f = getFloatFromPercentString(bean.getPercent());
                fList.add(f);
            }
            return fList;
        }
    }

    public static List<Float> getFloatListFromTempDisasterAssistancePercentageBeanList(List<TempDisasterAssistancePercentageBean> list) {
        if (ListUtils.isEmpty(list)) {
            return new ArrayList<>();
        } else {
            List<Float> fList = new ArrayList<>();
            for (int i = 0; i < list.size(); ++i) {
                TempDisasterAssistancePercentageBean bean = list.get(i);
                float f = getFloatFromPercentString(bean.getValue());
                fList.add(f);
            }
            return fList;
        }
    }

    public static String getNonscientificNumberStringFromString(String s) {
        s = trimString(s);
        double d = getDoubleValueFromString(s);
        String result = nonscientificNumberFormater.format(d);
        return result;
    }

    public static String getNonscientificNumberStringFromDouble(double d) {
        String s = String.valueOf(d);
        return getNonscientificNumberStringFromString(s);
    }

    public static String getNonscientificNumberStringFromFloat(Float f) {
        if (f == null) {
            return Constants.EMPTY_STRING;
        }
//        String s = String.valueOf(f);
//        float fl = getFloatFromString(s);
        float fl = f.floatValue();
        double d = (double) fl;
        String result = getNonscientificNumberStringFromDouble(d);
        return result;
    }

    public static String getStatisticsSerialNumberStringFromInt(int index){
        return String.valueOf(index);
    }

    public static String getNumberStringFromString(String s){
        if (s == null){
            return Constants.ZERO_STRING;
        } else {
            int i = getIntFromString(s);
            String result = String.valueOf(i);
            return result;
        }
    }
    public static void showDateNewDialogWithMaxDate1(Context context, String initialString, String tag, Calendar maxDate, DatePickerDialog.OnDateSetListener callback,Boolean maxdate) {
        Calendar now = Calendar.getInstance();
        int[] ymdArray = getYearMonthDayFromCanonicalString(initialString);
        int year = now.get(Calendar.YEAR);
//        int month = now.get(Calendar.MONTH) - 1;
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        if (ymdArray != null && ymdArray.length == 3) {
            year = ymdArray[0];
            month = ymdArray[1] - 1;
            day = ymdArray[2];
        }
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                callback,
                year,
                month,
                day
        );

        dpd.dismissOnPause(false);
        dpd.showYearPickerFirst(false);
        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
        dpd.setAccentColor(context.getResources().getColor(R.color.colorTheme));
//        dpd.setAccentColor(Color.parseColor("#9C27B0"));
        dpd.setTitle(context.getResources().getString(R.string.please_select_date));
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.WEEK_OF_MONTH, -1);
        Calendar date3 = Calendar.getInstance();
        date3.add(Calendar.WEEK_OF_MONTH, 1);
        Calendar[] days = {date1, date2, date3};
        dpd.setHighlightedDays(days);
        if (maxdate){
            dpd.setMaxDate(maxDate);
        }

        dpd.show(((Activity) context).getFragmentManager(), tag);
//        dpd.show(getChildFragmentManager(), "Datepickerdialog1");
    }

    /**
     * 获得指定文件的byte数组
     */

    public static byte[] getBytesFromFilePath(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static boolean isUrlNetwork(String url){
        url=trimString(url);
        boolean b = URLUtil.isNetworkUrl(url);
        return b;
    }

    public static float getSum(List<Float> floatList){
        float sum = 0f;
        if (!ListUtils.isEmpty(floatList)){
            for (int i = 0; i < floatList.size(); ++i){
                float f = floatList.get(i);
                sum+=f;
            }
        }
        return sum;
    }

    public static float getMax(List<Float> floatList){
        float sum = 0f;
        if (!ListUtils.isEmpty(floatList)){
            sum=Collections.max(floatList);
        }
        return sum;
    }

    public static float getMin(List<Float> floatList){
        float sum = 0f;
        if (!ListUtils.isEmpty(floatList)){
            sum=Collections.min(floatList);
        }
        return sum;
    }

    public static void setViewListener(View view, final Runnable runnable){
        if (view!=null && runnable!=null){
            RxView.clicks(view)
                    .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Object>() {
                        @Override
                        public void call(Object o) {
                            runnable.run();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {

                        }
                    });
        }
    }

    //=============================================================
    public static String getLoginMethodDeviceString(){
        return Version.LOGIN_METHOD_DEVICE_STRING;
    }

    public static String getLoginMethodDeviceValueString(){
        return Version.LOGIN_METHOD_DEVICE_VALUE_STRING;
    }

    public static String getNonloginMethodDeviceString(Context context){
        return IdentityManager.getDeviceForFingertip(context);
    }

    public static String getNonloginMethodTokenString(Context context){
        return IdentityManager.getTokenForFingertip(context);
    }


    public static String getLoginMethodSignString(String body, String timestamp){
        body=trimString(body);
        timestamp=trimString(timestamp);
        String device = getLoginMethodDeviceValueString();
        String s = device+body+timestamp;
        String result = MD5.md5(s);
        return result;
    }

    public static String getNonloginMethodSignString(Context context, String body, String timestamp){
        body=trimString(body);
        timestamp=trimString(timestamp);
        String device = getNonloginMethodDeviceString(context);
        String token = getNonloginMethodTokenString(context);
        String s = token+device+body+timestamp;
        String result = MD5.md5(s);
        return result;
    }

    public static long getTimestamp(){
        long result = System.currentTimeMillis();
        return result;
    }

    public static String getTimestampString(){
        long value = getTimestamp();
        String result = String.valueOf(value);
        return result;
    }

    public static void setTextMarquee(TextView textView) {
        if (textView != null) {
            textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textView.setSingleLine(true);
            textView.setSelected(true);
            textView.setFocusable(true);
            textView.setFocusableInTouchMode(true);
        }
    }

    public static void show(View view){
        if (view!=null){
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void hide(View view){
        if (view!=null){
            view.setVisibility(View.GONE);
        }
    }

    public static String getLeading10Substring(String s){
        s=trimString(s);
        if (s.length()>10){
            s=s.substring(0, 10);
        }
        return s;
    }


    public static Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity)cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper)cont).getBaseContext());

        return null;
    }


    public static void showSingleChoiceStringListMaterialDialogEx(Context context, String title, String msg,
                                                                  List<IStringRepresentationAndValue> list, int defaultSelectedIndex,
                                                                  MaterialDialog.ListCallbackSingleChoice callback) {
        ColorStateList csl = getColorStateList();
        List<String> stringList = new ArrayList<>();
        if (list==null){
            list=new ArrayList<>();
        }
        for (int i = 0; i < list.size(); ++i){
            IStringRepresentationAndValue sv = list.get(i);
            String representation = sv.getStringRepresentation();
            stringList.add(representation);
        }
        new MaterialDialog.Builder(context)
                .title(title)
                .content(msg)
                .items(stringList)
                .choiceWidgetColor(csl)
                .autoDismiss(true)
                .negativeText(Constants.CANCEL)
                .itemsCallbackSingleChoice(defaultSelectedIndex, callback)
                .show();
    }
    //根据身份证号输出年龄
    public static int IdNOToAge(String IdNO) {
        int leh = IdNO.length();
        String dates = "";
        if (leh == 18) {
            int u = 0;
            try {
                int se = Integer.valueOf(IdNO.substring(leh - 1)) % 2;
                dates = IdNO.substring(6, 10);
                SimpleDateFormat df = new SimpleDateFormat("yyyy");
                String year = df.format(new Date());
                u = Integer.parseInt(year) - Integer.parseInt(dates);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return u;
        } else {
            try {
                dates = IdNO.substring(6, 8);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Integer.parseInt(dates);
        }
    }
    public static void showDateNewDialogWithMaxDate(Context context, String initialString, String tag, Calendar maxDate, DatePickerDialog.OnDateSetListener callback) {
        Calendar now = Calendar.getInstance();
        int[] ymdArray = getYearMonthDayFromCanonicalString(initialString);
        int year = now.get(Calendar.YEAR);
//        int month = now.get(Calendar.MONTH) - 1;
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        if (ymdArray != null && ymdArray.length == 3) {
            year = ymdArray[0];
            month = ymdArray[1] - 1;
            day = ymdArray[2];
        }
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                callback,
                year,
                month,
                day
        );
        dpd.dismissOnPause(false);
        dpd.showYearPickerFirst(false);
        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
        dpd.setAccentColor(context.getResources().getColor(R.color.colorTheme));
//        dpd.setAccentColor(Color.parseColor("#9C27B0"));
        dpd.setTitle(context.getResources().getString(R.string.please_select_date));
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.WEEK_OF_MONTH, -1);
        Calendar date3 = Calendar.getInstance();
        date3.add(Calendar.WEEK_OF_MONTH, 1);
        Calendar[] days = {date1, date2, date3};
        dpd.setHighlightedDays(days);
        dpd.setMaxDate(maxDate);
        dpd.setTitle("");
        dpd.show(((Activity) context).getFragmentManager(), tag);
    }
}
