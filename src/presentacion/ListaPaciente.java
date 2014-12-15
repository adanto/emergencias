package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JTable;

import java.awt.GridBagConstraints;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import excepciones.LogicaExcepcion;
import logica.Paciente;
import logica.ServicioEmergencia;
import javax.swing.ListSelectionModel;
import java.awt.GridLayout;
import javax.swing.JScrollPane;

public class ListaPaciente extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaPaciente frame = new ListaPaciente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws LogicaExcepcion 
	 */
	public ListaPaciente() throws LogicaExcepcion {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"DNI", "Nombre", "Apellidos", "Dirreccion", "Edad", "Sexo", "Telefono"
			}
		));
		scrollPane.setViewportView(table);
		
		ServicioEmergencia s=new ServicioEmergencia();
		ConsultaPaciente c=new ConsultaPaciente();
		DefaultTableModel mo=(DefaultTableModel) table.getModel();
		
		Iterator<Paciente> pa=s.getPatients();
		while(pa.hasNext()){
			Paciente p=pa.next();
			mo.addRow(new Object[]{p.getDni(),p.getNombre(),p.getApellidos(),p.getDireccion(),p.getEdad(),p.getTelefono(),p.getSexo()});
		}
	}
}


