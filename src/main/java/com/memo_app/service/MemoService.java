package com.memo_app.service;

import com.memo_app.model.Dto.MemoRequest;
import com.memo_app.model.Dto.MemoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemoService {
    Page<MemoResponse> getMemos(Pageable pageable);
    MemoResponse findById(int id);
    MemoResponse save(MemoRequest memoRequest);
    MemoResponse update(MemoRequest memoRequest, int id);
    void deleteById(int id);
}
