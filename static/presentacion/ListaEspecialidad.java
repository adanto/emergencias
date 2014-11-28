package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Iterator;

import logica.Paciente;
import logica.ServicioEmergencia;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;

import excepciones.LogicaExcepcion;
import javax.swing.JComboBox;

public class ListaEspecialidad extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaEspecialidad dialog = new ListaEspecialidad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws LogicaExcepcion 
	 */
	public ListaEspecialidad() throws LogicaExcepcion {
		setBounds(100, 100, 378, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblEspecialidades = new JLabel("Especialidades:");
			lblEspecialidades.setBounds(27, 22, 77, 14);
			contentPanel.add(lblEspecialidades);
		}
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(76, 52, 180, 22);
		contentPanel.add(comboBox);
		
		for(int i=0; i<5;i++){
			comboBox.addItem(i);
		}
	}
}
