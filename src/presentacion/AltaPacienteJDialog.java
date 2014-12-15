package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

import excepciones.LogicaExcepcion;
import logica.ServicioEmergencia;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logica.Paciente;

public class AltaPacienteJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private EmergenciasApp a;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaPacienteJDialog dialog = new AltaPacienteJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaPacienteJDialog() {
		setBounds(100, 100, 450, 290);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI: ");
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setBounds(58, 29, 46, 14);
		contentPanel.add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(58, 54, 46, 14);
		contentPanel.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellidos.setBounds(55, 79, 49, 14);
		contentPanel.add(lblApellidos);
		
		JLabel lblSexo = new JLabel("Sexo: ");
		lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSexo.setBounds(58, 104, 46, 14);
		contentPanel.add(lblSexo);
		
		JLabel lblDireccion = new JLabel("Direccion: ");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(32, 129, 72, 14);
		contentPanel.add(lblDireccion);
		
		JLabel lblEdad = new JLabel("Edad: ");
		lblEdad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEdad.setBounds(58, 154, 46, 14);
		contentPanel.add(lblEdad);
		
		JLabel lblNewLabel = new JLabel("Telefono: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(47, 179, 57, 14);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(119, 26, 164, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(119, 51, 164, 20);
		contentPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(119, 76, 164, 20);
		contentPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(119, 176, 96, 20);
		contentPanel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(119, 126, 164, 20);
		contentPanel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(119, 151, 96, 20);
		contentPanel.add(textField_5);
		
		final JRadioButton rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setBounds(117, 100, 109, 23);
		contentPanel.add(rdbtnHombre);
		
		JRadioButton rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setBounds(239, 100, 109, 23);
		contentPanel.add(rdbtnMujer);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnHombre);
		bg.add(rdbtnMujer);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("   OK   ");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						char H; 
						if(rdbtnHombre.isSelected()){H='H';}
						else{H='M';}
						int edad=Integer.parseInt(textField_5.getText());
						int telf=Integer.parseInt(textField_3.getText());
						Paciente p= new Paciente(textField.getText(), textField_1.getText(), textField_2.getText(), textField_4.getText(),  telf, edad, H);
						ServicioEmergencia s;
						try {
							s = new ServicioEmergencia();
							s.anyadir(p);
						} catch (LogicaExcepcion e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						ConsultaPaciente c=new ConsultaPaciente();
						dispose();
						c.show();
						try {
							c.mostrar(textField.getText());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						dispose();
						a=new EmergenciasApp();
						a.frame.show();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
