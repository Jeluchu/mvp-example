package com.jeluchu.githubapi.core.api

import com.jeluchu.githubapi.features.listing.models.Repository
import com.jeluchu.githubapi.features.details.models.RepositoryDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface GithubApi {

    @GET(ApiCallsConst.ORGANIZATION_REPOS)
    fun getOrganizationRepos(@Path(ApiCallsConst.PATH_ORGANIZATION) organizationName: String,
                             @Query(ApiCallsConst.PARAM_REPOS_TYPE) reposType: String): Observable<MutableList<Repository>>

    @GET(ApiCallsConst.REPOSITORY)
    fun getRepository(@Path(ApiCallsConst.PATH_OWNER) owner: String,
                      @Path(ApiCallsConst.PATH_REPO) name: String): Observable<RepositoryDetail>

}