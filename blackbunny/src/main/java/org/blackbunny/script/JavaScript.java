package org.blackbunny.script;

import org.mozilla.javascript.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/17/12
 */
public class JavaScript {

    public Object functionCall( Reader reader, Map< String, Object > properties, String function,  Object[] arguments ) throws IOException
    {

        Context cx = Context.enter();

        cx.setClassShutter( new ClassShutter() {
            public boolean visibleToScripts(String s) {
                return true;
            }
        } );

        try {
            Scriptable scope = new ImporterTopLevel(cx);
            Script script = cx.compileReader( reader, "", 1, null);


            for( Map.Entry< String, Object > entry : properties.entrySet() ) {
                ScriptableObject.putProperty(scope, entry.getKey(), entry.getValue());
            }

            script.exec( cx, scope );
            Function fct = (Function)scope.get( function, scope );
            return fct.call( cx, scope, scope, arguments );

        } finally {
            Context.exit();
        }

    }
}
