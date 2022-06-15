package util;

import java.util.Random;

public class oConvertUtils {
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if ("null".equals(object)) {
            return (true);
        }
        return (false);
    }

    public static boolean isNotEmpty(Object object) {
        if (object != null && !object.equals("") && !object.equals("null")) {
            return (true);
        }
        return (false);
    }

    /**
     * 随机数
     * @param place 定义随机数的位数
     */
    public static String randomGen(int place) {
        String base = "qwertyuioplkjhgfdsazxcvbnmQAZWSXEDCRFVTGBYHNUJMIKLOP0123456789";
        StringBuffer sb = new StringBuffer();
        Random rd = new Random();
        for(int i=0;i<place;i++) {
            sb.append(base.charAt(rd.nextInt(base.length())));
        }
        return sb.toString();
    }
}
