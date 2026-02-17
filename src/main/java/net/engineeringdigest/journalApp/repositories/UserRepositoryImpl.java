package net.engineeringdigest.journalApp.repositories;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA () {

        Query query = new Query();

//        query.addCriteria(Criteria.where("userName").is("Papun"));
//        query.addCriteria(Criteria.where("field").ne("value"));  //(ne = not equal, gte= greater than, lte=less than)
         query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
         query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

         List<User> users = mongoTemplate.find(query, User.class);
         return users;
    }

}
