import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class PaletaDeCoresTexto extends JDialog implements ActionListener {

	private JButton[] botao;
	private Color corBotaoClicado = (Color.BLUE);
	private AreaDeTexto areaDeTexto;
	
	public PaletaDeCoresTexto(MainWindow window, AreaDeTexto areaDeTexto) {
		
		this.areaDeTexto = areaDeTexto;
		
		JPanel panel = new JPanel();
		botao = new JButton[13];
		panel.setLayout(new GridLayout(3,0));
		
		setLocation(window.getX() + window.getWidth()-145, window.getY()+20);
		
		for (int i = 0; i<botao.length; i++){
			botao[i] = new JButton();
			botao[i].addActionListener(this);
			panel.add(botao[i]);
		}	
        botao[0].setBackground(Color.BLACK);
        botao[1].setBackground(Color.BLUE);
        botao[2].setBackground(Color.CYAN);
        botao[3].setBackground(Color.DARK_GRAY);
        botao[4].setBackground(Color.GRAY);
        botao[5].setBackground(Color.GREEN);
        botao[4].setForeground(Color.GRAY);
        botao[6].setBackground(Color.LIGHT_GRAY);
        botao[7].setBackground(Color.MAGENTA);
        botao[8].setBackground(Color.ORANGE);
        botao[9].setBackground(Color.PINK);
        botao[10].setBackground(Color.RED);
        botao[11].setBackground(Color.WHITE);
        botao[12].setBackground(Color.YELLOW);
        
        panel.setSize(100, 100);
        add(panel);
        setSize(panel.getSize());
        setModal(true);
        setVisible(true);
        
	}
	
	public Color getCorBotaoClicado() {
		return corBotaoClicado;
	}
	
	public void setCorBackground(Color cor) {
		areaDeTexto.setBackground(cor);
	}
	
	public void setCorForeground(Color cor) {
		areaDeTexto.setForeground(cor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setCorForeground(((Component) e.getSource()).getBackground());
		setVisible(false);
	}
}