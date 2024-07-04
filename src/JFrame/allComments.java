package JFrame;

import DAO.moviesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class allComments extends JFrame {
    moviesDAO movies_dao=new moviesDAO();
    JPanel center_jp=null;
    JLabel jl=null;
    JButton jb=null;
    JTextField jt=null;
    JTextArea jta=null;
    JScrollPane jsp=null;
    public allComments(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("所有评论");
        this.setBounds(200,50,700,600);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void initPanel(){
        center_jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jb=new JButton("显示");
        jt=new JTextField(30);
        jl=new JLabel("所有评论");
        jta=new JTextArea(45,65);
        jsp=new JScrollPane(jta);
        center_jp.add(jb);
        center_jp.add(jt);
        center_jp.add(jl);
        center_jp.add(jsp);
        this.add(center_jp,BorderLayout.CENTER);
    }
    public void setListener(){
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jta.setText(movies_dao.getComments(jt.getText()));
            }
        });
    }
    public static void main(String[] args) {
        new allComments().setVisible(true);
    }
}