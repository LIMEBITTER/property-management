package cat.utils;


import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;
/**
 * 人脸识别工具
 */
public class FaceUtil {

    private static final String APP_ID = "25898232";
    private static final String APP_KEY = "em83xNFrufg3N9vYFphUEGzD";
    private static final String SECRET_KEY = "kag8AH8TgreH1jHSc5VEn9A08VvlqrPU";

    private static volatile AipFace client = new AipFace(APP_ID, APP_KEY, SECRET_KEY);
    // 创建单例避免多次获取sdk
    public static AipFace getClient(){
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }

    /**
     * 编码
     * @param form
     * @return
     */
    public static String  encodeBase64(byte[] form){
        return Base64Util.encode(form);
    }

    /**
     * 解码
     * @param data
     * @return
     */
    public static byte[] decodeBase64(String data){
        return Base64Util.decode(data);
    }
}

