package com.amedeekreuzer.flashcards;

public class FlashCard {
	
	private String question;
	private String answer;
	
	public FlashCard(String question, String answer) {
		this.question = question;
		this.answer = answer;	
	}
	
	//Getters and Setters
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	

}
