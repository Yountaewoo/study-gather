package com.example.study_gather.post;

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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long boardId;

    @Column(nullable = false)
    private Long categoryId;

    private Long locationId;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String content;

    private int maximumNumber;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    public Post(Long boardId, Long categoryId, Long locationId, String title, int maximumNumber,
                String content, LocalDate startDate, LocalDate endDate) {
        this.boardId = boardId;
        this.categoryId = categoryId;
        this.locationId = locationId;
        this.title = title;
        this.maximumNumber = maximumNumber;
        this.content = content;
        this.startDate = startDate;
        this.isActive = true;
        this.endDate = endDate;
    }
}
