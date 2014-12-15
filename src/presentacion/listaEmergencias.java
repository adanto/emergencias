package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;

import java.awt.GridLayout;
import java.util.Iterator;

import logica.Emergencia;
import logica.Paciente;
import logica.ServicioEmergencia;
import logica.Sintoma;

public class listaEmergencias extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			listaEmergencias dialog = new listaEmergencias();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public listaEmergencias() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Cod Emergencia", "Latitud", "Longitud", "Fecha", "Hora", "DNI Paciente", "Nombre Paciente", "Sexo", "Edad", "Sintomas", "Hospital Asignado", "Equipo", "Num Registro"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(83);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(6).setPreferredWidth(88);
		table.getColumnModel().getColumn(7).setPreferredWidth(53);
		table.getColumnModel().getColumn(8).setPreferredWidth(51);
		table.getColumnModel().getColumn(10).setPreferredWidth(93);
		scrollPane.setViewportView(table);
		
		ServicioEmergencia s;
		try {
			s = new ServicioEmergencia();
			DefaultTableModel mo=(DefaultTableModel) table.getModel();
			
			Iterator<Emergencia> pa=s.listaEmergencias().iterator();
			while(pa.hasNext()){
				Emergencia p=pa.next();
				Paciente pi=p.getPaciente();
				Iterator<Sintoma>ho=p.getSintomas().iterator();
				String sinto="";
				while(ho.hasNext()){
				sinto+=ho.next().getNombre();
				sinto+=" ";
				}
				mo.addRow(new Object[]{p.getCodEmergencia(),p.getLat(),p.getLong(),p.getFecha(),p.getHora(), pi.getDni(), pi.getNombre(), pi.getEdad(),pi.getSexo(), sinto, p.getHosp().getNombre(),p.getAmb().getNumRegistro(), p.getAmb().getEquipo()});
			}
		} catch (LogicaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
