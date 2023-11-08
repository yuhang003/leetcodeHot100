package easy;

import java.util.*;

/**
 * 20. 有效的括号
 * https://leetcode.cn/problems/valid-parentheses/
 */
public class Num20IsValid {
    public boolean isValid(String s) {
        ArrayList<Character> arrayList = new ArrayList<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '(' || chars[i] == '{' || chars[i] == '[') {
                arrayList.add(chars[i]);
            }else {
                Character character = map.get(chars[i]);
                if(arrayList.size() == 0 || character != arrayList.get(arrayList.size()-1)){
                    return false;
                }
                arrayList.remove(arrayList.size()-1);
            }
        }
        if (arrayList.size() > 0){
            return false;
        }
        return true;
    }


    public boolean isValid2(String s) {
        if (s.length() % 2 != 0) return false;
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');

        Deque<Character> deque = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '(' || chars[i] == '{' || chars[i] == '[') {
                deque.push(chars[i]);
            }else {
                Character character = map.get(chars[i]);
                if(deque.size() == 0 || character != deque.peek()){
                    return false;
                }
                deque.pop();
            }
        }

        return deque.size() == 0;
    }
}
