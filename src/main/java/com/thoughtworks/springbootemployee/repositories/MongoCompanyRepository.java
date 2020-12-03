package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoCompanyRepository extends MongoRepository<Company, String> {

}
