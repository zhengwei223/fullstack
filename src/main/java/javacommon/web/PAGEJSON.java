package javacommon.web;

import java.util.List;

/**
 * 封装集合，提供分页JSON的包装类
 * @todo
 * @author 丁彦涛
 * @date 2016年7月19日
 */
public class PAGEJSON {

    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }
    private int total;
    private List rows;
    
    public  PAGEJSON(){
        
        
    }
    
   public  PAGEJSON(int total ,List  rows){
        this.total=total;
        this.rows=rows;
        
    }
    
}
