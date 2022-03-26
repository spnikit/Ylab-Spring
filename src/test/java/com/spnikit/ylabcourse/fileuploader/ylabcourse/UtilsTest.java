package com.spnikit.ylabcourse.fileuploader.ylabcourse;

import com.spnikit.ylabcourse.fileuploader.ylabcourse.shared.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
    public void convertFromNumpadToXYCoord(){
        var result = Utils.convertNumpadValuesToXYCoordinates(1);

        Assertions.assertArrayEquals(new int[]{0, 0}, result);

        Assertions.assertThrows(IllegalArgumentException.class, () ->Utils.convertNumpadValuesToXYCoordinates(-1));
    }
}
