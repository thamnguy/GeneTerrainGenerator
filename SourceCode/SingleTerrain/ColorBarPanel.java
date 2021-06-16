import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class ColorBarPanel extends JPanel 
{
	Color [][] pixelColor;
	public ColorBarPanel() 
	{
		super();
	}
	
	
	public void initilizePixelColor()
	{
		pixelColor = new Color[this.getHeight()][this.getWidth()];
		for (int i = 0; i < this.getHeight(); i++)
		{
			for (int j = 0; j < this.getWidth(); j++)
			{
				pixelColor[i][j] = Color.blue;
			}
		}
	}
	
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		for (int h = 0; h < this.getHeight(); h++)
		{
			for (int w = 0; w < this.getWidth(); w++)
			{
				g.setColor(pixelColor[h][w]);
				g.fillRect(w, h, 1, 1);
			}
		}
	}

}
