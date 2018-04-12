package com.example.oauth.server.common.paging;

/***
 * 计算用类 
 * @author ljy
 *
 */
public class Projections {

	public static Projection Max(String col){  
        return new Projection(col,  ICriterion.Projection.MAX);  
    }  
      
    public static Projection Length(String col){  
        return new Projection(col, ICriterion.Projection.LENGTH);  
    }  
      
    public static Projection Min(String col){  
        return new Projection(col, ICriterion.Projection.MIN);  
    }  
      
    public static Projection Sum(String col){  
        return new Projection(col, ICriterion.Projection.SUM);  
    }  
}
