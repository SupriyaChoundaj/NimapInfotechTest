package com.example.demo.model;

import javax.persistence.*;




@Entity
@Table(name="categories")
public class categories 
{
 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long cid;
	
	@Column(name="cname")
	private String cname;
	
	@Column(name = "description")
	private String description;

	public categories() {
		
	}
	
	public categories(String cname,String description)
	{
		this.cname=cname;
		this.description=description;
	}


	public long getCid() {
		return cid;
	}


	public void setCid(long cid) {
		this.cid = cid;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "categories [cid=" + cid + ", cname=" + cname + ", description=" + description + "]";
	}
	
	
}
