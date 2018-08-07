package com.mgrsys.blankproject.model.repository.session

import com.mgrsys.blankproject.model.entity.SessionInfo

/**
 * Developed by Magora Team (magora-systems.com)
 * 2018
 *
 * @author Viktor Zemtsov
 */
interface SessionRepository {

  fun sessionInfo(): SessionInfo

  fun setSessionInfo(sessionInfo: SessionInfo)

  fun isAuthorized(): Boolean
}