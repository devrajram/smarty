package biharidelights.smarty.message;

import biharidelights.smarty.model.InboundMsg;
import biharidelights.smarty.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;


@Service
@Slf4j
public class Sender {

    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic.test}")
    private String topic;

    public void send(String message){
        log.info("sending message='{}' to topic='{}'", message, topic);
        InboundMsg inboundMsg = new InboundMsg();
        inboundMsg.setKey("1");
        inboundMsg.setProfile("Vendor1");
        inboundMsg.setZipCode("01803");
        Product p1 = new Product();
        p1.setName("rice");
        p1.setPrice("20");
        p1.setUnit("lb");
        p1.setZipCode(inboundMsg.getZipCode());
        p1.setDescription("White Rice");
        p1.setProfile(inboundMsg.getProfile());
        Product p2 = new Product();
        p2.setName("Potato");
        p2.setPrice("1.99");
        p2.setUnit("lb");
        p2.setDescription("Russet Potato");
        p2.setZipCode(inboundMsg.getZipCode());
        p2.setProfile(inboundMsg.getProfile());
        List<Product> data = new ArrayList<>();
        data.add(p1);
        data.add(p2);
        inboundMsg.setData(data);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(inboundMsg);
            log.info("JSON: {}",json);
            kafkaTemplate.send(topic, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}