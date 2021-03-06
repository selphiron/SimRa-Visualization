package visualization.web.resources.serializers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import visualization.data.mongodb.entities.StatisticsEntity;
import visualization.web.resources.StatisticsResource;

import java.io.IOException;

@Component
public class StatisticsResourceMapper {

    @Autowired
    StatisticsAgeGroupDataMapper statisticsAgeGroupDataMapper;

    @Autowired
    StatisticsAgeGroupTotalMapper statisticsAgeGroupTotalMapper;

    public StatisticsResource mapStatisticsEntityToResource(StatisticsEntity statisticsEntity) throws IOException {

        StatisticsResource statisticsResource = new StatisticsResource();

        statisticsResource.setId(statisticsEntity.getId());
        statisticsResource.setRegion(statisticsEntity.getRegion());
        statisticsResource.setTimestamp(statisticsEntity.getTimestamp());
        statisticsResource.setRideCount(statisticsEntity.getRideCount());
        statisticsResource.setAccumulatedDistance(statisticsEntity.getAccumulatedDistance());
        statisticsResource.setAccumulatedDuration(statisticsEntity.getAccumulatedDistance());
        statisticsResource.setAccumulatedSavedCO2(statisticsEntity.getAccumulatedDuration());
        statisticsResource.setAverageDuration(statisticsEntity.getAverageDuration());
        statisticsResource.setAverageDistance(statisticsEntity.getAverageDistance());
        statisticsResource.setAverageSpeed(statisticsEntity.getAverageSpeed());
        statisticsResource.setIncidentCount(statisticsEntity.getIncidentCount());
        statisticsResource.setIncidentCountScary(statisticsEntity.getIncidentCountScary());
        statisticsResource.setIncidentCountWithChildrenInvolved(statisticsEntity.getIncidentCountWithChildrenInvolved());
        statisticsResource.setIncidentCountWithTrailersInvolved(statisticsEntity.getIncidentCountCountWithTrailersInvolved());
        statisticsResource.setIncidentParticipantTypeData(statisticsEntity.getIncidentParticipantTypeData());
        statisticsResource.setIncidentParticipantTypeLabels(statisticsEntity.getIncidentParticipantTypeLabels());
        statisticsResource.setIncidentTypeData(statisticsEntity.getIncidentTypeData());
        statisticsResource.setIncidentTypeLabels(statisticsEntity.getIncidentTypeLabels());
        statisticsResource.setProfileAgeDistributionLabels(statisticsEntity.getProfileAgeDistributionLabels());
        statisticsResource.setProfileAgeDistributionData(statisticsEntity.getProfileAgeDistributionData());
        statisticsResource.setProfileAgeDistributionDataMale(statisticsEntity.getProfileAgeDistributionDataMale());
        statisticsResource.setProfileAgeDistributionDataFemale(statisticsEntity.getProfileAgeDistributionDataFemale());
        statisticsResource.setProfileAgeDistributionDataOther(statisticsEntity.getProfileAgeDistributionDataOther());


        statisticsResource.setProfileAgeGroupCrossData(statisticsAgeGroupDataMapper.mapAgeGroupDataToResource(statisticsEntity.getProfileAgeGroupCrossData()));
        statisticsResource.setProfileAgeGroupCrossTotal(statisticsAgeGroupTotalMapper.mapAgeGroupTotalToResource(statisticsEntity.getProfileAgeGroupCrossTotal()));


        statisticsResource.setProfileBikeTypeData(statisticsEntity.getProfileBikeTypeData());
        statisticsResource.setProfileBikeTypeLabels(statisticsEntity.getProfileBikeTypeLabels());
        statisticsResource.setProfileCount(statisticsEntity.getProfileCount());
        statisticsResource.setProfileCountFemale(statisticsEntity.getProfileCountFemale());
        statisticsResource.setProfileCountMale(statisticsEntity.getProfileCountMale());
        statisticsResource.setProfileCountOther(statisticsEntity.getProfileCountOther());

        return statisticsResource;
    }
}
