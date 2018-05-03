package iliada.dovizapp;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ExampleDoviz implements Serializable {




    @SerializedName("selling")
    @Expose
    private Double selling;
    @SerializedName("update_date")
    @Expose
    private Integer updateDate;
    @SerializedName("currency")
    @Expose
    private Integer currency;
    @SerializedName("buying")
    @Expose
    private Double buying;
    @SerializedName("change_rate")
    @Expose
    private Double changeRate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("code")
    @Expose
    private String code;

    public Double getSelling() {
        return selling;
    }

    public void setSelling(Double selling) {
        this.selling = selling;
    }

    public Integer getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Integer updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public Double getBuying() {
        return buying;
    }

    public void setBuying(Double buying) {
        this.buying = buying;
    }

    public Double getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(Double changeRate) {
        this.changeRate = changeRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}