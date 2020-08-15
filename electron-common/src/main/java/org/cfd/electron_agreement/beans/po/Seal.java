package org.cfd.electron_agreement.beans.po;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seal {
    private String id;
    private Date createTime;
    private String userId;
    private String path;
}
