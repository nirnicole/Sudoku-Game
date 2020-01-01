
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//nir nicole, mmn13
//sudoku boards genarator
public class SudokuGUI extends JFrame{

	private static SudokuJPanel[][] allPanels = new SudokuJPanel[9][9];	
	private JPanel sudokuMainPanel = new JPanel();
	private JPanel buttonsMainPanel = new JPanel();
	private JButton cmdSet = new JButton("Set");
	private JButton cmdClear = new JButton("Clear");
	private boolean boardReady = false;

	//costume constructor
	public SudokuGUI() {
		super("sudoku");
		  
		this.setLayout(new BorderLayout());

		sudokuMainPanel.setLayout(new GridLayout(9,9,3,3));

		//adding the panels in a colurfull pattern
		for(int i=0; i<9 ; i++)
			for(int j=0; j<9 ; j++)
			{
				if((i/3 + j/3)%2==0 )
					this.allPanels[i][j] = new SudokuJPanel(i,j,Color.gray);
				else this.allPanels[i][j] = new SudokuJPanel(i,j,Color.white);
				
			allPanels[i][j].addMouseListener(new MouseListenerCMD());
			sudokuMainPanel.add(allPanels[i][j]);
			}
			
		
		ButtonListenerCMD listener = new ButtonListenerCMD();
		cmdSet.addActionListener(listener);
		cmdClear.addActionListener(listener);
		buttonsMainPanel.add(cmdSet);
		buttonsMainPanel.add(cmdClear);
		this.add(sudokuMainPanel, BorderLayout.CENTER);
		this.add(buttonsMainPanel, BorderLayout.SOUTH);
		this.setSize(1000,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	

	//sudoku rules approval methods
	//
	public boolean approveLine(int line, int number) {
		
		for(int i=0; i<9 ; i++)
				if(allPanels[line][i].getNumber() == number)
					{
					JOptionPane.showMessageDialog(null, "Error, value already in line!");
					return false;
					}
		
		return true;
	}	
	
	public boolean approveColumn(int column, int number) {
		
		for(int i=0; i<9 ; i++)
				if(allPanels[i][column].getNumber() == number)
					{
					JOptionPane.showMessageDialog(null, "Error, value already in column!");
					return false;
					}
		
		return true;
	}	

	public boolean approveArea(int line,int column, int number) {
		
		for(int i=(line/3)*3; i< ((line/3)*3 + 3) ; i++)
			for(int j=(column/3)*3; j< ((column/3)*3 + 3) ; j++)
				if(allPanels[i][j].getNumber() == number)
					{
					JOptionPane.showMessageDialog(null, "Error, value already in 9^9 area!");
					return false;
					}
		
		return true;
	}	

	public boolean approveValue(int number) {
		
				if(number<1 || number>9)
					{
					JOptionPane.showMessageDialog(null, "Error, values must be between 1-9!");
					return false;
					}
		
		return true;
	}	
	
	
	 private class MouseListenerCMD implements MouseListener { 

		    // default constructor 
			MouseListenerCMD() 
		    { 

		    }

		    public void mousePressed(MouseEvent e) 
		    { 
		    } 
		    public void mouseReleased(MouseEvent e) 
		    { 
		    } 
		  
		    // this function is invoked when the mouse exits the component 
		    public void mouseExited(MouseEvent e) 
		    { 
		  
		        // show change of color when the mouse exited the frame 
	            Object source = e.getSource();
	            if(source instanceof SudokuJPanel){
	            	SudokuJPanel panelPressed = (SudokuJPanel) source;
	                if(! panelPressed.isLocked())
	                	panelPressed.setBackground(panelPressed.getColor());
	            }

		    } 
		  
		    // this function is invoked when the mouse enters the component 
		    public void mouseEntered(MouseEvent e) 
		    { 
		  
		        //  show change of color when the mouse entered the frame 
	            Object source = e.getSource();
	            if(source instanceof SudokuJPanel){
	            	SudokuJPanel panelPressed = (SudokuJPanel) source;
	                if(! panelPressed.isLocked())
	                	panelPressed.setBackground(Color.darkGray);
	            }

		    } 
		  
		    // this function is invoked when the mouse is pressed or released 
		    public void mouseClicked(MouseEvent e) 
		    { 

		    	int number;
	            Object source = e.getSource();
	            if(source instanceof SudokuJPanel){
	            	SudokuJPanel panelPressed = (SudokuJPanel) source;
	            
	            if(! panelPressed.isLocked()) 
	            	{
	            	number = Integer.parseInt(JOptionPane.showInputDialog("Please enter a number between 1-9:") );    
	            	if(approveValue(number) && approveLine(panelPressed.getLineIndex(), number) && approveColumn(panelPressed.getColumnIndex(), number) && approveArea(panelPressed.getLineIndex(), panelPressed.getColumnIndex(), number) )
	            		panelPressed.setNumber(number);
	            	}
	                
	            }
		    	repaint();
		    } 
	 }
	
	 public class ButtonListenerCMD implements ActionListener { 
		  public void  actionPerformed(ActionEvent e) {
			  
			  //set game to play
				  if(e.getSource() == cmdSet) {
					  if(!boardReady)
					  {
						  for(int i=0; i<9 ; i++)
							for(int j=0; j<9 ; j++)
								if(allPanels[i][j].getNumber()>=1 && allPanels[i][j].getNumber()<=9)
									allPanels[i][j].setLocked(true);
					  boardReady = true;
					  }else JOptionPane.showMessageDialog(null, "Board in play!");
			  }
				  
			//clear board
				  if(e.getSource() == cmdClear) {
						  for(int i=0; i<9 ; i++)
							for(int j=0; j<9 ; j++)
								if(allPanels[i][j].getNumber()>=1 && allPanels[i][j].getNumber()<=9)
									allPanels[i][j].clearPanel();
					  boardReady = false;
					  JOptionPane.showMessageDialog(null, "Clearing board!");
			  }
			  
			  repaint();
		  }
		  
	 }//end of button listner
	
}//end of class