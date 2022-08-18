package com.dis.bot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("age_equipments")
@Data
public class AgeEquipment {

    @Id
    private String id;
    private String characterId;
    private String name;
    private String description;
    private Boolean isMagic;
}
