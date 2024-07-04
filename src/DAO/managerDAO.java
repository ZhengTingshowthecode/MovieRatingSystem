package DAO;

import ENTITY.manager;
import UTIL.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class managerDAO {
    public manager checkManagerLogin(String username, String pwd){
        manager manager=null;
        Connection cn;
        PreparedStatement pst;
        ResultSet rs;
        try{
            cn= JDBC.getCn();
            String sb = " select manID,manPWD from manager where manID=? and manPWD=?";
            pst=cn.prepareStatement(sb);
            pst.setString(1,username);
            pst.setString(2,pwd);
            rs=pst.executeQuery();
            while(rs.next()){
                manager =new manager();
                manager.setManID(rs.getString("manID"));
                manager.setManPWD(rs.getString("manPWD"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            JDBC.closeCn();
        }
        return manager;
    }
}
