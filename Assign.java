public class Assign {
	
	public Id id;
	public Exp exp;
	
	
	//phase = 1 for DS
	//phase = 2 for SS
	public Assign() {
		id=null;
		exp=null;
		
	}
	
	public void parseAssign() {
		//for DS
		
		id=Id.parseId();
		if(Part2Main.tokenizer.getToken()==14) {
			Part2Main.tokenizer.skipToken(); 
			
		}
		else {
			System.out.println("Error, expected = ");
			System.exit(1);
		}
		 
		exp=new Exp();
		exp.parseExp();
		if(Part2Main.tokenizer.getToken()==12) { 
			//Part2Main.tokenizer.skipToken();
			
		}
		else {
			System.out.println("Error, Expected ;");
		}
		
				
	}
	
	public void printAssign() {
		id.printId();
		System.out.print("=");
		exp.printExp();
	}
	
	public void execAssign() {
		int ex=exp.execExp();
		id.setIdVal(ex); 
		//evaluate the exp on right and assign it to id on the left
	}
}
