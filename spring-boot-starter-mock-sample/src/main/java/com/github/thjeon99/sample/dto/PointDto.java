package com.github.thjeon99.sample.dto;

import com.github.thjeon99.sample.domain.Point;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by thjeon99@gmail.com on 2020. 10. 16.
 * 
 * Github : https://github.com/thjeon99
 */

@Getter
@Setter
@NoArgsConstructor
public class PointDto {

    private Long userId;
    private Long savePoint;
    private String description;

    @Builder
    public PointDto(Long userId, Long savePoint, String description) {
        this.userId = userId;
        this.savePoint = savePoint;
        this.description = description;
    }

    public Point toEntity() {
        return Point.builder()
                .userId(userId)
                .point(savePoint)
                .description(description)
                .build();
    }

}
