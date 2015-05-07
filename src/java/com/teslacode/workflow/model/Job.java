package com.teslacode.workflow.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Model for Job
 * 
 * @version 1
 * @author Ade Fruandta
 */
@Entity
@Table(name="WF_PRM_JOB")
public class Job implements Serializable {
    
    @Id @Column(name="CODE", updatable=false) 
    private String code;
    @Column(name="ENTRY_DATE", updatable=false) 
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;
    @Column(name="UPDATE_DATE") 
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name="DESCRIPTION") 
    private String description;
    @Column(name="ACTIVE") 
    private Boolean active;

    public Job(){
        entryDate = new Date();
        updateDate = new Date();
        active = true;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
}
