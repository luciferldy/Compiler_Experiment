import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;


public class ClientGui{
	public static void main(String[] args){
		LexFrame lexframe = new LexFrame();
		lexframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lexframe.setResizable(false);
		lexframe.setVisible(true);
		AnalyseList analyse = new AnalyseList();
		
	}
	public ClientGui() {
		// TODO Auto-generated constructor stub
	}
}
class LexFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel main_panel;
	
	private JMenuBar main_menu_bar;
	private JMenu menu_file;
	private JMenu menu_run;
	private JMenuItem file_open;
	private JMenuItem file_save;
	private JMenuItem file_saveas;
	private JMenuItem exit;
	private JMenuItem run_lex;
	
	private JLabel lb_lex_result;
	private JLabel lb_lex_error;
	private JLabel lb_text_edit;
	private JLabel lb_symbol;
	private JLabel lb_triples;
	
	private JButton btn_start_lex;
	private JButton btn_cleardata;
	private JTextArea ta_input;
	private JScrollPane scrollpane_input;
	
	// 输出词法分析结果
	private JTable tb_lex_result;
	private DefaultTableModel tbmodel_lex_result;
	private JScrollPane scrollpane_lex_result;
	// 输出句法分析结果
	private JTable tb_lex_error;
	private DefaultTableModel tbmodel_lex_error;
	private JScrollPane scrollpane_lex_error;
	
	// 输出符号表
	private JTable tb_symbol_list;
	private DefaultTableModel tbmodel_symbol_list;
	private JScrollPane scrollpane_symbol_list;
	// 输出三地址指令
	private JTable tb_triples;
	private DefaultTableModel tbmodel_triples;
	private JScrollPane scrollpane_triples;
	
	public LexFrame(){
		this.setTitle("语义分析器");
		this.setSize(1300,800);
		initPanel();
	}
	
	public void initPanel(){
		main_menu_bar = new JMenuBar();
		menu_file = new JMenu("文件");
		menu_run = new JMenu("运行");
		
		file_open = new JMenuItem("打开");
		file_save = new JMenuItem("保存");
		file_saveas = new JMenuItem("另存为");
		exit = new JMenuItem("退出");
		file_open.addActionListener(this);
		exit.addActionListener(this);
		menu_file.add(file_open);
		menu_file.add(file_save);
		menu_file.add(file_saveas);
		menu_file.add(exit);
		main_menu_bar.add(menu_file);
		
		run_lex = new JMenuItem("运行");
		run_lex.addActionListener(this);
		menu_run.add(run_lex);
		main_menu_bar.add(menu_run);
		this.setJMenuBar(main_menu_bar);
		
		main_panel = new JPanel();
		main_panel.setLayout(null);
		lb_text_edit = new JLabel("文本编辑区");
		main_panel.add(lb_text_edit);
		lb_text_edit.setBounds(10, 10, 70, 20);
		
		ta_input = new JTextArea();
		scrollpane_input = new JScrollPane(ta_input);
		main_panel.add(scrollpane_input);
		scrollpane_input.setBounds(10, 40, 400, 300);
		scrollpane_input.setRowHeaderView(new LineNumberHeaderView());
		
		lb_lex_result = new JLabel("词法分析结果");
		main_panel.add(lb_lex_result);
		lb_lex_result.setBounds(450, 10, 80, 20);
		
		tbmodel_lex_result = new DefaultTableModel(null, new String[]{"名称", "值"});
		tb_lex_result = new JTable(tbmodel_lex_result);
		tb_lex_result.setEnabled(false);
		scrollpane_lex_result = new JScrollPane(tb_lex_result);
		main_panel.add(scrollpane_lex_result);
		scrollpane_lex_result.setBounds(450, 40, 360, 300);
		
		lb_symbol = new JLabel("符号表");
		main_panel.add(lb_symbol);
		lb_symbol.setBounds(830, 10, 80, 20);
		
		tbmodel_symbol_list = new DefaultTableModel(null, new String[]{"变量名称", "所属类型", "长度", "内存地址"});
		tb_symbol_list = new JTable(tbmodel_symbol_list);
		tb_symbol_list.setEnabled(false);
		scrollpane_symbol_list = new JScrollPane(tb_symbol_list);
		main_panel.add(scrollpane_symbol_list);
		scrollpane_symbol_list.setBounds(830, 40, 360, 300);
		
		btn_start_lex = new JButton("运行");
		main_panel.add(btn_start_lex);
		btn_start_lex.setBounds(200, 350, 100, 20);
		btn_start_lex.addActionListener(this);
		
		btn_cleardata = new JButton("清空");
		main_panel.add(btn_cleardata);
		btn_cleardata.setBounds(330, 350, 60, 20);
		btn_cleardata.addActionListener(this);
		
		lb_lex_error = new JLabel("推导过程");
		main_panel.add(lb_lex_error);
		lb_lex_error.setBounds(10, 360, 80, 20);
		
		tbmodel_lex_error = new DefaultTableModel(null, new String[]{"推导", "运用的产生式"});
		tb_lex_error = new JTable(tbmodel_lex_error);
		tb_lex_error.setForeground(Color.BLUE);
		tb_lex_error.setEnabled(false);
		scrollpane_lex_error = new JScrollPane(tb_lex_error);
		main_panel.add(scrollpane_lex_error);
		scrollpane_lex_error.setBounds(10, 400, 800, 300);
		
		lb_triples = new JLabel("三地址指令");
		main_panel.add(lb_triples);
		lb_triples.setBounds(830,360, 80, 20);
		
		tbmodel_triples = new DefaultTableModel(null, new String[]{"序号","三地址码"});
		tb_triples = new JTable(tbmodel_triples);
		tb_triples.setEnabled(false);
		scrollpane_triples = new JScrollPane(tb_triples);
		main_panel.add(scrollpane_triples);
		scrollpane_triples.setBounds(830, 400, 360, 300);
		
		add(main_panel);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ((e.getSource() == btn_start_lex) || (e.getSource() == run_lex)) {
			// 进行判定k
			clearTableData();
			if(ta_input.getText().equals("")){
				JOptionPane.showMessageDialog(main_panel, "您什么都没有输入啊！", "提示", JOptionPane.ERROR_MESSAGE);
				System.out.println("nothing input!");
			}
			else {
				// 词法分析
				TextLex text_lex = new TextLex(ta_input.getText(), tbmodel_lex_result, tbmodel_lex_error);
				text_lex.scannerAll();
				
				// 获得结果的表
				ArrayList<String> lex_result_stack = text_lex.get_Lex_Result();
				ArrayList<HashMap<String, String>> lex_error_stack = text_lex.get_Lex_Error();
				
				// 若是存在词法分析错误
				if(lex_error_stack.size()!=0){
					JOptionPane.showMessageDialog(main_panel, "词法分析阶段出现错误！", "提示", JOptionPane.ERROR_MESSAGE);
				}
				else {
					// 句法分析
					TextParse textParse = new TextParse(lex_result_stack, tbmodel_lex_error);
					textParse.Parsing();
					// 语义分析
					SemanticAnalyse semanticanalyse = new SemanticAnalyse(ta_input.getText(), tbmodel_symbol_list, tbmodel_triples);
					semanticanalyse.Parsing();		
				}	
				
			}
		}
		else if(e.getSource() == btn_cleardata){
			ta_input.setText("");
			clearTableData();
		}
		else if(e.getSource() == file_open){
			String file_name;
			JFileChooser file_open_filechooser = new JFileChooser();
			file_open_filechooser.setCurrentDirectory(new File("."));
			file_open_filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int result = file_open_filechooser.showOpenDialog(main_panel);
			// 证明有选择
			if (result==JFileChooser.APPROVE_OPTION) {
				file_name = file_open_filechooser.getSelectedFile().getPath();
				// 读取文件，写到JTextArea里面
				File file = new File(file_name);
				try{
					InputStream in = new FileInputStream(file);
					int tempbyte;
					while ((tempbyte=in.read()) != -1) {
						ta_input.append(""+(char)tempbyte);
					}
					in.close();
				}
				catch(Exception event){
					event.printStackTrace();
				}
			}
			
		}
		else if(e.getSource() == exit){
			System.exit(1);
		}
		else {
			System.out.println("nothing！");
		}
	}
	
	public void clearTableData(){
//		System.out.println(tbmodel_lex_result.getRowCount());
		// 事先要是不给他们赋值的话就会造成，tbmodel_lex_error在删除的过程中会不断
		// 地减少，然后就会出现很蛋疼的删不干净的情况
		int error_rows = tbmodel_lex_error.getRowCount();
		int result_rows = tbmodel_lex_result.getRowCount();
		int triples_rows = tbmodel_triples.getRowCount();
		int symbols_rows = tbmodel_symbol_list.getRowCount();
		for(int i=0;i<error_rows;i++)
		{
			tbmodel_lex_error.removeRow(0);
			tb_lex_error.updateUI();
		}
			
		for (int i=0;i<result_rows;i++){
			tbmodel_lex_result.removeRow(0);
			tb_lex_result.updateUI();
		}
		
		for(int i=0;i<triples_rows;i++){
			tbmodel_triples.removeRow(0);
			tb_triples.updateUI();
		}
		
		for(int i=0;i<symbols_rows;i++){
			tbmodel_symbol_list.removeRow(0);
			tb_symbol_list.updateUI();
		}
			
	}
}