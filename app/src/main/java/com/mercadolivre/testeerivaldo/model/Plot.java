
package com.mercadolivre.testeerivaldo.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Plot {

    @SerializedName("installments")
    @Expose
    private Integer installments;
    @SerializedName("installment_rate")
    @Expose
    private Double installmentRate;
    @SerializedName("discount_rate")
    @Expose
    private Integer discountRate;
    @SerializedName("labels")
    @Expose
    private List<String> labels = null;
    @SerializedName("installment_rate_collector")
    @Expose
    private List<String> installmentRateCollector = null;
    @SerializedName("min_allowed_amount")
    @Expose
    private Integer minAllowedAmount;
    @SerializedName("max_allowed_amount")
    @Expose
    private Integer maxAllowedAmount;
    @SerializedName("recommended_message")
    @Expose
    private String recommendedMessage;
    @SerializedName("installment_amount")
    @Expose
    private Double installmentAmount;
    @SerializedName("total_amount")
    @Expose
    private Double totalAmount;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Plot() {
    }

    /**
     * 
     * @param minAllowedAmount
     * @param recommendedMessage
     * @param labels
     * @param totalAmount
     * @param installmentAmount
     * @param maxAllowedAmount
     * @param discountRate
     * @param installmentRateCollector
     * @param installmentRate
     * @param installments
     */
    public Plot(Integer installments, Double installmentRate, Integer discountRate, List<String> labels, List<String> installmentRateCollector, Integer minAllowedAmount, Integer maxAllowedAmount, String recommendedMessage, Double installmentAmount, Double totalAmount) {
        super();
        this.installments = installments;
        this.installmentRate = installmentRate;
        this.discountRate = discountRate;
        this.labels = labels;
        this.installmentRateCollector = installmentRateCollector;
        this.minAllowedAmount = minAllowedAmount;
        this.maxAllowedAmount = maxAllowedAmount;
        this.recommendedMessage = recommendedMessage;
        this.installmentAmount = installmentAmount;
        this.totalAmount = totalAmount;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Double getInstallmentRate() {
        return installmentRate;
    }

    public void setInstallmentRate(Double installmentRate) {
        this.installmentRate = installmentRate;
    }

    public Integer getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getInstallmentRateCollector() {
        return installmentRateCollector;
    }

    public void setInstallmentRateCollector(List<String> installmentRateCollector) {
        this.installmentRateCollector = installmentRateCollector;
    }

    public Integer getMinAllowedAmount() {
        return minAllowedAmount;
    }

    public void setMinAllowedAmount(Integer minAllowedAmount) {
        this.minAllowedAmount = minAllowedAmount;
    }

    public Integer getMaxAllowedAmount() {
        return maxAllowedAmount;
    }

    public void setMaxAllowedAmount(Integer maxAllowedAmount) {
        this.maxAllowedAmount = maxAllowedAmount;
    }

    public String getRecommendedMessage() {
        return recommendedMessage;
    }

    public void setRecommendedMessage(String recommendedMessage) {
        this.recommendedMessage = recommendedMessage;
    }

    public Double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }



}
