package com.jely91.springMVCHibernate.dao;

import com.jely91.springMVCHibernate.entity.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    private final SessionFactory sessionFactory;
    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        List<Employee> allEmployees = session.createQuery("from Employee", Employee.class)
                .getResultList();
        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session =sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session =sessionFactory.getCurrentSession();
        Employee employee =session.get(Employee.class,id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session=sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("delete from Employee " +
                "where id=:employeeId");
       query.setParameter("employeeId",id);
       query.executeUpdate();
    }
}
