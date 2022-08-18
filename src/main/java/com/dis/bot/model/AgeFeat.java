package com.dis.bot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("age_feats")
@Data
public class AgeFeat {

    @Id
    private String id;
    private String characterId;
    private String name;
    private FeatType type;

    public enum FeatType {
        ADVENTURER,
        CHAMPION,
        EPIC
    }

}
