package ru.maximoff.detect;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

public class Decoder {
	/**
	 * List of valid characters in URL.
	 */
	private static final List VALID_CHARACTERS = Arrays.asList(
        '-', '.', '_', '~', ':', '/', '?', '#', '[', ']', '@', '!',
        '$', '&', '\'', '(', ')', '*', '+', ',', ';', '='
	);

	/**
	 * Check that decoding was successful or not.
	 * @param url URL to check
	 * @return True if it's valid.
	 */
	private static boolean isMalformed(final String url) {
		for (char c : url.toCharArray()) {
			if (VALID_CHARACTERS.indexOf(c) == -1 && !Character.isLetterOrDigit(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Try to decode URL with specific encoding.
	 * @param url URL
	 * @param encoding Valid encoding
	 * @return Decoded URL or null of encoding is not write
	 * @throws java.io.UnsupportedEncodingException Throw if encoding does not support on your system.
	 */
	private static String _decodeUrl(final String url, final String encoding) {
		try {
			final String decoded = URLDecoder.decode(url, encoding);
			if (isMalformed(decoded)) {
				return decoded;
			}
		} catch (UnsupportedEncodingException ex) {
			throw new IllegalArgumentException("Illegal encoding: " + encoding);
		}
		return null;
	}

	/**
	 * Decode URL with most popular encodings for URL.
	 * @param url URL
	 * @return Decoded URL or original one if encoding does not support.
	 */
	public static String decodeUrl(final String url) {
		final String[] mostPopularEncodings = new String[] {"ISO-8859-1", "UTF-8", "GB2312", "CP1251"};
		return decodeUrl(url, mostPopularEncodings);
	}

	/**
	 * Decode URL with most popular encodings for URL.
	 * @param url URL
	 * @param encoding Encoding
	 * @return Decoded URL or original one if encoding does not support.
	 */
	public static String decodeUrl(final String url, final String... encoding) {
		for (String e:encoding) {
			final String decoded;
			if ((decoded = _decodeUrl(url, e)) != null) {
				return decoded;
			}
		}
		return url;
	}
}
