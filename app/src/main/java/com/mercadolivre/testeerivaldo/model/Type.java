
package com.mercadolivre.testeerivaldo.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("payment_type_id")
    @Expose
    private String paymentTypeId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("secure_thumbnail")
    @Expose
    private String secureThumbnail;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Type() {
    }

    /**
     * 
     * @param minAllowedAmount
     * @param deferredCapture
     * @param status
     * @param paymentTypeId
     * @param maxAllowedAmount
     * @param additionalInfoNeeded
     * @param processingModes
     * @param financialInstitutions
     * @param accreditationTime
     * @param id
     * @param secureThumbnail
     * @param thumbnail
     * @param name
     */
    public Type(String id, String name, String paymentTypeId, String status, String secureThumbnail, String thumbnail, String deferredCapture,List<String> additionalInfoNeeded, Integer minAllowedAmount, Integer maxAllowedAmount, Integer accreditationTime, List<Object> financialInstitutions, List<String> processingModes) {
        super();
        this.id = id;
        this.name = name;
        this.paymentTypeId = paymentTypeId;
        this.status = status;
        this.secureThumbnail = secureThumbnail;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSecureThumbnail() {
        return secureThumbnail;
    }

    public void setSecureThumbnail(String secureThumbnail) {
        this.secureThumbnail = secureThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
