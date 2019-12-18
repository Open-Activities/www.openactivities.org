package org.openactivities;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.openactivities.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.topobyte.melon.paths.PathUtil;

public class Events
{

	final static Logger logger = LoggerFactory.getLogger(Events.class);

	public static final Events INSTANCE = new Events();

	private List<Event> events = new ArrayList<>();

	public List<Event> getEvents()
	{
		return events;
	}

	public void load() throws IOException
	{
		Path repoData = Config.INSTANCE.getRepoData();
		Path type1 = repoData.resolve("type1");

		FileCache fileCache = new FileCache();

		List<Path> files = PathUtil.list(type1);
		for (Path file : files) {
			logger.info("Loading file: " + file);
			try (InputStream input = Files.newInputStream(file)) {
				List<String> lines = IOUtils.readLines(input);
				String start = lines.get(0);
				String linksPattern = lines.get(1);
				List<Event> es = load(fileCache, start, linksPattern);
				events.addAll(es);
			}
		}
	}

	private List<Event> load(FileCache fileCache, String start,
			String linksPattern) throws MalformedURLException, IOException
	{
		List<Event> allEvents = new ArrayList<>();
		Downloader downloader = new Downloader(fileCache, start, linksPattern);
		downloader.crawl(file -> {
			try {
				logger.info("Loading event file: " + file);
				List<Event> events = EventParsing.parse(file);
				allEvents.addAll(events);
			} catch (Throwable e) {
				logger.warn("Error while parsing file " + file);
				e.printStackTrace();
			}
		});
		return allEvents;
	}

}
