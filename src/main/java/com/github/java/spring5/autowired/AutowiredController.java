package com.github.java.spring5.autowired;

import com.github.java.spring5.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AutowiredController {
    @MyAutowired
    private AutowiredTest autowiredTest;
}
