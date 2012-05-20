package org.blackbunny.data;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
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
        InputStream in = null;

        try {
            in = new FileInputStream( resource );
        } catch ( FileNotFoundException e ) {
            logger.warn( "FileNotFound : file:{}", resource );
        }

        if( in == null ) {
            URL url = MybatisHelper.class.getClassLoader().getResource( resource );

            if( url == null ) {
                logger.error( "FileNotFound : classpath:{}", resource );
                return;
            }

            logger.info("Loaded : classpth:{}", resource );

            try {
                in = url.openStream();
            } catch ( Exception e ) {
                logger.error( "", e );
            }
        }

        if (sqlSessionFactory == null) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build( in );

            Reflections reflections = new Reflections( scanPackages );

            Set<Class<?>> annotated =
                    reflections.getTypesAnnotatedWith( DBMapper.class );

            for( Class cls : annotated ) {
                logger.info( "registered mapper : {}", cls.getName() );
                sqlSessionFactory.getConfiguration().addMapper( (Class<?>)cls );
            }
        }



    }

    public SqlSessionFactory getSqlSessionFactory() {

        return sqlSessionFactory;
    }
}
