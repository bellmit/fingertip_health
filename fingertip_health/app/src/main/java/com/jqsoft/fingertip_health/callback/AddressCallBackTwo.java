package com.jqsoft.fingertip_health.callback;


import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;


public interface AddressCallBackTwo {
    public void selectProvince(SRCLoginAreaBean province);
    public void selectCity(SRCLoginAreaBean city);
    public void selectDistrict(SRCLoginAreaBean district);
//    public void selectProvince(AddressManager.Province province);
//    public void selectCity(AddressManager.City city);
//    public void selectDistrict(AddressManager.District district);
}
