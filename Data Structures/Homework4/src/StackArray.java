public class StackArray {
	private String[] stack = new String[1000];
	private int top = -1; //Empty stack
	private int size = top+1;
	
	public void push(String x) {
		top++;
		stack[top] = x;
	}
	public String pop() {
		top--;
		return stack[top+1];
		
	}
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		}
		else {
			return false;
		}
	}
	public void print() {
		if (isEmpty()) {
			System.out.println("no data");
		}
		StackArray resultStack = new StackArray();
		while (!isEmpty()) {
			resultStack.push(pop());
		}
		while (resultStack.isEmpty() == false) {
			push(resultStack.pop());
			System.out.println(stack[top]);
		}
	}
	
	public void printX(int line) {
		StackArray resultStack = new StackArray();
		while (top != line-1) {
			resultStack.push(pop());
		}
		System.out.println(stack[top]);
			while (resultStack.isEmpty() == false) {
				push(resultStack.pop());
		}
	}
	public void delete() {
		while (isEmpty() == false) {
			pop();
		}
	}
	
	public void deleteX(int line) {
		StackArray resultStack = new StackArray();
		while (top != line-1) {
			resultStack.push(pop());
		}
		System.out.println(pop());
			while (resultStack.isEmpty() == false) {
				push(resultStack.pop());
		}
	}
	public static void main(String[] args) {
		StackArray stack = new StackArray();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.print();
		stack.deleteX(3);
		stack.print();
	}
}
