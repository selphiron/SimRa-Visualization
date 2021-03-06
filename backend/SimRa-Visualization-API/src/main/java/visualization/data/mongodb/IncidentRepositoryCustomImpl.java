package visualization.data.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import visualization.data.mongodb.entities.IncidentEntity;

import java.util.List;

public class IncidentRepositoryCustomImpl implements IncidentRepositoryCustom {

    @Autowired
    private MongoTemplate mongotemplate;

    @Override
    public List<IncidentEntity> findFilteredIncidents(GeoJsonPolygon polygon, Long fromTs, Long untilTs, Integer fromMinutesOfDay, Integer untilMinutesOfDay, List<String> weekdays, List<Integer> bikeTypes, List<Integer> incidentTypes, Boolean childInvolved, Boolean trailerInvolved, Boolean scary, List<Boolean> participants, Boolean description) {

        Query query = new Query();
        if (polygon != null) {
            query.addCriteria(Criteria.where("locationMapMatched").within(polygon));
        }
        if (fromTs != null && untilTs != null) {
            query.addCriteria(Criteria.where("ts").gte(fromTs).lte(untilTs));
        }
        if (fromMinutesOfDay != null && untilMinutesOfDay != null) {
            query.addCriteria(Criteria.where("minuteOfDay").gte(fromMinutesOfDay).lte(untilMinutesOfDay));
        }
        if (weekdays.size() > 0) {
            query.addCriteria(Criteria.where("weekday").in(weekdays));
        }
        if (bikeTypes.size() > 0) {
            query.addCriteria(Criteria.where("bike").in(bikeTypes));
        }
        if (incidentTypes.size() > 0) {
            query.addCriteria(Criteria.where("incident").in(incidentTypes));
        }
        if (childInvolved != null) {
            query.addCriteria(Criteria.where("childCheckBox").is(childInvolved));
        }
        if (trailerInvolved != null) {
            query.addCriteria(Criteria.where("trailerCheckBox").is(trailerInvolved));
        }
        if (scary != null) {
            query.addCriteria(Criteria.where("scary").is(scary));
        }
        if (description != null) {
            query.addCriteria(Criteria.where("description").exists(true));
        }
        if (participants.size() > 0) {
            query.addCriteria(Criteria.where("i1").is(participants.get(0))
                    .and("i2").is(participants.get(1))
                    .and("i3").is(participants.get(2))
                    .and("i4").is(participants.get(3))
                    .and("i5").is(participants.get(4))
                    .and("i6").is(participants.get(5))
                    .and("i7").is(participants.get(6))
                    .and("i8").is(participants.get(7))
                    .and("i9").is(participants.get(8))
                    .and("i10").is(participants.get(9)));
        }

        return mongotemplate.find(query, IncidentEntity.class);
    }
}
