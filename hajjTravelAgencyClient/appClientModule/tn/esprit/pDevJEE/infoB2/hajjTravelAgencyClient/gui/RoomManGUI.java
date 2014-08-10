package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Floor;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Hotel;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Room;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.hotelManagement.HotelManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.hotelManagement.RoomManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class RoomManGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField rid;
	private JTextField rnumber;
	private RoomManRemote remote;
	private List<Room> rooms;
	private HotelManRemote remote2;
	private List<Hotel> hotels;
	private JComboBox rhotel;
	private JComboBox rcapacity;
	private JComboBox rfloor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomManGUI frame = new RoomManGUI();
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
	public RoomManGUI() {
		remote = (RoomManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/RoomMan!"
								+ RoomManRemote.class.getCanonicalName());
		remote2 = (HotelManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/HotelMan!"
								+ HotelManRemote.class.getCanonicalName());
		rooms=remote.getAllRooms();
		hotels=remote2.getAllHotels();
		setTitle("Room Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Room Management", TitledBorder.CENTER, TitledBorder.TOP, null, Color.RED));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("ID");
		
		JLabel lblNumber = new JLabel("Number");
		
		JLabel lblType = new JLabel("Capacity");
		
		JLabel lblFloor = new JLabel("Floor");
		
		JLabel lblHotel = new JLabel("Hotel");
		
		rid = new JTextField();
		rid.setColumns(10);
		
		rnumber = new JTextField();
		rnumber.setColumns(10);
		
		rcapacity = new JComboBox();
		rcapacity.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "4"}));
		
		rfloor = new JComboBox();
		
		rhotel = new JComboBox();
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddRoomGUI arg=new AddRoomGUI();
				arg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				arg.setLocationRelativeTo(null);
				arg.setVisible(true);
				arg.pack();
			}
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Room room=new Room();
				room.setIdRoom(Integer.parseInt(rid.getText()));
				room.setNumberRoom(Integer.parseInt(rnumber.getText()));
				room.setCapacityRoom((int)rcapacity.getSelectedItem());
				room.setRoomFloor((Floor) rfloor.getSelectedItem());
				remote.updateRoom(room);
				rooms=remote.getAllRooms();
				initDataBindings();
			} 
		});
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remote.deleteRoom(Integer.parseInt(rid.getText()));
				rooms=remote.getAllRooms();
				initDataBindings();

			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rooms=remote.getAllRooms();
				initDataBindings();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNumber)
						.addComponent(lblType)
						.addComponent(lblFloor)
						.addComponent(lblHotel))
					.addGap(26)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addComponent(rid, 113, 113, Short.MAX_VALUE)
							.addComponent(rcapacity, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(rnumber, 113, 113, Short.MAX_VALUE)
							.addComponent(rfloor, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(rhotel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnRemove, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNew, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRefresh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(38))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(rid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNew))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumber)
						.addComponent(rnumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdate))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(rcapacity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRemove))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFloor)
						.addComponent(rfloor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRefresh))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHotel)
						.addComponent(rhotel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setBackground(Color.YELLOW);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Number", "Type", "Floor", "Hotel"
			}
		));
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}
	protected void initDataBindings() {
		org.jdesktop.swingbinding.JTableBinding<tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Room, java.util.List<tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Room>, javax.swing.JTable> jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms, table);
		//
		org.jdesktop.beansbinding.BeanProperty<tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Room, java.lang.Integer> roomBeanProperty = org.jdesktop.beansbinding.BeanProperty.create("idRoom");
		jTableBinding.addColumnBinding(roomBeanProperty).setColumnName("ID");
		//
		org.jdesktop.beansbinding.BeanProperty<tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Room, java.lang.Integer> roomBeanProperty_1 = org.jdesktop.beansbinding.BeanProperty.create("numberRoom");
		jTableBinding.addColumnBinding(roomBeanProperty_1).setColumnName("Number");
		//
		org.jdesktop.beansbinding.BeanProperty<tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Room, java.lang.Integer> roomBeanProperty_2 = org.jdesktop.beansbinding.BeanProperty.create("capacityRoom");
		jTableBinding.addColumnBinding(roomBeanProperty_2).setColumnName("Capacity");
		org.jdesktop.beansbinding.BeanProperty<tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Room, tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Floor> roomBeanProperty_3 = org.jdesktop.beansbinding.BeanProperty.create("roomFloor");
		jTableBinding.addColumnBinding(roomBeanProperty_3).setColumnName("Floor");
		//
		jTableBinding.bind();
		//
		org.jdesktop.beansbinding.BeanProperty<javax.swing.JTable, java.lang.Integer> jTableBeanProperty = org.jdesktop.beansbinding.BeanProperty.create("selectedElement.idRoom");
		org.jdesktop.beansbinding.BeanProperty<javax.swing.JTextField, java.lang.String> jTextFieldBeanProperty = org.jdesktop.beansbinding.BeanProperty.create("text");
		org.jdesktop.beansbinding.AutoBinding<javax.swing.JTable, java.lang.Integer, javax.swing.JTextField, java.lang.String> autoBinding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, table, jTableBeanProperty, rid, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		org.jdesktop.beansbinding.BeanProperty<javax.swing.JTable, java.lang.Integer> jTableBeanProperty_1 = org.jdesktop.beansbinding.BeanProperty.create("selectedElement.numberRoom");
		org.jdesktop.beansbinding.BeanProperty<javax.swing.JTextField, java.lang.String> jTextFieldBeanProperty_1 = org.jdesktop.beansbinding.BeanProperty.create("text");
		org.jdesktop.beansbinding.AutoBinding<javax.swing.JTable, java.lang.Integer, javax.swing.JTextField, java.lang.String> autoBinding_1 = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, table, jTableBeanProperty_1, rnumber, jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		org.jdesktop.swingbinding.JComboBoxBinding<tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Hotel, java.util.List<tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Hotel>, javax.swing.JComboBox> jComboBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, hotels, rhotel);
		jComboBinding.bind();
	}
}
