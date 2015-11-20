package Comandos;
import java.awt.print.PrinterException;

import Componentes.AreaDeTexto;

public class ComandoImprimir implements Comandos {
	
	private AreaDeTexto areaDeTexto;
	
	public ComandoImprimir(AreaDeTexto areaDeTexto) {
		this.areaDeTexto = areaDeTexto;
	}

	@Override
	public void executarComando() {

		try {
			areaDeTexto.print();
		} catch (PrinterException e1) {
			e1.printStackTrace();
		}
		
	}

}
