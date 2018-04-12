package com.example.oauth.server.common.paging;


/**  
 * 函数条件构造器  
 */  
public class Projection{  
    //函数作用字段  
    private String col;  
    //函数类型  
    private ICriterion.Projection type;  
      
    public Projection(String col, ICriterion.Projection type){  
        this.col = col;  
        this.type = type;  
    }  
  
    public String getCol() {  
        return col;  
    }  
  
    public ICriterion.Projection getType() {  
        return type;  
    }  
      
}  
