import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

public class Symbol {
	int no;
	private String name;
	private char type;//N代表非终结符，T代表终结符
	private String higher_name;//高层结构的符号
	public List<String> first;
	public List<String> follow;
//	public Map<String,String> attribute;
//	public List<String> select; 产生式才有可选集
	
	public Symbol(int no, String name, char type) {
		super();
		this.no=no;
		this.name = name;
		this.type=type;
		this.first = new ArrayList<String>();
//		this.attribute=new HashMap<String,String>(); 
		
		if(type=='N'){
			this.follow = new ArrayList<String>();
//			this.select = new ArrayList<String>();
		}else if(type=='T'){
			this.first.add(name);//非终结符的first集只包含它自身
			this.follow = null;
//			this.select = null;
		}else{
			throw new IllegalArgumentException("非法的符号类型");
		}
	}
	
	public Symbol(int no, String name, char type, String higher_name){
		this(no, name, type);
		this.higher_name=higher_name;
	}

	public String getName() {
		return name;
	}
	
	public String getHigher_name() {
		return higher_name;
	}

	public boolean isTerminal(){
		if(type=='N')
			return false;
		else
			return true;
	}
	
	public boolean has(String arr_name, String sym_name){
		List<String> arr=null;
		
		if(arr_name.toUpperCase().equals("FIRST"))
			arr=this.first;
		else if(arr_name.toUpperCase().equals("FOLLOW"))
			arr=this.follow;
//		else if(arr_name.toUpperCase().equals("SELECT"))
//			arr=this.select;
		else
			throw new IllegalArgumentException("非法的集合名");
		
		for(int i=0;i<arr.size();i++){
			if(arr.get(i).equals(sym_name))
				return true;
		}
		
		return false;
	}
}
