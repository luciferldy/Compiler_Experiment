import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
	String symbol_name;
	Node father;
	public List<Node> sons;
	public Map<String,String> attribute;
	
	public Node(String symbolName, Node father) {
		super();
		symbol_name = symbolName;
		this.father = father;
		sons=new ArrayList<Node>();
		attribute=new HashMap<String,String>();
	}

	public void setSons(List<Node> sons) {
		this.sons = sons;
	}

	public String getSymbol_name() {
		return symbol_name;
	}

	public Node getFather() {
		return father;
	}
}
