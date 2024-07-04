package JFrame;

import DAO.moviesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class userDeleteCommentJF extends JFrame {
    moviesDAO movie_dao=new moviesDAO();
    JPanel center_jp=null;
    JPanel south_jp=null;
    JLabel jl=null;
    JLabel jl1=null;
    JTextField jt=null;
    JTextField jt1=null;
    JTextField jt2=null;
    JButton jb=null;
    JButton jb2=null;
    JTable table=null;
    public userDeleteCommentJF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("删除评论");
        this.setBounds(300,200,800,500);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void initPanel(){
        center_jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jb=new JButton("删除");
        jb2=new JButton("查询我的评论");
        jl=new JLabel("您的ID");
        jl1=new JLabel("要删除的电影");
        JLabel jl2=new JLabel("要删除的评论");
        jt=new JTextField(10);
        jt1=new JTextField(10);
        jt2=new JTextField(10);
        jt.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        jt1.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        jt2.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        center_jp.add(jl);
        center_jp.add(jt);
        center_jp.add(jb2);
        center_jp.add(jl1);
        center_jp.add(jt1);
        center_jp.add(jl2);
        center_jp.add(jt2);
        center_jp.add(jb);
        this.add(center_jp,BorderLayout.CENTER);
        south_jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        Vector<String> columnNames = new Vector<>();
        columnNames.add("影名");
        columnNames.add("评论");
        columnNames.add("评论时间");
        Vector<Vector<String>> data = new Vector<>();
        table=new JTable(data,columnNames);
        JScrollPane jsp=new JScrollPane(table);
        south_jp.add(jsp);
        this.add(south_jp,BorderLayout.SOUTH);
    }
    public void setListener(){
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movie_dao.userDeleteComment(jt.getText(),jt1.getText(),jt2.getText());
                Vector<Vector<String>> list= movie_dao.userDeleteComment01(jt.getText());
                table.setModel(new javax.swing.table.DefaultTableModel(list,
                        new Vector<>(java.util.Arrays.asList("影名", "评论","评论时间"))));
            }
        });
        jb2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Vector<Vector<String>> list= movie_dao.userDeleteComment01(jt.getText());
                table.setModel(new javax.swing.table.DefaultTableModel(list,
                        new Vector<>(java.util.Arrays.asList("影名", "评论","评论时间"))));
            }
        });
    }
    public static void main(String[] args) {
        new userDeleteCommentJF().setVisible(true);
    }
}
