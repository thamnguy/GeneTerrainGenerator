import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Formatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JCheckBox;

public class GeneTerrainMainUI_testView extends JFrame{

	//private JFrame this;
	GeneTerrainData terrainData;  // specific class to handle terrain data and converge between back-end data and front-end terrain (data transformation)
	ButtonEventHandler eventHandler; // handle event when clicking button
	
	File geneListFile;
	File connectionFile;
	File layoutFile;
	File maskFile;
	
	// windows components that may change during execution
	JPanel terrainPanel; // panel to draw the terrain
	JPanel colorBarPanel;
	//DrawingTerrainPanel terrainPanel; // panel to draw the terrain
	//ColorBarPanel colorBarPanel;
	
	
	JLabel nodeFileLbl; // notice the user about the node file name.
	JLabel edgeFileLbl; // notice the user about the edge file name.
	JLabel layoutFileLbl; // notice the user about the layout file name
	JTextField centerXTxt; // the x coordinate of the terrain center. Can be edited to re-center the terrain
	JTextField centerYTxt; // the y coordinate of the terrain center. Can be edited to re-center the terrain
	JTextField moveDistanceTxt; // moving distance
	JTextField zoomByTxt; // percentage of zooming
	JLabel valleyShowColorLbl; // the color of the valley
	JTextField flatHeightTxt;
	JTextField upperHeighTxt;
	JTextField lowerHeightTxt;
	JTextField peakHeightTxt;
	JTextField valleyHeightTxt;
	JTextField nodeNameTxt;
	JCheckBox viewNodeCBx; // toggle node
	JCheckBox viewEdgeCbx; // toggle edge
	JCheckBox viewSizeComparison; // toggle whether or not showing the relative comparison among node sizes
	JTextPane nodeInfoTxt;
	
	JButton createTerrainBtn; // create terrain button
	
	// width factor control
	JTextField smallInfTxt;
	JTextField largeInfTxt;
	//JCheckBox showNodeCbx;
	//JCheckBox showEdgeCbx;
	
	final int IMG_SIZE = 900;
	
