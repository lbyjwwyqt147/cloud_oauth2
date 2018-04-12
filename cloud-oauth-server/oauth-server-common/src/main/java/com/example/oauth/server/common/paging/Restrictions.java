package com.example.oauth.server.common.paging;

import java.util.Collection;  

import org.apache.commons.lang3.StringUtils;



/***
 * 条件构造器  
 * 用于创建条件表达式 
 * @author ljy
 *
 */
public class Restrictions {  
  
    /** 
     * 不为空 
     * @param fieldName : 匹配字段 
     * @return 
     */  
    public static SimpleExpression isNotEmpty(String fieldName) {  
        return new SimpleExpression (fieldName, ICriterion.Operator.ISNOTEMPTY);
    }  
  
    /** 
     * 为空 
     * @param fieldName : 匹配字段 
     * @return 
     */  
    public static SimpleExpression isEmpty(String fieldName) {  
        return new SimpleExpression (fieldName, ICriterion.Operator.ISEMPTY);
    }  
  
    /** 
     * 为空 
     * @param fieldName : 匹配字段 
     * @return 
     */  
    public static SimpleExpression isNull(String fieldName) {  
        return new SimpleExpression (fieldName, ICriterion.Operator.ISNULL);
    }  
  
    /** 
     * 不为空 
     * @param fieldName : 匹配字段 
     * @return 
     */  
    public static SimpleExpression isNotNull(String fieldName) {  
        return new SimpleExpression (fieldName, ICriterion.Operator.ISNOTNULL);
    }  
  
    /** 
     * 等于  
     * @param fieldName : 匹配字段  
     * @param value : 匹配值 
     * @return  
     */    
    public static SimpleExpression eq(String fieldName, Object value) {    
        if(value == null || value.equals(""))return null;    
        return new SimpleExpression (fieldName, value, ICriterion.Operator.EQ);
    }    
      
    /**  
     * 等于 （函数条件查询） 
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...) 
     * @param value : 匹配值 
     * @return  
     */    
    public static ProjectionExpression eq(Projection projection, Object value) {    
        if(value == null || value.equals(""))return null;  
        return new ProjectionExpression (projection.getCol(), value, projection.getType(), ICriterion.Operator.EQ);
    }    
        
    /**  
     * 不等于  
     * @param fieldName : 匹配字段  
     * @param value : 匹配值 
     * @return  
     */    
    public static SimpleExpression ne(String fieldName, Object value) {    
        if(value == null || value.equals(""))return null;    
        return new SimpleExpression (fieldName, value, ICriterion.Operator.NE);
    }    
      
    /**  
     * 不等于（函数条件查询）  
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...) 
     * @param value : 匹配值 
     * @return  
     */    
    public static ProjectionExpression ne(Projection projection, Object value) {    
        if(value == null || value.equals(""))return null;    
        return new ProjectionExpression (projection.getCol(), value, projection.getType(), ICriterion.Operator.NE);
    }    
    
    /**  
     * 模糊匹配  
     * @param fieldName : 匹配字段  
     * @param value : 匹配值  
     * @return  
     */    
    public static SimpleExpression like(String fieldName, String value) {    
        if(value == null || value.equals(""))return null;    
        return new SimpleExpression (fieldName, value, ICriterion.Operator.LIKE);
    }    
      
    /**  
     * 模糊匹配 （函数条件查询） 
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...) 
     * @param value : 匹配值 
     * @return  
     */    
    public static ProjectionExpression like(Projection projection, String value) {    
        if(value == null || value.equals(""))return null;    
        return new ProjectionExpression (projection.getCol(), value, projection.getType(), ICriterion.Operator.LIKE);
    }    
    
    /**  
     *  自定义模式模糊匹配 
     * @param fieldName : 匹配字段 
     * @param value : 匹配值 
     * @param matchMode : 匹配方式(MatchMode.START\END\ANYWHERE) 
     * @return  
     */    
    public static SimpleExpression like(String fieldName, String value,    
            ICriterion.MatchMode matchMode) {
        if(value == null || value.equals(""))return null;    
        return new SimpleExpression(fieldName, value, matchMode, ICriterion.Operator.LIKE);
    }    
      
    /**  
     *  自定义模式模糊匹配（函数条件查询） 
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...) 
     * @param value  : 匹配值 
     * @param matchMode : 匹配方式(MatchMode.START\END\ANYWHERE) 
     * @return  
     */    
    public static ProjectionExpression like(Projection projection, String value,    
            ICriterion.MatchMode matchMode) {
        if(value == null || value.equals(""))return null;    
        return new ProjectionExpression (projection.getCol(), value, projection.getType(), ICriterion.Operator.LIKE, matchMode);
    }    
    
    /**  
     * 大于  
     * @param fieldName : 匹配字段  
     * @param value : 匹配值  
     * @return  
     */    
    public static SimpleExpression gt(String fieldName, Object value) {    
        if(value == null || value.equals(""))return null;    
        return new SimpleExpression (fieldName, value, ICriterion.Operator.GT);
    }    
      
