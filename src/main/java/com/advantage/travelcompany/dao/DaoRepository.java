package com.advantage.travelcompany.dao;

import com.advantage.travelcompany.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface DaoRepository<T> {

    //CRUD
    int addtoDb(T t) throws SQLException;
    T getfromDb(int id);
    T updatetoDb(int id, String newEmail);
    boolean deleteFromDb(int id);
    List<T> getfromDb();
}
