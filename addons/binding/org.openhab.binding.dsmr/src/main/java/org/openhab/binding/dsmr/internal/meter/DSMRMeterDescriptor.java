/**
 * Copyright (c) 2010-2018 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.dsmr.internal.meter;

import java.util.Objects;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * The DSMRMeterDescriptor describes a meter.
 *
 * A DSMR Meter consists of the following properties:
 * - MeterType
 * - M-Bus channel
 * - Identifier
 *
 * @author M. Volaart - Initial contribution
 */
@NonNullByDefault
public class DSMRMeterDescriptor {
    /**
     * Meter type
     */
    private final DSMRMeterType meterType;

    /**
     * M-Bus channel
     */
    private final int channel;

    /**
     * Constructor for new DSMRMeterDescriptor
     *
     * @param meterType The meter type
     * @param channel The M-Bus channel this meter is connected to
     * @throws IllegalArgumentException if one of the parameters is null
     */
    public DSMRMeterDescriptor(DSMRMeterType meterType, int channel) {
        this.meterType = meterType;
        this.channel = channel;
    }

    /**
     * @return the meterType
     */
    public DSMRMeterType getMeterType() {
        return meterType;
    }

    /**
     * @return the channel
     */
    public int getChannel() {
        return channel;
    }

    /**
     * @return the id to identify of channel as String or as "default" is its the unknown channel.
     */
    public String getChannelId() {
        return channel == DSMRMeterConstants.UNKNOWN_CHANNEL ? "default" : String.valueOf(channel);
    }

    /**
     * Returns true if both DSMRMeterDescriptor are equal. I.e.:
     * - meterType is the same
     * - channel is the same
     * - identification is the same
     *
     * @param other DSMRMeterDescriptor to check
     * @return true if both objects are equal, false otherwise
     */
    @Override
    public boolean equals(@Nullable Object other) {
        if (!(other instanceof DSMRMeterDescriptor)) {
            return false;
        }
        DSMRMeterDescriptor o = (DSMRMeterDescriptor) other;

        return meterType == o.meterType && channel == o.channel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meterType, channel);
    }

    @Override
    public String toString() {
        return "[Meter type: " + meterType + ", channel: " + channel + ']';
    }
}
