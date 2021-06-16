import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

// button event handler class for the main UI

public class ButtonEventHandler 
{
	GeneTerrainMainUI mainUI;

	public ButtonEventHandler (GeneTerrainMainUI argUI)
	{
		mainUI = argUI;
	}


	// ------------------ handling event for choose node file button ----------------------
	public void choseNodeFileEvent ()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fileChooser.showOpenDialog(mainUI);
		if(returnVal == JFileChooser.APPROVE_OPTION) 
		{
			mainUI.geneListFile = fileChooser.getSelectedFile(); // get the gene list file for the main UI
			String fileName = mainUI.geneListFile.getName(); // display the gene list file name in the main UI
			if (fileName.length() < 20)
			{
				mainUI.nodeFileLbl.setText(fileName);
			}
			else
			{
				mainUI.nodeFileLbl.setText( fileName.substring(0, 8) + "..." +  fileName.substring(fileName.length()-8, fileName.length()) ) ;
			}
			mainUI.nodeFileLbl.setToolTipText(mainUI.geneListFile.getAbsolutePath());
		}
	}


	// ------------------ handling event for choose edge file button ----------------------
	public void choseEdgeFileEvent ()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fileChooser.showOpenDialog(mainUI);
		if(returnVal == JFileChooser.APPROVE_OPTION) 
		{
			mainUI.connectionFile = fileChooser.getSelectedFile(); // get the gene list file for the main UI
			String fileName = mainUI.connectionFile.getName(); // display the gene list file name in the main UI
			if (fileName.length() < 20)
			{
				mainUI.edgeFileLbl.setText(fileName);
			}
			else
			{
				mainUI.edgeFileLbl.setText( fileName.substring(0, 8) + "..." +  fileName.substring(fileName.length()-8, fileName.length()) ) ;
			}
			mainUI.edgeFileLbl.setToolTipText(mainUI.connectionFile.getAbsolutePath());
		}
	}


	// ------------------ handling event for choose layout file button ----------------------
	public void choseLayoutFileEvent ()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fileChooser.showOpenDialog(mainUI);
		if(returnVal == JFileChooser.APPROVE_OPTION) 
		{
			mainUI.layoutFile = fileChooser.getSelectedFile(); // get the gene list file for the main UI
			String fileName = mainUI.layoutFile.getName(); // display the gene list file name in the main UI
			if (fileName.length() < 20)
			{
				mainUI.layoutFileLbl.setText(fileName);
			}
			else
			{
				mainUI.layoutFileLbl.setText( fileName.substring(0, 8) + "..." +  fileName.substring(fileName.length()-8, fileName.length()) ) ;
			}
			mainUI.layoutFileLbl.setToolTipText(mainUI.layoutFile.getAbsolutePath());
		}
	}


	// -----------------------handling choose mask file button ----------------------
	public void choseMaskFileEvent ()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fileChooser.showOpenDialog(mainUI);
		if(returnVal == JFileChooser.APPROVE_OPTION) 
		{
			mainUI.maskFile = fileChooser.getSelectedFile(); // get the gene list file for the main UI
			String fileName = mainUI.layoutFile.getName(); // display the gene list file name in the main UI
			if (fileName.length() < 20)
			{
				mainUI.maskFileLbl.setText(fileName);
			}
			else
			{
				mainUI.maskFileLbl.setText( fileName.substring(0, 8) + "..." +  fileName.substring(fileName.length()-8, fileName.length()) ) ;
			}
			mainUI.maskFileLbl.setToolTipText(fileName);
		}
	}


	// --------------- handling event for create terrain button -----------------------
	public void createTerrainEvent ()
	{
		try
		{
			TerrainDataImporter importer = new TerrainDataImporter (mainUI.terrainData);
			mainUI.terrainData.importer = importer;
			mainUI.terrainData.setupBackEndData(mainUI.geneListFile, mainUI.connectionFile, mainUI.layoutFile, mainUI.maskFile);
			//System.out.println("Data import OK");
		}

		catch (Exception e)
		{
			JOptionPane.showMessageDialog(mainUI, "Fail to import data file", "Data error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		mainUI.terrainData.upLeftY = mainUI.terrainData.drawHeight;
		mainUI.terrainData.upLeftX = mainUI.terrainData.drawWidth;

		mainUI.terrainData.updateBackendWeight();
		mainUI.terrainData.setNodePixelCoordinate();
		mainUI.terrainData.setupAllPixelVal();
		mainUI.terrainData.setAutomaticColorThreshold();
		mainUI.terrainData.setupDrawingPixelColor();
		mainUI.terrainData.setColorBar();
		//System.out.println("Terrain Pixel Ready");

		// show the center X and center Y in the terrain
		mainUI.centerXTxt.setText( String.format("%.02f", (mainUI.terrainData.leftView + mainUI.terrainData.rightView) / 2f) );
		mainUI.centerYTxt.setText( String.format("%.02f", (mainUI.terrainData.topView + mainUI.terrainData.bottomView) / 2f) );
		mainUI.zoomByTxt.setText("100");
		mainUI.nodeNameTxt.setText("");
		mainUI.nodeInfoTxt.setText("");

		mainUI.repaint();
	}


	// --------------- handling event for create terrain button with specific width scale -----------------------
	public void createTerrainEvent (float argWidthScale)
	{
		try
		{
			TerrainDataImporter importer = new TerrainDataImporter (mainUI.terrainData);
			mainUI.terrainData.importer = importer;
			
			
			mainUI.terrainData.setupBackEndData(mainUI.geneListFile, mainUI.connectionFile, mainUI.layoutFile, mainUI.maskFile);
			//System.out.println("Data import OK");
			mainUI.terrainData.scaleNodeWidth(argWidthScale);
		}

		catch (Exception e)
		{
			JOptionPane.showMessageDialog(mainUI, "Fail to import data file", "Data error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		mainUI.terrainData.upLeftY = mainUI.terrainData.drawHeight;
		mainUI.terrainData.upLeftX = mainUI.terrainData.drawWidth;

		mainUI.terrainData.updateBackendWeight();
		mainUI.terrainData.setNodePixelCoordinate();
		mainUI.terrainData.setupAllPixelVal();
		mainUI.terrainData.setAutomaticColorThreshold();
		mainUI.terrainData.setupDrawingPixelColor();
		mainUI.terrainData.setColorBar();
		//System.out.println("Terrain Pixel Ready");

		// show the center X and center Y in the terrain
		mainUI.centerXTxt.setText( String.format("%.02f", (mainUI.terrainData.leftView + mainUI.terrainData.rightView) / 2f) );
		mainUI.centerYTxt.setText( String.format("%.02f", (mainUI.terrainData.topView + mainUI.terrainData.bottomView) / 2f) );
		mainUI.zoomByTxt.setText("100");
		mainUI.nodeNameTxt.setText("");
		mainUI.nodeInfoTxt.setText("");

		mainUI.repaint();

		mainUI.currentTerrainName = mainUI.geneListFile.getName();
	}


	// ---------- handling move up button terrain -------------------------------
	public void moveUpTerrainEvent()
	{
		// check if there has been any exiting terrain
		if (mainUI.terrainData.nodeID == null)
		{
			JOptionPane.showMessageDialog(mainUI, "There has been no terrain", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String moveTextDistance = mainUI.moveDistanceTxt.getText();
		if (!isNumber(moveTextDistance))
		{
			JOptionPane.showMessageDialog(mainUI, "Please make sure that the moving distance is a number", "Numeric format error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (Float.parseFloat(moveTextDistance) < 0)
		{
			JOptionPane.showMessageDialog(mainUI, "Please make sure that the moving distance is non-negative", "Numeric format error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		else
		{
			float moveDis = Float.parseFloat(moveTextDistance);
			reCenterTerrain ( (mainUI.terrainData.leftView + mainUI.terrainData.rightView) / 2, 
					(mainUI.terrainData.topView + mainUI.terrainData.bottomView) / 2 + moveDis );

			//System.out.println("Move up success");
			mainUI.repaint();
		}
	}


	// ---------- handling move down button terrain -------------------------------
	public void moveDownTerrainEvent()
	{
		// check if there has been any exiting terrain
		if (mainUI.terrainData.nodeID == null)
		{
			JOptionPane.showMessageDialog(mainUI, "There has been no terrain", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String moveTextDistance = mainUI.moveDistanceTxt.getText();
		if (!isNumber(moveTextDistance))
		{
			JOptionPane.showMessageDialog(mainUI, "Please make sure that the moving distance is a number", "Numeric format error", JOptionPane.ERROR_MESSAGE);
			return;
		}


		if (Float.parseFloat(moveTextDistance) < 0)
		{
			JOptionPane.showMessageDialog(mainUI, "Please make sure that the moving distance is non-negative", "Numeric format error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		else
		{
			float moveDis = Float.parseFloat(moveTextDistance);
			reCenterTerrain (  (mainUI.terrainData.leftView + mainUI.terrainData.rightView) / 2, 
					(mainUI.terrainData.topView + mainUI.terrainData.bottomView) / 2 - moveDis);

			//System.out.println("Move down success");
			mainUI.repaint();
		}
	}


	// -------------------- handling move left button -----------------------------
	public void moveLeftTerrainEvent()
	{
		// check if there has been any exiting terrain
		if (mainUI.terrainData.nodeID == null)
		{
			JOptionPane.showMessageDialog(mainUI, "There has been no terrain", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String moveTextDistance = mainUI.moveDistanceTxt.getText();
		if (!isNumber(moveTextDistance))
		{
			JOptionPane.showMessageDialog(mainUI, "Please make sure that the moving distance is a number", "Numeric format error", JOptionPane.ERROR_MESSAGE);
			return;
		}


		if (Float.parseFloat(moveTextDistance) < 0)
		{
			JOptionPane.showMessageDialog(mainUI, "Please make sure that the moving distance is non-negative", "Numeric format error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		else
		{
			float moveDis = Float.parseFloat(moveTextDistance);

			reCenterTerrain ( (mainUI.terrainData.leftView + mainUI.terrainData.rightView) / 2 - moveDis, 
					(mainUI.terrainData.topView + mainUI.terrainData.bottomView) / 2);

			//System.out.println("Move left success");
			mainUI.repaint();
		}
	}


	// -------------------- handling move right button -----------------------------
	public void moveRightTerrainEvent()
	{
		// check if there has been any exiting terrain
		if (mainUI.terrainData.nodeID == null)
		{
			JOptionPane.showMessageDialog(mainUI, "There has been no terrain", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String moveTextDistance = mainUI.moveDistanceTxt.getText();
		if (!isNumber(moveTextDistance))
		{
			JOptionPane.showMessageDialog(mainUI, "Please make sure that the moving distance is a number", "Numeric format error", JOptionPane.ERROR_MESSAGE);
			return;
		}


		if (Float.parseFloat(moveTextDistance) < 0)
		{
			JOptionPane.showMessageDialog(mainUI, "Please make sure that the moving distance is non-negative", "Numeric format error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		else
		{
			float moveDis = Float.parseFloat(moveTextDistance);

			reCenterTerrain ( (mainUI.terrainData.leftView + mainUI.terrainData.rightView) / 2 + moveDis, 
					(mainUI.terrainData.topView + mainUI.terrainData.bottomView) / 2);
			//System.out.println("Move right success");
			mainUI.repaint();
		}
	}


	// ---------------------- handling re center the terrain event ------------------------
	public void reCenterTerrainEvent()
	{
		if (mainUI.terrainData.nodeID == null)
		{
			JOptionPane.showMessageDialog(mainUI, "There has been no terrain", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String newCenterXStr = mainUI.centerXTxt.getText();
		String newCenterYStr = mainUI.centerYTxt.getText();
		if (!isNumber(newCenterXStr) || !isNumber(newCenterYStr))
		{
			JOptionPane.showMessageDialog(mainUI, "Please make sure that the new (CenterX, CenterY) are numbers", "Numeric format error", JOptionPane.ERROR_MESSAGE);
			return;
		} 

		float newXCenter = Float.parseFloat(newCenterXStr);
		float newYCenter = Float.parseFloat(newCenterYStr);

		reCenterTerrain(newXCenter, newYCenter);

		//System.out.println("Recenter success");
		mainUI.repaint();
	}


	// ---------------- general function to re-center the terrain -------------------
	private void reCenterTerrain (float newXCenter, float newYCenter)
	{
		float oldXCenter = (mainUI.terrainData.leftView + mainUI.terrainData.rightView) / 2;
		float oldYCenter = (mainUI.terrainData.topView + mainUI.terrainData.bottomView) / 2;

		float moveXDis = newXCenter - oldXCenter;
		mainUI.terrainData.leftView += moveXDis;
		mainUI.terrainData.rightView += moveXDis;

		float moveYDis = newYCenter - oldYCenter;
		mainUI.terrainData.topView += moveYDis;
		mainUI.terrainData.bottomView += moveYDis;

		// calculate if we can just shift the drawing window within the pixelValue table
		int expectNewUpLeftY = Math.round(1f*mainUI.terrainData.upLeftY - 
				moveYDis / (mainUI.terrainData.topView-mainUI.terrainData.bottomView) * mainUI.terrainPanel.getHeight());

		int expectNewUpLeftX =  Math.round(1f*mainUI.terrainData.upLeftX + 
				moveXDis / (mainUI.terrainData.rightView-mainUI.terrainData.leftView) * mainUI.terrainPanel.getWidth());

		if (expectNewUpLeftY < 0 || expectNewUpLeftY > 2*mainUI.terrainData.drawHeight || 
				expectNewUpLeftX < 0 || expectNewUpLeftX > 2*mainUI.terrainData.drawWidth) // outside the drawing buffer. Need to recompute the terrain pixel
		{
			mainUI.terrainData.upLeftY = mainUI.terrainData.drawHeight;
			mainUI.terrainData.upLeftX = mainUI.terrainData.drawWidth;
			mainUI.terrainData.setupAllPixelVal();
		}
		else
		{
			mainUI.terrainData.upLeftY = expectNewUpLeftY;
			mainUI.terrainData.upLeftX = expectNewUpLeftX;
		}

		mainUI.terrainData.setAutomaticColorThreshold();
		mainUI.terrainData.setColorBar();
		mainUI.terrainData.setNodePixelCoordinate();
		mainUI.terrainData.setupDrawingPixelColor();

		mainUI.centerXTxt.setText( String.format("%.02f", (mainUI.terrainData.leftView + mainUI.terrainData.rightView) / 2f) );
		mainUI.centerYTxt.setText( String.format("%.02f", (mainUI.terrainData.topView + mainUI.terrainData.bottomView) / 2f) );
	}


	//-----------------handle zoom up button event ----------------------------
	// by default, each 'zoom up' increase the zoom percentage to the next 10, 20, 30, ... percentage
	public void zoomUpEvent()
	{
		if (mainUI.terrainData.nodeID == null)
		{
			JOptionPane.showMessageDialog(mainUI, "There has been no terrain", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		float drawingWindowWidth = mainUI.terrainData.rightView - mainUI.terrainData.leftView;
		float drawingWindowHeight = mainUI.terrainData.topView - mainUI.terrainData.bottomView;
		float drawingCenterX = (mainUI.terrainData.rightView + mainUI.terrainData.leftView) / 2;
		float drawingCenterY = (mainUI.terrainData.topView + mainUI.terrainData.bottomView) / 2;
		float zoomRatio =  20f / drawingWindowWidth;
		float nextZoomRatio = (float) Math.ceil(200f / drawingWindowWidth) / 10;

		//System.out.println("previous zoom ratio: " + zoomRatio + ", nextZoomRatio: " + nextZoomRatio);

		if (Math.abs(nextZoomRatio-zoomRatio) < 0.001)
		{
			nextZoomRatio += 0.1;
		}

		//System.out.println("later: previous zoom ratio: " + zoomRatio + ", nextZoomRatio: " + nextZoomRatio);

		// increase zoom means decrease the drawing window size
		mainUI.terrainData.topView = drawingCenterY + 10f/nextZoomRatio;
		mainUI.terrainData.bottomView = drawingCenterY - 10f/nextZoomRatio;
		mainUI.terrainData.leftView = drawingCenterX - 10f/nextZoomRatio;
		mainUI.terrainData.rightView = drawingCenterX + 10f/nextZoomRatio;

		// reset all pixel color
		mainUI.terrainData.setNodePixelCoordinate();

		mainUI.terrainData.upLeftY = mainUI.terrainData.drawHeight;
		mainUI.terrainData.upLeftX = mainUI.terrainData.drawWidth;

		mainUI.terrainData.setupAllPixelVal();
		mainUI.terrainData.setAutomaticColorThreshold();
		mainUI.terrainData.setColorBar();
		mainUI.terrainData.setupDrawingPixelColor();
		//System.out.println("Zoom up success");

		mainUI.zoomByTxt.setText(String.format("%.02f", nextZoomRatio*100));
		mainUI.repaint();
	}


	//-----------------handle zoom up button event ----------------------------
	// by default, each 'zoom up' increase the zoom percentage to the next 10, 20, 30, ... percentage
	public void zoomDownEvent()
	{
		if (mainUI.terrainData.nodeID == null)
		{
			JOptionPane.showMessageDialog(mainUI, "There has been no terrain", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		float drawingWindowWidth = mainUI.terrainData.rightView - mainUI.terrainData.leftView;
		float drawingWindowHeight = mainUI.terrainData.topView - mainUI.terrainData.bottomView;
		float drawingCenterX = (mainUI.terrainData.rightView + mainUI.terrainData.leftView) / 2;
		float drawingCenterY = (mainUI.terrainData.topView + mainUI.terrainData.bottomView) / 2;
		float zoomRatio =  20f / drawingWindowWidth;
		float nextZoomRatio = (float) Math.floor(200f / drawingWindowWidth) / 10;

		if (Math.abs(nextZoomRatio-zoomRatio) < 0.001)
		{
			nextZoomRatio -= 0.1;
		}

		if (Math.abs(nextZoomRatio - 0.1) < 0.0001) // cannot zoom down at 10% level
		{
			JOptionPane.showMessageDialog(mainUI, "Cannot zoom down anymore", "Too low resolution", JOptionPane.WARNING_MESSAGE);
			return;
		}

		else
		{
			// decreasing zoom means increase the drawing window size
			mainUI.terrainData.topView = drawingCenterY + 10f/nextZoomRatio;
			mainUI.terrainData.bottomView = drawingCenterY - 10f/nextZoomRatio;
			mainUI.terrainData.leftView = drawingCenterX - 10f/nextZoomRatio;
			mainUI.terrainData.rightView = drawingCenterX + 10f/nextZoomRatio;

			// reset all pixel color
			mainUI.terrainData.setNodePixelCoordinate();

			mainUI.terrainData.upLeftY = mainUI.terrainData.drawHeight;
			mainUI.terrainData.upLeftX = mainUI.terrainData.drawWidth;

			mainUI.terrainData.setupAllPixelVal();
			mainUI.terrainData.setAutomaticColorThreshold();
			mainUI.terrainData.setColorBar();
			mainUI.terrainData.setupDrawingPixelColor();
			//System.out.println("Zoom down success");

			mainUI.zoomByTxt.setText(String.format("%.02f", nextZoomRatio*100));
			mainUI.repaint();
		}

	}


	// ---------------------- zoom by percentage event --------------------------------
	public void zoomByPercentEvent()
	{
		if (mainUI.terrainData.nodeID == null)
		{
			JOptionPane.showMessageDialog(mainUI, "There has been no terrain", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String zoomRatioTxt = mainUI.zoomByTxt.getText();
		if (!isNumber(zoomRatioTxt) )
		{
			JOptionPane.showMessageDialog(mainUI, "Please make sure that the zoom percentage are numbers", "Numeric format error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		float zoomRatio = Float.parseFloat(zoomRatioTxt);
		if (zoomRatio < 10)
		{
			JOptionPane.showMessageDialog(mainUI, "Cannot zoom less than 10%", "Too low resolution", JOptionPane.WARNING_MESSAGE);
			return;
		}

		else
		{
			float drawingCenterX = (mainUI.terrainData.rightView + mainUI.terrainData.leftView) / 2;
			float drawingCenterY = (mainUI.terrainData.topView + mainUI.terrainData.bottomView) / 2;

			float nextZoomRatio = zoomRatio / 100f;
			// decreasing zoom means increase the drawing window size
			mainUI.terrainData.topView = drawingCenterY + 10f/nextZoomRatio;
			mainUI.terrainData.bottomView = drawingCenterY - 10f/nextZoomRatio;
			mainUI.terrainData.leftView = drawingCenterX - 10f/nextZoomRatio;
			mainUI.terrainData.rightView = drawingCenterX + 10f/nextZoomRatio;

			// reset all pixel color
			mainUI.terrainData.setNodePixelCoordinate();

			mainUI.terrainData.upLeftY = mainUI.terrainData.drawHeight;
			mainUI.terrainData.upLeftX = mainUI.terrainData.drawWidth;

			mainUI.terrainData.setupAllPixelVal();
			mainUI.terrainData.setAutomaticColorThreshold();
			mainUI.terrainData.setColorBar();

			mainUI.terrainData.setupDrawingPixelColor();
			//System.out.println("Zoom down success");

			mainUI.zoomByTxt.setText(String.format("%.02f", nextZoomRatio*100));
			mainUI.repaint();
		}
	}


	// --------------- toggle node event ------------------------------------
	public void toggleNodeView (ItemEvent e)
	{
		if (mainUI.terrainData.nodeID == null)
		{
			JOptionPane.showMessageDialog(mainUI, "There has been no terrain", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if ( e.getStateChange() == ItemEvent.SELECTED) 
		{
			mainUI.terrainPanel.drawNode = true;
		}
		else
		{
			mainUI.terrainPanel.drawNode = false;
		}

		mainUI.repaint();
	}


	// ------------- toggle viewing node size comparison event ------------------
	public void toggleNodeSizeComparisonView(ItemEvent e)
	{
		if (mainUI.terrainData.nodeID == null)
		{
			JOptionPane.showMessageDialog(mainUI, "There has been no terrain", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if ( e.getStateChange() == ItemEvent.SELECTED) 
		{
			mainUI.terrainPanel.viewRadius = true;
		}
		else
		{
			mainUI.terrainPanel.viewRadius = false;
		}

		mainUI.repaint();
	}


	// --------------- toggle edge event ------------------------------------
	public void toggleEdgeView (ItemEvent e)
	{
		if (mainUI.terrainData.nodeID == null)
		{
			JOptionPane.showMessageDialog(mainUI, "There has been no terrain", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if ( e.getStateChange() == ItemEvent.SELECTED) 
		{
			mainUI.terrainPanel.drawEdge = true;
		}
		else
		{
			mainUI.terrainPanel.drawEdge = false;
		}

		mainUI.repaint();
	}


	// ------------------------- choose peak color event ---------------------------
	public void choosePeakColorEvent ()
	{
		try
		{
			mainUI.terrainData.peakColor = JColorChooser.showDialog(mainUI, "Select a color",mainUI.terrainData.peakColor);
			mainUI.terrainData.setColorBar();
			mainUI.terrainData.setupDrawingPixelColor();
			mainUI.repaint();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(mainUI, "no color selected");
		}
	}


	// ------------------------- choose valley color event ---------------------------
	public void chooseValleyColorEvent ()
	{
		try
		{
			mainUI.terrainData.valleyColor = JColorChooser.showDialog(mainUI, "Select a color",mainUI.terrainData.peakColor);
			mainUI.terrainData.setColorBar();
			mainUI.terrainData.setupDrawingPixelColor();
			mainUI.repaint();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(mainUI, "no color selected");
		}
	}


	// ------------------------- choose peak color event ---------------------------
	public void chooseFlatColorEvent ()
	{
		try
		{
			mainUI.terrainData.flatColor = JColorChooser.showDialog(mainUI, "Select a color",mainUI.terrainData.peakColor);
			mainUI.terrainData.setColorBar();
			mainUI.terrainData.setupDrawingPixelColor();
			mainUI.repaint();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(mainUI, "no color selected");
		}
	}


	// ------------------------- choose peak color event ---------------------------
	public void chooseUpperColorEvent ()
	{
		try
		{
			mainUI.terrainData.upperColor = JColorChooser.showDialog(mainUI, "Select a color",mainUI.terrainData.peakColor);
			mainUI.terrainData.setColorBar();
			mainUI.terrainData.setupDrawingPixelColor();
			mainUI.repaint();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(mainUI, "no color selected");
		}
	}


	// ------------------------- choose peak color event ---------------------------
	public void chooseLowerColorEvent ()
	{
		try
		{
			mainUI.terrainData.lowerColor = JColorChooser.showDialog(mainUI, "Select a color",mainUI.terrainData.peakColor);
			mainUI.terrainData.setColorBar();
			mainUI.terrainData.setupDrawingPixelColor();
			mainUI.repaint();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(mainUI, "no color selected");
		}
	}


	// ----------------------- update color button event --------------------------
	public void updateColorBtnEvent ()
	{
		String upColorStr = mainUI.upperHeighTxt.getText();
		String peakColorStr = mainUI.peakHeightTxt.getText();
		String flatColorStr = mainUI.flatHeightTxt.getText();
		String lowColorStr = mainUI.lowerHeightTxt.getText();
		String valleyColorStr = mainUI.valleyHeightTxt.getText();

		if ( !isNumber(upColorStr) || !isNumber(peakColorStr) || !isNumber(flatColorStr) || !isNumber(lowColorStr) || !isNumber(valleyColorStr) )
		{
			JOptionPane.showMessageDialog(mainUI, "Please make sure that the zoom percentage are numbers", "Numeric format error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		float peakNum = Float.parseFloat(peakColorStr);
		float upNum = Float.parseFloat(upColorStr);
		float flatNum = Float.parseFloat(flatColorStr);
		float lowNum = Float.parseFloat(lowColorStr);
		float valleyNum = Float.parseFloat(valleyColorStr);

		if (! (peakNum >= upNum && upNum >= flatNum && flatNum >= lowNum && lowNum >= valleyNum) )
		{
			JOptionPane.showMessageDialog(mainUI, "Please make sure that the order for color thresholds is appropriate",
					"Inappropriate color order", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// update the new color thresholds
		mainUI.terrainData.peakValue = peakNum;
		mainUI.terrainData.upperValue = upNum;
		mainUI.terrainData.flatValue = flatNum;
		mainUI.terrainData.lowerValue = lowNum;
		mainUI.terrainData.valleyValue = valleyNum;

		mainUI.terrainData.setColorBar();
		mainUI.terrainData.setupDrawingPixelColor();
		mainUI.repaint();
	}


	// -------------- save terrain event -------------------------------
	public void saveTerrainEvent()
	{
		try
		{	
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnVal = fileChooser.showSaveDialog(mainUI);
			if(returnVal == JFileChooser.APPROVE_OPTION) 
			{
				mainUI.terrainPanel.saveImage(fileChooser.getSelectedFile()+".png");

				float peakNodeThres = mainUI.terrainData.valleyValue;
				try
				{
					peakNodeThres = Float.parseFloat( mainUI.peakThresTxt.getText() );
				}
				catch (Exception ex)
				{

				}
				mainUI.terrainData.saveGeneInPeak(peakNodeThres, fileChooser.getSelectedFile()+ "_" + peakNodeThres + ".txt");
				mainUI.xmlWriter.write2XML(fileChooser.getSelectedFile()+ ".xml");
				//mainUI.terrainData.savePixelValue(fileChooser.getSelectedFile()+".txt");
				//System.out.println("Save terrain success");

				TerrainSerializableObject serializableObject = new TerrainSerializableObject( mainUI.terrainData, 
						mainUI.geneListFile, mainUI.connectionFile, mainUI.layoutFile, mainUI.maskFile, mainUI.currentTerrainName, 
						Float.parseFloat(mainUI.smallInfTxt.getText()), Float.parseFloat(mainUI.largeInfTxt.getText()) );
				serializableObject.saveToSerializableFile(fileChooser.getSelectedFile()+ ".ser");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(mainUI, "Fail to save terrain image", "file error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}


	// -------------------- load terrain button event ---------------------
	public void loadTerrainEvent() 
	{
		TerrainSerializableObject terrainObject = null;

		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnVal = fileChooser.showOpenDialog(mainUI);
			if(returnVal == JFileChooser.APPROVE_OPTION) 
			{
				fin = new FileInputStream(fileChooser.getSelectedFile());
				ois = new ObjectInputStream(fin);
				terrainObject = (TerrainSerializableObject) ois.readObject();

				mainUI.terrainData = terrainObject.terrainData;
				(mainUI.terrainData).mainUI = mainUI;
				mainUI.mouseEventHandler.terrainData = mainUI.terrainData;
				mainUI.terrainData.importer = new TerrainDataImporter(mainUI.terrainData);
				
				// recompute and show the terrain parameter
				float oldXCenter = (mainUI.terrainData.leftView + mainUI.terrainData.rightView) / 2;
				float oldYCenter = (mainUI.terrainData.topView + mainUI.terrainData.bottomView) / 2;
				mainUI.centerXTxt.setText(""+oldXCenter);
				mainUI.centerYTxt.setText(""+oldYCenter);
				
				mainUI.smallInfTxt.setText(terrainObject.minBackendWidth + "");
				mainUI.largeInfTxt.setText(terrainObject.maxBackendWidth + "");
				
				float zoomRatio = 20f / ( mainUI.terrainData.rightView - mainUI.terrainData.leftView )* 100;
				mainUI.zoomByTxt.setText(zoomRatio+"");

				mainUI.terrainData.setColorBar();
				mainUI.terrainData.setupDrawingPixelColor();
				
				JTextField flatHeightTxt;
				JTextField upperHeighTxt;
				JTextField lowerHeightTxt;
				JTextField peakHeightTxt;
				JTextField valleyHeightTxt;
				
				mainUI.peakHeightTxt.setText(mainUI.terrainData.peakValue + "");
				mainUI.upperHeighTxt.setText(mainUI.terrainData.upperValue + "");
				mainUI.flatHeightTxt.setText(mainUI.terrainData.flatValue + "");
				mainUI.lowerHeightTxt.setText(mainUI.terrainData.lowerValue + "");
				mainUI.valleyHeightTxt.setText(mainUI.terrainData.valleyValue + "");
				
				if (terrainObject.geneListFile != null)
				{
					mainUI.geneListFile = terrainObject.geneListFile;
					mainUI.nodeFileLbl.setText(terrainObject.geneListFile.getName());
				}
				
				if (terrainObject.layoutFile != null)
				{
					mainUI.layoutFile = terrainObject.layoutFile;
					mainUI.layoutFileLbl.setText(terrainObject.layoutFile.getName());
				}
				
				if (terrainObject.connectionFile != null)
				{
					mainUI.connectionFile = terrainObject.connectionFile;
					mainUI.edgeFileLbl.setText(terrainObject.connectionFile.getName());
				}
				
				if (terrainObject.maskFile != null)
				{
					mainUI.maskFileLbl.setText(terrainObject.maskFile.getName());
					mainUI.maskFile = terrainObject.maskFile;
				}				
				
				mainUI.repaint();
			} 
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}


	// ----------------- find gene event -----------------------
	public void findNodeEvent ()
	{
		String targetNode = mainUI.nodeNameTxt.getText();
		String [] allHighlightNode = targetNode.split(",");

		boolean nodeInTerrain = true;
		if (allHighlightNode.length == 0)
		{
			nodeInTerrain = false;
		}
		else
		{
			for (int i = 0; i < allHighlightNode.length; i++)
			{
				if (mainUI.terrainData.nodeID2Index.get(allHighlightNode[i]) == null)
				{
					nodeInTerrain = false;
					break;
				}
			}
		}


		if (!nodeInTerrain)
		{
			JOptionPane.showMessageDialog(mainUI, "Gene " + targetNode + " does not appear in the terrain");
		}

		else
		{
			//mainUI.terrainPanel.highlightNode = targetNode;

			mainUI.terrainPanel.highlightNode = allHighlightNode;

			//reCenterTerrain( mainUI.terrainData.nodeX[mainUI.terrainData.nodeID2Index.get(targetNode)] , 
			//		mainUI.terrainData.nodeY[mainUI.terrainData.nodeID2Index.get(targetNode)]);

			int newCenterX = 0;
			int newCenterY = 0;
			for (int i = 0; i < allHighlightNode.length; i++)
			{
				newCenterX += mainUI.terrainData.nodeX[mainUI.terrainData.nodeID2Index.get(allHighlightNode[i])];
				newCenterY += mainUI.terrainData.nodeY[mainUI.terrainData.nodeID2Index.get(allHighlightNode[i])];
			}
			newCenterX = newCenterX / allHighlightNode.length;
			newCenterY = newCenterY / allHighlightNode.length;
			reCenterTerrain(newCenterX, newCenterY);


			if (mainUI.viewNodeCBx.isSelected() == false)
			{
				mainUI.viewNodeCBx.setSelected(true);
				mainUI.terrainPanel.drawNode = true;
			}
			mainUI.repaint();

			//System.out.println("find node " + targetNode);
		}


	}


	// --------------------adjust width factor event ------------------------------
	public void adjustWidthEvent ()
	{
		mainUI.terrainData.updateBackendWeight();
		mainUI.terrainData.setupAllPixelVal();
		mainUI.terrainData.setAutomaticColorThreshold();
		mainUI.terrainData.setColorBar();

		mainUI.terrainData.setupDrawingPixelColor();
		mainUI.repaint();
	}


	// ----------------- check if a string is a number -------------------------
	private boolean isNumber (String str)
	{
		boolean result = true;
		try
		{
			Float.parseFloat(str);
		}
		catch (Exception e)
		{
			result = false;
		}

		return result;
	}

}
