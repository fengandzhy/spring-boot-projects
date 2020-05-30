package org.zhouhy.springboot.project1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zhouhy.springboot.project1.domain.Company;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface CompanyService {

    void Save(Company company);

    @Transactional
    void delete(String uuid);

    @Transactional
    void update(Company company);

    List<Company> findAll();

    List<Company> findByCompanyName(String companyName);

    //简单分页查询
    Page<Company> findAllSimplePage(Pageable pageable);

    //多条件动态查询
    Page<Company> queryDynamic(Map<String,Object> reqMap, Pageable pageable);

}
