# 常用加密方法

## md5

#### 1、使用JDK自带MessageDigest

```
public class MD5Util {
    
    public static String getMD5Str(String str) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //16是表示转换为16进制数
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }

}
```

#### 2、使用Spring自带的DigestUtils

```
String md5Str = DigestUtils.md5DigestAsHex("原串".getBytes());
```

```
说明
只使用md5加密是不行的，很容易被破解。常见的做法有：

先对原串进行一些处理，比如先给它拼接一个字符串常量，再进行md5加密。即使对方解密，也获取不到原串。
使用多种加密算法。比如先用md5加密，再对加密的结果使用其它加密算法进行加密。比如先使用md5加密，对加密的结果再次使用md5加密。
这2种方式方式经常一起使用。
```

