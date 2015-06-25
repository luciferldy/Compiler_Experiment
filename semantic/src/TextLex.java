import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;


public class TextLex{
	
	private String text;
	private DefaultTableModel tbmodel_lex_result;
//	private DefaultTableModel tbmodel_lex_error;
	private ArrayList<String> lex_result_stack;
	private ArrayList<HashMap<String, String>> lex_error_stack;
	private int text_length;
	private int row_number=1;
	String[] Key = {"void", "int", "double", "long", "char", "float", "else", "if", "return", "for", "goto", "short", "static", "do", "while"};
	
	public TextLex(String text, DefaultTableModel tb_lex_result, DefaultTableModel tb_lex_error){
		lex_result_stack = new ArrayList<String>();
		lex_error_stack = new ArrayList<HashMap<String, String>>();
		this.text = text;
		this.tbmodel_lex_result = tb_lex_result;
//		this.tbmodel_lex_error = tb_lex_error;
		text_length = text.length();
	}
	
	public ArrayList<String> get_Lex_Result(){
		return lex_result_stack;
	}
	public ArrayList<HashMap<String, String>> get_Lex_Error() {
		// TODO Auto-generated constructor stub
		return lex_error_stack;
	}
	
	public int isAlpha(char c){
		if(((c<='z')&&(c>='a')) || ((c<='Z')&&(c>='A')) || (c=='_'))
			return 1;
		else 
			return 0;
	}
	
	public int isNumber(char c){
		if((c>='0')&&(c<='9'))
			return 1;
		else 
			return 0;
	}
	
	public int isKey(String t){
		for(int i=0;i<Key.length;i++){
			if (t.equals(Key[i])) {
				return 1;
			}
		}
		// 只是普通的标识符
		return 0;
	}
	
	// 处理整个字符串
	public void scannerAll(){
		int i=0;
		char c;
		// 将字符串延长一位，防止溢出
		text = text+'\0';
		while(i<text_length){
			c = text.charAt(i);
			if(c==' '||c=='\t')
				i++;
			else if (c=='\r'||c=='\n') {
				row_number++;
				i++;
			}
			else 
				i=scannerPart(i);
		}
	}
	
	public int scannerPart(int arg0){
		int i=arg0;
		char ch = text.charAt(i);
		String s="";
		// 第一个输入的字符是字母
		if (isAlpha(ch)==1) {
			s = ""+ch;
			return handleFirstAlpha(i, s);
		}
		// 第一个是数字的话
		else if (isNumber(ch)==1) {
			s = ""+ch;
			return handleFirstNum(i, s);
			
		}
		// 既不是既不是数字也不是字母
		else {
			s = ""+ch;
			switch (ch) {
			case ' ':
			case '\n':
			case '\r':
			case '\t':
				return ++i;
			case '[':
			case ']':
			case '(':
			case ')':
			case '{':
			case '}':
				printResult(s, "双界符");
				return ++i;
			case ':':
				if(text.charAt(i+1)=='='){
					s = s+"=";
					printResult(s, "界符");
					return i+2;
				}
				else {
					printError(row_number, s, "不能识别");
					return i+1;
				}
			case ',':
			case '.':
			case ';':
				printResult(s, "单界符");
				return ++i;
			case '\\':
				if(text.charAt(i+1)=='n'||text.charAt(i+1)=='t'||text.charAt(i+1)=='r'){
					printResult(s+text.charAt(i+1), "转义");
					return i+2;
				}
			case '\'':
				// 判断是否为单字符，否则报错
				return handleChar(i, s);
			case '\"':
				// 判定字符串
				return handleString(i, s);
			case '+':
				return handlePlus(i, s);
			case '-':
				return handleMinus(i, s);
			case '*':
			case '/':
				if(text.charAt(i+1)=='*'){
					return handleNote(i, s);
				}
				else if (text.charAt(i+1)=='/') {
					return handleSingleLineNote(i,s);
				}
			case '!':
			case '=':
				ch = text.charAt(++i);
				if (ch=='=') {
					// 输出运算符
					s = s+ch;
					printResult(s, "运算符");
					return ++i;
				}
				else{
					// 输出运算符
					printResult(s, "运算符");
					return i;
				}
			case '>':
				return handleMore(i, s);
			case '<':
				return handleLess(i, s);
			case '%':
				ch = text.charAt(++i);
				if (ch=='=') {
					// 输出运算符
					s = s+ch;
					printResult(s, "运算符");
					return ++i;
				}
				else if(ch=='s'||ch=='c'||ch=='d'||ch=='f'||ch=='l'){
					// 输出类型标识符
					s = s+ch;
					printResult(s, "输出类型标识符");
					return ++i;
				}
				else {
					// 输出求余标识符
					printResult(s, "求余标识符");
					return i;
				}
			default:
				// 输出暂时无法识别的字符,制表符也被当成了有问题的字符
				printError(row_number, s, "暂时无法识别的标识符");
				return ++i;
			}
		}
	}
	
