package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import logica.Paciente;
import logica.ServicioEmergencia;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import excepciones.LogicaExcepcion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.List;

public class ConsultaPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	public int soy=1;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConsultaPaciente dialog = new ConsultaPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConsultaPaciente() {
		setBounds(100, 100, 364, 227);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblDni = new JLabel("DNI: ");
			lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDni.setBounds(60, 27, 46, 14);
			contentPanel.add(lblDni);
		}
		{
			textField = new JTextField();
			textField.setBounds(116, 24, 150, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblPaciente = new JLabel("Paciente: ");
			lblPaciente.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPaciente.setBounds(44, 81, 62, 14);
			contentPanel.add(lblPaciente);
			
		}
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					ServicioEmergencia s= new ServicioEmergencia();
					Paciente p=s.buscarP(textField.getText());
					if(p==null){
						AltaPacienteJDialog a=new AltaPacienteJDialog();
						a.show();
						dispose();
					}
					else{
					textField_1.setText(p.getDni()+"  "+p.getNombre()+"  "+p.getApellidos()+"  "+p.getDireccion()+"  "+p.getEdad()+"  "+p.getTelefono()+"  "+p.getSexo());
					}
					} catch (LogicaExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setActionCommand("OK");
		button.setBounds(191, 152, 49, 23);
		contentPanel.add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EmergenciasApp h=new EmergenciasApp();
				h.frame.show();
				dispose();
			}
		});
		button_1.setActionCommand("Cancel");
		button_1.setBounds(250, 152, 67, 23);
		contentPanel.add(button_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(116, 78, 201, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		List list = new List();
		list.setBounds(207, 64, 110, 60);
		contentPanel.add(list);
	}
	public void mostrar(String DNI) throws InterruptedException{
		ServicioEmergencia s;
		try {
			s = new ServicioEmergencia();
			Paciente p=s.buscarP(DNI);
			textField_1.setText(p.getDni()+"  "+p.getNombre()+"  "+p.getApellidos()+"  "+p.getDireccion()+"  "+p.getEdad()+"  "+p.getTelefono()+"  "+p.getSexo());
			
		} catch (LogicaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
}
