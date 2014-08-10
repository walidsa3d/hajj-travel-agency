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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.PGroup;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pilgrim;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement.GroupManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement.PilgrimManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class AddGroupGUI extends JFrame {

	private JPanel contentPane;
	private JTextField gnumber;
	private JTextField gminsize;
	private JTextField gmaxsize;
	private GroupManRemote remote;
	private PilgrimManRemote remote2;
	private List<Pilgrim> pilgrims;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGroupGUI frame = new AddGroupGUI();
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
	public AddGroupGUI() {
		remote = (GroupManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/GroupMan!"
								+ GroupManRemote.class.getCanonicalName());
		remote2 = (PilgrimManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/PilgrimMan!"
								+ PilgrimManRemote.class.getCanonicalName());
		pilgrims=remote2.getAllPilgrims();
		setTitle("New Pilgrim Group");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "New Pilgrim Group", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 255)));
		setContentPane(contentPane);
		
		JLabel lblNumber = new JLabel("Number");
		
		JLabel lblMinSize = new JLabel("Min Size");
		
		JLabel lblMaxSize = new JLabel("Max Size");
		
		JLabel lblLeader = new JLabel("Leader");
		
		gnumber = new JTextField();
		gnumber.setColumns(10);
		
		gminsize = new JTextField();
		gminsize.setColumns(10);
		
		gmaxsize = new JTextField();
		gmaxsize.setColumns(10);
		
		comboBox = new JComboBox();
		
		JButton btnAdd = new JButton("Add"); //TODO subleader
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PGroup pgroup=new PGroup();
				pgroup.setGroupNumber(Integer.parseInt(gnumber.getText()));
				pgroup.setGroupMinSize(Integer.parseInt(gminsize.getText()));
				pgroup.setGroupMaxSize(Integer.parseInt(gmaxsize.getText()));
				remote.addGroup(pgroup);
			}
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPanelTextBoxes(contentPane);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNumber)
						.addComponent(lblMinSize)
						.addComponent(lblMaxSize)
						.addComponent(lblLeader))
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(comboBox, 0, 160, Short.MAX_VALUE)
						.addComponent(gmaxsize)
						.addComponent(gminsize)
						.addComponent(gnumber))
					.addContainerGap(163, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(264, Short.MAX_VALUE)
					.addComponent(btnClear)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAdd)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumber)
						.addComponent(gnumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblMinSize)
						.addComponent(gminsize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblMaxSize)
						.addComponent(gmaxsize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLeader)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnClear))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}
	private void clearPanelTextBoxes(Container co)
	{

	Component[] components = co.getComponents();
	JTextField t = new JTextField();
	JTextArea tx = new JTextArea();

	for ( Component c : components )
	{
	if (c instanceof JTextField )
	{
	t = ( JTextField ) c ;
	t.setText("");//cleat the fields 
	
	}
	if (c instanceof JTextArea ) 
	{tx = ( JTextArea ) c ;
	tx.setText("");//cleat the fields 
	}
	if (c instanceof Container ) clearPanelTextBoxes((Container) c);
	
	}
	}
	protected void initDataBindings() {
		JComboBoxBinding<Pilgrim, List<Pilgrim>, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, pilgrims, comboBox);
		jComboBinding.bind();
	}
}
