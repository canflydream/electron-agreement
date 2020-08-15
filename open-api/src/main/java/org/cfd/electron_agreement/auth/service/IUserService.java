package org.cfd.electron_agreement.auth.service;

import org.cfd.electron_agreement.beans.po.User;
import org.cfd.electron_agreement.beans.vo.ApplyVO;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {
    User findUserByLoginId(String loginId);

    User insert(ApplyVO applyVO);

    User findUserFromRequest(HttpServletRequest request);
}
