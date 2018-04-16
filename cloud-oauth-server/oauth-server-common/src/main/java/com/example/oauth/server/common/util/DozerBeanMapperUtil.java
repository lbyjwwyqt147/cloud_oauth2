package com.example.oauth.server.common.util;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/***
 *  bean 对象 复制
 */
public abstract class DozerBeanMapperUtil {

    /**
     *  通过spring BeanUtils 进行对象转换拷贝 DozerBeanMapperUtil.copyProperties(A,B.Class) 将A对象值拷贝到B对象
     * @param source 转换的对象值
     * @param targetClass 目标 类对象
     * @return 转换后的对象
     */
    public static<T> T copyProperties(Object source,Class<T> targetClass){
        T result = null;
        if(source!=null&&!source.equals("")) {
            try {
                result = (T) targetClass.newInstance();
                BeanUtils.copyProperties(source,result);
            } catch (Exception e1) {
                e1.printStackTrace();
                return null;
            }
        }
        return result;
    }

    /**
     *  通过spring BeanUtils 进行对象转换拷贝 DozerBeanMapperUtil.copyProperties(A,B.Class,id) 将A对象值拷贝到B对象 忽略转换字段
     * @param source 转换的对象值
     * @param targetClass 目标 类对象
     * @return 转换后的对象
     */
   public static<T> T copyProperties(Object source, Class<T> targetClass, String... ignoreProperties){
       T result = null;
       if(source!=null&&!source.equals("")) {
           try {
               result = (T) targetClass.newInstance();
               BeanUtils.copyProperties(source,result,ignoreProperties);
           } catch (Exception e1) {
               e1.printStackTrace();
               return null;
           }
       }
       return result;
   }

    /**
     *  通过spring BeanUtils 进行集合对象转换拷贝 DozerBeanMapperUtil.copyProperties(List<A>,B.Class) 将A对象集合值拷贝到B对象集合
     * @param sourceList 转换的对象集合
     * @param targetClass 目标 类对象
     * @return 转换后的对象
     */
    public static<T> List<T> copyProperties(Collection<?> sourceList, Class<T> targetClass){
        List<T> list = new LinkedList<>();
        if(sourceList!=null&&!sourceList.isEmpty()) {
            try {
                for (Iterator<?> i$ = sourceList.iterator(); i$.hasNext();) {
                    Object sourceObject = i$.next();
                    T  targetObject = (T) targetClass.newInstance();
                    BeanUtils.copyProperties(sourceObject,targetObject);
                    list.add(targetObject);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                return null;
            }
        }
        return list;
    }

    /**
     *  通过java 反射进行对象转换拷贝 DozerBeanMapperUtil.transferObject(A,B.Class) 将A对象值拷贝到B对象
     * @param source 转换的对象值
     * @param targetClass 目标 类对象
     * @return 转换后的对象
     */
    public static<T> T transferObject(Object source,Class<T> targetClass){
        T result = null;
        if(source!=null&&!source.equals("")){
            Method[] methods =  source.getClass().getMethods();
            try {
                result = (T)targetClass.newInstance();
            } catch (Exception e1) {
                return null;
            }
            Method m;
            for(int i=0;i<methods.length;i++){
                m = methods[i];
                try {
                    if(m.getName().startsWith("set")){
                        String fieldName =  m.getName().replaceFirst("set", "");
                        Method method = result.getClass().getMethod(m.getName(), m.getParameterTypes());
                        Method getMethod = source.getClass().getMethod("get"+fieldName, new Class[]{});
                        method.invoke(result, getMethod.invoke(source, new Object[]{}));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
        return result;
    }

}
