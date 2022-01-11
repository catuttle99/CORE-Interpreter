# CORE-Interpreter

* * Tokenizer was written by a different student *

# Compilation and Running
  Run from Part2Main.java with two command line arguments, the first one being the CORE program to be interpreted as a .txt, and the second being 
  input data, one integer per line. There are example input files in this repository, Prog1.txt and Data1.txt.
  
# CORE Language
  This program is an Interpreter for the fictional CORE language. The Grammar for the language follows:
  
  < prog >::= program < decl seq > begin < stmt seq > end 
  < decl seq >::= < decl > | < decl > < decl seq >
  
  
  
# Design Pattern
  This Interpreter uses the Object Oriented approach, where each Non-Terminal in the grammar is it's own class, that has a parser, executor, and printer method.
  The parsing was done via the Recursive Descent Parsing approach. 
  
# Tokenizer 
  
  
# Expected Behavior 

