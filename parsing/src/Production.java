import java.util.ArrayList;


// 产生式类
public class Production{
	String left;
	String[] right;
	// 初始化select集
	ArrayList<String> select = new ArrayList<String>();
	public Production(String left, String[] right){
		this.left = left;
		this.right = right;
	}
	
	public String[] returnRights(){
		return right;
	}
	
	public String returnLeft(){
		return left;
	}
	
	
	
}
