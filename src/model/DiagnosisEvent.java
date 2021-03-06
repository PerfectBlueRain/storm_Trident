package model;

import java.io.Serializable;

public class DiagnosisEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    public double lat;
    public double lng;
    public String time;
    public String diagnosisCode;

    public DiagnosisEvent(double lat, double lng, String time, String diagnosisCode) {
        super();
        this.time = time;
        this.lat = lat;
        this.lng = lng;
        this.diagnosisCode = diagnosisCode;
    }
}
