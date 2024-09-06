package com.example.firstlineandroidcode.room

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Entity。用于定义封装实际数据的实体类，
 * 每个实体类都会在数据库中有一张对应的表，
 * 并且表中的列是根据实体类中的字段自动生成的
 * */
@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}