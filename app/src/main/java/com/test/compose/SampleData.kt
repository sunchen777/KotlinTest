package com.test.compose

import kotlin.random.Random
import kotlin.random.nextInt

object SampleData {

    var conversationSample:List<Message>



    = listOf(

            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}"),
            Message("${getRandomString(10)}","${getRandomString(100)}")
                                            )

    //length用户要求产生字符串的长度
    fun getRandomString(length: Int): String? {
        val str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val sb = StringBuffer()
        for (i in 0 until length) {
            val number = Random.nextInt(62)
            sb.append(str[number])
        }
        return sb.toString()
    }
}
