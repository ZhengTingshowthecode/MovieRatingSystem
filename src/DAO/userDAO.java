package DAO;

import ENTITY.user;
import UTIL.JDBC;
import java.sql.*;

public class userDAO {
    public user checkLogin(String username, String pwd){
        user user=null;
        Connection cn;
        PreparedStatement pst;
        ResultSet rs;
        try{
            cn= JDBC.getCn();
            String sb = " select userID,Nickname from users where userID=? and PWD=?";
            pst=cn.prepareStatement(sb);
            pst.setString(1,username);
            pst.setString(2,pwd);
            rs=pst.executeQuery();
            while(rs.next()){
                user=new user();
                user.setUserID(rs.getString("userID"));
                user.setNickname(rs.getString("Nickname"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            JDBC.closeCn();
        }
        return user;
    }
    public void register(String userID, String Nickname, String PWD, String Phone){
        Connection cn;
        PreparedStatement pst;
        StringBuilder sb=null;
        ResultSet rs;
        try{
            cn= JDBC.getCn();
            sb = new StringBuilder();
            sb.append("insert into users(userID,Nickname,PWD,Phone) values(?,?,?,?)");
            pst=cn.prepareStatement(sb.toString());
            pst.setString(1,userID);
            pst.setString(2,Nickname);
            pst.setString(3,PWD);
            pst.setString(4,Phone);
            pst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            JDBC.closeCn();
        }
    }
}