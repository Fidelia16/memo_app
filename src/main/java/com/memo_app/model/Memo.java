package com.memo_app.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "memo_key", nullable = false)
    private String key;
    @Column(name = "memo_value", nullable = false)
    private String value;
}
