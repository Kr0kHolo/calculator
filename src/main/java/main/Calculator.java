package main;

public class Calculator {


    public String work(String expression) throws NumberFormatException {
        char op = ' ';
        int[] nums = new int[2];
        int j = 0;
        int amountRimNum = 0;
        String s = "";
        for (int i = 0; i < expression.length(); i++){
            char c = expression.charAt(i);
            if (Character.isLetterOrDigit(c)){
                s+=c;
            } else if (Character.isWhitespace(c) || i + 1 >= expression.length()){
                if(!s.isEmpty()) {
                    if (j > 1) throw new NumberFormatException("I cant operate on more than 2 nums");
                    int[] result = getNum(s);
                    nums[j] = result[0];
                    amountRimNum += result[1];
                    s="";
                    j++;
                }
            }
            else{
                op = c;
            }

            if(i+1 >= expression.length()){
                if (j > 1) throw new NumberFormatException("I cant operate on more than 2 nums");
                int[] result = getNum(s);
                nums[j] = result[0];
                amountRimNum += result[1];
                s="";
                j++;
            }
        }
        if(amountRimNum == 1) throw new NumberFormatException("I cant operate with offsuit nums");
        return switch (op) {
            case '+' -> sum(nums[0], nums[1], amountRimNum==2);
            case '-' -> sub(nums[0], nums[1], amountRimNum==2);
            case '/' -> div(nums[0], nums[1], amountRimNum==2);
            case '*' -> mul(nums[0], nums[1], amountRimNum==2);
            default -> throw new NumberFormatException("It isn't an expression");
        };

    }

    private int[] getNum(String s){
        if(s.lastIndexOf('.') > -1) throw new NumberFormatException("I cant operate on floating point nums");

        int[] result = new int[2];
        int num;

        try {
            num = Integer.parseInt(s);
            result[0] = num;
            result[1] = 0;
        } catch (NumberFormatException e) {
            if (RimNumManager.numIsRim(s)) {
                num =  RimNumManager.rimToInt(s);
                result[0] = num;
                result[1] = 1;
            } else{
                throw new NumberFormatException("Num is not a rim num");
            }
        }

        if(num > 9) throw new NumberFormatException("I cant operate on nums which greater than 9");
        return result;

    }

    private String sum(int a, int b, boolean isRim){
        if(isRim) return RimNumManager.intToRim(a+b);
        return String.valueOf(a+b);
    }

    private String sub(int a, int b, boolean isRim){
        if(a<b && isRim) throw new NumberFormatException("Rims haven't negative nums");
        if(isRim) return RimNumManager.intToRim(a-b);
        return String.valueOf(a-b);
    }

    private String mul(int a, int b, boolean isRim){
        if(isRim) return RimNumManager.intToRim(a*b);
        return String.valueOf(a*b);
    }

    private String div(int a, int b, boolean isRim){
        if(isRim) return RimNumManager.intToRim(a/b);
        return String.valueOf(a/b);
    }
}
