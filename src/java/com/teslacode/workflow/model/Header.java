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
 *
 * @version 1
 * @author Ade Fruandta
 */
@Entity
@Table(name="WF_HEADER")
public class Header implements Serializable {
    
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
    @Column(name="ACTIVITY_ID") 
    private Integer activityId;
    @Column(name="PREV_ACTIVITY_ID") 
    private Integer prevActivityId;
    @Column(name="STATUS") 
    private Integer status;
    
    @ManyToOne
    @JoinColumn(name="ACTIVITY_ID", insertable=false, updatable=false)
    private Activity activity;
    
    @ManyToOne
    @JoinColumn(name="PREV_ACTIVITY_ID", insertable=false, updatable=false)
    private Activity prevActivity;
    
    public Header(){
        entryDate = new Date();
        updateDate = new Date();
        status = 1;
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

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getPrevActivityId() {
        return prevActivityId;
    }

    public void setPrevActivityId(Integer prevActivityId) {
        this.prevActivityId = prevActivityId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getPrevActivity() {
        return prevActivity;
    }

    public void setPrevActivity(Activity prevActivity) {
        this.prevActivity = prevActivity;
    }
    
}
