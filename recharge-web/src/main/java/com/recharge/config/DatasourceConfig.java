package com.recharge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class DatasourceConfig {

	@Value("${mongodb.server.host:localhost}")
	private String host;
	
	@Value("${mongodb.server.port:27017}")
	private int port;
	
	@Value("${mongodb.server.dbname:rechargedb}")
	private String dbname;
	
	@Bean
	public Mongo mongo() throws Exception{
		return new MongoClient(host,port);
	}
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception{
		return new MongoTemplate(mongo(),dbname);
	}
}
