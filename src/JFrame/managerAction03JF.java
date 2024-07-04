package JFrame;

import DAO.moviesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class managerAction03JF extends JFrame {
    moviesDAO movies_dao=new moviesDAO();
    JPanel center_jp=null;
    JButton Title_jl=null;
    JLabel id_jl=null;
    JTextField id_jt=null;
    JLabel ReleaseYear_jl=null;
    JLabel Duration_jl=null;
    JLabel Genre_jl=null;
    JLabel movieLanguage_jl=null;
    JLabel Country_jl=null;
    JLabel Synopsis_jl=null;
    JLabel score_jl=null;
    JLabel actor_jl=null;
    JLabel actor_jl1=null;
    JLabel actor_jl2=null;
    JLabel director_jl=null;
    JLabel director_jl1=null;
    JLabel director_jl2=null;
    JTextField actor_jt=null;
    JTextField actor_jt1=null;
    JTextField actor_jt2=null;
    JTextField director_jt=null;
    JTextField director_jt1=null;
    JTextField director_jt2=null;
    JTextField Title_jt=null;
    JTextField ReleaseYear_jt=null;
    JTextField Duration_jt=null;
    JTextField Genre_jt=null;
    JTextField movieLanguage_jt=null;
    JTextField country_jt=null;
    JTextArea Synopsis_jta=null;
    JTextField score_jt=null;
    JScrollPane jsp=null;
    JButton jb1=new JButton("修改");
    JButton jb4=new JButton("修改");
    JButton jb5=new JButton("修改");
    JButton jb6=new JButton("修改");
    JButton jb7=new JButton("修改");
    JButton jb8=new JButton("修改");
    JButton jb9=new JButton("修改");
    JLabel null_jl=new JLabel();
    JLabel null_jl02=new JLabel();
    JLabel null_jl03=new JLabel();
    JLabel null_jl04=new JLabel();
    JLabel null_jl05=new JLabel();
    JLabel null_jl06=new JLabel();
    JLabel null_jl07=new JLabel();
    JLabel null_jl08=new JLabel();
    public managerAction03JF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("电影详情");
        this.setBounds(200,50,700,780);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void initPanel(){
        center_jp=new JPanel(new GridLayout(15,3));
        Title_jl=new JButton("电影名（点击查询）");
        Title_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        id_jl=new JLabel("电影id");
        id_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        actor_jl=new JLabel("演员1");
        actor_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        actor_jl1=new JLabel("演员2");
        actor_jl1.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        actor_jl2=new JLabel("演员3");
        actor_jl2.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        director_jl=new JLabel("导演1");
        director_jl.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        director_jl1=new JLabel("导演2");
        director_jl1.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        director_jl2=new JLabel("导演3");
        director_jl2.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        actor_jt=new JTextField(15);
        director_jt=new JTextField(15);
        actor_jt1=new JTextField(15);
        director_jt1=new JTextField(15);
        actor_jt2=new JTextField(15);
        director_jt2=new JTextField(15);
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
        id_jt=new JTextField(15);
        ReleaseYear_jt=new JTextField(15);
        Duration_jt=new JTextField(15);
        Genre_jt=new JTextField(15);
        movieLanguage_jt=new JTextField(15);
        country_jt=new JTextField(15);
        Synopsis_jta=new JTextArea(45,65);
        score_jt=new JTextField(15);
        jsp=new JScrollPane(Synopsis_jta);
        center_jp.add(jb1);
        center_jp.add(Title_jl);center_jp.add(Title_jt);
        center_jp.add(null_jl);
        center_jp.add(id_jl);center_jp.add(id_jt);
        center_jp.add(null_jl03);
        center_jp.add(actor_jl);center_jp.add(actor_jt);
        center_jp.add(null_jl04);
        center_jp.add(actor_jl1);center_jp.add(actor_jt1);
        center_jp.add(null_jl05);
        center_jp.add(actor_jl2);center_jp.add(actor_jt2);
        center_jp.add(null_jl06);
        center_jp.add(director_jl);center_jp.add(director_jt);
        center_jp.add(null_jl07);
        center_jp.add(director_jl1);center_jp.add(director_jt1);
        center_jp.add(null_jl08);
        center_jp.add(director_jl2);center_jp.add(director_jt2);
        center_jp.add(jb4);
        center_jp.add(ReleaseYear_jl);center_jp.add(ReleaseYear_jt);
        center_jp.add(jb5);
        center_jp.add(Duration_jl);center_jp.add(Duration_jt);
        center_jp.add(jb6);
        center_jp.add(Genre_jl);center_jp.add(Genre_jt);
        center_jp.add(jb7);
        center_jp.add(movieLanguage_jl);center_jp.add(movieLanguage_jt);
        center_jp.add(jb8);
        center_jp.add(Country_jl);center_jp.add(country_jt);
        center_jp.add(jb9);
        center_jp.add(Synopsis_jl);center_jp.add(jsp);
        center_jp.add(null_jl02);
        center_jp.add(score_jl);center_jp.add(score_jt);
        this.add(center_jp,BorderLayout.CENTER);
    }
    public void setListener(){
        Title_jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[]s=movies_dao.managerSearchAll(Title_jt.getText());
                ReleaseYear_jt.setText(s[0]);
                Duration_jt.setText(s[1]);
                Genre_jt.setText(s[2]);
                movieLanguage_jt.setText(s[3]);
                country_jt.setText(s[4]);
                Synopsis_jta.setText(s[5]);
                score_jt.setText(s[6]);
                id_jt.setText(s[7]);
                List<String> ss=movies_dao.managerSearchAll_actor(Title_jt.getText());
                List<String> sss=movies_dao.managerSearchAll_director(Title_jt.getText());
                actor_jt.setText(ss.get(0));
                director_jt.setText(sss.get(0));
                if(ss.size()==2){
                    actor_jt1.setText(ss.get(1));
                }
                if(ss.size()==3){
                    actor_jt1.setText(ss.get(1));
                    actor_jt2.setText(ss.get(2));
                }
                if(sss.size()==2){
                    director_jt1.setText(sss.get(1));
                }
                if(sss.size()==3){
                    director_jt1.setText(sss.get(1));
                    director_jt2.setText(sss.get(2));
                }
            }
        });
        jb1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movies_dao.managerUpdateTitle(id_jt.getText(),Title_jt.getText());
            }
        });
        jb4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movies_dao.managerUpdateReleaseYear(id_jt.getText(),ReleaseYear_jt.getText());
            }
        });
        jb5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movies_dao.managerUpdateDuration(id_jt.getText(),Integer.parseInt(Duration_jt.getText()));
            }
        });
        jb6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movies_dao.managerUpdateGenre(id_jt.getText(),Genre_jt.getText());
            }
        });
        jb7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movies_dao.managerUpdateLanguage(id_jt.getText(),movieLanguage_jt.getText());
            }
        });
        jb8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movies_dao.managerUpdateCountry(id_jt.getText(),country_jt.getText());
            }
        });
        jb9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movies_dao.managerUpdateSynopsis(id_jt.getText(),Synopsis_jta.getText());
            }
        });
    }

    public static void main(String[] args) {
        new managerAction03JF().setVisible(true);
    }
}
