/**
 * Copyright (c) 2010-2018 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.tplinksmarthome.internal.model;

import org.eclipse.smarthome.core.library.types.OnOffType;

import com.google.gson.annotations.SerializedName;

/**
 * Data class for reading TP-Link Smart Home device state.
 * Only getter methods as the values are set by gson based on the retrieved json.
 *
 * @author Hilbrand Bouwkamp - Initial contribution
 */
public class Sysinfo extends ErrorResponse {

    public static class CtrlProtocols {
        private String name;
        private String version;

        public String getName() {
            return name;
        }

        public String getVersion() {
            return version;
        }

        @Override
        public String toString() {
            return "name:" + name + ", version:" + version;
        }
    }

    /**
     * With default light state state. The default light state is set when the device is off. If the device is on the
     * state is in the parent fields.
     */
    public static class WithDefaultLightState extends LightState {
        private LightState dftOnState;

        public LightState getLightState() {
            if (dftOnState == null) {
                return this;
            } else {
                dftOnState.setOnOff(getOnOff());
                return dftOnState;
            }
        }

        @Override
        public String toString() {
            return super.toString() + ", dftOnState:{" + dftOnState + "}";
        }
    }

    /**
     * Status of the plug as set in the range extender products.
     */
    public static class Plug {
        private String feature;
        private String relayStatus;

        public String getFeature() {
            return feature;
        }

        public OnOffType getRelayStatus() {
            return "ON".equals(relayStatus) ? OnOffType.ON : OnOffType.OFF;
        }
    }

    /**
     * Status of the range extended Wi-Fi.
     */
    public static class RangeextenderWireless {
        private int w2gRssi;

        public int getW2gRssi() {
            return w2gRssi;
        }
    }

    private String swVer;
    private String hwVer;
    private String model;
    @SerializedName("deviceId")
    private String deviceId;
    @SerializedName("hwId")
    private String hwId;
    @SerializedName("oemId")
    private String oemId;
    private String alias;
    private String activeMode;
    private int rssi;
    @SerializedName(value = "type", alternate = "mic_type")
    private String type;
    @SerializedName(value = "mac", alternate = { "mic_mac", "ethernet_mac" })
    private String mac;

    // switch and plug specific system info
    @SerializedName("fwId")
    private String fwId;
    private String devName;
    private String iconHash;
    private int relayState; // 0 is off, 1 is on
    private long onTime;
    private String feature; // HS100 -> TIM, HS110 -> TIM:ENE
    // Disabled updating as it's a different type for different devices.
    // private int updating;
    private int ledOff;
    private double latitude;
    private double longitude;

    // dimmer specific system info
    private int brightness;

    // bulb specific system info
    private boolean isFactory;
    private String discoVer;
    private CtrlProtocols ctrlProtocols;
    private WithDefaultLightState lightState = new WithDefaultLightState();

    // range extender specific system info
    private String ledStatus;
    private Plug plug = new Plug();
    private Sysinfo system;
    @SerializedName("rangeextender.wireless")
    private RangeextenderWireless reWireless;

    public String getSwVer() {
        return swVer;
    }

    public String getHwVer() {
        return hwVer;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public String getMac() {
        return mac;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getHwId() {
        return hwId;
    }

    public String getFwId() {
        return fwId;
    }

    public String getOemId() {
        return oemId;
    }

    public String getAlias() {
        return alias;
    }

    public String getDevName() {
        return devName;
    }

    public String getIconHash() {
        return iconHash;
    }

    public OnOffType getRelayState() {
        return relayState == 1 ? OnOffType.ON : OnOffType.OFF;
    }

    public int getBrightness() {
        return brightness;
    }

    public long getOnTime() {
        return onTime;
    }

    public String getActiveMode() {
        return activeMode;
    }

    public String getFeature() {
        return feature;
    }

    public int getRssi() {
        // for range extender use the 2g rssi.
        return reWireless == null ? rssi : reWireless.getW2gRssi();
    }

    public OnOffType getLedOff() {
        return ledOff == 1 ? OnOffType.OFF : OnOffType.ON;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isFactory() {
        return isFactory;
    }

    public String getDiscoVer() {
        return discoVer;
    }

    public String getProtocolName() {
        return ctrlProtocols == null ? null : ctrlProtocols.getName();
    }

    public String getProtocolVersion() {
        return ctrlProtocols == null ? null : ctrlProtocols.getVersion();
    }

    public LightState getLightState() {
        return lightState.getLightState();
    }

    public OnOffType getLedStatus() {
        return "ON".equals(ledStatus) ? OnOffType.OFF : OnOffType.ON;
    }

    public Plug getPlug() {
        return plug;
    }

    public Sysinfo getSystem() {
        return system;
    }

    /**
     * Returns the {@link Sysinfo} object independent of the device. The range extender devices have the system
     * information in another place as the other devices. This method returns the object independent of how the device
     * returns it.
     *
     * @return device independent {@link Sysinfo} object.
     */
    public Sysinfo getActualSysinfo() {
        return system == null ? this : system;
    }

    public RangeextenderWireless getReWireless() {
        return reWireless;
    }

    @Override
    public String toString() {
        return "Sysinfo [swVer=" + swVer + ", hwVer=" + hwVer + ", model=" + model + ", deviceId=" + deviceId
                + ", hwId=" + hwId + ", oemId=" + oemId + ", alias=" + alias + ", activeMode=" + activeMode + ", rssi="
                + rssi + ", type=" + type + ", mac=" + mac + ", fwId=" + fwId + ", devName=" + devName + ", iconHash="
                + iconHash + ", relayState=" + relayState + ", brightness=" + brightness + ", onTime=" + onTime
                + ", feature=" + feature + ", ledOff=" + ledOff + ", latitude=" + latitude + ", longitude=" + longitude
                + ", isFactory=" + isFactory + ", discoVer=" + discoVer + ", ctrlProtocols=" + ctrlProtocols
                + ", lightState=" + lightState + ", ledStatus=" + ledStatus + ", plug=" + plug + ", system=" + system
                + ", reWireless=" + reWireless + "]";
    }
}