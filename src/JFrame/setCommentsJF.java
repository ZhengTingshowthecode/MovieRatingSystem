package JFrame;

import DAO.moviesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class setCommentsJF extends JFrame {
    moviesDAO movie_dao=new moviesDAO();
    JPanel center_jp=null;
    JLabel jl=null;
    JLabel jl1=null;
    JLabel jl2=null;
    JTextField jt=null;
    JTextField jt1=null;
    JTextArea jt2=null;
    JButton jb=null;
    JButton jb2=null;
    public setCommentsJF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("请评论");
        this.setBounds(300,200,380,460);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void initPanel(){
        center_jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jb=new JButton("提交评论");
        jb2=new JButton("重置文本");
        jl=new JLabel("您的ID");
        jl1=new JLabel("要评论的电影");
        jl2=new JLabel("评论内容");
        jt=new JTextField(30);
        jt1=new JTextField(30);
        jt2=new JTextArea(10,30);
        jt.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        jt1.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        jt2.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        center_jp.add(jl);
        center_jp.add(jt);
        center_jp.add(jl1);
        center_jp.add(jt1);
        center_jp.add(jl2);
        center_jp.add(jt2);
        center_jp.add(jb);
        center_jp.add(jb2);
        this.add(center_jp,BorderLayout.CENTER);
    }
    public void setListener(){
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movie_dao.setComment(jt.getText(),jt1.getText(),jt2.getText());
                JOptionPane.showMessageDialog(setCommentsJF.this,"已评论！","提示",
                        JOptionPane.WARNING_MESSAGE);
                jt.setText(null);
                jt1.setText(null);
                jt2.setText(null);
            }
        });
        jb2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jt2.setText(null);
            }
        });
    }
    public static void main(String[] args) {
        new setCommentsJF().setVisible(true);
    }
}
