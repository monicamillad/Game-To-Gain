package com.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Long qid;
	
	private String q ;
	
	private String c1 ;
	private String c2 ;
	private String c3 ;
	private String c4 ;
	
	private String answer ;
	
	private String cuurentAnswer ;
	
	@ManyToOne
	@JoinColumn(name="id") 
	private Game game ;
	
	public Question(){

		c1=c2=c3=c4="" ;
		game = new Game() ;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String[] getChoices(){
		
		String[] arr = new String[4] ;
		
		arr[0] = c1 ;
		arr[1] = c2 ;
		arr[2] = c3 ;
		arr[3] = c4 ;
				
		return arr ;
		
	}
	
	public void setChoices(String[] arr){
		
		c1 = arr[0] ;
		c2 = arr[1] ;
		c3 = arr[2] ;
		c4 = arr[3] ;
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getQid() {
		return qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
	
		this.game.setDescription(game.getDescription());
		this.game.setId(game.getId());
		this.game.setName(game.getName());
		this.game.setNumberOfQ(game.getNumberOfQ());
		this.game.setRate(game.getRate());
		this.game.setType(game.getType());
		this.game.setCollaborators(game.getCollaborators());
	}
	
	public String getCuurentAnswer() {
		return cuurentAnswer;
	}

	public void setCuurentAnswer(String cuurentAnswer) {
		this.cuurentAnswer = cuurentAnswer;
	}
	
	public Question( Question qq ){
		
		this.answer = qq.getAnswer() ;
		this.c1 = qq.getC1() ;
		this.c2 = qq.getC2() ;
		this.c3 = qq.getC3() ;
		this.c4 = qq.getC4() ;
		this.q = qq.getQ() ;
		this.game = new Game() ;
	}

}