
package visao;

import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Sobre extends JFrame {
		public Sobre() {
		super("O Chuck Tetris");
		Icon chuck = new ImageIcon( getClass().getResource("/resources/img/chuck.jpg") );
		JLabel fotoChuck = new JLabel(chuck);
		setLayout(new FlowLayout());
		add(fotoChuck);
		add(new JLabel("Uma história de sucesso..."));
		add(new JLabel("Esse jogo mudou minha vida!"));
		setSize(300,460);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}