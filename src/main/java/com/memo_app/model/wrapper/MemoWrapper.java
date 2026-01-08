package com.memo_app.model.wrapper;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MemoWrapper<T> {
    private int code;
    private String message;
    private T data;
}
