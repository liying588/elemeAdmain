package com.neusoft.dao.Impl;

import com.neusoft.dao.FoodDao;
import com.neusoft.domain.Food;
import com.neusoft.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public List<Food> findALL(Integer businessId) {
        ArrayList<Food> list = new ArrayList<>();
        String sql = "select * from food where businessId= ?";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, businessId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setFoodId(rs.getInt("foodId"));
                food.setFoodName(rs.getString("foodName"));
                food.setFoodExplain(rs.getString("foodExplain"));
                food.setFoodPrice(rs.getDouble("foodPrice"));
                food.setBusinessId(rs.getInt("businessId"));
                list.add(food);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn, rs);
        }
        return list;
    }

    @Override
    public Food getFoodById(Integer foodId) {
        Food food = null;
        String sql = "select * from food where foodId=?";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                food = new Food();
                food.setFoodId(rs.getInt("foodId"));
                food.setFoodName(rs.getString("foodName"));
                food.setFoodExplain(rs.getString("foodExplain"));
                food.setFoodPrice(rs.getDouble("foodPrice"));
                food.setBusinessId(rs.getInt("businessId"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn, null);
        }
        return food;
    }

    @Override
    public int saveFood(Food food) {
        int result = 0;
        String sql="insert into food values(null,?,?,?,?)";
        try {
            conn=JDBCUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,food.getFoodName());
            pstmt.setString(2,food.getFoodExplain());
            pstmt.setDouble(3,food.getFoodPrice());
            result=pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn,rs);
        }
        return result;
    }

    @Override
    public int updateFood(Food food) {
        int result=0;
        String sql="update food set foodName=?,foodExplain=?,foodPrice=? where foodId=?";
        try {
            conn=JDBCUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,food.getFoodName());
            pstmt.setString(2,food.getFoodExplain());
            pstmt.setDouble(3,food.getFoodPrice());
            pstmt.setInt(4,food.getFoodId());
            result=pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn,rs);
        }
        return result;
    }

    @Override
    public int removeFood(Integer foodId) {
        int result=0;
        String sql="delete from food where foodId=?";
        try {
            conn=JDBCUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,foodId);
            result=pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn,rs);
        }
        return result;
    }
}
