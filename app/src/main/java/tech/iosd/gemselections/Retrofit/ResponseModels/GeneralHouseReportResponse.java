package tech.iosd.gemselections.Retrofit.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class GeneralHouseReportResponse {

    @SerializedName("planet")
    private String planet;
    @SerializedName("house_report")
    private String houseReport;

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public String getHouseReport() {
        return houseReport;
    }

    public void setHouseReport(String houseReport) {
        this.houseReport = houseReport;
    }
}
