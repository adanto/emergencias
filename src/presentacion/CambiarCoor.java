package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logica.Ambulancia;
import logica.ServicioEmergencia;
import javax.swing.SwingConstants;

public class CambiarCoor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CambiarCoor dialog = new CambiarCoor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CambiarCoor() {
		setBounds(100, 100, 340, 296);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNumero = new JLabel("Numero: ");
			lblNumero.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNumero.setBounds(48, 33, 80, 14);
			contentPanel.add(lblNumero);
		}
		{
			JLabel lblLatitud = new JLabel("Latitud: ");
			lblLatitud.setHorizontalAlignment(SwingConstants.RIGHT);
			lblLatitud.setBounds(48, 58, 80, 14);
			contentPanel.add(lblLatitud);
		}
		{
			JLabel lblLongitud = new JLabel("Longitud: ");
			lblLongitud.setHorizontalAlignment(SwingConstants.RIGHT);
			lblLongitud.setBounds(37, 83, 91, 14);
			contentPanel.add(lblLongitud);
		}
		{
			textField = new JTextField();
			textField.setBounds(138, 30, 86, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(138, 55, 86, 20);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(138, 80, 86, 20);
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			textField_3 = new JTextField();
			textField_3.setEditable(false);
			textField_3.setBounds(32, 159, 255, 38);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			JLabel lblAmbulanciaModificada = new JLabel("Ambulancia Modificada:");
			lblAmbulanciaModificada.setHorizontalAlignment(SwingConstants.CENTER);
			lblAmbulanciaModificada.setBounds(32, 139, 153, 14);
			contentPanel.add(lblAmbulanciaModificada);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("   OK   ");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						try {
							ServicioEmergencia s=new ServicioEmergencia();
							int numero=Integer.parseInt(textField.getText());
							float latitud=Float.parseFloat(textField_1.getText());
							float longitud=Float.parseFloat(textField_2.getText());
							s.cambiarCoor(numero, latitud, longitud);
							Ambulancia a = s.buscarA(Integer.parseInt(textField.getText()));
							String t;
							if(a.getDisp()){t= a.getEquipo()+" "+a.getLatitud()+" "+a.getLongitud()+" "+a.getNumRegistro()+" Disponible";}
							else {t= a.getEquipo()+" "+a.getLatitud()+" "+a.getLongitud()+" "+a.getNumRegistro()+" Ocupada";}
							textField_3.setText(t);
						} catch (LogicaExcepcion e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (DAOExcepcion e) {
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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
