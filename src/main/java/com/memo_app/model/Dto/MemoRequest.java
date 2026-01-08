package com.memo_app.model.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MemoRequest {
    private String key;
    private String value;
}
