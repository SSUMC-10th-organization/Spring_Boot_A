package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.global.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(MissionErrorCode code) {
        super(code);
    }
}