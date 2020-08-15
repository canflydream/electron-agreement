package org.cfd.electron_agreement.beans.po;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String id;
    private String userName;
    private String privateKey;
    private String loginId;
    private String company;
    private String module;
    private Date createTime;
}
