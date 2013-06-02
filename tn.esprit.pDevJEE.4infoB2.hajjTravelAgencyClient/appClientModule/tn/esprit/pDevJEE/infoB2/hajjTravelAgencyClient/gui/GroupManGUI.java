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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.PGroup;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pilgrim;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement.GroupManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class GroupManGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField gid;
	private JTextField gminsize;
	private JTextField gmaxsize;
	private GroupManRemote remote;
	private List<PGroup> groups;
	private JComboBox gleader;
	private JTextField gnumber;

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
					GroupManGUI frame = new GroupManGUI();
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
	public GroupManGUI() {
		remote = (GroupManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/GroupMan!"
								+ GroupManRemote.class.getCanonicalName());
		groups = remote.getAllGroups();
		setTitle("Pilgrim Groups Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null,
				"Pilgrim Groups Management", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_contentPane
								.createSequentialGroup()
								.addComponent(panel,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
				.addGroup(
						gl_contentPane
								.createSequentialGroup()
								.addGap(20)
								.addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 496,
										Short.MAX_VALUE).addGap(28)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
								142, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 176,
								Short.MAX_VALUE).addContainerGap()));

		JLabel lblNewLabel = new JLabel("ID");

		JLabel lblMinSize = new JLabel("Min Size");

		JLabel lblMaxSize = new JLabel("Max Size");

		JLabel lblLeader = new JLabel("Leader");

		gid = new JTextField();
		gid.setEditable(false);
		gid.setColumns(10);

		gminsize = new JTextField();
		gminsize.setColumns(10);

		gmaxsize = new JTextField();
		gmaxsize.setColumns(10);

		gleader = new JComboBox();

		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddGroupGUI agg = new AddGroupGUI();
				agg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				agg.setLocationRelativeTo(null);
				agg.setVisible(true);

			}
		});

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PGroup pgroup = new PGroup();
				pgroup.setIdGroup(Integer.parseInt(gid.getText()));
				pgroup.setGroupNumber(Integer.parseInt(gnumber.getText()));
				pgroup.setGroupMinSize(Integer.parseInt(gminsize.getText()));
				pgroup.setGroupMaxSize(Integer.parseInt(gmaxsize.getText()));
				pgroup.setSubLeader((Pilgrim) gleader.getSelectedItem());
				remote.updateGroup(pgroup);
				groups = remote.getAllGroups();
				initDataBindings();

			}
		});

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PGroup pgroup = new PGroup();
				pgroup.setIdGroup(Integer.parseInt(gid.getText()));
				remote.removeGroup(pgroup);
				groups = remote.getAllGroups();
				initDataBindings();
			}
		});

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				groups = remote.getAllGroups();
				initDataBindings();
			}
		});

		gnumber = new JTextField();
		gnumber.setColumns(10);

		JLabel lblNumber = new JLabel("Number");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(20)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(lblMinSize)
												.addComponent(lblNewLabel)
												.addComponent(
														lblMaxSize,
														GroupLayout.PREFERRED_SIZE,
														50,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNumber)
												.addComponent(lblLeader))
								.addGap(26)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														gleader,
														GroupLayout.PREFERRED_SIZE,
														106,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						gid,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						gminsize,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						gmaxsize,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						gnumber,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE))
																.addGap(194)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						btnNew,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addGroup(
																						gl_panel.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																								.addComponent(
																										btnUpdate,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(
																										btnRefresh,
																										Alignment.LEADING,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(
																										btnRemove,
																										Alignment.LEADING,
																										GroupLayout.DEFAULT_SIZE,
																										85,
																										Short.MAX_VALUE)))))
								.addContainerGap(141, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING, false)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(19)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.BASELINE)
																				.addComponent(
																						gid,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						lblNewLabel))
																.addPreferredGap(
																		ComponentPlacement.UNRELATED))
												.addGroup(
														Alignment.TRAILING,
														gl_panel.createSequentialGroup()
																.addContainerGap(
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		btnNew)
																.addGap(4)))
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														gminsize,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblMinSize)
												.addComponent(btnUpdate))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(btnRemove)
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.BASELINE)
																.addComponent(
																		gmaxsize,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		lblMaxSize)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.BASELINE)
																.addComponent(
																		gnumber,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		lblNumber))
												.addComponent(btnRefresh))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														gleader,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblLeader))
								.addContainerGap(33, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		table = new JTable();
		table.setBackground(Color.YELLOW);
		table.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null },
				{ null, null, null, null, null }, }, new String[] { "ID",
				"Number", "Min Size", "Max Size", "Leader" }));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}

	protected void initDataBindings() {
		JTableBinding<PGroup, List<PGroup>, JTable> jTableBinding = SwingBindings
				.createJTableBinding(UpdateStrategy.READ_WRITE, groups, table);
		//
		BeanProperty<PGroup, Integer> pGroupBeanProperty = BeanProperty
				.create("idGroup");
		jTableBinding.addColumnBinding(pGroupBeanProperty).setColumnName("ID");
		//
		BeanProperty<PGroup, Integer> pGroupBeanProperty_1 = BeanProperty
				.create("groupNumber");
		jTableBinding.addColumnBinding(pGroupBeanProperty_1).setColumnName(
				"Number");
		//
		BeanProperty<PGroup, Integer> pGroupBeanProperty_2 = BeanProperty
				.create("groupMinSize");
		jTableBinding.addColumnBinding(pGroupBeanProperty_2).setColumnName(
				"Min Size");
		//
		BeanProperty<PGroup, Integer> pGroupBeanProperty_3 = BeanProperty
				.create("groupMaxSize");
		jTableBinding.addColumnBinding(pGroupBeanProperty_3).setColumnName(
				"Max Size");
		//
		BeanProperty<PGroup, Pilgrim> pGroupBeanProperty_4 = BeanProperty
				.create("subLeader");
		jTableBinding.addColumnBinding(pGroupBeanProperty_4).setColumnName(
				"Leader");
		//
		jTableBinding.bind();
		//
		BeanProperty<JTable, Integer> jTableBeanProperty = BeanProperty
				.create("selectedElement.idGroup");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<JTable, Integer, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, table,
						jTableBeanProperty, gid, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<JTable, Integer> jTableBeanProperty_1 = BeanProperty
				.create("selectedElement.groupMinSize");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<JTable, Integer, JTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, table,
						jTableBeanProperty_1, gminsize,
						jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<JTable, Integer> jTableBeanProperty_2 = BeanProperty
				.create("selectedElement.groupMaxSize");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty
				.create("text");
		AutoBinding<JTable, Integer, JTextField, String> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, table,
						jTableBeanProperty_2, gmaxsize,
						jTextFieldBeanProperty_2);
		autoBinding_2.bind();
		//
		BeanProperty<JTable, Pilgrim> jTableBeanProperty_3 = BeanProperty
				.create("selectedElement.subLeader");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty = BeanProperty
				.create("selectedItem");
		AutoBinding<JTable, Pilgrim, JComboBox, Object> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, table,
						jTableBeanProperty_3, gleader, jComboBoxBeanProperty);
		autoBinding_3.bind();
		//
		BeanProperty<JTable, Integer> jTableBeanProperty_4 = BeanProperty
				.create("selectedElement.groupNumber");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_3 = BeanProperty
				.create("text");
		AutoBinding<JTable, Integer, JTextField, String> autoBinding_4 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, table,
						jTableBeanProperty_4, gnumber, jTextFieldBeanProperty_3);
		autoBinding_4.bind();
	}
}
