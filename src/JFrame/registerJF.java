package JFrame;

import DAO.userDAO;
import ENTITY.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class registerJF extends JFrame {
    userDAO user_dao=new userDAO();
    JPanel center_jp=null;
    JLabel userID_jl=null;
    JLabel Nickname_jl=null;
    JLabel PWD_jl=null;
    JLabel Phone_jl=null;
    JTextField userID_jt=null;
    JTextField Nickname_jt=null;
    JTextField PWD_jt=null;
    JTextField Phone_jt=null;
    JPanel south_jp=null;
    JButton submit_jb=null;
    JButton cancel_jb=null;
    public registerJF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("请注册");
        this.setBounds(100,100,380,300);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void initPanel(){
        center_jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        userID_jl=new JLabel("8位数的ID");
        Nickname_jl=new JLabel("昵称");
        PWD_jl=new JLabel("登录密码");
        Phone_jl=new JLabel("正确的手机号");
        userID_jt=new JTextField(30);
        Nickname_jt=new JTextField(30);
        PWD_jt=new JTextField(30);
        Phone_jt=new JTextField(30);
        userID_jt.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        Nickname_jt.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        PWD_jt.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        Phone_jt.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        center_jp.add(userID_jl);
        center_jp.add(userID_jt);
        center_jp.add(Nickname_jl);
        center_jp.add(Nickname_jt);
        center_jp.add(PWD_jl);
        center_jp.add(PWD_jt);
        center_jp.add(Phone_jl);
        center_jp.add(Phone_jt);
        this.add(center_jp,BorderLayout.CENTER);
        south_jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        submit_jb=new JButton("提交注册信息");
        cancel_jb=new JButton("重置");
        south_jp.add(submit_jb);
        south_jp.add(cancel_jb);
        this.add(south_jp,BorderLayout.SOUTH);
    }
    public void setListener(){
        submit_jb.addMouseListener(new MouseAdapter() {
            user user=new user();
            @Override
            public void mouseClicked(MouseEvent e) {
                String username= userID_jt.getText();
                String nickname= Nickname_jt.getText();
                String pwd= PWD_jt.getText();//Arrays.toString(pwd_jt.getPassword());
                String phone= Phone_jt.getText();
                if(username!=null&&!username.equals("")&&pwd!=null&&!pwd.equals("")){
                    user=user_dao.checkLogin(username,pwd);
                    if (user!=null){
                        JOptionPane.showMessageDialog(registerJF.this,"用户已存在！","提示",
                                JOptionPane.WARNING_MESSAGE);
                    }else{
                        user_dao.register(username,nickname,pwd,phone);
                        JOptionPane.showMessageDialog(registerJF.this,"注册成功！","提示",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(registerJF.this,"不能为空！","提示",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        cancel_jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userID_jt.setText(null);
                Nickname_jt.setText(null);
                PWD_jt.setText(null);
                Phone_jt.setText(null);
            }
        });
    }
    public static void main(String[] args) {
        new registerJF().setVisible(true);
    }
}
