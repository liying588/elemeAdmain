package select.domain;

import com.neusoft.domain.Admin;
import com.neusoft.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCAdmin {
    public static void main(String[] args) {
        ArrayList<Admin> list = new JDBCAdmin().finalAll();
        for (Admin admin:list) {
            System.out.println(admin);
        }
    }
    public ArrayList<Admin> finalAll(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Admin> list=null;
        try{
            conn= JDBCUtils.getConnection();
            String sql="select * from admin";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            list = new ArrayList<>();
            while (rs.next()){
                Integer adminId=rs.getInt("adminId");
                String adminName=rs.getString("adminName");
                String password=rs.getString("password");
                Admin admin = new Admin();
                admin.setAdminId(adminId);
                admin.setAdminName(adminName);
                admin.setPassword(password);
                list.add(admin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  finally {
           if (stmt!=null){
               try{
                   stmt.close();
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
               }
           }
           if (conn!=null){
               try{
                   conn.close();
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
               }
           }
           if (rs!=null){
               try{
                rs.close();
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
               }
           }
        }
        return  list;
    }

}
