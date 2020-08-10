package com.neusoft.dao.Impl;

import com.neusoft.dao.AdminDao;
import com.neusoft.domain.Admin;
import com.neusoft.utils.JDBCUtils;

import java.sql.*;

public class AdminDaoImpl implements AdminDao {
    private  Connection conn =null;
    private PreparedStatement pstmt =null;
    private ResultSet rs =null;
    @Override
    public Admin getAdminBYNameByPass(String adminName, String password) {
            Admin admin = null;
            String sql = "select * from admin where adminName = ? and password = ?";
            try{
                conn = JDBCUtils.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, adminName);
                pstmt.setString(2, password);
                rs = pstmt.executeQuery();
                while (rs.next()){
                    admin = new Admin();
                    admin.setAdminId(rs.getInt("adminId"));
                    admin.setAdminName(rs.getString("adminName"));
                    admin.setPassword(rs.getString("password"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtils.close(pstmt, conn, rs);
            }

            return admin;
        }

    }
