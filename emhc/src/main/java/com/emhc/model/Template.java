package com.emhc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the templates database table.
 * 
 */
@Entity
@Table(name="templates")
@NamedQuery(name="Template.findAll", query="SELECT t FROM Template t")
public class Template implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int templateid;

	private String bcc;

	private String cc;

	private String content;

	private String name;

	private String sender;

	public Template() {
	}

	public int getTemplateid() {
		return this.templateid;
	}

	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}

	public String getBcc() {
		return this.bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getCc() {
		return this.cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

}