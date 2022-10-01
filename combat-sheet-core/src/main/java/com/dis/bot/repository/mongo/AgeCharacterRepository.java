package com.dis.bot.repository.mongo;

import com.dis.bot.model.age.AgeCharacter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgeCharacterRepository extends MongoRepository<AgeCharacter, String> {
}
