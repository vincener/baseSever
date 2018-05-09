package com.hq.CloudPlatform.CA.annotations;

import com.hq.CloudPlatform.CA.entity.BaseEntity;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * 前台字段合法性校验检查
 *
 * @author WANG YONG Created Date 2017年6月1日
 */
public class AttributeCheck<Entity extends BaseEntity> {

    static ResourceBundle rb = ResourceBundle.getBundle("regEx");

    public String CheckString(Entity entity) {
        //获取配置文件中strs的值按照,隔开组成List
        List<String> strs = Arrays.asList(rb.getString("ValidFieldNames").split(","));
        //遍历实体类的属性
        for (Field f : entity.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            //获取实体类的属性名称
            String str = f.getName();
            //如果配置文件中的strs中包涵实体类的属性就按照配置文件中的正则验证合法性
            if (strs.contains(str)) {
                try {
                    String  temp = "";
                    switch(str){
                        case "email":
                        case "phone":
                        case "address":
                        case "linkMan":
                            temp = "noValidated";
                            break;
                    }
                    if("noValidated".equals(temp) && (f.get(entity)==null||"".equals(f.get(entity)))){
                        ;
                    }else{

                        if (f.get(entity) == null) {
                            return rb.getString(str + "Title") + "不能为空，请重试";
                        }
                        if (f.get(entity) != null) {
                            if (!Pattern.compile(rb.getString(str)).matcher(f.get(entity).toString().trim()).matches()) {
                                return rb.getString(str + "Title") + "不合法，请重试";
                            }
                        }

                    }



                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
