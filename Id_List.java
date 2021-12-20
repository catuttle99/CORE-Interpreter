
public class Id_List {
	
	int altNo=0;
	public Id id;
	public Id_List idL;

	public Id_List() {
		id=null;
		idL=null;
		
	}
	
	public void parseId_List() {
		//id.parseId();
		id=Id.parseId();
		
		if(Part2Main.tokenizer.getToken()==13) { //If we are at a comma -> more Ids to come
			altNo=1;
			Part2Main.tokenizer.skipToken();
			idL=new Id_List();
			idL.parseId_List();
			
		}
		
	}
	
	public int getAltNo() {
		return this.altNo;
	}
	
	public void printId_List() {
		System.out.print(id.name);
		if(this.getAltNo()==1) {
			System.out.print(", ");
			idL.printId_List();
		}
	}
	
	public void execReadId_List() {
		String line="";
		if(Part2Main.data.hasNext()) {
			line=Part2Main.data.nextLine();
		}
		else {
			System.out.println("Error, nothing to read");
			System.exit(1);
		}
		
		int val=Integer.parseInt(line);
		
		id.setIdVal(val);
		if(this.getAltNo()==1) {
			idL.execReadId_List();
		}
	}
	
	public void execWriteId_List() {
		System.out.println(id.name+"= "+id.getIdVal());
		if(this.getAltNo()==1) {
			idL.execWriteId_List();
		}
	}
}
