package okhttp3;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;

import static okhttp3.HttpUrl.FORM_ENCODE_SET;
import static okhttp3.HttpUrl.percentDecode;

public class FormUtfBody extends RequestBody {
    private static final MediaType CONTENT_TYPE =
            MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");

    private final List<String> encodedNames;
    private final List<String> encodedValues;

    FormUtfBody(List<String> encodedNames, List<String> encodedValues) {
        this.encodedNames = Util.immutableList(encodedNames);
        this.encodedValues = Util.immutableList(encodedValues);
    }

    /**
     * The number of key-value pairs in this form-encoded body.
     */
    public int size() {
        return encodedNames.size();
    }

    public String encodedName(int index) {
        return encodedNames.get(index);
    }

    public String name(int index) {
        return percentDecode(encodedName(index), true);
    }

    public String encodedValue(int index) {
        return encodedValues.get(index);
    }

    public String value(int index) {
        return percentDecode(encodedValue(index), true);
    }

    @Override
    public MediaType contentType() {
        return CONTENT_TYPE;
    }

    @Override
    public long contentLength() {
        return writeOrCountBytes(null, true);
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        writeOrCountBytes(sink, false);
    }

    /**
     * Either writes this request to {@code sink} or measures its content length. We have one method
     * do double-duty to make sure the counting and content are consistent, particularly when it comes
     * to awkward operations like measuring the encoded length of header strings, or the
     * length-in-digits of an encoded integer.
     */
    private long writeOrCountBytes(@Nullable BufferedSink sink, boolean countBytes) {
        long byteCount = 0L;

        Buffer buffer;
        if (countBytes) {
            buffer = new Buffer();
        } else {
            buffer = sink.buffer();
        }

        for (int i = 0, size = encodedNames.size(); i < size; i++) {
            if (i > 0) buffer.writeByte('&');
            buffer.writeUtf8(encodedNames.get(i));
            buffer.writeByte('=');
            buffer.writeUtf8(encodedValues.get(i));
        }

        if (countBytes) {
            byteCount = buffer.size();
            buffer.clear();
        }

        return byteCount;
    }

    public static final class Builder {
        private final List<String> names = new ArrayList<>();
        private final List<String> values = new ArrayList<>();

        public Builder add(String name, String value) {
            names.add(HttpUrl.canonicalize(name, FORM_ENCODE_SET, false, false, true, true));
            values.add(HttpUrl.canonicalize(value, FORM_ENCODE_SET, false, false, true, true));
            return this;
        }

        public Builder addEncoded(String name, String value) {
            names.add(HttpUrl.canonicalize(name, FORM_ENCODE_SET, true, false, true, true));
            values.add(HttpUrl.canonicalize(value, FORM_ENCODE_SET, true, false, true, true));
            return this;
        }

        public FormUtfBody build() {
            return new FormUtfBody(names, values);
        }
    }
}