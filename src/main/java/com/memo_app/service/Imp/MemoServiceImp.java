package com.memo_app.service.Imp;

import com.memo_app.model.Dto.MemoRequest;
import com.memo_app.model.Dto.MemoResponse;
import com.memo_app.model.Memo;
import com.memo_app.repository.MemoRepository;
import com.memo_app.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemoServiceImp implements MemoService {
    @Autowired
    private MemoRepository memoRepository;
    @Override
    public Page<MemoResponse> getMemos(Pageable pageable) {
        Page<Memo> memos = memoRepository.findAll(pageable);
        return memos.map(memo ->MemoResponse.builder()
                .key(memo.getKey())
                .value(memo.getValue())
                .build());
    }

    @Override
    public MemoResponse findById(int id) {
        Memo memo = memoRepository.findById(id).orElse(null);
        return MemoResponse.builder()
                .key(memo.getKey())
                .value(memo.getValue())
                .build();
    }

    @Override
    public MemoResponse save(MemoRequest memoRequest) {
        Memo memo = Memo.builder()
                .key(memoRequest.getKey())
                .value(memoRequest.getValue())
                .build();
        memoRepository.save(memo);
        return MemoResponse.builder()
                .key(memo.getKey())
                .value(memo.getValue())
                .build();
    }

    @Override
    public MemoResponse update(MemoRequest memoRequest, int id) {
//        Memo memo = memoRepository.findById(id).orElse(null);
//        memo.setId(id);
//        memo.setKey(memoRequest.getKey());
//        memo.setValue(memoRequest.getValue());
//        memoRepository.save(memo);
        Memo memo = Memo.builder()
                .id(id)
                .key(memoRequest.getKey())
                .value(memoRequest.getValue())
                .build();
        return MemoResponse.builder()
                .key(memo.getKey())
                .value(memo.getValue())
                .build();
    }

    @Override
    public void deleteById(int id) {
        memoRepository.deleteById(id);
    }
}
