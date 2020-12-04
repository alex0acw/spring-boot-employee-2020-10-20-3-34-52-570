package com.thoughtworks.springbootemployee.repositories;

import com.thoughtworks.springbootemployee.entities.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {

}
