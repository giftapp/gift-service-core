package application.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by matan on 09/05/2016.
 */

@Configuration
@EnableMongoRepositories(basePackages="application.repositories")
class MongoDBConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "giftDB";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(new MongoClientURI("mongodb://gift-service-core:gift-service-core1234@localhost:27017/giftDB"));
    }

    @Override
    protected String getMappingBasePackage() {
        return "application.model";
    }
}
