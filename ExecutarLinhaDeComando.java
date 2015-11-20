import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ExecutarLinhaDeComando implements Comandos {

	public ExecutarLinhaDeComando() {

	}

	@Override
	public void executarComando() {
		
		String stringTemp = Character.toString(System.getProperty("os.name").charAt(0));
		
		if (stringTemp.equalsIgnoreCase("w")){
//			Process p = null;
//			try {
//				p = Runtime.getRuntime().exec("javac -d classes *.java");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
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
			  String message = null;

			  BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			  try {
				while((line = error.readLine()) != null){
				      message += line + "\n";
				  }
				JDialog dialog = new JDialog();
				dialog.setTitle("Console");
				dialog.setModal(true);
				JPanel panel = new JPanel();
				panel.add(new JLabel(message));
				panel.setVisible(true);
				
			} catch (IOException e3) {
				e3.printStackTrace();
			}
			  
			try {
				p.waitFor();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				p = Runtime.getRuntime().exec("java -cp classes "+JOptionPane.showInputDialog("Nome da classe Principal:"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			AutoMakefile makefile = new AutoMakefile();
			
			Process p = null;
			try {
				p = Runtime.getRuntime().exec("make run");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}