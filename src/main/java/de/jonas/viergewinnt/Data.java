package de.jonas.viergewinnt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Range;

public final class Data {

    public static final CircleLocation[] CIRCLE_LOCATIONS = new CircleLocation[] {
        // line 1
        new CircleLocation(
            15,
            15
        ),
        new CircleLocation(
            115,
            15
        ),
        new CircleLocation(
            215,
            15
        ),
        new CircleLocation(
            315,
            15
        ),
        new CircleLocation(
            415,
            15
        ),
        new CircleLocation(
            515,
            15
        ),
        new CircleLocation(
            615,
            15
        ),
        // line 2
        new CircleLocation(
            15,
            115
        ),
        new CircleLocation(
            115,
            115
        ),
        new CircleLocation(
            215,
            115
        ),
        new CircleLocation(
            315,
            115
        ),
        new CircleLocation(
            415,
            115
        ),
        new CircleLocation(
            515,
            115
        ),
        new CircleLocation(
            615,
            115
        ),
        // line 3
        new CircleLocation(
            15,
            215
        ),
        new CircleLocation(
            115,
            215
        ),
        new CircleLocation(
            215,
            215
        ),
        new CircleLocation(
            315,
            215
        ),
        new CircleLocation(
            415,
            215
        ),
        new CircleLocation(
            515,
            215
        ),
        new CircleLocation(
            615,
            215
        ),
        // line 4
        new CircleLocation(
            15,
            315
        ),
        new CircleLocation(
            115,
            315
        ),
        new CircleLocation(
            215,
            315
        ),
        new CircleLocation(
            315,
            315
        ),
        new CircleLocation(
            415,
            315
        ),
        new CircleLocation(
            515,
            315
        ),
        new CircleLocation(
            615,
            315
        ),
    };

    public static final WinPossibility[] WIN_POSSIBILITIES = new WinPossibility[] {
        // horizontal
        new WinPossibility(
            new int[] {0, 1, 2, 3}
        ),
        new WinPossibility(
            new int[] {1, 2, 3, 4}
        ),
        new WinPossibility(
            new int[] {2, 3, 4, 5}
        ),
        new WinPossibility(
            new int[] {3, 4, 5, 6}
        ),
        new WinPossibility(
            new int[] {7, 8, 9, 10}
        ),
        new WinPossibility(
            new int[] {8, 9, 10, 11}
        ),
        new WinPossibility(
            new int[] {9, 10, 11, 12}
        ),
        new WinPossibility(
            new int[] {10, 11, 12, 13}
        ),
        new WinPossibility(
            new int[] {14, 15, 16, 17}
        ),
        new WinPossibility(
            new int[] {15, 16, 17, 18}
        ),
        new WinPossibility(
            new int[] {16, 17, 18, 19}
        ),
        new WinPossibility(
            new int[] {17, 18, 19, 20}
        ),
        new WinPossibility(
            new int[] {21, 22, 23, 24}
        ),
        new WinPossibility(
            new int[] {22, 23, 24, 25}
        ),
        new WinPossibility(
            new int[] {23, 24, 25, 26}
        ),
        new WinPossibility(
            new int[] {24, 25, 26, 27}
        ),
        // vertical
        new WinPossibility(
            new int[] {0, 7, 14, 21}
        ),
        new WinPossibility(
            new int[] {1, 8, 15, 22}
        ),
        new WinPossibility(
            new int[] {2, 9, 16, 23}
        ),
        new WinPossibility(
            new int[] {3, 10, 17, 24}
        ),
        new WinPossibility(
            new int[] {4, 11, 18, 25}
        ),
        new WinPossibility(
            new int[] {5, 12, 19, 26}
        ),
        new WinPossibility(
            new int[] {6, 13, 20, 27}
        ),
        // diagonal left -> right
        new WinPossibility(
            new int[] {21, 15, 9, 3}
        ),
        new WinPossibility(
            new int[] {22, 16, 10, 4}
        ),
        new WinPossibility(
            new int[] {23, 17, 11, 5}
        ),
        new WinPossibility(
            new int[] {24, 18, 12, 6}
        ),
        // diagonal right -> left
        new WinPossibility(
            new int[] {24, 16, 8, 0}
        ),
        new WinPossibility(
            new int[] {25, 17, 9, 1}
        ),
        new WinPossibility(
            new int[] {26, 18, 10, 2}
        ),
        new WinPossibility(
            new int[] {27, 19, 11, 3}
        ),
    };

    public static final ButtonData[] BUTTON_DATA = new ButtonData[] {
        new ButtonData(
            0,
            120
        ),
        new ButtonData(
            120,
            100
        ),
        new ButtonData(
            220,
            100
        ),
        new ButtonData(
            320,
            100
        ),
        new ButtonData(
            420,
            100
        ),
        new ButtonData(
            520,
            100
        ),
        new ButtonData(
            620,
            115
        ),
    };

    public static final Column[] COLUMNS = new Column[] {
        new Column(
            new int[] {21, 14, 7, 0}
        ),
        new Column(
            new int[] {22, 15, 8, 1}
        ),
        new Column(
            new int[] {23, 16, 9, 2}
        ),
        new Column(
            new int[] {24, 17, 10, 3}
        ),
        new Column(
            new int[] {25, 18, 11, 4}
        ),
        new Column(
            new int[] {26, 19, 12, 5}
        ),
        new Column(
            new int[] {27, 20, 13, 6}
        ),
    };

    @RequiredArgsConstructor
    public static final class WinPossibility {
        @Getter
        private final int[] chance;
    }

    @RequiredArgsConstructor
    public static final class Column {
        @Getter
        private final int[] column;
    }

    public static final class CircleLocation {

        private static final int SIZE = 75;

        @Getter
        private final int size;
        @Getter
        private final int x;
        @Getter
        private final int y;
        @Getter
        @Setter
        private FieldState state;

        public CircleLocation(
            @Range(from = 0, to = Integer.MAX_VALUE) final int x,
            @Range(from = 0, to = Integer.MAX_VALUE) final int y
        ) {
            this.size = SIZE;
            this.x = Draw.MARGIN_LEFT_AND_RIGHT + x;
            this.y = Draw.MARGIN_TOP + y;
            this.state = FieldState.NONE;
        }
    }

    public static final class ButtonData {

        private static final int Y = 0;
        private static final int HEIGHT = GUI.HEIGHT;

        @Getter
        private final int x;
        @Getter
        private final int y;
        @Getter
        private final int width;
        @Getter
        private final int height;

        public ButtonData(
            @Range(from = 0, to = Integer.MAX_VALUE) final int x,
            @Range(from = 0, to = Integer.MAX_VALUE) final int width
        ) {
            this.x = x;
            this.y = Y;
            this.width = width;
            this.height = HEIGHT;
        }
    }

}
