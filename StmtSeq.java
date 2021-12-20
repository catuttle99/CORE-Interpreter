import java.io.FileNotFoundException;

public class StmtSeq {
	//ptr to stmt
	int altNo=0;
	public Stmt stmt;
	public StmtSeq stmtS;
	
	public StmtSeq() {
		stmt=null;
		stmtS=null;
	}
	
	public void parseSS() {
		stmt=new Stmt();
		stmt.parseStmt();
		int tok=Part2Main.tokenizer.getToken();
		
		if(tok!=3 && tok!=7) { //If we aren't at END, ElSE, parse a SS  
			altNo=1;
			stmtS=new StmtSeq(); 
			stmtS.parseSS();
			
		}
	}
	
	public int getAltNo() {
		return this.altNo;
	}
	
	public void printSS() {
		Part2Main.printTab();
		stmt.printStmt();
		
		if(this.getAltNo()==1) {
			
			stmtS.printSS();
		}
	}
	
	public void execSS() throws FileNotFoundException {
		stmt.execStmt();
		
		if(this.getAltNo()==1) {
			stmtS.execSS();
		}
	}
}
