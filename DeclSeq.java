
public class DeclSeq {
	int altNo=0;
	//ptr to decl
	public Decl decl;
	//ptr to declSeq
	public DeclSeq declS;
	
	public DeclSeq() {
		decl=null;
		declS=null;
	}
	
	public void parseDS() {
		
		decl=new Decl();
		decl.parseDecl();
		
		if(Part2Main.tokenizer.getToken()!=2) { //if we are not at a begin token
			altNo=1;
			declS=new DeclSeq();
			declS.parseDS();
		}
		
	}
	
	public int getAltNo() {
		return this.altNo;
	}
	
	public void printDS() {
		Part2Main.indent++;
		Part2Main.printTab();
		Part2Main.indent--;
		decl.printDecl();
		
		if(this.getAltNo()==1) { //we are not at begin token yet
			this.printDS(); 
		}
	}
}
