package des;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * http://www.blogjava.net/amigoxie/archive/2014/07/06/415503.html
 */
public class ThreeDESUtil {

    /** 
     * CBC加密 
     * @param key 密钥 
     * @param data 明文
     * @return Base64编码的密文 
     * @throws Exception 
     */
    public static String des3EncodeCBC(String key, String data) throws Exception {
        Security.addProvider(new BouncyCastleProvider()); 
        Key deskey = keyGenerator(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ips = new IvParameterSpec(KEY_IV);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data.getBytes(ENCODING));
        return new BASE64Encoder().encode(bOut);
    }

    /** 
     * CBC解密 
     * @param key 密钥 
     * @param data Base64编码的密文
     * @return 明文 
     * @throws Exception 
     */
    public static String des3DecodeCBC(String key, String data) throws Exception {
        Key deskey = keyGenerator(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ips = new IvParameterSpec(KEY_IV);
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(new BASE64Decoder().decodeBuffer(data));
        return new String(bOut, ENCODING);
    }

    /**
     *
     * 生成密钥key对象
     * @return 密钥对象
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws Exception
     */
    private static Key keyGenerator(String keyStr) throws Exception {
        byte input[] = HexString2Bytes(keyStr);
        DESedeKeySpec KeySpec = new DESedeKeySpec(input);
        SecretKeyFactory KeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        return ((Key) (KeyFactory.generateSecret(((java.security.spec.KeySpec) (KeySpec)))));
    }

    private static int parse(char c) {
        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    // 从十六进制字符串到字节数组转换
    private static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    // 算法名称
    private static final String KEY_ALGORITHM = "desede";
    // 算法名称/加密模式/填充方式
    private static final String CIPHER_ALGORITHM = "desede/CBC/NoPadding";

    private static final byte[] KEY_IV = { 1, 2, 3, 4, 5, 6, 7, 8 };

    private static final String ENCODING = "utf-8";

//    public static void main(String[] args) throws Exception {
//       String key = "6C4E60E55552386C759569836DC0F83869836DC0F838C0F7";
//        String data = "amigoxieamigoxie        ";
//        String str5 = des3EncodeCBC(key, data);
//        System.out.println(str5);
//
//        String str6 = des3DecodeCBC(key, str5);
//        System.out.println(str6);
//    }
}