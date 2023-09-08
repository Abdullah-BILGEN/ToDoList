package com.AbdullahBILGEN.business.dto;

import com.AbdullahBILGEN.annotation.UniqueCategoryName;
import com.AbdullahBILGEN.auditing.AuditingAwareBaseDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
// Validation

// ToDoDto(1) - BlogDto(N)
public class ToDoDto extends AuditingAwareBaseDto implements Serializable {

    // serileştirme
    public static final Long serialVersionUID=1L;

    // TODOs Content
    @UniqueCategoryName
    @NotEmpty(message = "{blog.category.validation.constraints.NotNull.message}")
    @Size(min=2,message = "{blog.category.least.validation.constraints.NotNull.message}")
    private String todoContent;

    // todoComplete as an enum
    private TodoStatus todoComplete;

    public enum TodoStatus {
        INCOMPLETE(1),
        COMPLETE(2);

        private final int value;

        TodoStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
