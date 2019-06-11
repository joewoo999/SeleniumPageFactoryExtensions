package com.github.pfextentions.common.config;

import java.util.List;

public class ConfigTest {
    @ConfigKey("string")
    public String _string;

    @ConfigKey("boolean")
    public boolean _boolean;

    @ConfigKey("int")
    public int _int;

    @ConfigKey("float")
    public float _float;

    @ConfigKey("double")
    public double _double;

    @ConfigKey("long")
    public long _long;

    @ConfigKey("empty")
    public String empty;

    @ConfigKey("nullValue")
    public String nullValue;

    public String ignore;

}



