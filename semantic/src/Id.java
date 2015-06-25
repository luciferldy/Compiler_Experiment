import java.util.ArrayList;
import java.util.List;

public class Id {
	String name;
	String type;//基本类型
	int offset;//起始地址
	int length;//长度
	public List<Integer> arr_list;//各维下标
	
	public Id(String name, String type, int offset, int length) {
		super();
		this.name = name;
		this.type = type;
		this.offset = offset;
		this.length = length;
		
		arr_list=new ArrayList<Integer>();
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getOffset() {
		return offset;
	}

	public int getLength() {
		return length;
	}

}
