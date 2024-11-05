﻿CREATE TABLE `Schedule` (
	`id`	int	NOT NULL,
	`UserName`	VARCHAR	NULL,
	`Password`	VARCHAR	NULL,
	`Title`	VARCHAR	NOT NULL,
	`Content`	VARCHAR	NULL,
	`CreateDate`	TIMESTAMP	NULL,
	`UpdateDate`	TIMESTAMP	NULL
);

ALTER TABLE `Schedule` ADD CONSTRAINT `PK_SCHEDULE` PRIMARY KEY (
	`id`
);

