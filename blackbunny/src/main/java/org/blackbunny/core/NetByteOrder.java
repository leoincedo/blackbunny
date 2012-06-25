package org.blackbunny.core;

import java.nio.ByteOrder;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 6/22/12
 * Time: 6:17 PM
 */
public class NetByteOrder {
    static ByteOrder _order = ByteOrder.LITTLE_ENDIAN;

    public static void order( ByteOrder order )
    {
        _order = order;
    }

    public static ByteOrder order()
    {
        return _order;
    }
}
