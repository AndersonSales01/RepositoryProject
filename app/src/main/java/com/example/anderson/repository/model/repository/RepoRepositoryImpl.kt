package com.example.anderson.repository.model.repository

import com.example.anderson.repository.model.api.RepositoryApi
import com.example.anderson.repository.model.api.endpoint.RepositoryEndPoint
import com.example.anderson.repository.model.entity.Author
import com.example.anderson.repository.model.entity.Repository
import com.example.anderson.repository.model.entityrequest.ResponseDTO
import com.example.anderson.repository.model.repository.interfaces.IRepoRepository
import org.koin.core.KoinComponent
import retrofit2.Response
import rx.Observable

/**
 * Created by Anderson on 16/12/2018.
 */
class RepoRepositoryImpl : IRepoRepository {

    //  lateinit var repositoryEndPoint: RepositoryEndPoint


//   suspend fun loadRepository(page:Int) = RepositoryApi.getInstance()
//
//            .create(RepositoryEndPoint::class.java)
//            .listRepository(page)
//            .flatMap { result ->
//                Observable.from(result.repositoryList)
//                        .flatMap { repositoryResult ->
//                            Observable.just(
//                                    Repository(repositoryResult.nameRepository,repositoryResult.fullName, repositoryResult.description,
//                                            repositoryResult.numberForks, repositoryResult.numberStarts, Author(repositoryResult.owner.name,repositoryResult.owner.urlAvatar)))
//                        }
//
//
//            }!!

    override suspend fun loadRepository(page: Int): List<Repository> {

        val listRepositorys = ArrayList<Repository>()

        var response = RepositoryApi.getInstance().create(RepositoryEndPoint::class.java).listRepository(page).await()


        response.let {

            if (response.repositoryList.isNotEmpty()) {

                for (repo in response!!.repositoryList) {


                    if (repo.owner != null && repo.description != null) {

                        val repository = Repository(repo.nameRepository, repo.fullName, repo.description,
                                repo.numberForks, repo.numberStarts, Author(repo.owner.name, repo.owner.urlAvatar))

                        listRepositorys.add(repository)

                    }
                }

            }
        }


        return listRepositorys
    }

}