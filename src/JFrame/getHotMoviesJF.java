package JFrame;

import DAO.moviesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class getHotMoviesJF extends JFrame {
    moviesDAO movie_dao=new moviesDAO();
    JPanel jp=null;
    JLabel jl=null;
    JScrollPane jsp=null;
    JTable table=null;
    JButton jButton=new JButton("点击");
    public getHotMoviesJF(){
        initJF();
        initPanel();
        setListener();
    }
    public void initJF() {
        this.setTitle("热门电影");
        this.setBounds(200,50,500,500);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void initPanel(){
        jp=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jl=new JLabel("热门前五");
        jl.setFont(new Font(Font.DIALOG,Font.BOLD,25));
        jl.setForeground(Color.RED);
        Vector<String> list=new Vector<>();
        list.add("影名");
        list.add("流派/类型");
        Vector<Vector<String>> data=new Vector<>();
        table=new JTable(data,list);
        jsp=new JScrollPane(table);
        jButton.setBackground(Color.RED);
        jButton.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        jp.add(jl);
        jp.add(jButton);
        jp.add(jsp);
        this.add(jp,BorderLayout.CENTER);
    }
    public void setListener(){
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Vector<Vector<String>> list= movie_dao.gethot();
                table.setModel(new javax.swing.table.DefaultTableModel(list,
                        new Vector<>(java.util.Arrays.asList("影名", "流派/类型"))));
            }
        });
    }
    public static void main(String[] args) {
        new getHotMoviesJF().setVisible(true);
    }
}
