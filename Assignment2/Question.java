/*
 * Student Name: Kateryna Chernega
 * Student Number: 7246161
 */
public class Question{
  
  private String question; 
  private String response;
  
  public Question(String response, String question){
    
    this.response = response; 
    this.question = question; 
    
  }
  
  public String getQuestion(){
    
    return question;
  }
  public String getResponse(){
    
    return response; 
  }
}

