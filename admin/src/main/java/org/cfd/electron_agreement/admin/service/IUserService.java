package org.cfd.electron_agreement.admin.service;

import org.cfd.electron_agreement.beans.po.User;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {
    User findUserByLoginId(String loginId);

    User findUserFromRequest(HttpServletRequest request);
}
