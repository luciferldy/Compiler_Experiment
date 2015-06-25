import java.util.List;

import javax.swing.table.DefaultTableModel;

public class SemanticAnalyse{
	DefaultTableModel tbmodel_symbol_list;
	DefaultTableModel tbmodel_triples;
	String text_input;
	
	public SemanticAnalyse(String text_input, DefaultTableModel tbmodel_symbol_list, DefaultTableModel tbmodel_triples){
		this.text_input = text_input;
		this.tbmodel_triples = tbmodel_triples;
		this.tbmodel_symbol_list = tbmodel_symbol_list;
	}
	
	public void Parsing(){
		MyScanner scan=new MyScanner(text_input);
		List<Token> token_list=scan.execute();
		GrammarComplier gc=new GrammarComplier();
		gc.analysis(token_list);
		List<String> codes=gc.getCodes();
		List<Id> ids=gc.getIds();
		String output[] = new String[5];
		
		codes.add("END");
		for(int i = 0; i < ids.size(); i++){
			output[0] = "<" + (i+1) + ">";
			output[1] = ids.get(i).getName();
			String type = ids.get(i).getType();
			for(int m = 0; m < ids.get(i).arr_list.size(); m++){
				 type = type + "[" + ids.get(i).arr_list.get(m) + "]";						
			}
			output[2] = type;
			output[3] = ids.get(i).getOffset() + "";
			output[4] = ids.get(i).getLength() + "";
			// 输出符号表
            tbmodel_symbol_list.addRow(new String[]{output[1], output[2], output[4], output[3]});
		}
		for(int n = 0; n < codes.size(); n++){
			// 输出三地址指令
			tbmodel_triples.addRow(new String[]{n+"", codes.get(n)});
		}
	}


}