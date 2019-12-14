package org.openactivities.pages;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.openactivities.BaseGenerator;
import org.openactivities.Events;
import org.openactivities.model.Event;
import org.openactivities.model.Location;

import de.topobyte.jsoup.HTML;
import de.topobyte.jsoup.components.Div;
import de.topobyte.jsoup.components.P;
import de.topobyte.jsoup.components.bootstrap3.CollapsiblePanel;
import de.topobyte.pagegen.core.Context;
import de.topobyte.webpaths.WebPath;

public class ListGenerator extends BaseGenerator
{

	public ListGenerator(Context context, WebPath path)
	{
		super(context, path);
	}

	@Override
	public void generate() throws IOException
	{
		super.generate();

		menu();

		Events events = Events.INSTANCE;
		List<Event> list = new ArrayList<>();
		list.addAll(events.getEvents());

		Collections.sort(list,
				(o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate()));

		for (Event event : list) {
			entry(event);
		}

		footer();
	}

	private static DateTimeFormatter formatter = DateTimeFormatter
			.ofPattern("EEEE dd.MM.yyyy HH:mm").withLocale(Locale.GERMAN);

	private void entry(Event event)
	{
		CollapsiblePanel panel = content
				.ac(new CollapsiblePanel(false, true, true));
		panel.getPanelHead().appendText(String.format("%s %s",
				formatter.format(event.getStartDate()), event.getName()));
		Div panelBody = panel.getPanelBody();

		Location location = event.getLocation();
		P p = panelBody.ac(HTML.p());
		p.appendText("Ort: ");
		p.appendText(location.getLocationName());

		p = panelBody.ac(HTML.p());
		p.appendText("Adresse: ");
		p.appendText(location.getAdress());
	}

}
