package com.hit.beans;

import java.io.Serializable;

public class TenderStatusBean implements Serializable {

    private String tendorId;
    private String bidderId;
    private String status;
    private String vendorId;

    // 🆕 added - new fields for names
    private String tenderName;
    private String vendorName;

    // Default constructor
    public TenderStatusBean() {
        super();
    }

    // Existing constructor
    public TenderStatusBean(String tendorId, String bidderId, String status, String vendorId) {
        super();
        this.tendorId = tendorId;
        this.bidderId = bidderId;
        this.status = status;
        this.vendorId = vendorId;
    }

    // 🆕 added - new constructor that includes names
    public TenderStatusBean(String tendorId, String tenderName, String bidderId, String status,
                            String vendorId, String vendorName) {
        super();
        this.tendorId = tendorId;
        this.tenderName = tenderName;
        this.bidderId = bidderId;
        this.status = status;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
    }

    public String getTendorId() {
        return tendorId;
    }

    public void setTendorId(String tendorId) {
        this.tendorId = tendorId;
    }

    public String getBidderId() {
        return bidderId;
    }

    public void setBidderId(String bidderId) {
        this.bidderId = bidderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    // 🆕 added - getter and setter for Tender Name
    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    // 🆕 added - getter and setter for Vendor Name
    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
