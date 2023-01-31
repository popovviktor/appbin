package com.example.testbinlist

class MyException: Exception() {
    override fun toString(): String {
        return "Введены неккоректные данные для поиска информации"
    }
}