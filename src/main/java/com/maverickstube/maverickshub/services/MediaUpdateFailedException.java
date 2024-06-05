package com.maverickstube.maverickshub.services;

import com.github.fge.jsonpatch.JsonPatchException;

public class MediaUpdateFailedException extends RuntimeException {
    public MediaUpdateFailedException(String message) {
        super(message);
    }
}
