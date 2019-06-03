/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.util;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class with helper test utils.
 *
 * @author Michał Gogolewski
 */
public class TestHelperUtils {
    private static final int LEFT_CHAR_LIMIT = 97; // letter 'a'
    private static final int RIGHT_CHAR_LIMIT = 122; // letter 'z'

    public static String buildStringWithSize(final int size) {
        Random random = new Random();


        return IntStream.range(0, size)
                .map(i -> LEFT_CHAR_LIMIT + (int) (random.nextFloat() * (RIGHT_CHAR_LIMIT - LEFT_CHAR_LIMIT + 1)))
                .mapToObj(randomLimitedInt -> String.valueOf((char) randomLimitedInt))
                .collect(Collectors.joining());
    }
}
