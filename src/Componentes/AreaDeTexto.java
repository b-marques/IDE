package Componentes;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextPane;
import javax.swing.undo.UndoManager;

@SuppressWarnings("serial")
public class AreaDeTexto extends JTextPane{

	private UndoManager undoManager;
	
	public AreaDeTexto () {
		
		undoManager = new UndoManager();
		
		setFont(new Font("TimesNewRoman",Font.PLAIN,13));
//		setBackground(new Color(0,0,0,0)); //COR DO FUNDO
//		setForeground(Color.green); //COR DA LETRA
		
		CodeDocument doc = new CodeDocument();
	    Map<String,Color> keywords = new HashMap<String,Color>();
	   
	    Color comment = new Color(63,197,95);
	    Color javadoc = new Color(63,95,191);
	    Color annotation = new Color(100,100,100);
	    doc.setCommentColor(comment);
	    doc.setJavadocColor(javadoc);
	    doc.setAnnotationColor(annotation);
	    
	    Color defColor = new Color(127,0,85);
	    keywords.put("abstract",defColor);
	    keywords.put("boolean",defColor);
	    keywords.put("break",defColor);
	    keywords.put("byte",defColor);
	    keywords.put("case",defColor);
	    keywords.put("catch",defColor);
	    keywords.put("char",defColor);
	    keywords.put("class",defColor);
	    keywords.put("continue",defColor);
	    keywords.put("default",defColor);
	    keywords.put("do",defColor);
	    keywords.put("double",defColor);
	    keywords.put("enum",defColor);
	    keywords.put("extends",defColor);
	    keywords.put("else",defColor);
	    keywords.put("false",defColor);
	    keywords.put("final",defColor);
	    keywords.put("finally",defColor);
	    keywords.put("float",defColor);
	    keywords.put("for",defColor);
	    keywords.put("if",defColor);
	    keywords.put("implements",defColor);
	    keywords.put("import",defColor);
	    keywords.put("instanceof",defColor);
	    keywords.put("int",defColor);
	    keywords.put("interface",defColor);
	    keywords.put("long",defColor);
	    keywords.put("native",defColor);
	    keywords.put("new",defColor);
	    keywords.put("null",defColor);
	    keywords.put("package",defColor);
	    keywords.put("private",defColor);
	    keywords.put("protected",defColor);
	    keywords.put("public",defColor);
	    keywords.put("return",defColor);
	    keywords.put("short",defColor);
	    keywords.put("static",defColor);
	    keywords.put("super",defColor);
	    keywords.put("switch",defColor);
	    keywords.put("synchronized",defColor);
	    keywords.put("this",defColor);
	    keywords.put("throw",defColor);
	    keywords.put("throws",defColor);
	    keywords.put("transient",defColor);
	    keywords.put("true",defColor);
	    keywords.put("try",defColor);
	    keywords.put("void",defColor);
	    keywords.put("volatile",defColor);
	    keywords.put("while",defColor);
	    doc.setKeywords(keywords);
	    this.setDocument(doc);
		
		revalidate();
	}
	
	public void setUndoManager(UndoManager undoManager) {
		this.undoManager = undoManager;
	}
	
	public UndoManager getUndoManager() {
		return undoManager;
	}
	
	
	
}
