package cn.itcast.util;

/**
 * Created by Administrator on 2019-08-22.
 */
public class StringUtil {

    /**
     * ?ж???????
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if (str==null || "".equals(str.trim())){
            return  true;
        }else {
            return false;
        }
    }

    /**
     * ?ж???????
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        if(str!=null&& !"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }
}
