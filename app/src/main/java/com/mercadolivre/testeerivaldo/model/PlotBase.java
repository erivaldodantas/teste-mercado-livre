
package com.mercadolivre.testeerivaldo.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlotBase {

    @SerializedName("payment_method_id")
    @Expose
    private String paymentMethodId;
    @SerializedName("payment_type_id")
    @Expose
    private String paymentTypeId;

    @SerializedName("processing_mode")
    @Expose
    private String processingMode;
    @SerializedName("merchant_account_id")
    @Expose
    private Object merchantAccountId;
    @SerializedName("payer_costs")
    @Expose
    private List<Plot> plots = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PlotBase() {
    }

    /**
     * 
     * @param plots
     * @param issuer
     * @param paymentTypeId
     * @param merchantAccountId
     * @param processingMode
     * @param paymentMethodId
     */
    public PlotBase(String paymentMethodId, String paymentTypeId, String processingMode, Object merchantAccountId, List<Plot> plots) {
        super();
        this.paymentMethodId = paymentMethodId;
        this.paymentTypeId = paymentTypeId;
        this.processingMode = processingMode;
        this.merchantAccountId = merchantAccountId;
        this.plots = plots;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }


    public String getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(String processingMode) {
        this.processingMode = processingMode;
    }

    public Object getMerchantAccountId() {
        return merchantAccountId;
    }

    public void setMerchantAccountId(Object merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }

    public List<Plot> getPlots() {
        return plots;
    }

    public void setPlots(List<Plot> plots) {
        this.plots = plots;
    }



}
