package br.com.charleston.data.cloud.responses.mapper

import br.com.charleston.data.cloud.responses.GithubResponse
import br.com.charleston.data.cloud.responses.UserResponse
import br.com.charleston.data.repository.Mapper
import br.com.charleston.domain.model.GithubModel
import br.com.charleston.domain.model.UserModel
import java.lang.ref.WeakReference

class GithubCloudMapper : Mapper<GithubResponse, GithubModel> {

    override fun transform(entity: GithubResponse): GithubModel {
        val userMapper: WeakReference<UserMapper> = WeakReference(UserMapper())

        return GithubModel(
            name = entity.name,
            fullName = entity.fullName,
            tags = entity.tags,
            user = userMapper.get()!!.transform(entity.owner)
        )
    }

    private inner class UserMapper : Mapper<UserResponse, UserModel> {
        override fun transform(entity: UserResponse): UserModel {
            return UserModel(
                entity.name,
                entity.urlAvatar
            )
        }
    }
}