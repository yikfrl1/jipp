package com.hp.jipp.encoding;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ResolutionType extends AttributeType<Resolution> {

    static final Attribute.Encoder<Resolution> ENCODER = new Attribute.Encoder<Resolution>() {
        @Override
        Resolution readValue(DataInputStream in, Tag valueTag) throws IOException {
            expectLength(in, 9);
            return Resolution.create(in.readInt(), in.readInt(),
                    Resolution.Unit.ENCODER.getEnum(in.readByte()));
        }

        @Override
        void writeValue(DataOutputStream out, Resolution value) throws IOException {
            out.writeShort(9);
            out.writeInt(value.getCrossFeedResolution());
            out.writeInt(value.getFeedResolution());
            out.writeByte((byte)value.getUnit().getCode());
        }

        @Override
        boolean valid(Tag valueTag) {
            return Tag.Resolution.equals(valueTag);
        }
    };

    public ResolutionType(Tag tag, String name) {
        super(ENCODER, tag, name);
    }
}