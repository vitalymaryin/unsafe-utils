package org.vit.unsafeutils.serializer;

import org.apache.log4j.Logger;
import org.vit.unsafeutils.api.ObjectTypesEnum;
import org.vit.unsafeutils.api.UnsafeSerializable;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/*
Copyright 2014 Vitaly Maryin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
public class UnsafeSerializer {
    private static Logger logger = Logger.getLogger(UnsafeSerializer.class);
    private static boolean isDebugEnabled = logger.isDebugEnabled();

    public static ArrayList<UnsafeSerializable> read(ByteBuffer buff, int size){
        UnsafeBuffer dataArray = new UnsafeBuffer(buff.array());
        ArrayList<UnsafeSerializable> result = new ArrayList<UnsafeSerializable>(2);
        while(dataArray.getOffset() < size){
            if (isDebugEnabled) logger.debug("reading array of size " + size + " with offset=" + dataArray.getOffset());
            long start = System.nanoTime();
            UnsafeSerializable cls = ObjectTypesEnum.getValues()[dataArray.getByte()].getName();
            UnsafeSerializable clone = cls.read(dataArray);
            if (isDebugEnabled) logger.debug("deserialized in " + (System.nanoTime() - start) + "ns.");
            result.add(clone);
        }
        buff.clear();
        return result;
    }

    public static byte[] write(UnsafeSerializable data) {
        UnsafeBuffer buff = new UnsafeBuffer(new byte[data.getDefaultSize()]);
        data.write(buff);
        return buff.toByteArray();
    }
}
