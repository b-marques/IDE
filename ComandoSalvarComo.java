import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class ComandoSalvarComo implements Comandos {

	private int index;
	private AreaDeTextoComScroll areaDeTextoComScroll;
	private TabsControl controleAbas;
	
	public ComandoSalvarComo(int index, AreaDeTextoComScroll areaDeTextoComScroll, TabsControl controleAbas) {
		this.index = index;
		this.areaDeTextoComScroll = areaDeTextoComScroll;
		this.controleAbas = controleAbas;
	}

	@Override
	public void executarComando() {
		PrintWriter printWriter = null;
		String nome_arquivo = null;
		try {
			nome_arquivo = JOptionPane.showInputDialog("Salvar Como:");
			printWriter = new PrintWriter (nome_arquivo);
		} catch (IOException evt) {
			evt.printStackTrace();
		}
		
		//Separa o textArea em linhas
		String[] lines = ((JTextPane)(areaDeTextoComScroll).getViewport()
															.getView())
															.getText().split("\\n");
		
		for (int i=0; i<lines.length; i++){
			printWriter.println(lines[i]);
		}
		areaDeTextoComScroll.setSaved(true);
		areaDeTextoComScroll.setFilename(nome_arquivo);
		controleAbas.setTitleAt(index, areaDeTextoComScroll.getFilename());
		
		printWriter.close();
	}
}
