package com.neusoft.dao;

import com.neusoft.domain.Admin;

public interface AdminDao {
    public Admin getAdminBYNameByPass(String adminName,String password);

}
