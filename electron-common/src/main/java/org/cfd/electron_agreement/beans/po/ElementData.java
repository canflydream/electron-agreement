package org.cfd.electron_agreement.beans.po;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ElementData {
    private String userId;
    private String data;
    private Date createTime;
}
