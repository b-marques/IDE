import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;


public class MenuBar extends JMenuBar implements ActionListener, MouseListener {

	private JMenuItem   newAction,
						openAction,
						closeTabAction,
						saveAction,
						saveAsAction,
						exitAction,
						undoAction,
						redoAction,
						cutAction, 
						copyAction, 
						pasteAction,
						runAction,
						actionMenuFonte,
						actionMenuCorFonte,
				        actionMenuCorTxtArea;
	private MainWindow 	frame;
	private TabsControl controleAbas;
	private PaletaDeCoresTexto coresTxt;
    private PaletaDeCoresTexto coresAreaDeTxt;
    private Color corTexto, corFundo;
	private int index;
	
	public MenuBar(MainWindow window, TabsControl abasControle){
		
		
		this.controleAbas = abasControle;
		this.frame = window;
		controleAbas.addMouseListener(this);
		corTexto = Color.BLACK;
		corFundo = Color.WHITE;
		
        // Define e adiciona dois menus drop down na barra de menus
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu runMenu = new JMenu("Run");
        JMenu windowMenu = new JMenu("Window");
        
        add(fileMenu);
        add(editMenu);
        add(runMenu);
        add(windowMenu);
        
        // Cria e adiciona um item para o menu
        newAction = new JMenuItem("New");
        newAction.setAccelerator(KeyStroke.getKeyStroke(
                					KeyEvent.VK_N, 
                					Event.CTRL_MASK));
        newAction.addActionListener(this);
        
        openAction = new JMenuItem("Open");
        openAction.setAccelerator(KeyStroke.getKeyStroke(
        							KeyEvent.VK_O, 
        							Event.CTRL_MASK));
        openAction.addActionListener(this);
        
        closeTabAction = new JMenuItem("Close tab");
        closeTabAction.setAccelerator(KeyStroke.getKeyStroke(
                						KeyEvent.VK_MINUS, 
                						Event.CTRL_MASK));
        closeTabAction.addActionListener(this);
        
        saveAction = new JMenuItem("Save");
        saveAction.setAccelerator(KeyStroke.getKeyStroke(
                					KeyEvent.VK_S, 
                					Event.CTRL_MASK));
        saveAction.addActionListener(this);
        
        saveAsAction = new JMenuItem("Save As");
        saveAsAction.addActionListener(this);
        
        exitAction = new JMenuItem("Exit");
        exitAction.setAccelerator(KeyStroke.getKeyStroke(
                					KeyEvent.VK_E,
                					Event.CTRL_MASK));
        exitAction.addActionListener(this);
        
//        UNDO and REDO
        
        undoAction = new JMenuItem("Undo");
        undoAction.setAccelerator(KeyStroke.getKeyStroke(
                					KeyEvent.VK_Z,
                					Event.CTRL_MASK));
        undoAction.setEnabled(false);
        undoAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	((UndoManager) ((AreaDeTexto) getTextArea()).getUndoManager()).undo();
                } catch (CannotRedoException cre) {
                  cre.printStackTrace();
                }
                updateButtons();
              }
            });
        
        redoAction = new JMenuItem("Redo");
        redoAction.setAccelerator(KeyStroke.getKeyStroke(
                					KeyEvent.VK_R,
                					Event.CTRL_MASK));
        redoAction.setEnabled(false);
        redoAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                  ((UndoManager) ((AreaDeTexto) getTextArea()).getUndoManager()).redo();
                } catch (CannotRedoException cre) {
                  cre.printStackTrace();
                }
                updateButtons();
              }
            });
        
        adicionaListener();
        
        cutAction = new JMenuItem(new DefaultEditorKit.CutAction());
        cutAction.setText("Cut");
        cutAction.setAccelerator(KeyStroke.getKeyStroke(
        							KeyEvent.VK_X,
        							Event.CTRL_MASK));
        
        copyAction = new JMenuItem(new DefaultEditorKit.CopyAction());
        copyAction.setText("Copy");
        copyAction.setAccelerator(KeyStroke.getKeyStroke(
        							KeyEvent.VK_C,
        							Event.CTRL_MASK));
        
        pasteAction = new JMenuItem(new DefaultEditorKit.PasteAction());
        pasteAction.setText("Paste");
        pasteAction.setAccelerator(KeyStroke.getKeyStroke(
        							KeyEvent.VK_V,
        							Event.CTRL_MASK));
        
        runAction = new JMenuItem("Compile & Run - Java");
        runAction.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F11,
                Event.CTRL_MASK));
        runAction.addActionListener(this);
        
        actionMenuFonte = new JMenuItem("Fonte");
        actionMenuFonte.addActionListener(this);
        
        actionMenuCorFonte = new JMenuItem("Cor da fonte");
        actionMenuCorFonte.addActionListener(this);
        
        actionMenuCorTxtArea = new JMenuItem("Cor plano de fundo");
        actionMenuCorTxtArea.addActionListener(this);
        
        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(closeTabAction);
        fileMenu.addSeparator();
        fileMenu.add(saveAction);
        fileMenu.add(saveAsAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);

        editMenu.add(undoAction);
        editMenu.add(redoAction);
        editMenu.addSeparator();
        editMenu.add(cutAction);
        editMenu.add(copyAction);
        editMenu.add(pasteAction);
        
        runMenu.add(runAction);
        
        windowMenu.add(actionMenuCorTxtArea);
        windowMenu.add(actionMenuFonte);
        windowMenu.add(actionMenuCorFonte);
        
        controleAbas.revalidate();
        revalidate();
	}

	private void updateButtons() {
//		undoAction.setText(undoManager.getUndoPresentationName());
//		redoAction.setText(undoManager.getRedoPresentationName());
		undoAction.setEnabled(((UndoManager) ((AreaDeTexto) getTextArea()).getUndoManager()).canUndo());
		redoAction.setEnabled(((UndoManager) ((AreaDeTexto) getTextArea()).getUndoManager()).canRedo());
	}
	
	private void adicionaListener() {
		((JTextPane)((AreaDeTextoComScroll)controleAbas.getComponentAt(index))
				.getViewport()
				.getView())
        		.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                  public void undoableEditHappened(UndoableEditEvent e) {
                	  ((UndoManager) ((AreaDeTexto) getTextArea()).getUndoManager()).addEdit(e.getEdit());
                    updateButtons();
                  }
                });
		
		((AreaDeTextoComScroll)controleAbas.getComponentAt(index)).setUndoRedo(true);
	}
	
	private void atualizaCor() {
		
		for (int i = 0; i<controleAbas.getComponentCount(); i++){
			index = i;
			getTextArea().setBackground(corFundo);
		}
		for (int i = 0; i<controleAbas.getComponentCount(); i++){
			index = i;
			getTextArea().setForeground(corTexto);
		}
		getIndex();
		
	}
	
	private AreaDeTexto getTextArea(){
		return ((AreaDeTexto)((AreaDeTextoComScroll)controleAbas.getComponentAt(index))
				.getViewport()
				.getView());
	}
	
	private AreaDeTextoComScroll getScrollPane() {
		
		return ((AreaDeTextoComScroll)controleAbas.getComponentAt(index));
		
	}
	
	private int getIndex() {
		this.index = controleAbas.getSelectedIndex();
		return index;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		getIndex();
		
		
		if (e.getSource() == exitAction) { 
			frame.dispose();
		}
		else if (e.getSource() == saveAction ) {
			if(((AreaDeTextoComScroll)controleAbas.getComponentAt(index)).isSaved() == true){
			    PrintWriter printWriter = null;
				try {
					printWriter = new PrintWriter (((AreaDeTextoComScroll)controleAbas.getComponentAt(index)).getFilename());
				} catch (IOException evt) {
					evt.printStackTrace();
				}
				
				//Separa o textArea em linhas
				String[] lines = ((JTextPane)((AreaDeTextoComScroll)controleAbas.getComponentAt(index))
																		.getViewport()
																		.getView())
																		.getText().split("\\n");
				
				for (int i=0; i<lines.length; i++){
					printWriter.println(lines[i]);
				}
				printWriter.close();
			}
			else {
				PrintWriter printWriter = null;
				String nome_arquivo = null;
				try {
					nome_arquivo = JOptionPane.showInputDialog("Salvar Como:");
					printWriter = new PrintWriter (nome_arquivo);
				} catch (IOException evt) {
					evt.printStackTrace();
				}
				
				//Separa o textArea em linhas
				String[] lines = ((JTextPane)((AreaDeTextoComScroll)controleAbas.getComponentAt(index))
																		.getViewport()
																		.getView())
																		.getText().split("\\n");
				
				for (int i=0; i<lines.length; i++){
					printWriter.println(lines[i]);
				}
				((AreaDeTextoComScroll)controleAbas.getComponentAt(index)).setSaved(true);
				((AreaDeTextoComScroll)controleAbas.getComponentAt(index)).setFilename(nome_arquivo);
				controleAbas.setTitleAt(index, ((AreaDeTextoComScroll)controleAbas.getComponentAt(index)).getFilename());
				printWriter.close();
			}
		}
		else if (e.getSource() == saveAsAction ) {
			
			ComandoSalvarComo comando = new ComandoSalvarComo(index, getScrollPane(), controleAbas);
			comando.executarComando();
		}
		
		else if(e.getSource() == openAction) {
			
			controleAbas.newTab();
			
			index = controleAbas.getComponentCount()-1;
			
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(this);
			
			File file = null;
			if(returnVal == JFileChooser.APPROVE_OPTION){     
				file = chooser.getSelectedFile();    
				BufferedReader in = null;
				try {
					in = new BufferedReader(new FileReader(file));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				String line = "";
				try {
					line = in.readLine();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				((JTextPane)((AreaDeTextoComScroll)controleAbas.getComponentAt(index))
														.getViewport()
														.getView())
														.setText(null);
				String resultado = "";
				
				while(line != null){
				
					try {
						line = in.readLine();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(line !=null)resultado += line+"\n";
				}
				((JTextPane)((AreaDeTextoComScroll)controleAbas.getComponentAt(index))
						.getViewport()
						.getView())
						.setText(resultado);
				
				((AreaDeTextoComScroll)controleAbas.getComponentAt(index)).setSaved(true);
				controleAbas.setTitleAt(index, file.getName());
				getTextArea().setForeground(corTexto);
				getTextArea().setBackground(corFundo);
				
				atualizaCor();
				
			}
		}
		else if(e.getSource() == newAction) {
			
			controleAbas.newTab();
			getIndex();
			adicionaListener();
	
			atualizaCor();
			
			controleAbas.revalidate();
			
			
		}
		else if(e.getSource() == closeTabAction) {
			
			if (index != -1)controleAbas.remove((AreaDeTextoComScroll)controleAbas.getComponentAt(index));
		}
		else if (e.getSource() == runAction){
			
			ExecutarLinhaDeComando comando = new ExecutarLinhaDeComando();
			comando.executarComando();
			
		}
		else if (e.getSource() == actionMenuCorTxtArea){
			
			PaletaDeCoresPlanoDeFundo paleta = new PaletaDeCoresPlanoDeFundo(frame, getTextArea());
			
			corFundo = getTextArea().getBackground();
			
			for (int i = 0; i<controleAbas.getComponentCount(); i++){
				index = i;
				getTextArea().setBackground(corFundo);
			}
			getIndex();
		}
		else if (e.getSource() == actionMenuCorFonte){
			
			PaletaDeCoresTexto paleta = new PaletaDeCoresTexto(frame, getTextArea());
			
			corTexto = getTextArea().getForeground();
			
			for (int i = 0; i<controleAbas.getComponentCount(); i++){
				index = i;
				getTextArea().setForeground(corTexto);
			}
			getIndex();
			
		}	
		else{}

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		getIndex();
		
		if(((AreaDeTextoComScroll)controleAbas.getComponentAt(index)).getUndoRedo() == false){
			adicionaListener();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
