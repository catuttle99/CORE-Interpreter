import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class In {

	Id_List idlist;
	
	public In() {
		idlist=null;
	}
	
	public void parseIn() {
		Part2Main.tokenizer.skipToken();
		idlist=new Id_List();
		idlist.parseId_List();
		
	}
	
	public void printIn() {
		System.out.print("read ");
		idlist.printId_List();
	}
	
	public void execIn() throws FileNotFoundException {
		
		idlist.execReadId_List();
		
		
	}
}
