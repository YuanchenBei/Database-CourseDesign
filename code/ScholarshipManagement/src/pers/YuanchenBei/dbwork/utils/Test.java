package pers.YuanchenBei.dbwork.utils;

public class Test {
    public static void main(String[] args){
        String user1="user1";
        String pass1="123321";
        String user2="user2";
        String pass2="456789";
        String admin="admin";
        String pass="123456";
        String encrypto1=MD5_Encryption.md5(pass1);
        String enctypto2=MD5_Encryption.md5(pass2);
        String enrypto=MD5_Encryption.md5(pass);
        System.out.println(user1+" "+encrypto1);
        System.out.println(user2+" "+enctypto2);
        System.out.println(admin+" "+enrypto);
    }
}
