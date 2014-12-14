package jp.niconico.comment.util;

import java.util.Map;

import net.unit8.sastruts.UrlRewriter;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.util.tiger.CollectionsUtil;

public class UrlRewriterUtil {

	public static String urlFor(String controller, String action) {

		return urlFor(controller, action, null, null);
	}

	public static String urlFor(String controller, String action, BeanMap params) {

		return urlFor(controller, action, params, null);
	}

	public static String urlFor(String controller, String action, BeanMap params, String hash) {
		Map<String, Object> opts = CollectionsUtil.newHashMap();
		if (MapUtils.isNotEmpty(params)) {
			opts.putAll(params);
		}
		opts.put("controller", controller);
		opts.put("action", action);

		if (StringUtils.isNotEmpty(hash)) {
			opts.put("anchor", hash);
		}
		opts.put("skip_context_path", true);

		return UrlRewriter.urlFor(opts);
	}

}
