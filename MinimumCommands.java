/**
You are given two integers L and R. Their values are initially set to L = 0 and R = 1;

You can change their values by performing the following commands:

The coommand L changes te value of L to 2 * L - R
the command R changes the values of R to 2 * R - L

You are given an integer N =. The goal is to find the minimum number of comands after which L = N or R = N

**/


// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int N) {
        // if (N == 0 || N == 1) 
        //     return 0;
            
        int originalNumber = N;
        
        if (N <= 0) {
            N = Math.abs(N);
        } else {
            N--;
        }
        int L = 0, R = 1;
        ArrayList<String> moves = new ArrayList<>();
        
        if (originalNumber <= 0) {
            while(N != 0) {
                if(Math.abs(L) <= Math.abs(R)) {
                    L = moveLeft(moves, L, R);
                } else {
                    R = moveRight(moves, L, R);
                }
                N = N >> 1;
            }
        } else {
            while(N != 0) {
                if(Math.abs(R) <= Math.abs(L)) {
                    R = moveRight(moves, L, R);
                } else {
                    L = moveLeft(moves, L, R);
                }
                N = N >> 1;
            }
        }
        
        return moves.size();
    }
    
    public int moveLeft(ArrayList<String> moves, int L, int R) {
      moves.add("L");
      return 2 * L - R;
    }
    
    public int moveRight(ArrayList<String> moves, int L, int R) {
      moves.add("R"); 
      return 2 * R - L;
    }

    public static void main(String[] args) {
      Solution sol = new Solution();
      int ans = sol.solution(15);
      System.out.printf("for N = %s ans = %s\n", 15, ans);
    }
}
