import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;

public class AutoMakefile {

	private String makefile;
	
	public AutoMakefile(){
		
		makefile = ("JFLAGS = -g\n"
				+ "JC = javac\n"
				+ "JVM = java\n"
				+ "FILE=\n"
				+ ".SUFFIXES: .java .class\n"
				+ ".java.class:\n"
				+ "	$(JC) $(JFLAGS) $*.java\n\n"
				+ "CLASSES = \\\n");
		
		File pasta = new File(System.getProperty("user.dir"));
		File[] listOfFiles = pasta.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()){
				
				String nomeArquivo = listOfFiles[i].getName();
				String extensaoArquivo = ""+listOfFiles[i].getName().charAt(nomeArquivo.length()-5)
										+listOfFiles[i].getName().charAt(nomeArquivo.length()-4)
										+listOfFiles[i].getName().charAt(nomeArquivo.length()-3)
										+listOfFiles[i].getName().charAt(nomeArquivo.length()-2)
										+listOfFiles[i].getName().charAt(nomeArquivo.length()-1);
				
				if(extensaoArquivo.equals(".java")){
					makefile += ("	"+nomeArquivo+" \\\n");
				}
			}  
		}
		StringBuilder sb = new StringBuilder(makefile);
		sb.deleteCharAt(makefile.length()-2);
		
		makefile = sb.toString();
		
		makefile += ("\nMAIN = "
					+JOptionPane.showInputDialog("Nome da classe Principal")+"\n\n"
					+"default: classes\n\n"
					+"classes: $(CLASSE:.java=.class)\n\n"
					+"run: $(MAIN).class\n"
					+"	$(JVM) $(MAIN)\n\n"
					+"clean:\n"
					+"	$(RM) *.class");
		
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter ("makefile");
		} catch (IOException evt) {
			evt.printStackTrace();
		}
		
//		Separa o textArea em linhas
//		String[] lines = makefile.getText().split("\\n");
//		
//		for (int i=0; i<makefile.length; i++){
			printWriter.print(makefile);
			printWriter.close();
//		}
	}
}
