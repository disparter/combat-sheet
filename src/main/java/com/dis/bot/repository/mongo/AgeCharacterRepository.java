package com.dis.bot.repository.mongo;

import com.dis.bot.model.age.AgeCharacter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeCharacterRepository extends MongoRepository<AgeCharacter, String> {
}
