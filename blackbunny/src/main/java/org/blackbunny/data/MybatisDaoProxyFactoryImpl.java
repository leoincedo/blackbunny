package org.blackbunny.data;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/18/12
 */
public class MybatisDaoProxyFactoryImpl implements DaoProxyFactory {

    MybatisHelper mybatisHelper;
    SqlSessionFactory sqlSessionFactory;


    public MybatisDaoProxyFactoryImpl(String config, String scanPackages) {

        mybatisHelper = new MybatisHelper( config, scanPackages );
        sqlSessionFactory = mybatisHelper.getSqlSessionFactory();
    }

    public <T> T createDaoProxy( final Class<T> cls )
    {
        class MethodCallLogInterceptor implements MethodInterceptor {

            SqlSessionFactory sqlSessionFactory;

            MethodCallLogInterceptor( SqlSessionFactory sqlSessionFactory )
            {
                this.sqlSessionFactory = sqlSessionFactory;
            }

            public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy)
                    throws Throwable {

                SqlSession session = sqlSessionFactory.openSession();

                try {
                    T obj = session.getMapper( cls );
                    return method.invoke( obj, args );

                } finally {
                    session.close();
                }
            }

        }

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass( cls );
        enhancer.setCallback( new MethodCallLogInterceptor( sqlSessionFactory ) );
        T object = (T) enhancer.create();

        return object;

    }

    public Connection createConnection()
    {
        return sqlSessionFactory.openSession().getConnection();
    }
}
