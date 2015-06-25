import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;


public class TextParse{
	HashMap<String, String> predictmap; 
	ArrayList<String> input_cache;
	ArrayList<String> deduce_str;
	DefaultTableModel tbmodel_lex_result;
	
	public TextParse(ArrayList<String> input_cache, DefaultTableModel tbmodel_lex_result){
		predictmap = new HashMap<String, String>();
		this.input_cache = input_cache;
		deduce_str = new ArrayList<String>();
		this.tbmodel_lex_result = tbmodel_lex_result;
		getPredictMap();
	}
	
	// 句法分析
	public void Parsing(){
		// 初始符号压入栈
		deduce_str.add("S");
		String right;
		String leftandinput;
		String process="";
		
		while (deduce_str.size()>0 && input_cache.size()>0 ) {
						
			// 输入缓冲区与推导符号串第一个字符相等的话，删掉
			try {
					if(input_cache.get(0).equals(deduce_str.get(deduce_str.size()-1))){
						input_cache.remove(0);
						deduce_str.remove(deduce_str.size()-1);
						continue;
					}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			// 匹配字符
			leftandinput = deduce_str.get(deduce_str.size()-1)+"-"+input_cache.get(0);
//			if(input_cache.get(0)==null)
//			{
//				leftandinput = leftandinput+"$";
//			}
			// 能够找到匹配的
			if((right=predictmap.get(leftandinput))!=null){
				
				// 输出产生式和推导过程
				process = "";
				for (int i=deduce_str.size()-1;i>-1;i--) {
					process = process+deduce_str.get(i)+" ";
				}
				tbmodel_lex_result.addRow(new String[]{process, deduce_str.get(deduce_str.size()-1)+" -> "+right});
				
				// 删掉产生的字符，压入堆栈
				deduce_str.remove(deduce_str.size()-1);
				if(right.equals("$")){
					// 只弹不压
				}
				else {
					String[] arg = right.split(" ");
					for(int i=arg.length-1;i>-1;i--){
						// 反向压入堆栈
						deduce_str.add(arg[i]);
					}
				}
				
			}
			// 否则的话报错
			else {
				// 重新书写process
				process="";
				for (int i=deduce_str.size()-1;i>-1;i--) {
					process = process+deduce_str.get(i)+" ";
				}
				tbmodel_lex_result.addRow(new String[]{process, "ERROR!  无法识别的字符"+input_cache.get(0)+"产生式"+leftandinput});
				input_cache.remove(0);
			}
		}
	}
	
	// 获得预测分析表中的产生式以及对应的select集
	// 存储方式为键值对的形式
	public void getPredictMap(){
		String text_line;
		String left;
		String symbol;
		String right;
		try {
			// 初始化
			predictmap = new HashMap<String, String>();
			// 采用随机读取方式
			File file = new File("predictldy.txt");
			RandomAccessFile predictfile = new RandomAccessFile(file,"r");
			while ((text_line = predictfile.readLine())!=null){
				left = text_line.split("#")[0];
				symbol = (text_line.split("#")[1]).split("->")[0].trim();
				right = (text_line.split("#")[1]).split("->")[1].trim();
				predictmap.put(left+"-"+symbol, right);
				
			}
			predictfile.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}