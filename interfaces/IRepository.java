package com.nescude.spoofy.interfaces;

import org.springframework.stereotype.Repository;

@Repository
public interface IRepository<T> {

    public boolean save(T t);
    public boolean delete(int id);
    public T find(int id);
    public Iterable<T> findAll();

}
