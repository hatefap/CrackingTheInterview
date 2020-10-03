package com.zerone;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringAndArray {

    boolean isUniqueChars(String s){
        if(s.length() == 0)
            return true;
        Long result = s.codePoints()
                .mapToObj(cp -> String.valueOf(Character.toChars(cp)))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getValue();
        return result <= 1;

    }


    Map<String, Long> getCharCountMap(String s){
        return  s.codePoints()
                .mapToObj(cp -> String.valueOf(Character.toChars(cp)))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    boolean permutation(String s1, String s2){
        if (s1.length() != s2.length())
            return false;
        return getCharCountMap(s1).equals(getCharCountMap(s2));
    }

    boolean palindrome(String s){
        // our string should have at least one character with odd number
        Map<String, Long> charCount = getCharCountMap(s.toLowerCase().replaceAll("\\s+",""));
        // number of characters with odd count
        long cnt = charCount.entrySet()
                .stream()
                .filter(e -> e.getValue() % 2 != 0)
                .count();
        return cnt <= 1;
    }


    String compress(String s){
        StringBuilder sb = new StringBuilder("");
        char ch = s.charAt(0);
        int cnt = 0;
        for (int i = 0; i < s.length(); i++){
        if (ch == s.charAt(i)) cnt++;
            else{
                sb.append(ch).append(cnt);
                ch = s.charAt(i);
                cnt = 1;
            }
        }
        StringBuilder compressed = sb.append(ch).append(cnt);
        return compressed.length() > s.length() ? compressed.toString() : s;
    }
    public static void main(String[] args) {
        String s1 = "Hi, 你好, おはよう, α-Ω\uD834\uDD1E";
        String s2 = "Hi, 你好,α-Ω\uD834\uDD1E おはよう, ";
        StringAndArray m = new StringAndArray();
//        System.out.println(m.permutation(s1, s2));
//        System.out.println(m.getCharCountMap("asfasdf afdafdfdfdfdfdfyjyj"));
//        System.out.println(m.palindrome("Tact Coa"));
        m.compress("a");

    }
}
