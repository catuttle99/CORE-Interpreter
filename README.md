# CORE-Interpreter

# Compilation and Running
  Run from Part2Main.java with two command line arguments, the first one being the CORE program to be interpreted as a .txt, and the second being 
  input data, one integer per line. There are example input files in this repository, Prog1.txt and Data1.txt.
  
# CORE Language
  This program is an Interpreter for the fictional CORE language. The Grammar for the language follows:
  
  < prog >::= program < decl seq > begin < stmt seq > end 
  
  < decl seq >::= < decl > | < decl > < decl seq >
  
  < stmt seq >::= < stmt > | < stmt > < stmt seq >
  
  < decl >::= int < id list >;
  
  < id list >::= < id > | < id > , < id list >
  
  < stmt >::= < assign > | < if > | < loop > | < in > | < out >
  
  < assign >::= < id > = < exp >;
  
  < if >::= if < cond > then < stmt seq > end; | if < cond > then < stmt seq > else < stmt seq > end;
  
  < loop >::= while < cond > loop < stmt seq > end;
  
  < in >::= read < id list >;
   
  < out >::= write < id list >;
  
  < cond >::= < comp > | !< cond > | ( < cond > && < cond > ) | ( < cond > or < cond > )
  
  < comp >::= ( < op > < comp op > < op > )
  
  < exp >::= < fac > | < fac > + < exp > | < fac > - < exp >
  
  < fac >::= < op > | < op > * < fac >
  
  < op >::= < int > | < id > | ( < exp > )
  
  < comp op >::= != | == | < | > | <= | >=
  
  < id >::= < let > | < let > < id > | < let > < int >
  
  < let >::= A | B | C | ... | Z 
  
  < int >::= < digit > | < digit > < int >
  
  < digit >::= 0 | 1 | 2 | ... | 9
  
  
  
# Design Pattern
  This Interpreter uses the Object Oriented approach, where each Non-Terminal in the grammar is it's own class, that has a parser, executor, and printer method.
  The parse tree was created via the Recursive Descent Parsing approach, and then the tree was used by executor and printer. 
  
# Tokenizer 
  My original Tokenizer had a few minor errors, so I was given an example Tokenizer to use here that was written by another individual 
  
# Expected Behavior 
  Once running, the Tokenizer will generate the tokens from the program and then using Recrusive Descent Parsing, the parse tree will be created and the program will be executed. The program will output a pretty printed version of the CORE program input and then the corresponding write calls that are executed by the CORE program input

