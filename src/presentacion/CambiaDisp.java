package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logica.Ambulancia;
import logica.ServicioEmergencia;

import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CambiaDisp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CambiaDisp dialog = new CambiaDisp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CambiaDisp() {
		setBounds(100, 100, 450, 358);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNumero = new JLabel("Numero: ");
		lblNumero.setBounds(31, 40, 68, 14);
		contentPanel.add(lblNumero);
		
		textField = new JTextField();
		textField.setBounds(109, 37, 165, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNuevaDisponibilidad = new JLabel("Nueva Disponibilidad: ");
		lblNuevaDisponibilidad.setBounds(53, 91, 105, 14);
		contentPanel.add(lblNuevaDisponibilidad);
		
		JRadioButton rdbtnDisponible = new JRadioButton("Disponible");
		rdbtnDisponible.setBounds(72, 124, 109, 23);
		contentPanel.add(rdbtnDisponible);
		
		final JRadioButton rdbtnOcupada = new JRadioButton("Ocupada");
		rdbtnOcupada.setBounds(183, 124, 109, 23);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnOcupada);
		bg.add(rdbtnDisponible);
		contentPanel.add(rdbtnOcupada);
		
		JButton button = new JButton("OK");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					ServicioEmergencia e=new ServicioEmergencia();
					boolean h;
					if(rdbtnOcupada.isSelected()){h=false;}
					else{h=true;}
					e.setDisp(Integer.parseInt(textField.getText()), h);
					Ambulancia a=e.buscarA(Integer.parseInt(textField.getText()));
					String t;
					if(a.getDisp()){t= a.getEquipo()+" "+a.getLatitud()+" "+a.getLongitud()+" "+a.getNumRegistro()+" Disponible";}
					else {t= a.getEquipo()+" "+a.getLatitud()+" "+a.getLongitud()+" "+a.getNumRegistro()+" Ocupada";}
					textField_1.setText(t);
				} catch (LogicaExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DAOExcepcion e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setActionCommand("OK");
		button.setBounds(311, 297, 49, 23);
		contentPanel.add(button);
		
		JButton button_1 = new JButton("Cancel");
			button_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					dispose();
					EmergenciasApp e=new EmergenciasApp();
					e.frame.show();
				}
		});
		button_1.setActionCommand("Cancel");
		button_1.setBounds(365, 297, 67, 23);
		contentPanel.add(button_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(72, 207, 247, 41);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAmbulancia = new JLabel("Ambulancia:");
		lblAmbulancia.setBounds(31, 175, 68, 14);
		contentPanel.add(lblAmbulancia);
	}
}
