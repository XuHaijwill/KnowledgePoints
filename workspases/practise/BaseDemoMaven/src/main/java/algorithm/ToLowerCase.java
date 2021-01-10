package algorithm;

/**
 * @Author xhj
 * @Description // a-z：97-122  A-Z：65-90  0-9：48-57
 * @Date 2020-03-18 22:11
 **/
public class ToLowerCase {

    public static void main(String[] args){
        System.out.println(toLowerCase("Hello World"));
    }

    public static String toLowerCase(String str) {
        if(str == null || str.length() == 0){
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for(char ch : str.toCharArray()){
            if(ch >= 'A' && ch <= 'Z'){
                sb.append((char)(ch + 32));
            }else{
                sb.append(ch);
            }
        }
        return sb.toString();
    }


}
