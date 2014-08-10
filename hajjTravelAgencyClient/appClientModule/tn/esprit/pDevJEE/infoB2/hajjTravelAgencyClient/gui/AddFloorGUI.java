package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.Color;
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

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Floor;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Hotel;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.hotelManagement.FloorManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.hotelManagement.HotelManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class AddFloorGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fnumber;
	private List<Hotel> hotels;
	private HotelManRemote remote;
	private FloorManRemote remote2;
	private JComboBox fhotel;

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
					AddFloorGUI frame = new AddFloorGUI();
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
	public AddFloorGUI() {
		remote = (HotelManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/HotelMan!"
								+ HotelManRemote.class.getCanonicalName());
		remote2 = (FloorManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/FloorMan!"
								+ FloorManRemote.class.getCanonicalName());
		hotels=remote.getAllHotels();
		setTitle("New Floor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "New Floor", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		
		JLabel lblNumber = new JLabel("Number");
		
		JLabel lblHotel = new JLabel("Hotel");
		
		fnumber = new JTextField();
		fnumber.setColumns(10);
		
		fhotel = new JComboBox();
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Floor floor=new Floor();
				floor.setNumberFloor(Integer.parseInt(fnumber.getText()));
				floor.setFloorHotel((Hotel) fhotel.getSelectedItem());
				remote2.addFloor(floor);
				dispose();
					}
		});
		
		JButton btnClear = new JButton("Clear");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNumber)
						.addComponent(lblHotel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(fhotel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fnumber, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
					.addContainerGap(189, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(217, Short.MAX_VALUE)
					.addComponent(btnClear)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAdd)
					.addGap(57))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumber)
						.addComponent(fnumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHotel)
						.addComponent(fhotel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnClear))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}
	protected void initDataBindings() {
		JComboBoxBinding<Hotel, List<Hotel>, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, hotels, fhotel);
		jComboBinding.bind();
	}
}
