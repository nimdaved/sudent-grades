package org.ab.student.domain;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@ApiModel
public class StudentSummary {
	private Long id;
	private String first;
	private String last;
	private String email;
	private BigDecimal gpa;

}
