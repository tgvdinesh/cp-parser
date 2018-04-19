package com.parser.cp;

import com.parser.cp.exception.*;
import com.parser.cp.model.payload.Task;

public interface DomParser {
    Task parse(String domAsString) throws NotSupportedException;
}
