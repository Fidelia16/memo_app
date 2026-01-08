package com.memo_app.model.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MemoResponse {
    private String key;
    private String value;
}
