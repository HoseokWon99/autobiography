package com.autobiography.users.utils.cache;

public interface KeyValueRepository<K, V> {
    void setData(K key, V value, long timeout); // millisecond
    V getData(K key);
    void deleteData(K key);

    boolean hasKey(K key);
}
