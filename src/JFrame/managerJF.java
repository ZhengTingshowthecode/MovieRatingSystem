package JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class managerJF extends JFrame {
    JPanel jp=new JPanel();
    JButton jb1=new JButton("删除用户不当言论");
    JButton jb2=new JButton("添加/删除电影");
    JButton jb3=new JButton("修改电影信息");
    public managerJF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("管理员界面");
        this.setBounds(500,200,300,250);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void initPanel(){
        jp.setLayout(new GridLayout(3,1));
        jb1.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        jb2.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        jb3.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        this.add(jp);
    }
    public void setListener(){
        jb1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new managerAction01JF().setVisible(true);
            }
        });
        jb2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new managerAction02JF().setVisible(true);
            }
        });
        jb3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new managerAction03JF().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new managerJF().setVisible(true);
    }
}
