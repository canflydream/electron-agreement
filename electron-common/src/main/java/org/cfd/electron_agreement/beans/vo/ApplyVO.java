package org.cfd.electron_agreement.beans.vo;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplyVO {
    private String userName;
    private String company;
    private String module;
}