	public int handleFirstAlpha(int arg, String arg0){
		int i=arg;
		String s = arg0;
		char ch=text.charAt(++i);
		while(isAlpha(ch)==1 || isNumber(ch)==1){
			s = s+ch;
			ch=text.charAt(++i);
		}
//		if(s.length()==1){
//			printResult(s, "字符常数");
//			return i;
//		}
		// 到了结尾
		if(isKey(s)==1){
			// 输出key
			printResult(s, "关键字");
			return i;
			
		}
		else {
			// 输出普通的标识符
			printResult(s, "标识符");
			return i;
		}
	}
	
	public int handleFirstNum(int arg, String arg0){
		int i = arg;
		char ch = text.charAt(++i);
		String s = arg0;
		while(isNumber(ch)==1){
			s = s+ch;
			ch = text.charAt(++i);
		}
		if((text.charAt(i)==' ')||(text.charAt(i)=='\t')||(text.charAt(i)=='\n')||(text.charAt(i)=='\r')||(text.charAt(i)=='\0')||ch==';'||ch==','||ch==')'||ch==']'||ch=='['||ch=='('){
			// 到了结尾，输出数字
			printResult(s, "整数");
			return i;
		}
		else if (ch=='E') {
			if (text.charAt(i+1)=='+') {
				s = s+ch;
				ch = text.charAt(++i);
				s = s+ch;
				ch = text.charAt(++i);
				while (isNumber(ch)==1) {
					s = s+ch;
					ch = text.charAt(++i);
				}
				if(ch=='\r'||ch=='\n'||ch==';'||ch=='\t'){
					printResult(s, "科学计数");
					return ++i;
				}
				else {
					printError(i, s, "浮点数错误");
					return i;
				}
			}
			else if (isNumber(text.charAt(i+1))==1) {
				s = s+ch;
				ch = text.charAt(++i);
				while (isNumber(ch)==1) {
					s = s+ch;
					ch = text.charAt(++i);
				}
				if(ch=='\r'||ch=='\n'||ch==';'||ch=='\t'){
					printResult(s, "科学计数");
					return ++i;
				}
				else {
					printError(row_number, s, "浮点数错误");
					return i;
				}
			}
			else {
				printError(row_number, s, "科学计数法错误");
				return ++i;
			}
		}
		
		// 浮点数判断
		else if (text.charAt(i)=='.'&&(isNumber(text.charAt(i+1))==1)) {
			s = s +'.';
			ch = text.charAt(++i);
			while (isNumber(ch)==1) {
				s = s+ch;
				ch = text.charAt(++i);		
			}
			if (ch=='E') {
				if (text.charAt(i+1)=='+') {
					s = s+ch;
					ch = text.charAt(++i);
					s = s+ch;
					ch = text.charAt(++i);
					while (isNumber(ch)==1) {
						s = s+ch;
						ch = text.charAt(++i);
					}
					if(ch=='\r'||ch=='\n'||ch==';'||ch=='\t'){
						printResult(s, "科学计数");
						return ++i;
					}
					else {
						printError(i, s, "浮点数错误");
						return i;
					}
				}
				else if (isNumber(text.charAt(i+1))==1) {
					s = s+ch;
					ch = text.charAt(++i);
					while (isNumber(ch)==1) {
						s = s+ch;
						ch = text.charAt(++i);
					}
					if(ch=='\r'||ch=='\n'||ch==';'||ch=='\t'){
						printResult(s, "科学计数");
						return ++i;
					}
					else {
						printError(row_number, s, "浮点数错误");
						return i;
					}
				}
				else {
					printError(row_number, s, "科学计数法错误");
					return ++i;
				}
			}
			else if (ch=='\n'||ch=='\r'||ch=='\t'||ch==' '||ch=='\0'||ch!=','||ch!=';') {
				printResult(s, "浮点数");
				return i;
			}
			else if (ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='\0') {
				printResult(s, "浮点数");
				return i;
			}
			else {
				while (ch!='\n'&&ch!='\t'&&ch!=' '&&ch!='\r'&&ch!='\0'&&ch!=';'&&ch!='.'&&ch!=',') {
					s = s+ch;
					ch = text.charAt(++i);						
				}
				printError(row_number, s, "不合法的字符");
				return i;
			}
		}
		else if (ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='\0') {
			printResult(s, "整数");
			return i;
		}
		else {
			do {
				ch = text.charAt(i++);
				s = s+ch;
			} while ((text.charAt(i)!=' ')&&(text.charAt(i)!='\t')&&(text.charAt(i)!='\n')&&(text.charAt(i)!='\r')&&(text.charAt(i)!='\0'));
			printError(row_number, s, "错误的标识符");
			return i;
		}
	}
	public int handleChar(int arg, String arg0){
		String s = arg0;
		int i = arg;
		char ch = text.charAt(++i);
		while(ch!='\''){
			if (ch=='\r'||ch=='\n') {
				row_number++;
			}
		    else if(ch=='\0'){
				printError(row_number, s, "单字符错误");
				return i;
			}
			s = s+ch;
			ch = text.charAt(++i);
		}
		s = s+ch;
		System.out.println(s);
		if (s.length()==3||s.equals("\'"+"\\"+"t"+"\'")||s.equals("\'"+"\\"+"n"+"\'")||s.equals("\'"+"\\"+"r"+"\'")) {
			printResult(s, "单字符");
		}
		else 
			printError(row_number, s, "字符溢出");
		return ++i;
	}
	
