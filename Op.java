import java.util.Objects;

public class Op {
	
	Int in;
	Id id;
	Exp exp;
	int altNo=0;
	
	public Op(){
		in=null;
		id=null;
		exp=null;
	}
	
	public void parseOp() {
		
		int tok=Part2Main.tokenizer.getToken();
		
		if(tok==31) { //if it is an integer
			altNo=1;
			in=new Int();
			in.parseInt();
			//Part2Main.tokenizer.skipToken();
		}
		else if(tok==32) { // if it is an identifier
			altNo=2;
			id=Id.parseId();
			
		}
		else if(tok==20) { //open paren
			altNo=3;
			Part2Main.tokenizer.skipToken();
			exp=new Exp();
			exp.parseExp();
			if(Part2Main.tokenizer.getToken()==21) { //closed paren
				Part2Main.tokenizer.skipToken();
			}
			else {
				System.out.println("Error, Expected )");
				System.exit(1);
			}
		}
		else {
			System.out.println("Error");
			System.exit(1);
		}
		
	}
	
	public int getAltNo() {
		return this.altNo;
	}
	
	public void printOp() {
		int alt=this.getAltNo();
		
		if(alt==1) {
			in.printInt();
		}
		else if(alt==2) {
			id.printId();
			
		}
		else if(alt==3) {
			System.out.print("(");
			exp.printExp();
			System.out.print(")");
			
		}
	}
	
	public int execOp() { 
		int alt=this.getAltNo();
		int answer=0;
		if(alt==1) {
			answer= in.execInt();
		}
		else if(alt==2) {
			
			int val=id.execId();
			if(!Objects.nonNull(val)) {
				System.out.println("Id not initialized");
				System.exit(1);
			}
			answer=val;
			
		}
		else if(alt==3) {
			
			answer=exp.execExp();
			
			
		}
		return answer;
	}

}
