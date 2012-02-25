package visao;

import controle.ControleTetris;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author KALEU
 */
public class ItemMenuNivel extends JMenu implements ActionListener {

	private ControleTetris controle;

	private String[] niveis = {"Mané", "Tosco", "Marreco", "Mais ou Menos", "Chuck Norris"};
	private JRadioButtonMenuItem itensNiveis[];
	private ButtonGroup grupoNiveis;

	public ItemMenuNivel(ControleTetris controle) {
		super("Nível");
		this.controle = controle;

		grupoNiveis = new ButtonGroup();
		itensNiveis = new JRadioButtonMenuItem[niveis.length];

		this.criaBotoesNivel();
		itensNiveis[0].setSelected(true);
	}

	private void criaBotoesNivel(){
		for(int i=0; i<niveis.length; i++) {
			itensNiveis[i] = new JRadioButtonMenuItem(niveis[i]);
			this.add(itensNiveis[i]);
			grupoNiveis.add(itensNiveis[i]);

			itensNiveis[i].addActionListener(this);
		}
	}

	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<itensNiveis.length; i++){
			if(itensNiveis[i].isSelected()) {
				this.controle.setNivel(i);
			}
		}

	}




}
