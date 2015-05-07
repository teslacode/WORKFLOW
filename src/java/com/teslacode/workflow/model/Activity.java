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
 * Model for Activity
 * 
 * @version 1
 * @author Ade Fruandta
 */
@Entity
@Table(name="WF_PRM_ACTIVITY")
public class Activity implements Serializable {
    
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
    @Column(name="PROCESS_ID") 
    private Integer processId;
    @Column(name="ORDERS") 
    private Integer order;
    @Column(name="PATH") 
    private String path;
    @Column(name="DESCRIPTION") 
    private String description;
    @Column(name="ACTIVE") 
    private Boolean active;
    @Column(name="RULES") 
    private String rules;
    
    @ManyToOne
    @JoinColumn(name="PROCESS_ID", insertable=false, updatable=false)
    private Process process;
    
    public Activity(){
        entryDate = new Date();
        updateDate = new Date();
        order = 1;
        active = true;
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

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
    
}
