package UTIL;

import DAO.moviesDAO;

public class pageModel {
    moviesDAO movies_dao=new moviesDAO();
    private int pageNO;  //当前页数
    private int pageSize;  //每页的显示个数
    private long totalCnt;  //总共的同学个数
    private long totalPage;  //总页数
    public pageModel(int pageSize){
        //默认当前第一页
        this.pageNO=1;
        this.pageSize=pageSize;
        this.totalCnt= movies_dao.getMovieTotal();
        if (totalCnt%pageSize==0){
            totalPage=totalCnt/pageSize;
        }else{
            totalPage=totalCnt/pageSize+1;
        }
    }
    public void nextPage(){
        if(pageNO!=totalPage){
            pageNO++;
        }
    }
    public void prevPage(){
        if(pageNO!=1){
            pageNO--;
        }
    }
    public void firstPage(){
        pageNO=1;
    }
    public void lastPage(){
        pageNO=(int)totalPage;
    }
    public int getPageNO() {
        return pageNO;
    }

    public void setPageNO(int pageNO) {
        this.pageNO = pageNO;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(long totalCnt) {
        this.totalCnt = totalCnt;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
}