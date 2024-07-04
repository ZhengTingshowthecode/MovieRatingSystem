package DAO;

import ENTITY.movies_info;
import UTIL.pageModel;
import UTIL.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class moviesDAO {
    /**
     * 主界面的电影表显示
     */
    public List<movies_info> getMovies(pageModel pageModel) {
        Connection cn;
        PreparedStatement pst;
        ResultSet rs;
        List<movies_info> list = new ArrayList<>();
        try {
            cn = JDBC.getCn();
            pst = cn.prepareStatement("select Title,ReleaseYear,Duration,Genre,movieLanguage,Country,Synopsis,Score" +
                    " from movies limit ?,?");
            pst.setInt(1, (pageModel.getPageNO() - 1) * pageModel.getPageSize());
            pst.setInt(2, pageModel.getPageSize());
            rs = pst.executeQuery();
            while (rs.next()) {
                movies_info movies = new movies_info();
                movies.setMovieName(rs.getString("Title"));
                movies.setReleaseYear(rs.getString("ReleaseYear"));
                movies.setDuration(rs.getInt("Duration"));
                movies.setGenre(rs.getString("Genre"));
                movies.setMovieLanguage(rs.getString("movieLanguage"));
                movies.setCountry(rs.getString("Country"));
                movies.setSynopsis(rs.getString("Synopsis"));
                movies.setScore(rs.getFloat("Score"));
                list.add(movies);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return list;
    }
    public Long getMovieTotal() {
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        long totalCnt = 0;
        try {
            cn = JDBC.getCn();
            StringBuilder sb;
            sb = new StringBuilder("select count(*) from movies");
            pst = cn.prepareStatement(sb.toString());
            rs = pst.executeQuery();
            if (rs.next()) {
                totalCnt = rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return totalCnt;
    }



    /**
     *管理员查看所有用户的所有评论信息
     */
    public String managerAllComments(){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder sb=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select userID,movieID,commentText,commentTime from comments";
            pst = cn.prepareStatement(sb2);
            rs = pst.executeQuery();
            while (rs.next()) {
                sb.append(rs.getString("userID")).append(" 在 ").append(rs.getString("commentTime"))
                                .append(" 在 ").
                        append("电影").append(rs.getString("movieID")).append(" 评论 ").append('\n').
                        append(rs.getString("commentText")).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return sb.toString();
    }
    /**
     * 管理员删除用户的不当评论
     */
    public void deleteComment(String userID,String movieID,String text){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder sb=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="delete from comments where userID=? and movieID=? and commentText=? ";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,userID);
            pst.setString(2,movieID);
            pst.setString(3,text);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }


    /**
     *管理员添加一部新上架的电影
     */
    public void mamagerAddMovie(String t1,String t2,String t3,String t4,String t5,String t6,String t7,String t8,String t9){
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = JDBC.getCn();
            String s = "insert into movies values(?,?,?,?,?,?,?,?,?);";
            pst = cn.prepareStatement(s);
            pst.setString(1, t1);
            pst.setString(2, t2);
            pst.setString(3, t3);
            pst.setString(4, t4);
            pst.setString(5, t5);
            pst.setString(6, t6);
            pst.setString(7, t7);
            pst.setString(8, t8);
            pst.setString(9, t9);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }
    /**
     * 管理员给新电影添加演员
     */
    public void managerAddAct(String id,String[]ss){
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = JDBC.getCn();
            if(ss.length==1){
                String s = "insert into movie_actor(movieID,ActorID) values(?,?);";
                pst = cn.prepareStatement(s);
                pst.setString(1, id);
                pst.setString(2, ss[0]);
            }else if(ss.length==2){
                String s = "insert into movie_actor(movieID,ActorID) values(?,?),(?,?);";
                pst = cn.prepareStatement(s);
                pst.setString(1, id);
                pst.setString(2, ss[0]);
                pst.setString(3, id);
                pst.setString(4, ss[1]);
            }else{
                String s = "insert into movie_actor(movieID,ActorID) values(?,?),(?,?),(?,?);";
                pst = cn.prepareStatement(s);
                pst.setString(1, id);
                pst.setString(2, ss[0]);
                pst.setString(3, id);
                pst.setString(4, ss[1]);
                pst.setString(5, id);
                pst.setString(6, ss[2]);
            }
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }
    /**
     * 新添加的演员信息
     */
    public void Act_Info(String[][]ss){
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = JDBC.getCn();
            if(ss.length==1){
                String s = "insert into actors values(?,?,?,?,?);";
                pst = cn.prepareStatement(s);
                pst.setString(1, ss[0][0]);
                pst.setString(2, ss[0][1]);
                pst.setString(3, ss[0][2]);
                pst.setString(4, ss[0][3]);
                pst.setString(5, ss[0][4]);
            }else if(ss.length==2){
                String s = "insert into actors values(?,?,?,?,?)," +
                        "(?,?,?,?,?);";
                pst = cn.prepareStatement(s);
                pst.setString(1, ss[0][0]);
                pst.setString(2, ss[0][1]);
                pst.setString(3, ss[0][2]);
                pst.setString(4, ss[0][3]);
                pst.setString(5, ss[0][4]);

                pst.setString(6, ss[1][0]);
                pst.setString(7, ss[1][1]);
                pst.setString(8, ss[1][2]);
                pst.setString(9, ss[1][3]);
                pst.setString(10, ss[1][4]);
            }else{
                String s = "insert into actors values(?,?,?,?,?)," +
                        "(?,?,?,?,?)," +
                        "(?,?,?,?,?);";
                pst = cn.prepareStatement(s);
                pst.setString(1, ss[0][0]);
                pst.setString(2, ss[0][1]);
                pst.setString(3, ss[0][2]);
                pst.setString(4, ss[0][3]);
                pst.setString(5, ss[0][4]);

                pst.setString(6, ss[1][0]);
                pst.setString(7, ss[1][1]);
                pst.setString(8, ss[1][2]);
                pst.setString(9, ss[1][3]);
                pst.setString(10, ss[1][4]);

                pst.setString(11, ss[2][0]);
                pst.setString(12, ss[2][1]);
                pst.setString(13, ss[2][2]);
                pst.setString(14, ss[2][3]);
                pst.setString(15, ss[2][4]);
            }
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }
    /**
     * 管理员给新电影添加导演
     */
    public void managerAddDir(String id,String[]ss){
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = JDBC.getCn();
            if(ss.length==1){
                String s = "insert into movie_director(movieID,directorID) values(?,?);";
                pst = cn.prepareStatement(s);
                pst.setString(1, id);
                pst.setString(2, ss[0]);
            }else if(ss.length==2){
                String s = "insert into movie_director(movieID,directorID) values(?,?),(?,?);";
                pst = cn.prepareStatement(s);
                pst.setString(1, id);
                pst.setString(2, ss[0]);
                pst.setString(3, id);
                pst.setString(4, ss[1]);
            }else{
                String s = "insert into movie_director(movieID,directorID) values(?,?),(?,?),(?,?);";
                pst = cn.prepareStatement(s);
                pst.setString(1, id);
                pst.setString(2, ss[0]);
                pst.setString(3, id);
                pst.setString(4, ss[1]);
                pst.setString(5, id);
                pst.setString(6, ss[2]);
            }
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }
    /**
     * 新添加的导演信息
     */
    public void Dir_Info(String[][]ss){
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = JDBC.getCn();
            if(ss.length==1){
                String s = "insert into directors values(?,?,?,?,?);";
                pst = cn.prepareStatement(s);
                pst.setString(1, ss[0][0]);
                pst.setString(2, ss[0][1]);
                pst.setString(3, ss[0][2]);
                pst.setString(4, ss[0][3]);
                pst.setString(5, ss[0][4]);
            }else if(ss.length==2){
                String s = "insert into directors values(?,?,?,?,?)," +
                        "(?,?,?,?,?);";
                pst = cn.prepareStatement(s);
                pst.setString(1, ss[0][0]);
                pst.setString(2, ss[0][1]);
                pst.setString(3, ss[0][2]);
                pst.setString(4, ss[0][3]);
                pst.setString(5, ss[0][4]);

                pst.setString(6, ss[1][0]);
                pst.setString(7, ss[1][1]);
                pst.setString(8, ss[1][2]);
                pst.setString(9, ss[1][3]);
                pst.setString(10, ss[1][4]);
            }else{
                String s = "insert into directors values(?,?,?,?,?)," +
                        "(?,?,?,?,?)," +
                        "(?,?,?,?,?);";
                pst = cn.prepareStatement(s);
                pst.setString(1, ss[0][0]);
                pst.setString(2, ss[0][1]);
                pst.setString(3, ss[0][2]);
                pst.setString(4, ss[0][3]);
                pst.setString(5, ss[0][4]);

                pst.setString(6, ss[1][0]);
                pst.setString(7, ss[1][1]);
                pst.setString(8, ss[1][2]);
                pst.setString(9, ss[1][3]);
                pst.setString(10, ss[1][4]);

                pst.setString(11, ss[2][0]);
                pst.setString(12, ss[2][1]);
                pst.setString(13, ss[2][2]);
                pst.setString(14, ss[2][3]);
                pst.setString(15, ss[2][4]);
            }
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }

    /**
     * 管理员删除某部电影
     */
    public void managerDeleteMovie(String movieID){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder sb=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="delete from movies where movieID=?";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,movieID);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }


    /**
     * 管理员修改信息界面   展示某电影所有信息
     */
    public String[] managerSearchAll(String moviename){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        String[]s=new String[8];
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select movieID,ReleaseYear,Duration,Genre,movieLanguage,Country,Synopsis,Score from movies " +
                    " where Title=?;";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,moviename);
            rs = pst.executeQuery();
            while (rs.next()) {
                s[0]=rs.getString("ReleaseYear");
                s[1]=rs.getString("Duration");
                s[2]=rs.getString("Genre");
                s[3]=rs.getString("movieLanguage");
                s[4]=rs.getString("Country");
                s[5]=rs.getString("Synopsis");
                s[6]=rs.getString("Score");
                s[7]=rs.getString("movieID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return s;
    }
    /**
     * 展示电影三个演员
     */
    public List<String> managerSearchAll_actor(String moviename) {
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        List<String> list=new ArrayList<>();
        String sb2 = null;
        try {
            cn = JDBC.getCn();
            sb2 = "select actorName from show03 " +
                    " where Title=?;";
            pst = cn.prepareStatement(sb2);
            pst.setString(1, moviename);
            rs = pst.executeQuery();
            int cnt=0;
            while (rs.next()) {
                list.add(rs.getString("actorName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return list;
    }
    /**
     * 展示电影三个导演
     */
    public List<String> managerSearchAll_director(String moviename) {
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        List<String> list=new ArrayList<>();
        String sb2 = null;
        try {
            cn = JDBC.getCn();
            sb2 = "select directorName from show01 " +
                    " where Title=?;";
            pst = cn.prepareStatement(sb2);
            pst.setString(1, moviename);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("directorName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return list;
    }
    /**
     * 管理员修改 影名
     */
    public void managerUpdateTitle(String id,String name){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="update movies set Title=? where movieID=?";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,name);
            pst.setString(2,id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }
    /**
     * 管理员修改 发行时间
     */
    public void managerUpdateReleaseYear(String id,String time){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="update movies set ReleaseYear=? where movieID=?";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,time);
            pst.setString(2,id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }
    /**
     * 管理员修改 时长
     */
    public void managerUpdateDuration(String id,int time){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="update movies set Duration=? where movieID=?";
            pst = cn.prepareStatement(sb2);
            pst.setInt(1,time);
            pst.setString(2,id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }
    /**
     * 管理员修改 流派
     */
    public void managerUpdateGenre(String id,String genre){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="update movies set Genre=? where movieID=?";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,genre);
            pst.setString(2,id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }
    /**
     * 管理员修改 语言
     */
    public void managerUpdateLanguage(String id,String language){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="update movies set movieLanguage=? where movieID=?";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,language);
            pst.setString(2,id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }
    /**
     * 管理员修改 国家
     */
    public void managerUpdateCountry(String id,String name){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="update movies set Country=? where movieID=?";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,name);
            pst.setString(2,id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }
    /**
     * 管理员修改 简介
     */
    public void managerUpdateSynopsis(String id,String name){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="update movies set Synopsis=? where movieID=?";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,name);
            pst.setString(2,id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }


    /**
     * 用户删除自己的评分
     */
    public void userDeleteScore(String userID,String name){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder s=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="call deleteScore(?,?)";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,userID);
            pst.setString(2,name);
            rs = pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }
    /**
     * 用户查询自己评过的所有分
     */
    public Vector<Vector<String>> userDeleteScore01(String userID){
        Vector<Vector<String>> res=new Vector<>();
        Vector<String> list=new Vector<>();
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder s=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select Title,Scores.score,scoreTime from movies,Scores where movies.movieID=Scores.movieID " +
                    "and userID=? ";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,userID);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Title"));
                list.add(rs.getString("Scores.score"));
                list.add(rs.getString("scoreTime"));
                res.add(list);
                list=new Vector<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return res;
    }


    /**
     * 用户删除往日的评论
     */
    public void userDeleteComment(String userID,String name,String text){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder s=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="call deleteComment(?,?,?)";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,userID);
            pst.setString(2,name);
            pst.setString(3,text);
            rs = pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }
    /**
     *用户查询自己本人写过的所有评论
     */
    public Vector<Vector<String>> userDeleteComment01(String userID){
        Vector<Vector<String>> res=new Vector<>();
        Vector<String> list=new Vector<>();
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder s=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select Title,Comments.commentText,commentTime from movies,Comments where " +
                    " movies.movieID=Comments.movieID " +
                    " and userID=? ";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,userID);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Title"));
                list.add(rs.getString("Comments.commentText"));
                list.add(rs.getString("commentTime"));
                res.add(list);
                list=new Vector<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return res;
    }


    /**
     * 用户评分
     */
    public void setScore(String id,String movie,float score){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder sb=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="call setscore(?,?,?)";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,id);
            pst.setString(2,movie);
            pst.setFloat(3,score);
            rs = pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }
    /**
     * 用户评论
     */
    public void setComment(String id,String movie,String text){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder sb=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="call setcomment(?,?,?)";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,id);
            pst.setString(2,movie);
            pst.setString(3,text);
            rs = pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
    }


    /**
     * 输入某电影的名称 得到它的所有评论
     */
    public String getComments(String jt){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder sb=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="call getComments(?)";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,jt);
            rs = pst.executeQuery();
            while (rs.next()) {
                sb.append(rs.getString(1)+"在"+rs.getString(3)+"评论:  "+
                        rs.getString(2)+"  ");
                sb.append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return sb.toString();
    }


    /**
     * 得到演员的生平信息
     */
    public String[] getActors(String name){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        String[]s=new String[3];
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select Gender,Birthdate,Nationality from actors where actorName=? ";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,name);
            rs = pst.executeQuery();
            while (rs.next()) {
                s[0]=rs.getString("Gender");
                s[1]=rs.getString("Birthdate");
                s[2]=rs.getString("Nationality");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return s;
    }
    /**
     * 得到某位演员演过的所有电影的重要信息
     */
    public String movie_actor(String name){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder s=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select Title,ReleaseYear,Duration,Score from m_a where actorName=? ";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,name);
            rs = pst.executeQuery();
            while (rs.next()) {
                s.append(rs.getString("Title")).append("  ").append("发布于").
                        append(rs.getString("ReleaseYear")).append("  ").
                        append(rs.getString("Duration")).append("分钟").append("  ").append("评分：").
                        append(rs.getString("Score")).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return s.toString();
    }
    /**
     * 得到导演的生平信息
     */
    public String[] getDirectors(String name){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        String[]s=new String[3];
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select Gender,Birthdate,Nationality from directors where directorName=? ";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,name);
            rs = pst.executeQuery();
            while (rs.next()) {
                s[0]=rs.getString("Gender");
                s[1]=rs.getString("Birthdate");
                s[2]=rs.getString("Nationality");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return s;
    }
    /**
     * 得到某位导演导过的所有电影的重要信息
     */
    public String movie_director(String name){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder s=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select Title,ReleaseYear,Duration,Score from m_d where directorName=? ";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,name);
            rs = pst.executeQuery();
            while (rs.next()) {
                s.append(rs.getString("Title")).append("  ").append("发布于").
                        append(rs.getString("ReleaseYear")).append("  ").
                        append(rs.getString("Duration")).append("分钟").append("  ").append("评分：").
                        append(rs.getString("Score")).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return s.toString();
    }


    /**
     * 得到某电影的具体详情
     */
    public String[] searchAll(String moviename){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        String[]s=new String[7];
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select ReleaseYear,Duration,Genre,movieLanguage,Country,Synopsis,Score from movies " +
                    " where Title=?;";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,moviename);
            rs = pst.executeQuery();
            while (rs.next()) {
                s[0]=rs.getString("ReleaseYear");
                s[1]=rs.getString("Duration");
                s[2]=rs.getString("Genre");
                s[3]=rs.getString("movieLanguage");
                s[4]=rs.getString("Country");
                s[5]=rs.getString("Synopsis");
                s[6]=rs.getString("Score");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return s;
    }
    /**
     *电影详情的界面的 某电影参演的所有演员名字
     */
    public String searchAll_actor(String moviename){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder sb=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select actorName from show03 " +
                    " where Title=?;";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,moviename);
            rs = pst.executeQuery();
            while (rs.next()) {
                sb.append(rs.getString("actorName")).append("  ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return sb.toString();
    }
    /**
     *电影详情的界面的 某电影参与的所有导演名字
     */
    public String searchAll_director(String moviename){
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder sb=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select directorName from show01 " +
                    " where Title=?;";
            pst = cn.prepareStatement(sb2);
            pst.setString(1,moviename);
            rs = pst.executeQuery();
            while (rs.next()) {
                sb.append(rs.getString("directorName")).append("  ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return sb.toString();
    }


    /**
     * 分别按照三个下拉框的内容进行排序的排行榜
     */
    public Vector<Vector<String>> orderReleaseYear(){
        Vector<Vector<String>> res=new Vector<>();
        Vector<String> list=new Vector<>();
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder s=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select Title,ReleaseYear,Genre,Score from movies order by ReleaseYear desc";
            pst = cn.prepareStatement(sb2);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Title"));list.add(rs.getString("ReleaseYear"));
                list.add(rs.getString("Genre"));list.add(rs.getString("Score"));
                res.add(list);
                list=new Vector<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return res;
    }
    public Vector<Vector<String>> orderGenre(){
        Vector<Vector<String>> res=new Vector<>();
        Vector<String> list=new Vector<>();
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder s=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select Title,ReleaseYear,Genre,Score from movies order by Genre";
            pst = cn.prepareStatement(sb2);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Title"));list.add(rs.getString("ReleaseYear"));
                list.add(rs.getString("Genre"));list.add(rs.getString("Score"));
                res.add(list);
                list=new Vector<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return res;
    }
    public Vector<Vector<String>> orderScore(){
        Vector<Vector<String>> res=new Vector<>();
        Vector<String> list=new Vector<>();
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder s=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select Title,ReleaseYear,Genre,Score from movies order by Score desc";
            pst = cn.prepareStatement(sb2);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Title"));list.add(rs.getString("ReleaseYear"));
                list.add(rs.getString("Genre"));list.add(rs.getString("Score"));
                res.add(list);
                list=new Vector<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return res;
    }


    /**
     * 组合搜索
     */
    public Vector<Vector<String>> zuhe(String sss){
        Vector<Vector<String>> res=new Vector<>();
        Vector<String> list=new Vector<>();
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder s=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2=sss;
            pst = cn.prepareStatement(sb2);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Title"));list.add(rs.getString("ReleaseYear"));
                list.add(rs.getString("Genre"));list.add(rs.getString("Score"));
                res.add(list);
                list=new Vector<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return res;
    }


    /**
     * 得到热门电影的排行表
     */
    public Vector<Vector<String>> gethot(){
        Vector<Vector<String>> res=new Vector<>();
        Vector<String> list=new Vector<>();
        Connection cn;
        PreparedStatement pst = null;
        ResultSet rs;
        StringBuilder s=new StringBuilder();
        String sb2= null;
        try {
            cn = JDBC.getCn();
            sb2="select Title,Genre from show02 limit 5";
            pst = cn.prepareStatement(sb2);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Title"));
                list.add(rs.getString("Genre"));
                res.add(list);
                list=new Vector<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeCn();
        }
        return res;
    }
}
