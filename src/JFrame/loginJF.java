package JFrame;

import DAO.managerDAO;
import DAO.userDAO;
import ENTITY.manager;
import ENTITY.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loginJF extends JFrame {
    userDAO user_dao=new userDAO();
    managerDAO manager_dao=new managerDAO();
    JPanel north_panel=null;
    JPanel center_panel=null;
    JLabel user_label=null;
    JTextField jt=null;
    JLabel pwd_label=null;
    JTextField pwd_jt=null;
    //JPasswordField pwd_jt=null;
    JPanel south_panel=null;
    JButton login_jb=null;
    JButton cancel_jb=null;
    JButton register_jb=null;
    JButton manager_jb=null;
    public loginJF(){
        initFrame();
        initPanel();
        setListener();
    }
    public void initFrame(){
        this.setTitle("电影评分系统");
        this.setLocationRelativeTo(null);
        this.setSize(400,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void initPanel(){
        north_panel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon img=new ImageIcon("D:\\二\\movieSystem\\src\\IMAGE\\logo.jpg");
        JLabel img_jl=new JLabel(img);
        north_panel.add(img_jl);
        this.add(north_panel,BorderLayout.NORTH);
        center_panel=new JPanel(new GridLayout(2,2));
        user_label=new JLabel("账号:",JLabel.CENTER);
        jt=new JTextField();
        pwd_label=new JLabel("密码:",JLabel.CENTER);
        pwd_jt=new JPasswordField();
        center_panel.add(user_label);
        center_panel.add(jt);
        center_panel.add(pwd_label);
        center_panel.add(pwd_jt);
        this.add(center_panel,BorderLayout.CENTER);
        south_panel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        login_jb=new JButton("登录");
        cancel_jb=new JButton("取消");
        register_jb=new JButton("注册");
        manager_jb=new JButton("我是管理员");
        south_panel.add(login_jb);
        south_panel.add(cancel_jb);
        south_panel.add(register_jb);
        south_panel.add(manager_jb);
        this.add(south_panel,BorderLayout.SOUTH);
    }
    public void setListener(){
        login_jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username= jt.getText();
                String pwd= pwd_jt.getText();//Arrays.toString(pwd_jt.getPassword());
                if(username!=null&&!username.equals("")&&pwd!=null&&!pwd.equals("")){
                    user user=user_dao.checkLogin(username,pwd);
                    if (user==null){
                        JOptionPane.showMessageDialog(loginJF.this,"用户名或密码错误！","提示",
                                JOptionPane.WARNING_MESSAGE);
                    }else{
                        loginJF.this.dispose();
                        new mainJF().setVisible(true);
                    }
                }else{
                    JOptionPane.showMessageDialog(loginJF.this,"不能为空！","提示",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        cancel_jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginJF.this.dispose();
            }
        });
        register_jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new registerJF().setVisible(true);
            }
        });
        manager_jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username= jt.getText();
                String pwd= pwd_jt.getText();//Arrays.toString(pwd_jt.getPassword());
                if(username!=null&&!username.equals("")&&pwd!=null&&!pwd.equals("")){
                    manager manager=manager_dao.checkManagerLogin(username,pwd);
                    if (manager==null){
                        JOptionPane.showMessageDialog(loginJF.this,"用户名或密码错误！","提示",
                                JOptionPane.WARNING_MESSAGE);
                    }else{
                        loginJF.this.dispose();
                        new managerJF().setVisible(true);
                    }
                }else{
                    JOptionPane.showMessageDialog(loginJF.this,"不能为空！","提示",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    public static void main(String[] args) {
        new loginJF().setVisible(true);
    }
}