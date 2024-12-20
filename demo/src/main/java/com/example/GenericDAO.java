package JAVA.rendu_2.demo.src.main.java.com.example;

import java.util.List;

public interface GenericDAO<T> {
    void add(T entity);
    T get(String nom);  
    List<T> getAll();
    void update(T entity);
    void delete(String nom);  
}
