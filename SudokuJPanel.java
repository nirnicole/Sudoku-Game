import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

//nir nicole, mmn13
//sudoku panel genarator
public class SudokuJPanel extends JPanel {
	
	private int number=0;
	private int lineIndex;
	private int columnIndex;
	private Color beckgroundColor;
	private boolean locked = false;	//indicate a panel with a given value - not to be changed

	//costume constructor
	public SudokuJPanel(int line , int column, Color beckgroundColor) {
		this.lineIndex = line;
		this.columnIndex = column;
		this.beckgroundColor = beckgroundColor;
		setBackground(beckgroundColor);
	}
	
	//paint
	public void paintComponent(Graphics g)
	{
	   super.paintComponent(g); 

	   Font font = new Font("Verdana", Font.BOLD, 16);
	   g.setFont(font);

	   if(this.locked)
			  g.setColor(Color.BLUE);

	   if(this.number>=1 && this.number<=9)
		   g.drawString(Integer.toString(this.number), getWidth()/2, getHeight()/2);


	}
	
    	//getters and setters
  	//
	public int getLineIndex() {return lineIndex;}
	public int getColumnIndex() {return columnIndex;}
	public int getNumber() {return number;}
	public void setNumber(int n) {number = n;}
	public boolean isLocked() {return locked;}
	public void setLocked(boolean flag) {locked = flag;}
	public Color getColor() {return this.beckgroundColor;}
	public void setColor(Color c) {this.beckgroundColor = c;}
	
	//clean panel
	public void clearPanel() {
		number=0;
		locked = false;
	}
	
}//end of class
