package implementations;

import interfaces.Deque;
import interfaces.Solvable;

public class BalancedParentheses implements Solvable {
    private String parentheses;
    private Character current;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @Override
    public Boolean solve() {

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < parentheses.length(); i++) {
            char x = parentheses.charAt(i);

            if (x == '(' || x == '{' || x == '[') {
                stack.push(x);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            }

            switch (x) {
                case ')':
                    current = stack.pop();
                    if (x == '{' || x == '[') {
                        return false;
                    }
                    break;
                case '}':
                    current = stack.pop();
                    if (current == '(' || current == '[') {
                        return false;
                    }
                    break;
                case ']':
                    current = stack.pop();
                    if (current == '(' || current == '{') {
                        return false;
                    }
                    break;
            }
        }
        return (stack.isEmpty());
    }
}
