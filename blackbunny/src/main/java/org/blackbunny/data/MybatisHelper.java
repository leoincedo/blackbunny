package org.blackbunny.data;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/15/12
 */
public class MybatisHelper {

    public static final Logger logger = LoggerFactory.getLogger(MybatisHelper.class);

    SqlSessionFactory sqlSessionFactory = null;

    public MybatisHelper( String resource, String scanPackages )
    {
        try {

            Reader reader = Resources.getUrlAsReader( "file:" + resource );

            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build( reader );

                Reflections reflections = new Reflections( scanPackages );

                Set<Class<?>> annotated =
                        reflections.getTypesAnnotatedWith( DBMapper.class );

                for( Class cls : annotated ) {
                    logger.info( "registered mapper : {}", cls.getName() );
                    sqlSessionFactory.getConfiguration().addMapper( (Class<?>)cls );
                }
            }
        }

        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public SqlSessionFactory getSqlSessionFactory() {

        return sqlSessionFactory;
    }
}
