
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class GrammarComplier {
	List<Production> productions;
	List<Symbol> symbols;
	List<Id> ids;//标识符表
	List<String> codes; //三地址码

	public List<Id> getIds() {
		return ids;
	}

	public List<String> getCodes() {
		return codes;
	}

	public GrammarComplier() {
		
		ids=new ArrayList<Id>();
		codes=new ArrayList<String>();
		symbols=new ArrayList<Symbol>();
		productions = new ArrayList<Production>();
		CreateNewProduction();
		getFirst();
		getFollow();
		getSelect();	
	}

	// 创造新的产生式
	public void CreateNewProduction(){
		//TODO:改造文法
		productions.add(new Production(0, "S", "func", "S"));
		productions.add(new Production(1, "S"));
		productions.add(new Production(2, "func", "type", "id", "(", "params", ")", "func_body"));
		productions.add(new Production(3, "func", "VOID", "id", "(", "params", ")", "func_body"));
		productions.add(new Production(4, "params"));
		productions.add(new Production(5, "params", "type", "id", "params\'"));
		productions.add(new Production(6, "params\'"));
		productions.add(new Production(7, "params\'", ",", "type", "id", "params\'"));
		productions.add(new Production(8, "func_body", ";"));
		productions.add(new Production(9, "func_body", "block"));
		productions.add(new Production(10, "block", "{", "def_stmts", "stmts", "}"));
		productions.add(new Production(11, "def_stmts", "def_stmt", "def_stmts"));
		productions.add(new Production(12, "def_stmts"));
		productions.add(new Production(13, "def_stmt", "type", "id", "M13_2", "array", "M13_4", "vars", ";"));	
			productions.add(new Production(13, "M13_2"));
			symbols.add(new Symbol(-1, "M13_2", 'N'));
			productions.add(new Production(13, "M13_4"));
			symbols.add(new Symbol(-1, "M13_4", 'N'));
		productions.add(new Production(14, "array", "M14_1"));
			productions.add(new Production(14, "M14_1"));
			symbols.add(new Symbol(-1, "M14_1", 'N'));
		productions.add(new Production(15, "array", "[", "int", "]", "M15_4", "array", "M15_6"));
			productions.add(new Production(15, "M15_4"));
			symbols.add(new Symbol(-1, "M15_4", 'N'));
			productions.add(new Production(15, "M15_6"));
			symbols.add(new Symbol(-1, "M15_6", 'N'));
		productions.add(new Production(16, "vars"));
		productions.add(new Production(17, "vars", ",", "id", "M17_3", "array", "M17_5", "vars"));
			productions.add(new Production(17, "M17_3"));
			symbols.add(new Symbol(-1, "M17_3", 'N'));
			productions.add(new Production(17, "M17_5"));
			symbols.add(new Symbol(-1, "M17_5", 'N'));
		productions.add(new Production(18, "stmts"));
		productions.add(new Production(19, "stmts", "stmt", "stmts"));
		productions.add(new Production(20, "stmt", "E", ";"));
		productions.add(new Production(21, "stmt", ";"));
		productions.add(new Production(22, "stmt", "block"));
		productions.add(new Production(23, "stmt", "RETURN", "e", ";"));
		productions.add(new Production(24, "stmt", "CONTINUE", ";"));
		productions.add(new Production(25, "stmt", "BREAK", ";"));
		productions.add(new Production(26, "stmt", "IF", "(", "E", ")", "M26_5", "stmt", "M26_7", "else", "M26_9"));
			productions.add(new Production(26, "M26_5"));
			symbols.add(new Symbol(-1, "M26_5", 'N'));
			productions.add(new Production(26, "M26_7"));
			symbols.add(new Symbol(-1, "M26_7", 'N'));
			productions.add(new Production(26, "M26_9"));
			symbols.add(new Symbol(-1, "M26_9", 'N'));
		productions.add(new Production(27, "stmt", "SWITCH", "(", "E", ")", "{", "cases", "}"));
		productions.add(new Production(28, "stmt", "DO", "stmt", "WHILE", "(", "E", ")", ";"));
		productions.add(new Production(30, "else", "ELSE", "stmt"));
		productions.add(new Production(29, "else"));
		productions.add(new Production(31, "cases", "case", "cases"));
		productions.add(new Production(32, "cases"));
		productions.add(new Production(33, "case", "CASE", "const", ":", "stmts"));
		productions.add(new Production(34, "case", "DEFAULT", ":", "stmts"));
		productions.add(new Production(35, "e"));
		productions.add(new Production(36, "e", "E"));
		productions.add(new Production(37, "stmt", "FOR", "(", "e", ";", "e", ";", "e", ")", "stmt"));
		productions.add(new Production(38, "stmt", "WHILE", "(", "M38_3", "E", ")", "M26_5", "stmt", "M38_7"));
			productions.add(new Production(38, "M38_3"));
			symbols.add(new Symbol(-1, "M38_3", 'N'));
			productions.add(new Production(38, "M38_7"));
			symbols.add(new Symbol(-1, "M38_7", 'N'));
		productions.add(new Production(39, "factor", "+", "factor"));
		productions.add(new Production(40, "E", "value", "M52_2", "comp", "M52_4"));
		productions.add(new Production(41, "comp"));
		productions.add(new Production(42, "comp", "<", "value", "M42_4"));
			productions.add(new Production(42, "M42_4"));
			symbols.add(new Symbol(-1, "M42_4", 'N'));
		productions.add(new Production(43, "comp", "<=", "value", "M43_4"));
			productions.add(new Production(43, "M43_4"));
			symbols.add(new Symbol(-1, "M43_4", 'N'));
		productions.add(new Production(44, "comp", ">", "value", "M44_4"));
			productions.add(new Production(44, "M44_4"));
			symbols.add(new Symbol(-1, "M44_4", 'N'));
		productions.add(new Production(45, "comp", ">=", "value", "M45_4"));
			productions.add(new Production(45, "M45_4"));
			symbols.add(new Symbol(-1, "M45_4", 'N'));
		productions.add(new Production(46, "comp", "==", "value", "M46_4"));
			productions.add(new Production(46, "M46_4"));
			symbols.add(new Symbol(-1, "M46_4", 'N'));
		productions.add(new Production(47, "comp", "!=", "value", "M47_4"));
			productions.add(new Production(47, "M47_4"));
			symbols.add(new Symbol(-1, "M47_4", 'N'));
		productions.add(new Production(48, "value", "item", "M52_2", "items", "M52_4"));
		productions.add(new Production(49, "items"));
		productions.add(new Production(50, "items", "+", "item", "M50_3", "items", "M52_4"));
			productions.add(new Production(50, "M50_3"));
			symbols.add(new Symbol(-1, "M50_3", 'N'));
		productions.add(new Production(51, "items", "-", "item", "M51_3", "items", "M52_4"));
			productions.add(new Production(51, "M51_3"));
			symbols.add(new Symbol(-1, "M51_3", 'N'));
		productions.add(new Production(52, "item", "factor", "M52_2", "factors", "M52_4"));
			productions.add(new Production(52, "M52_2"));
			symbols.add(new Symbol(-1, "M52_2", 'N'));
			productions.add(new Production(52, "M52_4"));
			symbols.add(new Symbol(-1, "M52_4", 'N'));
		productions.add(new Production(53, "factors"));
		productions.add(new Production(54, "factors", "*", "factor", "M54_3", "factors", "M52_4"));
			productions.add(new Production(54, "M54_3"));
			symbols.add(new Symbol(-1, "M54_3", 'N'));
		productions.add(new Production(55, "factors", "/", "factor", "M55_3", "factors", "M52_4"));
			productions.add(new Production(55, "M55_3"));
			symbols.add(new Symbol(-1, "M55_3", 'N'));
		productions.add(new Production(56, "factors", "%", "factor", "M56_3", "factors", "M52_4"));
			productions.add(new Production(56, "M56_3"));
			symbols.add(new Symbol(-1, "M56_3", 'N'));
		productions.add(new Production(57, "factor", "!", "factor", "M57_3"));
			productions.add(new Production(58, "M57_3"));
			symbols.add(new Symbol(-1, "M57_3", 'N'));
		productions.add(new Production(58, "factor", "++", "factor", "M58_3"));
			productions.add(new Production(58, "M58_3"));
			symbols.add(new Symbol(-1, "M58_3", 'N'));
		productions.add(new Production(59, "factor", "--", "factor", "M59_3"));
			productions.add(new Production(59, "M59_3"));
			symbols.add(new Symbol(-1, "M59_3", 'N'));
		productions.add(new Production(60, "factor", "(", "E", ")", "M60_4"));
			productions.add(new Production(60, "M60_4"));
			symbols.add(new Symbol(-1, "M60_4", 'N'));
		productions.add(new Production(61, "factor", "id", "M61_2", "call", "M61_4"));
			productions.add(new Production(61, "M61_2"));
			symbols.add(new Symbol(-1, "M61_2", 'N'));
			productions.add(new Production(61, "M61_4"));
			symbols.add(new Symbol(-1, "M61_4", 'N'));
		productions.add(new Production(62, "factor", "const", "M62_2"));
			productions.add(new Production(62, "M62_2"));
			symbols.add(new Symbol(-1, "M62_2", 'N'));
		productions.add(new Production(63, "call", "M63_1", "array", "M63_3"));
			productions.add(new Production(63, "M63_1"));
			symbols.add(new Symbol(-1, "M63_1", 'N'));
			productions.add(new Production(63, "M63_3"));
			symbols.add(new Symbol(-1, "M63_3", 'N'));
		productions.add(new Production(64, "call", "(", "Es", ")"));
		productions.add(new Production(65, "Es", "E", "Es\'"));
		productions.add(new Production(66, "Es\'", ",", "E", "Es\'"));
		productions.add(new Production(67, "Es\'"));
		productions.add(new Production(68, "type", "CHAR","M68_2"));	
			productions.add(new Production(68, "M68_2"));
			symbols.add(new Symbol(-1, "M68_2", 'N'));
		productions.add(new Production(69, "type", "INT", "M69_2"));
			productions.add(new Production(69, "M69_2"));
			symbols.add(new Symbol(-1, "M69_2", 'N'));
		productions.add(new Production(70, "type", "LONG", "M70_2"));
			productions.add(new Production(70, "M70_2"));
			symbols.add(new Symbol(-1, "M70_2", 'N'));
		productions.add(new Production(71, "type", "SHORT", "M71_2"));
			productions.add(new Production(71, "M71_2"));
			symbols.add(new Symbol(-1, "M71_2", 'N'));
		productions.add(new Production(72, "type", "FLOAT", "M72_2"));
			productions.add(new Production(72, "M72_2"));
			symbols.add(new Symbol(-1, "M72_2", 'N'));
		productions.add(new Production(73, "type", "DOUBLE", "M73_2"));
			productions.add(new Production(73, "M73_2"));
			symbols.add(new Symbol(-1, "M73_2", 'N'));
		productions.add(new Production(74, "const", "int", "M74_2"));
		productions.add(new Production(75, "const", "float", "M74_2"));
		productions.add(new Production(76, "const", "double", "M74_2"));
		productions.add(new Production(77, "const", "char", "M74_2"));
			productions.add(new Production(74, "M74_2"));
			symbols.add(new Symbol(-1, "M74_2", 'N'));
		productions.add(new Production(78, "comp", "=", "value", "M78_3"));
			productions.add(new Production(78, "M78_3"));
			symbols.add(new Symbol(-1, "M78_3", 'N'));
		productions.add(new Production(79, "comp", "+=", "value", "M79_3"));
			productions.add(new Production(79, "M79_3"));
			symbols.add(new Symbol(-1, "M79_3", 'N'));
		productions.add(new Production(80, "comp", "-=", "value", "M80_3"));
			productions.add(new Production(80, "M80_3"));
			symbols.add(new Symbol(-1, "M80_3", 'N'));
		productions.add(new Production(81, "comp", "*=", "value", "M81_3"));
			productions.add(new Production(81, "M81_3"));
			symbols.add(new Symbol(-1, "M81_3", 'N'));
		productions.add(new Production(82, "comp", "/=", "value", "M82_3"));
			productions.add(new Production(82, "M82_3"));
			symbols.add(new Symbol(-1, "M82_3", 'N'));
		productions.add(new Production(83, "comp", "%=", "value", "M83_3"));
			productions.add(new Production(83, "M83_3"));
			symbols.add(new Symbol(-1, "M83_3", 'N'));
		productions.add(new Production(84, "factor", "-", "factor", "M84_3"));
			productions.add(new Production(84, "M84_3"));
			symbols.add(new Symbol(-1, "M84_3", 'N'));
		productions.add(new Production(85, "const", "string"));
		productions.add(new Production(86, "params", "VOID"));
		
		symbols.add(new Symbol(0,"S",'N'));		
		symbols.add(new Symbol(1,"func",'N'));
		symbols.add(new Symbol(2,"params",'N'));
		symbols.add(new Symbol(3,"params\'",'N'));
		symbols.add(new Symbol(4,"func_body",'N'));
		symbols.add(new Symbol(5,"block",'N'));
		symbols.add(new Symbol(6,"def_stmts",'N'));
		symbols.add(new Symbol(7,"def_stmt",'N'));
		symbols.add(new Symbol(8,"array",'N'));
		symbols.add(new Symbol(9,"vars",'N'));
		symbols.add(new Symbol(10,"stmts",'N'));
		symbols.add(new Symbol(11,"stmt",'N'));
		symbols.add(new Symbol(12,"else",'N'));
		symbols.add(new Symbol(13,"cases",'N'));
		symbols.add(new Symbol(14,"case",'N'));
		symbols.add(new Symbol(15,"e",'N'));
		symbols.add(new Symbol(16,"E",'N'));
		symbols.add(new Symbol(17,"comp",'N'));
		symbols.add(new Symbol(18,"value",'N'));
		symbols.add(new Symbol(19,"items",'N'));
		symbols.add(new Symbol(20,"item",'N'));
		symbols.add(new Symbol(21,"factors",'N'));
		symbols.add(new Symbol(22,"factor",'N'));
		symbols.add(new Symbol(23,"call",'N'));
		symbols.add(new Symbol(24,"Es",'N'));
		symbols.add(new Symbol(25,"Es\'",'N'));
		symbols.add(new Symbol(26,"type",'N'));
		symbols.add(new Symbol(27,"const",'N'));
		symbols.add(new Symbol(28,"string",'T'));
		symbols.add(new Symbol(29,"id",'T'));
		symbols.add(new Symbol(30,"(",'T'));
		symbols.add(new Symbol(31,")",'T'));
		symbols.add(new Symbol(32,"VOID",'T'));
		symbols.add(new Symbol(33,"{",'T'));
		symbols.add(new Symbol(34,"}",'T'));
		symbols.add(new Symbol(35,",",'T'));
		symbols.add(new Symbol(36,";",'T'));
		symbols.add(new Symbol(37,"=",'T'));
		symbols.add(new Symbol(38,"IF",'T'));
		symbols.add(new Symbol(39,"SWITCH",'T'));
		symbols.add(new Symbol(40,"DO",'T'));
		symbols.add(new Symbol(41,"WHILE",'T'));
		symbols.add(new Symbol(42,"ELSE",'T'));
		symbols.add(new Symbol(43,"CASE",'T'));
		symbols.add(new Symbol(44,":",'T'));
		symbols.add(new Symbol(45,"DEFAULT",'T'));
		symbols.add(new Symbol(46,"FOR",'T'));
		symbols.add(new Symbol(47,"<",'T'));
		symbols.add(new Symbol(48,"<=",'T'));
		symbols.add(new Symbol(49,">",'T'));
		symbols.add(new Symbol(50,">=",'T'));
		symbols.add(new Symbol(51,"==",'T'));
		symbols.add(new Symbol(52,"!=",'T'));
		symbols.add(new Symbol(53,"+",'T'));
		symbols.add(new Symbol(54,"-",'T'));
		symbols.add(new Symbol(55,"*",'T'));
		symbols.add(new Symbol(56,"/",'T'));
		symbols.add(new Symbol(57,"%",'T'));
		symbols.add(new Symbol(58,"!",'T'));
		symbols.add(new Symbol(59,"++",'T'));
		symbols.add(new Symbol(60,"--",'T'));
		symbols.add(new Symbol(61,"CHAR",'T'));
		symbols.add(new Symbol(62,"INT",'T'));
		symbols.add(new Symbol(63,"LONG",'T'));
		symbols.add(new Symbol(64,"SHORT",'T'));
		symbols.add(new Symbol(65,"FLOAT",'T'));
		symbols.add(new Symbol(66,"DOUBLE",'T'));
		symbols.add(new Symbol(67,"int",'T'));
		symbols.add(new Symbol(68,"float",'T'));
		symbols.add(new Symbol(69,"double",'T'));
		symbols.add(new Symbol(70,"char",'T'));
		symbols.add(new Symbol(71,"+=",'T'));
		symbols.add(new Symbol(72,"-=",'T'));
		symbols.add(new Symbol(73,"*=",'T'));
		symbols.add(new Symbol(74,"/=",'T'));
		symbols.add(new Symbol(75,"%=",'T'));
		symbols.add(new Symbol(76,"RETURN",'T'));
		symbols.add(new Symbol(77,"CONTINUE",'T'));
		symbols.add(new Symbol(78,"BREAK",'T'));
		symbols.add(new Symbol(79,"[",'T'));
		symbols.add(new Symbol(80,"]",'T'));
		symbols.add(new Symbol(81,"#",'T'));//结束符
	}
	
	// 获取first集
	public void getFirst(){
		//求first集（所有符号） 
				boolean flag=true;
				
				while(flag){
					
					for(int i=0;i<productions.size();i++){
						Production temp=productions.get(i);
						String left=temp.getLeft();
						String[] right=temp.getRight();
						Symbol left_symbol=getSymbol(left);
						
						for(int j=0;j<right.length;j++){
							Symbol right_symbol=getSymbol(right[j]);			
							for(int k=0;k<right_symbol.first.size();k++){
								String element=right_symbol.first.get(k);
								
								if(!left_symbol.has("first", element)){
									left_symbol.first.add(element);
									flag=false;//first集发生改变
								}
							}
							
							//该符号不能推出空，则跳出循环
							if(!canBeBlank(right[j]))
								break;
						}
					}
					
					flag=!flag;//first集发生改变要重新循环，没发生改变则跳出
				}
	}
	
	// 获取follow集
	public void getFollow(){
		//求follow集（所有非终结符）
		boolean flag;
		getSymbol("S").follow.add("#");
		
		flag=true;
		while(flag){
			
			for(int i=0;i<productions.size();i++){
				Production temp=productions.get(i);
				String left=temp.getLeft();
				String[] right=temp.getRight();
				
				if(right.length==0)
					continue;
				
				Symbol left_symbol=getSymbol(left);
				
				for(int j=0;j<right.length-1;j++){
					Symbol right_symbol=getSymbol(right[j]);
					
					if(right_symbol.isTerminal())
						continue;
					
					Symbol follow_symbol=getSymbol(right[j+1]);					
					for(int k=0;k<follow_symbol.first.size();k++){
						String element=follow_symbol.first.get(k);
						
						if(!right_symbol.has("follow", element)){
							right_symbol.follow.add(element);
							flag=false;//follow集发生改变
						}
					}
					
					//right右边的串如果能推出空，则将left_symbol.follow并入right_symbol.follow
					boolean blank=true;
					for(int k=j+1;k<right.length;k++){
						if(canBeBlank(right[k])){
							if(k+1<right.length){
								Symbol rr_symbol=getSymbol(right[k+1]);
//										System.out.println(rr_symbol.getName()+right_symbol.getName());
								for(int m=0;m<rr_symbol.first.size();m++){
									String element=rr_symbol.first.get(m);
									
									if(!right_symbol.has("follow", element)){
										right_symbol.follow.add(element);
										flag=false;//follow集发生改变
									}
								}
							}
						}else{
							blank=false;
							break;
						}
					}
					
					if(blank){
						for(int k=0;k<left_symbol.follow.size();k++){
							String element=left_symbol.follow.get(k);
							
							if(!right_symbol.has("follow", element)){
								right_symbol.follow.add(element);
								flag=false;//follow集发生改变
							}
						}
					}
				}
				
				//对于每个产生式，左部符号的follow集要并到右部最后一个符号(如果是非终结符)的follow集中
				Symbol last_symbol=getSymbol(right[right.length-1]);
				if(last_symbol.isTerminal())
					continue;
				
				for(int k=0;k<left_symbol.follow.size();k++){
					String element=left_symbol.follow.get(k);
					
					if(!last_symbol.has("follow", element)){
						last_symbol.follow.add(element);
						flag=false;//follow集发生改变
					}
				}
			}
			
			flag=!flag;//follow集发生改变要重新循环，没发生改变则跳出
		}
				
	}
	
	// 获取select集
	public void getSelect(){
		//求select集（所有产生式）
		for(int i=0;i<productions.size();i++){
			Production production=productions.get(i);
			
			if(production.getRight().length==0){
				List<String> select=new ArrayList<String>();
				
				List<String> follow=getSymbol(production.getLeft()).follow;
				
				for(int j=0;j<follow.size();j++){
					select.add(follow.get(j));
				}
				
				production.setSelect(select);
			}else{
				List<String> select=new ArrayList<String>();
				String[] right=production.getRight();
				
				boolean blank=true; //产生式能推出空
				for(int j=0;j<right.length;j++){
					List<String> first=getSymbol(right[j]).first;
					
					for(int k=0;k<first.size();k++){
						select.add(first.get(k));
					}
					
					if(!canBeBlank(right[j])){
						blank=false;
						break;
					}
				}
				
				if(blank){
					List<String> follow=getSymbol(production.getLeft()).follow;
					
					for(int k=0;k<follow.size();k++){
						if(!select.contains(follow.get(k)))
							select.add(follow.get(k));
					}
				}
				
				production.setSelect(select);
			}
		}
	}
	
	public List<Production> getProductions() {
		return productions;
	}
	
	public List<Production> getProductionsByLeft(String left){
		List<Production> ret=new ArrayList<Production>();
		
		for(int i=0;i<this.productions.size();i++){
			Production temp=this.productions.get(i);
			if(temp.getLeft().equals(left))
				ret.add(temp);
		}
		
		return ret;
	}
	
	private boolean canBeBlank(String name){
		
		for(int i=0;i<this.productions.size();i++){
			Production temp=this.productions.get(i);
			if(temp.getLeft().equals(name)){
				String[] right=temp.getRight();
				
				if(right.length==0)
					return true;
				
				boolean flag=true;
				for(int j=0;j<right.length;j++){
					if(!canBeBlank(right[j])){
						flag=false;
						break;
					}
				}
				
				if(flag)
					return true;
			}
		}
		
		return false;
	}
	
	// 与boolean canBeBlank(String name)方法的实现很接近
	// 返回能使name推出空的一连串产生式的第一个
	// 如果name不能推出空，返回null
	// @SuppressWarnings("unused")
	private Production getProductionToBlank(String name){
		for(int i=0;i<this.productions.size();i++){
			Production temp=this.productions.get(i);
			if(temp.getLeft().equals(name)){
				String[] right=temp.getRight();
				
				if(right.length==0)
					return temp;
				
				boolean flag=true;
				for(int j=0;j<right.length;j++){
					if(!canBeBlank(right[j])){
						flag=false;
						break;
					}
				}
				
				if(flag)
					return temp;
			}
		}
		
		return null;
	}
	
	public List<Symbol> getSymbols() {
		return symbols;
	}

	Symbol getSymbol(String name){
		for(int i=0;i<symbols.size();i++){
			Symbol temp=symbols.get(i);
			if(temp.getName().equals(name))
				return temp;
		}
		
		return null;//不在符号表中的符号
	}
	
	private Id getId(String name){
		for(int i=0;i<ids.size();i++){
			if(ids.get(i).getName().equals(name))
				return ids.get(i);
		}
			
		return null;
	}
	
	// 分析，标识符在内存中的偏移
	public List<Production> analysis(List<Token> token_list){
		
		int offset=0;//标识符在内存中的偏移
		int tno=0;//中间变量的个数
		int bno=0;//布尔变量的个数
		
		token_list.add(new Token("#",null));
		
		Stack<Symbol> stack=new Stack<Symbol>();//符号栈
		Stack<Node> node_stack=new Stack<Node>();
		List<Production> pro_list=new ArrayList<Production>();//产生式顺序
		
		stack.push(getSymbol("#"));//栈底
		stack.push(getSymbol("S"));
		
		node_stack.push(new Node("S",null));
		
		int pos=0;//已匹配数目
		int line=1;//行号
		
		while(pos<token_list.size()){
			Token token=token_list.get(pos);
			
			Symbol input_symbol=getSymbol(token.getName());
			
			if(input_symbol==null){//该文法不能识别的输入符号
				if(token.getName().equals("ENTER")){
					line++;
				}else if(token.getName().equals("ERROR")){
					ErrorProduction err_pro=new ErrorProduction(-1,stack.get(stack.size()-1).getName(),stack.get(stack.size()-1).getName());
					err_pro.setError("无法识别的单词\'"+token.getSource()+"\' at line "+line);
					err_pro.setSolution("跳过错误单词\'"+token.getSource()+"\'");
					pro_list.add(err_pro);
				}else{
					ErrorProduction err_pro=new ErrorProduction(-1,stack.get(stack.size()-1).getName(),stack.get(stack.size()-1).getName());
					err_pro.setError("无法识别的输入符号\'"+token.getName()+"\' at line "+line);
					err_pro.setSolution("跳过输入符号\'"+token.getName()+"\'");
					pro_list.add(err_pro);
				}
				
				
				pos++;//跳过该输入符号
				continue;
			}
			
			Symbol leftest=null;
			Node left_node=null;
			try{
				leftest=stack.pop();
				
				if(!leftest.isTerminal())
					left_node=node_stack.pop();
				
			}catch(EmptyStackException e){//符号栈已空，输入未结束
				ErrorProduction err_pro=new ErrorProduction(-1,"#","#");
				err_pro.setError("符号栈已空，输入栈仍然有字符存在");
				err_pro.setSolution("句法分析终止");
				
				pro_list.add(err_pro);
				
				break;
			}
			
			//TODO：当leftest是一个代表语义分析程序段的文法符号时，left_node.getFather()就代表它所在产生式的左部结点
			if(leftest.getName().equals("M68_2")){
				left_node.getFather().attribute.put("type", "char");
				left_node.getFather().attribute.put("length", "1");
			}else if(leftest.getName().equals("M69_2")){
				left_node.getFather().attribute.put("type", "int");
				left_node.getFather().attribute.put("length", "4");
			}else if(leftest.getName().equals("M70_2")){
				left_node.getFather().attribute.put("type", "long");
				left_node.getFather().attribute.put("length", "4");
			}else if(leftest.getName().equals("M71_2")){
				left_node.getFather().attribute.put("type", "short");
				left_node.getFather().attribute.put("length", "2");
			}else if(leftest.getName().equals("M72_2")){
				left_node.getFather().attribute.put("type", "float");
				left_node.getFather().attribute.put("length", "4");
			}else if(leftest.getName().equals("M73_2")){
				left_node.getFather().attribute.put("type", "double");
				left_node.getFather().attribute.put("length", "8");
			}else if(leftest.getName().equals("M13_2")){
				
				Node father=left_node.getFather();
				
				father.sons.get(1).attribute.put("name", token_list.get(pos-1).getSource());
				father.sons.get(1).attribute.put("type", father.sons.get(0).attribute.get("type"));
				father.sons.get(1).attribute.put("length", father.sons.get(0).attribute.get("length"));
				father.sons.get(1).attribute.put("dimension", "0");
			}else if(leftest.getName().equals("M15_4")){
				
				Node father=left_node.getFather();
				int num=Integer.parseInt(token_list.get(pos-2).getValue());
				int father_dimension=Integer.parseInt(father.attribute.get("dimension"));
				
				father.sons.get(0).attribute.put("name", father.attribute.get("name"));
				father.sons.get(0).attribute.put("type", father.attribute.get("type"));
				
				if(father.attribute.get("length")!=null)
					father.sons.get(0).attribute.put("length", Integer.parseInt(father.attribute.get("length")) * num + "");
				
				father.sons.get(0).attribute.put("dimension", (father_dimension + 1) + "");
				father.sons.get(0).attribute.put("arr"+ father_dimension, ""+num);
				
				for(int i=0;i<father_dimension;i++){
					father.sons.get(0).attribute.put("arr"+ i, ""+father.attribute.get("arr"+i));
				}
				
			}else if(leftest.getName().equals("M14_1")){
				
				Node father=left_node.getFather();
				
				if(father.attribute.get("length")!=null){//定义语句中的数组
					int length=Integer.parseInt(father.attribute.get("length"));
					Id id=new Id(father.attribute.get("name"),father.attribute.get("type"), offset, length);
					
					offset+=length;
					
					int dimension=Integer.parseInt(father.attribute.get("dimension"));
					
					for(int i=0;i<dimension;i++){
						id.arr_list.add(Integer.parseInt(father.attribute.get("arr"+i)));
					}
					
					ids.add(id);
				}else{//执行语句中的数组
					String name=father.attribute.get("name");
					Id id=getId(name);
					String type=id.getType();
					int dimension=id.arr_list.size();
					
					int ofst=0;
					int width=1;
					
					for(int i=dimension-1;i>=0;i--){
						int arr=Integer.parseInt(father.attribute.get("arr"+i));
						ofst+=arr*width;
						width*=id.arr_list.get(i);
					}
					
					if(type.equals("int")||type.equals("long")||type.equals("float"))
						ofst*=4;
					else if(type.equals("double"))
						ofst*=8;
					else if(type.equals("short"))
						ofst*=2;	
				
					if(id.arr_list.size()>0){
						String t="t"+(tno++);
						codes.add(t+" := "+name+"["+ofst+"]");
						father.attribute.put("value", t);
						father.attribute.put("val", name+"["+ofst+"]");
					}else{
						father.attribute.put("value", name);
					}
					
				}
				
			}else if(leftest.getName().equals("M13_4")){
				
				Node father=left_node.getFather();
				
				father.sons.get(2).attribute.put("type", father.sons.get(0).attribute.get("type"));
				father.sons.get(2).attribute.put("length", father.sons.get(0).attribute.get("length"));
			}else if(leftest.getName().equals("M17_3")){
				
				Node father=left_node.getFather();
				
				father.sons.get(0).attribute.put("type", father.attribute.get("type"));
				father.sons.get(0).attribute.put("length", father.attribute.get("length"));
				father.sons.get(0).attribute.put("name", token_list.get(pos-1).getSource());
				father.sons.get(0).attribute.put("dimension", "0");
			}else if(leftest.getName().equals("M17_5")){
				
				Node father=left_node.getFather();
				
				father.sons.get(1).attribute.put("type", father.sons.get(0).attribute.get("type"));
				father.sons.get(1).attribute.put("length", father.sons.get(0).attribute.get("length"));
			}else if(leftest.getName().equals("M74_2")){
				
				Node father=left_node.getFather();
				
				father.attribute.put("value", token_list.get(pos-1).getValue());
			}else if(leftest.getName().equals("M57_3")){
				
				Node father=left_node.getFather();
				
				String f1="b"+(bno++);
				String f2=father.sons.get(0).attribute.get("value");
				
				codes.add(f1+" := ~"+f2);
				
				father.attribute.put("value", f1);
			}else if(leftest.getName().equals("M58_3")){
				
				Node father=left_node.getFather();
				
				String f1="t"+(tno++);
				String f2=father.sons.get(0).attribute.get("value");
				
				codes.add(f1+" := "+f2+" + 1");
				
				father.attribute.put("value", f1);
			}else if(leftest.getName().equals("M59_3")){
				
				Node father=left_node.getFather();
				
				String f1="t"+(tno++);
				String f2=father.sons.get(0).attribute.get("value");
				
				codes.add(f1+" := "+f2+" - 1");
				
				father.attribute.put("value", f1);
			}else if(leftest.getName().equals("M84_3")){
				
				Node father=left_node.getFather();
				
				String f1="t"+(tno++);
				String f2=father.sons.get(0).attribute.get("value");
				
				codes.add(f1+" := 0 - "+f2);
				
				father.attribute.put("value", f1);
			}else if(leftest.getName().equals("M60_4")){
				
				Node father=left_node.getFather();
				
				father.attribute.put("value", father.sons.get(0).attribute.get("value"));
			}else if(leftest.getName().equals("M61_2")){
				
				Node father=left_node.getFather();
				
				father.sons.get(0).attribute.put("name", token_list.get(pos-1).getSource());
			}else if(leftest.getName().equals("M63_1")){
				
				Node father=left_node.getFather();
				
				father.sons.get(0).attribute.put("name", father.attribute.get("name"));
				father.sons.get(0).attribute.put("dimension", "0");
			}else if(leftest.getName().equals("M15_6") || leftest.getName().equals("M63_3") 
					|| leftest.getName().equals("M61_4") || leftest.getName().equals("M62_2")){
				
				Node father=left_node.getFather();
				
				father.attribute.put("value", father.sons.get(0).attribute.get("value"));
				father.attribute.put("val", father.sons.get(0).attribute.get("val"));
			}else if(leftest.getName().equals("M52_2")){
				
				Node father=left_node.getFather();
				
				father.sons.get(1).attribute.put("value", father.sons.get(0).attribute.get("value"));
				father.sons.get(1).attribute.put("val", father.sons.get(0).attribute.get("val"));
			}else if(leftest.getName().equals("M54_3")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				String t="t"+(tno++);
				
				codes.add(t+" := "+inh+" * "+value);
				
				father.sons.get(1).attribute.put("value", t);
			}else if(leftest.getName().equals("M55_3")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				String t="t"+(tno++);
				
				codes.add(t+" := "+inh+" / "+value);
				
				father.sons.get(1).attribute.put("value", t);
			}else if(leftest.getName().equals("M56_3")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				String t="t"+(tno++);
				
				codes.add(t+" := "+inh+" % "+value);
				
				father.sons.get(1).attribute.put("value", t);
			}else if(leftest.getName().equals("M50_3")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				String t="t"+(tno++);
				
				codes.add(t+" := "+inh+" + "+value);
				
				father.sons.get(1).attribute.put("value", t);
			}else if(leftest.getName().equals("M51_3")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				String t="t"+(tno++);
				
				codes.add(t+" := "+inh+" - "+value);
				
				father.sons.get(1).attribute.put("value", t);
			}else if(leftest.getName().equals("M52_4")){
				
				Node father=left_node.getFather();
				
				father.attribute.put("value", father.sons.get(1).attribute.get("value"));
				father.attribute.put("val", father.sons.get(1).attribute.get("val"));
			}else if(leftest.getName().equals("M42_4")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				String b="b"+(bno++);
				
				codes.add(b+" := "+inh+" < "+value);
				
				father.attribute.put("value", b);
			}else if(leftest.getName().equals("M43_4")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				String b="b"+(bno++);
				
				codes.add(b+" := "+inh+" <= "+value);
				
				father.attribute.put("value", b);
			}else if(leftest.getName().equals("M44_4")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				String b="b"+(bno++);
				
				codes.add(b+" := "+inh+" > "+value);
				
				father.attribute.put("value", b);
			}else if(leftest.getName().equals("M45_4")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				String b="b"+(bno++);
				
				codes.add(b+" := "+inh+" >= "+value);
				
				father.attribute.put("value", b);
			}else if(leftest.getName().equals("M46_4")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				String b="b"+(bno++);
				
				codes.add(b+" := "+inh+" == "+value);
				
				father.attribute.put("value", b);
			}else if(leftest.getName().equals("M47_4")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				String b="b"+(bno++);
				
				codes.add(b+" := "+inh+" != "+value);
				
				father.attribute.put("value", b);
			}else if(leftest.getName().equals("M78_3")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("val");
				String value=father.sons.get(0).attribute.get("value");
				if(inh==null || inh.equals("null"))
					inh=father.attribute.get("value");
				else{
					String temp=father.attribute.get("value");
					
					for(int i=codes.size()-1;i>=0;i--){
						if(codes.get(i)!=null&&codes.get(i).startsWith(temp)){
							codes.remove(i);
						}
					}
				}
				
				codes.add(inh+" := "+value);
				
				father.attribute.put("value", inh);
			}else if(leftest.getName().equals("M79_3")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				
				codes.add(inh+" := "+inh+" + "+value);
				
				father.attribute.put("value", inh);
			}else if(leftest.getName().equals("M80_3")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				
				codes.add(inh+" := "+inh+" - "+value);
				
				father.attribute.put("value", inh);
			}else if(leftest.getName().equals("M81_3")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				
				codes.add(inh+" := "+inh+" * "+value);
				
				father.attribute.put("value", inh);
			}else if(leftest.getName().equals("M82_3")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				
				codes.add(inh+" := "+inh+" / "+value);
				
				father.attribute.put("value", inh);
			}else if(leftest.getName().equals("M83_3")){
				
				Node father=left_node.getFather();
				
				String inh=father.attribute.get("value");
				String value=father.sons.get(0).attribute.get("value");
				
				codes.add(inh+" := "+inh+" % "+value);
				
				father.attribute.put("value", inh);
			}else if(leftest.getName().equals("M26_5")){
				
				Node father=left_node.getFather();
				String b=father.sons.get(0).attribute.get("value");
				
				codes.add("if "+b+" goto "+(codes.size()+2));
				father.attribute.put("backpatch", ""+codes.size());
				codes.add(null);
			}else if(leftest.getName().equals("M26_7")){
				
				Node father=left_node.getFather();
				int backpatch=Integer.parseInt(father.attribute.get("backpatch"));
				
				codes.add(null);
				codes.set(backpatch, "goto "+codes.size());//回填
				
				father.attribute.put("backpatch", ""+(codes.size()-1));
			}else if(leftest.getName().equals("M26_9")){
				
				Node father=left_node.getFather();
				int backpatch=Integer.parseInt(father.attribute.get("backpatch"));
				
				codes.set(backpatch, "goto "+codes.size());//回填
			}else if(leftest.getName().equals("M38_7")){
				
				Node father=left_node.getFather();
				int backpatch=Integer.parseInt(father.attribute.get("backpatch"));
				int backto=Integer.parseInt(father.attribute.get("backto"));
				codes.add("goto "+backto);
				codes.set(backpatch, "goto "+codes.size());//回填
			}else if(leftest.getName().equals("M38_3")){
				
				Node father=left_node.getFather();
				
				father.attribute.put("backto", ""+codes.size());
			}
//			System.out.println(leftest.getName());
			
			if(leftest.isTerminal()){
				if(leftest.getName().equals(input_symbol.getName())){
					pos++;//匹配
				}else if(pos<token_list.size()&&token_list.get(pos+1).getName().equals(leftest.getName())){//error:栈顶的终结符与输入的终结符不匹配
					
					ErrorProduction err_pro=new ErrorProduction(-1,leftest.getName(),leftest.getName());//action:弹出此终结符
					err_pro.setError("栈顶的终结符\'"+leftest.getName()+"\'与输入的终结符\'"+input_symbol.getName()+"\'不匹配 at line "+line);
					err_pro.setSolution("跳过输入的终结符\'"+input_symbol.getName()+"\'");
	
					pro_list.add(err_pro);
					
					pos++;//跳过一个输入
					stack.push(leftest);//把栈顶终结符压回
				}else{//error:栈顶的终结符与输入的终结符不匹配
					
					ErrorProduction err_pro=new ErrorProduction(-1,leftest.getName());//action:弹出此终结符
					err_pro.setError("栈顶的终结符\'"+leftest.getName()+"\'与输入的终结符\'"+input_symbol.getName()+"\'不匹配 at line "+line);
					err_pro.setSolution("弹出栈顶终结符\'"+leftest.getName()+"\'");
	
					pro_list.add(err_pro);
				}
			}else{
				List<Production> pros=this.getProductionsByLeft(leftest.getName());
				
				boolean error=true;//出现错误
				for(int i=0;i<pros.size();i++){
					if(pros.get(i).getSelect().contains(input_symbol.getName())){
						pro_list.add(pros.get(i));
						String[] right=pros.get(i).getRight();
						
						for(int j=right.length-1;j>=0;j--){
							
							Symbol temp=getSymbol(right[j]);
							
							stack.push(temp);
							
							if(!temp.isTerminal()){
								Node node=new Node(temp.getName(), left_node);
								
								if(node.getSymbol_name().charAt(0)!='M')
									left_node.sons.add(0,node);
								
								node_stack.push(node);
							}
						}
						
						error=false;
						break;
					}
				}
				
				if(error){
					Production pro=this.getProductionToBlank(leftest.getName());
					
					if(pro!=null){//栈顶非终结符能推出空
						ErrorProduction err_pro=new ErrorProduction(pro);
						err_pro.setError("栈顶非终结符\'"+leftest.getName()+"\'不能接收输入的终结符\'"+input_symbol.getName()+"\' at line "+line);
						err_pro.setSolution("使用能将栈顶非终结符\'"+leftest.getName()+"\'推导为空的产生式，推迟错误处理");
						
						pro_list.add(err_pro);
					}else if(leftest.has("follow", input_symbol.getName())){
						ErrorProduction err_pro=new ErrorProduction(-1,leftest.getName());
						err_pro.setError("栈顶非终结符\'"+leftest.getName()+"\'不能接收输入的终结符\'"+input_symbol.getName()+"\' at line "+line);
						err_pro.setSolution("跳过栈顶非终结符\'"+leftest.getName()+"\'");
						
						pro_list.add(err_pro);
					}else{
						ErrorProduction err_pro=new ErrorProduction(-1,stack.get(stack.size()-1).getName(),stack.get(stack.size()-1).getName());
						err_pro.setError("栈顶非终结符\'"+leftest.getName()+"\'不能接收输入的终结符\'"+input_symbol.getName()+"\' at line "+line);
						err_pro.setSolution("跳过输入的终结符\'"+input_symbol.getName()+"\'");
						pro_list.add(err_pro);
						
						pos++;
						stack.push(input_symbol);
					}
				}
			}
		}
		
		/////////////测试
		for(int i=0;i<codes.size();i++){
			System.out.println(i+"\t"+codes.get(i));
		}
		
		return pro_list;
	}
}
