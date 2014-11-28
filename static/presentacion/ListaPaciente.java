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

public class ListaPaciente extends JFrame {

	private JPanel contentPane;
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
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		panel.add(table);
		DefaultTableModel mo=(DefaultTableModel) table.getModel();
		
		ServicioEmergencia s=new ServicioEmergencia();
		ConsultaPaciente c=new ConsultaPaciente();
		
		Iterator<Paciente> pa=s.getPatients();
		while(pa.hasNext()){
			Paciente p=pa.next();
			mo.addRow(new Object[]{p.getDni(),p.getNombre(),p.getApellidos(),p.getDireccion(),p.getEdad(),p.getTelefono(),p.getSexo()});
		}
	}
}


