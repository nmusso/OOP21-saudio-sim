package model.filter;

import model.source.Source;
import model.source.SourceType;
import static org.lwjgl.openal.EXTEfx.*;

public class FilterImpl extends AbstractFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyFilter(final Source source, final SourceType type) {
        switch (type) {
        case LOW:
            lowpass(source);
            break;
        case MID:
            bandpass(source);
            break;
        case HIGH:
            highpass(source);
            break;
        default:
            break;
        }
    }

    /**
     * Apply lowpass filter on source.
     * @param source  the source
     */
    private void lowpass(final Source source) {
        initFilterBuffer();
        final int filter = getFilter().get(0);

        alFilteri(filter, AL_FILTER_TYPE, AL_FILTER_LOWPASS);
        alFilterf(filter, AL_LOWPASS_GAIN, LOWPASS_VALUE);
        alFilterf(filter, AL_LOWPASS_GAINHF, LOWPASS_VALUE);

        setOnSource(filter, source);
    }

    /**
     * Apply highpass filter on source.
     * @param source  the source
     */
    public void highpass(final Source source) {
        initFilterBuffer();
        final int filter = getFilter().get(0);

        alFilteri(filter, AL_FILTER_TYPE, AL_FILTER_HIGHPASS);
        alFilterf(filter, AL_HIGHPASS_GAIN, HIGHPASS_VALUE);
        alFilterf(filter, AL_HIGHPASS_GAINLF, HIGHPASS_VALUE);

        setOnSource(filter, source);

    }

    /**
     * Apply bandpass filter on source.
     * @param source  the source
     */
    public void bandpass(final Source source) {
        initFilterBuffer();
        final int filter = getFilter().get(0);

        alFilteri(filter, AL_FILTER_TYPE, AL_FILTER_BANDPASS);
        alFilterf(filter, AL_BANDPASS_GAIN, BANDPASS_VALUE);
        alFilterf(filter, AL_BANDPASS_GAINLF, BANDPASS_VALUE);
        alFilterf(filter, AL_BANDPASS_GAINHF, BANDPASS_VALUE);

        setOnSource(filter, source);

    }
}
