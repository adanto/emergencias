package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logica.Especialidad;
import logica.Hospital;
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
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaEspecialidad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ServicioEmergencia h;
	private int count=0;
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
		setBounds(100, 100, 378, 236);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblEspecialidades = new JLabel("Especialidades:");
			lblEspecialidades.setBounds(26, 80, 77, 14);
			contentPanel.add(lblEspecialidades);
		}
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(79, 105, 180, 22);
		contentPanel.add(comboBox);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EmergenciasApp h= new EmergenciasApp();
				dispose();
				h.frame.show();
			}
		});
		
		btnCancel.setBounds(269, 175, 91, 23);
		contentPanel.add(btnCancel);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(79, 23, 158, 17);
		contentPanel.add(comboBox_1);
		h=new ServicioEmergencia();
		List<Hospital> hospital= h.listarHospital();
		Iterator<Hospital> hosp=hospital.iterator();
		while(hosp.hasNext()){
			comboBox_1.addItem(hosp.next().getNombre());
		}
		
		JLabel lblHospital = new JLabel("Hospital: ");
		lblHospital.setBounds(26, 26, 61, 14);
		contentPanel.add(lblHospital);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(count>1){comboBox.removeAll();}
				try {
					count++;
					String nom=(String) comboBox_1.getSelectedItem();
					
					h=new ServicioEmergencia();
					List<Especialidad> a= h.listarEspecialidad(nom);
					Iterator<Especialidad> c=a.iterator();
					
					while(c.hasNext()){
						comboBox.addItem(c.next().getNombre());
					}
				} catch (LogicaExcepcion e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOk.setBounds(171, 175, 91, 23);
		contentPanel.add(btnOk);
		
		
		
			
		
	}
}
