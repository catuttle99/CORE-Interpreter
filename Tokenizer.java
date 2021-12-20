//package TokenizerPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//Author: Noah Hufnagel

class Tokenizer{
	BufferedReader br;
	String[] currentLine;
	int currentIndex;
	int lineCount;
	
	ArrayList<String> reserved= new ArrayList<String>();
	ArrayList<String> symbols= new ArrayList<String>();
	//a list of the first characters in a symbol
	final String symbolPrimers= ";,!=[]&|()+-*<>";
	
	public Tokenizer(String fname) {
		try {
			br= new BufferedReader(new FileReader(new File(fname)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//load our arrayLists up with words and symbols (all but int and id)
		reserved.add("program");
		reserved.add("begin");
		reserved.add("end");
		reserved.add("int");
		reserved.add("if");
		reserved.add("then");
		reserved.add("else");
		reserved.add("while");
		reserved.add("loop");
		reserved.add("read");
		reserved.add("write");
		symbols.add(";");
		symbols.add(",");
		symbols.add("=");
		symbols.add("!");
		symbols.add("[");
		symbols.add("]");
		symbols.add("&&");
		symbols.add("||");
		symbols.add("(");
		symbols.add(")");
		symbols.add("+");
		symbols.add("-");
		symbols.add("*");
		symbols.add("!=");
		symbols.add("==");
		symbols.add("<");
		symbols.add(">");
		symbols.add("<=");
		symbols.add(">=");
		
		
		lineCount=0;
		readLine();
	}
	
	private void readLine() {
		try {
			String s="";
			while(s!=null &&s.trim().equals("")) {
				s=br.readLine();
				lineCount++;
			}
			if(s!=null) {		
				currentLine=s.trim().split("[ \t\r\n]");
			}
			else currentLine= null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//check to see if we have symbols wedged in tokens and seperate them
		for (int i=0; currentLine != null && i<currentLine.length; i++) {
			//only check more if this is not already a perfect reserved word or symbol
			if(!reserved.contains(currentLine[i]) && !symbols.contains(currentLine[i])) {
				//check to see if the start of a symbol is somewhere in this string
				int cInd=0;
				while(cInd<symbolPrimers.length()) {
					//if so then we should seperate it
					if (currentLine[i].contains( symbolPrimers.substring(cInd,cInd+1) ))seperate(i);
					cInd++;
				}
			}
			
		}
		
		currentIndex=0;
		
	}
	private void seperate(int i) {
		int count=0;
		int tokenStart=0;
		ArrayList<String> newTokens = new ArrayList<String>();
		//loop through all characters
		while(count<currentLine[i].length()) {
			if(symbolPrimers.contains(currentLine[i].substring(count,count+1))) {
				//we have found the start of a symbol
				//add the string up to this point as a new token
				if(count>0 && count-tokenStart>0) {
					newTokens.add(currentLine[i].substring(tokenStart,count));
					tokenStart+=count-tokenStart;
				}
				//isolate the symbol part of it greedily
				//first try for a 2 length symbol
				if(count+1<currentLine[i].length() && symbols.contains(currentLine[i].substring(count,count+2))) {
					newTokens.add(currentLine[i].substring(count,count+2));
					//count++;
					tokenStart+=2;
				}
				//then try for a 1 length symbol
				else if(symbols.contains(currentLine[i].substring(count,count+1))) {
					newTokens.add(currentLine[i].substring(count,count+1));
					tokenStart++;
					//count++;
				}
				//if neither work than we have a stray symbol character, end with error
				else {
					System.err.println("ERROR: Invalid Symbol character at Line:" + lineCount);
					System.exit(-1);
				}
				count=tokenStart;
			}
			else count++;
		}
		//if there is another nonsymbol token after this we should add it too.
		if(tokenStart!=count) {
			newTokens.add(currentLine[i].substring(tokenStart,count));
		}
		insertAt(i,newTokens.toArray(new String[newTokens.size()]));
	}
	
	private void insertAt(int i, String[] tokens) {
		String[] arr= new String[currentLine.length+tokens.length-1];
		int count=0;
		while(count<i) {
			arr[count]=currentLine[count];
			count++;
		}
		for(int j=0; j<tokens.length; j++ ) {
			arr[count]=tokens[j];
			count++;
		}
		int tokLen=tokens.length;
		while(count<arr.length) {
			arr[count]=currentLine[count-tokLen+1];
			count++;
		}
		currentLine=arr;
	}
	
	public int getToken() {
		//if(currentLine!=null)System.out.println(currentLine[currentIndex]);
		int tokenVal=-1;
		if(currentLine==null) {
			//only if EOF
			tokenVal=33;
		}
		else if(reserved.contains(currentLine[currentIndex])) {
			tokenVal=reserved.indexOf(currentLine[currentIndex])+1;
		}else if(symbols.contains(currentLine[currentIndex])) {
			tokenVal=symbols.indexOf(currentLine[currentIndex])+12;
		}
		else if(Character.isDigit(currentLine[currentIndex].charAt(0)) && isValidInt()) {
			//if the first character is a digit then its an int
			tokenVal=31;
		}
		else if(Character.isUpperCase(currentLine[currentIndex].charAt(0)) && isValidId()) {
			//if the first character is upper case then its an id
			tokenVal=32;
		}
		
		
		if (tokenVal==-1) {
			//if our token is not a valid digit print error and stop
			System.err.println("ERROR: Invalid Token \""+ currentLine[currentIndex]+"\" at Line:" + lineCount);
			System.exit(-1);
		}
		
		
		return tokenVal;
	}
	
	private boolean isValidInt(){
		boolean ret=true;
		//check length
		if(currentLine[currentIndex].length()>8) ret=false;
		int i=0;
		//check to make sure every character is a digit
		while(ret && i<currentLine[currentIndex].length()) {
			ret=Character.isDigit(currentLine[currentIndex].charAt(i));
			i++;
		}
		
		return ret;
	}
	private boolean isValidId(){
		boolean ret=true;
		//check length
		if(currentLine[currentIndex].length()>8) ret=false;
		int i=0;
		//check sequence of Uppercase
		while(ret && i<currentLine[currentIndex].length() && !Character.isDigit(currentLine[currentIndex].charAt(i))) {
			ret=Character.isUpperCase(currentLine[currentIndex].charAt(i));
			i++;
		}
		//if we reach this point and ret is still true then that means
		//that the first nonUppercase value we hit was a digit
		//so we keep searching the rest and make sure the remainder of the string is digits
		//check sequence of digits
		while(ret && i<currentLine[currentIndex].length() ) {
			ret=Character.isDigit(currentLine[currentIndex].charAt(i));
			i++;
		}
		
		return ret;
	}
	
	public void skipToken() {
		if(currentIndex+1<currentLine.length) {
			currentIndex++;
		}
		else {
			readLine();
		}
	}
	
	public int intVal() {
		if (getToken()==31) {
			return Integer.parseInt(currentLine[currentIndex]);
		}
		else {
			System.err.println("ERROR: Attempted to get intVal of a non-integer token at Line:" + lineCount);
			System.exit(-1);
			return -1;
		}
		
	}
	
	public String idName() {
		if (getToken()==32) {
			return currentLine[currentIndex];
		}
		else {
			System.err.println("ERROR: Attempted to get idName of a non-ID token at Line:" + lineCount);
			System.exit(-1);
			return "";
		}
	}
	
}

