import java.io.FileNotFoundException;

public class Program {

	//ptr to declSeq
	public DeclSeq declSeq;
	//ptr to stmtSeq
	public StmtSeq stmtSeq;
	
	public Program() {
		declSeq=null;
		stmtSeq=null;
	}
	
	public void parseProgram() {
		//parse declSeq and parse stmtSeq
		if(Part2Main.tokenizer.getToken()==1) {
			Part2Main.tokenizer.skipToken();
			Part2Main.phase=1;
			
			declSeq=new DeclSeq();
			declSeq.parseDS();
		}
		else {
			System.out.println("Needs Program keyword");
			System.exit(1);
		}
		
		if(Part2Main.tokenizer.getToken()==2) {
			Part2Main.tokenizer.skipToken();
			Part2Main.phase=2;
			stmtSeq=new StmtSeq();
			stmtSeq.parseSS();
		}
		else {
			System.out.println("Needs Begin keyword");
			System.exit(1);
		}
		if(Part2Main.tokenizer.getToken()==3) {
			Part2Main.tokenizer.skipToken();
		}
		else {
			System.out.println("Needs End keyword");
			System.exit(1);
		}
	}
	
	public void printProgram() {
		System.out.println("program");
		declSeq.printDS();
		System.out.println("begin");
		
		Part2Main.indent++;
		stmtSeq.printSS();
		System.out.println("end");
		
		
		
	}
	
	public void execProgram() throws FileNotFoundException {
		stmtSeq.execSS();
	}
	
}
