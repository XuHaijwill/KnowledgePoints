package algorithm;

/**
 * @Author xhj
 * @Description 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 * <p>
 * 如果不存在最后一个单词，请返回 0 。
 * <p>
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 * @Date 2020-03-18 22:11
 **/
public class LengthOfLastWord {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("       "));
    }

    public static int lengthOfLastWord(String s) {
        if (s == null && s.trim().length() == 0) {
            return 0;
        }
        int count = 0;
        char[] sArrs = s.toCharArray();
        int lastIndex = 0;
        int beaginIndex = 0;
        for (int i = sArrs.length - 1; i >= 0; i--) {
            if (sArrs[i] != ' ' && count == 0) {
                lastIndex = i;
                count++;
            }else if(sArrs[i] == ' '){
                beaginIndex = i;
                break;
            }
        }

        return lastIndex - beaginIndex;
    }
}
