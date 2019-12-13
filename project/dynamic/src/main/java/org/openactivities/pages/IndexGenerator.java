package org.openactivities.pages;

import java.io.IOException;

import org.openactivities.BaseGenerator;
import org.openactivities.Markdown;

import de.topobyte.pagegen.core.Context;
import de.topobyte.webpaths.WebPath;

public class IndexGenerator extends BaseGenerator
{

	public IndexGenerator(Context context, WebPath path)
	{
		super(context, path);
	}

	@Override
	public void generate() throws IOException
	{
		super.generate();

		menu();

		Markdown.renderResource(content, "markdown/index.md");

		footer();
	}

}
