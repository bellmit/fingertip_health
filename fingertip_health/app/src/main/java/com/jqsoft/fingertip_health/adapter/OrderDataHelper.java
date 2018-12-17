package com.jqsoft.fingertip_health.adapter;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.UrbanLowFujianBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2016/11/8.
 */

public class OrderDataHelper {

    /**
     * List<Object>有三种数据类型：
     * 1、GoodsOrderInfo 表示每个小订单的头部信息（订单号、订单状态、店铺名称）
     * 2、OrderGoodsItem 表示小订单中的商品
     * 3、OrderPayInfo 表示大订单的支付信息（金额、订单状态）
     * @param resultList
     * @return
     */
    public static List<Object> getDataAfterHandle(List<UrbanLowFujianBean> resultList) {
        List<Object> dataList = new ArrayList<Object>();

        //遍历每一张大订单
        for (int i=0;i<resultList.size();i++) {
            //大订单支付的金额核定单状态
            /*OrderPayInfo orderPayInfo = new OrderPayInfo();
            orderPayInfo.setTotalAmount(orderSummary.getTotalPrice());
            orderPayInfo.setStatus(orderSummary.getStatus());
            orderPayInfo.setId(orderSummary.getId());
*/
            //小订单商品的
            List<UrbanLowFujianBean.Jiuzhuxiang> orderDetailList = resultList.get(i).getFiles();
            Map<String, List<UrbanLowFujianBean.Jiuzhuxiang>> orderGoodsMap = new HashMap<String, List<UrbanLowFujianBean.Jiuzhuxiang>>();
            Map<String, UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang> orderInfoMap = new HashMap<String, UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang>();

            //遍历每个大订单里面的小订单
            for (int j=0;j<orderDetailList.size();j++) {
                //获取小订单里面的商铺信息的订单号
         //       String orderCode = orderGoodsItem.getFileCodeName();
             //   orderDetailList.set()
                String orderCode = orderDetailList.get(j).getFileCode();
                //拿到相对应订单号的所有商品
                List<UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang> goodsList =orderDetailList.get(j).getDetails();


                if (goodsList == null) {
                    goodsList = new ArrayList<>();
                  //  orderGoodsMap.put(orderCode, goodsList.get(0).);
                }

              /*  UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang orderInfo =new UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang;
                if(orderInfo == null) {
                    orderInfo = new GoodsOrderInfo();
                    orderInfo.setOrderCode(orderCode);
                    orderInfo.setShopName(orderGoodsItem.getOrder().getShopName());
                    orderInfo.setStatus(orderGoodsItem.getOrder().getStatus());
                    orderInfoMap.put(orderCode, orderInfo);
                }*/

           //     goodsList.add(orderDetailList.get(j));
                //如果goodsList为空，则新建；而且把这个订单号的orderGoodsMap持有订单的对象goodsList
              /*  if (goodsList == null) {
                    goodsList = new ArrayList<>();
                    orderGoodsMap.put(orderCode, goodsList);
                }
                //goodsList添加商品的对象，因为orderGoodsMap已经持有这个订单号的goodsList对象，所以不用重新put
                goodsList.add(orderGoodsItem);

                //把小订单的店铺信息赋给GoodsOrderInfo对象，并加入到orderInfoMap
                UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang orderInfo = orderInfoMap.get(orderCode);
                if(orderInfo == null) {
                 //   orderInfo = new UrbanLowFujianBean.Jiuzhuxiang.Shenqingxiang();
                    orderInfo.setFileId(orderCode);
                 //   orderInfo.setFileName(orderGoodsItem.getDetails().get());
                //    orderInfo.setStatus(orderGoodsItem.getOrder().getStatus());
                    orderInfoMap.put(orderCode, orderInfo);
                }*/
            }
            //把所有数据按照头部、内容和尾部三个部分排序好
            Set<String> keySet = orderGoodsMap.keySet();
            for(String orderCode : keySet) {
                dataList.add(orderInfoMap.get(orderCode));
                dataList.addAll(orderGoodsMap.get(orderCode));
            }
        //    dataList.add(orderPayInfo);
        }

        return dataList;
    }

}
