package com.wild.spring.rest.dao;

import com.wild.spring.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession();

        List<Employee> allEmployees = session.createQuery("from Employee",
                Employee.class).getResultList(); // получаем список работников

//        Query<Employee> query = session.createQuery("from Employee", Employee.class);
//        List<Employee> allEmployees = query.getResultList(); // получаем список работников


        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession(); // открываем сессию

        //session.save(employee); // сохраняем работника
        session.saveOrUpdate(employee); // сохраняем или обновляем работника
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession(); // открываем сессию
        Employee employee = session.get(Employee.class, id); // получаем работника с указ. id
        return employee; // возвращаем этого работника
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession(); // открываем сессию
        Query<Employee> query = session.createQuery("delete from Employee where id =:empId");
        query.setParameter("empId", id); // присвоить параметру employeeId значение параметра id
        query.executeUpdate();
    }
}
