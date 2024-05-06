package main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class RimNumManager {

    private static final HashMap<Character, Integer> rimSymbols = new HashMap<>(){{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
    }};

    private static final HashMap<Integer, Character> intToRim = new HashMap<>(){{
        put(1, 'I');
        put(5, 'V');
        put(10, 'X');
        put(50, 'L');
        put(100, 'C');
    }};

    public static boolean numIsRim(String sequence) {
        int checkSum = 0;
        HashMap<Character, Long> symbCount = new HashMap<>();
        for (char rimSymbol : rimSymbols.keySet()) {
            if (sequence.lastIndexOf(rimSymbol) != -1) {
                checkSum += numOfChars(sequence, rimSymbol);
            }
            symbCount.put(rimSymbol, sequence.chars().filter(c->c==rimSymbol).count());
            if (symbCount.get(rimSymbol) > 3) return false;
        }

        return checkSum == sequence.length();
    }

    private static int numOfChars(String sequence, char ch){
        int sum = 0;
        for(char symbol : sequence.toCharArray()){
            if(symbol == ch){
               sum += 1;
            }
        }
        return sum;
    }

    public static int rimToInt(String sequence){
        sequence = sequence.toUpperCase();
        int result = 0;
        for(int i = 0; i < sequence.length(); i++){
            if (i+1 == sequence.length()) {result+=rimSymbols.get(sequence.charAt(i)); break;}
            if(rimSymbols.get(sequence.charAt(i)) >=  rimSymbols.get(sequence.charAt(i+1))){
                result += rimSymbols.get(sequence.charAt(i));
            }else{
                result += rimSymbols.get(sequence.charAt(i+1)) - rimSymbols.get(sequence.charAt(i));
                i++;
            }
        }
        return result;
    }

    public static String intToRim(int num){
        String result = "";
        Integer[] test = intToRim.keySet().toArray(new Integer[0]);
        Arrays.sort(test, Comparator.reverseOrder());
        for(int i : test){
            String ch = String.valueOf(intToRim.get(i));
            if(isRepresentableWithTwo(num)) {result += representWithTwo(num); break;}
            result += ch.repeat(num/i);
            num %= i;
            if(num==0) break;
//            System.out.println(result);
        }
        return result;
    }

    private static boolean isRepresentableWithTwo(int num){
        for(char symbol : rimSymbols.keySet()){
            if(num+1 == rimSymbols.get(symbol)||num+10 == rimSymbols.get(symbol)){
                return true;
            }
        }
        return false;
    }

    private static String representWithTwo(int num){
        for(char symbol : rimSymbols.keySet()){
            if(num+1 == rimSymbols.get(symbol)){
                return "I"+symbol;
            }
            if(num+10 == rimSymbols.get(symbol)){
                return "X"+symbol;
            }
        }
        return null;
    }
}
