package pers.YuanchenBei.dbwork.utils;

public class String_isEmpty {
    /*
    判断字符串是否为空
    */
    public static boolean isEmpty(String str){
        if(str==null||"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

    /*
    判断字符串是否非空
    */
    public static boolean isNotEmpty(String str){
        if(str!=null && (!"".equals(str.trim()))){
            return true;
        } else{
            return false;
        }
    }
}
