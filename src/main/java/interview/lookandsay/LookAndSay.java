package interview.lookandsay;

import org.apache.commons.lang3.StringUtils;

public class LookAndSay {

    public static String lookAndSay(String sec) {
        if (!StringUtils.isNumeric(sec)) {
            throw new IllegalArgumentException("invalid arguments");
        }

        StringBuilder result = new StringBuilder();
        if (sec.length() == 1) {
            result.append(1).append(sec);
        } else {
            int currentCounter = 1;
            char startNum = sec.charAt(0);
            char currentNum = startNum;
            for (int i = 1; i < sec.length(); i++) {
                currentNum = sec.charAt(i);
                if (currentNum != startNum) {
                    result.append(currentCounter).append(startNum);
                    startNum = currentNum;
                    currentCounter = 0;
                }
                currentCounter++;
            }
            result.append(currentCounter).append(startNum);
        }
        return result.toString();
    }

    public static void lookAndSayLoop(String seq, int limit, int currentLoop) {
        if (currentLoop > limit) {
            return;
        } else {
            String res = lookAndSay(seq);
            System.out.println(res);
            currentLoop++;
            lookAndSayLoop(res, limit, currentLoop);
        }

    }

    public static void main(String[] args) {
        lookAndSayLoop("1", 10, 0);
    }
}
