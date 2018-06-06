public class ONPCalculator {

    private String ONP = new String();

    public ONPCalculator() {
    }

    public String parseEquation(String equation) {
        if (null == equation || equation.isEmpty())
            return null;
        //todo
        Stack ZNAK = new Stack();
        int bracket = 0;
        //System.out.print(args[j]+ " ");
        ONP = "";
        if (equation.endsWith("=")) {
            for (int c = 0; c < equation.length(); ++c) {
                if (equation.charAt(c) >= '0' && equation.charAt(c) <= '9' || equation.charAt(c) == '.') {
                    ONP += equation.charAt(c);
                    if (!(equation.charAt(c + 1) >= '0' && equation.charAt(c + 1) <= '9') &&
                            equation.charAt(c + 1) != '.')
                        ONP += " ";
                } else switch (equation.charAt(c)) {

                    case '-':
                    case '+':

                        while (!ZNAK.isEmpty() && !ZNAK.top().equals("(")) {
                            ONP += ZNAK.top() + " ";
                            ZNAK.pop();
                        }

                        ZNAK.push(equation.charAt(c) + "");
                        break;

                    case '*':
                    case '/':

                        while (!ZNAK.isEmpty() && !ZNAK.top().equals("(") && !ZNAK.top().equals("+") &&
                                !ZNAK.top().equals("-")) {
                            ONP += ZNAK.top() + " ";
                            ZNAK.pop();
                        }

                        ZNAK.push(equation.charAt(c) + "");
                        break;


                    case '^':
                        while (!ZNAK.isEmpty() && !ZNAK.top().equals("(") && !ZNAK.top().equals("+")
                                && !ZNAK.top().equals("-") && !ZNAK.top().equals("*") && !ZNAK.top().equals("/")) {
                            ONP += ZNAK.top() + " ";
                            ZNAK.pop();
                        }

                        ZNAK.push(equation.charAt(c) + "");
                        break;

                    case '%':
                        while (!ZNAK.isEmpty() && !ZNAK.top().equals("(") && !ZNAK.top().equals("+")
                                && !ZNAK.top().equals("-") && !ZNAK.top().equals("*") && !ZNAK.top().equals("/")
                                && !ZNAK.top().equals("^")) {
                            ONP += ZNAK.top() + " ";
                            ZNAK.pop();
                        }

                        ZNAK.push(equation.charAt(c) + "");
                        break;

                    case 'V':
                        while (!ZNAK.isEmpty() && !ZNAK.top().equals("(") && !ZNAK.top().equals("+")
                                && !ZNAK.top().equals("-") && !ZNAK.top().equals("*") && !ZNAK.top().equals("/")
                                && !ZNAK.top().equals("^") && !ZNAK.top().equals("%")) {
                            ONP += ZNAK.top() + " ";
                            ZNAK.pop();
                        }

                        ZNAK.push(equation.charAt(c) + "");
                        break;

                    case '(':
                        ZNAK.push(equation.charAt(c) + "");
                        bracket++;
                        break;
                    case ')':
                        while (!ZNAK.isEmpty() && !ZNAK.top().equals("(")) {
                            ONP += ZNAK.top() + " ";
                            ZNAK.pop();
                            bracket--;
                        }
                        ZNAK.pop();
                        break;

                    case '=':
                        while (!ZNAK.isEmpty()) {
                            ONP += ZNAK.top() + " ";
                            ZNAK.pop();
                        }
                        break;


                    default:
                        break;
                }
            }

        } else {
            System.out.print("Wprowadzone rownanie jest nieprawidlowe! Brakuje znaku ");
            return null;
        }
        if(bracket!=0){
            return null;
        }
        String equationONP = ONP+"=";
        return equationONP;

    }

    public Double calculateONPEquation(String equationONP) {
        if (null == equationONP || equationONP.isEmpty())
            return null;

        String[] elements = equationONP.split(" ");
        Stack stack = new Stack();
        for (String element : elements) {
            if (!element.equals("-") && !element.equals("+") && !element.equals("/") && !element.equals("*")
                    && !element.equals("^") && !element.equals("%") && !element.equals("V")) {
                stack.push(element);
            } else if (stack.size() >= 2) {
                Double y = Double.parseDouble(stack.top());
                stack.pop();
                Double x = Double.parseDouble(stack.top());
                stack.pop();

                switch (element) {
                    case "+":
                        stack.push((x + y) + "");
                        break;
                    case "-":
                        stack.push((x - y) + "");
                        break;
                    case "*":
                        stack.push((x * y) + "");
                        break;
                    case "/":
                        if (x != 0)
                            stack.push((x / y) + "");
                        break;
                    case "^":
                        stack.push((Math.pow(x, y)) + "");
                        break;
                    case "%":
                        stack.push((x % y) + "");
                        break;
                    case "V":
                        stack.push((Math.pow(x, (1 / y))) + "");
                        break;
                    default:
                        break;
                }

            } else
                System.out.println("Error: ");
        }

        if(isNumeric(stack.top()) && stack.size() == 1){
            return Double.parseDouble(stack.top());
        } else {
            return null;
        }


    }
    public boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}

