package com.yricky.dogs.pojo

import com.google.gson.annotations.Expose

/**
 * @author  Yricky
 * @date  2021/10/22 下午7:28
 */
class Dog {
    @Expose
    var name:String = ""
    @Expose
    var date:Long = 0
    @Expose
    var avatarPath:String = ""
    @Expose
    var description:String = ""
}