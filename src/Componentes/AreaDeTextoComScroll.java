package Componentes;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class AreaDeTextoComScroll extends JScrollPane {

	private String filename;
	private boolean saved, undoRedo;

	public AreaDeTextoComScroll() {
		
		super(new AreaDeTexto());
		setSaved(false);
		setUndoRedo(false);
		revalidate();
	}
	
	public boolean isSaved() {
		return saved;
	}
	
	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public boolean getUndoRedo() {
		return undoRedo;
	}
	
	public void setUndoRedo(boolean undoRedo) {
		this.undoRedo = undoRedo;
	}
}
