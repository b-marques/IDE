package GUI;

import javax.swing.JFrame;

import Componentes.AreaDeTextoComScroll;
import Componentes.TabsControl;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	private TabsControl tabs;
	public static JTabbedPane consolePane;
	
	public MainWindow (){
		
		getContentPane().setLayout(null);
		
		consolePane = new JTabbedPane();
		
		tabs = new TabsControl(this);
		tabs.newTab();
		((AreaDeTextoComScroll)tabs.getComponentAt(0)).setUndoRedo(true);
		tabs.setBounds(0, 0, 594, 549);
		
		setJMenuBar(new MenuBar(this, tabs));
		
		consolePane.setBounds(0, 550, 594, 100);
		getContentPane().add(consolePane);
		
		AreaDeTextoComScroll textArea = new AreaDeTextoComScroll();
		
		textArea.setFocusable(false);
		consolePane.addTab("Console", null, textArea, null);

		setSize(600, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Notepad--");
		setResizable(false);
		
		setVisible(true);
		
		revalidate();
	}
}