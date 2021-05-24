package ru.maximoff.detect;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class DetectEncoding {
	private static String[] encodigs = new String[] {"UTF-8", "CP1251", "ISO-8859-1"};

	public static String detectNameEncoding(String url) throws UnsupportedEncodingException {
		String name = getNameFromUrl(url);
		if (name.equals("")) {
			return encodigs[0];
		}
		for (String enc : encodigs) {
			String decode = URLDecoder.decode(name, enc);
			String encode = URLEncoder.encode(decode, enc);
			String encode2 = encode.replace("+", "%20");
			if (name.equals(encode) || name.equals(encode2)) {
				return enc;
			}
		}
		return encodigs[0];
	}

	private static String getNameFromUrl(String url) {
		String name = "";
		int p = url.lastIndexOf('/');
		if (p >= 0 && url.length() > p) {
			name = url.substring(p + 1);
			p = name.lastIndexOf('?');
			if (p >= 0) {
				name = name.substring(0, p);
			}
		}
		return name;
	}
}
