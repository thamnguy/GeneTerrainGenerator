import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawingTerrainPanel extends JPanel 
{
	Color [][] pixelColor;
	GeneTerrainMainUI mainUI;
	boolean drawNode;
	boolean drawEdge;
	boolean viewRadius;
	boolean pressNode;
	int pressPtX;
	int pressPtY;
	int pressRadius;
	
	//String highlightNode = "";
	String [] highlightNode = new String[0];
	
	//float [][] pixelValue;
	//int upLeftX; // the 'upper left' x coordinate of the pixel table to be drawn
	//int upLeftY; // the 'upper left' y coordinate of the pixel table to be drawn
	
	public DrawingTerrainPanel(GeneTerrainMainUI argUI) 
	{
		super();
		drawNode = false;
		drawEdge = false;
		viewRadius = false;
		pressNode = false;
		pressPtX = -1;
		pressPtY = -1;
		pressRadius = 0;
		mainUI = argUI;
	}
	
	
	// --------------------- response to mouse press event -------------------------
	public void receiveMousePress(int argX, int argY, int argRadius )
	{
		pressNode = true;
		pressPtX = argX;
		pressPtY = argY;
		pressRadius = argRadius;
	}
	
	
	// --------------- response to mouse release event -------------------
	public void receiveMouseRelease()
	{
		pressNode = false;
		pressPtX = -1;
		pressPtY = -1;
		pressRadius = 0;
	}
	
	
	// ------------------ initialize the pixel ----------------------
	public void initilizePixelColor()
	{
		pixelColor = new Color[this.getHeight()][this.getWidth()];
		//pixelColor = new Color[3*this.getHeight()][3*this.getWidth()];
		//pixelValue = new float [3*this.getHeight()][3*this.getWidth()];
		
		for (int i = 0; i < this.getHeight(); i++)
		//for (int i = 0; i < 3*this.getHeight(); i++)
		{
			for (int j = 0; j < this.getWidth(); j++)
			//for (int j = 0; j < 3*this.getWidth(); j++)
			{
				pixelColor[i][j] = Color.blue;
				//pixelValue[i][j] = 0;
			}
		}
		
		//upLeftX = this.getWidth();
		//upLeftY = this.getHeight();
		
	}
	
	
	// ------------------ pain the terrain panel ---------------------
	public void paintComponent (Graphics g)
	{
		ArrayList<String> list = new ArrayList<String>();
		if (highlightNode.length > 0)
		{
			for (int i = 0; i < highlightNode.length; i++)
			{
				list.add(highlightNode[i]);
			}
		}
		
		super.paintComponent(g);
		for (int h = 0; h < this.getHeight(); h++)
		{
			for (int w = 0; w < this.getWidth(); w++)
			{
				g.setColor(pixelColor[h][w]);
				//g.setColor(pixelColor[h+upLeftY][w+upLeftX]);
				g.fillRect(w, h, 1, 1);
			}
		}
		
		if (drawNode)
		{
			g.setColor(Color.GRAY);
			for (int i = 0; i < mainUI.terrainData.nodeID.length; i++)
			{
				//if (mainUI.terrainData.nodeID[i].compareToIgnoreCase(highlightNode) != 0)
				if(!list.contains(mainUI.terrainData.nodeID[i]))
				{
					int stringLen = (int)
				            g.getFontMetrics().getStringBounds(mainUI.terrainData.nodeID[i], g).getWidth();
					
					//g.drawString(mainUI.terrainData.nodeID[i], mainUI.terrainData.nodeXPixel[i]-2*(int)mainUI.terrainData.nodeSize[i] - 2,
					//		mainUI.terrainData.nodeYPixel[i]-2*(int)mainUI.terrainData.nodeSize[i]-2);
					g.drawString(mainUI.terrainData.nodeID[i], mainUI.terrainData.nodeXPixel[i]-stringLen/2,
							mainUI.terrainData.nodeYPixel[i]);
				}
				
			}
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			for (int i = 0; i < mainUI.terrainData.nodeID.length; i++)
			{
				//if (mainUI.terrainData.nodeID[i].compareToIgnoreCase(highlightNode) == 0)
				if(list.contains(mainUI.terrainData.nodeID[i]))
				{
					int stringLen = (int)
				            g.getFontMetrics().getStringBounds(mainUI.terrainData.nodeID[i], g).getWidth();
					
					//g.drawString(mainUI.terrainData.nodeID[i], mainUI.terrainData.nodeXPixel[i]-2*(int)mainUI.terrainData.nodeSize[i] - 2,
					//		mainUI.terrainData.nodeYPixel[i]-2*(int)mainUI.terrainData.nodeSize[i]-2);
					g.drawString(mainUI.terrainData.nodeID[i], mainUI.terrainData.nodeXPixel[i]-stringLen/2,
							mainUI.terrainData.nodeYPixel[i]);
				}
				if (viewRadius)
				{
					g.drawOval(mainUI.terrainData.nodeXPixel[i] - mainUI.terrainData.nodeRadius[i], mainUI.terrainData.nodeYPixel[i] - mainUI.terrainData.nodeRadius[i], 
							2*mainUI.terrainData.nodeRadius[i], 2*mainUI.terrainData.nodeRadius[i]);
				}
				else
				{
					g.drawOval(mainUI.terrainData.nodeXPixel[i] - 2, mainUI.terrainData.nodeYPixel[i] - 2, 4, 4);
				}
				
				
			}		
		}
		
		if (drawEdge)
		{
			g.setColor(Color.GRAY);
			for (int i = 0; i < mainUI.terrainData.edge.length; i++)
			{
				int startX = mainUI.terrainData.nodeXPixel[mainUI.terrainData.edge[i].beginIndex];
				int startY = mainUI.terrainData.nodeYPixel[mainUI.terrainData.edge[i].beginIndex];
				int endX = mainUI.terrainData.nodeXPixel[mainUI.terrainData.edge[i].endIndex];
				int endY = mainUI.terrainData.nodeYPixel[mainUI.terrainData.edge[i].endIndex];
				
				g.drawLine(startX, startY, endX, endY);
			}
		}
		
		if (pressNode)
		{
			g.setColor(Color.MAGENTA);
			g.drawOval(pressPtX-pressRadius, pressPtY-pressRadius, 2*pressRadius, 2*pressRadius);
		}
	}

	
	// ----------------- save the terrain panel to image --------------------------------
	public void saveImage (String imgFileName) throws Exception
	{
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		printAll(g);
		g.dispose();

		ImageIO.write(image, "png", new File(imgFileName)); 

	}
}
