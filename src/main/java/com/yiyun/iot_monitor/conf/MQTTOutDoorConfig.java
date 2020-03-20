package com.yiyun.iot_monitor.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.UUID;

/**
 * MQTT室内设备配置
 */
@Configuration
public class MQTTOutDoorConfig {

    @Autowired
    private MqttPahoClientFactory mqttClientFactory;

    public static final String CHANNEL_OUTDOOR_IN = "mqttInboundChannelOutdoor";

    @Value("${spring.mqtt.consumer.client.id}")
    private String clientId;

    @Value("${spring.mqtt.consumer.outdoor.topic}")
    private String[] topic;

    @Value("${spring.mqtt.completionTimeout}")
    private int completionTimeout;

    //室外设备接收通道
    @Bean(name = CHANNEL_OUTDOOR_IN)
    public MessageChannel mqttInputChannelOutdoor() {
        return new DirectChannel();
    }

    //配置client,监听的topic
    @Bean
    public MessageProducer inboundOutDoor() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(UUID.randomUUID().toString(), mqttClientFactory,
                        topic);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannelOutdoor());
        return adapter;
    }

    //{mqtt_receivedRetained=false, id=eeb5cb5b-f75f-47b3-540a-f30867a4a3aa, mqtt_duplicate=false,
    // mqtt_receivedTopic=lc_gps, mqtt_receivedQos=0, timestamp=1584709440494}
    @Bean
    @ServiceActivator(inputChannel = CHANNEL_OUTDOOR_IN)
    public MessageHandler handlerIndoor() {
        return message -> {
            System.out.println(message.getHeaders());
            String msg = message.getPayload().toString();
            System.out.println("msg = " + msg);
        };
    }
}
