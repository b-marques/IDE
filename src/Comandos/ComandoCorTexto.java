package Comandos;
import java.awt.Color;

import Componentes.AreaDeTexto;
import Componentes.TabsControl;
import GUI.PaletaDeCores;

public class ComandoCorTexto implements Comandos {

	@SuppressWarnings("unused")
	private int index;
	private Color corTexto;
	private AreaDeTexto textArea;
	private TabsControl controleAbas;
	
	public ComandoCorTexto(AreaDeTexto textArea, int index, TabsControl controleAbas, Color corTexto) {
	this.textArea = textArea;
	this.index = index;
	this.controleAbas = controleAbas;
	this.corTexto = corTexto;
	}
	
	public Color getCorTexto() {
		return corTexto;
	}
	
	@Override
	public void executarComando() {

		new PaletaDeCores(textArea, 0);
		
		corTexto = textArea.getForeground();
		
		for (int i = 0; i<controleAbas.getComponentCount(); i++){
			index = i;
				textArea.setForeground(corTexto);
			}
	}

}
