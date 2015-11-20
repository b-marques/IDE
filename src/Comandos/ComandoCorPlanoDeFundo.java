package Comandos;
import java.awt.Color;

import Componentes.AreaDeTexto;
import Componentes.TabsControl;
import GUI.PaletaDeCores;

public class ComandoCorPlanoDeFundo implements Comandos {

	@SuppressWarnings("unused")
	private int index;
	private Color corFundo;
	private AreaDeTexto textArea;
	private TabsControl controleAbas;
	
	public ComandoCorPlanoDeFundo(AreaDeTexto textArea, int index, TabsControl controleAbas, Color corFundo) {
	this.textArea = textArea;
	this.index = index;
	this.controleAbas = controleAbas;
	this.corFundo = corFundo;
	}
	
	public Color getCorFundo() {
		return corFundo;
	}
	
	@Override
	public void executarComando() {

		new PaletaDeCores(textArea, 1);
		
		corFundo = textArea.getBackground();
		
		for (int i = 0; i<controleAbas.getComponentCount(); i++){
			index = i;
				textArea.setBackground(corFundo);
			}
	}
}
