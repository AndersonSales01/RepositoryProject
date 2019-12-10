package com.example.anderson.repository.model.entity

/**
 * Created by Anderson on 15/12/2018.
 */
class Repository(val name: String,
                 val fullName: String,
                 val description: String,
                 val numberForks: Int,
                 val numberStarts: Int,
                 val author: Author
                ) {
}