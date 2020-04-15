package org.zhouhy.springboot.project1.service;

import org.zhouhy.springboot.project1.domain.Company;

import javax.transaction.Transactional;
import java.util.List;

public interface CompanyService {

    void Save(Company company);

    @Transactional
    void delete(String uuid);

    @Transactional
    void update(Company company);

    List<Company> findAll();

    List<Company> findByCompanyName(String companyName);

}
