package org.openactivities;

import static de.topobyte.jsoup.HTML.a;

import org.jsoup.nodes.Element;

import de.topobyte.jsoup.HTML;
import de.topobyte.jsoup.bootstrap3.components.Menu;
import de.topobyte.jsoup.components.A;
import de.topobyte.jsoup.components.Img;
import de.topobyte.pagegen.core.LinkResolver;
import de.topobyte.webpaths.WebPaths;

public class MainMenu extends Menu
{

	public MainMenu(LinkResolver resolver)
	{
		A brandIcon = a("/");
		brandIcon.attr("style", "float:left");
		navbarHeader.ap(brandIcon);

		Img image = HTML.img(resolver.getLink(WebPaths.get("images/icon.svg")));
		image.attr("width", "50px");
		image.addClass("img-responsive");
		image.attr("style", "padding:5px");
		brandIcon.ap(image);

		A brand = a("/");
		brand.appendText("OpenActivities");

		addBrand(brand);

		String listLink = resolver.getLink(WebPaths.get("/list"));
		Element linkList = a(listLink).inner("Liste");
		addMain(linkList, false);

		String aboutLink = "/";

		Element linkAbout = a(aboutLink).inner("About");
		addRight(linkAbout, false);
	}

}
