
public class Comp {
	Op op1;
	Op op2;
	Comp_Op comp_op;

	public Comp() {
		op1=null;
		op2=null;
		comp_op=null;
		
	}
	
	public void parseComp() {
		Part2Main.tokenizer.skipToken(); //skip (
		op1=new Op();
		op1.parseOp();
		
		comp_op=new Comp_Op();
		comp_op.parseComp_Op();
		
		op2=new Op();
		op2.parseOp();
		
		if(Part2Main.tokenizer.getToken()==21) { //)
			Part2Main.tokenizer.skipToken();
		}
		else {
			System.out.println("Error, Expected )");
			System.exit(1);
		}
	}
	
	public void printComp() {
		System.out.print("(");
		op1.printOp();
		comp_op.printComp_Op();
		op2.printOp();
		System.out.print(")");
	}
	
	public boolean execComp() {
		boolean answer=comp_op.execComp_Op(op1, op2);
		return answer;
	}
}
