package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Privilege;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.User;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.userManagement.UserManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.Authenticate;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class MainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4856819740294334603L;
	private JPanel contentPane;
	private JTextField musername;
	private JPasswordField mpassword;
	private final JPanel panel = new JPanel();
	private UserManRemote remote;
	private JMenuBar menuBar;
	private JMenu PILGRIMS;
	private JLabel msglabel;
	private List<User> users;
	private JLabel loglabel;

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
					MainGUI frame = new MainGUI();
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
	public MainGUI() {
		remote = (UserManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/UserMan!"
								+ UserManRemote.class.getCanonicalName());

		setTitle("Hajj Travel Agency Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		final JMenu MAIN = new JMenu("Main");
		menuBar.add(MAIN);

		JMenuItem mntmSignIn = new JMenuItem("Sign In");
		mntmSignIn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
			}
		});
		MAIN.add(mntmSignIn);

		JMenuItem mntmSignOut = new JMenuItem("Sign Out");
		mntmSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Component child : menuBar.getComponents()) {
					if (child instanceof JMenu)
						child.setEnabled(false);
				}
				MAIN.setEnabled(true);
				musername.setText("");
				mpassword.setText("");
				msglabel.setText("");
				panel.setVisible(true);
				Authenticate auth = new Authenticate();
				auth.createSession("", "");
				loglabel.setText("");
			}
		});
		MAIN.add(mntmSignOut);

		final JMenu USERS = new JMenu("Users");
		menuBar.add(USERS);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUserGUI aug = new AddUserGUI();
				aug.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				aug.setLocationRelativeTo(null);
				aug.setVisible(true);
			}
		});
		USERS.add(mntmNew);

		JMenuItem mntmManage = new JMenuItem("Manage");
		mntmManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManGUI staffmangui = new UserManGUI();
				staffmangui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				staffmangui.setLocationRelativeTo(null);
				staffmangui.setVisible(true);

			}
		});
		USERS.add(mntmManage);

		JMenu ROLES = new JMenu("Roles");
		ROLES.setEnabled(false);
		menuBar.add(ROLES);

		JMenu mntmRoles = new JMenu("Roles");
		ROLES.add(mntmRoles);

		JMenuItem mntmNew_7 = new JMenuItem("New");
		mntmNew_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRoleGUI addrolegui = new AddRoleGUI();
				addrolegui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				addrolegui.setLocationRelativeTo(null);
				addrolegui.setVisible(true);

			}

		});
		mntmRoles.add(mntmNew_7);

		JMenuItem mntmManage_7 = new JMenuItem("Manage");
		mntmManage_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoleManGUI rolemangui = new RoleManGUI();
				rolemangui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				rolemangui.setLocationRelativeTo(null);
				rolemangui.setVisible(true);
				// rolemangui.pack();

			}
		});
		mntmRoles.add(mntmManage_7);

		JMenu mntmPrivileges = new JMenu("Privileges");
		ROLES.add(mntmPrivileges);

		JMenuItem mntmNew_8 = new JMenuItem("New");
		mntmNew_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPrivilegeGUI apg=new AddPrivilegeGUI();
				apg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				apg.setLocationRelativeTo(null);
				apg.setVisible(true);
				
			}
		});
		mntmPrivileges.add(mntmNew_8);

		JMenuItem mntmManage_8 = new JMenuItem("Manage");
		mntmManage_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrivilegeManGUI privilegemangui = new PrivilegeManGUI();
				privilegemangui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				privilegemangui.setLocationRelativeTo(null);
				privilegemangui.setVisible(true);

			}
		});
		mntmPrivileges.add(mntmManage_8);

		PILGRIMS = new JMenu("Pilgrims");
		PILGRIMS.setEnabled(false);
		menuBar.add(PILGRIMS);

		JMenuItem mntmNew_1 = new JMenuItem("New");
		mntmNew_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPilgrimGUI addpilgrimgui = new AddPilgrimGUI();
				addpilgrimgui.setLocationRelativeTo(null);
				addpilgrimgui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				addpilgrimgui.setVisible(true);

			}
		});
		PILGRIMS.add(mntmNew_1);

		JMenuItem mntmManage_1 = new JMenuItem("Manage");
		mntmManage_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PilgrimsManGUI pilgrimsmangui = new PilgrimsManGUI();
				pilgrimsmangui.setLocationRelativeTo(null);
				pilgrimsmangui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				pilgrimsmangui.setVisible(true);

			}
		});
		PILGRIMS.add(mntmManage_1);

		JMenu GROUPS = new JMenu("Groups");
		GROUPS.setEnabled(false);
		menuBar.add(GROUPS);

		JMenuItem mntmNew_12 = new JMenuItem("New");
		mntmNew_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGroupGUI agg = new AddGroupGUI();
				agg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				agg.setLocationRelativeTo(null);
				agg.setVisible(true);
			}
		});
		GROUPS.add(mntmNew_12);

		JMenuItem mntmManage_12 = new JMenuItem("Manage");
		mntmManage_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroupManGUI gmg = new GroupManGUI();
				gmg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				gmg.setLocationRelativeTo(null);
				gmg.setVisible(true);
			}
		});
		GROUPS.add(mntmManage_12);

		JMenu TRANSPORT = new JMenu("Transport");
		TRANSPORT.setEnabled(false);
		menuBar.add(TRANSPORT);

		JMenu mntmAirports = new JMenu("Airports");
		TRANSPORT.add(mntmAirports);

		JMenuItem mntmNew_3 = new JMenuItem("New");
		mntmNew_3.setMnemonic('A');
		mntmNew_3.setMnemonic(KeyEvent.VK_CONTROL);
		mntmNew_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAirportGUI addairportgui = new AddAirportGUI();
				addairportgui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				addairportgui.setLocationRelativeTo(null);
				addairportgui.setVisible(true);

			}
		});
		mntmAirports.add(mntmNew_3);

		JMenuItem mntmManage_3 = new JMenuItem("Manage");
		mntmManage_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AirportManGUI airportmangui = new AirportManGUI();
				airportmangui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				airportmangui.setLocationRelativeTo(null);
				airportmangui.setVisible(true);

			}
		});
		mntmAirports.add(mntmManage_3);

		JMenu mntmCompanies = new JMenu("Companies");
		TRANSPORT.add(mntmCompanies);

		JMenuItem mntmNew_4 = new JMenuItem("New");
		mntmNew_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCompanyGUI addcompanygui = new AddCompanyGUI();
				addcompanygui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				addcompanygui.setLocationRelativeTo(null);
				addcompanygui.setVisible(true);

			}
		});
		mntmCompanies.add(mntmNew_4);

		JMenuItem mntmManage_4 = new JMenuItem("Manage");
		mntmManage_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompanyManGUI companymmangui = new CompanyManGUI();
				companymmangui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				companymmangui.setLocationRelativeTo(null);
				companymmangui.setVisible(true);

			}
		});
		mntmCompanies.add(mntmManage_4);

		JMenu mntmPlanes = new JMenu("Planes");
		TRANSPORT.add(mntmPlanes);

		JMenuItem mntmNew_5 = new JMenuItem("New");
		mntmNew_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPlaneGUI addPlanegui = new AddPlaneGUI();
				addPlanegui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				addPlanegui.setLocationRelativeTo(null);
				addPlanegui.setVisible(true);
			}
		});
		mntmPlanes.add(mntmNew_5);

		JMenuItem mntmManage_5 = new JMenuItem("Manage");
		mntmManage_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlaneManGUI planemangui = new PlaneManGUI();
				planemangui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				planemangui.setLocationRelativeTo(null);
				planemangui.setVisible(true);
			}
		});
		mntmPlanes.add(mntmManage_5);

		JMenu mntmFlights = new JMenu("Flights");
		TRANSPORT.add(mntmFlights);

		JMenuItem mntmNew_6 = new JMenuItem("New");
		mntmNew_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddFlightGUI addflightgui = new AddFlightGUI();
				addflightgui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				addflightgui.setLocationRelativeTo(null);
				addflightgui.setVisible(true);
			}
		});
		mntmFlights.add(mntmNew_6);

		JMenuItem mntmManage_6 = new JMenuItem("Manage");
		mntmManage_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FlightManGUI flightmangui = new FlightManGUI();
				flightmangui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				flightmangui.setLocationRelativeTo(null);
				flightmangui.setVisible(true);
			}
		});
		mntmFlights.add(mntmManage_6);

		JMenu HOTELS = new JMenu("Hotels");
		HOTELS.setEnabled(false);
		menuBar.add(HOTELS);

		JMenu mnHotels_1 = new JMenu("Hotels");
		HOTELS.add(mnHotels_1);

		JMenuItem mntmNew_9 = new JMenuItem("New");
		mntmNew_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddHotelGUI ahg = new AddHotelGUI();
				ahg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				ahg.setLocationRelativeTo(null);
				ahg.setVisible(true);
			}
		});
		mnHotels_1.add(mntmNew_9);

		JMenuItem mntmManage_9 = new JMenuItem("Manage");
		mntmManage_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotelManGUI hmg = new HotelManGUI();
				hmg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				hmg.setLocationRelativeTo(null);
				hmg.setVisible(true);
			}
		});
		mnHotels_1.add(mntmManage_9);

		JMenu mnFloors = new JMenu("Floors");
		HOTELS.add(mnFloors);

		JMenuItem mntmNew_10 = new JMenuItem("New");
		mntmNew_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFloorGUI addfloorgui = new AddFloorGUI();
				addfloorgui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				addfloorgui.setLocationRelativeTo(null);
				addfloorgui.setVisible(true);
			}
		});
		mnFloors.add(mntmNew_10);

		JMenuItem mntmManage_10 = new JMenuItem("Manage");
		mntmManage_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FloorManGUI fmg = new FloorManGUI();
				fmg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				fmg.setLocationRelativeTo(null);
				fmg.setVisible(true);
			}
		});
		mnFloors.add(mntmManage_10);

		JMenu mnRooms = new JMenu("Rooms");
		HOTELS.add(mnRooms);

		JMenuItem mntmNew_11 = new JMenuItem("New");
		mntmNew_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRoomGUI arg = new AddRoomGUI();
				arg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				arg.setLocationRelativeTo(null);
				arg.setVisible(true);
			}
		});
		mnRooms.add(mntmNew_11);

		JMenuItem mntmManage_11 = new JMenuItem("Manage");
		mntmManage_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomManGUI rmg = new RoomManGUI();
				rmg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				rmg.setLocationRelativeTo(null);
				rmg.setVisible(true);
			}
		});
		mnRooms.add(mntmManage_11);

		JMenu PACKAGES = new JMenu("Packages");
		PACKAGES.setEnabled(false);
		menuBar.add(PACKAGES);

		JMenuItem mntmNew_2 = new JMenuItem("New");
		mntmNew_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPackageGUI apg = new AddPackageGUI();
				apg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				apg.setLocationRelativeTo(null);
				apg.setVisible(true);
			}
		});
		PACKAGES.add(mntmNew_2);

		JMenuItem mntmManage_2 = new JMenuItem("Manage");
		mntmManage_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PackageManGUI pmg = new PackageManGUI();
				pmg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				pmg.setLocationRelativeTo(null);
				pmg.setVisible(true);
			}
		});
		PACKAGES.add(mntmManage_2);

		JMenu REPORTS = new JMenu("Reports");
		REPORTS.setEnabled(false);
		menuBar.add(REPORTS);

		JMenu Tools = new JMenu("Tools");
		Tools.setEnabled(false);
		menuBar.add(Tools);

		JMenuItem tmManage_13 = new JMenuItem("History");
		tmManage_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TraceManGUI tmg = new TraceManGUI();
				tmg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				tmg.setLocationRelativeTo(null);
				tmg.setVisible(true);
			}
		});
		Tools.add(tmManage_13);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		loglabel = new JLabel("");
		loglabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		loglabel.setForeground(Color.BLUE);
		loglabel.setBackground(Color.WHITE);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(199)
																		.addComponent(
																				panel,
																				GroupLayout.PREFERRED_SIZE,
																				283,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																loglabel,
																GroupLayout.PREFERRED_SIZE,
																144,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(242, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addComponent(loglabel, GroupLayout.PREFERRED_SIZE, 43,
								GroupLayout.PREFERRED_SIZE)
						.addGap(55)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 215,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(116, Short.MAX_VALUE)));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.ORANGE);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});

		JLabel lblUsername = new JLabel("Username");

		JLabel lblPassword = new JLabel("Password");

		musername = new JTextField();
		musername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		musername.setColumns(10);

		mpassword = new JPasswordField();
		mpassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		mpassword.setColumns(10);

		msglabel = new JLabel("");
		msglabel.setForeground(Color.RED);
		msglabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addGroup(
																										gl_panel.createParallelGroup(
																												Alignment.LEADING)
																												.addComponent(
																														lblPassword)
																												.addComponent(
																														lblUsername))
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addGroup(
																										gl_panel.createParallelGroup(
																												Alignment.TRAILING)
																												.addComponent(
																														musername,
																														GroupLayout.DEFAULT_SIZE,
																														204,
																														Short.MAX_VALUE)
																												.addComponent(
																														mpassword,
																														GroupLayout.DEFAULT_SIZE,
																														204,
																														Short.MAX_VALUE)))
																				.addComponent(
																						btnNewButton,
																						Alignment.TRAILING)))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(33)
																.addComponent(
																		msglabel,
																		GroupLayout.PREFERRED_SIZE,
																		226,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(30)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblUsername)
												.addComponent(
														musername,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblPassword)
												.addComponent(
														mpassword,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnNewButton).addGap(18)
								.addComponent(msglabel)
								.addContainerGap(41, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

	}

	/**
	 * 
	 */
	public void login() {
		users = remote.getAllUsers();
		String username = musername.getText();
		String password = new String(mpassword.getPassword());
		for (User user : users) {
			if (user.getUserName().equals(username)
					&& user.getUserPassword().equals(password)) {
				Authenticate auth = new Authenticate();
				auth.createSession(username, user.getUserRole().toString());

				List<Privilege> privs = user.getUserRole().getPrivileges();

				for (Privilege priv : privs) {
					for (Component child : menuBar.getComponents()) {
						if (child instanceof JMenu) {

							if (priv.getNamePrivilege().equalsIgnoreCase(
									((JMenu) child).getText()))
								child.setEnabled(true);
							// child.getParent().setEnabled(true);
						}
					}

				}
				panel.setVisible(false);
				loglabel.setText(auth.loadSession());
			} else
				msglabel.setText("Wrong username or password");
			musername.setText("");
			mpassword.setText("");

		}
	}
}
