
public class Fac {
	Op op;
	Fac fac;
	int altNo=0;
	
	public Fac() {
		op=null;
		fac=null;
	}
	
	public void parseFac() {
		op=new Op();
		op.parseOp();
		//Part2Main.tokenizer.skipToken();
		if(Part2Main.tokenizer.getToken()==24) {
			altNo=1; //*
			Part2Main.tokenizer.skipToken();
			fac =new Fac();
			fac.parseFac();
		}
	}
	
	public int getAltNo() {
		return this.altNo;
	}
	
	public void printFac() {
		op.printOp();
		
		if(this.getAltNo()==1) { 
			System.out.print(" * ");
			fac.parseFac();
		}
		
	}
	
	public int execFac() {
		int temp=op.execOp();
		int ans=temp;
		if(this.getAltNo()==1) { 
			//System.out.print(" * ");
			int temp2=fac.execFac();
			ans=temp*temp2;
		}
		return ans;
	}

}
