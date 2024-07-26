package com.MARS.QuizApplication.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse <T>{
    private String message;
    private T data;
    private int status;
}
