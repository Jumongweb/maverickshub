package com.maverickstube.maverickshub.exceptions;

import com.github.fge.jsonpatch.JsonPatchException;

public class MediaUpdateFailedException extends RuntimeException {
    public MediaUpdateFailedException(String message) {
        super(message);
    }
}
