import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author jueyue on 20-5-8.
 */
public class URITest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode("我是新英雄","UTF-8"));
        System.out.println(URLDecoder.decode("%E6%88%91%E6%98%AF%E6%96%B0%E8%8B%B1%E9%9B%84","UTF-8"));
        System.out.println(URLEncoder.encode("我是新英雄","UTF-8"));
    }
}
