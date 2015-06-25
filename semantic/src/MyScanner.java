import java.util.ArrayList;
import java.util.List;

public class MyScanner {
	
	String[] keys={"asm",		"auto",		"break",	"case",		"cdecl",
				   "char",		"const",	"continue",	"default",	"do",
				   "double",	"else",		"enum",		"extern",	"far",
				   "float",		"for",		"goto",		"huge",		"if",
				   "interrupt",	"int",		"long",		"near",		"pascal",
				   "register",	"return",	"short",	"signed",	"sizeof",
				   "static",	"struct",	"switch",	"typedef",	"union",
				   "unsigned",	"void",		"volatile",	"while"};
	String[] rwords={"new","delete"};
	
	int pos=0;//用来扫描源程序的指针
	String source;//源程序
	String status="S";
	
	public MyScanner(String source){
		this.source=source;
	}
	
	public List<Token> execute(){
		
		Token.freeId_list();
		
		char next_char;
		int begin_pos=0;
		
		List<Token> list=new ArrayList<Token>();
		
		while(true){
			
			next_char=this.getNextChar();
			
//			System.out.println(this.status+"\t"+next_char+"\tbegin_pos="+begin_pos+"\tpos="+pos);
			
			if(status.equals("S")){
				if(next_char==0){
					//什么也不做
				}else if(next_char=='0'){//AB
					this.status="AB";
				}else if(next_char>='1' && next_char<='9'){//AC
					this.status="AC";
				}else if(next_char=='\''){//BA
					this.status="BA";
				}else if(next_char=='\"'){//CA
					this.status="CA";
				}else if(next_char=='_' || (next_char>='a' && next_char<='z') || (next_char>='A' && next_char<='Z')){//DT
					this.status="DT";
				}else if(next_char==' ' || next_char=='\t'){//E1
					begin_pos=this.pos;
				}else if(next_char=='\n' || next_char=='\r'){//E1
					list.add(new Token("ENTER",null));
					begin_pos=this.pos;
				}else if(next_char=='('){//E2
					list.add(new Token("(","("));
					begin_pos=this.pos;
				}else if(next_char==')'){//E2
					list.add(new Token(")",")"));
					begin_pos=this.pos;
				}else if(next_char=='['){//E2
					list.add(new Token("[","["));
					begin_pos=this.pos;
				}else if(next_char==']'){//E2
					list.add(new Token("]","]"));
					begin_pos=this.pos;
				}else if(next_char=='.'){//E2
					list.add(new Token(".","."));
					begin_pos=this.pos;
				}else if(next_char=='~'){//E2
					list.add(new Token("~","~"));
					begin_pos=this.pos;
				}else if(next_char=='?'){//E2
					list.add(new Token("?","?"));
					begin_pos=this.pos;
				}else if(next_char==':'){//E2
					list.add(new Token(":",":"));
					begin_pos=this.pos;
				}else if(next_char==','){//E2
					list.add(new Token(",",","));
					begin_pos=this.pos;
				}else if(next_char=='{'){//E2
					list.add(new Token("{","{"));
					begin_pos=this.pos;
				}else if(next_char=='}'){//E2
					list.add(new Token("}","}"));
					begin_pos=this.pos;
				}else if(next_char==';'){//E2
					list.add(new Token(";",";"));
					begin_pos=this.pos;
				}else if(next_char=='-'){//EA
					this.status="EA";
				}else if(next_char=='+'){//EB
					this.status="EB";
				}else if(next_char=='<'){//EC
					this.status="EC";
				}else if(next_char=='>'){//EE
					this.status="EE";
				}else if(next_char=='='){//EG
					this.status="EG";
				}else if(next_char=='&'){//EH
					this.status="EH";
				}else if(next_char=='|'){//EI
					this.status="EI";
				}else if(next_char=='*'){//EJ
					this.status="EJ";
				}else if(next_char=='%'){//EL
					this.status="EL";
				}else if(next_char=='^'){//EM
					this.status="EM";
				}else if(next_char=='!'){//EN
					this.status="EN";
				}else if(next_char=='/'){//FA
					this.status="FA";
				}else{
					list.add(new ErrorToken("ERROR","非法的开始字符",next_char+""));
					begin_pos=this.pos;
				}
				
			}else if(status.equals("AB")){
				if(next_char=='x' || next_char=='X'){//AE
					this.status="AE";
				}else if(next_char>='0' && next_char<='7'){//AD
					this.status="AD";
				}else if(next_char=='8' || next_char=='9'){//AI
					this.status="AI";
				}else if(next_char=='.'){//AG
					this.status="AG";
				}else if(next_char=='e' || next_char=='E'){//AJ
					this.status="AJ";
				}else if(next_char=='u' || next_char=='U'){//AM
					list.add(new Token("unsigned",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char=='l' || next_char=='L'){//AN
					this.status="AN";
				}else{
					list.add(new Token(ConstantType.INT,source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("AC")){
				if(next_char>='0' && next_char<='9'){//AC
					continue;
				}else if(next_char=='.'){//AG
					this.status="AG";
				}else if(next_char=='e' || next_char=='E'){//AJ
					this.status="AJ";
				}else if(next_char=='u' || next_char=='U'){//AM
					list.add(new Token("unsigned",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char=='l' || next_char=='L'){//AN
					this.status="AN";
				}else if(next_char==0){
					list.add(new Token(ConstantType.INT,source.substring(begin_pos, --pos)));
				}else{
					list.add(new Token(ConstantType.INT,source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("AD")){
				if(next_char>='0' && next_char<='7'){//AD
					continue;
				}else if(next_char=='8' || next_char=='9'){//AI
					this.status="AI";
				}else if(next_char=='.'){//AG
					this.status="AG";
				}else if(next_char=='e' || next_char=='E'){//AJ
					this.status="AJ";
				}else if(next_char=='u' || next_char=='U'){//AM
					list.add(new Token("unsigned",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char=='l' || next_char=='L'){//AN
					this.status="AN";
				}else if(next_char==0){
					list.add(new Token(ConstantType.DOUBLE,source.substring(begin_pos, pos)));
				}else{
					list.add(new Token(ConstantType.INT,source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("AE")){
				if((next_char>='0' && next_char<='9') || (next_char>='a' && next_char<='f') || (next_char>='A' && next_char<='F')){//AF
					this.status="AF";
				}else{
					list.add(new ErrorToken("ERROR","不完整的十六进制表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("AF")){
				if((next_char>='0' && next_char<='9') || (next_char>='a' && next_char<='f') || (next_char>='A' && next_char<='F')){//AF
					continue;
				}else if(next_char=='u' || next_char=='U'){//AM
					list.add(new Token("unsigned",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char=='l' || next_char=='L'){//AN
					this.status="AN";
				}else if(next_char==0){
					list.add(new Token(ConstantType.DOUBLE,source.substring(begin_pos, --pos)));
				}else{
					list.add(new Token(ConstantType.INT,source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("AG")){
				if(next_char>='0' && next_char<='9'){//AH
					this.status="AH";
				}else{
					list.add(new ErrorToken("ERROR","不完整的小数表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("AH")){
				if(next_char>='0' && next_char<='9'){//AH
					continue;
				}else if(next_char=='e' || next_char=='E'){//AJ
					this.status="AJ";
				}else if(next_char=='f' || next_char=='F'){//AQ
					list.add(new Token(ConstantType.FLOAT,source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char=='l' || next_char=='L'){//AR
					list.add(new Token(ConstantType.LONG_DOUBLE,source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char==0){
					list.add(new Token(ConstantType.DOUBLE,source.substring(begin_pos, pos)));
				}else{
					list.add(new Token(ConstantType.DOUBLE,source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("AI")){
				if(next_char>='0' && next_char<='9'){//AI
					continue;
				}else if(next_char=='.'){//AG
					this.status="AG";
				}else if(next_char=='e' || next_char=='E'){//AJ
					this.status="AJ";
				}else{
					list.add(new ErrorToken("ERROR","非法的八进制数表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("AJ")){
				if(next_char=='+' || next_char=='-'){//AK
					this.status="AK";
				}else if(next_char>='0' && next_char<='9'){//AL
					this.status="AL";
				}else{
					list.add(new ErrorToken("ERROR","不完整的指数表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("AK")){
				if(next_char>='0' && next_char<='9'){//AL
					this.status="AL";
				}else{
					list.add(new ErrorToken("ERROR","不完整的指数表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("AL")){
				if(next_char>='0' && next_char<='9'){//AL
					continue;
				}else if(next_char=='f' || next_char=='F'){//AQ
					list.add(new Token(ConstantType.FLOAT,source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char=='l' || next_char=='L'){//AR
					list.add(new Token(ConstantType.LONG_DOUBLE,source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token(ConstantType.DOUBLE,source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("AN")){
				if(next_char=='u' || next_char=='U'){//AP
					list.add(new Token(ConstantType.LONG_UNSIGNED,source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token(ConstantType.LONG,source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
				
			}else if(status.equals("BA")){
				if(next_char=='\\'){//BD
					this.status="BD";
				}else if(next_char=='\'' || next_char=='\n' || next_char=='\r'){
					list.add(new ErrorToken("ERROR","不完整的字符表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}else if(next_char==0){
					list.add(new ErrorToken("ERROR","不完整的字符表示形式",source.substring(begin_pos, --pos)));
				}else{//BB
					this.status="BB";
				}
			}else if(status.equals("BB")){
				if(next_char=='\''){//BC
					list.add(new Token(ConstantType.CHAR,source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new ErrorToken("ERROR","错误的字符表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("BD")){
				if(next_char=='0' || next_char=='1'){//BI
					this.status="BI";
				}else if(next_char>='2' && next_char<='7'){//BJ
					this.status="BJ";
				}else if(next_char=='x'){//BM
					this.status="BM";
				}else if(next_char=='\n' || next_char=='\r'){
					list.add(new ErrorToken("ERROR","不完整的字符表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}else if(next_char==0){
					list.add(new ErrorToken("ERROR","不完整的字符表示形式",source.substring(begin_pos, --pos)));
				}else{//BEG
					this.status="BEG";
				}
			}else if(status.equals("BEG")){
				if(next_char=='\''){//BFH
					list.add(new Token(ConstantType.CHAR,source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new ErrorToken("ERROR","错误的字符表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("BI")){
				if(next_char=='\''){//BL
					list.add(new Token(ConstantType.CHAR,source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char>='0' && next_char<='7'){//BJ
					this.status="BJ";
				}else{
					list.add(new ErrorToken("ERROR","不完整的字符表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("BJ")){
				if(next_char=='\''){//BL
					list.add(new Token(ConstantType.CHAR,source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char>='0' && next_char<='7'){//BK
					this.status="BK";
				}else{
					list.add(new ErrorToken("ERROR","不完整的字符表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("BK")){
				if(next_char=='\''){//BL
					list.add(new Token(ConstantType.CHAR,source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new ErrorToken("ERROR","不完整的字符表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("BM")){
				if(next_char>='0' && next_char<='7'){//BN
					this.status="BN";
				}else if((next_char>='8' && next_char<='9') || (next_char>='a' && next_char<='f') || (next_char>='A' && next_char<='F')){//BP
					this.status="BP";
				}else{
					list.add(new ErrorToken("ERROR","不完整的字符表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("BN")){
				if(next_char=='\''){//BQ
					list.add(new Token(ConstantType.CHAR,source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if((next_char>='0' && next_char<='9') || (next_char>='a' && next_char<='f') || (next_char>='A' && next_char<='F')){//BP
					this.status="BP";
				}else{
					list.add(new ErrorToken("ERROR","不完整的字符表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("BP")){
				if(next_char=='\''){//BQ
					list.add(new Token(ConstantType.CHAR,source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new ErrorToken("ERROR","不完整的字符表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}
			}else if(status.equals("CA")){
				if(next_char=='\"'){//CT
					list.add(new Token(ConstantType.STRING,source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char=='\\'){//CB
					this.status="CB";
				}else if(next_char=='\n' || next_char=='\r'){
					list.add(new ErrorToken("ERROR","不完整的字符串表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}else if(next_char==0){
					list.add(new ErrorToken("ERROR","不完整的字符串表示形式",source.substring(begin_pos, --pos)));
				}else{//CA
					continue;
				}
			}else if(status.equals("CB")){
				if(next_char=='\n' || next_char=='\r'){
					list.add(new ErrorToken("ERROR","不完整的字符串表示形式",source.substring(begin_pos, --pos)));
					begin_pos= pos;
					this.status="S";
				}else{//CA
					this.status="CA";
				}
			}else if(status.equals("DT")){
				if(next_char=='_' || (next_char>='a' && next_char<='z') || (next_char>='A' && next_char<='Z') || (next_char>='0' && next_char<='9')){//DT
					continue;
				}else{
					String temp=source.substring(begin_pos, --pos);
					
					boolean flag=true;
					for(int i=0;i<keys.length;i++){
						if(keys[i].equals(temp)){
							list.add(new Token(temp.toUpperCase(),temp));
							flag=false;
							break;
						}
					}
					for(int i=0;i<rwords.length;i++){
						if(rwords[i].equals(temp)){
							list.add(new ErrorToken("ERROR","保留字不能作为标示符使用",temp));
							flag=false;
							break;
						}
					}
					
					if(flag)
						list.add(new Token("id",temp));
					
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("EA")){
				if(next_char=='>'){//E3
					list.add(new Token("->",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char=='='){//E4
					list.add(new Token("-=",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char=='-'){//E5
					list.add(new Token("--",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token("-",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("EB")){
				if(next_char=='+'){//E6
					list.add(new Token("++",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char=='='){//E7
					list.add(new Token("+=",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token("+",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("EC")){
				if(next_char=='<'){//ED
					this.status="ED";
				}else if(next_char=='='){//E81
					list.add(new Token("<=",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token("<",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("ED")){
				if(next_char=='='){//E8
					list.add(new Token("<<=",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token("<<",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("EE")){
				if(next_char=='>'){//EF
					this.status="EF";
				}else if(next_char=='='){//E10
					list.add(new Token(">=",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token(">",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("EF")){
				if(next_char=='='){//E9
					list.add(new Token(">>=",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token(">>",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("EG")){
				if(next_char=='='){//E11
					list.add(new Token("==",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token("=",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("EH")){
				if(next_char=='&'){//E12
					list.add(new Token("&&",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char=='='){//E13
					list.add(new Token("&=",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token("&",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("EI")){
				if(next_char=='|'){//E14
					list.add(new Token("||",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char=='='){//E15
					list.add(new Token("|=",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token("|",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("EJ")){
				if(next_char=='='){//E16
					list.add(new Token("*=",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token("*",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("EL")){
				if(next_char=='='){//E17
					list.add(new Token("%=",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token("%",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("EM")){
				if(next_char=='='){//E18
					list.add(new Token("^=",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token("^",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("EN")){
				if(next_char=='='){//E19
					list.add(new Token("!=",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token("!",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("FA")){
				if(next_char=='/'){//FC
					this.status="FC";
				}else if(next_char=='*'){//FE
					this.status="FE";
				}else if(next_char=='='){//FB
					list.add(new Token("/=",source.substring(begin_pos, pos)));
					begin_pos=this.pos;
					this.status="S";
				}else{
					list.add(new Token("/",source.substring(begin_pos, --pos)));
					begin_pos=this.pos;
					this.status="S";
				}
			}else if(status.equals("FC")){
				if(next_char=='\n' || next_char=='\r'){
					begin_pos=this.pos;
					this.status="S";
					list.add(new Token("ENTER",null));
				}else if(next_char==0){
					break;
				}else{
					continue;
				}
			}else if(status.equals("FE")){
				if(next_char=='*'){
					this.status="FF";
				}else if(next_char==0){
					list.add(new ErrorToken("ERROR","缺少\'*/\'作为注释的结束符",source.substring(begin_pos, --pos)));
				}else{
					continue;
				}
			}else if(status.equals("FF")){
				if(next_char=='/'){
					begin_pos=this.pos;
					this.status="S";
				}else if(next_char==0){
					list.add(new ErrorToken("ERROR","缺少\'*/\'作为注释的结束符",source.substring(begin_pos, --pos)));
				}else if(next_char=='*'){
					continue;
				}else{
					this.status="FE";
				}
			}
			if(next_char==0)
				break;
		}
		
		return list;
	}

	private char getNextChar(){
		if(pos>=source.length()){
			pos++;
			return 0;
		}else{
			return source.charAt(pos++);
		}
	}
}
