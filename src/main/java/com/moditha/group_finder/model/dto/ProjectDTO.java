package com.moditha.group_finder.model.dto;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProjectDTO extends BaseProjectDTO {
    private List<ProjectMemberDTO> projectMembers;
}
