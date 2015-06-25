import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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


public class ldylex{
	public static void main(String[] args){
		LexFrame lexframe = new LexFrame();
		lexframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lexframe.setResizable(false);
		lexframe.setVisible(true);
	}
	public ldylex() {
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
	
	private JButton btn_start_lex;
	private JButton btn_cleardata;
	private JTextArea ta_input;
	private JScrollPane scrollpane_input;
	
	
	private JTable tb_lex_result;
	private DefaultTableModel tbmodel_lex_result;
	private JScrollPane scrollpane_lex_result;
	private JTable tb_lex_error;
	private DefaultTableModel tbmodel_lex_error;
	private JScrollPane scrollpane_lex_error;
	
	public LexFrame(){
		this.setTitle("ldylex");
		this.setSize(700,700);
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
		
		run_lex = new JMenuItem("词法编译");
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
		
		lb_lex_result = new JLabel("语法分析结果");
		main_panel.add(lb_lex_result);
		lb_lex_result.setBounds(450, 10, 80, 20);
		
		tbmodel_lex_result = new DefaultTableModel(null, new String[]{"单词", "类别"});
		tb_lex_result = new JTable(tbmodel_lex_result);
		tb_lex_result.setEnabled(false);
		scrollpane_lex_result = new JScrollPane(tb_lex_result);
		main_panel.add(scrollpane_lex_result);
		scrollpane_lex_result.setBounds(450, 40, 200, 300);
		
		btn_start_lex = new JButton("词法编译");
		main_panel.add(btn_start_lex);
		btn_start_lex.setBounds(200, 350, 100, 20);
		btn_start_lex.addActionListener(this);
		
		btn_cleardata = new JButton("清空");
		main_panel.add(btn_cleardata);
		btn_cleardata.setBounds(330, 350, 60, 20);
		btn_cleardata.addActionListener(this);
		
		lb_lex_error = new JLabel("错误分析列表");
		main_panel.add(lb_lex_error);
		lb_lex_error.setBounds(10, 390, 80, 20);
		
		tbmodel_lex_error = new DefaultTableModel(null, new String[]{"行号", "错误类型", "错误单词"});
		tb_lex_error = new JTable(tbmodel_lex_error);
		tb_lex_error.setForeground(Color.RED);
		tb_lex_error.setEnabled(false);
		scrollpane_lex_error = new JScrollPane(tb_lex_error);
		main_panel.add(scrollpane_lex_error);
		scrollpane_lex_error.setBounds(10, 420, 600, 200);
		
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
				TextLex text_lex = new TextLex(ta_input.getText(), tbmodel_lex_result, tbmodel_lex_error);
				text_lex.scannerAll();
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
		for(int i=0;i<error_rows;i++)
		{
			tbmodel_lex_error.removeRow(0);
			tb_lex_error.updateUI();
		}
			
		for (int i=0;i<result_rows;i++){
			tbmodel_lex_result.removeRow(0);
			tb_lex_result.updateUI();
		}
			
	}
}