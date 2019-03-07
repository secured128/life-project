package interview.fb.screening;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GetAllGroups {

    public static Set<Set<String>> getAllGroups(int startIndex, int endIndex, Set<Set<String>> result, String[] array) {
        if (startIndex > endIndex) {
            Set<String> newSet = new HashSet<String>();
            newSet.add("empty set");
            result.add(newSet);
            return result;
        }
        Set<Set<String>> newResult = new HashSet<Set<String>>();
        for (Set<String> set : result) {
            newResult.add(set);
            Set<String> newSet = new HashSet<String>(set);
            newSet.add(array[startIndex]);
            newResult.add(newSet);

        }
        Set<String> newSet = new HashSet<String>();
        newSet.add(array[startIndex]);
        newResult.add(newSet);
        startIndex++;
        return getAllGroups(startIndex, endIndex, newResult, array);
    }

    public static void main(String[] args) {
        Set<Set<String>> result = new HashSet<Set<String>>();
        String[] array = new String[]{"a", "b", "c", "d"};
        Set<Set<String>> result1 = getAllGroups(0, array.length - 1, result, array);
        for (Set<String> set : result1) {
            System.out.println(Arrays.toString(set.toArray()));
        }

    }

}
