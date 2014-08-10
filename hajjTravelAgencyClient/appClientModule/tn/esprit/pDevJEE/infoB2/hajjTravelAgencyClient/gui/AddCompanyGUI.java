package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Company;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement.CompanyManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class AddCompanyGUI extends JFrame {

	private JPanel contentPane;
	private JTextField cname;
	private JTextField caddress;
	private JTextField cemail;
	private JTextField cphone;
	private CompanyManRemote remote;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCompanyGUI frame = new AddCompanyGUI();
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
	public AddCompanyGUI() {
		remote = (CompanyManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/CompanyMan!"
								+ CompanyManRemote.class.getCanonicalName());
		setTitle("New Company");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Add Company", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		
		JLabel lblName = new JLabel("Name");
		
		JLabel lblAddress = new JLabel("Address");
		
		JLabel lblEmail = new JLabel("Email");
		
		JLabel lblPhone = new JLabel("Phone");
		
		cname = new JTextField();
		cname.setColumns(10);
		
		caddress = new JTextField();
		caddress.setColumns(10);
		
		cemail = new JTextField();
		cemail.setColumns(10);
		
		cphone = new JTextField();
		cphone.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Company company=new Company();
				company.setNameCompany(cname.getText());
				company.setAddressCompany(caddress.getText());
				company.setEmailCompany(cemail.getText());
				company.setPhone(cphone.getText());
				remote.addCompany(company);
				dispose();
				
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
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(lblAddress)
						.addComponent(lblEmail)
						.addComponent(lblPhone))
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(cemail)
							.addComponent(caddress)
							.addComponent(cname, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
						.addComponent(cphone, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(54, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(292, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnClear)
						.addComponent(btnAdd))
					.addGap(41))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(cname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress)
						.addComponent(caddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(cemail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhone)
						.addComponent(cphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClear)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
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
