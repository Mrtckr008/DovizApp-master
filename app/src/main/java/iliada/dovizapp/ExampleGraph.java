package iliada.dovizapp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

class ExampleGraph implements Serializable {

    @SerializedName("selling")
    @Expose
    private Float selling;

    @SerializedName("update_date")
    @Expose
    private Integer updateDate;


    public Float getSelling() {
        return selling;
    }

    public void setSelling(Float selling) {
        this.selling = selling;
    }

    public Integer getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Integer updateDate) {
        this.updateDate = updateDate;
    }


}
