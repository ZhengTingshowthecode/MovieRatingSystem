package JFrame;

import DAO.moviesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class actorInfoJF extends JFrame {
    moviesDAO movies_dao=new moviesDAO();
    JPanel north_jp=null;
    JPanel south_jp=null;
    JTextField jt=null;
    JButton jb=null;
    JLabel jl1=null;
    JLabel jl2=null;
    JLabel jl3=null;
    JTextField jt1=null;
    JTextField jt2=null;
    JTextField jt3=null;
    JScrollPane jsp=null;
    JTextArea jta=null;
    public actorInfoJF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("演员信息");
        this.setBounds(100,100,600,600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void initPanel() {
        north_jp = new JPanel(new GridLayout(4,2));
        jt=new JTextField(30);
        jb=new JButton("搜索");
        jl1=new JLabel("性别");
        jl2=new JLabel("生日");
        jl3=new JLabel("国籍");
        jt1=new JTextField(10);
        jt2=new JTextField(10);
        jt3=new JTextField(10);
        north_jp.add(jt);north_jp.add(jb);
        north_jp.add(jl1);north_jp.add(jt1);
        north_jp.add(jl2);north_jp.add(jt2);
        north_jp.add(jl3);north_jp.add(jt3);
        this.add(north_jp, BorderLayout.NORTH);
        south_jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jta=new JTextArea(22,42);
        jta.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        jsp=new JScrollPane(jta);
        south_jp.add(jsp);
        this.add(south_jp,BorderLayout.SOUTH);
    }
    public void setListener(){
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[]s=movies_dao.getActors(jt.getText());
                jt1.setText(s[0]);
                jt2.setText(s[1]);
                jt3.setText(s[2]);
                jta.setText(movies_dao.movie_actor(jt.getText()));
            }
        });
    }

    public static void main(String[] args) {
        new actorInfoJF().setVisible(true);
    }
}
