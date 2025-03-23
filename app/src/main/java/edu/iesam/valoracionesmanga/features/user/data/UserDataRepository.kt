package edu.iesam.valoracionesmanga.features.user.data

import edu.iesam.valoracionesmanga.features.user.data.remote.UserFirebaseRemoteDataSource
import edu.iesam.valoracionesmanga.features.user.domain.UserRepository
import org.koin.core.annotation.Single

@Single
class UserDataRepository(private val remoteDataSource: UserFirebaseRemoteDataSource):
    UserRepository {
    override suspend fun createUser(email: String, passwd: String) {
        remoteDataSource.createUser(email, passwd)
    }

    override suspend fun login(email: String, passwd: String) {
        remoteDataSource.login(email, passwd)
    }
}