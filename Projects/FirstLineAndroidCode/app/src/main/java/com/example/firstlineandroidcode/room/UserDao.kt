package com.example.firstlineandroidcode.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * data access objects
 * Dao是数据访问对象的意思，
 * 通常会在这里对数据库的各项操作进行封装，
 * 在实际编程的时候，逻辑层就不需要和底层数据库打交道了，直接和Dao层进行交互即可。
 * */
@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers(): List<User>

    @Query("select * from User where age > :age")
    fun loadUsersOlderThan(age: Int): List<User>

    @Delete
    fun deleteUser(user: User)

    /**
     * 如果是使用非实体类参数来增删改数据，要编写SQL语句才行，
     * 而且这个时候不能使用@Insert、@Delete或@Update注解，
     * 都要使用@Query注解才行，参考deleteUserByLastName()方法的写法
     * */
    @Query("delete from User where lastName = :lastName")
    fun deleteUserByLastName(lastName: String): Int

}