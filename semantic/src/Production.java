import java.util.List;

public class Production {
	
	int no;
	String left;
	String[] right;
	List<String> select;
	
	public String getError(){
		return null;
	}
	
	public String getSolution(){
		return null;
	}
	
	@Override
	public String toString() {
		String ret=left+" -->";
		
		if(right.length==0)
			ret+=" ξ";
		
		for(int i=0;i<right.length;i++){
			ret+=" "+right[i];
		}
		
		return ret;
	}

	public List<String> getSelect() {
		return select;
	}

	public void setSelect(List<String> select) {
		this.select = select;
	}

	public int getNo() {
		return no;
	}

	public String getLeft() {
		return left;
	}

	public String[] getRight() {
		return right;
	}

	public Production(){}
	
	public Production(int no, String left) {
		this.no = no;
		this.left = left;
		this.right = new String[0];
//		this.canBeBlank=true;
	}
	
	public Production(int no, String left, String symbol1) {
		this.no = no;
		this.left = left;
		this.right = new String[1];
		this.right[0]=symbol1;
	}
	
	public Production(int no, String left, String symbol1, String symbol2) {
		this.no = no;
		this.left = left;
		this.right = new String[2];
		this.right[0]=symbol1;
		this.right[1]=symbol2;
	}
	
	public Production(int no, String left, String symbol1, String symbol2, String symbol3) {
		this.no = no;
		this.left = left;
		this.right = new String[3];
		this.right[0]=symbol1;
		this.right[1]=symbol2;
		this.right[2]=symbol3;
	}
	
	public Production(int no, String left, String symbol1, String symbol2, String symbol3,
			String symbol4) {
		this.no = no;
		this.left = left;
		this.right = new String[4];
		this.right[0]=symbol1;
		this.right[1]=symbol2;
		this.right[2]=symbol3;
		this.right[3]=symbol4;
	}
	
	public Production(int no, String left, String symbol1, String symbol2, String symbol3,
			String symbol4, String symbol5) {
		this.no = no;
		this.left = left;
		this.right = new String[5];
		this.right[0]=symbol1;
		this.right[1]=symbol2;
		this.right[2]=symbol3;
		this.right[3]=symbol4;
		this.right[4]=symbol5;
	}
	
	public Production(int no, String left, String symbol1, String symbol2, String symbol3,
			String symbol4, String symbol5, String symbol6) {
		this.no = no;
		this.left = left;
		this.right = new String[6];
		this.right[0]=symbol1;
		this.right[1]=symbol2;
		this.right[2]=symbol3;
		this.right[3]=symbol4;
		this.right[4]=symbol5;
		this.right[5]=symbol6;
	}
	
	public Production(int no, String left, String symbol1, String symbol2, String symbol3,
			String symbol4, String symbol5, String symbol6, String symbol7) {
		this.no = no;
		this.left = left;
		this.right = new String[7];
		this.right[0]=symbol1;
		this.right[1]=symbol2;
		this.right[2]=symbol3;
		this.right[3]=symbol4;
		this.right[4]=symbol5;
		this.right[5]=symbol6;
		this.right[6]=symbol7;
	}
	
	public Production(int no, String left, String symbol1, String symbol2, String symbol3,
			String symbol4, String symbol5, String symbol6, String symbol7, String symbol8) {
		this.no = no;
		this.left = left;
		this.right = new String[8];
		this.right[0]=symbol1;
		this.right[1]=symbol2;
		this.right[2]=symbol3;
		this.right[3]=symbol4;
		this.right[4]=symbol5;
		this.right[5]=symbol6;
		this.right[6]=symbol7;
		this.right[7]=symbol8;
	}
	
	public Production(int no, String left, String symbol1, String symbol2, String symbol3,
			String symbol4, String symbol5, String symbol6, String symbol7, String symbol8,
			String symbol9) {
		this.no = no;
		this.left = left;
		this.right = new String[9];
		this.right[0]=symbol1;
		this.right[1]=symbol2;
		this.right[2]=symbol3;
		this.right[3]=symbol4;
		this.right[4]=symbol5;
		this.right[5]=symbol6;
		this.right[6]=symbol7;
		this.right[7]=symbol8;
		this.right[8]=symbol9;
	}
}
