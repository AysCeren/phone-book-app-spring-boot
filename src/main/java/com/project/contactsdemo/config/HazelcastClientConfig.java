package com.project.contactsdemo.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastClientConfig {

    @Bean
    public ClientConfig clientConfig(){
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setInstanceName("training-instance");
        clientConfig.setClusterName("dev");
        ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
        networkConfig.addAddress("127.0.0.1:5701");
        networkConfig
                .setRedoOperation(true)
                .setConnectionTimeout(200);
        return clientConfig;
    }

    @Bean
    public HazelcastInstance trainingInstance(ClientConfig clientConfig) {
        return HazelcastClient.newHazelcastClient(clientConfig);
    }
}