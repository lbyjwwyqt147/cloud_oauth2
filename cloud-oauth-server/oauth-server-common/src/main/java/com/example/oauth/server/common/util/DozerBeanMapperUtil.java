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

    private static Map<Class, MethodAccess> methodMap = new HashMap<Class, MethodAccess>();
    private static Map<String, Integer> methodIndexMap = new HashMap<String, Integer>();
    private static Map<Class, List<String>> fieldMap = new HashMap<Class, List<String>>();


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


    /**
     *  通过 ReflectASM 类来实现java 反射对象拷贝 DozerBeanMapperUtil.asmCopyProperties(A,B.Class) 将A对象值拷贝到B对象
     * @param source 转换的对象值
     * @param target 目标 类对象
     * @return  返回转换后对象
     */
    public static<T> T  reflectasmCopyProperties(Object source, Class<T> target) {
        T result = null;
        MethodAccess descMethodAccess = methodMap.get(source.getClass());
        if (descMethodAccess == null) {
            descMethodAccess = cache(source);
        }
        MethodAccess orgiMethodAccess = methodMap.get(target.getClass());
        if (orgiMethodAccess == null) {
            orgiMethodAccess = cache(target);
        }

        List<String> fieldList = fieldMap.get(target.getClass());
        for (String field : fieldList) {
            String getKey = target.getClass().getName() + "." + "get" + field;
            String setkey = source.getClass().getName() + "." + "set" + field;
            Integer setIndex = methodIndexMap.get(setkey);
            if (setIndex != null) {
                int getIndex = methodIndexMap.get(getKey);
                // 参数一需要反射的对象
                // 参数二class.getDeclaredMethods 对应方法的index
                // 参数对三象集合
                descMethodAccess.invoke(source, setIndex.intValue(),
                        orgiMethodAccess.invoke(target, getIndex));
            }
            try {
                result = (T)target.newInstance();
            } catch (Exception e1) {
                return null;
            }
        }
        return  result;
    }




    // 单例模式
    private static MethodAccess cache(Object target) {
        synchronized (target.getClass()) {
            MethodAccess methodAccess = MethodAccess.get(target.getClass());
            Field[] fields = target.getClass().getDeclaredFields();
            List<String> fieldList = new ArrayList<String>(fields.length);
            for (Field field : fields) {
                if (Modifier.isPrivate(field.getModifiers())
                        && !Modifier.isStatic(field.getModifiers())) { // 是否是私有的，是否是静态的
                    // 非公共私有变量
                    String fieldName = StringUtils.capitalize(field.getName()); // 获取属性名称
                    int getIndex = methodAccess.getIndex("get" + fieldName); // 获取get方法的下标
                    int setIndex = methodAccess.getIndex("set" + fieldName); // 获取set方法的下标
                    methodIndexMap.put(target.getClass().getName() + "." + "get"
                            + fieldName, getIndex); // 将类名get方法名，方法下标注册到map中
                    methodIndexMap.put(target.getClass().getName() + "." + "set"
                            + fieldName, setIndex); // 将类名set方法名，方法下标注册到map中
                    fieldList.add(fieldName); // 将属性名称放入集合里
                }
            }
            fieldMap.put(target.getClass(), fieldList); // 将类名，属性名称注册到map中
            methodMap.put(target.getClass(), methodAccess);
            return methodAccess;
        }
    }
}
