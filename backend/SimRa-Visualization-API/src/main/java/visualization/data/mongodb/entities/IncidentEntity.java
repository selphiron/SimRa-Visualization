package visualization.data.mongodb.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "incidents")
public class IncidentEntity {

    @Id
    private CompositeKey id;

    private String fileId;

    private int key;

    private int bike;

    // not used atm
    //private GeoJsonPoint location;

    private GeoJsonPoint locationMapMatched;

    private long ts;

    private Boolean childCheckBox;

    private Boolean trailerCheckBox;

    private int pLoc;

    private int incident;

    private Boolean scary;

    private String description;

    private Boolean i1;

    private Boolean i2;

    private Boolean i3;

    private Boolean i4;

    private Boolean i5;

    private Boolean i6;

    private Boolean i7;

    private Boolean i8;

    private Boolean i9;

    private Boolean i10;


    // Incident Key made of rideId and key
    @Getter
    @Setter
    @AllArgsConstructor
    public static class CompositeKey implements Serializable {
        private String rideid;
        private String key;
    }

}
