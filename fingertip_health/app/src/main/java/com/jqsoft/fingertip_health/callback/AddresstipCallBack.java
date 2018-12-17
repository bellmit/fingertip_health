package com.jqsoft.fingertip_health.callback;


import com.jqsoft.fingertip_health.bean.fingertip.gdws_sys_area;

public interface AddresstipCallBack {
    public void selectProvince(gdws_sys_area province);
    public void selectCity(gdws_sys_area city);
    public void selectDistrict(gdws_sys_area district);
    public void selectDistrict4(gdws_sys_area district4);
    public void selectDistrict5(gdws_sys_area district5);
    public void selectDistrict6(gdws_sys_area district6);


//    public void selectProvince(AddressManager.Province province);
//    public void selectCity(AddressManager.City city);
//    public void selectDistrict(AddressManager.District district);
}
