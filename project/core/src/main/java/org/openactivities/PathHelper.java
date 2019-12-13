package org.openactivities;

import de.topobyte.webpaths.WebPath;
import de.topobyte.webpaths.WebPaths;

public class PathHelper
{

	public static WebPath imprint()
	{
		return WebPaths.get("imprint");
	}

	public static WebPath privacyPolicy()
	{
		return WebPaths.get("privacy-policy");
	}

	public static WebPath faq()
	{
		return WebPaths.get("faq");
	}

}
