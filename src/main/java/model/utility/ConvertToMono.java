package model.utility;
/*Copyright 2009 The Open University
http://www.open.ac.uk/lts/projects/audioapplets/

This file is part of the "Open University audio applets" project.

The "Open University audio applets" project is free software: you can
redistribute it and/or modify it under the terms of the GNU General Public
License as published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

The "Open University audio applets" project is distributed in the hope that it
will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with the "Open University audio applets" project.
If not, see <http://www.gnu.org/licenses/>.
 */

public final class ConvertToMono {
    private static final int OFFSET = 0xff;

    private ConvertToMono() {
    }

    /**
     * Converts (resamples and mono to/from stereo) audio data.
     * 
     * @param data         Input data
     * @param length       Amount of input buffer that is actually used
     * @return Converted audio data
     */
    public static byte[] convert(final byte[] data, final int length) {

        short[] converted = byteToShort(data, length);
        converted = trimArray(converted, converted.length);
        return shortToByte(converted, converted.length);
    }

    /**
     * @param data   Data
     * @param length Length of valid data
     * @return Array trimmed to length (or same array if it already is)
     */
    public static short[] trimArray(final short[] data, final int length) {
        if (data.length == length) {
            return data;
        } else {
            final short[] output = new short[length];
            System.arraycopy(output, 0, data, 0, length);
            return output;
        }
    }

    /**
     * Converts audio data in 'byte' format (little-endian 16-bit) to short array.
     * 
     * @param data         Data
     * @param length       Number of bytes to actually use
     * @return Short version of data
     */
    public static short[] byteToShort(final byte[] data, final int length) {
        short[] shortData = new short[length / 4];
        for (int i = 0; i < shortData.length; i++) {
            final short val1 = (short) ((data[i * 4 + 1] << 8) | (data[i * 4] & OFFSET));
            final short val2 = (short) ((data[i * 4 + 3] << 8) | (data[i * 4 + 2] & OFFSET));
            shortData[i] = (short) (((int) val1 + (int) val2) / 2);
        }
        return shortData;
    }

    /**
     * Converts audio data in 'short' format (little-endian) to byte array.
     * 
     * @param data       Data buffer
     * @param length     Length of buffer that's used (number of shorts)
     * @return Byte array containing translated version
     */
    public static byte[] shortToByte(final short[] data, final int length) {
        byte[] byteData = new byte[length * 2];
        for (int i = 0; i < length; i++) {
            byteData[i * 2] = (byte) data[i];
            byteData[i * 2 + 1] = (byte) (data[i] >> 8);
        }
        return byteData;
    }
}
