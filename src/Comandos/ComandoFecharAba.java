package Comandos;

import Componentes.AreaDeTexto;
import Componentes.AreaDeTextoComScroll;
import Componentes.TabsControl;

public class ComandoFecharAba implements Comandos {

	private TabsControl controleAbas;
	private int index;
	private AreaDeTextoComScroll areaDeTextoComScroll;
	
	public ComandoFecharAba(TabsControl controleAbas, int index, AreaDeTextoComScroll areaDeTextoComScroll) {
		this.controleAbas = controleAbas;
		this.index = index;
		this.areaDeTextoComScroll = areaDeTextoComScroll;
	}
	
	@Override
	public void executarComando() {
		
		
		if (areaDeTextoComScroll.isSaved() || ((AreaDeTexto)((AreaDeTextoComScroll)controleAbas.getComponentAt(index))
				.getViewport()
				.getView()).getText().equals("")){
			controleAbas.remove((AreaDeTextoComScroll)controleAbas.getComponentAt(index));
		}
		else{
			ComandoSalvarComo comando = new ComandoSalvarComo(index, areaDeTextoComScroll, controleAbas);
			comando.executarComando();
			controleAbas.remove((AreaDeTextoComScroll)controleAbas.getComponentAt(index));
		}
	}
}
