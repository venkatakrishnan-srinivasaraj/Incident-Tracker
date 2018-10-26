package com.venkat.hackuta.incidenttracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.geo.GeoDataManager;
import com.amazonaws.geo.GeoDataManagerConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;


@Configuration
@ComponentScan
public class DynamoDbConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.dynamodb.tablename}")
    private String amazonDynamoDBIncidentTableName;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

//    @Bean
//    public AmazonDynamoDB amazonDynamoDB() {
//        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
//        return amazonDynamoDB;
//    }
//    
//    @Bean
//    public DynamoDB dynamoDB() {
//        return new DynamoDB(amazonDynamoDBClient());
//    }
    
    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(amazonDynamoDBClient());
    }
    
    @Bean
    public AmazonDynamoDBClient amazonDynamoDBClient() {
        return new AmazonDynamoDBClient(amazonAWSCredentials());
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }

    @Bean
    public GeoDataManager geoDataManager() {
        return new GeoDataManager(geoDataManagerConfiguration());
    }

    @Bean
    public GeoDataManagerConfiguration geoDataManagerConfiguration() {
        return new GeoDataManagerConfiguration(amazonDynamoDBClient(), "incident");
    }
}
