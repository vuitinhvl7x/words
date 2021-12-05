package com.epam.rd.autotasks.words;

import java.util.ArrayList;
import java.util.Arrays;

public class StringUtil {
    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {
        try {
            if (sample == null || words == null)
                return 0;

            sample = sample.trim();
            sample = sample.toLowerCase();
            int count = 0;
            for (String word : words) {
                word = word.trim();
                word = word.toLowerCase();
                if (word.equals(sample))
                    count++;
            }
            return count;
        }catch (Exception ex){
            throw new UnsupportedOperationException();
        }
    }

    public static String[] splitWords(String text) {
        try {
            if (text == null || text.equals("")) return null;
            text = text.replaceAll("\\s", "\\,");
            text = text.replaceAll(";", "\\,");
            text = text.replaceAll(":", "\\,");
            text = text.replaceAll("\\.", "\\,");
            text = text.replaceAll("\\?", "\\,");
            text = text.replaceAll("!", "\\,");
            String[] texts = new String[1000];
            ArrayList<String> textSub = new ArrayList<>();
            texts = text.split(",");
            for (String s : texts) {
                if (!s.equals("")) {
                    textSub.add(s);
                }
            }
            texts = textSub.toArray(new String[0]);
            if (textSub.isEmpty()) {
                return null;
            }
            return texts;
        }catch (Exception ex){
            throw new UnsupportedOperationException();
        }
    }

    public static String convertPath(String path, boolean toWin) {
        try{
        if(path == null){
            return null;
        }else if(path.equals("")) {
            return null;
        }else{
            if(path.contains("C:") &&path.lastIndexOf("C:\\") != 0 ){
                return null;
            }
            if(path.contains("~") && path.lastIndexOf("~") != 0){
                return null;
            }
            if(path.contains("/")&&path.contains("\\")) return null;
            if(path.contains("C:")&&path.contains("/")) return null;
            if(path.contains("~")&&path.contains("\\")) return null;


            if(toWin){
                path = path.replace("~","C:\\User");
                if(path.indexOf("/")==0) {
                    path = path.replaceFirst("/", "C:\\\\");// C:\\
                }
                path = path.replaceAll("/","\\\\");
            }else{
                path = path.replace("C:\\User","~");
                path = path.replace("C:\\","/");
                path = path.replaceAll("\\\\","/");
            }
            return path;
        }
        }catch (Exception ex){
            throw new UnsupportedOperationException();

        }
    }

    public static String joinWords(String[] words) {
        try{

            String res = "[";
        ArrayList<String> List = new ArrayList<>();
        if (words == null || words.length == 0)
            return null;
        for (String word : words)
        {
            if (!word.equals(""))
                List.add(word);
        }
        if (List.size() == 0)
            return null;
        String lastChar = List.get(List.size()-1);
        List.remove(List.size()-1);

        for (String subList : List)
        {
            res = res + subList + ", ";
        }
        res += lastChar + "]";
        return res;
        }catch (Exception ex){
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS", };
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}