public class ONPCalculator {
    //TODO implement parseEquation and calculateONPEquation by moving foo() and bar() methods
    private Stack stack = new Stack();

    private String equation = "";
    private String ONP = new String();


    @Deprecated
    public ONPCalculator(String equation) {
        System.out.println("Ctor ONP: " + equation);
        this.equation = equation;
    }

    public ONPCalculator() {
        this("");
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

        //todo
        String[] wartosci = equationONP.split(" ");
        Stack ZNAKw = new Stack();
        for (String element : wartosci) {
            if (!element.equals("-") && !element.equals("+") && !element.equals("/") && !element.equals("*")
                    && !element.equals("^") && !element.equals("%") && !element.equals("V")) {
                ZNAKw.push(element);
            } else if (ZNAKw.size() >= 2) {
                Double y = Double.parseDouble(ZNAKw.top());
                ZNAKw.pop();
                Double x = Double.parseDouble(ZNAKw.top());
                ZNAKw.pop();

                switch (element) {
                    case "+":
                        ZNAKw.push((x + y) + "");
                        break;
                    case "-":
                        ZNAKw.push((x - y) + "");
                        break;
                    case "*":
                        ZNAKw.push((x * y) + "");
                        break;
                    case "/":
                        if (x != 0)
                            ZNAKw.push((x / y) + "");
                        break;
                    case "^":
                        ZNAKw.push((Math.pow(x, y)) + "");
                        break;
                    case "%":
                        ZNAKw.push((x % y) + "");
                        break;
                    case "V":
                        ZNAKw.push((Math.pow(x, (1 / y))) + "");
                        break;
                    default:
                        break;
                }

            } else
                System.out.println("Error: ");
        }
        //System.out.println(ZNAKw.top());

        if(isNumeric(ZNAKw.top()) && ZNAKw.size() == 1){
            return Double.parseDouble(ZNAKw.top());
        } else {
            return null;
        }


    }
    public boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}