	// 单行注释处理
	public int handleSingleLineNote(int arg, String arg0){
		String s = arg0;
		int i = arg;
		char ch = text.charAt(++i);
		while (ch!='\r'&&ch!='\n'&&ch!='\0') {
			s = s+ch;
			ch = text.charAt(++i);			
		}
		printResult(s, "单行注释");
		return i;
	}
	
	// 字符串处理
	public int handleString(int arg, String arg0){
		String s = arg0;
		int i=arg;
		char ch = text.charAt(++i);
		while(ch!='"'){
			if (ch=='\r'||ch=='\n') {
				row_number++;
			}
		    else if(ch=='\0'){
				printError(row_number, s, "字符串没有闭合");
				return i;
			}
			s = s+ch;
			ch = text.charAt(++i);
		}
		s = s+ch;
		printResult(s, "字符串");
		return ++i;
	}
	
	public int handlePlus(int arg, String arg0){
		int i=arg;
		char ch = text.charAt(++i);
		String s = arg0;
		if (ch=='+'){
			// 输出运算符
			s = s+ch;
			printResult(s, "运算符");
			return ++i;
		}
		
		else if(ch=='='){
			s = s+ch;
			// 输出运算符
			printResult(s, "运算符");
			return ++i;
		}	
		else{
			// 输出运算符
			printResult(s, "运算符");
			return i;
		}
	}
	
