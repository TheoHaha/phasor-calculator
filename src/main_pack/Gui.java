package main_pack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import javax.swing.ListSelectionModel;

import numbers.*;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.Component;
import javax.swing.ImageIcon;

public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField result;
	private JTextField opResultLabel;
	
	private ButtonGroup numtypeBGroup = new ButtonGroup();
	private ButtonGroup operationBGroup = new ButtonGroup();
	private ButtonGroup resultNumTypeBGroup = new ButtonGroup();
	
	private Complex complex = new Complex(0,0);
	private Phasor phasor = new Phasor(0,0);
	private MyNumber resultNum = null;
	
	protected boolean isComplex = true;
	/**
	 * Create the frame.
	 */
	public Gui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Gui.class.getResource("/main_pack/phasor_icon--.png")));
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Phasor Calculator\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 405);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 355, 376);
		contentPane.add(tabbedPane);
		
		JPanel listMaker = new JPanel();
		tabbedPane.addTab("Number List", null, listMaker, null);
		listMaker.setLayout(null);
		
		JPanel inputPane = new JPanel();
		inputPane.setBounds(10, 11, 150, 47);
		listMaker.add(inputPane);
		GridBagLayout gbl_inputPane = new GridBagLayout();
		gbl_inputPane.columnWidths = new int[]{58, 75, 0};
		gbl_inputPane.rowHeights = new int[]{22, 22, 0};
		gbl_inputPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_inputPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		inputPane.setLayout(gbl_inputPane);
		
		JLabel xLabel = new JLabel("Enter x");
		xLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_xLabel = new GridBagConstraints();
		gbc_xLabel.fill = GridBagConstraints.VERTICAL;
		gbc_xLabel.insets = new Insets(0, 0, 5, 5);
		gbc_xLabel.gridx = 0;
		gbc_xLabel.gridy = 0;
		inputPane.add(xLabel, gbc_xLabel);
		
		JSpinner xInput = new JSpinner();
		xInput.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				complex.setX((double)xInput.getValue());
				phasor.setZ((double)xInput.getValue());
				if (isComplex)
					result.setText(complex.toString());
				else
					result.setText(phasor.toString());
			}
		});
		xInput.setModel(new SpinnerNumberModel(0.0, null, null, 0.1));
		GridBagConstraints gbc_xInput = new GridBagConstraints();
		gbc_xInput.fill = GridBagConstraints.BOTH;
		gbc_xInput.insets = new Insets(0, 0, 5, 0);
		gbc_xInput.gridx = 1;
		gbc_xInput.gridy = 0;
		inputPane.add(xInput, gbc_xInput);
		
		JLabel yLabel = new JLabel("Enter y");
		yLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_yLabel = new GridBagConstraints();
		gbc_yLabel.fill = GridBagConstraints.VERTICAL;
		gbc_yLabel.insets = new Insets(0, 0, 0, 5);
		gbc_yLabel.gridx = 0;
		gbc_yLabel.gridy = 1;
		inputPane.add(yLabel, gbc_yLabel);
		
		JSpinner yInput = new JSpinner();
		yInput.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) 
			{
				complex.setY((double)yInput.getValue());
				phasor.setPhase((double)yInput.getValue());
				if (isComplex)
					result.setText(complex.toString());
				else
					result.setText(phasor.toString());
			}
		});
		yInput.setModel(new SpinnerNumberModel(0.0, null, null, 0.1));
		GridBagConstraints gbc_yInput = new GridBagConstraints();
		gbc_yInput.fill = GridBagConstraints.BOTH;
		gbc_yInput.gridx = 1;
		gbc_yInput.gridy = 1;
		inputPane.add(yInput, gbc_yInput);
		
		JPanel numTypePane = new JPanel();
		numTypePane.setBounds(10, 65, 150, 50);
		listMaker.add(numTypePane);
		FlowLayout fl_numTypePane = new FlowLayout(FlowLayout.LEADING, 0, 1);
		numTypePane.setLayout(fl_numTypePane);
		
		JRadioButton radioComplex = new JRadioButton("Complex Number");
		radioComplex.setToolTipText("Complex numbers look like this: \r\na = x + i*y");
		radioComplex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				xLabel.setText("Enter x");
				yLabel.setText("Enter y");
				isComplex = true;
				result.setText(complex.toString());
			}
		});
		numTypePane.add(radioComplex);
		radioComplex.setSelected(true);
		numtypeBGroup.add(radioComplex);
		
		JRadioButton radioPhasor = new JRadioButton("Phasor");
		radioPhasor.setToolTipText("Phasors look like this: \r\na = z | φ°");
		radioPhasor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				xLabel.setText("Enter z");
				yLabel.setText("Enter φ");
				isComplex = false;
				result.setText(phasor.toString());
			}
		});
		numTypePane.add(radioPhasor);
		
		numtypeBGroup.add(radioPhasor);
		
		result = new JTextField();
		result.setBounds(10, 123, 150, 25);
		listMaker.add(result);
		result.setText("0");
		result.setEditable(false);
		result.setHorizontalAlignment(SwingConstants.CENTER);
		result.setColumns(10);
		
		JScrollPane numberListPane = new JScrollPane();
		numberListPane.setBounds(176, 11, 150, 316);
		listMaker.add(numberListPane);
		DefaultListModel<String> numberListModel = new DefaultListModel<String>();
		JList<String> numberList = new JList<String>(numberListModel);
		numberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		numberListPane.setViewportView(numberList);
		
		JPanel moreButtons = new JPanel();
		moreButtons.setBounds(10, 154, 150, 75);
		listMaker.add(moreButtons);
		moreButtons.setLayout(new GridLayout(0, 1, 0, 2));
		
		JButton addToList = new JButton("Add to list");
		addToList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (isComplex)
					numberListModel.addElement(complex.toString());
				else
					numberListModel.addElement(phasor.toString());
			}
		});
		moreButtons.add(addToList);
		
		JButton removeFromList = new JButton("Remove from list");
		removeFromList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				try {
					numberListModel.removeElementAt(numberList.getSelectedIndex());
				}catch(Exception e) {
					if (numberListModel.isEmpty())
						JOptionPane.showMessageDialog(moreButtons, "The number list is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
					else
						JOptionPane.showMessageDialog(moreButtons, "Please select an item to remove from the number list.", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		moreButtons.add(removeFromList);
		
		JButton reset = new JButton("Reset");
		moreButtons.add(reset);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				complex.setX(0);
				phasor.setZ(0);
				
				complex.setY(0);
				phasor.setPhase(0);
				
				xInput.setValue(0.0);
				yInput.setValue(0.0);
				
				radioComplex.setSelected(true);
				radioPhasor.setSelected(false);
				isComplex = true;
				
				xLabel.setText("Enter x");
				yLabel.setText("Enter y");
				
				result.setText("0");
				opResultLabel.setText("Result: ");
				
				resultNum = null;
				
				numberListModel.clear();
			}
		});
		
		JLabel logo1 = new JLabel("v1.0");
		logo1.setFont(new Font("Tahoma", Font.BOLD, 11));
		logo1.setBounds(10, 240, 150, 87);
		listMaker.add(logo1);
		logo1.setHorizontalAlignment(SwingConstants.CENTER);
		logo1.setIcon(new ImageIcon(Gui.class.getResource("/main_pack/phasor_icon--.png")));
		
		JPanel calculator = new JPanel();
		tabbedPane.addTab("Calculator", null, calculator, null);
		calculator.setLayout(null);
		
		JScrollPane calcListPane1 = new JScrollPane();
		calcListPane1.setBounds(10, 11, 125, 160);
		calculator.add(calcListPane1);
		
		JList<String> calcList1 = new JList<String>(numberListModel);
		calcListPane1.setViewportView(calcList1);
		
		JScrollPane calcListPane2 = new JScrollPane();
		calcListPane2.setBounds(201, 11, 125, 160);
		calculator.add(calcListPane2);
		
		JList<String> calcList2 = new JList<String>(numberListModel);
		calcListPane2.setViewportView(calcList2);
		
		JPanel operationPane = new JPanel();
		operationPane.setBounds(145, 11, 50, 160);
		calculator.add(operationPane);
		operationPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JRadioButton radioPlus = new JRadioButton("+");
		radioPlus.setSelected(true);
		radioPlus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		operationPane.add(radioPlus);
		
		JRadioButton radioMinus = new JRadioButton("-");
		radioMinus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		operationPane.add(radioMinus);
		
		JRadioButton radioTimes = new JRadioButton("*");
		radioTimes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		operationPane.add(radioTimes);
		
		JRadioButton radioDivBy = new JRadioButton("/");
		radioDivBy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		operationPane.add(radioDivBy);
		
		operationBGroup.add(radioPlus);
		operationBGroup.add(radioMinus);
		operationBGroup.add(radioTimes);
		operationBGroup.add(radioDivBy);
		
		JScrollPane resultPane = new JScrollPane();
		resultPane.setBounds(10, 219, 316, 50);
		calculator.add(resultPane);
		
		opResultLabel = new JTextField("Result: ");
		resultPane.setViewportView(opResultLabel);
		opResultLabel.setEditable(false);
		
		JPanel resultNumType = new JPanel();
		resultNumType.setBounds(10, 182, 316, 26);
		calculator.add(resultNumType);
		
		JRadioButton radioResultC = new JRadioButton("Complex");
		radioResultC.setHorizontalAlignment(SwingConstants.LEFT);
		radioResultC.setHorizontalTextPosition(SwingConstants.LEFT);
		radioResultC.setSelected(true);
		radioResultC.setVerticalAlignment(SwingConstants.TOP);
		
		JRadioButton radioResultPh = new JRadioButton("Phasor");
		radioResultPh.setHorizontalTextPosition(SwingConstants.LEFT);
		radioResultPh.setHorizontalAlignment(SwingConstants.LEFT);
		radioResultPh.setVerticalAlignment(SwingConstants.TOP);
		
		resultNumTypeBGroup.add(radioResultC);
		resultNumTypeBGroup.add(radioResultPh);
		resultNumType.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		JButton equals = new JButton("Result");
		equals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				try {
					String choice1 = "";
					String choice2 = "";
					
					choice1 = (String)calcList1.getSelectedValue();
					choice2 = (String)calcList2.getSelectedValue();
					
					MyNumber a1,a2;
					
					if(choice1.contains("|"))
					{
						a1 = Phasor.parsePhasor(choice1);
					}
					else
					{
						a1 = Complex.parseComplex(choice1);
					}
					
					if(choice2.contains("|"))
					{
						a2 = Phasor.parsePhasor(choice2);
					}
					else
					{
						a2 = Complex.parseComplex(choice2);
					}
					
					if(radioResultC.isSelected())
					{
						if (radioPlus.isSelected())
						{
							resultNum = Complex.add(a1, a2);
							opResultLabel.setText("Result: "+resultNum.toString());
						}
						else if (radioMinus.isSelected())
						{
							resultNum = Complex.sub(a1, a2);
							opResultLabel.setText("Result: "+resultNum.toString());
						}
						else if (radioTimes.isSelected())
						{
							resultNum = Complex.mul(a1, a2);
							opResultLabel.setText("Result: "+resultNum.toString());
						}
						else if (radioDivBy.isSelected())
						{
							resultNum = Complex.div(a1, a2);
							opResultLabel.setText("Result: "+resultNum.toString());
						}
					}
					else if(radioResultPh.isSelected())
					{
						if (radioPlus.isSelected())
						{
							resultNum = Phasor.add(a1, a2);
							opResultLabel.setText("Result: "+((Phasor)resultNum).toString());
						}
						else if (radioMinus.isSelected())
						{
							resultNum = Phasor.sub(a1, a2);
							opResultLabel.setText("Result: "+((Phasor)resultNum).toString());
						}
						else if (radioTimes.isSelected())
						{
							resultNum = Phasor.mul(a1, a2);
							opResultLabel.setText("Result: "+((Phasor)resultNum).toString());
						}
						else if (radioDivBy.isSelected())
						{
							resultNum = Phasor.div(a1, a2);
							opResultLabel.setText("Result: "+((Phasor)resultNum).toString());
						}
					}
				
				}catch(NullPointerException e) {
					if (numberListModel.isEmpty())
						JOptionPane.showMessageDialog(moreButtons, "The number list is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
					else
						JOptionPane.showMessageDialog(moreButtons, "Please select an item from both lists to complete the operation.", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		resultNumType.add(equals);
		resultNumType.add(radioResultC);
		resultNumType.add(radioResultPh);
		
		JPanel moremorebuttons = new JPanel();
		moremorebuttons.setBounds(91, 276, 156, 50);
		calculator.add(moremorebuttons);
		moremorebuttons.setLayout(new GridLayout(0, 1, 0, 2));
		
		JButton resultAddToList = new JButton("Add result to list");
		moremorebuttons.add(resultAddToList);
		resultAddToList.setAlignmentY(0.0f);
		resultAddToList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					if (resultNum.getClass()==new Complex().getClass())
						numberListModel.addElement(((Complex)resultNum).toString());
					else if (resultNum.getClass()==new Phasor().getClass())
						numberListModel.addElement(((Phasor)resultNum).toString());
				}catch(NullPointerException exception) {
					JOptionPane.showMessageDialog(new JPanel(), "There is no result to add to the list!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		JButton reset2 = new JButton("Reset");
		moremorebuttons.add(reset2);
		reset2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				complex.setX(0);
				phasor.setZ(0);
				
				complex.setY(0);
				phasor.setPhase(0);
				
				xInput.setValue(0.0);
				yInput.setValue(0.0);
				
				radioComplex.setSelected(true);
				radioPhasor.setSelected(false);
				isComplex = true;
				
				xLabel.setText("Enter x");
				yLabel.setText("Enter y");
				
				result.setText("0");
				opResultLabel.setText("Result: ");
				
				resultNum = null;
				
				numberListModel.clear();
			}
		});
		
		JPanel aboutpage = new JPanel();
		tabbedPane.addTab("About...", null, aboutpage, (String) null);
		aboutpage.setLayout(null);
		
		JLabel lblCopyrightLolololol = new JLabel("Copyright© lolololol");
		lblCopyrightLolololol.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCopyrightLolololol.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCopyrightLolololol.setForeground(SystemColor.textHighlight);
		lblCopyrightLolololol.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblCopyrightLolololol.setBounds(206, 57, 119, 14);
		aboutpage.add(lblCopyrightLolololol);
		
		JLabel logo2 = new JLabel("Theo's Phasor Calc");
		logo2.setForeground(SystemColor.textHighlight);
		logo2.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 26));
		logo2.setIcon(new ImageIcon(Gui.class.getResource("/main_pack/phasor_icon--.png")));
		logo2.setBounds(10, 11, 315, 60);
		aboutpage.add(logo2);
		
		JPanel links = new JPanel();
		links.setBounds(10, 300, 315, 24);
		aboutpage.add(links);
		
		JHyperlink twitterHyperlink = new JHyperlink("Twitter", "https://twitter.com/Theo_xde", "Message me on Twitter if you encounter any problems with this app or if you have any suggestions.\r \r\nMake sure you tell me that you came from here!");
		twitterHyperlink.setAlignmentX(Component.CENTER_ALIGNMENT);
		links.add(twitterHyperlink);
		
		JLabel seperator = new JLabel("|");
		seperator.setAlignmentX(Component.CENTER_ALIGNMENT);
		links.add(seperator);
		
		JHyperlink githubHyperlink = new JHyperlink("GitHub", "https://github.com/TheoHaha", "My GitHub profile. It's mostly empty (for now!) but this is where I'll post updates for this app.");
		githubHyperlink.setAlignmentX(0.5f);
		links.add(githubHyperlink);
		
		JLabel seperator_1 = new JLabel("|");
		seperator_1.setAlignmentX(0.5f);
		links.add(seperator_1);
		
		JHyperlink licenseHyperlink = new JHyperlink("GNU GPLv3", "https://spdx.org/licenses/GPL-3.0.html", "This is the license of this program.");
		links.add(licenseHyperlink);
		licenseHyperlink.setAlignmentX(0.5f);
		
		JLabel lblCurrentVersion = new JLabel("Current version: 1.0");
		lblCurrentVersion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCurrentVersion.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurrentVersion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentVersion.setBounds(10, 72, 315, 24);
		aboutpage.add(lblCurrentVersion);
		
		JTextArea about = new JTextArea();
		about.setWrapStyleWord(true);
		about.setLineWrap(true);
		about.setBackground(SystemColor.control);
		about.setFont(new Font("Tahoma", Font.PLAIN, 11));
		about.setEditable(false);
		about.setText("This is a program created by Theodoros Michailidis which aims to help students or anyone who has to deal with phasors and complex numbers in their day to day lives.\r\n\r\nI actually made this for my own use and for the sake of practice, but you can use it too. Feel free to do so if you think it's going to help you. You can even take a look into the source code and maybe learn something from it. \r\n\r\nI'm more than open to advice on how to improve it.\r\n\r\nThis program is and will always be 100% free to use, modify, redistribute, and anything else that is entailed by the GNU GPLv3.");
		about.setBounds(10, 97, 315, 240);
		aboutpage.add(about);
	}
}
