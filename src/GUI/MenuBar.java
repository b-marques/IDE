package GUI;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

import Comandos.ComandoAbrir;
import Comandos.ComandoCorPlanoDeFundo;
import Comandos.ComandoCorTexto;
import Comandos.ComandoExecutarLinhaDeComando;
import Comandos.ComandoFecharAba;
import Comandos.ComandoImprimir;
import Comandos.ComandoSalvar;
import Comandos.ComandoSalvarComo;
import Componentes.AreaDeTexto;
import Componentes.AreaDeTextoComScroll;
import Componentes.TabsControl;


@SuppressWarnings("serial")
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
						actionMenuCorFonte,
				        actionMenuCorTxtArea,
						printAction;
	private MainWindow 	frame;
	private TabsControl controleAbas;
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
        JMenu windowMenu = new JMenu("Options");
        
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
        
        actionMenuCorFonte = new JMenuItem("Font color");
        actionMenuCorFonte.addActionListener(this);
        
        actionMenuCorTxtArea = new JMenuItem("Background Color");
        actionMenuCorTxtArea.addActionListener(this);
        
        printAction = new JMenuItem("Print");
        printAction.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P,
                Event.CTRL_MASK));
        printAction.addActionListener(this);
        
        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(closeTabAction);
        fileMenu.addSeparator();
        fileMenu.add(saveAction);
        fileMenu.add(saveAsAction);
        fileMenu.addSeparator();
        fileMenu.add(printAction);
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
        windowMenu.add(actionMenuCorFonte);
        
        controleAbas.revalidate();
        revalidate();
	}

	private void updateButtons() {
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
			
			ComandoSalvar comando = new ComandoSalvar(getTextArea(), getScrollPane(), controleAbas, index);
			comando.executarComando();
		}
		
		else if (e.getSource() == saveAsAction ) {
			
			ComandoSalvarComo comando = new ComandoSalvarComo(index, getScrollPane(), controleAbas);
			comando.executarComando();
		}
		
		else if(e.getSource() == openAction) {
			
			ComandoAbrir comando = new ComandoAbrir(index, controleAbas, getTextArea(), corTexto, corFundo);
			comando.executarComando();
			atualizaCor();
		}
		else if(e.getSource() == newAction) {
			
			controleAbas.newTab();
			getIndex();
			adicionaListener();
			atualizaCor();
			controleAbas.revalidate();
			
			
		}
		else if(e.getSource() == closeTabAction) {
			
			if (index != -1){
				ComandoFecharAba comando = new ComandoFecharAba(controleAbas, index, getScrollPane());
				comando.executarComando();
				getIndex();
			}
		}
		else if (e.getSource() == runAction){
			
			ComandoExecutarLinhaDeComando comando = new ComandoExecutarLinhaDeComando();
			comando.executarComando();
			
		}
		else if (e.getSource() == actionMenuCorTxtArea){
			
			ComandoCorPlanoDeFundo comando = new ComandoCorPlanoDeFundo(getTextArea(), index, controleAbas, corFundo);
			comando.executarComando();
			corFundo = comando.getCorFundo();
			
			getIndex();
		}
		else if (e.getSource() == actionMenuCorFonte){
			
			ComandoCorTexto comando = new ComandoCorTexto(getTextArea(), index, controleAbas, corTexto);
			comando.executarComando();
			corTexto = comando.getCorTexto();
		
			getIndex();
			
		}	
		else if (e.getSource() == printAction){
			
			ComandoImprimir comando = new ComandoImprimir(getTextArea());
			comando.executarComando();

		}
		
		else{}

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (index != -1){
			getIndex();
			if(((AreaDeTextoComScroll)controleAbas.getComponentAt(index)).getUndoRedo() == false){
			adicionaListener();
			}
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
