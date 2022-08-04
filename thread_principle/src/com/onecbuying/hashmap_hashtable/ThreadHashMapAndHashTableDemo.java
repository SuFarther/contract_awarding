package com.onecbuying.hashmap_hashtable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author 苏东坡
 * @version 1.0
 * @ClassName ThreadHashMapAndHashTableDemo
 * @company 公司
 * @Description HashMap和HashTable源码分析
 * HashTable源码:
 * public synchronized V put(K key, V value) {
 *         // Make sure the value is not null
 *         if (value == null) {
 *             throw new NullPointerException();
 *         }
 *
 *         // Makes sure the key is not already in the hashtable.
 *         Entry<?,?> tab[] = table;
 *         int hash = key.hashCode();
 *         int index = (hash & 0x7FFFFFFF) % tab.length;
 *         @SuppressWarnings("unchecked")
 *         Entry<K, V> entry = (Entry<K,V>)tab[index];
 *         for(; entry != null ; entry = entry.next) {
 *             if ((entry.hash == hash) && entry.key.equals(key)) {
 *                 V old = entry.value;
 *                 entry.value = value;
 *                 return old;
 *             }
 *         }
 *
 *         addEntry(hash, key, value, index);
 *         return null;
 *     }
 *  HashMap源码:
 *   public V put(K key, V value) {
 *         return putVal(hash(key), key, value, false, true);
 *     }
 * @createTime 2022年08月04日 16:47:47
 */
public class ThreadHashMapAndHashTableDemo {
    public static void main(String[] args) {
        Hashtable<Object, Object> hashtable = new Hashtable<>();
        hashtable.put("张三",41);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(1,20);
        // 将不安全的map集合转成安全集合
        Collections.synchronizedMap(hashMap);
    }
}
