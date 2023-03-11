package com.sparta.hanghaespringsecond.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {  //생성 시간

    @CreatedDate        //게시글을 쓸때 자동으로 들어간다
    private LocalDateTime createdAt;

    @LastModifiedDate   //수정이 될때 알아서 시간을 넣어준다
    private LocalDateTime modifiedAt;
}