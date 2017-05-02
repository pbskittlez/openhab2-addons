package org.openhab.binding.russound.rnet.internal;

public class MuteZoneParser extends AbstractZoneParser {

    public MuteZoneParser(int zone, int controller) {
        super(zone, controller);
    }

    @Override
    public boolean matches(Byte[] bytes) {
        return (bytes[0] == (byte) 0xF0 && bytes[12] == (byte) 0xF1 && bytes[13] == (byte) 0x40
                && bytes[1] == this.controller - 1 && bytes[5] == this.zone - 1);
    }

    @Override
    public ZoneStateUpdate process(Byte[] bytes) {
        if (matches(bytes)) {
            return null;// new ZoneAction(RNetConstants.CHANNEL_ZONEMUTE, bytes[5] == 5 ? OnOffType.ON : OnOffType.OFF);
        } else {
            return null;
        }
    }

}
