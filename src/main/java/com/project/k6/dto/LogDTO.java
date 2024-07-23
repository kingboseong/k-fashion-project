package com.project.k6.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {
	 private String memberId;
	 private boolean evaluation;
     private Long inputproduct;
     private Set<Long> resultproduct;
}
