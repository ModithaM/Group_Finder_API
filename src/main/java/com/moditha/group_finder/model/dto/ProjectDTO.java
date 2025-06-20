package com.moditha.group_finder.model.dto;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Important: call parent's equals/hashCode
@ToString(callSuper = true)
public class ProjectDTO extends BaseProjectDTO {
    private int creatorId;
    private List<ProjectMemberDTO> projectMembers;
}
