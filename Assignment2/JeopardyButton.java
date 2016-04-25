/*
 * Student Name: Kateryna Chernega
 * Student Number: 7246161
 */
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class JeopardyButton extends JButton{

  private int category; 
  private int question;

  public JeopardyButton(ActionListener listener, int category, 
		  int question, int amount){
	  super(""+amount);
    this.category = category; 
    this.question = question; 

   /* setBackground(Color.WHITE);
    setIcon(getIcon());
    Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
    setBorder(emptyBorder);
    setBorderPainted(false);*/
    this.addActionListener(listener);
    
    
  }
  


public int getCategory(){
  
    return category;
  }
  public int getQuestion(){
  
    return question;
  }
}