    /**  
     * 大于（函数条件查询）  
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...)  
     * @param value : 匹配值 
     * @return  
     */    
    public static ProjectionExpression gt(Projection projection, Object value) {    
        if(value == null || value.equals(""))return null;    
        return new ProjectionExpression (projection.getCol(), value, projection.getType(), ICriterion.Operator.GT);
    }    
    
    /**  
     * 小于  
     * @param fieldName : 匹配字段  
     * @param value : 匹配值  
     * @return  
     */    
    public static SimpleExpression lt(String fieldName, Object value) {    
        if(value == null || value.equals(""))return null;    
        return new SimpleExpression (fieldName, value, ICriterion.Operator.LT);
    }    
      
    /**  
     * 小于（函数条件查询）   
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...)   
     * @param value : 匹配值  
     * @return  
     */    
    public static ProjectionExpression lt(Projection projection, Object value) {    
        if(value == null || value.equals(""))return null;    
        return new ProjectionExpression (projection.getCol(), value, projection.getType(), ICriterion.Operator.LT);
    }    
    
    /**  
     * 小于等于 
     * @param fieldName : 匹配字段  
     * @param value : 匹配值  
     * @return  
     */    
    public static SimpleExpression lte(String fieldName, Object value) {    
        if(value == null || value.equals(""))return null;    
        return new SimpleExpression (fieldName, value, ICriterion.Operator.LTE);
    }    
      
    /**  
     * 小于等于（函数条件查询） 
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...)  
     * @param value : 匹配值 
     * @return  
     */    
    public static ProjectionExpression lte(Projection projection, Object value) {    
        if(value == null || value.equals(""))return null;    
        return new ProjectionExpression (projection.getCol(), value, projection.getType(), ICriterion.Operator.LTE);
    }    
    
    /**  
     * 大于等于 
     * @param fieldName : 匹配字段 
     * @param value : 匹配值 
     * @return  
     */    
    public static SimpleExpression gte(String fieldName, Object value) {    
        if(value == null || value.equals(""))return null;    
        return new SimpleExpression (fieldName, value, ICriterion.Operator.GTE);
    }    
      
    /**  
     * 大于等于 
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...)  
     * @param value : 匹配值 
     * @return  
     */   
    public static ProjectionExpression gte(Projection projection, Object value) {    
        if(value == null || value.equals(""))return null;    
        return new ProjectionExpression (projection.getCol(), value, projection.getType(), ICriterion.Operator.GTE);
    }    
    
      
    /**  
     * 或者  
     * @param criterions  
     * @return  
     */    
    public static LogicalExpression or(ICriterion... criterions){  
        return new LogicalExpression(criterions, ICriterion.Operator.OR);
    }    
      
    /**
     * 或者
     * @param fieldName
     * @param value
     * @return
     */
    public static LogicalExpression orIn(Collection fieldName, String value) {  
    	if(StringUtils.isNotBlank(value)){
    	    SimpleExpression[] ses = new SimpleExpression[fieldName.size()];  
            int i=0;  
            for(Object obj : fieldName){  
                ses[i]=new SimpleExpression(obj.toString(),value,ICriterion.Operator.EQ);
                i++;  
            }  
            return new LogicalExpression(ses,ICriterion.Operator.OR);
    	}else{
            return null;  
    	}
    
    }
    
      
    /**  
     * 区间  
     * @param column : 匹配字段 
     * @param1 val1 左区间  
     * @param2 val2 右区间 
     * @return  
     */   
    public static LogicalExpression between(String column, Object val1, Object val2){  
        return new LogicalExpression(column, val1, val2, ICriterion.Operator.BETWEEN);
    }  
      
      
      
      
    /**  
     * 包含于  
     * @param fieldName : 匹配字段 
     * @param value : 匹配值 
     * @return  
     */    
    @SuppressWarnings("rawtypes")    
    public static LogicalExpression in(String fieldName, Collection value) {    
        SimpleExpression[] ses = new SimpleExpression[value.size()];    
        int i=0;    
        for(Object obj : value){    
            ses[i]=new SimpleExpression(fieldName,obj,ICriterion.Operator.EQ);
            i++;    
        }    
        return new LogicalExpression(ses,ICriterion.Operator.OR);
    }  
  
    /** 
     * 包含于 
     * @param fieldName : 匹配字段 
     * @param value : 匹配值 
     * @return 
     */  
    @SuppressWarnings("rawtypes")  
    public static LogicalExpression notIn(String fieldName, Collection value) {  
        SimpleExpression[] ses = new SimpleExpression[value.size()];  
        int i=0;  
        for(Object obj : value){  
            ses[i]=new SimpleExpression(fieldName,obj,ICriterion.Operator.NE);
            i++;  
        }  
        return new LogicalExpression(ses,ICriterion.Operator.AND);
    }  
  
}  