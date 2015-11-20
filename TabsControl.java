import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

public class TabsControl extends JTabbedPane implements MouseListener {
	
	private MainWindow window;
	
	public TabsControl(MainWindow mainWindow) {
		
		addMouseListener(this);
		window = mainWindow;
		window.getContentPane().add(this);
		
		revalidate();
	}
	
	public void newTab() {
		
		AreaDeTextoComScroll txtArea = new AreaDeTextoComScroll();
		this.addTab("New Tab",txtArea);
		window.getContentPane().add(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
