package com.onefool.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author linjiawei
 * @Date 2023/12/26 2:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {

    private String account;

    private String password;
}
