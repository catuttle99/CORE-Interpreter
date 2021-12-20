import java.io.FileNotFoundException;

public class Stmt {
	
	

	public Assign assign;
	
	public If if1;
	
	public Loop loop;
	
	public In in;
	
	public Out out;
	
	public int altNo;
	
	public Stmt() {
		assign = null;
		if1= null;
		loop= null;
		in=null;
		out=null;
		altNo=0;
		
	}
	
	public void parseStmt() {
		int tok=Part2Main.tokenizer.getToken();
		
		if(tok==32) { //if next token is ID
			altNo=1;
			assign=new Assign();
			assign.parseAssign();
			
		}
		else if(tok==5){ //if next token is if
			altNo=2;
			if1=new If();
			if1.parseIf();
		}
		else if(tok==8) { //if next token is while
			altNo=3;
			loop=new Loop();
			loop.parseLoop();
		}
		else if(tok==10) { // if next token is read
			altNo=4;
			in=new In();
			in.parseIn();
		}
		else if(tok==11) { // if next token is write
			altNo=5;
			out=new Out();
			out.parseOut();
		}
		else {
			System.out.println("Error, Expected a Statement");
			System.exit(1);
		}
		
		if(Part2Main.tokenizer.getToken()==12) { //looking for ;
			Part2Main.tokenizer.skipToken();
		}
		else {
			System.out.println("Error, Expected a semicolon");
			System.exit(1);
		}
		
	}
	
	public int getAltNo() {
		return this.altNo;
	}
	
	public void printStmt() {
		int alt=this.getAltNo();
		
		if(alt==1) { //ID
			assign.printAssign();
			
		}
		else if(alt==2) { // If
			if1.printIf();
		}
		else if(alt==3) { // While
			//loop
			loop.printLoop();
		}
		else if(alt==4) { //Read
			in.printIn();
		}
		else if(alt==5) { //Write
			out.printOut();
		}
		
		System.out.println(";");
	}
	
	public void execStmt() throws FileNotFoundException {
		int alt=this.getAltNo();
		
		if(alt==1) { //ID
			assign.execAssign();
			
		}
		else if(alt==2) { // If
			if1.execIf();
		}
		else if(alt==3) { // While
			//loop
			loop.execLoop();
		}
		else if(alt==4) { //Read
			in.execIn();
		}
		else if(alt==5) { //Write
			out.execOut();
		}
	}
}
