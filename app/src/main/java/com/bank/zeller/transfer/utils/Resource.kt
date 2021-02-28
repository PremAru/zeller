package com.bank.zeller.transfer.utils

data class Resource<out T>(val status: Status, val data: T?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> failure(data: T?): Resource<T> {
            return Resource(Status.FAILURE, data)
        }

        fun <T> error(data: T?): Resource<T> {
            return Resource(Status.ERROR, data)
        }

    }
}