package com.shareem.myapplication.database;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static io.realm.RealmConfiguration.Builder;

public class RealmFactory {
    public static Realm get(){
        return Realm.getInstance(createConfig());
    }

    private static RealmConfiguration createConfig(){
        return new Builder()
                .name("default2")
                .schemaVersion(3)
                .deleteRealmIfMigrationNeeded()
                .build();
    }
}
