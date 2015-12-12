import java.util.StringTokenizer;

class Converter {
    private StringTokenizer tokenizer;
    private String[] tokenList;
    private int tokenCount;
    private String PostFixResult;
    Stack operatorStack;

    public void tokenize(String input) {

        this.tokenizer = new StringTokenizer(input, "+-/()*^", true);
        this.tokenCount = tokenizer.countTokens();
        this.tokenList = new String[tokenCount];
        for (int i = 0; i < tokenCount; i++) {
            this.tokenList[i] = tokenizer.nextToken();
        }
    }

    public String toPostFix(String input) {
        this.PostFixResult = "";
        this.operatorStack = new Stack();
        this.tokenize(input);

        for (int i = 0; i < this.tokenList.length; i++) {
            switch (this.tokenList[i]) {
                case "(":
                    operatorStack.push(this.tokenList[i]);
                    break;
                case ")":
                    while (!(operatorStack.top().equals("("))) {
                        this.PostFixResult += this.operatorStack.top() + " ";
                        this.operatorStack.pop();
                    }
                    this.operatorStack.pop(); // One last time to remove "("
                    break;
                case "*":
                    while (!(this.isLastOnStackLower("*"))) {
                        this.PostFixResult += this.operatorStack.top() + " ";
                        this.operatorStack.pop();
                    }
                    this.operatorStack.push("*");
                    break;
                case "^":
                    while (!(this.isLastOnStackLower("^"))) {
                        this.PostFixResult += this.operatorStack.top() + " ";
                        this.operatorStack.pop();
                    }
                    this.operatorStack.push("^");
                    break;
                case "/":
                    while (!(this.isLastOnStackLower("/"))) {
                        this.PostFixResult += this.operatorStack.top() + " ";
                        this.operatorStack.pop();
                    }
                    this.operatorStack.push("/");
                    break;
                case "+":
                    while (!(this.isLastOnStackLower("+"))) {
                        this.PostFixResult += this.operatorStack.top() + " ";
                        this.operatorStack.pop();
                    }
                    this.operatorStack.push("+");
                    break;
                case "-":
                    while (!(this.isLastOnStackLower("-"))) {
                        this.PostFixResult += this.operatorStack.top() + " ";
                        this.operatorStack.pop();
                    }
                    this.operatorStack.push("-");
                    break;
                default:
                    this.PostFixResult += this.tokenList[i] + " ";
                    break;
            }
        }
        while (!(this.operatorStack.top().equals("EMPTY"))) {
            this.PostFixResult += this.operatorStack.top() + " ";
            this.operatorStack.pop();
        }
        return this.PostFixResult;
    }

    private boolean isLastOnStackLower(String currentOperand) {
        String lastOperand = operatorStack.top();
        boolean condition;
        switch (currentOperand) {
            case "*":
                if (lastOperand.equals("+") || lastOperand.equals("-") || lastOperand.equals("(") ||
                        lastOperand.equals("EMPTY")) {
                    condition = true;
                } else {
                    condition = false;
                }
                break;
            case "/":
                if (lastOperand.equals("+") || lastOperand.equals("-") || lastOperand.equals("(") ||
                        lastOperand.equals("EMPTY")) {
                    condition = true;
                } else {
                    condition = false;
                }
                break;
            case "^":
                if (lastOperand.equals("+") || lastOperand.equals("-") || lastOperand.equals("(") ||
                        lastOperand.equals("EMPTY") || lastOperand.equals("/") || lastOperand.equals("*")) {
                    condition = true;
                } else {
                    condition = false;
                }
                break;
            case "+":
                if (lastOperand.equals("(") || lastOperand.equals("EMPTY")) {
                    condition = true;
                } else {
                    condition = false;
                }
                break;
            case "-":
                if (lastOperand.equals("(") || lastOperand.equals("EMPTY")) {
                    condition = true;
                } else {
                    condition = false;
                }
                break;
            default:
                condition = false;
        }
        return condition;
    }
}