	JLabel peakThresLbl; // for setting up peak threshold - in order to get the peak gene saved in text file
	JTextField peakThresTxt;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		GeneTerrainMainUI_testView testUI = new GeneTerrainMainUI_testView();
	}
	
	

	/**
	 * Create the application.
	 */
	public GeneTerrainMainUI_testView() 
	{
		initialize();
		
		//terrainData = new GeneTerrainData(this);
		//eventHandler = new ButtonEventHandler(this);
		
		//terrainPanel.addMouseListener(new MouseEventListener(terrainData, terrainPanel));
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() 
	{
		//this = new JFrame();
		this.setTitle("Gene terrain UI");
		this.setBounds(100, 100, 615+IMG_SIZE, IMG_SIZE + 47);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		//this.setVisible(true);
		
		
		// ---------- panel to display the terrain -------------
		terrainPanel = new JPanel();
		terrainPanel.setBounds(601, 11, IMG_SIZE, IMG_SIZE);
		this.getContentPane().add(terrainPanel);
		terrainPanel.setVisible(true);
		//terrainPanel.initilizePixelColor();
		
		// ------------ choose input file area -------------------
		JLabel chooseFileLabel = new JLabel("Choose Files");
		chooseFileLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		chooseFileLabel.setBounds(10, 11, 77, 19);
		this.getContentPane().add(chooseFileLabel);
		chooseFileLabel.setVisible(true);
		
		JButton choseNodeFileBtn = new JButton("Choose node file");
		choseNodeFileBtn.setToolTipText("Choose the node file <Node ID, Node value>");
		choseNodeFileBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
			//	eventHandler.choseNodeFileEvent();
			}
		});
		choseNodeFileBtn.setBounds(10, 33, 142, 23);
		this.getContentPane().add(choseNodeFileBtn);
		choseNodeFileBtn.setVisible(true);
		
		JButton choseEdgeFileBtn = new JButton("Choose edge file");
		choseEdgeFileBtn.setToolTipText("Choose the edge file <Begin Node, End Node, Streng> or <Begin Node, End Node, Streng, Direction>");
		choseEdgeFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//eventHandler.choseEdgeFileEvent();
			}
		});
		choseEdgeFileBtn.setBounds(191, 33, 141, 23);
		this.getContentPane().add(choseEdgeFileBtn);
		choseEdgeFileBtn.setVisible(true);
		
		JButton chooseLayoutFileBtn = new JButton("Choose layout file");
		chooseLayoutFileBtn.setToolTipText("Choose the layout file <Node ID, node X, nodeY, node weight>");
		chooseLayoutFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//eventHandler.choseLayoutFileEvent();
			}
		});
		chooseLayoutFileBtn.setBounds(366, 33, 147, 23);
		this.getContentPane().add(chooseLayoutFileBtn);
		chooseLayoutFileBtn.setVisible(true);
		
		nodeFileLbl = new JLabel("(not uploaded)"); // display the node file name
		nodeFileLbl.setBounds(10, 59, 142, 19);
		this.getContentPane().add(nodeFileLbl);
		nodeFileLbl.setVisible(true);
		
		edgeFileLbl = new JLabel("(not uploaded)"); // display the edge file name
		edgeFileLbl.setBounds(191, 61, 141, 14);
		this.getContentPane().add(edgeFileLbl);
		edgeFileLbl.setVisible(true);
		
		layoutFileLbl = new JLabel("(not uploaded)"); // display the layout file name
		layoutFileLbl.setBounds(366, 61, 147, 14);
		this.getContentPane().add(layoutFileLbl);
		layoutFileLbl.setVisible(true);
		
		//JButton createTerrainBtn = new JButton("Create terrain"); // create button terrain
		createTerrainBtn = new JButton("Create terrain"); // create button terrain
		createTerrainBtn.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) 
			{
				//eventHandler.createTerrainEvent();
			}
		});
		createTerrainBtn.setToolTipText("Click to create terrain");
		createTerrainBtn.setBounds(366, 87, 141, 23);
		this.getContentPane().add(createTerrainBtn);
		createTerrainBtn.setVisible(true);
		
		
		// ----------- buttons of moving terrains  ----------------------
		JLabel moveViewingCenterLbl = new JLabel("Move terrain center");
		moveViewingCenterLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		moveViewingCenterLbl.setBounds(10, 129, 113, 14);
		this.getContentPane().add(moveViewingCenterLbl);
		moveViewingCenterLbl.setVisible(true);
		
		JButton moveUpBtn = new JButton("Up"); // move up button
		moveUpBtn.setBounds(74, 145, 78, 23);
		this.getContentPane().add(moveUpBtn);
		moveUpBtn.setVisible(true);
		moveUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eventHandler.moveUpTerrainEvent();
			}
		});
		
		JButton moveDownBtn = new JButton("Down"); // move down button
		moveDownBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eventHandler.moveDownTerrainEvent();
			}
		});
		moveDownBtn.setBounds(74, 214, 78, 23);
		this.getContentPane().add(moveDownBtn);
		moveDownBtn.setVisible(true);
		
		JButton moveLeftBtn = new JButton("Left"); // move left button
		moveLeftBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eventHandler.moveLeftTerrainEvent();
			}
		});
		moveLeftBtn.setBounds(10, 179, 67, 23);
		this.getContentPane().add(moveLeftBtn);
		moveLeftBtn.setVisible(true);
		
		JButton moveRightBtn = new JButton("Right"); // move right button
		moveRightBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eventHandler.moveRightTerrainEvent();
			}
		});
		moveRightBtn.setBounds(152, 179, 77, 23);
		this.getContentPane().add(moveRightBtn);
		moveRightBtn.setVisible(true);
		
		JLabel moveDistanceLbl = new JLabel("Moving distance"); // set the moving distance when clicking up, down, left, right
		moveDistanceLbl.setBounds(238, 157, 100, 14);
		this.getContentPane().add(moveDistanceLbl);
		moveDistanceLbl.setVisible(true);
		moveDistanceTxt = new JTextField();
		moveDistanceTxt.setText("1");
		moveDistanceTxt.setBounds(260, 180, 60, 20);
		getContentPane().add(moveDistanceTxt);
		moveDistanceTxt.setColumns(10);
		
		JLabel centerXLbl = new JLabel("CenterX"); // display the current X coordinate of the center
		centerXLbl.setBounds(366, 158, 60, 14);
		this.getContentPane().add(centerXLbl);
		centerXLbl.setVisible(true);
		centerXTxt = new JTextField();
		centerXTxt.setBounds(366, 180, 67, 20);
		getContentPane().add(centerXTxt);
		centerXTxt.setColumns(10);
		centerXTxt.setVisible(true);
		
		JLabel centerYLbl = new JLabel("CenterY"); // display the current Y coordinate of the center
		centerYLbl.setBounds(453, 158, 60, 14);
		this.getContentPane().add(centerYLbl);
		centerYLbl.setVisible(true);
		centerYTxt = new JTextField();
		centerYTxt.setToolTipText("Y coordinate of the center");
		centerYTxt.setText("");
		centerYTxt.setBounds(453, 179, 60, 20);
		this.getContentPane().add(centerYTxt);
		centerYTxt.setVisible(true);
		
		JButton reCenterBtn = new JButton("Center at (X, Y)"); // button to re-center the terrain as the user type in the (Center, CenterY) textbox
		reCenterBtn.setToolTipText("Re-center the terrain by the coordinate typed in (Center X, CenterY) above");
		reCenterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eventHandler.reCenterTerrainEvent();
			}
		});
		reCenterBtn.setBounds(366, 214, 147, 23);
		this.getContentPane().add(reCenterBtn);
		reCenterBtn.setVisible(true);
		
		
		// --------------- zooming terrain ----------------
		JLabel zoomLbl = new JLabel("Zoom terrain");
		zoomLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		zoomLbl.setBounds(10, 259, 87, 14);
		this.getContentPane().add(zoomLbl);
		zoomLbl.setVisible(true);
		
		JButton zoomUpBtn = new JButton("Zoom up"); // zoom up button
		zoomUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eventHandler.zoomUpEvent();
			}
		});
		zoomUpBtn.setBounds(10, 278, 87, 23);
		this.getContentPane().add(zoomUpBtn);
		zoomUpBtn.setVisible(true);
		
		JButton zoomDownBtn = new JButton("Zoom down"); // zoom down button
		zoomDownBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eventHandler.zoomDownEvent();
			}
		});
		zoomDownBtn.setBounds(120, 278, 109, 23);
		this.getContentPane().add(zoomDownBtn);
		zoomDownBtn.setVisible(true);
		
		JButton zoomPercentBtn = new JButton("zoom by"); // restore 100% zoom button
		zoomPercentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventHandler.zoomByPercentEvent();
			}
		});
		zoomPercentBtn.setBounds(255, 278, 107, 23);
		this.getContentPane().add(zoomPercentBtn);
		zoomPercentBtn.setVisible(true);
		
		zoomByTxt = new JTextField();
		zoomByTxt.setText("100");
		zoomByTxt.setBounds(375, 279, 51, 20);
		this.getContentPane().add(zoomByTxt);
		zoomByTxt.setColumns(10);
		zoomByTxt.setVisible(true);
		
		JLabel zoomByLbl = new JLabel("%");
		zoomByLbl.setBounds(432, 282, 21, 14);
		this.getContentPane().add(zoomByLbl);
		zoomByLbl.setVisible(true);
		
		
		// ----- find interested node and center the terrain around the interested node ---------
		JLabel findNodeLbl = new JLabel("Find node");
		findNodeLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		findNodeLbl.setBounds(10, 347, 67, 14);
		this.getContentPane().add(findNodeLbl);
		findNodeLbl.setVisible(true);
		
		JLabel nodeNameLbl = new JLabel("Node name");
		nodeNameLbl.setBounds(32, 372, 77, 14);
		this.getContentPane().add(nodeNameLbl);
		nodeNameLbl.setVisible(true);
		
		nodeNameTxt = new JTextField();
		nodeNameTxt.setBounds(103, 369, 126, 20);
		getContentPane().add(nodeNameTxt);
		nodeNameTxt.setColumns(10);
		
		JButton findNodeBtn = new JButton("Find node");
		findNodeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eventHandler.findNodeEvent();
			}
		});
		findNodeBtn.setBounds(245, 368, 117, 23);
		this.getContentPane().add(findNodeBtn);
		findNodeBtn.setVisible(true);
		
		// --------- this part is for specifying peak threshold so that we can save genes in peak region later ------------
		peakThresLbl = new JLabel ("Peak threshold");
		peakThresLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		peakThresLbl.setBounds(410, 347, 90, 14);
		this.getContentPane().add(peakThresLbl);
		peakThresLbl.setVisible(true);
		
		peakThresTxt = new JTextField();
		peakThresTxt.setBounds(410, 369, 126, 20);
		getContentPane().add(peakThresTxt);
		peakThresTxt.setColumns(10);
		
		// ---------------- color mapping ----------------
		colorBarPanel = new JPanel();
		colorBarPanel.setBounds(442, 443, 21, 340);
		getContentPane().add(colorBarPanel);
		//colorBarPanel.initilizePixelColor();
		
		JLabel colorMapLbl = new JLabel("Color mapping");
		colorMapLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		colorMapLbl.setBounds(254, 421, 84, 14);
		this.getContentPane().add(colorMapLbl);
		colorMapLbl.setVisible(true);
		
		JLabel valleyHeightLbl = new JLabel("Valley height"); // height value - color mapping
		valleyHeightLbl.setBounds(489, 748, 87, 14);
		this.getContentPane().add(valleyHeightLbl);
		valleyHeightLbl.setVisible(true);
		
		JLabel upperHeightLbl = new JLabel("Upper height");
		upperHeightLbl.setBounds(489, 511, 86, 14);
		getContentPane().add(upperHeightLbl);
		
		JLabel flatHeightLbl = new JLabel("Flat height");
		flatHeightLbl.setBounds(489, 588, 87, 14);
		getContentPane().add(flatHeightLbl);
		
		JLabel lowerHeightLbl = new JLabel("Lower height");
		lowerHeightLbl.setBounds(488, 674, 88, 14);
		getContentPane().add(lowerHeightLbl);
		
		JLabel peakHeightLbl = new JLabel("Peak height"); // peak value - color mapping
		peakHeightLbl.setBounds(489, 421, 82, 14);
		this.getContentPane().add(peakHeightLbl);
		peakHeightLbl.setVisible(true);
		
		flatHeightTxt = new JTextField();
		flatHeightTxt.setBounds(489, 607, 67, 20);
		getContentPane().add(flatHeightTxt);
		flatHeightTxt.setColumns(10);
		
		upperHeighTxt = new JTextField();
		upperHeighTxt.setBounds(489, 529, 67, 20);
		getContentPane().add(upperHeighTxt);
		upperHeighTxt.setColumns(10);
		
		lowerHeightTxt = new JTextField();
		lowerHeightTxt.setBounds(489, 695, 67, 20);
		getContentPane().add(lowerHeightTxt);
		lowerHeightTxt.setColumns(10);
		
		peakHeightTxt = new JTextField();
		peakHeightTxt.setBounds(489, 447, 67, 20);
		getContentPane().add(peakHeightTxt);
		peakHeightTxt.setColumns(10);
		
		valleyHeightTxt = new JTextField();
		valleyHeightTxt.setBounds(490, 761, 66, 20);
		getContentPane().add(valleyHeightTxt);
		valleyHeightTxt.setColumns(10);
		
		JButton choosePeakColorBtn = new JButton("Choose peak color");
		choosePeakColorBtn.setBounds(256, 446, 160, 23);
		this.getContentPane().add(choosePeakColorBtn);
		choosePeakColorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventHandler.choosePeakColorEvent();
			}
		});
		choosePeakColorBtn.setVisible(true);
		
		JButton chooseValleyColorBtn = new JButton("Choose valley color");
		chooseValleyColorBtn.setBounds(256, 760, 160, 23);
		this.getContentPane().add(chooseValleyColorBtn);
		chooseValleyColorBtn.setVisible(true);
		chooseValleyColorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eventHandler.chooseValleyColorEvent();
			}
		});
		
		JButton chooseFlatColorBtn = new JButton("Choose flat color");
		chooseFlatColorBtn.setBounds(256, 606, 160, 23);
		getContentPane().add(chooseFlatColorBtn);
		chooseFlatColorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eventHandler.chooseFlatColorEvent();
			}
		});
		
		JButton chooseUpperColorBtn = new JButton("Choose upper color");
		chooseUpperColorBtn.setBounds(256, 528, 160, 23);
		chooseUpperColorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eventHandler.chooseUpperColorEvent();
			}
		});
		getContentPane().add(chooseUpperColorBtn);
		
		JButton chooseLowerColorBtn = new JButton("Choose lower color");
		chooseLowerColorBtn.setBounds(256, 694, 160, 23);
		getContentPane().add(chooseLowerColorBtn);
		chooseLowerColorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eventHandler.chooseLowerColorEvent();
			}
		});
		
		JButton updateColorBtn = new JButton("Update color");
		updateColorBtn.setBounds(453, 810, 126, 23);
		getContentPane().add(updateColorBtn);
		updateColorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//eventHandler.updateColorBtnEvent();
			}
		});
		
		// -------------- toggle node and edge -------------------
		viewNodeCBx = new JCheckBox("View node");
		viewNodeCBx.setBounds(10, 426, 113, 23);
		getContentPane().add(viewNodeCBx);
		viewNodeCBx.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				//eventHandler.toggleNodeView(e);			
			}
		});
		
		viewEdgeCbx = new JCheckBox("View edge");
		viewEdgeCbx.setBounds(12, 446, 111, 23);
		getContentPane().add(viewEdgeCbx);
		viewEdgeCbx.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				//eventHandler.toggleEdgeView(e);			
			}
		});
		
		viewSizeComparison = new JCheckBox("View node size comparison");
		viewSizeComparison.setBounds(12, 466, 300, 23);
		getContentPane().add(viewSizeComparison);
		viewSizeComparison.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				//eventHandler.toggleNodeSizeComparisonView(e);			
			}
		});
		
		
		/// ------------ save terrain -------------------
		JButton saveTerrainBtn = new JButton("Save terrain");
		saveTerrainBtn.setBounds(10, 507, 126, 23);
		getContentPane().add(saveTerrainBtn);
		saveTerrainBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
			//	eventHandler.saveTerrainEvent();
			}
		});
		
		// --------- show node information ----------------
		JLabel nodeInforLbl = new JLabel("Node information");
		nodeInforLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		nodeInforLbl.setBounds(10, 769, 126, 14);
		getContentPane().add(nodeInforLbl);
		
		nodeInfoTxt = new JTextPane();
		nodeInfoTxt.setEditable(false);
		nodeInfoTxt.setBounds(10, 794, 189, 101);
		getContentPane().add(nodeInfoTxt);
		
		// ---------- adjust the width factor -------------------------------
		JLabel withFactorLbl = new JLabel("Width factors");
		withFactorLbl.setToolTipText("This option allows changing the width factor of the node such that the node width of influence is linearly proportional to the node size");
		withFactorLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		withFactorLbl.setBounds(10, 576, 113, 17);
		getContentPane().add(withFactorLbl);
		
		JLabel smallInfLbl = new JLabel("% influence, smallest");
		smallInfLbl.setBounds(20, 600, 126, 14);
		getContentPane().add(smallInfLbl);
		
		JLabel largeInfLbl = new JLabel("% influence, largest");
		largeInfLbl.setBounds(20, 650, 126, 14);
		getContentPane().add(largeInfLbl);
		
		smallInfTxt = new JTextField();
		smallInfTxt.setToolTipText("The influence percentage of the smallest node on the terrain.");
		smallInfTxt.setText("5");
		smallInfTxt.setColumns(10);
		smallInfTxt.setBounds(20, 620, 67, 20);
		getContentPane().add(smallInfTxt);
		
		largeInfTxt = new JTextField();
		largeInfTxt.setToolTipText("The influence percentage of the largest node on the terrain.");
		largeInfTxt.setText("20");
		largeInfTxt.setColumns(10);
		largeInfTxt.setBounds(20, 670, 67, 20);
		getContentPane().add(largeInfTxt);
		
		JButton widthSliderBtn = new JButton("Adjust width factor");
		widthSliderBtn.setBounds(20, 700, 150, 23);
		getContentPane().add(widthSliderBtn);
		
		JButton chooseMaskFileBtn = new JButton("Choose mask file");
		chooseMaskFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		chooseMaskFileBtn.setBounds(191, 87, 147, 25);
		getContentPane().add(chooseMaskFileBtn);
		
		JLabel maskFileLbl = new JLabel("(not uploaded)");
		maskFileLbl.setBounds(191, 117, 147, 19);
		getContentPane().add(maskFileLbl);
		widthSliderBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
			//	eventHandler.adjustWidthEvent();
			}
		});
		
		// add the mouse listener handler for the terrain panel, which allows showing gene details
		
		
		this.setVisible(true);
		this.setResizable(false);
		
	}
}
