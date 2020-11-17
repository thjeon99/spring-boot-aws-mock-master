package com.github.thjeon99.sample.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by thjeon99@gmail.com on 2020. 10. 15.
 * 
 * Github : https://github.com/thjeon99
 */

@Getter
@NoArgsConstructor
@Entity
public class Point {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private Long point;
    private String description;

    @Builder
    public Point(Long userId, Long point, String description) {
        this.userId = userId;
        this.point = point;
        this.description = description;
    }
}
