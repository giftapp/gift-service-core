package application.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by matan on 11/05/2016.
 */

@Document
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PersistedObject implements Serializable {
    @Id
    @JsonSerialize(using=ObjectID_Serializer.class)
    private ObjectId id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public ObjectId getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt= new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public PersistedObject() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersistedObject)) return false;

        PersistedObject that = (PersistedObject) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

/**
 * Serialize ObjectId class to string
 */
class ObjectID_Serializer extends JsonSerializer<ObjectId> {

    @Override
    public void serialize(ObjectId objid, JsonGenerator jsongen, SerializerProvider provider) throws IOException, JsonProcessingException {

        if(objid == null ){
            jsongen.writeNull();
        }else{
            jsongen.writeString(objid.toString());
        }

    }

}