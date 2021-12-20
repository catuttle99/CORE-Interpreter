
public class Exp {

	public Fac fac;
	public Exp exp;
	int altNo=0;
	int operator;
	public Exp() {
		fac=null;
		exp=null;
		
	}
	
	public void parseExp() {
		fac=new Fac();
		fac.parseFac();
		
		
		if(Part2Main.tokenizer.getToken()==22 || Part2Main.tokenizer.getToken()==23) { //if + or -
			if(Part2Main.tokenizer.getToken()==22) {
				altNo=1;
				operator=1; //+
			}
			else {
				altNo=2;
				operator=2; //-
			}
			Part2Main.tokenizer.skipToken();
			exp=new Exp();
			exp.parseExp();
		}
		
		
		
	}
	
	public int getAltNo() {
		return this.altNo;
	}
	
	public void printExp() {
		fac.printFac();
		
		if(this.getAltNo()==1) {
			System.out.print(" + ");
			exp.printExp();
		}
		else if(this.getAltNo()==2) {
			System.out.print(" - ");
			exp.printExp();
		}
	}
	
	public int execExp() {
		int temp1=fac.execFac();
		int ans=temp1;
		
		if(this.getAltNo()==1) {
			//System.out.print(" + ");
			int temp2=exp.execExp();
			ans=temp1+temp2;
		}
		else if(this.getAltNo()==2) {
			//System.out.print(" - ");
			int temp2=exp.execExp();
			ans=temp1-temp2;
		}
		
		return ans;
		
	}
}
