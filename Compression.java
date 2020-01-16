/**
 * Given a string and a compression width compress it such that the string returned cannot be compressed further. 
 * Remove all consecutive similar characters of length "width" from string and compress again until we can't ompress further
 * E.g Given acbbbcc and width = 3, it is first compressed to accc then to a. a is then returned as as it cannot be 
 * compressed further
 * E.g Given accab and width = 2, should return b
 */

 public class Compression {

    public String solution(String value, int width) {
        StringBuilder sb = new StringBuilder(value);

        for (int i = 0; i < sb.length(); i++) {
            System.out.println("word is " + value + " index is " + i);
            // we need to do a check for when i is less than string length since we will be comparing with 
            // next string, this takes care of end of string character so we dont have IndexOfBound exception
            if (i < sb.length() - 1 &&  sb.charAt(i) == sb.charAt(i+1)) {  
                int counter = 1, tempIndex = i;
                while(tempIndex < sb.length() - 1 && 
                    sb.charAt(tempIndex) == sb.charAt(tempIndex+1) && 
                    counter < width) 
                {
                    counter++;
                    tempIndex++;
                }

                sb.delete(i, i + width);
                break;
            }
        }

        String result = sb.toString();

        if (value.equals(result)) 
            return result;
        else
            return solution(result, width);
    }

    public static void main(String[] args) {
        Compression com = new Compression();

        String[][] array = { 
            { "acbbbcc", "3" }, 
            {"accab", "2"},
        };

        for (int i = 0; i < array.length; i++) {
            String word = array[i][0];
            int width = Integer.parseInt(array[i][1]);
            System.out.printf("%s compressed to %s \n", word, com.solution(word, width));
        }
    }
 }