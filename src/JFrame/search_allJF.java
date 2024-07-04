package JFrame;

import DAO.moviesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class search_allJF extends JFrame {
    moviesDAO movies_dao=new moviesDAO();
    JPanel center_jp=null;
    JButton Title_jl=null;
    JLabel ReleaseYear_jl=null;
    JLabel Duration_jl=null;
    JLabel Genre_jl=null;
    JLabel movieLanguage_jl=null;
    JLabel Country_jl=null;
    JLabel Synopsis_jl=null;
    JLabel score_jl=null;
    JLabel actor_jl=null;
    JLabel director_jl=null;
    JTextField actor_jt=null;
    JTextField director_jt=null;
    JTextField Title_jt=null;
    JTextField ReleaseYear_jt=null;
    JTextField Duration_jt=null;
    JTextField Genre_jt=null;
    JTextField movieLanguage_jt=null;
    JTextField country_jt=null;
    JTextArea Synopsis_jta=null;
    JTextField score_jt=null;
    JScrollPane jsp=null;
    public search_allJF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("电影详情");
        this.setBounds(200,50,700,600);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void initPanel(){
        center_jp=new JPanel(new GridLayout(10,2));
        Title_jl=new JButton("电影名（点击查询）");
        Title_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        actor_jl=new JLabel("演员");
        actor_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        director_jl=new JLabel("导演");
        director_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        actor_jt=new JTextField(15);
        director_jt=new JTextField(15);
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
        center_jp.add(Title_jl);center_jp.add(Title_jt);
        center_jp.add(actor_jl);center_jp.add(actor_jt);
        center_jp.add(director_jl);center_jp.add(director_jt);
        center_jp.add(ReleaseYear_jl);center_jp.add(ReleaseYear_jt);
        center_jp.add(Duration_jl);center_jp.add(Duration_jt);
        center_jp.add(Genre_jl);center_jp.add(Genre_jt);
        center_jp.add(movieLanguage_jl);center_jp.add(movieLanguage_jt);
        center_jp.add(Country_jl);center_jp.add(country_jt);
        center_jp.add(Synopsis_jl);center_jp.add(jsp);
        center_jp.add(score_jl);center_jp.add(score_jt);
        this.add(center_jp,BorderLayout.CENTER);
    }
    public void setListener(){
        Title_jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[]s=movies_dao.searchAll(Title_jt.getText());
                ReleaseYear_jt.setText(s[0]);
                Duration_jt.setText(s[1]);
                Genre_jt.setText(s[2]);
                movieLanguage_jt.setText(s[3]);
                country_jt.setText(s[4]);
                Synopsis_jta.setText(s[5]);
                score_jt.setText(s[6]);
                actor_jt.setText(movies_dao.searchAll_actor(Title_jt.getText()));
                director_jt.setText(movies_dao.searchAll_director(Title_jt.getText()));
            }
        });
    }


    public static void main(String[] args) {
        new search_allJF().setVisible(true);
    }
}
