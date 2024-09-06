package com.example.firstlineandroidcode.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * AppDatabase类的头部使用了@Database注解，
 * 并在注解中声明了数据库的版本号以及包含哪些实体类，多个实体类之间用逗号隔开即可。
 *
 * AppDatabase类必须继承自RoomDatabase类，并且一定要使用abstract关键字将它声明成抽象类
 * */
@Database(version = 3, entities = [User::class, Book::class])
abstract class AppDatabase : RoomDatabase() {

    /**
     * 提供相应的抽象方法，用于获取Dao的实例，比如这里提供的userDao()方法。
     * 不过我们只需要进行方法声明就可以了，具体的方法实现是由Room在底层自动完成的。
     * */
    abstract fun userDao(): UserDao
    abstract fun bookDao(): BookDao

    companion object {

        // 版本1升级版本2，新增book表
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Book (id integer primary key autoincrement not null, name text not null, pages integer not null)")
            }
        }

        // book 表新增author字段
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column author text not null default 'unknown'")
            }
        }

        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "app_database")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build().apply {
                    instance = this
                }
        }
    }
}