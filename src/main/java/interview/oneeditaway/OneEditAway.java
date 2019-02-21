package interview.oneeditaway;

import org.apache.commons.lang3.StringUtils;

public class OneEditAway {

    public static boolean isOneEditAway(String s1, String s2) {
        if (StringUtils.isEmpty(s1) || StringUtils.isEmpty(s1)) {
            throw new IllegalArgumentException("input strings could not be empty");
        }
        int errorsNumber = 0;
        if (s1.length() == s2.length()) {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    errorsNumber++;
                    if (errorsNumber > 1) {
                        return false;
                    }
                }
            }
        } else if (Math.abs(s1.length() - s2.length()) == 1) {
            String shortStr = s1;
            String longStr = s2;
            if (s1.length() > s2.length()) {
                shortStr = s2;
                longStr = s1;
            }
            for (int i = 0; i < shortStr.length(); i++) {
                if (shortStr.charAt(i - errorsNumber) != longStr.charAt(i)) {
                    errorsNumber++;
                    if (errorsNumber > 1) {

                        return false;
                    }
                }
            }
            errorsNumber++;
        } else {
            return false;
        }
        if (errorsNumber == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("\"cat\",\"cut\" : "+isOneEditAway("cat","cut"));
        System.out.println("\"cat\",\"cuti\" : "+isOneEditAway("cat","cuti"));
        System.out.println("\"caot\",\"cuot\" : "+isOneEditAway("caot","cuot"));
        System.out.println("\"cat\",\"cat\" : "+isOneEditAway("cat","cat"));
        System.out.println("\"cat\",\"cu\" : "+isOneEditAway("cat","cu"));
        System.out.println("\"at\",\"cut\" : "+isOneEditAway("at","cut"));
        System.out.println("\"cat\",\"ca\" : "+isOneEditAway("cat","ca"));

    }
}
