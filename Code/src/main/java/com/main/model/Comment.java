package com.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private long commentId;
	
	@ManyToOne
	@JoinColumn(name="id")
	private Game game1 ;
	
	private String content ;
	
	public Comment(){
		
		game1 = new Game() ;
	}

	public long getCommentId() {
		return commentId;
	}



	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}



	public Game getGame1() {
		return game1;
	}

	public void setGame1(Game game1) {
		
		this.game1.setDescription(game1.getDescription());
		this.game1.setId(game1.getId());
		this.game1.setName(game1.getName());
		this.game1.setNumberOfQ(game1.getNumberOfQ());
		this.game1.setRate(game1.getRate());
		this.game1.setType(game1.getType());
		this.game1.setCollaborators(game1.getCollaborators());
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
