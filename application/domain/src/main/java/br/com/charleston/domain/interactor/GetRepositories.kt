package br.com.charleston.domain.interactor

import br.com.charleston.domain.UseCase
import br.com.charleston.domain.executor.PostExecutionThread
import br.com.charleston.domain.executor.ThreadExecutor
import br.com.charleston.domain.model.GithubModel
import br.com.charleston.domain.repository.IGithubRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRepositories @Inject constructor(
    private val repository: IGithubRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<List<GithubModel>, String>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Observable<List<GithubModel>> {
        return if (params != null) {
            repository.getRepositories(params)
        } else {
            Observable.error(Throwable("No match"))
        }
    }
}