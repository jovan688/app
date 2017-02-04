package yio.io.sifaapp.utils;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by JUANCARLOS on 27/07/2016.
 */

@Database(name = SifacDataBase.NAME, version = SifacDataBase.VERSION , foreignKeysSupported = true)
public class SifacDataBase{
        public static final String NAME = "sifacdb";
        public static final int VERSION = 9;
}


