package com.anhttvn.textinputlibary.model;

import com.squareup.otto.Bus;

public class Eventbus {
    private static Bus sBus;

    public static Bus getBus() {
        if (sBus == null)
            sBus = new Bus();
        return sBus;
    }
}
