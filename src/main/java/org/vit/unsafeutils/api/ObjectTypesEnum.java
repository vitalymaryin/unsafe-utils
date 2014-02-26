package org.vit.unsafeutils.api;

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
public enum ObjectTypesEnum {
    MARKET_DATA((byte) 1, new MarketData(), MarketData.class.getSimpleName());

    private final UnsafeSerializable cls;
    private final byte id;
    private final String cacheName;
    private static final ObjectTypesEnum[] values = ObjectTypesEnum.values();

    ObjectTypesEnum(byte num, UnsafeSerializable obj, String cache) {
        cls = obj;
        id = num;
        cacheName = cache;
    }

    public UnsafeSerializable getName() {
        return cls;
    }

    public byte getId() {
        return id;
    }

    public String getCacheName() {
        return cacheName;
    }

    public static ObjectTypesEnum[] getValues(){
        return values;
    }
}
