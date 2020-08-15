package org.cfd.electron_agreement.admin.service;

import org.cfd.electron_agreement.beans.po.User;

public interface ISealService {
    void upload(User user, String destFileName);
}
