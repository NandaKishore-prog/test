package hello;

public class genric<T> {
	private T element;
	public genric(T element){
		this.element=element;
		
	}
	public T getelement() {
		return element;
	}
	public    void setelement(T element) {
	this.element=element;
	}
	public static void main(String[]args) {
	genric<String> a=new genric<>("Lavade");
	genric<Integer> b=new genric<>(300);
	System.out.println("The value is:" +b.getelement());
	System.out.println("the value is:" +a.getelement());
	}

}
