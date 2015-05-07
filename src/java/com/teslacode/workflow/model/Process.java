package com.teslacode.workflow.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Model for Process
 * 
 * @version 1
 * @author Ade Fruandta
 */
@Entity
@Table(name="WF_PRM_PROCESS")
public class Process implements Serializable {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", updatable=false) 
    private Integer id;
    @Column(name="ENTRY_DATE", updatable=false) 
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;
    @Column(name="UPDATE_DATE") 
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name="JOB_CODE") 
    private String jobCode;
    @Column(name="VERSION") 
    private Integer version;
    @Column(name="NOTES") 
    private String notes;
    @Column(name="ACTIVE") 
    private Boolean active;

    @ManyToOne
    @JoinColumn(name="JOB_CODE", insertable=false, updatable=false)
    private Job job;
    
    public Process(){
        entryDate = new Date();
        updateDate = new Date();
        active = false;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
    
}
