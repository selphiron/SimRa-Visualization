package main.java.com.simra.app.csvimporter.model;

import com.mongodb.client.model.geojson.MultiPoint;
import com.mongodb.client.model.geojson.Position;
import main.java.com.simra.app.csvimporter.Utils;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The type Ride.
 */
public class Ride implements MongoDocument {

    private List<RideCSV> rideBeans;
    private List<RideCSV> mapMatchedRideBeans;
    private List<IncidentCSV> incidents;

    private Float distance;

    /**
     * Instantiates a new Ride.
     */
    public Ride() {
        // default constructor
    }

    /**
     * Gets ride beans.
     *
     * @return the ride beans
     */
    public List getRideBeans() {
        return this.rideBeans;
    }

    /**
     * Sets ride beans.
     *
     * @param rideBeans the ride beans
     */
    public void setRideBeans(List rideBeans) {
        this.rideBeans = rideBeans;
    }

    /**
     * Gets map matched ride beans.
     *
     * @return the map matched ride beans
     */
    public List getMapMatchedRideBeans() {
        return this.mapMatchedRideBeans;
    }

    /**
     * Sets map matched ride beans.
     *
     * @param mapMatchedRideBeans the map matched ride beans
     */
    public void setMapMatchedRideBeans(List mapMatchedRideBeans) {
        this.mapMatchedRideBeans = mapMatchedRideBeans;
    }

    /**
     * Gets incidents.
     *
     * @return the incidents
     */
    public List getIncidents() {
        return incidents;
    }

    /**
     * Sets incidents.
     *
     * @param incidents the incidents
     */
    public void setIncidents(List incidents) {
        this.incidents = incidents;
    }

    /**
     * Gets distance.
     *
     * @return distance
     */
    public Float getDistance() {
        return distance;
    }

    /**
     * Sets distance.
     *
     * @param distance the distance
     */
    public void setDistance(Float distance) {
        this.distance = distance;
    }

    /**
     * To document object document.
     *
     * @return the document
     */
    @Override
    public Document toDocumentObject() {
        Document singleRide = new Document();
        singleRide.put("rideId", ((RideCSV) this.getRideBeans().get(0)).getFileId());
        singleRide.put("distance", this.distance);

        parseRideBeans(singleRide, rideBeans, "");
        parseRideBeans(singleRide, mapMatchedRideBeans, "MapMatched");


        singleRide.put("weekday", Utils.getWeekday(rideBeans.get(0).getTimeStamp()));
        singleRide.put("minuteOfDay", Utils.getMinuteOfDay(rideBeans.get(0).getTimeStamp()));

        return singleRide;
    }

    private void parseRideBeans(Document document, List<RideCSV> rideBeans, String suffix) {
        ArrayList<Position> coordinates = new ArrayList<>();

        rideBeans.forEach(ride -> {
            List<Double> places = Arrays.asList(Double.parseDouble(ride.getLat()), Double.parseDouble(ride.getLon()));
            Position pos = new Position(places);
            coordinates.add(pos);
        });
        MultiPoint coordinatesMulti = new MultiPoint(coordinates);

        document.put("location" + suffix, coordinatesMulti);
        ArrayList<Long> ts = new ArrayList<>();
        rideBeans.forEach(ride -> ts.add((ride).getTimeStamp()));
        document.put("ts" + suffix, ts);
    }

    public List<Document> incidentsDocuments() {
        ArrayList incidentsList = new ArrayList<Document>();
        this.incidents.forEach(incident -> incidentsList.add(((IncidentCSV) incident).toDocumentObject()));
        return incidentsList;
    }
}
