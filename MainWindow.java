
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Character.Subset;
import java.util.ArrayList;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class MainWindow extends JFrame {
	
	private TabsControl tabs;
	private MenuBar menuBar;
	private Component selComponent;
	
	public MainWindow (){
		
		tabs = new TabsControl(this);
		tabs.newTab();
		((AreaDeTextoComScroll)tabs.getComponentAt(0)).setUndoRedo(true);
		
		setJMenuBar(menuBar = new MenuBar(this, tabs));
		
		setSize(600, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Notepad--");
		setResizable(true);
		
		setLayout(new GridLayout(1, 1));
		setVisible(true);
		
		revalidate();
	}
}