package com.neusoft.dao.Impl;

import com.mysql.jdbc.PreparedStatement;
import com.neusoft.dao.AdminDao;
import com.neusoft.domain.Admin;
import com.neusoft.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDAOImpl implements AdminDao {
    private Connection conn =null;
    private Statement stmt =null;
    private ResultSet rs =null;
    @Override
    public Admin getAdminByNameByPass(String adminName, String password) {
        try{
            conn= JDBCUtils.getConnection();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(stmt, conn, rs);
        }


        return null;
    }
}
