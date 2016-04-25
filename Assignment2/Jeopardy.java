/*
 * Student Name: Kateryna Chernega
 * Student Number: 7246161
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Jeopardy extends JFrame {
	private Database database; 
	private JeopardyButton[] allButtons;
	public Jeopardy(){
		super("Jeopardy");
		database = 	Database.readQuestion("test.txt");
	 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setBackground(Color.WHITE);
		 		 
		 allButtons= new JeopardyButton[database.getNumCategories()
		               *database.getNumQuestions()];
		 JPanel control = new JPanel();
	
		 for(int i = 0; i < database.getNumCategories(); i++ ){
			 for(int k = 0; k < database.getNumQuestions(); k++){
				 ActionListener listener = null; 
				 JeopardyButton button = new JeopardyButton(listener, i, k, 100); 
				 allButtons[i+k*(database.getNumQuestions()/database.getNumCategories())]= button;
				 control.add(button);
				 button.addActionListener(listener);
				 button.setFocusPainted(false);
				button.setVisible(true);
			 }
		 }
		 

		
		 control.setBackground(Color.WHITE);
		 
		 add(control, BorderLayout.SOUTH);

		 pack();
		 setResizable(false);
		 setVisible(true);
	}
	
	public static void main(String[] args){
		StudentInfo.display();
		new Jeopardy();
		
	}
}
