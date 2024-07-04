package UTIL;

import java.util.List;
import DAO.moviesDAO;
import ENTITY.movies_info;
import javax.swing.table.AbstractTableModel;

public class tableModel extends AbstractTableModel {
    moviesDAO movies_dao = new moviesDAO();
    String[] table_cols = {"电影名", "发布时间", "时长(分钟)", "类型", "语言", "国家","简介","评分"};
    List<movies_info> list = null;
    String[][] data = null;

    public tableModel(pageModel pageModel) {
        list = movies_dao.getMovies(pageModel);
        data = new String[list.size()][8];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getMovieName();
            data[i][1] = list.get(i).getReleaseYear();
            data[i][2] = list.get(i).getDuration()+"";
            data[i][3] = list.get(i).getGenre();
            data[i][4] = list.get(i).getMovieLanguage();
            data[i][5] = list.get(i).getCountry();
            data[i][6] = list.get(i).getSynopsis();
            data[i][7] = list.get(i).getScore()+"";
        }
    }

    @Override
    public int getColumnCount() {
        return table_cols.length;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int index) {
        return table_cols[index];
    }
}