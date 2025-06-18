package com.moditha.group_finder.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectFilterDTO {
    private String courseId;
    private String frontendTechnology;
    private String backendTechnology;
    private int page;
    private int size;
}
