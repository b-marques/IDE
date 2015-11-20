package Comandos;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;

import Componentes.AreaDeTexto;
import Componentes.AreaDeTextoComScroll;
import Componentes.TabsControl;

@SuppressWarnings("serial")
public class ComandoAbrir extends JDialog implements Comandos {

	private int index;
	private TabsControl controleAbas;
	private AreaDeTexto areaDeTexto;
	private Color corTexto, corFundo;
	
	public ComandoAbrir(int index, TabsControl controleAbas, AreaDeTexto areaDeTexto, Color corTexto, Color corFundo) {
		this.index = index;
		this.controleAbas = controleAbas;
		this.areaDeTexto = areaDeTexto;
		this.corTexto = corTexto;
		this.corFundo = corFundo;
	}


	@Override
	public void executarComando() {
		
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
//			try {
//				line = in.readLine();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
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
				if(line !=null)
					resultado += line+"\n";
			}
			((JTextPane)((AreaDeTextoComScroll)controleAbas.getComponentAt(index))
					.getViewport()
					.getView())
					.setText(resultado);
			
			((AreaDeTextoComScroll)controleAbas.getComponentAt(index)).setSaved(true);
			controleAbas.setTitleAt(index, file.getName());
			areaDeTexto.setForeground(corTexto);
			areaDeTexto.setBackground(corFundo);
		
		}
	}
}
