package org.blackbunny.data;

import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/18/12
 */
public interface DaoProxyFactory {
    public <T> T createDaoProxy( final Class<T> cls );
    public Connection createConnection();
}
