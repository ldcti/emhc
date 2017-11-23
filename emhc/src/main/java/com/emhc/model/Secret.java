package com.emhc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the secrets database table.
 * 
 */
@Entity
@Table(name="secrets")
@NamedQuery(name="Secret.findAll", query="SELECT s FROM Secret s")
public class Secret implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int secretid;

	private String code;

	public Secret() {
	}

	public int getSecretid() {
		return this.secretid;
	}

	public void setSecretid(int secretid) {
		this.secretid = secretid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}