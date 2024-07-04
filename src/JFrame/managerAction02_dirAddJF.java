package JFrame;

import DAO.moviesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class managerAction02_dirAddJF extends JFrame {
    moviesDAO movie_dao=new moviesDAO();
    JPanel center_jp=null;
    JLabel id_jl=null;
    JTextField id_jt=null;
    JLabel null_jl01=new JLabel();
    JLabel null_jl02=new JLabel();
    JLabel null_jl03=new JLabel();
    JLabel null_jl04=new JLabel();
    JLabel null_jl05=new JLabel();
    JLabel null_jl06=new JLabel();
    JLabel null_jl07=new JLabel();
    JLabel null_jl08=new JLabel();
    JLabel jl=null;
    JLabel jl1=null;
    JLabel jl2=null;
    JLabel jl3=null;
    JLabel jl4=null;
    JLabel jl5=null;
    JTextField jt=null;
    JTextField jt1=null;
    JTextField jt2=null;
    JTextField jt3=null;
    JTextField jt4=null;
    JTextField jt5=null;
    JButton jb=null;
    JLabel gender_jl1=new JLabel("性别");
    JLabel gender_jl2=new JLabel("性别");
    JLabel gender_jl3=new JLabel("性别");
    JLabel birth_jl1=new JLabel("生日");
    JLabel birth_jl2=new JLabel("生日");
    JLabel birth_jl3=new JLabel("生日");
    JLabel na_jl1=new JLabel("国籍");
    JLabel na_jl2=new JLabel("国籍");
    JLabel na_jl3=new JLabel("国籍");
    JTextField gender_jt1=new JTextField(30);
    JTextField gender_jt2=new JTextField(30);
    JTextField gender_jt3=new JTextField(30);
    JTextField birth_jt1=new JTextField(30);
    JTextField birth_jt2=new JTextField(30);
    JTextField birth_jt3=new JTextField(30);
    JTextField na_jt1=new JTextField(30);
    JTextField na_jt2=new JTextField(30);
    JTextField na_jt3=new JTextField(30);
    public managerAction02_dirAddJF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("请添加导演");
        this.setBounds(300,200,780,550);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void initPanel(){
        center_jp=new JPanel(new GridLayout(11,4));
        jb=new JButton("确定");
        id_jl=new JLabel("电影id");
        id_jt=new JTextField(30);
        jl=new JLabel("导演1 id");
        jl1=new JLabel("导演2 id");
        jl2=new JLabel("导演3 id");
        jl3=new JLabel("导演1 名字");
        jl4=new JLabel("导演2 名字");
        jl5=new JLabel("导演3 名字");
        jt=new JTextField(30);
        jt1=new JTextField(30);
        jt2=new JTextField(30);
        jt3=new JTextField(30);
        jt4=new JTextField(30);
        jt5=new JTextField(30);
        id_jt.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        jt.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        jt1.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        jt2.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        jt3.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        jt4.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));
        jt5.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,18));

        center_jp.add(id_jl);center_jp.add(id_jt);
        center_jp.add(null_jl01);center_jp.add(null_jl02);

        center_jp.add(jl);center_jp.add(jt);
        center_jp.add(jl3);center_jp.add(jt3);
        center_jp.add(gender_jl1);center_jp.add(gender_jt1);
        center_jp.add(birth_jl1);center_jp.add(birth_jt1);
        center_jp.add(na_jl1);center_jp.add(na_jt1);
        center_jp.add(null_jl03);center_jp.add(null_jl04);

        center_jp.add(jl1);center_jp.add(jt1);
        center_jp.add(jl4);center_jp.add(jt4);
        center_jp.add(gender_jl2);center_jp.add(gender_jt2);
        center_jp.add(birth_jl2);center_jp.add(birth_jt2);
        center_jp.add(na_jl2);center_jp.add(na_jt2);
        center_jp.add(null_jl05);center_jp.add(null_jl06);

        center_jp.add(jl2);center_jp.add(jt2);
        center_jp.add(jl5);center_jp.add(jt5);
        center_jp.add(gender_jl3);center_jp.add(gender_jt3);
        center_jp.add(birth_jl3);center_jp.add(birth_jt3);
        center_jp.add(na_jl3);center_jp.add(na_jt3);
        center_jp.add(null_jl07);center_jp.add(null_jl08);

        center_jp.add(jb);
        this.add(center_jp,BorderLayout.CENTER);
    }
    public void setListener(){
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String id=id_jt.getText();
                String[]s;
                String[][]ss;
                if((jt.getText()!=null || !jt.getText().equals("") || !jt.getText().equals(" "))&&
                        jt1.getText()==null || jt1.getText().equals("") || jt1.getText().equals(" ")){
                    s=new String[1];
                    s[0]=jt.getText();

                    ss=new String[1][5];
                    ss[0][0]=jt.getText();
                    ss[0][1]=jt3.getText();
                    ss[0][2]=gender_jt1.getText();
                    ss[0][3]=birth_jt1.getText();
                    ss[0][4]=na_jt1.getText();
                }else if((jt1.getText()!=null || !jt1.getText().equals("") || !jt1.getText().equals(" "))&&
                        jt2.getText()==null || jt2.getText().equals("") || jt2.getText().equals(" ")){
                    s=new String[2];
                    s[0]=jt.getText();
                    s[1]=jt1.getText();

                    ss=new String[2][5];
                    ss[0][0]=jt.getText();
                    ss[0][1]=jt3.getText();
                    ss[0][2]=gender_jt1.getText();
                    ss[0][3]=birth_jt1.getText();
                    ss[0][4]=na_jt1.getText();

                    ss[1][0]=jt1.getText();
                    ss[1][1]=jt4.getText();
                    ss[1][2]=gender_jt2.getText();
                    ss[1][3]=birth_jt2.getText();
                    ss[1][4]=na_jt2.getText();
                }else{
                    s=new String[3];
                    s[0]=jt.getText();
                    s[1]=jt1.getText();
                    s[2]=jt2.getText();

                    ss=new String[3][5];
                    ss[0][0]=jt.getText();
                    ss[0][1]=jt3.getText();
                    ss[0][2]=gender_jt1.getText();
                    ss[0][3]=birth_jt1.getText();
                    ss[0][4]=na_jt1.getText();

                    ss[1][0]=jt1.getText();
                    ss[1][1]=jt4.getText();
                    ss[1][2]=gender_jt2.getText();
                    ss[1][3]=birth_jt2.getText();
                    ss[1][4]=na_jt2.getText();

                    ss[2][0]=jt2.getText();
                    ss[2][1]=jt5.getText();
                    ss[2][2]=gender_jt3.getText();
                    ss[2][3]=birth_jt3.getText();
                    ss[2][4]=na_jt3.getText();
                }
                movie_dao.Dir_Info(ss);
                movie_dao.managerAddDir(id,s);
                JOptionPane.showMessageDialog(managerAction02_dirAddJF.this,
                        "添加成功！","提示",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        new managerAction02_dirAddJF().setVisible(true);
    }
}
