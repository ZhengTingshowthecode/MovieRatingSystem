package JFrame;

import DAO.moviesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class zuheSearchJF extends JFrame {
    moviesDAO movie_dao=new moviesDAO();
    JPanel north_jp=null;
    JPanel south_jp=null;
    JLabel year_jl=null;
    JRadioButton Y2018, Y2019, Y2020, Y2021, Y2022, Y2023,Y2024;
    JLabel genre_jl=null;
    JRadioButton dongzuo,zhanzheng,juqing,xuanyi,fanzui,donghua,lishi,kehuan,zainan,maoxian,
            zhuanji,xiju,jingsong,jiating;
    JLabel score_jl=null;
    JRadioButton S0_2,S3_5,S6_8,S9_10;
    JButton jb=null;
    JTable table=null;
    JScrollPane jsp=null;
    public zuheSearchJF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("组合查询");
        this.setBounds(200,50,880,620);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void initPanel(){
        north_jp=new JPanel(new BorderLayout());
        JPanel jp01=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jb=new JButton("搜索");
        year_jl=new JLabel("年份");
        genre_jl=new JLabel("类型/流派");
        score_jl=new JLabel("评分");
        jp01.add(year_jl);
        ButtonGroup year_group=new ButtonGroup();
        Y2018=new JRadioButton("2018");Y2019=new JRadioButton("2019");
        Y2020=new JRadioButton("2020");Y2021=new JRadioButton("2021");
        Y2022=new JRadioButton("2022");Y2023=new JRadioButton("2023");
        Y2024=new JRadioButton("2024");
        year_group.add(Y2018);year_group.add(Y2019);year_group.add(Y2020);
        year_group.add(Y2021);year_group.add(Y2022);year_group.add(Y2023);
        year_group.add(Y2024);
        jp01.add(Y2018);jp01.add(Y2019);jp01.add(Y2020);jp01.add(Y2021);
        jp01.add(Y2022);jp01.add(Y2023);jp01.add(Y2024);
        north_jp.add(jp01,BorderLayout.NORTH);
        JPanel jp02=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jp02.add(genre_jl);
        ButtonGroup genre_group=new ButtonGroup();
        dongzuo=new JRadioButton("动作");zhanzheng=new JRadioButton("战争");
        juqing=new JRadioButton("剧情");xuanyi=new JRadioButton("悬疑");
        fanzui=new JRadioButton("犯罪");donghua=new JRadioButton("动画");
        lishi=new JRadioButton("历史");kehuan=new JRadioButton("科幻");
        zainan=new JRadioButton("灾难");maoxian=new JRadioButton("冒险");
        zhuanji=new JRadioButton("传记");xiju=new JRadioButton("喜剧");
        jingsong=new JRadioButton("惊悚");jiating=new JRadioButton("家庭");
        genre_group.add(dongzuo);genre_group.add(zhanzheng);genre_group.add(juqing);genre_group.add(xuanyi);
        genre_group.add(fanzui);genre_group.add(donghua);genre_group.add(lishi);genre_group.add(kehuan);
        genre_group.add(zainan);genre_group.add(maoxian);genre_group.add(zhuanji);genre_group.add(xiju);
        genre_group.add(jingsong);genre_group.add(jiating);
        jp02.add(dongzuo);jp02.add(zhanzheng);jp02.add(juqing);jp02.add(xuanyi);
        jp02.add(fanzui);jp02.add(donghua);jp02.add(lishi);jp02.add(kehuan);
        jp02.add(zainan);jp02.add(maoxian);jp02.add(zhuanji);jp02.add(xiju);
        jp02.add(jingsong);jp02.add(jiating);
        north_jp.add(jp02,BorderLayout.CENTER);
        JPanel jp03=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jp03.add(score_jl);
        ButtonGroup score_group=new ButtonGroup();
        S0_2=new JRadioButton("0-2分");S3_5=new JRadioButton("3-5分");
        S6_8=new JRadioButton("6-8分");S9_10=new JRadioButton("9-10分");
        score_group.add(S0_2);score_group.add(S3_5);score_group.add(S6_8);score_group.add(S9_10);
        jp03.add(S0_2);jp03.add(S3_5);jp03.add(S6_8);jp03.add(S9_10);
        north_jp.add(jp03,BorderLayout.SOUTH);
        this.add(north_jp,BorderLayout.NORTH);
        south_jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jb.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        south_jp.add(jb);
        Vector<String> col_list=new Vector<>();
        col_list.add("影名");
        col_list.add("年份");
        col_list.add("流派/类型");
        col_list.add("评分");
        Vector<Vector<String>> data=new Vector<>();
        table=new JTable(data,col_list);
        jsp=new JScrollPane(table);
        south_jp.add(jsp);
        this.add(south_jp,BorderLayout.SOUTH);
    }
    public void setListener(){
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String s=performance();
                Vector<Vector<String>> list=movie_dao.zuhe(s);
                table.setModel(new javax.swing.table.DefaultTableModel(list,
                        new Vector<>(java.util.Arrays.asList("影名", "年份", "流派/类型", "评分"))));
            }
        });
    }
    public String performance(){
        StringBuilder sb=new StringBuilder("select Title,ReleaseYear,Genre,Score from movies where ");
        if (Y2018.isSelected()){
            sb.append(" Year(ReleaseYear)=2018 and ");
        }else if (Y2019.isSelected()){
            sb.append(" Year(ReleaseYear)=2019 and ");
        }else if (Y2020.isSelected()){
            sb.append(" Year(ReleaseYear)=2020 and ");
        }else if (Y2021.isSelected()){
            sb.append(" Year(ReleaseYear)=2021 and ");
        }else if (Y2022.isSelected()){
            sb.append(" Year(ReleaseYear)=2022 and ");
        }else if (Y2023.isSelected()){
            sb.append(" Year(ReleaseYear)=2023 and ");
        }else{
            sb.append(" Year(ReleaseYear)=2024 and ");
        }
        if(dongzuo.isSelected()){
            sb.append(" Genre like '%动作%' and ");
        }else if(zhanzheng.isSelected()){
            sb.append(" Genre like '%战争%' and ");
        }else if(juqing.isSelected()){
            sb.append(" Genre like '%剧情%' and ");
        }else if(xuanyi.isSelected()){
            sb.append(" Genre like '%悬疑%' and ");
        }else if(fanzui.isSelected()){
            sb.append(" Genre like '%犯罪%' and ");
        }else if(donghua.isSelected()){
            sb.append(" Genre like '%动画%' and ");
        }else if(lishi.isSelected()){
            sb.append(" Genre like '%历史%' and ");
        }else if(kehuan.isSelected()){
            sb.append(" Genre like '%科幻%' and ");
        }else if(zainan.isSelected()){
            sb.append(" Genre like '%灾难%' and ");
        }else if(maoxian.isSelected()){
            sb.append(" Genre like '%冒险%' and ");
        }else if(zhuanji.isSelected()){
            sb.append(" Genre like '%传记%' and ");
        }else if(xiju.isSelected()){
            sb.append(" Genre like '%喜剧%' and ");
        }else if(jingsong.isSelected()){
            sb.append(" Genre like '%惊悚%' and ");
        }else{
            sb.append(" Genre like '%家庭%' and ");
        }
        if(S0_2.isSelected()){
            sb.append(" Score>=0 and Score<=2 ");
        }else if(S3_5.isSelected()){
            sb.append(" Score>=3 and Score<=5 ");
        } else if(S6_8.isSelected()) {
            sb.append(" Score>=6 and Score<=8 ");
        }else{
            sb.append(" Score>=9 and Score<=10 ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new zuheSearchJF().setVisible(true);
    }
}
