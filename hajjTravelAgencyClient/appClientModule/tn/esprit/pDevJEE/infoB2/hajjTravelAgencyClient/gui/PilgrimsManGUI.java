package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pilgrim;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement.PilgrimManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

import com.toedter.calendar.JDateChooser;

public class PilgrimsManGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3239306466423069671L;
	private JPanel contentPane;
	private JTable table;
	private PilgrimManRemote remote;
	private List<Pilgrim> pilgrims;
	private JTextField pfirstname;
	private JTextField plastname;
	private JTextField paddress;
	private JTextField pemail;
	private JTextField pphone;
	private JDateChooser pbirthdate;
	private JComboBox<?> pgender;
	private JTextField pcin;
	private JTextField ppassport;
	private JComboBox pmahrem;
	private List<Pilgrim> malePilgrims;

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
					PilgrimsManGUI frame = new PilgrimsManGUI();
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
	public PilgrimsManGUI() {
		setResizable(false);
		setBackground(Color.BLUE);
		remote = (PilgrimManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/PilgrimMan!"
								+ PilgrimManRemote.class.getCanonicalName());		
		
		pilgrims=remote.getAllPilgrims();
		malePilgrims=remote.getPilgrimsByGender("Male");
//	SwingWorker<List<Pilgrim>, Void> worker = new SwingWorker<List<Pilgrim>, Void>() { 
//		   @Override
//			   public List<Pilgrim> doInBackground() {
//				   pilgrims=remote.getAllPilgrims();
//			     return pilgrims;
//			   }
//			   @Override
//			   public void done() {
//			     try {
//			       pilgrims = get();
//			       initDataBindings();
//		
//		
//			     } catch (InterruptedException ex) {
//			       ex.printStackTrace();
//			     } catch (ExecutionException ex) {
//			       ex.printStackTrace();
//			     }
//			   }
//		};
//		worker.execute();
			 
		setTitle("Pilgrims Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 690);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(new TitledBorder(null, "Pilgrims Management", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		table.setBackground(Color.YELLOW);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"First", "Last", "Gender", "CIN", "Passport", "Birthdate", "Address", "Email", "Phone", "Mahrem"
			}
		));
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(66)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addGap(45))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
					.addContainerGap(65, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		
		pemail = new JTextField();
		pemail.setText("");
		pemail.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		
		pphone = new JTextField();
		pphone.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pilgrim pilgrim=new Pilgrim();
				pilgrim.setPilgrimFirstName(pfirstname.getText());
				pilgrim.setPilgrimLastName(plastname.getText());
				pilgrim.setPilgrimGender(pgender.getSelectedItem().toString());
				pilgrim.setPilgrimCin(Integer.parseInt(pcin.getText()));
				pilgrim.setPilgrimPassport(Integer.parseInt(ppassport.getText()));
				pilgrim.setPilgrimBirthDate(pbirthdate.getDate());
				pilgrim.setPilgrimAddress(paddress.getText());
				pilgrim.setPilgrimEmail(pemail.getText());
				pilgrim.setPilgrimPhone(Integer.parseInt(pphone.getText()));
				pilgrim.setMahrem((Pilgrim) pmahrem.getSelectedItem());
				remote.updatePilgrim(pilgrim);
				pilgrims=remote.getAllPilgrims();
				malePilgrims=remote.getPilgrimsByGender("Male");
				initDataBindings();
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remote.deletePilgrim(Integer.parseInt(pcin.getText()));
				pilgrims=remote.getAllPilgrims();
				malePilgrims=remote.getPilgrimsByGender("Male");
				initDataBindings();
			}
		});
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPilgrimGUI addpilgrimgui=new AddPilgrimGUI();				
				addpilgrimgui.setLocationRelativeTo(null);
				addpilgrimgui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				addpilgrimgui.setVisible(true);
				addpilgrimgui.pack();
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pilgrims=remote.getAllPilgrims();
				malePilgrims=remote.getPilgrimsByGender("Male");
				initDataBindings();
			}
		});
		
		pmahrem = new JComboBox();
		
		JLabel lblMahrem = new JLabel("Mahrem");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPhone)
								.addComponent(lblMahrem)
								.addComponent(lblNewLabel_1))
							.addGap(35)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pemail, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
								.addComponent(pphone)
								.addComponent(pmahrem, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(30)
							.addComponent(btnNew)
							.addGap(18)
							.addComponent(btnUpdate)
							.addGap(14)
							.addComponent(btnDelete)
							.addGap(18)
							.addComponent(btnRefresh)))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(pemail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone)
						.addComponent(pphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblMahrem)
						.addComponent(pmahrem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(89)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRefresh)
						.addComponent(btnNew)
						.addComponent(btnDelete)
						.addComponent(btnUpdate))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblFirstName = new JLabel("First Name");
		
		pfirstname = new JTextField();
		pfirstname.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		
		plastname = new JTextField();
		plastname.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender");
		
		pgender = new JComboBox();
		pgender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		
		JLabel lblBirthDate = new JLabel("Birth Date");
		
		pbirthdate = new JDateChooser();
		
		JLabel lblAddress = new JLabel("Address");
		
		paddress = new JTextField();
		paddress.setColumns(10);
		
		JLabel lblCin = new JLabel("CIN");
		
		pcin = new JTextField();
		pcin.setColumns(10);
		
		JLabel lblPassport = new JLabel("Passport");
		
		ppassport = new JTextField();
		ppassport.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFirstName)
								.addComponent(lblLastName)
								.addComponent(lblGender)
								.addComponent(lblAddress)
								.addComponent(lblBirthDate))
							.addGap(64)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(pgender, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addComponent(plastname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
										.addComponent(pfirstname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
										.addComponent(pcin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
										.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addComponent(pbirthdate, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
												.addGroup(gl_panel_1.createSequentialGroup()
													.addComponent(ppassport, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
													.addPreferredGap(ComponentPlacement.RELATED))
												.addComponent(paddress, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))))
									.addGap(50))))
						.addComponent(lblCin)
						.addComponent(lblPassport)))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstName)
						.addComponent(pfirstname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastName)
						.addComponent(plastname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(pgender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGender))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCin)
						.addComponent(pcin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassport)
						.addComponent(ppassport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(pbirthdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBirthDate))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress)
						.addComponent(paddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}
	protected void initDataBindings() {
		JTableBinding<Pilgrim, List<Pilgrim>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, pilgrims, table);
		//
		BeanProperty<Pilgrim, Long> pilgrimBeanProperty = BeanProperty.create("pilgrimCin");
		jTableBinding.addColumnBinding(pilgrimBeanProperty).setColumnName("CIN");
		//
		BeanProperty<Pilgrim, String> pilgrimBeanProperty_1 = BeanProperty.create("pilgrimFirstName");
		jTableBinding.addColumnBinding(pilgrimBeanProperty_1).setColumnName("First");
		//
		BeanProperty<Pilgrim, String> pilgrimBeanProperty_2 = BeanProperty.create("pilgrimLastName");
		jTableBinding.addColumnBinding(pilgrimBeanProperty_2).setColumnName("Last");
		//
		BeanProperty<Pilgrim, String> pilgrimBeanProperty_3 = BeanProperty.create("pilgrimGender");
		jTableBinding.addColumnBinding(pilgrimBeanProperty_3).setColumnName("Gender");
		//
		BeanProperty<Pilgrim, Date> pilgrimBeanProperty_4 = BeanProperty.create("pilgrimBirthDate");
		jTableBinding.addColumnBinding(pilgrimBeanProperty_4).setColumnName("BirthDate");
		//
		BeanProperty<Pilgrim, Long> pilgrimBeanProperty_5 = BeanProperty.create("pilgrimPassport");
		jTableBinding.addColumnBinding(pilgrimBeanProperty_5).setColumnName("Passport");
		//
		BeanProperty<Pilgrim, String> pilgrimBeanProperty_6 = BeanProperty.create("pilgrimAddress");
		jTableBinding.addColumnBinding(pilgrimBeanProperty_6).setColumnName("Address");
		//
		BeanProperty<Pilgrim, String> pilgrimBeanProperty_7 = BeanProperty.create("pilgrimEmail");
		jTableBinding.addColumnBinding(pilgrimBeanProperty_7).setColumnName("Email");
		//
		BeanProperty<Pilgrim, Integer> pilgrimBeanProperty_8 = BeanProperty.create("pilgrimPhone");
		jTableBinding.addColumnBinding(pilgrimBeanProperty_8).setColumnName("Phone");
		//
		BeanProperty<Pilgrim, Pilgrim> pilgrimBeanProperty_9 = BeanProperty.create("mahrem");
		jTableBinding.addColumnBinding(pilgrimBeanProperty_9).setColumnName("Mahrem");
		//
		jTableBinding.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty = BeanProperty.create("selectedElement.pilgrimAddress");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty, paddress, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<JTable, Date> jTableBeanProperty_1 = BeanProperty.create("selectedElement.pilgrimBirthDate");
		BeanProperty<JDateChooser, Date> jDateChooserBeanProperty = BeanProperty.create("date");
		AutoBinding<JTable, Date, JDateChooser, Date> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_1, pbirthdate, jDateChooserBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<JTable, Long> jTableBeanProperty_2 = BeanProperty.create("selectedElement.pilgrimCin");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<JTable, Long, JTextField, String> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_2, pcin, jTextFieldBeanProperty_1);
		autoBinding_2.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty_3 = BeanProperty.create("selectedElement.pilgrimEmail");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_3, pemail, jTextFieldBeanProperty_2);
		autoBinding_3.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty_4 = BeanProperty.create("selectedElement.pilgrimFirstName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_3 = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_4, pfirstname, jTextFieldBeanProperty_3);
		autoBinding_4.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty_5 = BeanProperty.create("selectedElement.pilgrimGender");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty = BeanProperty.create("selectedItem");
		AutoBinding<JTable, String, JComboBox, Object> autoBinding_5 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_5, pgender, jComboBoxBeanProperty);
		autoBinding_5.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty_6 = BeanProperty.create("selectedElement.pilgrimLastName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_4 = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding_6 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_6, plastname, jTextFieldBeanProperty_4);
		autoBinding_6.bind();
		//
		BeanProperty<JTable, Long> jTableBeanProperty_7 = BeanProperty.create("selectedElement.pilgrimPassport");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_5 = BeanProperty.create("text");
		AutoBinding<JTable, Long, JTextField, String> autoBinding_7 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_7, ppassport, jTextFieldBeanProperty_5);
		autoBinding_7.bind();
		//
		BeanProperty<JTable, Integer> jTableBeanProperty_8 = BeanProperty.create("selectedElement.pilgrimPhone");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_6 = BeanProperty.create("text");
		AutoBinding<JTable, Integer, JTextField, String> autoBinding_8 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_8, pphone, jTextFieldBeanProperty_6);
		autoBinding_8.bind();
		//
		BeanProperty<JTable, Pilgrim> jTableBeanProperty_9 = BeanProperty.create("selectedElement.mahrem");
		BeanProperty<JComboBox, Pilgrim> jComboBoxBeanProperty_1 = BeanProperty.create("selectedItem");
		AutoBinding<JTable, Pilgrim, JComboBox, Pilgrim> autoBinding_9 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty_9, pmahrem, jComboBoxBeanProperty_1);
		autoBinding_9.bind();
		//
		JComboBoxBinding<Pilgrim, List<Pilgrim>, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, malePilgrims, pmahrem);
		jComboBinding.bind();
	}
}
