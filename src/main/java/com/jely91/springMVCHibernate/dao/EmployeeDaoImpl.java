package com.jely91.springMVCHibernate.dao;

import com.jely91.springMVCHibernate.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class EmployeeDaoImpl implements EmployeeDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> allEmployees = entityManager.createQuery("from Employee", Employee.class)
                .getResultList();
        return allEmployees;
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        entityManager.merge(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        TypedQuery<Employee> typedQuery = entityManager.createQuery("from Employee " +
                "where id =:id",Employee.class);
        typedQuery.setParameter("id",id);
        return typedQuery.getResultStream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        Employee employee =entityManager.find(Employee.class,id);
        entityManager.remove(employee);
    }
}
