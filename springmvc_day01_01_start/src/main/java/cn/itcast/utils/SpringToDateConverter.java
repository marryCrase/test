package cn.itcast.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串转日期格式
 */
public class SpringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        if (s==null){
            throw new RuntimeException("请输入日期");
        }else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return dateFormat.parse(s);
            } catch (Exception e) {
                throw new RuntimeException("数据类型转换错误");
            }
        }
    }
}
