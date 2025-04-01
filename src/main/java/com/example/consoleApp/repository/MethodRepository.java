package com.example.consoleApp.repository;
import java.util.List;

public interface MethodRepository<K, V> {
    List<K> getAll();
    void save(K entity);
    void update(K entity);
}
