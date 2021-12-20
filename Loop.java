import java.io.FileNotFoundException;

public class Loop {
	Cond cond;
	StmtSeq stmtSeq;

	public Loop() {
		cond=null;
		stmtSeq=null;
		
	}
	
	public void parseLoop() {
		Part2Main.tokenizer.skipToken(); //skip while token
		cond=new Cond();
		cond.parseCond();
		
		
		Part2Main.tokenizer.skipToken(); //skip loop token
		stmtSeq=new StmtSeq();
		stmtSeq.parseSS();
		
		
		Part2Main.tokenizer.skipToken();
		//System.out.println(Part2Main.tokenizer.getToken());
		
		
	}
	
	public void printLoop() {
		System.out.print("While ");
		cond.printCond();
		System.out.println(" loop ");
		Part2Main.indent++;
		stmtSeq.printSS();
		Part2Main.indent--;
		Part2Main.printTab();
		System.out.print("end");
	}
	
	public void execLoop() throws FileNotFoundException {
		
		while(cond.execCond()) {
			stmtSeq.execSS();
			
		}
	}
}
