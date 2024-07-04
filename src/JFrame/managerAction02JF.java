package JFrame;

import DAO.moviesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class managerAction02JF extends JFrame {
    moviesDAO movies_dao=new moviesDAO();
    JPanel center_jp=null;
    JLabel movieID=null;
    JLabel Title_jl=null;
    JLabel ReleaseYear_jl=null;
    JLabel Duration_jl=null;
    JLabel Genre_jl=null;
    JLabel movieLanguage_jl=null;
    JLabel Country_jl=null;
    JLabel Synopsis_jl=null;
    JLabel score_jl=null;
    JTextField ID_jt=null;
    JTextField Title_jt=null;
    JTextField ReleaseYear_jt=null;
    JTextField Duration_jt=null;
    JTextField Genre_jt=null;
    JTextField movieLanguage_jt=null;
    JTextField country_jt=null;
    JTextArea Synopsis_jta=null;
    JTextField score_jt=null;
    JScrollPane jsp=null;
    JButton jb1=new JButton("确定添加");
    JButton jb2=new JButton("确定删除(只需填写电影ID即可)");
    JButton actAdd_jb=new JButton("添加演员");
    JButton dirAdd_jb=new JButton("添加导演");
    public managerAction02JF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("电影详情");
        this.setBounds(200,50,700,660);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void initPanel(){
        center_jp=new JPanel(new GridLayout(11,2));
        movieID=new JLabel("电影ID");
        movieID.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        ID_jt=new JTextField(15);
        Title_jl=new JLabel("电影名");
        Title_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        ReleaseYear_jl=new JLabel("发行时间");
        ReleaseYear_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        Duration_jl=new JLabel("时长");
        Duration_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        Genre_jl=new JLabel("类型");
        Genre_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        movieLanguage_jl=new JLabel("语言");
        movieLanguage_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        Country_jl=new JLabel("国家");
        Country_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        Synopsis_jl=new JLabel("简介");
        Synopsis_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        score_jl=new JLabel("评分");
        score_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        Title_jt=new JTextField(15);
        ReleaseYear_jt=new JTextField(15);
        Duration_jt=new JTextField(15);
        Genre_jt=new JTextField(15);
        movieLanguage_jt=new JTextField(15);
        country_jt=new JTextField(15);
        Synopsis_jta=new JTextArea(45,65);
        score_jt=new JTextField(15);
        jsp=new JScrollPane(Synopsis_jta);
        center_jp.add(movieID);center_jp.add(ID_jt);
        center_jp.add(Title_jl);center_jp.add(Title_jt);
        center_jp.add(actAdd_jb);center_jp.add(dirAdd_jb);
        center_jp.add(ReleaseYear_jl);center_jp.add(ReleaseYear_jt);
        center_jp.add(Duration_jl);center_jp.add(Duration_jt);
        center_jp.add(Genre_jl);center_jp.add(Genre_jt);
        center_jp.add(movieLanguage_jl);center_jp.add(movieLanguage_jt);
        center_jp.add(Country_jl);center_jp.add(country_jt);
        center_jp.add(Synopsis_jl);center_jp.add(jsp);
        center_jp.add(score_jl);center_jp.add(score_jt);
        center_jp.add(jb1);center_jp.add(jb2);
        this.add(center_jp,BorderLayout.CENTER);
    }
    public void setListener(){
        jb1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movies_dao.mamagerAddMovie(ID_jt.getText(),Title_jt.getText(),ReleaseYear_jt.getText(),
                        Duration_jt.getText(),Genre_jt.getText(),movieLanguage_jt.getText(),
                        country_jt.getText(),Synopsis_jta.getText(),score_jt.getText());
                JOptionPane.showMessageDialog(managerAction02JF.this,
                        "添加成功！","提示",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        jb2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movies_dao.managerDeleteMovie(ID_jt.getText());
                JOptionPane.showMessageDialog(managerAction02JF.this,
                        "删除成功！","提示",
                        JOptionPane.WARNING_MESSAGE);
                ID_jt.setText(null);
            }
        });
        actAdd_jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new managerAction02_actAddJF().setVisible(true);
            }
        });
        dirAdd_jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new managerAction02_dirAddJF().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new managerAction02JF().setVisible(true);
    }
}
