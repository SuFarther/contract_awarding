package com.onecbuying.vector_arraylist;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @author 苏东坡
 * @version 1.0
 * @ClassName ThreadVector
 * @company 公司
 * @Description Vector源码分析
 * Vector源码部分:
 * 将指定元素附加到此 Vector 的末尾。 @param e 要附加到此向量的元素
 * @return {@code true}（由 {@link Collection add} 指定）@since 1.2
 * public synchronized boolean add(E e) {
 *         modCount++;
 *         ensureCapacityHelper(elementCount + 1);
 *         elementData[elementCount++] = e;
 *         return true;
 *     }
 *  ArrayList源码部分:
 *   将指定元素附加到此列表的末尾。参数：e - 要附加到此列表的元素返回：true（由 Collection.add 指定）
 *   public boolean add(E e) {
 *         ensureCapacityInternal(size + 1);  // Increments modCount!!
 *         elementData[size++] = e;
 *         return true;
 *     }
 *    没有加synchronized关键字,没有做线程同步,如果加了synchronized每次都要把这个东西锁住，等下个资源竞争
 *    才做释放锁,所以这个效率就高，在多线程中会产生数据冲突
 *
 * @createTime 2022年08月04日 08:41:41
 */
public class ThreadVector {
    public static void main(String[] args) {
        Vector<?> vector = new Vector<Object>();
//        vector.add("");
        ArrayList<?> list = new ArrayList<>();
//        list.add(1);
    }
}
