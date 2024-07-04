package JFrame;

import DAO.moviesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class managerAction01JF extends JFrame {
    moviesDAO movie_dao=new moviesDAO();
    JPanel center_jp=null;
    JLabel jl=null;
    JLabel jl1=new JLabel("需删除言论的用户ID");
    JLabel jl2=new JLabel("需删除评论的电影ID");
    JLabel jl3=new JLabel("需要删除的评论内容");
    JTextField jt=null;
    JTextField jt1=null;
    JTextField jt3=null;
    JTextArea jt2=null;
    JScrollPane jsp=null;
    JButton jb=null;
    public managerAction01JF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("删除用户不当言论");
        this.setBounds(300,200,580,700);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void initPanel(){
        center_jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jb=new JButton("删除");
        jl=new JLabel("所有评论");
        jt=new JTextField(30);
        jt1=new JTextField(30);
        jt3=new JTextField(40);
        jt2=new JTextArea(30,50);
        jt2.setText(movie_dao.managerAllComments());
        jt.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        jt1.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        jt3.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        jsp=new JScrollPane(jt2);
        center_jp.add(jl);
        center_jp.add(jsp);
        center_jp.add(jl1);
        center_jp.add(jt);
        center_jp.add(jl2);
        center_jp.add(jt1);
        center_jp.add(jl3);
        center_jp.add(jt3);
        center_jp.add(jb);
        this.add(center_jp,BorderLayout.CENTER);
    }
    public void setListener(){
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movie_dao.deleteComment(jt.getText(),jt1.getText(),jt3.getText());
                JOptionPane.showMessageDialog(managerAction01JF.this,"操作成功！","提示",
                        JOptionPane.WARNING_MESSAGE);
                jt.setText(null);
                jt1.setText(null);
                jt3.setText(null);
                jt2.setText(movie_dao.managerAllComments());
            }
        });
    }

    public static void main(String[] args) {
        new managerAction01JF().setVisible(true);
    }
}
