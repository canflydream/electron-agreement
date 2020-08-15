package org.cfd.electron_agreement.beans.po;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AgreementTemplate {
    private String id;
    private String path;
    private String userId;
    private Date createTime;
}
