
public class Cond {
	int altNo;
	Comp comp;
	Cond cond1; //!<cond>
	Cond cond2; //<cond> or <cond> Left
	Cond cond3; //<cond> and <cond> Right
	int type; //1 for &&, 2 for or
	
	public Cond() {
		altNo=0;
		comp=null;
		cond1=null;
		cond2=null;
		cond3=null;
		type=0;
		
	}
	
	public void parseCond() {
		int tok=Part2Main.tokenizer.getToken();
		
		if(tok==20) { //open paren===<comp>
			altNo=1;
			comp=new Comp();
			comp.parseComp();
		}
		else if(tok==15) { // !  ===!<cond>
			altNo=2;
			Part2Main.tokenizer.skipToken();
			cond1=new Cond();
			cond1.parseCond(); 
			
		}
		else if(tok==16) { //[  could be one of last two alternates
			altNo=3;
			Part2Main.tokenizer.skipToken();
			cond2 = new Cond();
			cond2.parseCond(); //parse left cond
			if(Part2Main.tokenizer.getToken()==18) { //&&
				type=1;
				Part2Main.tokenizer.skipToken();
			}
			else if(Part2Main.tokenizer.getToken()==19) { // or
				type=2;
				Part2Main.tokenizer.skipToken();
			}
			else {
				System.out.println("Error, Expected && or or");
				System.exit(1);
			}
			cond3=new Cond();
			cond3.parseCond();
			if(Part2Main.tokenizer.getToken()==17) { //]
				Part2Main.tokenizer.skipToken();
			}
			else {
				System.out.println("Error, Expected ]");
				System.exit(1);
			}
			
			
		}
		
	}
	
	public int geAltNo() {
		return this.altNo;
	}
	
	public void printCond() {
		int alt=this.geAltNo();
		
		if(alt==1) { //comp
			comp.printComp();
		}
		else if(alt==2) { //!cond
			System.out.print("!");
			cond1.printCond();
		}
		else if(alt==3) { //[Cond op cond]
			System.out.print("[");
			cond2.printCond();
			
			if(this.type==1) {
				System.out.print(" && ");
			}
			else if(this.type==2) {
				System.out.print(" or ");
			}
			
			cond3.printCond();
			
			System.out.print("]");
		}
		
		
	}
	
	public boolean execCond() {
		boolean answer=false;
		int alt=this.geAltNo();
		
		if(alt==1) { //comp
			answer=comp.execComp();
		}
		else if(alt==2) { //!cond
			
			answer=!(cond1.execCond());
		}
		else if(alt==3) { //[Cond op cond]
			
			if(this.type==1) {
				//System.out.print(" && ");
				answer=(cond2.execCond() && cond3.execCond());
			}
			else if(this.type==2) {
				//System.out.print(" or ");
				answer=(cond2.execCond() || cond3.execCond());
			}
			
			
		}
		return answer;
	}

}
