package com.neusoft.dao.Impl;

import com.neusoft.dao.BusinessDao;
import com.neusoft.domain.Business;
import com.neusoft.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImpl implements BusinessDao {
    private Connection conn =null;
    private PreparedStatement pstmt =null;
    private ResultSet rs =null;

    @Override
    public List<Business> listBusiness(String businessName, String businessAddress) {
        List<Business> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from business where 1=1");
        if (businessName != null && !businessName.equals("")){
            // 传入了商家名
            sql.append(" and businessName like '%").append(businessName).append("%' ");
        }
        if (businessAddress != null && !businessAddress.equals("")){
            // 传入了商家名
            sql.append(" and businessAddress like '%").append(businessAddress).append("%' ");
        }
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            while (rs.next()){
                Business business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStartPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
                list.add(business);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt, conn, rs);
        }


        return list;
    }

    @Override
    public int saveBusiness(String businessName) {
        int businessId = 0;
        // 附带一个初始密码
        String sql = "insert into business(businessName, password)values(?, '123456')";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // 可以在prepareStatement中设置返回自增长列的值
            pstmt.setString(1, businessName);
            pstmt.executeUpdate();
            // 获取自增长的列
            rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                businessId = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt, conn, rs);
        }
        return businessId;
    }

    @Override
    public Business getBusinessByNameByPass(Integer businessId, String password) {
        Business business=null;
        String sql="select * from business where businessId = ? and password = ?";
        try {
            conn=JDBCUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, businessId);
            pstmt.setString(2,password);
            rs=pstmt.executeQuery();
            while (rs.next()){
                business=new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                //todo
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn,rs);
        }
        return business;
    }

    @Override
    public int removeBusiness(int businessId) {
        int result=0;
        String sql="delete from business where businessId=?";

        try {
            conn=JDBCUtils.getConnection();
            //手动开启事物
            conn.setAutoCommit(false);
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,businessId);
            result=pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException throwables) {
            result=0;
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn,rs);
        }
        return result;

    }

    @Override
    public Business getBusinessByBusinessId(Integer businessId) {
        Business business=null;
        String sql="select * from business where businessId = ? ";
        try {
            conn=JDBCUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,businessId);
            rs=pstmt.executeQuery();
            while (rs.next()){
                business= new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStartPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryprice"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn,rs);
        }
        return business;
    }

    @Override
    public int updateBusiness(Business business) {
        int result = 0;
        String sql="update business set businessName = ?,businessAddress =?,businessExplain=?,starPrice=?,deliveryPrice=? where businessId = ? ";
        try {
            conn=JDBCUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,business.getBusinessId());
            pstmt.setString(2,business.getBusinessExplain());
            pstmt.setString(2,business.getBusinessAddress());
            pstmt.setString(2,business.getBusinessName());
            pstmt.setDouble(2,business.getStartPrice());
            pstmt.setDouble(2,business.getDeliveryPrice());
            result=pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn,rs);
        }
        return result;
    }

    @Override
    public int updateBusinessByPassword(Integer businessId, String password) {
        int result=0;
        String sql="update business set password=? where businessId=?";
        try {
            conn=JDBCUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,businessId);
            pstmt.setString(2,password);
            result=pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn,rs);
        }
        return result;
    }

    @Override
    public Business getBusinessById(Integer businessId) {
        Business business=null;
        String sql="select *from business where businessId=?";
        try {
            conn=JDBCUtils.getConnection();
            pstmt=conn.prepareStatement(sql) ;
            rs=pstmt.executeQuery();
            while (rs.next()){
                business=new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setStartPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
                business.setPassword(rs.getString("password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn,rs);
        }
        return business;
    }

}