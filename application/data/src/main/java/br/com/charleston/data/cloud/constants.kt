package br.com.charleston.data.cloud

const val USER_AGENT_INTERCEPTOR = "USER_AGENT_INTERCEPTOR"
const val RESPONSE_INTERCEPTOR = "RESPONSE_INTERCEPTOR"
const val URL_DOMAIN = "URL_DOMAIN"

const val QUERY_REPO_NAME = "name"

/**
 * Can be one of all, public, or private. Default: all
 */
const val QUERY_REPO_VISIBILITY = "visibility"

/**
 * Can be one of all, public, private, forks, sources, member. Default: all
 */
const val QUERY_REPO_TYPE = "type"