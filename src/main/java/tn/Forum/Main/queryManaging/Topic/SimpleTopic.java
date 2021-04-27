package tn.Forum.Main.queryManaging.Topic;

import tn.Forum.Main.queryManaging.Category.SimpleCategory;

public interface SimpleTopic {

	long getId();

	String getTopicId();

	String getTopicTitle();

	String getTopicDescription();

	String getTopicLogo();

	String getTopicImage();

	SimpleCategory getCategory();

}
