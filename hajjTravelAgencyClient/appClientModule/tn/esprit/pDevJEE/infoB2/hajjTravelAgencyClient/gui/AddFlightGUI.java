package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Airport;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Flight;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Plane;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement.AirportManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement.FlightManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement.PlaneManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

import com.toedter.calendar.JDateChooser;

public class AddFlightGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4492790329222661238L;
	private JPanel contentPane;
	private JTextField fnumber;
	private AirportManRemote remote;
	private PlaneManRemote remote2;
	private FlightManRemote remote3;
	private List<Airport> airports=new ArrayList<Airport>();	
	private List<Plane> planes=new ArrayList<Plane>();
	private JComboBox fplane;
	private JComboBox fairport;	
	private JComboBox ftype;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFlightGUI frame = new AddFlightGUI();
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
	public AddFlightGUI() {
		setTitle("New Flight");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "New Flight", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		remote = (AirportManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/AirportMan!"
								+ AirportManRemote.class.getCanonicalName());
		airports=remote.getAllAirports();
		remote2 = (PlaneManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/PlaneMan!"
								+ PlaneManRemote.class.getCanonicalName());
		planes=remote2.getAllPlanes();
		
		remote3=(FlightManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/FlightMan!"
								+ FlightManRemote.class.getCanonicalName());
		JLabel lblNumber = new JLabel("Number");
		
		JLabel lblDate = new JLabel("Date");
		
		JLabel lblPlane = new JLabel("Plane");
		
		JLabel lblAirport = new JLabel("Airport");
		
		fnumber = new JTextField();
		fnumber.setColumns(10);
		
		final JDateChooser fdate = new JDateChooser();
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Flight flight=new Flight();
				flight.setNumberFlight(Long.parseLong(fnumber.getText()));
				flight.setDateFlight(fdate.getDate());
				flight.setTypeFlight(ftype.getSelectedItem().toString());
				flight.setFlightPlane((Plane)fplane.getSelectedItem());
				flight.setFlightAirport((Airport)fairport.getSelectedItem());
				remote3.addFlight(flight);
				dispose();
				
			}
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPanelTextBoxes(contentPane);

			}
		});
		
		fplane = new JComboBox<Object>();
		
		fairport = new JComboBox<String>();
		
		JLabel lblType = new JLabel("Type");
		
		ftype = new JComboBox<Object>();
		ftype.setModel(new DefaultComboBoxModel<Object>(new String[] {"One Way", "Return"}));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(260, Short.MAX_VALUE)
					.addComponent(btnClear)
					.addGap(18)
					.addComponent(btnAdd)
					.addGap(30))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDate)
						.addComponent(lblNumber)
						.addComponent(lblAirport)
						.addComponent(lblPlane)
						.addComponent(lblType))
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(ftype, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fplane, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fairport, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fnumber, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
					.addGap(161))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumber)
						.addComponent(fnumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDate)
						.addComponent(fdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(ftype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(fplane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPlane))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(fairport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAirport))
					.addContainerGap(41, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(206, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClear)
						.addComponent(btnAdd))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}
	protected void initDataBindings() {
		JComboBoxBinding<Plane, List<Plane>, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, planes, fplane);
		jComboBinding.bind();
		//
		JComboBoxBinding<Airport, List<Airport>, JComboBox> jComboBinding_1 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, airports, fairport);
		jComboBinding_1.bind();
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
