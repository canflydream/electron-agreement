package org.cfd.electron_agreement.admin.service;

import org.cfd.electron_agreement.beans.po.OneLevelElement;

import java.util.List;

public interface IOneLevelService {
    List<OneLevelElement> getList(String loginId);

    void save(String loginId, String data);
}
