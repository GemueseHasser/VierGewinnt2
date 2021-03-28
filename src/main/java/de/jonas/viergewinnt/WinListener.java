package de.jonas.viergewinnt;

import org.jetbrains.annotations.NotNull;

@NotNull
public interface WinListener {

    void win(@NotNull FieldState state);

}
