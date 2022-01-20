package com.gabriel.util;

import java.util.List;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
public interface DaoService<T> {
    public List<T> showData();
    public int addData(T data);
    public int updateData (T data);
}
