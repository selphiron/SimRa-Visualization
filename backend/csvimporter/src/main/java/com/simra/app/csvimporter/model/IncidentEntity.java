package com.simra.app.csvimporter.model;


import com.mongodb.client.model.geojson.Point;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

import java.util.Date;
import java.util.HashMap;


@Document(collection = "incidents")
public class IncidentEntity extends IncidentCSV {

    @Id
    public HashMap id;

    public String region;

    private Date addedAt;

    private String weekday;

    private int minuteOfDay;


    /**
     * {@code location} is stored in GeoJSON format.
     *
     * <pre>
     * <code>
     * {
     *   "type" : "Point",
     *   "coordinates" : [ x, y ]
     * }
     * </code>
     * </pre>
     */
    public Point location;

    public IncidentEntity() {
    }

    public IncidentEntity(String fileId) {
        this.fileId = fileId;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    public HashMap getId() {
        return id;
    }

    public void setId(HashMap id) {
        this.id = id;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public int getMinuteOfDay() {
        return minuteOfDay;
    }

    public void setMinuteOfDay(int minuteOfDay) {
        this.minuteOfDay = minuteOfDay;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
