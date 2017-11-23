package com.emhc.dto;

import com.emhc.model.Answer;
import com.emhc.model.EmhcUser;

public class AnswerDTO {
	
	

	private int answerid;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String answer5;
	private String answer6;
	private String answer7;
	private String answer8;
	private String answer9;
	private String answer10;
	private String answer11;
	private String answer12;
	private String answer13;
	private String answer14;

	
	public AnswerDTO(){
		
	}	
	
	public AnswerDTO(Answer answer){
		
		this.answerid = answer.getAnswerid();
		this.user = answer.getUser();
		this.answer1 = answer.getAnswer1();
		this.answer2 = answer.getAnswer2();
		this.answer3 = answer.getAnswer3();
		this.answer4 = answer.getAnswer4();
		this.answer5 = answer.getAnswer5();
		this.answer6 = answer.getAnswer6();
		this.answer7 = answer.getAnswer7();
		this.answer8 = answer.getAnswer8();
		this.answer9 = answer.getAnswer9();
		this.answer10 = answer.getAnswer10();
		this.answer11 = answer.getAnswer11();
		this.answer12 = answer.getAnswer12();
		this.answer13 = answer.getAnswer13();
		this.answer14 = answer.getAnswer14();
	}
	
	public Answer getAnswer(){
		
	Answer answer = new Answer();
	answer.setAnswerid(this.getAnswerid());
	answer.setUser(this.getUser());
	answer.setAnswer1(this.getAnswer1());
	answer.setAnswer2(this.getAnswer2());
	answer.setAnswer3(this.getAnswer3());
	answer.setAnswer4(this.getAnswer4());
	answer.setAnswer5(this.getAnswer5());
	answer.setAnswer6(this.getAnswer6());
	answer.setAnswer7(this.getAnswer7());
	answer.setAnswer8(this.getAnswer8());
	answer.setAnswer9(this.getAnswer9());
	answer.setAnswer10(this.getAnswer10());
	answer.setAnswer11(this.getAnswer11());
	answer.setAnswer12(this.getAnswer12());
	answer.setAnswer13(this.getAnswer13());
	answer.setAnswer14(this.getAnswer14());
	return answer;
		
	} 
	public int getAnswerid() {
		return answerid;
	}
	public void setAnswerid(int answerid) {
		this.answerid = answerid;
	}
	public EmhcUser getUser() {
		return user;
	}

	public void setUser(EmhcUser user) {
		this.user = user;
	}
	private EmhcUser user;
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	public String getAnswer5() {
		return answer5;
	}
	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}
	public String getAnswer6() {
		return answer6;
	}
	public void setAnswer6(String answer6) {
		this.answer6 = answer6;
	}
	public String getAnswer7() {
		return answer7;
	}
	public void setAnswer7(String answer7) {
		this.answer7 = answer7;
	}
	public String getAnswer8() {
		return answer8;
	}
	public void setAnswer8(String answer8) {
		this.answer8 = answer8;
	}
	public String getAnswer9() {
		return answer9;
	}
	public void setAnswer9(String answer9) {
		this.answer9 = answer9;
	}
	public String getAnswer10() {
		return answer10;
	}
	public void setAnswer10(String answer10) {
		this.answer10 = answer10;
	}
	public String getAnswer11() {
		return answer11;
	}
	public void setAnswer11(String answer11) {
		this.answer11 = answer11;
	}
	public String getAnswer12() {
		return answer12;
	}
	public void setAnswer12(String answer12) {
		this.answer12 = answer12;
	}
	public String getAnswer13() {
		return answer13;
	}
	public void setAnswer13(String answer13) {
		this.answer13 = answer13;
	}
	public String getAnswer14() {
		return answer14;
	}
	public void setAnswer14(String answer14) {
		this.answer14 = answer14;
	}

}
