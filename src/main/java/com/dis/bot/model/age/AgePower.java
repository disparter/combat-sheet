package com.dis.bot.model.age;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("age_powers")
@Data
public class AgePower {

    @Id
    private String id;
    private String characterId;
    private String name;
    private Long level;
    private String description;
}
