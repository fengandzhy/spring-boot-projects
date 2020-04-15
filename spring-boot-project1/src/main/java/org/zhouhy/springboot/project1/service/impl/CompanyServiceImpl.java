package org.zhouhy.springboot.project1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhouhy.springboot.project1.domain.Company;
import org.zhouhy.springboot.project1.repository.CompanyRepository;
import org.zhouhy.springboot.project1.service.CompanyService;

import javax.transaction.Transactional;
import java.util.List;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void Save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void delete(String uuid) {
        companyRepository.deleteById(uuid);
    }

    @Override
    @Transactional
    public void update(Company company) {
        companyRepository.save(company);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public List<Company> findByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }
}
