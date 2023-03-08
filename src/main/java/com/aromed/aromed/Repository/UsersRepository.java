package com.aromed.aromed.Repository;

import com.aromed.aromed.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.expression.spel.ast.Projection;

import java.util.List;

public interface UsersRepository extends MongoRepository<Users, String> {
    public Users findByUserId(String userId);
    public Users findByUserName(String username);
    public void deleteByUserId(String userId);
    boolean existsByUserName(String username);
    public List<Users> findAllByUserType(String userType);


}



