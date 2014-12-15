package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import logica.Ambulancia;
import logica.Emergencia;
import logica.Hospital;
import logica.Paciente;
import logica.ServicioEmergencia;

public class NuevaEmergencia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private Paciente p=null;
	private JTextField textField_7;
	private JTextField textField_8;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevaEmergencia dialog = new NuevaEmergencia();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevaEmergencia() {
		setBounds(100, 100, 383, 556);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI: ");
		lblDni.setBounds(34, 23, 46, 14);
		contentPanel.add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(34, 51, 46, 14);
		contentPanel.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(67, 20, 115, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(90, 48, 92, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPaciente = new JLabel("Paciente: ");
		lblPaciente.setBounds(34, 93, 61, 14);
		contentPanel.add(lblPaciente);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(90, 105, 280, 48);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnComprobar = new JButton("Comprobar");
		btnComprobar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(textField.getText()!=null){
					try {
					ServicioEmergencia s= new ServicioEmergencia();
					 p=s.buscarP(textField.getText());
					if(p==null){
						AltaPacienteJDialog a=new AltaPacienteJDialog();
						a.show();
						dispose();
					}
					else{
					textField_2.setText(p.getDni()+"  "+p.getNombre()+"  "+p.getApellidos()+"  "+p.getDireccion()+"  "+p.getEdad()+"  "+p.getTelefono()+"  "+p.getSexo());
					}
					} catch (LogicaExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				else{
				try {
				ServicioEmergencia s= new ServicioEmergencia();
				 p=s.buscarP(textField_1.getText());
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
			}}
		}
			
		});
		btnComprobar.setBounds(274, 174, 91, 23);
		contentPanel.add(btnComprobar);
		
		JLabel lblLatitud = new JLabel("Latitud: ");
		lblLatitud.setBounds(34, 231, 46, 14);
		contentPanel.add(lblLatitud);
		
		JLabel lblLongitud = new JLabel("Longitud: ");
		lblLongitud.setBounds(19, 256, 61, 14);
		contentPanel.add(lblLongitud);
		
		textField_3 = new JTextField();
		textField_3.setBounds(90, 228, 86, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(90, 253, 86, 20);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblFecha = new JLabel("fecha: ");
		lblFecha.setBounds(34, 299, 46, 14);
		contentPanel.add(lblFecha);
		
		JLabel lblHora = new JLabel("hora: ");
		lblHora.setBounds(23, 324, 46, 14);
		contentPanel.add(lblHora);
		
		textField_5 = new JTextField();
		textField_5.setBounds(77, 296, 86, 20);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(77, 321, 86, 20);
		contentPanel.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblHospitalAsignado = new JLabel("Hospital Asignado: ");
		lblHospitalAsignado.setBounds(34, 384, 92, 14);
		contentPanel.add(lblHospitalAsignado);
		
		JLabel lblAmbulanciaAsignada = new JLabel("Ambulancia Asignada: ");
		lblAmbulanciaAsignada.setBounds(34, 427, 115, 14);
		contentPanel.add(lblAmbulanciaAsignada);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(136, 381, 229, 20);
		contentPanel.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setBounds(144, 424, 221, 20);
		contentPanel.add(textField_8);
		textField_8.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						ServicioEmergencia e;
						try {
							e = new ServicioEmergencia();
						List<Emergencia>pen=e.listaEmergencias();
						Iterator<Emergencia> es=pen.iterator();
						
						
						String algo = es.next().getCodEmergencia()+1;
						float latitud=Float.parseFloat(textField_3.getText());
						float longitud=Float.parseFloat(textField_4.getText());
						Emergencia m= new Emergencia(algo, longitud, latitud, textField_6.getText(), textField_5.getText());
						m.setPaciente(p);
						
						int cos=e.ambMinima(longitud, latitud);
						Ambulancia a=e.buscarA(cos);
						m.setAmb(a);
						String ey=e.hospMinimo(longitud, latitud);
						Hospital h=e.buscarH(ey);
						m.setHosp(h);
						e.anyadir(m);
						//textField_7.setText(h.getNombre()+" "+h.getDireccion()+" "+h.getLatitud()+" "+h.getLongitud());
						//textField_8.setText(a.getEquipo()+" "+a.getNumRegistro()+" "+a.getDisp()+" "+a.getLatitud()+" "+a.getLongitud());
						} catch (LogicaExcepcion e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DAOExcepcion e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
						EmergenciasApp e= new EmergenciasApp();
						dispose();
						e.frame.show();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
