public class Main {

    public static void main(String[] args) {
        Stack testStack = new Stack();
        System.out.println(testStack.push("test"));
        System.out.println(testStack.isEmpty());
        System.out.println(testStack.size());
        System.out.println(testStack.top());
        System.out.println(testStack.pop());
        System.out.println(testStack.isEmpty());


        ONPCalculator onpCalculator = new ONPCalculator("2+2=");
        //onpCalculator.foo();
        //onpCalculator.bar();


    }
}
