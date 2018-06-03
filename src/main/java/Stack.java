import java.util.LinkedList;

public class Stack {

    LinkedList<String> list = new LinkedList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public String top() throws IllegalStateException {
        if (list.isEmpty())
            throw new IllegalStateException("Cannot peek the top element. Stack is empty!");
        else
            return list.getFirst();
    }

    public String push(String e) {
        list.addFirst(e);
        return list.getFirst();

    }

    public String pop() throws IllegalStateException {
        if (list.isEmpty())
            throw new IllegalStateException("Cannot remove element. Stack is empty!");
        else
            return list.removeFirst();
    }

    public long size() {
        return list.size();
    }

    @Override
    public String toString() {
        String sb = "Stack{" + list +
                '}';
        return sb;
    }
}
