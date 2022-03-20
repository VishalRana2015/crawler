package com.capillary.crawler.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Validated
@Data
public class InputDataDto {
    @NotEmpty
    @NotBlank
    @NotNull
    String url;
}
