package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Flight;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Hotel;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pakage;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement.FlightManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.hotelManagement.HotelManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.packageManagement.PackageManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

import com.toedter.calendar.JDateChooser;

public class AddPackageGUI extends JFrame {

	private JPanel contentPane;
	private JTextField pname;
	private HotelManRemote remote;
	private FlightManRemote remote2;
	private JComboBox photelmedina;
	private JComboBox photelmecca;
	private List<Flight> returnFlights;
	private List<Flight> oneWayflights;
	private List<Hotel> meccaHotels;
	private List<Hotel> medinaHotels;
	private JTextField pcapacity;
	private JComboBox prflight;
	private JComboBox poflight;
	private PackageManRemote remote3;
	private JTextField pprice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPackageGUI frame = new AddPackageGUI();
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
	public AddPackageGUI() {
		remote = (HotelManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/HotelMan!"
								+ HotelManRemote.class.getCanonicalName());
		remote2 = (FlightManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/FlightMan!"
								+ FlightManRemote.class.getCanonicalName());
		remote3 = (PackageManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/PackageMan!"
								+ PackageManRemote.class.getCanonicalName());
		
		oneWayflights=remote2.getFlightsByType("One Way");
		returnFlights=remote2.getFlightsByType("Return");
		meccaHotels=remote.getHotelsByLocation("Mecca");
		medinaHotels=remote.getHotelsByLocation("Medina");
		setTitle("New Package");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "New Package", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Name");
		
		JLabel lblStart = new JLabel("Start");
		
		JLabel lblEnd = new JLabel("End");
		
		JLabel lblHotelMecca = new JLabel("Hotel Mecca");
		
		JLabel lblHotelMedina = new JLabel("Hotel Medina");
		
		pname = new JTextField();
		pname.setColumns(10);
		
		final JDateChooser pstart = new JDateChooser();
		
		final JDateChooser pend = new JDateChooser();
		
		photelmecca = new JComboBox();
		
		photelmedina = new JComboBox();
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pakage pkg=new Pakage();
				pkg.setCapacityPackage(pcapacity.getText());
				pkg.setStartDate(pstart.getDate());
				pkg.setEndDate(pend.getDate());				
				pkg.setHotelMecca((Hotel) photelmecca.getSelectedItem());
				pkg.setHotelMedina((Hotel) photelmedina.getSelectedItem());
				pkg.setNamePackage(pname.getText());
				pkg.setOneWayFlight((Flight) poflight.getSelectedItem());
				pkg.setReturnFlight((Flight) prflight.getSelectedItem());
				pkg.setPricePackage(Long.parseLong(pprice.getText()));
				remote3.addPackage(pkg);
				dispose();
				
				
			}
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPanelTextBoxes(contentPane);

			}
		});
		
		JLabel lblOneWayFlight = new JLabel("One Way Flight");
		
		JLabel lblReturnFlight = new JLabel("Return Flight");
		
		JLabel lblCapacity = new JLabel("Capacity");
		
		poflight = new JComboBox();
		
		prflight = new JComboBox();
		
		pcapacity = new JTextField();
		pcapacity.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		
		pprice = new JTextField();
		pprice.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnClear)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAdd)
							.addGap(35))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblOneWayFlight)
								.addComponent(lblReturnFlight)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStart)
								.addComponent(lblEnd)
								.addComponent(lblHotelMecca)
								.addComponent(lblHotelMedina))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(pname, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
									.addComponent(pstart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(pend, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(photelmecca, Alignment.TRAILING, 0, 130, Short.MAX_VALUE))
								.addComponent(photelmedina, Alignment.TRAILING, 0, 130, Short.MAX_VALUE)
								.addComponent(prflight, 0, 130, Short.MAX_VALUE)
								.addComponent(poflight, 0, 130, Short.MAX_VALUE)
								.addComponent(pcapacity, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
								.addComponent(pprice, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
							.addGap(190))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCapacity)
							.addContainerGap(353, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPrice)
							.addContainerGap(372, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(pname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(pstart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStart))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(pend, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEnd))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblHotelMecca)
						.addComponent(photelmecca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblHotelMedina)
						.addComponent(photelmedina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblOneWayFlight)
						.addComponent(poflight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblReturnFlight, Alignment.TRAILING)
						.addComponent(prflight, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCapacity)
						.addComponent(pcapacity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrice)
						.addComponent(pprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnClear))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}
	protected void initDataBindings() {
		JComboBoxBinding<Hotel, List<Hotel>, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, medinaHotels, photelmedina);
		jComboBinding.bind();
		//
		JComboBoxBinding<Hotel, List<Hotel>, JComboBox> jComboBinding_1 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, meccaHotels, photelmecca);
		jComboBinding_1.bind();
		//
		JComboBoxBinding<Flight, List<Flight>, JComboBox> jComboBinding_2 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, returnFlights, prflight);
		jComboBinding_2.bind();
		//
		JComboBoxBinding<Flight, List<Flight>, JComboBox> jComboBinding_3 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, oneWayflights, poflight);
		jComboBinding_3.bind();
	}
	private void clearPanelTextBoxes(Container co)
	{

	Component[] components = co.getComponents();
	JTextField t = new JTextField();
	for ( Component c : components )
	{
	if (c instanceof JTextField )
	{
	t = ( JTextField ) c ;
	t.setText("");//cleat the fields 
	}
	if (c instanceof Container ) clearPanelTextBoxes((Container) c);
	
	}
	}
}
