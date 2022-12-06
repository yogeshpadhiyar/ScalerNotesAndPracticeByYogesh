package com.stack;

import java.util.Scanner;
import java.util.Stack;

public class StackAll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        System.out.println(isBalance(A));
    }

    private static int isBalance(String A) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i)==')'){
                if(stack.isEmpty() || stack.peek()=='{' ||stack.peek()=='[')   return 0;
                else stack.pop();
            }else if(A.charAt(i)=='}'){
                if(stack.isEmpty() || stack.peek()=='(' ||stack.peek()=='[')   return 0;
                else stack.pop();
            }else if(A.charAt(i)==']'){
                if(stack.isEmpty() || stack.peek()=='{' ||stack.peek()=='(')   return 0;
                else stack.pop();
            } else   stack.push(A.charAt(i));
        }
        return (stack.isEmpty())?1:0;
    }
}
