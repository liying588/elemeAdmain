package com.neusoft.view;

import com.neusoft.domain.Business;

public interface BusinessView {
    public void listBusinessAll();
    public void listBusinessBySearch();
    public void saveBusiness();

    //添加一个商家登录验证的方法
    public Business login();
    public void removeBusiness();
    //显示商家信息
    public void showBusinessInfo(Integer businessId);
    //修改商家
    public  void  updateBusinessInfo(Integer businessId);

    public  void  updateBusinessByPassword(Integer businessId);

}