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
 * Model for Next Flag
 * 
 * @version 1
 * @author Ade Fruandta
 */
@Entity
@Table(name="WF_PRM_NEXT_FLAG")
public class NextFlag implements Serializable {
    
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
    @Column(name="FROM_ACTIVITY_ID") 
    private Integer fromActivityId;
    @Column(name="TO_ACTIVITY_ID") 
    private Integer toActivityId;
    @Column(name="FLAG") 
    private String flag;
    @Column(name="ORDERS") 
    private Integer order;
    @Column(name="HIDE") 
    private Boolean hide;
    @Column(name="ACTIVE") 
    private Boolean active;

    @ManyToOne
    @JoinColumn(name="FROM_ACTIVITY_ID", insertable=false, updatable=false)
    private Activity fromActivity;
    
    @ManyToOne
    @JoinColumn(name="TO_ACTIVITY_ID", insertable=false, updatable=false)
    private Activity toActivity;
    
    public NextFlag(){
        entryDate = new Date();
        updateDate = new Date();
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

    public Integer getFromActivityId() {
        return fromActivityId;
    }

    public void setFromActivityId(Integer fromActivityId) {
        this.fromActivityId = fromActivityId;
    }

    public Integer getToActivityId() {
        return toActivityId;
    }

    public void setToActivityId(Integer toActivityId) {
        this.toActivityId = toActivityId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getHide() {
        return hide;
    }

    public void setHide(Boolean hide) {
        this.hide = hide;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Activity getFromActivity() {
        return fromActivity;
    }

    public void setFromActivity(Activity fromActivity) {
        this.fromActivity = fromActivity;
    }

    public Activity getToActivity() {
        return toActivity;
    }

    public void setToActivity(Activity toActivity) {
        this.toActivity = toActivity;
    }
    
}
