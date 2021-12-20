import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2Main {

	public static Tokenizer tokenizer; //tokenizer is now a global variable
	//public static String dataF;
	public static int phase; //TODO implement which phase we are in, DeclS or StmtS in order to know about id's (DN Part 3)
	//phase = 1 : DeclSeq phase
	//phase = 2 : StmtSeq phase
	public static File dataFile;
	public static Scanner data;
	public static int indent=0;
	
	public static void printTab() {
		for(int i=0; i<indent; i++) {
			System.out.print("   ");
		}
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		
		tokenizer=new Tokenizer(args[0]);
		
		String dataF=args[1];
		
		dataFile=new File(dataF);
		data=new Scanner(dataFile);
		
		Program prog=new Program();
		
		prog.parseProgram();
		
		prog.printProgram();
		
		System.out.println();
		prog.execProgram();
		
		
		
	}
}
