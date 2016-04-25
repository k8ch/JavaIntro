/*
 * Student Name: Kateryna Chernega
 * Student Number: 7246161
 */
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Database{
	private Question[] questions;
	private int size; 
	private String[] category;
  
  //stores categories, m strings and m*n questions, that are objects of class Question
  private Database(int m, int n){
	  size = n;	  
	  questions = new Question[m*n];
	  category = new String[m];
	  
  }
  //reads information from a file and returns an object with the information in it. Return null if the file not found/incorrect/incomplete 
  public static Database readQuestion(String name){
	  Scanner info;
	  String test; 
	  name="test.txt";

	  int n;
	  int m;
	  try{
		  info = new Scanner(new File(name));
	  }
	  catch (FileNotFoundException e){
		info = null;  
	  }
	  
	  if(name == null){
		 System.out.println("File not found");
		 }
	  else{  
		  m = Integer.parseInt(info.nextLine());
		  n = Integer.parseInt(info.nextLine());
		  String[] category = new String[m];
		  Question[] question =  new Question[n*m];
		 for(int i = 0; i < m; i++){
			 category[i] = info.nextLine(); 
		 }
		 for(int i = 0; i < m; i++ ){
			 for(int k = 0; k < n; k++){
				 Question q = new Question(info.nextLine(),info.nextLine());
				 question[k+(i*n)]= q; 
			 }
		 }
		  Database database = new Database(m, n);
		  database.questions=question;
		  database.category = category; 
	return database; 
	  }
	  
	  return null; 

  }
	 
				 
  public String getCategory(int index){
	  return category[index];
     
  }
  //set the category at position index to category
  public void setCategory(String category, int index){
	   this.category[index] = category; 
  }
  public Question getQuestion(int category, int index){
	return questions[index+size*category];
	  	
  }
  public void setQuestion(int category, int index, Question question){
	  questions[index+size*category] = question; 
	  
  }
  public int getNumCategories(){
	  return category.length; 
	  
  }
  public int getNumQuestions(){
	  return questions.length; 
  }
    
}
