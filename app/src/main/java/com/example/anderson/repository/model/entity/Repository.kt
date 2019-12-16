package com.example.anderson.repository.model.entity

/**
 * Created by Anderson on 15/12/2018.
 */
class Repository(val name: String = "",
                 val fullName: String = "",
                 val description: String = "",
                 val numberForks: Int = 0,
                 val numberStarts: Int = 0,
                 val author: Author
                ) {
}