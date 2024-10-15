package com.training.SpringBootRESTRepo.actuator;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Endpoint(id = "custom", enableByDefault = false)
public class MyEndpointActuator {

    List<Record> records = new ArrayList<>();

    public MyEndpointActuator() {
        records.add(new Record(1, "msg1"));
        records.add(new Record(2, "msg2"));
    }

    @ReadOperation
    public Record getRecord(@Selector Integer idx){
        return records.get(idx);
    }

    @WriteOperation
    public Record addRecords(String message ){
        Record record = new Record(records.size(), message);
        records.add(record);
        return  record;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Record{

    private  int id;
    private String message;
}

