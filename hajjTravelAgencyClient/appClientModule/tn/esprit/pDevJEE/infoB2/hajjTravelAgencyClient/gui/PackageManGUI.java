package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Flight;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Hotel;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pakage;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement.FlightManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.hotelManagement.HotelManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.packageManagement.PackageManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

import com.toedter.calendar.JDateChooser;

public class PackageManGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
	private PackageManRemote remote;
	private HotelManRemote remote2;
	private List<Pakage> packages;
	private JLabel lblId;
	private JLabel lblName;
	private JLabel lblCapacity;
	private JLabel lblStart;
	private JLabel lblEnd;
	private JLabel lblOneWayFlight;
	private JLabel lblReturnFlight;
	private JLabel lblHotelMecca;
	private JLabel lblHotelMedina;
	private JTextField pid;
	private JTextField pname;
	private JTextField pcapacity;
	private JLabel lblPrice;
	private JTextField pprice;
	private FlightManRemote remote3;
	private List<Flight> oneWayflights;
	private List<Flight> returnFlights;
	private List<Hotel> meccaHotels;
	private List<Hotel> medinaHotels;
	private JComboBox poflight;
	private JComboBox prflight;
	private JComboBox phmecca;
	private JComboBox phmedina;
	private JDateChooser pstart;
	private JDateChooser pend;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PackageManGUI frame = new PackageManGUI();
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
	public PackageManGUI() {
		remote = (PackageManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/PackageMan!"
								+ PackageManRemote.class.getCanonicalName());
		remote2 = (HotelManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/HotelMan!"
								+ HotelManRemote.class.getCanonicalName());
		remote3=(FlightManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/FlightMan!"
								+ FlightManRemote.class.getCanonicalName());
		packages=remote.getAllPackages();
		oneWayflights=remote3.getFlightsByType("One Way");
		returnFlights=remote3.getFlightsByType("Return");
		meccaHotels=remote2.getHotelsByLocation("Mecca");
		medinaHotels=remote2.getHotelsByLocation("Medina");
		setTitle("Package Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 648);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new LineBorder(new Color(165, 163, 151)), "Package Management", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		lblId = new JLabel("ID");
		
		lblName = new JLabel("Name");
		
		lblCapacity = new JLabel("Capacity");
		
		lblStart = new JLabel("Start");
		
		lblEnd = new JLabel("End");
		
		lblOneWayFlight = new JLabel("One Way Flight");
		
		lblReturnFlight = new JLabel("Return Flight");
		
		lblHotelMecca = new JLabel("Hotel Mecca");
		
		lblHotelMedina = new JLabel("Hotel Medina");
		
		pid = new JTextField();
		pid.setColumns(10);
		
		pname = new JTextField();
		pname.setColumns(10);
		
		pcapacity = new JTextField();
		pcapacity.setColumns(10);
		
		poflight = new JComboBox();
		
		prflight = new JComboBox();
		
		phmecca = new JComboBox();
		
		phmedina = new JComboBox();
		
		JButton btnNewButton = new JButton("New");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPackageGUI apg=new AddPackageGUI();
				apg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				apg.setLocationRelativeTo(null);
				apg.setVisible(true);
				apg.pack();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pakage selectedPakage=new Pakage();				
				selectedPakage.setIdPackage(Integer.parseInt(pid.getText()));
				selectedPakage.setNamePackage(pname.getText());
				selectedPakage.setCapacityPackage(pcapacity.getText());
				selectedPakage.setPricePackage(Integer.parseInt(pprice.getText()));
				selectedPakage.setStartDate(pstart.getDate());
				selectedPakage.setEndDate(pend.getDate());
				selectedPakage.setHotelMecca((Hotel) phmecca.getSelectedItem());
				selectedPakage.setHotelMedina((Hotel) phmedina.getSelectedItem());
				selectedPakage.setOneWayFlight((Flight) poflight.getSelectedItem());
				selectedPakage.setReturnFlight((Flight) prflight.getSelectedItem());
				remote.updatePackage(selectedPakage);
				packages=remote.getAllPackages();
				initDataBindings();				


			}
		});
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pakage selectedPakage=new Pakage();
				selectedPakage.setIdPackage(Integer.parseInt(pid.getText()));
				remote.removePackage(selectedPakage);
				packages=remote.getAllPackages();
				initDataBindings();
			}
		});
		
		JButton btnNewButton_3 = new JButton("Refresh");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				packages=remote.getAllPackages();
				initDataBindings();
			}
		});
		
		lblPrice = new JLabel("Price");
		
		pprice = new JTextField();
		pprice.setColumns(10);
		
		pstart = new JDateChooser();
		
		pend = new JDateChooser();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblId)
						.addComponent(lblName)
						.addComponent(lblCapacity)
						.addComponent(lblStart)
						.addComponent(lblEnd)
						.addComponent(lblOneWayFlight)
						.addComponent(lblReturnFlight)
						.addComponent(lblHotelMecca)
						.addComponent(lblHotelMedina)
						.addComponent(lblPrice))
					.addGap(36)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pprice)
						.addComponent(pend, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pstart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pcapacity)
						.addComponent(pname)
						.addComponent(poflight, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(prflight, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(phmecca, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(phmedina, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pid, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
					.addGap(67)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(572, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblId)
								.addComponent(pid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblName)
								.addComponent(pname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCapacity)
								.addComponent(pcapacity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStart)
								.addComponent(pstart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(12)
									.addComponent(lblEnd))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(pend, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblOneWayFlight, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblReturnFlight))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(4)
									.addComponent(poflight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(prflight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblHotelMecca)
								.addComponent(phmecca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblHotelMedina)
								.addComponent(phmedina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(22)
							.addComponent(btnNewButton)
							.addGap(18)
							.addComponent(btnNewButton_1)
							.addGap(18)
							.addComponent(btnNewButton_2)
							.addGap(18)
							.addComponent(btnNewButton_3)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrice)
						.addComponent(pprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(71))
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		table.setBackground(Color.YELLOW);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Name", "Price", "Capacity", "Start", "End", "1-Way Flight", "Return Flight", "Hotel Mecca", "Hotel Medina"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}

	protected void initDataBindings() {
		JTableBinding<Pakage, List<Pakage>, JTable> jTableBinding = SwingBindings
				.createJTableBinding(UpdateStrategy.READ_WRITE, packages, table);
		//
		BeanProperty<Pakage, Integer> pakageBeanProperty = BeanProperty
				.create("idPackage");
		jTableBinding.addColumnBinding(pakageBeanProperty).setColumnName("ID");
		//
		BeanProperty<Pakage, String> pakageBeanProperty_1 = BeanProperty
				.create("namePackage");
		jTableBinding.addColumnBinding(pakageBeanProperty_1).setColumnName(
				"Name");
		//
		BeanProperty<Pakage, Long> pakageBeanProperty_2 = BeanProperty
				.create("pricePackage");
		jTableBinding.addColumnBinding(pakageBeanProperty_2).setColumnName(
				"Price");
		//
		BeanProperty<Pakage, String> pakageBeanProperty_3 = BeanProperty
				.create("capacityPackage");
		jTableBinding.addColumnBinding(pakageBeanProperty_3).setColumnName(
				"Capacity");
		//
		BeanProperty<Pakage, Date> pakageBeanProperty_4 = BeanProperty
				.create("startDate");
		jTableBinding.addColumnBinding(pakageBeanProperty_4).setColumnName(
				"Start");
		//
		BeanProperty<Pakage, Date> pakageBeanProperty_5 = BeanProperty
				.create("endDate");
		jTableBinding.addColumnBinding(pakageBeanProperty_5).setColumnName(
				"End");
		//
		BeanProperty<Pakage, Hotel> pakageBeanProperty_6 = BeanProperty
				.create("hotelMecca");
		jTableBinding.addColumnBinding(pakageBeanProperty_6).setColumnName(
				"Hotel Mecca");
		//
		BeanProperty<Pakage, Hotel> pakageBeanProperty_7 = BeanProperty
				.create("hotelMedina");
		jTableBinding.addColumnBinding(pakageBeanProperty_7).setColumnName(
				"Hotel Medina");
		//
		BeanProperty<Pakage, Flight> pakageBeanProperty_8 = BeanProperty
				.create("oneWayFlight");
		jTableBinding.addColumnBinding(pakageBeanProperty_8).setColumnName(
				"1-Way Flight");
		//
		BeanProperty<Pakage, Flight> pakageBeanProperty_9 = BeanProperty
				.create("returnFlight");
		jTableBinding.addColumnBinding(pakageBeanProperty_9).setColumnName(
				"Return Flight");
		//
		jTableBinding.bind();
		//
		BeanProperty<JTable, Integer> jTableBeanProperty = BeanProperty
				.create("selectedElement.idPackage");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<JTable, Integer, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, table,
						jTableBeanProperty, pid, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty_1 = BeanProperty
				.create("selectedElement.namePackage");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, table,
						jTableBeanProperty_1, pname, jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty_2 = BeanProperty
				.create("selectedElement.capacityPackage");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty
				.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, table,
						jTableBeanProperty_2, pcapacity,
						jTextFieldBeanProperty_2);
		autoBinding_2.bind();
		//
		BeanProperty<JTable, Long> jTableBeanProperty_3 = BeanProperty
				.create("selectedElement.pricePackage");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_3 = BeanProperty
				.create("text");
		AutoBinding<JTable, Long, JTextField, String> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, table,
						jTableBeanProperty_3, pprice, jTextFieldBeanProperty_3);
		autoBinding_3.bind();
		//
		JComboBoxBinding<Flight, List<Flight>, JComboBox> jComboBinding = SwingBindings
				.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
						oneWayflights, poflight);
		jComboBinding.bind();
		//
		JComboBoxBinding<Flight, List<Flight>, JComboBox> jComboBinding_1 = SwingBindings
				.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
						returnFlights, prflight);
		jComboBinding_1.bind();
		//
		JComboBoxBinding<Hotel, List<Hotel>, JComboBox> jComboBinding_2 = SwingBindings
				.createJComboBoxBinding(UpdateStrategy.READ_WRITE, meccaHotels,
						phmecca);
		jComboBinding_2.bind();
		//
		JComboBoxBinding<Hotel, List<Hotel>, JComboBox> jComboBinding_3 = SwingBindings
				.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
						medinaHotels, phmedina);
		jComboBinding_3.bind();
	}
}
