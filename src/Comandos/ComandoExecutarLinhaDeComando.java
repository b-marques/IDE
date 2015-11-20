package Comandos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import Componentes.AreaDeTexto;
import Componentes.AreaDeTextoComScroll;
import Componentes.AutoMakefile;

import GUI.MainWindow;

public class ComandoExecutarLinhaDeComando implements Comandos {

	public ComandoExecutarLinhaDeComando() {

	}

	@Override
	public void executarComando() {
		
		String stringTemp = Character.toString(System.getProperty("os.name").charAt(0));
		
		
		if (stringTemp.equalsIgnoreCase("w")){
			String classePrincipal = JOptionPane.showInputDialog("Nome da classe Principal:");
			Process p = null;
			try {
				p = Runtime.getRuntime().exec("javac -d classes *.java");
			} catch (IOException e5) {
				e5.printStackTrace();
			}
			  try {
				p.waitFor();
			} catch (InterruptedException e4) {
				e4.printStackTrace();
			}

			  String line;
			  String message = "";

			  BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			  try {
				while((line = error.readLine()) != null){
				      message += line + "\n";
				  }
				if (message.equals(""))
					message += "Compilação concluída com sucesso!";
				
				((AreaDeTexto)((AreaDeTextoComScroll)MainWindow.consolePane.getComponentAt(0)).getViewport().getView()).setText(message);
			
			} catch (IOException e3) {
				e3.printStackTrace();
			}
			  
			try {
				p.waitFor();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				p = Runtime.getRuntime().exec("java -cp classes " + classePrincipal);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			
			new AutoMakefile();
			
			Process p = null;
			try {
				p = Runtime.getRuntime().exec("make run");
			} catch (IOException e5) {
				e5.printStackTrace();
			}
			  try {
				p.waitFor();
			} catch (InterruptedException e4) {
				e4.printStackTrace();
			}

			  String line;
			  String message = "";

			  BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			  try {
				while((line = error.readLine()) != null){
				      message += line + "\n";
				  }
				if (message.equals(""))
					message += "Compilação concluída com sucesso!";
				
				((AreaDeTexto)((AreaDeTextoComScroll)MainWindow.consolePane.getComponentAt(0)).getViewport().getView()).setText(message);
			
			} catch (IOException e3) {
				e3.printStackTrace();
			}
		}
	}

}