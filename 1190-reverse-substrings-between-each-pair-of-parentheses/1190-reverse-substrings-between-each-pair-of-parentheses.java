class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();

        Stack<Character> stack = new Stack<>();


        for(int i = 0; i < n; i ++){

            char c = s.charAt(i);

            if(c != ')'){
                stack.push(c);
            }else{
                StringBuilder sb = new StringBuilder();
                while(!stack.isEmpty() && stack.peek() != '('){
                    sb.append(stack.pop());
                }

                if(!stack.isEmpty()){
                    stack.pop();
                }

                for(int j = 0; j < sb.length(); j ++){
                    stack.push(sb.charAt(j));
                }
            }

        }
        StringBuilder res = new StringBuilder();
        int x = stack.size();
        for(int i = 0; i < x; i ++){
            res.append(stack.pop());
        }

        return res.reverse().toString();
    }
}