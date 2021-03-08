package de.jonas.viergewinnt;

import lombok.Getter;
import org.jetbrains.annotations.Range;

public class CircleLocation {

    private static final int SIZE = 75;

    @Getter
    private final int size;
    @Getter
    private final int x;
    @Getter
    private final int y;
    @Getter
    private final FieldState state;

    public CircleLocation(
        @Range(from = 0, to = Integer.MAX_VALUE) final int x,
        @Range(from = 0, to = Integer.MAX_VALUE) final int y
    ) {
        this.size = SIZE;
        this.x = DrawGui.MARGIN_LEFT_AND_RIGHT + x;
        this.y = DrawGui.MARGIN_TOP + y;
        this.state = FieldState.NONE;
    }

}
