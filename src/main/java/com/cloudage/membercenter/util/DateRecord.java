package com.cloudage.membercenter.util;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class DateRecord extends BaseEntity{
	Date creataDate;
	Date editDate;
	public Date getCreataDate() {
		return creataDate;
	}
	public void setCreataDate(Date creataDate) {
		this.creataDate = creataDate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	@PreUpdate
	void onPreUpdate()
	{
		editDate=new Date();
	}
	@PrePersist
	void onPrePersist()
	{
		creataDate=new Date();
		editDate=new Date();
				
	}

}
