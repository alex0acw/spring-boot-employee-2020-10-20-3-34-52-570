package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoEmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> getAllByGender(String gender);
}
