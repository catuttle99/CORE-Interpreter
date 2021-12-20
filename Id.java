import java.util.ArrayList;

public class Id {
	
	String name;
	int val;
	boolean declared;
	boolean initialized;
	
	
	public static ArrayList<Id> IdContainer=new ArrayList<Id>();
	public static int idCount=0;
	//public static int phase;
	
	private Id(String n) {
		name=n;
		declared=false;
		initialized=false;
	}
	
	
	public static Id parseId() {
		String temp=Part2Main.tokenizer.idName();
		boolean alreadyIn=false;
		int keep=0;
		
		for(int i=0; i<idCount; i++) {
			if(temp.equals(IdContainer.get(i).name)) {
				alreadyIn=true;
				keep=i;
				break;
			}		
		}
		
		if(alreadyIn) {
			if(Part2Main.phase==1) {
				System.out.println("Error, Double Declaration of "+ IdContainer.get(keep).name);
				System.exit(1);
			}
			Part2Main.tokenizer.skipToken();
			return IdContainer.get(keep);
		}
		else {
			if(Part2Main.phase==2) {
				System.out.println("Error, Undeclared ID "+temp);
				System.exit(1);
			}
			Id nId=new Id(temp);
			IdContainer.add(nId);
			idCount++;
			Part2Main.tokenizer.skipToken();
			return nId;
		}
	}
	
	public int getIdVal() {
		
		return val;
	}
	
	public void setIdVal(int num) {
		this.val=num;
	}
	
	public void printId() {
		System.out.print(this.name);
	}
	
	public int execId() {
		return this.getIdVal();
	}
	
	
	
	
}
