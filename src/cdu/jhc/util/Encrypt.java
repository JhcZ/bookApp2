package cdu.jhc.util;

import com.mysql.cj.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//加密工具类：MD5
public class Encrypt {
    public static String toMd5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String pwd = URLEncoder.encode(s, "utf-8");
            byte[] bytes = md.digest(pwd.getBytes("utf-8"));
            return StringUtils.toHexString(bytes, bytes.length);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
