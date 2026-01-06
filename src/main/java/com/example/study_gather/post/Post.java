package com.example.study_gather.post;

import com.example.study_gather.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@Getter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long categoryId;

    private Long locationId;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String content;

    private Integer maxNumber;

    private Integer minNumber;

    private Boolean isOnline;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    public Post(Long categoryId, Long locationId, String title, Integer maxNumber, Integer minNumber, Boolean isOnline,
                String content, LocalDate startDate, LocalDate endDate) {
        this.categoryId = categoryId;
        this.locationId = locationId;
        this.title = title;
        this.isOnline = isOnline;
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
        this.content = content;
        this.startDate = startDate;
        this.isActive = true;
        this.endDate = endDate;
    }
}
