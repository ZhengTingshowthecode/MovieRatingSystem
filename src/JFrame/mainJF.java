package JFrame;

import DAO.moviesDAO;
import UTIL.pageModel;
import UTIL.tableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainJF extends JFrame {
    moviesDAO movie_dao=new moviesDAO();
    JPanel north_jp=null;
    JButton zuhechaxun=null;
    JButton search_all_jb=null;
    JButton order_jb=null;
    JButton search_jb=null;
    JButton search_hot=null;
    JComboBox<String> comboBox=null;
    JComboBox<String> comboBox02=null;
    JComboBox<String> comboBox03=null;
    JButton jb=new JButton("选定");
    JButton jb02=new JButton("选定");
    JButton jb03=null;
    JPanel south_jp=null;
    JButton first_jb=null;
    JButton last_jb=null;
    JButton next_jb=null;
    JButton pre_jb=null;
    JScrollPane jsp=null;
    JLabel showPage=null;
    pageModel pageModel=new pageModel(5);
    //这样写换页的时候就可以不用整个的换table，换table model就行
    JTable table=new JTable(new tableModel(pageModel));
    public mainJF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("电影信息");
        this.setBounds(100,100,1200,660);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void initPanel(){
        north_jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        zuhechaxun=new JButton("组合查询");
        order_jb=new JButton("排行榜");
        search_all_jb=new JButton("某电影的详细信息");
        String[]items02={"演员相关","导演相关"};
        comboBox02=new JComboBox<>(items02);
        comboBox02.setBackground(Color.CYAN);
        jb02.setBackground(Color.CYAN);
        String[]items03={"删除我的评论","删除我的评分"};
        comboBox03=new JComboBox<>(items03);
        jb03=new JButton("选定");
        comboBox03.setBackground(Color.orange);
        jb03.setBackground(Color.orange);
        search_hot=new JButton("热门影片");
        search_hot.setBackground(Color.RED);
        String[]items={"查看搜索电影的所有评论","给某电影打分","给某电影评论"};
        comboBox=new JComboBox<>(items);
        comboBox.setBackground(Color.green);
        jb.setBackground(Color.green);
        north_jp.add(search_hot);
        north_jp.add(order_jb);
        north_jp.add(zuhechaxun);
        north_jp.add(search_all_jb);
        north_jp.add(comboBox02);
        north_jp.add(jb02);
        north_jp.add(comboBox);
        north_jp.add(jb);
        north_jp.add(comboBox03);
        north_jp.add(jb03);
        this.add(north_jp,BorderLayout.NORTH);
        table.setFont(new Font(Font.MONOSPACED,Font.PLAIN,15));
        table.setRowHeight(150);
        jsp=new JScrollPane(table);
        this.add(jsp,BorderLayout.CENTER);
        south_jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        first_jb=new JButton("第一页");
        last_jb=new JButton("最后一页");
        next_jb=new JButton("下一页");
        pre_jb=new JButton("上一页");
        showPage=new JLabel("第"+pageModel.getPageNO()+"页/共"+pageModel.getTotalPage()+"页");
        south_jp.add(first_jb);
        south_jp.add(pre_jb);
        south_jp.add(next_jb);
        south_jp.add(last_jb);
        south_jp.add(showPage);
        this.add(south_jp,BorderLayout.SOUTH);
    }
    public void setListener(){
        zuhechaxun.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new zuheSearchJF().setVisible(true);
            }
        });
        search_all_jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new search_allJF().setVisible(true);
            }
        });
        order_jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new topJF().setVisible(true);
            }
        });
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedItem = comboBox.getSelectedItem().toString();
                if(selectedItem.equals("查看搜索电影的所有评论")){
                    new allComments().setVisible(true);
                }else if(selectedItem.equals("给某电影评论")){
                    new setCommentsJF().setVisible(true);
                }else{
                    new setScoreJF().setVisible(true);
                }
            }
        });
        jb02.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedItem = comboBox02.getSelectedItem().toString();
                if(selectedItem.equals("演员相关")){
                    new actorInfoJF().setVisible(true);
                }else if(selectedItem.equals("导演相关")){
                    new directorInfoJF().setVisible(true);
                }
            }
        });
        jb03.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedItem = comboBox03.getSelectedItem().toString();
                if(selectedItem.equals("删除我的评论")){
                    new userDeleteCommentJF().setVisible(true);
                }else if(selectedItem.equals("删除我的评分")){
                    new userDeleteScoreJF().setVisible(true);
                }
            }
        });
        search_hot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getHotMoviesJF getHotMoviesJF=new getHotMoviesJF();
                getHotMoviesJF.setVisible(true);
            }
        });


        first_jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageModel.firstPage();
                table.setModel(new tableModel(pageModel));
                showPage=new JLabel("第"+pageModel.getPageNO()+"页/共"+pageModel.getTotalPage()+"页");
            }
        });
        last_jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageModel.lastPage();
                table.setModel(new tableModel(pageModel));
                showPage=new JLabel("第"+pageModel.getTotalPage()+"页/共"+pageModel.getTotalPage()+"页");
            }
        });
        next_jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageModel.nextPage();
                table.setModel(new tableModel(pageModel));
                showPage=new JLabel("第"+pageModel.getPageNO()+"页/共"+pageModel.getTotalPage()+"页");
            }
        });
        pre_jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pageModel.prevPage();
                table.setModel(new tableModel(pageModel));
                showPage=new JLabel("第"+pageModel.getPageNO()+"页/共"+pageModel.getTotalPage()+"页");
            }
        });
    }
    public static void main(String[] args) {
        new mainJF().setVisible(true);
    }
}