package Comandos;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import Componentes.AreaDeTexto;
import Componentes.AreaDeTextoComScroll;
import Componentes.TabsControl;

public class ComandoSalvar implements Comandos {

	private AreaDeTexto areaDeTexto;
	private AreaDeTextoComScroll areaDeTextoComScroll;
	private TabsControl controleAbas;
	private int index;
	
	public ComandoSalvar(AreaDeTexto areaDeTexto, AreaDeTextoComScroll areaDeTextoComScroll, TabsControl controleAbas, int index){
		this.areaDeTexto = areaDeTexto;
		this.areaDeTextoComScroll = areaDeTextoComScroll;
		this.controleAbas = controleAbas;
		this.index = index;
	}
	
	@Override
	public void executarComando() {
		if(areaDeTextoComScroll.isSaved() == true){
		    PrintWriter printWriter = null;
			try {
				printWriter = new PrintWriter (areaDeTextoComScroll.getFilename());
			} catch (IOException evt) {
				evt.printStackTrace();
			}
			
			//Separa o textArea em linhas
			String[] lines = areaDeTexto.getText().split("\\n");
			
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
			String[] lines = areaDeTexto.getText().split("\\n");
			
			for (int i=0; i<lines.length; i++){
				printWriter.println(lines[i]);
			}
			areaDeTextoComScroll.setSaved(true);
			areaDeTextoComScroll.setFilename(nome_arquivo);
			controleAbas.setTitleAt(index, ((AreaDeTextoComScroll)controleAbas.getComponentAt(index)).getFilename());
			printWriter.close();
		}
		
	}
	
}
