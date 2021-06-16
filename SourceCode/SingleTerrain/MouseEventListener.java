import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEventListener implements MouseListener {

	GeneTerrainData terrainData;
	DrawingTerrainPanel drawingPanel;
	
	public MouseEventListener (GeneTerrainData argTerrainData, DrawingTerrainPanel argDrawingPanel)
	{
		terrainData = argTerrainData;
		drawingPanel = argDrawingPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("Click at: " + e.getX() + ", " + e.getY());
		if (terrainData.nodeID != null)
		{
			int closestGeneIndex = terrainData.findClosestGene(e.getX(), e.getY());
			//System.out.println(closestGeneIndex);
			
			if (closestGeneIndex >= 0)
			{
				drawingPanel.mainUI.nodeInfoTxt.setText("Gene name: " + terrainData.nodeID[closestGeneIndex] + "\r\n"
					+ "Value: " + terrainData.nodeValue[closestGeneIndex] + "\r\n"
					+ "Size: " + terrainData.nodeSize[closestGeneIndex] + "\r\n"
					+ "Cumulative value: " + terrainData.pixelValue[e.getY() + terrainData.upLeftY][e.getX() + terrainData.upLeftX] + "\r\n"
					+ "X: " + terrainData.nodeX[closestGeneIndex] + "\r\n"
					+ "Y: " + terrainData.nodeY[closestGeneIndex] + "\r\n");
			}
		}
		

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int ptX = e.getX();
		int ptY = e.getY();
		int closestGeneIndex = terrainData.findClosestGene(ptX, ptY);
		//System.out.println(closestGeneIndex);
		
		if (closestGeneIndex >= 0)
		{
			drawingPanel.mainUI.nodeInfoTxt.setText("Gene name: " + terrainData.nodeID[closestGeneIndex] + "\r\n"
				+ "Value: " + terrainData.nodeValue[closestGeneIndex] + "\r\n"
				+ "Size: " + terrainData.nodeSize[closestGeneIndex] + "\r\n"
				+ "Cumulative value: " + terrainData.pixelValue[e.getY() + terrainData.upLeftY][e.getX() + terrainData.upLeftX] + "\r\n"
				+ "X: " + terrainData.nodeX[closestGeneIndex] + "\r\n"
				+ "Y: " + terrainData.nodeY[closestGeneIndex] + "\r\n");
			
			int radius = Math.round(terrainData.nodeBackWeight[closestGeneIndex] / (terrainData.rightView - terrainData.leftView) * terrainData.drawHeight);
			drawingPanel.receiveMousePress(ptX, ptY, radius);
			drawingPanel.repaint();
			drawingPanel.setToolTipText("Gene name: " + terrainData.nodeID[closestGeneIndex] + "\r\n, "
				+ "Value: " + terrainData.nodeValue[closestGeneIndex] + "\r\n, "
				+ "Size: " + terrainData.nodeSize[closestGeneIndex]);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drawingPanel.receiveMouseRelease();
		drawingPanel.repaint();
	}

}
