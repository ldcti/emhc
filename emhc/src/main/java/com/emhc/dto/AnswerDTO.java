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

	private String button01;

	public String getButton02() {
		return button02;
	}

	public void setButton02(String button02) {
		this.button02 = button02;
	}

	public String getButton03() {
		return button03;
	}

	public void setButton03(String button03) {
		this.button03 = button03;
	}

	public String getButton04() {
		return button04;
	}

	public void setButton04(String button04) {
		this.button04 = button04;
	}

	public String getButton05() {
		return button05;
	}

	public void setButton05(String button05) {
		this.button05 = button05;
	}

	public String getButton06() {
		return button06;
	}

	public void setButton06(String button06) {
		this.button06 = button06;
	}

	public String getButton07() {
		return button07;
	}

	public void setButton07(String button07) {
		this.button07 = button07;
	}

	public String getButton08() {
		return button08;
	}

	public void setButton08(String button08) {
		this.button08 = button08;
	}

	public String getButton09() {
		return button09;
	}

	public void setButton09(String button09) {
		this.button09 = button09;
	}

	public String getButton10() {
		return button10;
	}

	public void setButton10(String button10) {
		this.button10 = button10;
	}

	public String getButton11() {
		return button11;
	}

	public void setButton11(String button11) {
		this.button11 = button11;
	}

	public String getButton12() {
		return button12;
	}

	public void setButton12(String button12) {
		this.button12 = button12;
	}

	public String getButton13() {
		return button13;
	}

	public void setButton13(String button13) {
		this.button13 = button13;
	}

	public String getButton14() {
		return button14;
	}

	public void setButton14(String button14) {
		this.button14 = button14;
	}

	private String button02;
	private String button03;
	private String button04;
	private String button05;
	private String button06;
	private String button07;
	private String button08;
	private String button09;
	private String button10;
	private String button11;
	private String button12;
	private String button13;
	private String button14;

	public String getButton01() {
		return button01;
	}

	public void setButton01(String button01) {
		this.button01 = button01;
	}

	public AnswerDTO() {

	}

	public AnswerDTO(Answer answer) {

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

	public Answer getAnswer() {

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
