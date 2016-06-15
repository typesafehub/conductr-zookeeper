# conductr-zookeeper

## Overview

This project provides a [ConductR](httpw://conductr.typesafe.com) unit of deployment (bundle) for [Apache Zookeepers](http://zookeeper.apache.org/). By using this bundle you can deploy Zookeeper over many nodes and it will scale automatically.

Quick start:

```
conduct load zookeeper
conduct run zookeeper --scale 3
```


### Zookeeper nodes

The bundle will assume that the Zookeeper directories reside outside of the bundle itself, under `/var/lib`.

You must ensure that the nodes where Kafka bundles will run have these directories established and owned by the conductr user e.g.:

```
sudo mkdir /var/lib/zookeeper
