package org.openactivities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.openactivities.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		String startLido = "https://www.lido-berlin.de";
		String startAstra = "https://www.astra-berlin.de";
		String linksPattern = "/events/.*";
		FileCache fileCache = new FileCache();
		List<Event> eventsLido = load(fileCache, startLido, linksPattern);
		List<Event> eventsAstra = load(fileCache, startAstra, linksPattern);
		events.addAll(eventsLido);
		events.addAll(eventsAstra);
	}

	private List<Event> load(FileCache fileCache, String start,
			String linksPattern) throws MalformedURLException, IOException
	{
		List<Event> allEvents = new ArrayList<>();
		Downloader downloader = new Downloader(fileCache, start, linksPattern);
		downloader.crawl(file -> {
			try {
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
