package org.openactivities;

import java.nio.file.Path;

public class Config
{

	public static final Config INSTANCE = new Config();

	private Path repoData;

	public Path getRepoData()
	{
		return repoData;
	}

	public void setRepoData(Path path)
	{
		this.repoData = path;
	}

}
