public class Out {
	Id_List id_list;

	public Out() {
		id_list=null;
	}
	
	public void parseOut() {
		Part2Main.tokenizer.skipToken(); //skip Write token
		id_list=new Id_List();
		id_list.parseId_List();
	}
	
	public void printOut() {
		System.out.print("write");
		id_list.printId_List();
	}
	
	public void execOut() {
		id_list.execWriteId_List();
	}
}
