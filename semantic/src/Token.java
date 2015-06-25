import java.util.ArrayList;
import java.util.List;

public class Token implements Cloneable{
	
	//标识符表
	static List<String> id_list;
	
	String name;
	String value=null;
	String source;
	
	public static List<String> getId_list() {
		return id_list;
	}
	
	public static void freeId_list(){
		id_list=new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public String getSource() {
		return source;
	}

	public Token(){}
	
	public Token(String name,String source){
		this.name=name;
		this.source=source;
		
		if(source==null)
			return;
		
		if(name.equals("int")||name.equals("unsigned")||name.equals("long")||name.equals("long_unsigned")){
			
			long k;//k代表k进制
			int begin;//从source的begin号字符开始算数
			
			if(source.charAt(0)=='0'){
				if(source.length()==1||source.equals("0l")||source.equals("0L")
						||source.equals("0U")||source.equals("0u")
						||source.equals("0LU")||source.equals("0Lu")
						||source.equals("0lU")||source.equals("0lu")){
					this.value="0";
					return;
				}
				
				if(source.charAt(1)=='x' || source.charAt(1)=='X'){
					k=16;
					begin=2;
				}else if(source.charAt(1)>='0' && source.charAt(1)<='7'){
					k=8;
					begin=1;
				}else{
					throw new IllegalArgumentException("整形常量\'"+source+"\'的第二个字符不合法");
				}
				
			}else if(source.charAt(0)>='1' && source.charAt(0)<='9'){
				
				k=10;
				begin=0;
				
			}else{
				throw new IllegalArgumentException("整形常量\'"+source+"\'的首字符必须是数字");
			}
			
			long ret=0;
			for(int i=begin;i<source.length();i++){
				int temp=char_to_int(source.charAt(i));
				if(temp<0)
					break;
				
				ret*=k;
				ret+=temp;
			}
			
			this.value=""+ret;
		}else if(name.equals("float")||name.equals("double")||name.equals("long_double")){
			
//			System.out.println("source = "+source);
			
			int p1=source.indexOf('.');
			int p2=source.indexOf('e')>source.indexOf('E')?source.indexOf('e'):source.indexOf('E');
			int p3=p2;
			
			if(p2<0)
				p2=source.length();
			else if(source.charAt(p2+1)=='-' || source.charAt(p2+1)=='+')
				p3++;
			
			long zhengbu=0;
			
//			System.out.println("p1 = "+p1);
			
			for(int i=0;i<p1;i++){
				
//				System.out.println(i+"\t"+(long)char_to_int(source.charAt(i)));
				
				zhengbu*=10;
				zhengbu+=(long)char_to_int(source.charAt(i));
			}
			
			double xiaoshubu=0.0;
			for(int i=p2-1;i>p1;i--){
				xiaoshubu+=(double)char_to_int(source.charAt(i));
				xiaoshubu/=10.0;
			}
			
			long zhishu=0;
			for(int i=p3+1;i<source.length();i++){
				long temp=(long)char_to_int(source.charAt(i));
				if(temp<0)
					break;
				
				zhishu*=10;
				zhishu+=temp;
			}
			
			if(p3>=0 && source.charAt(p3)=='-')
				zhishu=-zhishu;
			
			if(p3<0)
				zhishu=0;
			
//			System.out.println("zhengbu = "+zhengbu);
//			System.out.println("xiaoshubu = "+xiaoshubu);
//			System.out.println("zhishu = "+zhishu);
			this.value=((zhengbu+xiaoshubu)*Math.pow(10.0,zhishu))+"";
		}else if(name.equals("char")){
			
			if(source.charAt(1)=='\\'){
				
				int ret=0;
				
				char temp=source.charAt(2);
				
				switch(temp){
				case 'x':
					ret+=char_to_int(source.charAt(3));
					if(source.charAt(4)!='\''){
						ret*=16;
						ret+=char_to_int(source.charAt(4));
					}
					break;
				case 'n':
					ret='\n';
					break;
				case 'r':
					ret='\r';
					break;
				case 't':
					ret='\t';
					break;
				case 'v':
					ret=11;
					break;
				case 'b':
					ret='\b';
					break;
				case 'f':
					ret='\f';
					break;
				case 'a':
					ret=7;
					break;
				default:
					if(source.charAt(2)>='0' && source.charAt(2)<='7'){
						ret+=source.charAt(2)-'0';
						
						if(source.charAt(3)!='\''){
							ret*=8;
							ret+=source.charAt(3)-'0';
							
							if(source.charAt(4)!='\''){
								ret*=8;
								ret+=source.charAt(4)-'0';
							}
						}
					}else{
						ret=source.charAt(2);
					}
				}
				
				this.value=ret+"";
				
			}else{
				this.value=(int)source.charAt(1)+"";
			}
		}else if(name.equals("string")){
			this.value=source.substring(1, source.length()-1);
		}else if(name.equals("id")){
			int temp=search_id_list(source);
			
			if(temp>=0){
				this.value=temp+"";
			}else{
				id_list.add(source);
				this.value=(id_list.size()-1)+"";
			}
		}
	}
	
	public Token clone(){
		try {
			return (Token)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private static int char_to_int(char c){
		if(c>='0' && c<='9'){
			return c-'0';
		}else if(c>='a' && c<='f'){
			return c-'a'+10;
		}else if(c>='A' && c<='F'){
			return c-'A'+10;
		}else{
			return -1;
		}
	}
	
	private static int search_id_list(String id){
		for(int i=0;i<id_list.size();i++){
			if(id.equals(id_list.get(i)))
				return i;
		}
		
		return -1;
	}
}
