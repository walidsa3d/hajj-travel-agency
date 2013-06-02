package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Airport;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Flight;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Plane;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement.AirportManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement.FlightManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement.PlaneManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

import com.toedter.calendar.JDateChooser;

public class FlightManGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5307902842958136476L;
	private JPanel contentPane;
	private JTable table;
	private FlightManRemote remote;
	private List<Flight> flights;
	private List<Airport> airports=new ArrayList<Airport>();	
	private List<Plane> planes=new ArrayList<Plane>();
	private JTextField fid;
	private JTextField fnumber;
	private AirportManRemote remote2;
	private PlaneManRemote remote3;
	private JComboBox fplane;
	private JComboBox fairport;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightManGUI frame = new FlightManGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FlightManGUI() {
		setBackground(new Color(255, 255, 102));
		remote2 = (AirportManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/AirportMan!"
								+ AirportManRemote.class.getCanonicalName());
		airports=remote2.getAllAirports();
		remote3 = (PlaneManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/PlaneMan!"
								+ PlaneManRemote.class.getCanonicalName());
		planes=remote3.getAllPlanes();
		setTitle("Flight Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Flight Management", TitledBorder.CENTER, TitledBorder.TOP, null, Color.RED));
		setContentPane(contentPane);
		remote = (FlightManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/FlightMan!"
								+ FlightManRemote.class.getCanonicalName());
		flights=remote.getAllFlights();
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblId = new JLabel("ID");
		
		JLabel lblNumber = new JLabel("Number");
		
		JLabel lblDate = new JLabel("Date");
		
		JLabel lblPlane = new JLabel("Plane");
		
		JLabel lblAirport = new JLabel("Airport");
		
		fid = new JTextField();
		fid.setColumns(10);
		
		fnumber = new JTextField();
		fnumber.setColumns(10);
		
		final JDateChooser fdate = new JDateChooser();
		
		fplane = new JComboBox();
		fplane.setEditable(true);
		
		fairport = new JComboBox();
		fairport.setEditable(true);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddFlightGUI addflightgui=new AddFlightGUI();				
				addflightgui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				addflightgui.setLocationRelativeTo(null);
				addflightgui.setVisible(true);
				addflightgui.pack();
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Flight selectedFlight=new Flight();
				selectedFlight.setIdFlight(Integer.parseInt(fid.getText()));
				remote.removeFlight(selectedFlight);
				flights=remote.getAllFlights();
				initDataBindings();	
				
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flights=remote.getAllFlights();
				planes=remote3.getAllPlanes();
				airports=remote2.getAllAirports();				
				initDataBindings();
			}
		});
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			private JComboBox ftype;

			public void actionPerformed(ActionEvent arg0) {
				Flight selectedFlight=new Flight();
				selectedFlight.setIdFlight(Integer.parseInt(fid.getText()));
				selectedFlight.setNumberFlight(Long.getLong(fnumber.getText()));
				selectedFlight.setDateFlight(fdate.getDate());
				selectedFlight.setFlightPlane((Plane) fplane.getSelectedItem());
				selectedFlight.setFlightAirport((Airport) fairport.getSelectedItem());
				selectedFlight.setTypeFlight(ftype.getSelectedItem().toString());
				remote.updateFlight(selectedFlight);
				flights=remote.getAllFlights();
				initDataBindings();				
				
			}
		});
		
		JLabel lblType = new JLabel("Type");
		
		JComboBox ftype = new JComboBox();
		ftype.setModel(new DefaultComboBoxModel(new String[] {"One Way", "Return"}));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblId)
						.addComponent(lblNumber)
						.addComponent(lblDate)
						.addComponent(lblPlane)
						.addComponent(lblAirport)
						.addComponent(lblType))
					.addGap(33)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(ftype, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fnumber)
						.addComponent(fplane, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fairport, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fid, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNew, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
						.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRefresh, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
						.addComponent(btnNewButton, 0, 0, Short.MAX_VALUE))
					.addGap(130))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(fid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNew))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumber)
								.addComponent(fnumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDate)
								.addComponent(fdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPlane)
								.addComponent(fplane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(18)
							.addComponent(btnNewButton)
							.addGap(16)
							.addComponent(btnDelete)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAirport)
						.addComponent(fairport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRefresh))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblType)
						.addComponent(ftype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29))
		);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setBackground(Color.YELLOW);
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setForeground(Color.BLACK);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Number", "Type", "Date", "Date", "Plane", "Airport"
			}
		));
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}
	protected void initDataBindings() {
		JTableBinding<Flight, List<Flight>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, flights, table);
		//
		BeanProperty<Flight, Integer> flightBeanProperty = BeanProperty.create("idFlight");
		jTableBinding.addColumnBinding(flightBeanProperty).setColumnName("ID");
		//
		BeanProperty<Flight, Date> flightBeanProperty_1 = BeanProperty.create("dateFlight");
		jTableBinding.addColumnBinding(flightBeanProperty_1).setColumnName("Date");
		//
		BeanProperty<Flight, Long> flightBeanProperty_2 = BeanProperty.create("numberFlight");
		jTableBinding.addColumnBinding(flightBeanProperty_2).setColumnName("Number");
		//
		BeanProperty<Flight, String> flightBeanProperty_3 = BeanProperty.create("typeFlight");
		jTableBinding.addColumnBinding(flightBeanProperty_3).setColumnName("Type");
		//
		BeanProperty<Flight, String> flightBeanProperty_4 = BeanProperty.create("flightPlane.namePlane");
		jTableBinding.addColumnBinding(flightBeanProperty_4).setColumnName("Plane");
		//
		BeanProperty<Flight, String> flightBeanProperty_5 = BeanProperty.create("flightAirport.nameAirport");
		jTableBinding.addColumnBinding(flightBeanProperty_5).setColumnName("Airport");
		//
		jTableBinding.bind();
		//
		JComboBoxBinding<Plane, List<Plane>, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, planes, fplane);
		jComboBinding.bind();
		//
		JComboBoxBinding<Airport, List<Airport>, JComboBox> jComboBinding_1 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, airports, fairport);
		jComboBinding_1.bind();
		//
		BeanProperty<JTable, Integer> jTableBeanProperty = BeanProperty.create("selectedElement.idFlight");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<JTable, Integer, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty, fid, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<JTable, Long> jTableBeanProperty_1 = BeanProperty.create("selectedElement.numberFlight");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<JTable, Long, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_1, fnumber, jTextFieldBeanProperty_1);
		autoBinding_1.bind();
	}
}
