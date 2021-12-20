
public class Decl {
	
	public Id_List idL;
	
	
	public Decl() {
		idL=null;
	}
	
	public void parseDecl() {
		if(Part2Main.tokenizer.getToken()==4) { //if we are at an int keyword
			
			Part2Main.tokenizer.skipToken();
			idL=new Id_List();
			idL.parseId_List();
			
			if(Part2Main.tokenizer.getToken()==12) { //if we are at a semicolon, id_list is over
				Part2Main.tokenizer.skipToken();
			}
			else {
				System.out.println("Error, Expected ; keyword");
				System.exit(1);
			}
		}
		else {
			System.out.println("Error, Expected int keyword");
			System.exit(1);
		}
	}
	
	public void printDecl() {
		System.out.print("int ");
		idL.printId_List();
		System.out.println(";");
	}
}
