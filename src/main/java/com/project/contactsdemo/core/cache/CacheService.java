package com.project.contactsdemo.core.cache;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheService<T> {

    private final HazelcastInstance hazelcastInstance;

    public CacheService() {
        hazelcastInstance = Hazelcast.newHazelcastInstance(); // Instantiation
    }

    public boolean saveToCache(T saveDTO, String key, String mapName) {
        try {
            IMap<String, T> map = hazelcastInstance.getMap(mapName);//map name
            map.putIfAbsent(key,saveDTO); //key parametre olacak
            return true;
        } catch (Exception e) { //buranın exception'ını handle'layacağız
            return false;
        }
    }

    public T getFromCache(String key, String mapName) {
        try {
            IMap<String, T> map = hazelcastInstance.getMap(mapName); //map adı
            return map.get(key);//liste olmasa key daha anlamlı olurdu, liste için all
        } catch (Exception e) {
            return null;
        }
    }
}
