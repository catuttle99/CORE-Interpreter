
public class Int {
	Digit dig;
	Int in;
	int num;
	
	public Int() {
		dig=null;
		in=null;
		num=0;
	}
	
	public void parseInt() {
		num=Part2Main.tokenizer.intVal();
		Part2Main.tokenizer.skipToken();
		
	
	}
	
	public int getNum() {
		return this.num;
	}
	
	public void printInt() {
		System.out.print(this.getNum());
	}
	
	public int execInt() {
		return this.getNum();
	}

}