	// 处理注释,没有考虑不闭合的情况
	public int handleNote(int arg, String arg0){
		int i = arg;
		char ch=text.charAt(++i);
		String s = arg0+ch;
		ch = text.charAt(++i);
		while (ch!='*' || ((i+1)<text_length) && text.charAt(i+1)!='/') {
			s = s+ch;
			if (ch=='\r'||ch=='\n') {
				row_number++;
			}
			else if (ch=='\0') {
				printError(row_number, s, "注释没有闭合");
				return i;
			}
			ch = text.charAt(++i);
		}
		s = s+"*/";
		printResult(s, "注释");
		return i+2;
	}
	
	// 处理减号
	public int handleMinus(int arg, String arg0){
		int i=arg;
		char ch = text.charAt(++i);
		String s = arg0;
		if (ch=='-'){
			s = s+ch;
			// 输出运算符
			printResult(s, "运算符");
			return ++i;
		}
		
		else if(ch=='='){
			s = s+ch;
			// 输出运算符
			printResult(s, "运算符");
			return ++i;
		}	
		else{
			// 输出运算符
			printResult(s, "运算符");
			return i;
		}
	}
	
	public int handleMore(int arg, String arg0){
		int i=arg;
		char ch = text.charAt(++i);
		String s = arg0;
		if (ch=='='){
			s = s+ch;
			// 输出运算符
			printResult(s, "运算符");
			return ++i;
		}
		
		else if(ch=='>'){
			s = s+ch;
			// 输出运算符
			printResult(s, "运算符");
			return ++i;
		}	
		else{
			// 输出运算符
			printResult(s, "运算符");
			return i;
		}
	}
	
	public int handleLess(int arg, String arg0){
		int i=arg;
		String s = arg0;
		char ch = text.charAt(++i);
		if (ch=='='){
			s = s+ch;
			// 输出运算符
			printResult(s, "运算符");
			return ++i;
		}
		
		else if(ch=='<'){
			s = s+ch;
			// 输出运算符
			printResult(s, "运算符");
			return ++i;
		}	
		else{
			// 输出运算符
			printResult(s, "运算符");
			return i;
		}
	}
	
	// 打印结果
	public void printResult(String rs_value, String rs_name){
//		tbmodel_lex_result.addRow(new String[]{rs_value, rs_name});
		if(rs_name.equals("标识符")){
			lex_result_stack.add("IDN");
			tbmodel_lex_result.addRow(new String[]{"IDN", rs_value});
		}
		else if(rs_name.equals("整数")){
			lex_result_stack.add("INT10");
			tbmodel_lex_result.addRow(new String[]{"INT10", rs_value});
		}
		else if (rs_name.equals("科学计数")||rs_name.equals("浮点数")) {
			lex_result_stack.add("FLOAT");
			tbmodel_lex_result.addRow(new String[]{"FLOAT", rs_value});
		}
		else if(rs_name.equals("单字符")){
			lex_result_stack.add("CHAR");
			tbmodel_lex_result.addRow(new String[]{"CHAR", rs_value});
		}
		else if(rs_name.equals("字符串")){
			lex_result_stack.add("STR");
			tbmodel_lex_result.addRow(new String[]{"STR", rs_value});
		}
		else {
			lex_result_stack.add(rs_value);
			tbmodel_lex_result.addRow(new String[]{rs_name, rs_value});
		}
			
	}
	
	// 打印错误信息
	public void printError(int row_num, String rs_value, String rs_name) {
//		tbmodel_lex_error.addRow(new String[]{row_num+"", rs_value, rs_name});
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("row_num", row_num+"");
		hashMap.put("rs_value", rs_value+"");
		hashMap.put("rs_name", rs_name+"");
		lex_error_stack.add(hashMap);
		tbmodel_lex_result.addRow(new String[]{"ERROR，"+rs_name, rs_value});
	}
	
}