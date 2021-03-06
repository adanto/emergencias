package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import excepciones.LogicaExcepcion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmergenciasApp {

	JFrame frame;
	private AltaPacienteJDialog a;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmergenciasApp window = new EmergenciasApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmergenciasApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnOperador = new JMenu("Operador");
		menuBar.add(mnOperador);
		
		JMenuItem mntmAltaPaciente = new JMenuItem("Alta paciente");
		mntmAltaPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				a=new AltaPacienteJDialog();
				frame.dispose();
				a.show();
			}
		});
	
		mnOperador.add(mntmAltaPaciente);
		
		JSeparator separator = new JSeparator();
		mnOperador.add(separator);
		
		JMenuItem mntmConsultarPaciente = new JMenuItem("consultar paciente");
		mntmConsultarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaPaciente p= new ConsultaPaciente();
				p.show();
			}
		});
		mnOperador.add(mntmConsultarPaciente);
		
		JMenu mnConductor = new JMenu("Conductor");
		menuBar.add(mnConductor);
		
		JMenuItem mntmCambiarCoordenadas = new JMenuItem("Cambiar Coordenadas");
		mntmCambiarCoordenadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambiarCoor a = new CambiarCoor(); 
				a.show();
				frame.dispose();
			}
		});
		mnConductor.add(mntmCambiarCoordenadas);
		
		JSeparator separator_1 = new JSeparator();
		mnConductor.add(separator_1);
		
		JMenuItem mntmCambiarDisponibilidad = new JMenuItem("Cambiar Disponibilidad");
		mntmCambiarDisponibilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CambiaDisp c=new CambiaDisp();
				c.show();
				frame.dispose();
			}
		});
		mntmCambiarDisponibilidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}
		});
		mnConductor.add(mntmCambiarDisponibilidad);
		
		JMenu mnNewMenu = new JMenu("Personal comision");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmListarPacientes = new JMenuItem("Listar Pacientes");
		mntmListarPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaPaciente lp;
				try {
					lp = new ListaPaciente();
					lp.show();
				} catch (LogicaExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		JMenuItem mntmListarEmergencias = new JMenuItem("Listar Llamadas Emergencias");
		mntmListarEmergencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaEmergencias l=new listaEmergencias();
				frame.dispose();
				l.show();
			}
		});
		mnNewMenu.add(mntmListarEmergencias);
		
		JSeparator separator_3 = new JSeparator();
		mnNewMenu.add(separator_3);
		mnNewMenu.add(mntmListarPacientes);
		
		JSeparator separator_2 = new JSeparator();
		mnNewMenu.add(separator_2);
		
		JMenuItem mntmListarEspecialidadesDe = new JMenuItem("Listar Especialidades de Hospital");
		mntmListarEspecialidadesDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaEspecialidad le;
				try {
					le = new ListaEspecialidad();
					le.show();
				} catch (LogicaExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		mnNewMenu.add(mntmListarEspecialidadesDe);
		
		JMenu mnOperadoremergencia = new JMenu("OperadorEmergencia");
		menuBar.add(mnOperadoremergencia);
		
		JMenuItem mntmNuevaEmergencia = new JMenuItem("Nueva Emergencia");
		mntmNuevaEmergencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaPaciente n= new ConsultaPaciente();
				frame.dispose();
				n.show();
			}
		});
		mnOperadoremergencia.add(mntmNuevaEmergencia);
		
		JMenu mnSalir = new JMenu("Salir");
							
		
		menuBar.add(mnSalir);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		mnSalir.add(mntmExit);
	}

}
