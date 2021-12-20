public class Comp_Op {
	int operation;
	public Comp_Op() {
		operation=0;
	}
	
	public void parseComp_Op() {
		int tok=Part2Main.tokenizer.getToken();
		
		if(tok==25) { //!=
			operation=1;
			Part2Main.tokenizer.skipToken();
		}
		else if(tok==26) { //==
			operation=2;
			Part2Main.tokenizer.skipToken();
		}
		else if(tok==27) {// <
			operation=3;
			Part2Main.tokenizer.skipToken();
		}
		else if(tok==28) { // >
			operation=4;
			Part2Main.tokenizer.skipToken();
		}
		else if(tok==29) { // <=
			operation=5;
			Part2Main.tokenizer.skipToken();
		}
		else if(tok==30) { //>=
			operation=6;
			Part2Main.tokenizer.skipToken();
		}
		else {
			System.out.println("Error, Expected boolean operator");
			System.exit(1);
		}
	}
	
	public void printComp_Op() {
		int o=this.operation;
		
		switch(o) {
		
		case 1:
			System.out.print(" != ");
			break;
			
		case 2:
			System.out.print(" == ");
			break;
		
		case 3:
			System.out.print(" < ");
			break;
		
		case 4:
			System.out.print(" > ");
			break;
		
		case 5:
			System.out.print(" <= ");
			break;
		
		case 6:
			System.out.print(" >= ");
			break;
			
		default:
			break;
		}
			
		
		
	}
	
	public boolean execComp_Op(Op op1, Op op2) {
		boolean answer=false;
		int val1=op1.execOp();
		int val2=op2.execOp();
		
		int o=this.operation;
		
		switch(o) {
		
		case 1:
			//System.out.print(" != ");
			answer=(val1 != val2);
			break;
			
		case 2:
			//System.out.print(" == ");
			answer=(val1 == val2);
			break;
		
		case 3:
			//System.out.print(" < ");
			answer=(val1 < val2);
			break;
		
		case 4:
			//System.out.print(" > ");
			answer=(val1 > val2);
			break;
		
		case 5:
			//System.out.print(" <= ");
			answer=(val1 <= val2);
			break;
		
		case 6:
			//System.out.print(" >= ");
			answer=(val1 >= val2);
			break;
			
		default:
			break;
		}
		
		return answer;
		
		
	}

}
