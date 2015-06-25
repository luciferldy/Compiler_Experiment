public class ErrorProduction extends Production{

	String error;//错误描述
	String solution;//处理描述
	
	
	
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return "error: "+error+"\nsolution: "+solution;
//	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	public ErrorProduction(Production father){
		this.no=father.no;
		this.left=father.left;
		this.right=father.right;
	}

	public ErrorProduction(int no, String left, String symbol1, String symbol2,
			String symbol3, String symbol4, String symbol5, String symbol6,
			String symbol7, String symbol8, String symbol9) {
		super(no, left, symbol1, symbol2, symbol3, symbol4, symbol5, symbol6, symbol7,
				symbol8, symbol9);
	}

	public ErrorProduction(int no, String left, String symbol1, String symbol2,
			String symbol3, String symbol4, String symbol5, String symbol6,
			String symbol7, String symbol8) {
		super(no, left, symbol1, symbol2, symbol3, symbol4, symbol5, symbol6, symbol7,
				symbol8);
	}

	public ErrorProduction(int no, String left, String symbol1, String symbol2,
			String symbol3, String symbol4, String symbol5, String symbol6,
			String symbol7) {
		super(no, left, symbol1, symbol2, symbol3, symbol4, symbol5, symbol6, symbol7);
	}

	public ErrorProduction(int no, String left, String symbol1, String symbol2,
			String symbol3, String symbol4, String symbol5, String symbol6) {
		super(no, left, symbol1, symbol2, symbol3, symbol4, symbol5, symbol6);
	}

	public ErrorProduction(int no, String left, String symbol1, String symbol2,
			String symbol3, String symbol4, String symbol5) {
		super(no, left, symbol1, symbol2, symbol3, symbol4, symbol5);
	}

	public ErrorProduction(int no, String left, String symbol1, String symbol2,
			String symbol3, String symbol4) {
		super(no, left, symbol1, symbol2, symbol3, symbol4);
	}

	public ErrorProduction(int no, String left, String symbol1, String symbol2,
			String symbol3) {
		super(no, left, symbol1, symbol2, symbol3);
	}

	public ErrorProduction(int no, String left, String symbol1, String symbol2) {
		super(no, left, symbol1, symbol2);
	}

	public ErrorProduction(int no, String left, String symbol1) {
		super(no, left, symbol1);
	}

	public ErrorProduction(int no, String left) {
		super(no, left);
	}
}
