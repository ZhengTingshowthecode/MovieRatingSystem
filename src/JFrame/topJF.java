package JFrame;

import DAO.moviesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class topJF extends JFrame {
    moviesDAO movie_dao=new moviesDAO();
    JPanel north_jp=null;
    JPanel south_jp=null;
    JComboBox<String> comboBox=null;
    JButton jb=null;
    JTable table=null;
    JScrollPane jsp=null;
    public topJF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF(){
        this.setTitle("排行榜");
        this.setBounds(200,50,580,560);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void initPanel(){
        north_jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[]items={"选择","上映时间","流派/类型","评分"};
        comboBox=new JComboBox<>(items);
        jb=new JButton("排序");
        north_jp.add(comboBox);
        north_jp.add(jb);
        this.add(north_jp,BorderLayout.NORTH);
        south_jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        Vector<String> col_list=new Vector<>();
        col_list.add("影名");
        col_list.add("上映时间");
        col_list.add("流派/类型");
        col_list.add("评分");
        Vector<Vector<String>> data=new Vector<>();
        table=new JTable(data,col_list);
        jsp=new JScrollPane(table);
        south_jp.add(jsp);
        this.add(south_jp,BorderLayout.CENTER);
    }
    public void setListener(){
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedItem = comboBox.getSelectedItem().toString();
                if(selectedItem.equals("上映时间")){
                    Vector<Vector<String>> list= movie_dao.orderReleaseYear();
                    table.setModel(new javax.swing.table.DefaultTableModel(list,
                            new Vector<>(java.util.Arrays.asList("影名", "发行时间", "流派/类型", "评分"))));
                }else if(selectedItem.equals("流派/类型")){
                    Vector<Vector<String>> list= movie_dao.orderGenre();
                    table.setModel(new javax.swing.table.DefaultTableModel(list,
                            new Vector<>(java.util.Arrays.asList("影名", "发行时间", "流派/类型", "评分"))));
                }else{
                    Vector<Vector<String>> list= movie_dao.orderScore();
                    table.setModel(new javax.swing.table.DefaultTableModel(list,
                            new Vector<>(java.util.Arrays.asList("影名", "发行时间", "流派/类型", "评分"))));
                }
            }
        });
    }
    public static void main(String[] args) {
        new topJF().setVisible(true);
    }
}
