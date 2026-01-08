package com.memo_app.controller;

import com.memo_app.model.Dto.MemoRequest;
import com.memo_app.model.Dto.MemoResponse;
import com.memo_app.model.wrapper.MemoWrapper;
import com.memo_app.repository.MemoRepository;
import com.memo_app.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/memo_app")
public class MemoController {
    @Autowired
    MemoService memoService;
    @Autowired
    private MemoRepository memoRepository;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "10") int size,
            @RequestParam(name = "sortBy",defaultValue = "id") String sortBy,
            @RequestParam(name = "orderBy",defaultValue = "asc") String orderBy
    ) {
        Sort sort = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Pageable pageable= PageRequest.of(page,size, sort);
        Page<MemoResponse> memoResponses= memoService.getMemos(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
                MemoWrapper.builder()
                        .code(HttpStatus.OK.value())
                        .message("success")
                        .data(memoResponses)
                        .build()
        );
    }
    @PostMapping
    public ResponseEntity<?> addMemo(@RequestBody MemoRequest memoRequest){
        MemoResponse memoResponse= memoService.save(memoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                MemoWrapper.builder()
                        .code(HttpStatus.CREATED.value())
                        .message("Created")
                        .data(memoResponse)
                        .build()
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMemo(@RequestBody MemoRequest memoRequest, @PathVariable int id){
        MemoResponse memoResponse= memoService.update(memoRequest,id);
        return ResponseEntity.status(HttpStatus.OK).body(
                MemoWrapper.builder()
                        .code(HttpStatus.OK.value())
                        .message("Update Success")
                        .data(memoResponse)
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable int id){
        memoService.deleteById(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        MemoResponse memoResponse = memoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                MemoWrapper.builder()
                        .code(HttpStatus.OK.value())
                        .message("Find Memo")
                        .data(memoResponse)
                        .build()
        );
    }
}
