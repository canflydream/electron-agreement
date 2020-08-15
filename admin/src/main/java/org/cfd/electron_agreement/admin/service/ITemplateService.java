package org.cfd.electron_agreement.admin.service;

import org.cfd.electron_agreement.beans.po.User;

public interface ITemplateService {
    void uploadTl(User user, String path);
}
