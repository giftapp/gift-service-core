package application.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

/**
 * Created by matan on 09/05/2016.
 */

@Configuration
@EnableMongoRepositories(basePackages="application.repositories")
@EnableMongoAuditing
class MongoDBConfiguration extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private int mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private char[] password;

    @Override
    protected String getDatabaseName() {
        return mongoDB;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        MongoCredential mongoCredential = MongoCredential.createCredential(username, mongoDB, password);
        return new MongoClient(new ServerAddress(mongoHost, mongoPort) , Arrays.asList(mongoCredential));
    }

    @Override
    protected String getMappingBasePackage() {
        return "application.model";
    }
}
