import java.io.FileNotFoundException;

public class If {

	int altNo=0;
	Cond cond;
	StmtSeq stmtS1;
	StmtSeq stmtS2;
	public If() {
		cond=null;
		stmtS1=null;
		stmtS2=null;
		
	}
	
	
	public void parseIf() {
		Part2Main.tokenizer.skipToken(); //skip the if token
		
		cond=new Cond();
		cond.parseCond();
		
		Part2Main.tokenizer.skipToken(); //skip the then token
		
		stmtS1=new StmtSeq();
		stmtS1.parseSS();
		
		int tok=Part2Main.tokenizer.getToken();
		if(tok==3) { // at end
			altNo=1;
			Part2Main.tokenizer.skipToken(); //skip end
			//we will now be at a ;
		}
		else if(tok==7) { //at else
			altNo=2;
			Part2Main.tokenizer.skipToken(); //skip else
			
			stmtS2=new StmtSeq();
			stmtS2.parseSS();
			
			tok=Part2Main.tokenizer.getToken(); 
			if(tok==3) { //at end
				Part2Main.tokenizer.skipToken();
				//we will now be at a ;
			}
			else {
				System.out.println("Error, Expected end");
				System.exit(1);
			}
		}
		else {
			System.out.println("Error, Expected end or else to match if");
			System.exit(1);
		}
		
	}
	
	public int getAltNo() {
		return this.altNo;
	}
	
	public void printIf() {
		System.out.print("if ");
		cond.printCond();
		System.out.print(" then ");
		System.out.println();
		Part2Main.indent++;
		stmtS1.printSS();
		
		if(this.getAltNo()==1) {
			System.out.print(" end");
		}
		else if(this.getAltNo()==2) {
			Part2Main.indent--;
			
			Part2Main.printTab();
			System.out.print("else ");
			Part2Main.indent++;
			System.out.println();
			stmtS2.printSS();
			Part2Main.indent--;
			Part2Main.printTab();
			System.out.print("end");
		}
	}
	
	public void execIf() throws FileNotFoundException {
		if(cond.execCond()) { //if condition is true
			stmtS1.execSS(); //execute statement 
		}
		else {
			if(this.getAltNo()==2) {
				stmtS2.execSS(); //execute the statment after else
			}
		}
	}
}
